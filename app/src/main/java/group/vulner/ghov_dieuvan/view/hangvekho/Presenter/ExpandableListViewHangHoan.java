package group.vulner.ghov_dieuvan.view.hangvekho.Presenter;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
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
import java.util.HashMap;
import java.util.List;

import group.vulner.ghov_dieuvan.R;
import group.vulner.ghov_dieuvan.Utils;
import group.vulner.ghov_dieuvan.model.file.SharepreferenceManager;
import group.vulner.ghov_dieuvan.view.MainActivity;
import group.vulner.ghov_dieuvan.view.hangvekho.model.DonHang_Hoan;
import group.vulner.ghov_dieuvan.view.hangvekho.model.NhanVienGiaoHang_Hoan;

import static group.vulner.ghov_dieuvan.Utils.CheckRespone;

/**
 * Created by TuTV on 3/4/2017.
 */

public class ExpandableListViewHangHoan extends BaseExpandableListAdapter {
    TextView tvTenNguoiGui, tvTenNguoiNhan, tvDiaChiNhan, tvGhiChu;
    EditText edtSuaGhiChu;
    private static final int MY_REQUEST_CALL_PHONE = 123;
    Boolean[] click = {false};
    Context context;
    List<NhanVienGiaoHang_Hoan> lstNhanVienGiaoHang_Hoan_;
    HashMap<NhanVienGiaoHang_Hoan, List<DonHang_Hoan>> hashMapDonHang_Hoan;

    public ExpandableListViewHangHoan(Context context, List<NhanVienGiaoHang_Hoan> lstNhanVienGiaoHang_Hoan_,
                                      HashMap<NhanVienGiaoHang_Hoan, List<DonHang_Hoan>> hashMapDonHang_Hoan) {
        this.context = context;
        this.lstNhanVienGiaoHang_Hoan_ = lstNhanVienGiaoHang_Hoan_;
        this.hashMapDonHang_Hoan = hashMapDonHang_Hoan;
    }

    @Override
    public int getGroupCount() {
        return lstNhanVienGiaoHang_Hoan_.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return hashMapDonHang_Hoan.get(lstNhanVienGiaoHang_Hoan_.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return lstNhanVienGiaoHang_Hoan_.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return hashMapDonHang_Hoan.get(lstNhanVienGiaoHang_Hoan_.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View view, ViewGroup parent) {
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.ten_nhan_vien_hanghengiao, parent, false);
        }
        TextView tvTenNV = (TextView) view.findViewById(R.id.tv_ten_nhan_vien_hanghengiao);

        tvTenNV.setText(lstNhanVienGiaoHang_Hoan_.get(groupPosition).getTenNhanVienGiao());
        return view;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View view, final ViewGroup parent) {
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.don_hang_hanghengiao, parent, false);
        }

        LinearLayout lnGhiChu,lnChiChuContainsTxtEdt;
        lnGhiChu= (LinearLayout) view.findViewById(R.id.ln_ghichu);
        lnChiChuContainsTxtEdt= (LinearLayout) lnGhiChu.findViewById(R.id.lnGhiChuContainsTxtEdt);
        tvTenNguoiGui = (TextView) view.findViewById(R.id.tv_ten_nguoi_gui_hangvekho);
        tvTenNguoiNhan = (TextView) view.findViewById(R.id.tv_ten_nguoi_nhan_hangvekho);
        tvDiaChiNhan = (TextView) view.findViewById(R.id.tv_dia_chi_nguoi_nhan_hangvekho);
        tvGhiChu = (TextView) view.findViewById(R.id.tv_ghi_chu_hangvekho);
