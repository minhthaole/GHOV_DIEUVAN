package group.vulner.ghov_dieuvan.view.hangvekho.Presenter.hanghoan;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import group.vulner.ghov_dieuvan.R;
import group.vulner.ghov_dieuvan.view.hangvekho.model.DonHang_Hoan;
import group.vulner.ghov_dieuvan.view.hangvekho.model.NhanVienGiaoHang_Hoan;
import group.vulner.ghov_dieuvan.view.hangvekho.view.FragmentHangHoan;

import static group.vulner.ghov_dieuvan.view.hangvekho.view.FragmentHangHoan.lstDonHangHoan;

/**
 * Created by TuTV on 3/8/2017.
 */

public class DialogChinhSuaGhiChuHangHoan extends DialogFragment {
    Context context;
    EditText edtChinhSuaGhiChu;
    Button btnXacNhanSua, btnHuyBoSua;
    static String strSuaGhiChu = "";
    TextView tvGhiChu;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.dialog_sua_ghi_chu, container, false);
        getDialog().setTitle("Sửa ghi chú!");
        final String key = "key";
        ExpandableListView my_expandableListView;
        final FragmentHangHoan donHangHoen=new FragmentHangHoan();

        View viewAA = LayoutInflater.from(getContext()).inflate(R.layout.don_hang_hanghengiao, container, false);
        tvGhiChu = (TextView) viewAA.findViewById(R.id.tv_ghi_chu_hangvekho);
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
                if (edtChinhSuaGhiChu.getText().toString().length() < 1) {
                    Toast.makeText(getContext(), "Không được bỏ trống!", Toast.LENGTH_SHORT).show();

                } else {
                    Log.e("pos id", String.valueOf(tvGhiChu));
                    String idDonHang = getArguments().getString("idDonHang");
                    for (int i = 0; i < lstDonHangHoan.size(); i++) {
                        if (lstDonHangHoan.get(i).getId_Hoan() == idDonHang) {
                            Toast.makeText(getContext(), "Doi tuong TRUOC khi sua " + lstDonHangHoan.get(i).getGhiChu_Hoan(), Toast
                                    .LENGTH_SHORT).show();
                            tvGhiChu.setText(edtChinhSuaGhiChu.getText().toString());
                        }
                    }


                    Toast.makeText(getContext(), "id don hang nhan duoc tu ben nut chinh sua " + idDonHang, Toast.LENGTH_SHORT).show();
                    Log.e("Agument nhận", idDonHang);
                    for (int i = 0; i < lstDonHangHoan.size(); i++) {
                        if (lstDonHangHoan.get(i).getId_Hoan() == idDonHang) {
                            lstDonHangHoan.get(i).setGhiChu_Hoan(edtChinhSuaGhiChu.getText().toString());
                            Toast.makeText(getContext(), "Doi tuong SAU khi sua " + lstDonHangHoan.get(i).getGhiChu_Hoan(), Toast
                                    .LENGTH_SHORT).show();

                            List<NhanVienGiaoHang_Hoan> temp=donHangHoen.listNhanVienGiaoHang(lstDonHangHoan);
                            HashMap<NhanVienGiaoHang_Hoan, List<DonHang_Hoan>> hashMapDonHang_Hoan = new HashMap<>();


                        }
                    }
                    Toast.makeText(getContext(), "" + tvGhiChu.getText(), Toast.LENGTH_SHORT).show();
                    dismiss();
                }
            }
        });
        return view;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        if (edtChinhSuaGhiChu.getText().toString().length() > 0) {
            strSuaGhiChu = edtChinhSuaGhiChu.getText().toString();
            tvGhiChu.setText(edtChinhSuaGhiChu.getText().toString());
        }
    }
}
