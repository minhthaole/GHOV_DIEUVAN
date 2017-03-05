package group.vulner.ghov_dieuvan.view.dangky.fragment_dangky;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;

import java.io.IOException;
import java.util.Calendar;

import group.vulner.ghov_dieuvan.R;
import group.vulner.ghov_dieuvan.view.dangky.ThongTinDangKy;


public class FragmentThongTinNhanVien extends Fragment {
    private Context context;
    private String nameFragment = getClass().getSimpleName();
    private Calendar myCalendar = Calendar.getInstance();
    private ImageView imgChonAnh;
    private EditText edtHoTen;
    private RadioButton cbNam, cbNu;
    private EditText edtSdt;
    private EditText edtDiachi;
    private EditText edtNgaySinh;
    private EditText edtCmnd;
    private EditText edtNgayCap;
    private com.toptoche.searchablespinnerlibrary.SearchableSpinner edtNoiCap;
    private MessagesThongTinNhanVienListener messagesThongTinNhanVienListener;
    private int RESULT_LOAD_IMAGE = 99;

    DatePickerDialog.OnDateSetListener dateNgaySinh = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            String day = "" + dayOfMonth;
            String month = "" + (monthOfYear + 1);
            if (dayOfMonth < 10) day = "0" + dayOfMonth;
            if (monthOfYear + 1 < 10) month = "0" + (monthOfYear + 1);

            String ngaySinh = day + "-" + month + "-" + String.valueOf(year);
            edtNgaySinh.setText(ngaySinh);
            messagesThongTinNhanVienListener.getNgaySinh(ngaySinh);
        }

    };
    DatePickerDialog.OnDateSetListener dateNgayCap = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            String day = "" + dayOfMonth;
            String month = "" + (monthOfYear + 1);
            if (dayOfMonth < 10) day = "0" + dayOfMonth;
            if (monthOfYear + 1 < 10) month = "0" + (monthOfYear + 1);
            String ngayCap = day + "-" + month + "-" + String.valueOf(year);
            edtNgayCap.setText(ngayCap);
            messagesThongTinNhanVienListener.getNgayCap(ngayCap);
        }

    };


    public FragmentThongTinNhanVien() {

    }

    @SuppressLint("ValidFragment")
    public FragmentThongTinNhanVien(Context _context) {
        context = _context;

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            messagesThongTinNhanVienListener = (MessagesThongTinNhanVienListener) activity;
        } catch (ClassCastException e) {
            Log.i("" + nameFragment, "ClassCastException:" + e.toString());
        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dangky_thongtinnhanvien, container, false);
        imgChonAnh = (ImageView) view.findViewById(R.id.imgChonAnh);
        edtHoTen = (EditText) view.findViewById(R.id.edtHoTen);
        edtSdt = (EditText) view.findViewById(R.id.edtSdt);
        edtDiachi = (EditText) view.findViewById(R.id.edtDiachi);
        edtNgaySinh = (EditText) view.findViewById(R.id.edtNgaySinh);
        edtCmnd = (EditText) view.findViewById(R.id.edtCmnd);
        edtNgayCap = (EditText) view.findViewById(R.id.edtNgayCap);
        edtNoiCap = (com.toptoche.searchablespinnerlibrary.SearchableSpinner) view.findViewById(R.id.edtNoiCap);
        cbNam = (RadioButton) view.findViewById(R.id.cbNam);
        cbNu = (RadioButton) view.findViewById(R.id.cbNu);

        edtNoiCap.setTitle(getResources().getString(R.string.chon_thanh_pho));
        sendMessage();

        imgChonAnh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i, RESULT_LOAD_IMAGE);
            }
        });

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null) {
            try {
                Bitmap bm = MediaStore.Images.Media.getBitmap(context.getContentResolver(), data.getData());

                String pathImg = getRealPathFromURI(context, data.getData());
                ExifInterface ei = new ExifInterface(pathImg);
                int orientation = ei.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                        ExifInterface.ORIENTATION_UNDEFINED);

                switch (orientation) {
                    case ExifInterface.ORIENTATION_ROTATE_90:
                        imgChonAnh.setImageBitmap(rotateImage(bm, 90));
                        break;
                    case ExifInterface.ORIENTATION_ROTATE_180:
                        imgChonAnh.setImageBitmap(rotateImage(bm, 180));
                        break;
                    case ExifInterface.ORIENTATION_ROTATE_270:
                        imgChonAnh.setImageBitmap(rotateImage(bm, 270));
                        break;
                    case ExifInterface.ORIENTATION_NORMAL:
                        imgChonAnh.setImageBitmap(rotateImage(bm, 0));
                        break;
                    default:
                        imgChonAnh.setImageBitmap(rotateImage(bm, 0));
                        break;
                }

                messagesThongTinNhanVienListener.getAnhCaNhan(pathImg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private Bitmap rotateImage(Bitmap source, float angle) {
        Matrix matrix = new Matrix();
        matrix.postRotate(angle);
        return Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight(), matrix,
                true);
    }

    private String getRealPathFromURI(Context context, Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = {MediaStore.Images.Media.DATA};
            cursor = context.getContentResolver().query(contentUri, proj, null, null, null);
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    private void sendMessage() {

        edtHoTen.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                messagesThongTinNhanVienListener.getHoTen(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        edtNgaySinh.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    new DatePickerDialog(context, dateNgaySinh, myCalendar.get(Calendar.YEAR),
                            myCalendar.get(Calendar.MONTH),
                            myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                } else {
                }
            }
        });

        edtSdt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                messagesThongTinNhanVienListener.getSdt(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        edtDiachi.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                messagesThongTinNhanVienListener.getDiaChi(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        edtCmnd.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                messagesThongTinNhanVienListener.getCmnd(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        edtNgayCap.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    new DatePickerDialog(context, dateNgayCap, myCalendar.get(Calendar.YEAR),
                            myCalendar.get(Calendar.MONTH),
                            myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                } else {
                }
            }
        });


        messagesThongTinNhanVienListener.getGioiTinh(ThongTinDangKy.GIOI_TINH_NAM);
        cbNam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                cbNam.setChecked(true);
                cbNu.setChecked(false);
                messagesThongTinNhanVienListener.getGioiTinh(ThongTinDangKy.GIOI_TINH_NAM);
            }
        });

        cbNu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cbNu.setChecked(true);
                cbNam.setChecked(false);
                messagesThongTinNhanVienListener.getGioiTinh(ThongTinDangKy.GIOI_TINH_NU);
            }
        });

        edtNoiCap.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                if (position == 0) {
                    messagesThongTinNhanVienListener.getNoiCap("");
                } else {
                    messagesThongTinNhanVienListener.getNoiCap((String) parentView.getItemAtPosition(position));
                    Log.i("thanh pho", "" + (String) parentView.getItemAtPosition(position));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }

        });
    }

    public interface MessagesThongTinNhanVienListener {
        public void getHoTen(String strHoTen);

        public void getGioiTinh(String strGioiTinh);

        public void getSdt(String strSdt);

        public void getDiaChi(String strDiachi);

        public void getNgaySinh(String strNgaySinh);

        public void getCmnd(String strCmnd);

        public void getNgayCap(String strNgayCap);

        public void getNoiCap(String strNoiCap);

        public void getAnhCaNhan(String strAnhCaNhan);
    }

}
