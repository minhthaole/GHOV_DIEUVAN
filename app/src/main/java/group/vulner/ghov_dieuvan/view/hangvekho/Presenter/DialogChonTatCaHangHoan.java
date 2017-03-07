package group.vulner.ghov_dieuvan.view.hangvekho.Presenter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import group.vulner.ghov_dieuvan.R;
import group.vulner.ghov_dieuvan.view.hangvekho.model.NhanVienGiaoHang_Hoan;

import static group.vulner.ghov_dieuvan.view.hangvekho.view.FragmentHangHoan.lstDonHangHoan;

/**
 * Created by TuTV on 3/6/2017.
 */

public class DialogChonTatCaHangHoan extends android.support.v4.app.DialogFragment {
    ArrayList<NhanVienGiaoHang_Hoan> lstNhanVienGiaoHang_hoan = new ArrayList<>();
    NhanVienGiaoHang_Hoan nhanVienGiaoHang_hoan = new NhanVienGiaoHang_Hoan();
    Context context;

    Button btnCancel, btnSubmit;
    ListView lv_hien_thi_xac_nhan_tat_ca;
    String[] arr = {"123", "SDfsfs", "Áđasád", "ÁĐâsđá", "Zzzzzzzzzzzzzzzz"};

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialogfragment_xac_nhan_tat_ca_hanghoan, container, false);

        getDialog().setTitle("This is title dialog");
        btnCancel = (Button) view.findViewById(R.id.btn_cancel_dialog_hanghoan);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        btnSubmit = (Button) view.findViewById(R.id.btn_submit_dialog_hoan);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Cảm ơn bạn đã push lên nhé ^^!", Toast.LENGTH_SHORT).show();
            }
        });

        CustomAdapterXacNhanTatCaHangHoan arrayAdapter = new CustomAdapterXacNhanTatCaHangHoan(getContext(), R.layout
                .custom_listview_nhan_tat_ca_hang_hoan, lstDonHangHoan);
        lv_hien_thi_xac_nhan_tat_ca = (ListView) view.findViewById(R.id.lv_hien_thi_xac_nhan_tat_ca);
        lv_hien_thi_xac_nhan_tat_ca.setAdapter(arrayAdapter);
        return view;
    }
}
