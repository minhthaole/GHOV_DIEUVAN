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

import java.util.ArrayList;

import group.vulner.ghov_dieuvan.R;
import group.vulner.ghov_dieuvan.view.hangvekho.model.DonHang_HHG;

/**
 * Created by TuTV on 3/7/2017.
 */

public class CustomAdapterXacNhanTatCaHangHenGiao extends ArrayAdapter<DonHang_HHG> {
    private Context context;
    private int resource;
    private ArrayList<DonHang_HHG> lstDonHangHang_HHG;

    public CustomAdapterXacNhanTatCaHangHenGiao(Context context, int resource, ArrayList<DonHang_HHG> lstDonHangHang_HHG) {
        super(context, resource, lstDonHangHang_HHG);
        this.context = context;
        this.resource = resource;
        this.lstDonHangHang_HHG = lstDonHangHang_HHG;
    }

    @NonNull
    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        view = LayoutInflater.from(context).inflate(R.layout.custom_listview_nhan_tat_ca_hanghengiao, parent, false);
        TextView tvHienThiThongTinXacNhanTatCa = (TextView) view.findViewById(R.id.tv_hienthi_diachi_thanhpho_xacnhantatca_hanghengiao);
        final CheckBox checkBox = (CheckBox) view.findViewById(R.id.ck_tick_xacnhantatca_hanghengiao);

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (checkBox.isChecked()) {
                    Bundle bundleCheckBox = new Bundle();
                    bundleCheckBox.putString("idCheckBox", lstDonHangHang_HHG.get(position).getId_HHG());
                    DialogChonTatCaHangHenGiao dialogChonTatCaHangHenGiao = new DialogChonTatCaHangHenGiao();
                    dialogChonTatCaHangHenGiao.setArguments(bundleCheckBox);
                }
            }
        });
        tvHienThiThongTinXacNhanTatCa.setText(lstDonHangHang_HHG.get(position).getTenNguoiNhan_HHG()
                                              + "\n" + lstDonHangHang_HHG.get(position).getDoiDiaChiNhan_HHG());
        return view;
    }
}
