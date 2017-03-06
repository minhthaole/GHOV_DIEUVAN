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
import group.vulner.ghov_dieuvan.view.hangvekho.model.DonHang_HHG;
import group.vulner.ghov_dieuvan.view.hangvekho.model.NhanVienGiaohang_HHG;

import static group.vulner.ghov_dieuvan.Utils.CheckRespone;

/**
 * Created by TuTV on 3/2/2017.
 */

public class ExpandableListViewHangHenGiao extends BaseExpandableListAdapter {
    private static final int MY_REQUEST_CALL_PHONE = 123;
    Context context;
    List<NhanVienGiaohang_HHG> lstNhanVienGiaoHang_HHG_;
    HashMap<NhanVienGiaohang_HHG, List<DonHang_HHG>> hashMapDonHang_HHG;

    public ExpandableListViewHangHenGiao(Context context, List<NhanVienGiaohang_HHG> lstNhanVienGiaoHang_HHG_, HashMap<NhanVienGiaohang_HHG, List<DonHang_HHG>> hashMapDonHang_HHG) {
        this.context = context;
        this.lstNhanVienGiaoHang_HHG_ = lstNhanVienGiaoHang_HHG_;
        this.hashMapDonHang_HHG = hashMapDonHang_HHG;
    }

    @Override
    public int getGroupCount() {
        return lstNhanVienGiaoHang_HHG_.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return hashMapDonHang_HHG.get(lstNhanVienGiaoHang_HHG_.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return lstNhanVienGiaoHang_HHG_.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return hashMapDonHang_HHG.get(lstNhanVienGiaoHang_HHG_.get(groupPosition)).get(childPosition);
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

        tvTenNV.setText(lstNhanVienGiaoHang_HHG_.get(groupPosition).getTenNhanVienGiao());
        return view;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View view, final ViewGroup parent) {
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.don_hang_hanghengiao, parent, false);
        }
        final TextView tvTenNguoiGui, tvTenNguoiNhan, tvDiaChiNhan, tvGhiChu;
        tvTenNguoiGui = (TextView) view.findViewById(R.id.tv_ten_nguoi_gui_hangvekho);
        tvTenNguoiNhan = (TextView) view.findViewById(R.id.tv_ten_nguoi_nhan_hangvekho);
        tvDiaChiNhan = (TextView) view.findViewById(R.id.tv_dia_chi_nguoi_nhan_hangvekho);
        tvGhiChu = (TextView) view.findViewById(R.id.tv_ghi_chu_hangvekho);
        final EditText edtSuaGhiChu = (EditText) view.findViewById(R.id.edt_sua_ghi_chu);
        final Boolean[] click = {false};


        final Button btnSuaGhiChu, btnGoiNguoiNhan, btnXacNhan;

        tvTenNguoiGui.setText(hashMapDonHang_HHG.get(lstNhanVienGiaoHang_HHG_.get(groupPosition)).get(childPosition).getIdKhachHang_HHG());
        tvTenNguoiNhan.setText(hashMapDonHang_HHG.get(lstNhanVienGiaoHang_HHG_.get(groupPosition)).get(childPosition).getTenNguoiNhan_HHG());

        tvDiaChiNhan.setText(hashMapDonHang_HHG.get(lstNhanVienGiaoHang_HHG_.get(groupPosition)).get(childPosition).getDoiDiaChiNhan_HHG());

        String ghiChuOgri = hashMapDonHang_HHG.get(lstNhanVienGiaoHang_HHG_.get(groupPosition)).get(childPosition).getGhiChu_HHG();
        String ghiChu = ghiChuOgri.replace("\\n", "\n");
//        Log.e("id", hashMapDonHang_HHG.get(lstNhanVienGiaoHang_HHG_.get(groupPosition)).get(childPosition).getId_HHG());
        tvGhiChu.setText(ghiChu);

        final String listID = hashMapDonHang_HHG.get(lstNhanVienGiaoHang_HHG_.get(groupPosition)).get(childPosition).getId_HHG();

        btnSuaGhiChu = (Button) view.findViewById(R.id.btn_sua_ghi_chu_hangvekho);
        btnGoiNguoiNhan = (Button) view.findViewById(R.id.btn_goi_nguoi_nhan_hangvekho);
        btnXacNhan = (Button) view.findViewById(R.id.btn_xac_nhan_hangvekho);
        final String sdtNguoiNhan = "tel:" + hashMapDonHang_HHG.get(lstNhanVienGiaoHang_HHG_.get(groupPosition)).get(childPosition).getSdtNguoiNhan_HHG();
        //
        final InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        btnSuaGhiChu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
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
                AsyntaskXacNhanDonHangHenGiao asyntaskXacNhanDonHangHenGiao = new AsyntaskXacNhanDonHangHenGiao(context);
                asyntaskXacNhanDonHangHenGiao.execute(listID);
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

    public class AsyntaskXacNhanDonHangHenGiao extends AsyncTask<String, Void, String> {
        Context context;

        public AsyntaskXacNhanDonHangHenGiao(Context context) {
            this.context = context;
        }

        @Override
        protected String doInBackground(String... params) {
            SharepreferenceManager manager = new SharepreferenceManager(context);
            String sesstion = manager.getSession("giá trị mặc định");
            String GiaTriTraVe = "";
            String value = params[0];
            String UrlXacNhan = "http://www.giaohangongvang.com/api/dieuvan/xac-nhan-hang-hen-ngay";
            HttpClient client = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(UrlXacNhan);
            MultipartEntity entity = new MultipartEntity();

//            String sessionBase64 = "YWVhMDQzNWNmNTVkMzI2ZmYyOWY4YzA2NzNmYTllNmU=";
            Log.e("id xac nhan", value);
            try {
                entity.addPart("session", new StringBody(Utils.encodeBase64(sesstion)));
                entity.addPart("list", new StringBody(Utils.encodeBase64(value)));
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
            return GiaTriTraVe;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.e("xac nhan hen giao: ", s);
            String timeStamp = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss").format(Calendar.getInstance().getTime());
            try {
                if (CheckRespone(s)) {
                    Toast.makeText(context, "Xác nhận hẹn giao" + "\n" + timeStamp, Toast.LENGTH_SHORT).show();
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
