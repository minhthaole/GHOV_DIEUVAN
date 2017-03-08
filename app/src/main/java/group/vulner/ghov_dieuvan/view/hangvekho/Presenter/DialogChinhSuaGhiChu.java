package group.vulner.ghov_dieuvan.view.hangvekho.Presenter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import group.vulner.ghov_dieuvan.R;

/**
 * Created by TuTV on 3/8/2017.
 */

public class DialogChinhSuaGhiChu extends DialogFragment {
    Context context;
    EditText edtChinhSuaGhiChu;
    Button btnXacNhanSua, btnHuyBoSua;
    static String strSuaGhiChu = "";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_sua_ghi_chu, container, false);
        getDialog().setTitle("Sửa ghi chú!");
        edtChinhSuaGhiChu = (EditText) view.findViewById(R.id.edt_sua_ghi_chu_hanghoan);
        btnXacNhanSua = (Button) view.findViewById(R.id.btn_cap_nhat_ghi_chu_hanghoan);
        btnHuyBoSua = (Button) view.findViewById(R.id.btn_huy_bo_ghi_chu_hanghoan);
        btnHuyBoSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        btnXacNhanSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strSuaGhiChu=edtChinhSuaGhiChu.getText().toString();
                Toast.makeText(getContext(), ""+strSuaGhiChu, Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }
}
