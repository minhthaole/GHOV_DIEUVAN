package group.vulner.ghov_dieuvan.view.hangvekho.Presenter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

import group.vulner.ghov_dieuvan.R;
import group.vulner.ghov_dieuvan.view.hangvekho.model.DonHang_Hoan;

/**
 * Created by TuTV on 3/6/2017.
 */

public class CustomAdapterXacNhanTatCa extends ArrayAdapter<DonHang_Hoan> {
    private Context context;
    private int resource;
    private ArrayList<DonHang_Hoan> lstDonHangHang_Hoan;

    public CustomAdapterXacNhanTatCa(Context context, int resource, ArrayList<DonHang_Hoan> lstDonHangHang_Hoan) {
        super(context, resource, lstDonHangHang_Hoan);
        this.context = context;
        this.resource = resource;
        this.lstDonHangHang_Hoan = lstDonHangHang_Hoan;
    }

    @NonNull
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        view = LayoutInflater.from(context).inflate(R.layout.custom_nhan_tat_ca_hang_hoan, parent, false);
        TextView tvHienThiThongTinXacNhanTatCa = (TextView) view.findViewById(R.id.tv_hienthi_diachi_thanhpho_xacnhantatca);
        CheckBox checkBox = (CheckBox) view.findViewById(R.id.ck_tick_xacnhantatca);
        tvHienThiThongTinXacNhanTatCa.setText(lstDonHangHang_Hoan.get(position).getTenNguoiNhanHang_Hoan());
        return view;
    }
}
