package group.vulner.ghov_dieuvan.view.hangvekho.Presenter;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import group.vulner.ghov_dieuvan.R;
import group.vulner.ghov_dieuvan.view.hangvekho.model.DonHang_Hoan;

/**
 * Created by TuTV on 3/6/2017.
 */

public class CustomAdapterXacNhanTatCaHangHoan extends ArrayAdapter<DonHang_Hoan> {
    static String id_HHG = null;
    static List<String> lstIDChecked = new ArrayList<>();
    private Context context;

    private int resource;
    private ArrayList<DonHang_Hoan> lstDonHangHang_Hoan;

    public CustomAdapterXacNhanTatCaHangHoan(Context context, int resource, ArrayList<DonHang_Hoan> lstDonHangHang_Hoan) {
        super(context, resource, lstDonHangHang_Hoan);
        this.context = context;
        this.resource = resource;
        this.lstDonHangHang_Hoan = lstDonHangHang_Hoan;
    }
    @NonNull
    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        view = LayoutInflater.from(context).inflate(resource, parent, false);
        TextView tvHienThiThongTinXacNhanTatCa = (TextView) view.findViewById(R.id.tv_hienthi_diachi_thanhpho_hanghoan);
        final CheckBox checkBox = (CheckBox) view.findViewById(R.id.ck_tick_xacnhantatca_hanghoan);

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    lstIDChecked.add(lstDonHangHang_Hoan.get(position).getId_Hoan());
                }
            }
        });
        tvHienThiThongTinXacNhanTatCa.setText(lstDonHangHang_Hoan.get(position).getTenNguoiNhanHang_Hoan()
                                              +"\n"+lstDonHangHang_Hoan.get(position).getDoiDiaChiNhan_Hoan());
        return view;
    }


}

