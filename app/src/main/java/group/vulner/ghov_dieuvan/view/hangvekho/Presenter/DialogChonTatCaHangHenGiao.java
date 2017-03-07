package group.vulner.ghov_dieuvan.view.hangvekho.Presenter;

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
import java.util.Calendar;

import group.vulner.ghov_dieuvan.R;
import group.vulner.ghov_dieuvan.Utils;
import group.vulner.ghov_dieuvan.model.file.SharepreferenceManager;
import group.vulner.ghov_dieuvan.view.MainActivity;

import static group.vulner.ghov_dieuvan.Utils.CheckRespone;
import static group.vulner.ghov_dieuvan.view.hangvekho.Presenter.CustomAdapterXacNhanTatCaHangHenGiao.lstIDChecked_HenGiao;
import static group.vulner.ghov_dieuvan.view.hangvekho.view.FragmentHangHenGiao.lstDonHang_HHG;

/**
 * Created by TuTV on 3/7/2017.
 */

public class DialogChonTatCaHangHenGiao extends android.support.v4.app.DialogFragment {
    Context context;
    Button btnCancelDiaLogHangHenGiao, btnSubmitDialogHangHenGiao, btnSubmitAllDialogHangHenGiao;
    ListView lv_hien_thi_xac_nhan_tat_ca_hanghengiao;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.dialogfragment_nhan_tat_ca_hanghengiao, container, false);

        getDialog().setTitle("This is title dialog");



        btnCancelDiaLogHangHenGiao = (Button) view.findViewById(R.id.btn_cancel_dialog_hanghengiao);
        btnSubmitDialogHangHenGiao = (Button) view.findViewById(R.id.btn_submit_dialog_hanghengiao);
        btnCancelDiaLogHangHenGiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        btnSubmitDialogHangHenGiao = (Button) view.findViewById(R.id.btn_submit_dialog_hanghengiao);
        btnSubmitDialogHangHenGiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AsyntaskXacNhanTatCaDonHangDonHang_HHG asyntaskXacNhanTatCaDonHangDonHang_hhg=new
                        AsyntaskXacNhanTatCaDonHangDonHang_HHG(getContext());
                asyntaskXacNhanTatCaDonHangDonHang_hhg.execute();

            }
        });
        CustomAdapterXacNhanTatCaHangHenGiao arrayAdapter = new CustomAdapterXacNhanTatCaHangHenGiao(getContext(), R.layout
                .custom_listview_nhan_tat_ca_hanghengiao, lstDonHang_HHG);
        lv_hien_thi_xac_nhan_tat_ca_hanghengiao = (ListView) view.findViewById(R.id.lv_hien_thi_xac_nhan_tat_ca_hanghengiao);
        lv_hien_thi_xac_nhan_tat_ca_hanghengiao.setAdapter(arrayAdapter);
        return view;
    }
    public class AsyntaskXacNhanTatCaDonHangDonHang_HHG extends AsyncTask<Void, Void, String> {
        Context context;

        public AsyntaskXacNhanTatCaDonHangDonHang_HHG(Context context) {
            this.context = context;
        }

        @Override
        protected String doInBackground(Void... params) {

            SharepreferenceManager manager = new SharepreferenceManager(context);
            String sesstion = manager.getSession("giá trị mặc định");
            String GiaTriTraVe = null;
            for (int i = 0; i < lstIDChecked_HenGiao.size(); i++) {
                GiaTriTraVe = "";
                String UrlXacNhan = "http://www.giaohangongvang.com/api/dieuvan/xac-nhan-hang-hen-ngay";
                HttpClient client = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost(UrlXacNhan);
                MultipartEntity entity = new MultipartEntity();

                try {
                    entity.addPart("session", new StringBody(Utils.encodeBase64(sesstion)));
                    entity.addPart("list", new StringBody(Utils.encodeBase64(lstIDChecked_HenGiao.get(i))));
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
                }
            }
            return GiaTriTraVe;

        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            String timeStamp = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss").format(Calendar.getInstance().getTime());
            try {
                if (CheckRespone(s)) {
                    Toast.makeText(context, "Xác nhận hẹn giao" + "\n" + timeStamp, Toast.LENGTH_SHORT).show();
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
        }
    }
}
