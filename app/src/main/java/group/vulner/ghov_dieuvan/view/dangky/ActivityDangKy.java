package group.vulner.ghov_dieuvan.view.dangky;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import group.vulner.ghov_dieuvan.R;
import group.vulner.ghov_dieuvan.Utils;
import group.vulner.ghov_dieuvan.controller.Security;
import group.vulner.ghov_dieuvan.view.dangky.fragment_dangky.FragmentThongTinNhanVien;
import group.vulner.ghov_dieuvan.view.dangky.fragment_dangky.FragmentThongTinTaiKhoan;
import group.vulner.ghov_dieuvan.view.dangky.fragment_dangky.giadinh.FragmentThongTinGiadinh2;
import group.vulner.ghov_dieuvan.view.dangky.fragment_dangky.giadinh.ThongTinGiaDinh;


/**
 * Created by APC on 10/13/2016.
 */
public class ActivityDangKy extends AppCompatActivity implements FragmentThongTinTaiKhoan.MessagesTaiKhoanListener, FragmentThongTinGiadinh2.MessagesThongTinGiaDinhListener, FragmentThongTinNhanVien.MessagesThongTinNhanVienListener {

    protected static String REGISTER_SUCCESS = Utils.RESPONSE_SUCCESS;
    protected static String REGISTER_ACTIVATED = "activated";
    protected static String REGISTER_FAILED = Utils.RESPONSE_FAIL;
    protected static String REGISTER_DUPLICATE = "duplicated";
    private String nameClass = getClass().getSimpleName();
    private Context context;
    private ViewPager viewPager;
    private AdapterFragmentDangKy adapterFragmentDangKy;
    // private SmartTabLayout tabLayout;
    private int numItem = 3;
    private Button btnDangKy;
    private int current = 0;
    private Security sec;

    private ThongTinDangKy thongTinDangKy;
    private String nameVoChong, ngaySinhVoChong;
    private List<ThongTinGiaDinh> lstThongTinGiaDinh;

    private String URL_DANGKY = Utils.URL_GHOV_API_DANGKY;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangky);
        context = this;
        thongTinDangKy = new ThongTinDangKy();
        viewPager = (ViewPager) findViewById(R.id.viewPager);
//        tabLayout = (SmartTabLayout) findViewById(R.id.pgTabStrip);
        btnDangKy = (Button) findViewById(R.id.btnDangKy);
        adapterFragmentDangKy = new AdapterFragmentDangKy(context, getSupportFragmentManager(), numItem);
        viewPager.setAdapter(adapterFragmentDangKy);
//        tabLayout.setViewPager(viewPager);