//        edtSuaGhiChu = (EditText) view.findViewById(R.id.edt_sua_ghi_chu_123);
//        edtSuaGhiChu.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(context, "Ahihii", Toast.LENGTH_SHORT).show();
//            }
//        });

        final Button btnSuaGhiChu, btnGoiNguoiNhan, btnXacNhan;

        tvTenNguoiGui.setText(hashMapDonHang_Hoan.get(lstNhanVienGiaoHang_Hoan_.get(groupPosition)).get(childPosition).getId_Hoan());
        tvTenNguoiNhan.setText(hashMapDonHang_Hoan.get(lstNhanVienGiaoHang_Hoan_.get(groupPosition)).get(childPosition).getTenNguoiNhanHang_Hoan());

        tvDiaChiNhan.setText(hashMapDonHang_Hoan.get(lstNhanVienGiaoHang_Hoan_.get(groupPosition)).get(childPosition).getDoiDiaChiNhan_Hoan());

        String ghiChuOgri = hashMapDonHang_Hoan.get(lstNhanVienGiaoHang_Hoan_.get(groupPosition)).get(childPosition).getGhiChu_Hoan();
        String ghiChu = ghiChuOgri.replace("\\n", "\n");
        tvGhiChu.setText(ghiChu);
        final String listID = hashMapDonHang_Hoan.get(lstNhanVienGiaoHang_Hoan_.get(groupPosition)).get(childPosition).getId_Hoan();

        btnSuaGhiChu = (Button) view.findViewById(R.id.btn_sua_ghi_chu_hangvekho);
        btnGoiNguoiNhan = (Button) view.findViewById(R.id.btn_goi_nguoi_nhan_hangvekho);
        btnXacNhan = (Button) view.findViewById(R.id.btn_xac_nhan_hangvekho);
        final String sdtNguoiNhan = "tel:" + hashMapDonHang_Hoan.get(lstNhanVienGiaoHang_Hoan_.get(groupPosition)).get
                (childPosition).getSdtNguoiNhan_Hoan();
        //
        btnSuaGhiChu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        //
        btnGoiNguoiNhan.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Xác nhận gọi cho khách hàng!");
                builder.setPositiveButton("Xác nhận", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.CALL_PHONE}, MY_REQUEST_CALL_PHONE);
                        }
                        Intent intent = new Intent(Intent.ACTION_CALL);
                        intent.setData(Uri.parse(sdtNguoiNhan));
                        context.startActivity(intent);
                        Toast.makeText(context, "Xác nhận", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(context, "Hủy bỏ", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
            }
        });
        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                AsyntaskXacNhanDonHangHoan asyntaskXacNhanDonHangHoan = new AsyntaskXacNhanDonHangHoan(context);
                asyntaskXacNhanDonHangHoan.execute(listID);
                Intent intent = new Intent(context, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                context.startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    public class AsyntaskXacNhanDonHangHoan extends AsyncTask<String, Void, String> {
        Context context;

        public AsyntaskXacNhanDonHangHoan(Context context) {
            this.context = context;
        }

        @Override
        protected String doInBackground(String... params) {
            SharepreferenceManager manager = new SharepreferenceManager(context);
            String sesstion = manager.getSession("giá trị mặc định");
            String GiaTriTraVe = "";
            String value = params[0];
            String UrlXacNhan = "http://www.giaohangongvang.com/api/dieuvan/xac-nhan-hang-hoan-ve-kho";
            HttpClient client = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(UrlXacNhan);
            MultipartEntity entity = new MultipartEntity();

            Log.e("id xac nhan", value);
            try {
                entity.addPart("session", new StringBody(Utils.encodeBase64(sesstion)));
                entity.addPart("list", new StringBody(Utils.encodeBase64(value)));
                Log.e(" entity", entity.toString());
                Log.e("sesion", Utils.encodeBase64(sesstion));
                Log.e("id", Utils.encodeBase64(value).toString());
                httpPost.setEntity(entity);
                HttpResponse response = client.execute(httpPost);
                Log.e("response", entity.toString() + "");
                if (response == null
                    || response.getStatusLine().getStatusCode() != 200) {
                    return null;
                } else {
                    ByteArrayOutputStream DataOut = new ByteArrayOutputStream();
                    response.getEntity().writeTo(DataOut);
                    GiaTriTraVe = DataOut.toString();
                    Log.e("request hoan", GiaTriTraVe.toString());
                    DataOut.close();
                }

            }
            catch (Exception e) {
                e.printStackTrace();
            }
            return GiaTriTraVe;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            String timeStamp = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss").format(Calendar.getInstance().getTime());
            try {
                if (CheckRespone(s)) {
                    Toast.makeText(context, "Xác nhận hàng hoàn" + "\n" + timeStamp, Toast.LENGTH_SHORT).show();
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
