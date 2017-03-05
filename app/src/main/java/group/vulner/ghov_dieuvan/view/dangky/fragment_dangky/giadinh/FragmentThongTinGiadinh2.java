package group.vulner.ghov_dieuvan.view.dangky.fragment_dangky.giadinh;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import group.vulner.ghov_dieuvan.R;
import group.vulner.ghov_dieuvan.view.dangky.ThongTinDangKy;


public class FragmentThongTinGiadinh2 extends Fragment {

    private Context context;
    private String nameFragment = getClass().getSimpleName();
    private Calendar myCalendar = Calendar.getInstance();

    private EditText edtNameNguoiLienHe, edtSdtNguoiLienHe;
    private RadioButton rbDaKetHon, rbChuaKetHon, rbKhac;
    private LinearLayout llTTVoChong;
    private EditText edtNameVoChong, edtNgaySinhVoChong;
    private EditText edtNameCon, edtNgaySinhCon;
    private RadioButton rbNam, rbNu;
    private ListView lvCon;
    private List<ThongTinGiaDinh> lstThongTinGiaDinh;
    private AdapterCon adapterCon;
    private Button btnThemCon;

    DatePickerDialog.OnDateSetListener dateNgaySinhVoChong = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            String day = "" + dayOfMonth;
            String month = "" + (monthOfYear + 1);
            if (dayOfMonth < 10) day = "0" + dayOfMonth;
            if (monthOfYear + 1 < 10) month = "0" + (monthOfYear + 1);
            String ngaySinh = day + "-" + month + "-" + String.valueOf(year);
            edtNgaySinhVoChong.setText(ngaySinh);
            messagesThongTinGiaDinhListener.getNgaySinhVoChong(ngaySinh);
        }
    };

    DatePickerDialog.OnDateSetListener dateNgaySinhCon = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            String day = "" + dayOfMonth;
            String month = "" + (monthOfYear + 1);
            if (dayOfMonth < 10) day = "0" + dayOfMonth;
            if (monthOfYear + 1 < 10) month = "0" + (monthOfYear + 1);
            String ngaySinh = day + "-" + month + "-" + String.valueOf(year);
            edtNgaySinhCon.setText(ngaySinh);
        }
    };

    private MessagesThongTinGiaDinhListener messagesThongTinGiaDinhListener;

    public FragmentThongTinGiadinh2() {
    }

    @SuppressLint("ValidFragment")
    public FragmentThongTinGiadinh2(Context _context) {
        context = _context;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            messagesThongTinGiaDinhListener = (MessagesThongTinGiaDinhListener) activity;
        } catch (ClassCastException e) {
            Log.i("" + nameFragment, "ClassCastException:" + e.toString());
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dangky_thongtingiadinh, container, false);
        edtNameNguoiLienHe = (EditText) view.findViewById(R.id.edtNameNguoiLienHe);
        edtSdtNguoiLienHe = (EditText) view.findViewById(R.id.edtSdtNguoiLienHe);
        rbChuaKetHon = (RadioButton) view.findViewById(R.id.rbChuaKetHon);
        rbDaKetHon = (RadioButton) view.findViewById(R.id.rbDaKetHon);
        rbKhac = (RadioButton) view.findViewById(R.id.rbKhac);
        llTTVoChong = (LinearLayout) view.findViewById(R.id.llTTVoChong);
        edtNameVoChong = (EditText) view.findViewById(R.id.edtNameVoChong);
        edtNgaySinhVoChong = (EditText) view.findViewById(R.id.edtNgaySinhVoChong);
        edtNameCon = (EditText) view.findViewById(R.id.edtNameCon);
        edtNgaySinhCon = (EditText) view.findViewById(R.id.edtNgaySinhCon);
        rbNam = (RadioButton) view.findViewById(R.id.rbNam);
        rbNu = (RadioButton) view.findViewById(R.id.rbNu);
        btnThemCon = (Button) view.findViewById(R.id.btnThemCon);
        lvCon = (ListView) view.findViewById(R.id.lvCon);

        lstThongTinGiaDinh = new ArrayList<ThongTinGiaDinh>();

        edtNameNguoiLienHe.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String strNguoiLienHe = edtNameNguoiLienHe.getText().toString();
                messagesThongTinGiaDinhListener.getNguoiLienHe(strNguoiLienHe);
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        edtSdtNguoiLienHe.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String strSdtNguoiLienHe = edtSdtNguoiLienHe.getText().toString();
                messagesThongTinGiaDinhListener.getSdtNguoiLienHe(strSdtNguoiLienHe);
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        edtNameVoChong.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String strTenVoChong = charSequence.toString();
                messagesThongTinGiaDinhListener.getTenVoChong(strTenVoChong);
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        edtNgaySinhVoChong.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    new DatePickerDialog(context, dateNgaySinhVoChong, myCalendar.get(Calendar.YEAR),
                            myCalendar.get(Calendar.MONTH),
                            myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                } else {
                }
            }
        });

        edtNgaySinhCon.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    new DatePickerDialog(context, dateNgaySinhCon, myCalendar.get(Calendar.YEAR),
                            myCalendar.get(Calendar.MONTH),
                            myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                } else {
                }
            }
        });

        btnThemCon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameCon = edtNameCon.getText().toString();
                String ngaysinhCon = edtNgaySinhCon.getText().toString();
                if ("".equals(nameCon)) {
                    Toast.makeText(context, "Nhập họ tên con", Toast.LENGTH_LONG).show();
                } else if ("".equals(ngaysinhCon)) {
                    Toast.makeText(context, "Nhập ngày sinh con", Toast.LENGTH_LONG).show();
                } else {
                    ThongTinGiaDinh con = new ThongTinGiaDinh();
                    con.name = nameCon;
                    con.ngay_sinh = ngaysinhCon;
                    con.quan_he = ThongTinGiaDinh.CON;
                    con.gioitinh = rbNam.isChecked() ? ThongTinGiaDinh.GIOI_TINH_NAM : ThongTinGiaDinh.GIOI_TINH_NU;
                    lstThongTinGiaDinh.add(con);
                    adapterCon = new AdapterCon(context, lstThongTinGiaDinh, getFragmentManager());
                    lvCon.setAdapter(adapterCon);
                    edtNameCon.setText("");
                    edtNgaySinhCon.setText("");
                    messagesThongTinGiaDinhListener.getListCon(lstThongTinGiaDinh);
                }
            }
        });
        eventRadioButton();

        return view;
    }

    private void eventRadioButton() {
        rbChuaKetHon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rbDaKetHon.setChecked(false);
                rbKhac.setChecked(false);
                llTTVoChong.setVisibility(View.GONE);
                messagesThongTinGiaDinhListener.getTrangThaiKetHon(ThongTinDangKy.CHUA_KET_HON);
            }
        });

        rbDaKetHon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rbKhac.setChecked(false);
                rbChuaKetHon.setChecked(false);
                llTTVoChong.setVisibility(View.VISIBLE);
                messagesThongTinGiaDinhListener.getTrangThaiKetHon(ThongTinDangKy.DA_KET_HON);
            }
        });

        rbKhac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rbDaKetHon.setChecked(false);
                rbChuaKetHon.setChecked(false);
                llTTVoChong.setVisibility(View.VISIBLE);
                messagesThongTinGiaDinhListener.getTrangThaiKetHon(ThongTinDangKy.KHAC);
            }
        });
    }


    public interface MessagesThongTinGiaDinhListener {


        public void getNguoiLienHe(String strNguoiLienHe);

        public void getSdtNguoiLienHe(String strSdtNguoiLienHe);

        public void getTrangThaiKetHon(String strTrangThai);

        public void getTenVoChong(String strTenVoChong);

        public void getNgaySinhVoChong(String strNgaySinhVoChong);

        public void getListCon(List<ThongTinGiaDinh> lstThongTinGiaDinh);

    }

}
