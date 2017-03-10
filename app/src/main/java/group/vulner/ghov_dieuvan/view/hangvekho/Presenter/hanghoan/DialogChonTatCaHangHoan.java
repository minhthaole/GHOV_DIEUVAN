package group.vulner.ghov_dieuvan.view.hangvekho.Presenter.hanghoan;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import group.vulner.ghov_dieuvan.R;
import group.vulner.ghov_dieuvan.Utils;
import group.vulner.ghov_dieuvan.model.file.SharepreferenceManager;
import group.vulner.ghov_dieuvan.view.MainActivity;
import group.vulner.ghov_dieuvan.view.hangvekho.model.NhanVienGiaoHang_Hoan;

import static group.vulner.ghov_dieuvan.Utils.CheckRespone;
import static group.vulner.ghov_dieuvan.view.hangvekho.Presenter.hanghoan.CustomAdapterXacNhanTatCaHangHoan.lstIDChecked_Hoan;
import static group.vulner.ghov_dieuvan.view.hangvekho.view.FragmentHangHoan.lstDonHangHoan;

/**
 * Created by TuTV on 3/6/2017.
 */

public class DialogChonTatCaHangHoan extends android.support.v4.app.DialogFragment {
    ArrayList<NhanVienGiaoHang_Hoan> lstNhanVienGiaoHang_hoan = new ArrayList<>();
    NhanVienGiaoHang_Hoan nhanVienGiaoHang_hoan = new NhanVienGiaoHang_Hoan();
    Context context;

    Button btnCancel, btnSubmit, btnSubmitAll;
    ListView lv_hien_thi_xac_nhan_tat_ca;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialogfragment_xac_nhan_tat_ca_hanghoan, container, false);
        getDialog().setTitle("This is title dialog");
        btnCancel = (Button) view.findViewById(R.id.btn_cancel_dialog_hanghoan);

        btnSubmit = (Button) view.findViewById(R.id.btn_submit_dialog_hoan);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AsyntaskXacNhanTatCaDonHangDonHang_Hoan asyntaskXacNhanTatCaDonHangDonHang_hoan = new
                        AsyntaskXacNhanTatCaDonHangDonHang_Hoan(getContext());
                asyntaskXacNhanTatCaDonHangDonHang_hoan.execute();



            }
        });

        CustomAdapterXacNhanTatCaHangHoan arrayAdapter = new CustomAdapterXacNhanTatCaHangHoan(getContext(), R.layout
                .custom_listview_nhan_tat_ca_hang_hoan, lstDonHangHoan);
        lv_hien_thi_xac_nhan_tat_ca = (ListView) view.findViewById(R.id.lv_hien_thi_xac_nhan_tat_ca);
        lv_hien_thi_xac_nhan_tat_ca.setAdapter(arrayAdapter);
        return view;
    }
    public class AsyntaskXacNhanTatCaDonHangDonHang_Hoan extends AsyncTask<Void, Void, String> {
        Context context;
        ProgressDialog progressDialog;
        public AsyntaskXacNhanTatCaDonHangDonHang_Hoan(Context context) {
            this.context = context;
            progressDialog = new ProgressDialog(context);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.setTitle("Xác nhận hàng hoàn!");
            progressDialog.setMessage("Vui lòng đợi...");
            progressDialog.show();
        }

        @Override
        protected String doInBackground(Void... params) {

            SharepreferenceManager manager = new SharepreferenceManager(context);
            String sesstion = manager.getSession("giá trị mặc định");
            String GiaTriTraVe = null;
            String listID = "";
            for (int i = 0; i < lstIDChecked_Hoan.size(); i++) {
                if (i == lstIDChecked_Hoan.size() - 1) {
                    listID += lstIDChecked_Hoan.get(i);
                } else {
                    listID += lstIDChecked_Hoan.get(i) + ",";
                }
                Log.e("listID", listID);
            }

                GiaTriTraVe = "";
                String UrlXacNhan = "http://www.giaohangongvang.com/api/dieuvan/xac-nhan-hang-hoan-ve-kho";
                HttpClient client = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost(UrlXacNhan);
                MultipartEntity entity = new MultipartEntity();

                try {
                    entity.addPart("session", new StringBody(Utils.encodeBase64(sesstion)));
                    entity.addPart("list", new StringBody(Utils.encodeBase64(listID)));
                    Log.e(" entity", entity.toString());
                    httpPost.setEntity(entity);
                    HttpResponse response = client.execute(httpPost);
                    Log.i("response", entity.toString() + "");
                    if (response == null
                        || response.getStatusLine().getStatusCode() != 200) {
                        return null;
                    } else {
                        ByteArrayOutputStream DataOut = new ByteArrayOutputStream();
                        response.getEntity().writeTo(DataOut);
                        GiaTriTraVe = DataOut.toString();
                        Log.e("request hengiao", GiaTriTraVe.toString());
                        DataOut.close();
                    }

                }
                catch (Exception e) {
                    e.printStackTrace();
//                }
            }
//            }
            return GiaTriTraVe;

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.e("Xác nhận hoàn", s);
            String timeStamp = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss").format(Calendar.getInstance().getTime());
            try {
                if (CheckRespone(s)) {
                    Log.e("return hoan ", s);
                    Toast.makeText(context, "Xác nhận hoàn" + "\n" + timeStamp, Toast.LENGTH_SHORT).show();
                    dismiss();
                    Intent intent = new Intent(context, MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    context.startActivity(intent);
                } else {
                    Toast.makeText(context, "Xác nhận thất bại!", Toast.LENGTH_SHORT).show();
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            progressDialog.dismiss();
        }
    }
}