//        getSupportActionBar().setTitle(getResources().getString(R.string.name_company));
        viewPager.setCurrentItem(current);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int _position) {
//                position = _position;
//                setTextButton(position);
//                if (!Utils.checkEmail(thongTinDangKy.email) || !thongTinDangKy.password.equals(thongTinDangKy.nhaplaipassword) || thongTinDangKy.password.length() < 6) {
//                    viewPager.setCurrentItem(0);
//                } else if (thongTinDangKy.anh_ca_nhan == null || thongTinDangKy.name.equals("") || thongTinDangKy.dob.equals("") || thongTinDangKy.cmnd.equals("") || thongTinDangKy.ngay_cap.equals("") || thongTinDangKy.noi_cap.equals("")) {
//                    viewPager.setCurrentItem(1);
//                } else if (thongTinDangKy.phone.equals("") || thongTinDangKy.dia_chi.equals("") || thongTinDangKy.nguoi_lien_he.equals("") || thongTinDangKy.phone_lien_he.equals("")) {
//                    viewPager.setCurrentItem(2);
//                } else {
//                    viewPager.setCurrentItem(3);
//                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int _position) {

                if (_position == 1 || _position == 0) {
                    btnDangKy.setText("Tiếp theo");
                } else if (_position == 2) {
                    btnDangKy.setText("Đăng ký");
                }
                if (_position > current) {
                    viewPager.setCurrentItem(current);
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        final EditText edt = new EditText(this);
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (current == 0) {
                    // thong tin lien he
                    ((EditText) findViewById(R.id.edtHoTen)).setBackgroundDrawable(edt.getBackground());
                    ((EditText) findViewById(R.id.edtSdt)).setBackground(edt.getBackground());
                    ((EditText) findViewById(R.id.edtDiachi)).setBackground(edt.getBackground());
                    ((EditText) findViewById(R.id.edtNgaySinh)).setBackground(edt.getBackground());
                    ((EditText) findViewById(R.id.edtCmnd)).setBackground(edt.getBackground());
                    ((EditText) findViewById(R.id.edtNgayCap)).setBackground(edt.getBackground());
                    if (thongTinDangKy.anh_ca_nhan == null) {
                        ((ImageView) findViewById(R.id.imgChonAnh)).setBackground(getResources().getDrawable(R.drawable.background_edittext_error));
                        Toast.makeText(context, getResources().getString(R.string.thongbaoanhcanhanthieu), Toast.LENGTH_SHORT).show();
                        current = 0;
                        viewPager.setCurrentItem(current);

                    } else if ("".equals(thongTinDangKy.name)) {
                        current = 0;
                        viewPager.setCurrentItem(current);
                        ((EditText) findViewById(R.id.edtHoTen)).setBackground(getResources().getDrawable(R.drawable.background_edittext_error));
                        Toast.makeText(context, getResources().getString(R.string.thongbaohotenthieu), Toast.LENGTH_SHORT).show();

                    } else if ("".equals(thongTinDangKy.phone)) {
                        current = 0;
                        viewPager.setCurrentItem(current);
                        ((EditText) findViewById(R.id.edtSdt)).setBackground(getResources().getDrawable(R.drawable.background_edittext_error));
                        Toast.makeText(context, getResources().getString(R.string.thongbaosdtthieu), Toast.LENGTH_SHORT).show();

                    } else if ("".equals(thongTinDangKy.dia_chi)) {
                        current = 0;
                        viewPager.setCurrentItem(current);
                        ((EditText) findViewById(R.id.edtDiachi)).setBackground(getResources().getDrawable(R.drawable.background_edittext_error));
                        Toast.makeText(context, getResources().getString(R.string.thongbaodiachithieu), Toast.LENGTH_SHORT).show();

                    } else if ("".equals(thongTinDangKy.ngay_sinh)) {
                        current = 0;
                        viewPager.setCurrentItem(current);
                        ((EditText) findViewById(R.id.edtNgaySinh)).setBackground(getResources().getDrawable(R.drawable.background_edittext_error));
                        Toast.makeText(context, getResources().getString(R.string.thongbaongaysinhthieu), Toast.LENGTH_SHORT).show();

                    } else if ("".equals(thongTinDangKy.cmnd)) {
                        current = 0;
                        viewPager.setCurrentItem(current);
                        ((EditText) findViewById(R.id.edtCmnd)).setBackground(getResources().getDrawable(R.drawable.background_edittext_error));
                        Toast.makeText(context, getResources().getString(R.string.thongbaocmndthieu), Toast.LENGTH_SHORT).show();

                    } else if ("".equals(thongTinDangKy.ngay_cap)) {
                        current = 0;
                        viewPager.setCurrentItem(current);
                        ((EditText) findViewById(R.id.edtNgayCap)).setBackground(getResources().getDrawable(R.drawable.background_edittext_error));
                        Toast.makeText(context, getResources().getString(R.string.thongbaongaycapthieu), Toast.LENGTH_SHORT).show();

                    } else if ("".equals(thongTinDangKy.noi_cap)) {
                        current = 0;
                        viewPager.setCurrentItem(current);
                        Toast.makeText(context, getResources().getString(R.string.thongbaonoicapthieu), Toast.LENGTH_SHORT).show();

                    } else {
                        current = 1;
                        viewPager.setCurrentItem(current);
                    }
                } else if (current == 1) {
                    // thong tin gia dinh
                    ((EditText) findViewById(R.id.edtNameNguoiLienHe)).setBackground(edt.getBackground());
                    ((EditText) findViewById(R.id.edtSdtNguoiLienHe)).setBackground(edt.getBackground());
                    if ("".equals(thongTinDangKy.nguoi_lien_he)) {
                        current = 1;
                        viewPager.setCurrentItem(current);
                        ((EditText) findViewById(R.id.edtNameNguoiLienHe)).setBackground(getResources().getDrawable(R.drawable.background_edittext_error));
                        Toast.makeText(context, getResources().getString(R.string.thongbaonguoilienhethieu), Toast.LENGTH_SHORT).show();

                    } else if ("".equals(thongTinDangKy.phone_lien_he)) {
                        current = 1;
                        viewPager.setCurrentItem(current);
                        ((EditText) findViewById(R.id.edtSdtNguoiLienHe)).setBackground(getResources().getDrawable(R.drawable.background_edittext_error));
                        Toast.makeText(context, getResources().getString(R.string.thongbaosdtnguoilienhethieu), Toast.LENGTH_SHORT).show();
                    } else {
                        current = 2;
                        viewPager.setCurrentItem(current);
                    }
                } else if (current == 2) {
                    // thong tin tai khoan
                    viewPager.setCurrentItem(current);
                    ((EditText) findViewById(R.id.edtEmailCaNhan)).setBackground(edt.getBackground());
                    ((EditText) findViewById(R.id.edtMatKhau)).setBackground(edt.getBackground());
                    ((EditText) findViewById(R.id.edtNhapLaiMatKhau)).setBackground(edt.getBackground());

                    if ("".equals(thongTinDangKy.email)) {
                        current = 2;
                        viewPager.setCurrentItem(current);
                        ((EditText) findViewById(R.id.edtEmailCaNhan)).setBackground(getResources().getDrawable(R.drawable.background_edittext_error));
                        Toast.makeText(context, getResources().getString(R.string.thongbaoemailthieu), Toast.LENGTH_SHORT).show();

                    } else if (!Utils.checkEmail(thongTinDangKy.email)) {
                        current = 2;
                        viewPager.setCurrentItem(current);
                        ((EditText) findViewById(R.id.edtEmailCaNhan)).setBackground(getResources().getDrawable(R.drawable.background_edittext_error));
                        Toast.makeText(context, getResources().getString(R.string.thongbaoemailsai), Toast.LENGTH_SHORT).show();

                    } else if ("".equals(thongTinDangKy.password)) {
                        current = 2;
                        viewPager.setCurrentItem(current);
                        ((EditText) findViewById(R.id.edtMatKhau)).setBackground(getResources().getDrawable(R.drawable.background_edittext_error));
                        Toast.makeText(context, getResources().getString(R.string.thongbaomatkhauthieu), Toast.LENGTH_SHORT).show();

                    } else if (!thongTinDangKy.password.equals(thongTinDangKy.nhaplaipassword)) {
                        current = 2;
                        viewPager.setCurrentItem(current);
                        ((EditText) findViewById(R.id.edtNhapLaiMatKhau)).setBackground(getResources().getDrawable(R.drawable.background_edittext_error));
                        Toast.makeText(context, getResources().getString(R.string.thongbaomatkhauchuakhop), Toast.LENGTH_SHORT).show();

                    } else {
                        DangKyAsyncTask dangKyAsyncTask = new DangKyAsyncTask(context, URL_DANGKY, thongTinDangKy);
                        dangKyAsyncTask.execute();
                    }
                }
            }
        });

    }

    @Override
    protected Dialog onCreateDialog(int id) {
        return super.onCreateDialog(id);
    }

    // thông tin tài khoản
    @Override
    public void getEmail(String strEmail) {
        thongTinDangKy.email = strEmail;
    }

    @Override
    public void getMatKhau(String strMatKhau) {
        thongTinDangKy.password = strMatKhau;
    }

    @Override
    public void getNhapLaiMatKhau(String strNhapLaiMatKhau) {
        thongTinDangKy.nhaplaipassword = strNhapLaiMatKhau;
    }

    // thông tin nhân viên
    @Override
    public void getHoTen(String strHoTen) {
        thongTinDangKy.name = strHoTen;

    }

    @Override
    public void getGioiTinh(String strGioiTinh) {
        thongTinDangKy.gender = strGioiTinh;

    }

    @Override
    public void getSdt(String strSdt) {
        thongTinDangKy.phone = strSdt;
    }

    @Override
    public void getDiaChi(String strDiaChi) {
        thongTinDangKy.dia_chi = strDiaChi;

    }

    @Override
    public void getNgaySinh(String strNgaySinh) {
        thongTinDangKy.ngay_sinh = strNgaySinh;

    }

    @Override
    public void getCmnd(String strCmnd) {
        thongTinDangKy.cmnd = strCmnd;

    }

    @Override
    public void getNgayCap(String strNgayCap) {
        thongTinDangKy.ngay_cap = strNgayCap;

    }

    @Override
    public void getNoiCap(String strNoiCap) {
        thongTinDangKy.noi_cap = strNoiCap;

    }

    @Override
    public void getAnhCaNhan(String strAnhCaNhan) {
        thongTinDangKy.anh_ca_nhan = strAnhCaNhan;
    }

    // thông tin gia đình
    @Override
    public void getNguoiLienHe(String strNguoiLienHe) {
        thongTinDangKy.nguoi_lien_he = strNguoiLienHe;

    }

    @Override
    public void getSdtNguoiLienHe(String strSdtNguoiLienHe) {
        thongTinDangKy.phone_lien_he = strSdtNguoiLienHe;

    }

    public void getTrangThaiKetHon(String strTrangThai) {
        thongTinDangKy.tinh_trang_ket_hon = strTrangThai;
    }

    public void getTenVoChong(String strTenVoChong) {
        nameVoChong = strTenVoChong;
    }

    public void getNgaySinhVoChong(String strNgaySinhVoChong) {
        ngaySinhVoChong = strNgaySinhVoChong;
    }

    @Override
    public void getListCon(List<ThongTinGiaDinh> _lstThongTinGiaDinh) {
        lstThongTinGiaDinh = _lstThongTinGiaDinh;
    }

    public class DangKyAsyncTask extends AsyncTask<Void, Void, String> {
        private Context context;
        private String urlDangKy;
        private ProgressDialog progressDialog;
        private ThongTinDangKy thongTinDangKy;

        public DangKyAsyncTask(Context _context, String _urlDangKy, ThongTinDangKy _thongTinDangKy) {
            context = _context;
            progressDialog = new ProgressDialog(context);
            urlDangKy = _urlDangKy;
            thongTinDangKy = _thongTinDangKy;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.setMessage("Đang đăng ký, vui lòng chờ...");
            progressDialog.show();
        }

        @Override
        protected String doInBackground(Void... voids) {
            try {
                HttpClient client = new DefaultHttpClient();
                HttpPost postRequest = new HttpPost(Utils.URL_GHOV_API_DANGKY_NHANVIEN);
                MultipartEntity multiPartEntity = new MultipartEntity();
                // =================name file upload===========

                multiPartEntity.addPart("email", new StringBody(Utils.encodeBase64(thongTinDangKy.email)));
                sec = new Security();
                multiPartEntity.addPart("password", new StringBody(Utils.encodeBase64(sec.md5(thongTinDangKy.password))));
                multiPartEntity.addPart("name", new StringBody(Utils.encodeBase64(thongTinDangKy.name)));
                multiPartEntity.addPart("gioi_tinh", new StringBody(Utils.encodeBase64(thongTinDangKy.gender)));
                multiPartEntity.addPart("ngay_sinh", new StringBody(Utils.encodeBase64(thongTinDangKy.ngay_sinh)));
                multiPartEntity.addPart("cmnd", new StringBody(Utils.encodeBase64(thongTinDangKy.cmnd)));
                multiPartEntity.addPart("ngay_cap", new StringBody(Utils.encodeBase64(thongTinDangKy.ngay_cap)));
                multiPartEntity.addPart("noi_cap", new StringBody(Utils.encodeBase64(thongTinDangKy.noi_cap)));
                multiPartEntity.addPart("phone", new StringBody(Utils.encodeBase64(thongTinDangKy.phone)));
                multiPartEntity.addPart("dia_chi", new StringBody(Utils.encodeBase64(thongTinDangKy.dia_chi)));
                multiPartEntity.addPart("nguoi_lien_he", new StringBody(Utils.encodeBase64(thongTinDangKy.nguoi_lien_he)));
                multiPartEntity.addPart("phone_lien_he", new StringBody(Utils.encodeBase64(thongTinDangKy.phone_lien_he)));
                multiPartEntity.addPart("hon_nhan", new StringBody(Utils.encodeBase64(thongTinDangKy.tinh_trang_ket_hon)));

                String thong_tin_gia_dinh = "0";
                if (thongTinDangKy.tinh_trang_ket_hon.equals(ThongTinDangKy.CHUA_KET_HON)) {
                    multiPartEntity.addPart("thong_tin_gia_dinh", new StringBody(Utils.encodeBase64("0")));
                } else if (thongTinDangKy.tinh_trang_ket_hon.equals(ThongTinDangKy.DA_KET_HON)) {
                    ThongTinGiaDinh voChong = new ThongTinGiaDinh();
                    voChong.name = nameVoChong;
                    voChong.ngay_sinh = ngaySinhVoChong;
                    if (thongTinDangKy.gender.equals(ThongTinDangKy.GIOI_TINH_NAM)) {
                        voChong.gioitinh = ThongTinGiaDinh.GIOI_TINH_NU;
                        voChong.quan_he = ThongTinGiaDinh.VO;

                    } else {
                        voChong.gioitinh = ThongTinGiaDinh.GIOI_TINH_NAM;
                        voChong.quan_he = ThongTinGiaDinh.CHONG;
                    }


                    if (lstThongTinGiaDinh == null) {
                        lstThongTinGiaDinh = new ArrayList<ThongTinGiaDinh>();
                        if (!voChong.name.equals("") && !voChong.ngay_sinh.equals("") && !checkTonTai(lstThongTinGiaDinh, voChong))
                            lstThongTinGiaDinh.add(voChong);
                    } else {
                        if (!voChong.name.equals("") && !voChong.ngay_sinh.equals("") && !checkTonTai(lstThongTinGiaDinh, voChong))
                            lstThongTinGiaDinh.add(voChong);

                    }

                    if (lstThongTinGiaDinh.size() == 0) {
                        multiPartEntity.addPart("thong_tin_gia_dinh", new StringBody(Utils.encodeBase64("0")));
                    } else {
                        multiPartEntity.addPart("thong_tin_gia_dinh", new StringBody(Utils.encodeBase64(convertListToJsonThongTinGiaDinh(lstThongTinGiaDinh))));
                        thong_tin_gia_dinh = convertListToJsonThongTinGiaDinh(lstThongTinGiaDinh);
                    }

                }

                // =================file upload================
                ContentBody bodyZipFile = new FileBody(new File(thongTinDangKy.anh_ca_nhan));
                multiPartEntity.addPart("img_path", bodyZipFile);
                postRequest.setEntity(multiPartEntity);
                // client.setTimeout(500000);
                // ====== create response from server
                HttpResponse response = client.execute(postRequest);

                Log.i("response", "" + response.getStatusLine().getStatusCode());
                if (response == null
                        || response.getStatusLine().getStatusCode() != 200) {
                    return REGISTER_FAILED;
                } else {
                    ByteArrayOutputStream out = new ByteArrayOutputStream();
                    response.getEntity().writeTo(out);
                    String rp = out.toString();
                    Log.i("response", "" + rp + " ");
                    out.close();
                    if (rp.equals(REGISTER_ACTIVATED)) {
                        return REGISTER_ACTIVATED;
                    } else if (rp.equals(REGISTER_SUCCESS)) {
                        return REGISTER_SUCCESS;
                    } else if (rp.equals(REGISTER_DUPLICATE)) {
                        return REGISTER_DUPLICATE;
                    } else {
                        return REGISTER_FAILED;
                    }
                }
            } catch (Exception e) {
                Log.i("" + nameClass, "Exception:" + e.toString());
                return REGISTER_FAILED;
            }

        }

        private boolean checkTonTai(List<ThongTinGiaDinh> lstThongTinGiaDinh, ThongTinGiaDinh voChong) {
            for (int i = 0; i < lstThongTinGiaDinh.size(); i++) {
                ThongTinGiaDinh vc = lstThongTinGiaDinh.get(i);
                if (voChong.name.equals(vc.name) && voChong.ngay_sinh.equals(vc.ngay_sinh) && voChong.quan_he.equals(vc.quan_he) && voChong.gioitinh.equals(vc.gioitinh))
                    return true;

            }
            return false;
        }

        @Override
        protected void onPostExecute(String response) {
            super.onPostExecute(response);
            progressDialog.dismiss();
            if (response.equals(REGISTER_ACTIVATED)) {
                Toast.makeText(context, "Tài khoản đã được kích hoạt", Toast.LENGTH_LONG).show();
            } else if (response.equals(REGISTER_FAILED)) {
                Toast.makeText(context, "Đăng ký thất bại, Kiểm tra lại kết nối", Toast.LENGTH_LONG).show();
            } else if (response.equals(REGISTER_DUPLICATE)) {
                Toast.makeText(context, "Tài khoản đã đăng ký.\n Vui lòng liên hệ bộ phận nhân sự kích hoạt", Toast.LENGTH_LONG).show();
            } else {
                Toast.makeText(context, "Đăng ký thành công.\n" +
                        " Vui lòng liên hệ bộ phận nhân sự kích hoạt", Toast.LENGTH_LONG).show();
                finish();
            }
        }
    }

    private String convertListToJsonThongTinGiaDinh(List<ThongTinGiaDinh> _lstThongTinGiaDinh) {
        String strReturn = "[";
        for (int i = 0; i < _lstThongTinGiaDinh.size(); i++) {
            ThongTinGiaDinh thongTin = _lstThongTinGiaDinh.get(i);
            String strName = "{\"ho_ten\":\"" + thongTin.name + "\",";
            String strQuanHe = "\"quan_he\":\"" + thongTin.quan_he + "\",";
            String strNgaySinh = "\"ngay_sinh\":\"" + thongTin.ngay_sinh + "\",";
            String strgioi_tinh = "\"gioi_tinh\":\"" + thongTin.gioitinh + "\"},";
            strReturn += (strName + strQuanHe + strNgaySinh + strgioi_tinh);
        }
        strReturn = strReturn.substring(0, strReturn.length() - 1) + "]";
        return strReturn;
    }


}
