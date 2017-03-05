package group.vulner.ghov_dieuvan.view.dangky.fragment_dangky;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import group.vulner.ghov_dieuvan.R;


public class FragmentThongTinTaiKhoan extends Fragment {
    private Context context;
    private String nameFragment = getClass().getSimpleName();
    private EditText edtEmailCaNhan, edtMatKhau, edtNhapLaiMatKhau;
    private MessagesTaiKhoanListener messagesListener;

        @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            messagesListener = (MessagesTaiKhoanListener) activity;
        } catch (ClassCastException e) {
            Log.i("" + nameFragment, "ClassCastException:" + e.toString());
        }
    }

    public FragmentThongTinTaiKhoan() {

    }

    @SuppressLint("ValidFragment")
    public FragmentThongTinTaiKhoan(Context _context) {
        context = _context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dangky_thongtintaikhoan, container, false);
        edtEmailCaNhan = (EditText) view.findViewById(R.id.edtEmailCaNhan);
        edtMatKhau = (EditText) view.findViewById(R.id.edtMatKhau);
        edtNhapLaiMatKhau = (EditText) view.findViewById(R.id.edtNhapLaiMatKhau);
        sendMessage();
        return view;
    }

    private void sendMessage() {
        edtEmailCaNhan.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String email = charSequence.toString();
                messagesListener.getEmail(email);
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });


        edtMatKhau.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String matkhau =charSequence.toString();
                messagesListener.getMatKhau(matkhau);
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });


        edtNhapLaiMatKhau.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String nhaplaimatkhau = charSequence.toString();
                messagesListener.getNhapLaiMatKhau(nhaplaimatkhau);
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }

    public interface MessagesTaiKhoanListener {
        public void getEmail(String strEmail);

        public void getMatKhau(String strMatKhau);

        public void getNhapLaiMatKhau(String strNhapLaiMatKhau);
    }


}
