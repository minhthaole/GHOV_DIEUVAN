package group.vulner.ghov_dieuvan.view.hangvekho.Presenter.hanghoan;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.ByteArrayOutputStream;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import group.vulner.ghov_dieuvan.R;
import group.vulner.ghov_dieuvan.Utils;
import group.vulner.ghov_dieuvan.model.file.SharepreferenceManager;
import group.vulner.ghov_dieuvan.view.hangvekho.model.DonHang_Hoan;
import group.vulner.ghov_dieuvan.view.hangvekho.model.NhanVienGiaoHang_Hoan;

import static group.vulner.ghov_dieuvan.Utils.CheckRespone;

/**
 * Created by TuTV on 3/4/2017.
 */

public class ExpandableListViewHangHoan extends BaseExpandableListAdapter {
    private FragmentManager Manager;
    Button btnSuaGhiChu, btnGoiNguoiNhan, btnXacNhan, btnNhanTatCa;
    Utils utils = new Utils();

    private static final int MY_REQUEST_CALL_PHONE = 123;
    Boolean[] click = {false};
    Context context;
    List<NhanVienGiaoHang_Hoan> lstNhanVienGiaoHang_Hoan_;
    HashMap<NhanVienGiaoHang_Hoan, List<DonHang_Hoan>> hashMapDonHang_Hoan;
    private InterfaceHangHoan interfaceHangHoan;
    public ExpandableListViewHangHoan(Context context, List<NhanVienGiaoHang_Hoan> lstNhanVienGiaoHang_Hoan_,
                                      HashMap<NhanVienGiaoHang_Hoan, List<DonHang_Hoan>> hashMapDonHang_Hoan, FragmentManager
                                              Manager, InterfaceHangHoan interfaceHangHoan) {
        this.context = context;
        this.lstNhanVienGiaoHang_Hoan_ = lstNhanVienGiaoHang_Hoan_;
        this.hashMapDonHang_Hoan = hashMapDonHang_Hoan;
        this.Manager = Manager;
        this.interfaceHangHoan = interfaceHangHoan;
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
        final TextView tvTenNguoiGui, tvTenNguoiNhan, tvDiaChiNhan, tvGhiChu,tvGhiChuSauSua;
        tvTenNguoiGui = (TextView) view.findViewById(R.id.tv_ten_nguoi_gui_hangvekho);
        tvTenNguoiNhan = (TextView) view.findViewById(R.id.tv_ten_nguoi_nhan_hangvekho);
        tvDiaChiNhan = (TextView) view.findViewById(R.id.tv_dia_chi_nguoi_nhan_hangvekho);
        tvGhiChu = (TextView) view.findViewById(R.id.tv_ghi_chu_hangvekho);
        tvGhiChuSauSua = (TextView) view.findViewById(R.id.tv_ghi_chu_hangvekho);

        final FragmentManager manager = new FragmentManager() {
            @Override
            public FragmentTransaction beginTransaction() {
                return null;
            }

            @Override
            public boolean executePendingTransactions() {
                return false;
            }

            @Override
            public Fragment findFragmentById(@IdRes int id) {
                return null;
            }

            @Override
            public Fragment findFragmentByTag(String tag) {
                return null;
            }

            @Override
            public void popBackStack() {

            }

            @Override
            public boolean popBackStackImmediate() {
                return false;
            }

            @Override
            public void popBackStack(String name, int flags) {

            }

            @Override
            public boolean popBackStackImmediate(String name, int flags) {
                return false;
            }

            @Override
            public void popBackStack(int id, int flags) {

            }

            @Override
            public boolean popBackStackImmediate(int id, int flags) {
                return false;
            }

            @Override
            public int getBackStackEntryCount() {
                return 0;
            }

            @Override
            public BackStackEntry getBackStackEntryAt(int index) {
                return null;
            }

            @Override
            public void addOnBackStackChangedListener(OnBackStackChangedListener listener) {

            }

            @Override
            public void removeOnBackStackChangedListener(OnBackStackChangedListener listener) {

            }

            @Override
            public void putFragment(Bundle bundle, String key, Fragment fragment) {

            }

            @Override
            public Fragment getFragment(Bundle bundle, String key) {
                return null;
            }

            @Override
            public List<Fragment> getFragments() {
                return null;
            }

            @Override
            public Fragment.SavedState saveFragmentInstanceState(Fragment f) {
                return null;
            }

            @Override
            public boolean isDestroyed() {
                return false;
            }

            @Override
            public void dump(String prefix, FileDescriptor fd, PrintWriter writer, String[] args) {

            }
        };
        tvTenNguoiGui.setText(hashMapDonHang_Hoan.get(lstNhanVienGiaoHang_Hoan_.get(groupPosition)).get(childPosition).getIdKhachHang_Hoan());
        tvTenNguoiNhan.setText(hashMapDonHang_Hoan.get(lstNhanVienGiaoHang_Hoan_.get(groupPosition)).get(childPosition).getTenNguoiNhanHang_Hoan());

        tvDiaChiNhan.setText(hashMapDonHang_Hoan.get(lstNhanVienGiaoHang_Hoan_.get(groupPosition)).get(childPosition).getDoiDiaChiNhan_Hoan());
        String ghiChuOgir=hashMapDonHang_Hoan.get(lstNhanVienGiaoHang_Hoan_.get(groupPosition)).get(childPosition)
                .getGhiChu_Hoan();
        String A=ghiChuOgir.replace("\\n","\n");
        tvGhiChu.setText(A);
        final String listID = hashMapDonHang_Hoan.get(lstNhanVienGiaoHang_Hoan_.get(groupPosition)).get(childPosition).getId_Hoan();

        btnSuaGhiChu = (Button) view.findViewById(R.id.btn_sua_ghi_chu_hangvekho);
        btnGoiNguoiNhan = (Button) view.findViewById(R.id.btn_goi_nguoi_nhan_hangvekho);
        btnXacNhan = (Button) view.findViewById(R.id.btn_xac_nhan_hangvekho);
        btnNhanTatCa = (Button) view.findViewById(R.id.btn_nhan_tat_ca);
        final String sdtNguoiNhan = "tel:" + hashMapDonHang_Hoan.get(lstNhanVienGiaoHang_Hoan_.get(groupPosition)).get
                (childPosition).getSdtNguoiNhan_Hoan();
        btnSuaGhiChu.setOnClickListener(new View.OnClickListener() {
                @Override
            public void onClick(View v) {
                    DialogChinhSuaGhiChuHangHoan dialogChinhSuaGhiChuHangHoan = new DialogChinhSuaGhiChuHangHoan();

                    Bundle bundle = new Bundle();
                    bundle.putString("idDonHang", hashMapDonHang_Hoan.get(lstNhanVienGiaoHang_Hoan_.get(groupPosition)).get
                            (childPosition).getId_Hoan());
                    Log.e("bundle", String.valueOf(bundle));
                    dialogChinhSuaGhiChuHangHoan.setArguments(bundle);
                dialogChinhSuaGhiChuHangHoan.show(Manager, "this this dialog for change note!");
                    Toast.makeText(context, "" + hashMapDonHang_Hoan.get(lstNhanVienGiaoHang_Hoan_.get(groupPosition)).get
                            (childPosition).getId_Hoan(), Toast
                            .LENGTH_SHORT).show();

              return;
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
                    }
                });
                builder.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                builder.show();
            }
        });

        // Button chon tat ca
        btnNhanTatCa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogChonTatCaHangHoan dialogChonTatCaHangHoan = new DialogChonTatCaHangHoan();
                dialogChonTatCaHangHoan.show(Manager, "This is may dialogfragment");

            }
        });
        // Button xac nhan
        btnXacNhan.setOnClickListener(new View.OnClickListener() {
                                          public void onClick(View view) {
                                              final AlertDialog.Builder dialog = new AlertDialog.Builder(context);
                                              dialog.setMessage("Xác nhận nhận đơn!");
                                              dialog.setPositiveButton("Xác nhận", new DialogInterface.OnClickListener() {
                                                  @Override
                                                  public void onClick(DialogInterface dialog, int which) {
                                                      AsyntaskXacNhanDonHangHoan asyntaskXacNhanDonHangHoan = new AsyntaskXacNhanDonHangHoan(context);
                                                      asyntaskXacNhanDonHangHoan.execute(listID);



                                                  }
                                              });
                                              dialog.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                                                  @Override
                                                  public void onClick(DialogInterface dialog, int which) {
                                                  }
                                              });
                                              dialog.show();
                                          }
                                      }

        );

        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    public class AsyntaskXacNhanDonHangHoan extends AsyncTask<String, Void, String> {
        Context context;
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog = new ProgressDialog(context);
            progressDialog.setMessage("Đang thực hiện");
            progressDialog.show();

        }

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

            if (s != null) {
            String timeStamp = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss").format(Calendar.getInstance().getTime());
            try {
                if (CheckRespone(s)) {
                    Log.e("xac nhan hang hoan", s);
                    progressDialog.dismiss();
                    interfaceHangHoan.refeshFragmentHangHoan(context);
                    Toast.makeText(context, "Xác nhận hàng hoàn" + "\n" + timeStamp, Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(context, "Xác nhận thất bại!", Toast.LENGTH_SHORT).show();
                    Log.e("xac nhan hang hoan", s);
                    progressDialog.dismiss();
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            } else {
                progressDialog.dismiss();
                Toast.makeText(context, "Không thành công", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
