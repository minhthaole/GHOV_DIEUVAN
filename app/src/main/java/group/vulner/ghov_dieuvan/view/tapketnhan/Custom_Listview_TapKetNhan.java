package group.vulner.ghov_dieuvan.view.tapketnhan;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import group.vulner.ghov_dieuvan.R;
import group.vulner.ghov_dieuvan.view.tapketnhan.model.DanhSachTapKetNhan;

/**
 * Created by TuTV on 2/15/2017.
 */

public class Custom_Listview_TapKetNhan extends ArrayAdapter<DanhSachTapKetNhan> {
    private Context context;
    private int resource;
    private List<DanhSachTapKetNhan> lstDanhSachTapKet;

    public Custom_Listview_TapKetNhan(Context context, int resource, List<DanhSachTapKetNhan> lstDanhSachTapKet) {
        super(context, resource, lstDanhSachTapKet);
        this.context = context;
        this.resource = resource;
        this.lstDanhSachTapKet = lstDanhSachTapKet;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(resource, parent, false);
        TextView id, tracking_id, ghi_chu, id_khachhang, ten_shop, ten_nguoi_gui, sdt_nguoi_gui, dia_chi_gui, ten_nguoi_nhan_hang,
                sdt_nguoi_nhan, dia_chi_nhan, id_nhanvien_nhan, id_nhanvien_giao, tinh_trang_don_hang, tien_thu_ho;
        id = (TextView) view.findViewById(R.id.tv_id);
        tracking_id = (TextView) view.findViewById(R.id.tv_id_tracking);
        ghi_chu = (TextView) view.findViewById(R.id.tv_ghi_chu);
        id_khachhang = (TextView) view.findViewById(R.id.tv_id_khach_hang);
        ten_shop = (TextView) view.findViewById(R.id.tv_ten_shop);
        ten_nguoi_gui = (TextView) view.findViewById(R.id.tv_ten_nguoi_gui);
        sdt_nguoi_gui = (TextView) view.findViewById(R.id.tv_sdt_nguoi_gui);
        dia_chi_gui = (TextView) view.findViewById(R.id.tv_dia_chi_nguoi_gui);
        ten_nguoi_nhan_hang = (TextView) view.findViewById(R.id.tv_ten_nguoi_nhan);
        sdt_nguoi_nhan = (TextView) view.findViewById(R.id.tv_sdt_nguoi_nhan);
        dia_chi_nhan = (TextView) view.findViewById(R.id.tv_dia_chi_nguoi_nhan);
        id_nhanvien_nhan = (TextView) view.findViewById(R.id.tv_nhan_vien_nhan);
        id_nhanvien_giao = (TextView) view.findViewById(R.id.tv_nhan_vien_giao);
        tinh_trang_don_hang = (TextView) view.findViewById(R.id.tv_tinh_trang);
        tien_thu_ho = (TextView) view.findViewById(R.id.tv_tien_thu_ho);

        id.setText("Id: " + lstDanhSachTapKet.get(position).getId().toString());
        Log.e("loi", lstDanhSachTapKet.get(position).getId().toString());
        tracking_id.setText("Id tracking: " + lstDanhSachTapKet.get(position).getTracking_id());
        Log.e("loi", lstDanhSachTapKet.get(position).getTracking_id().toString());
        ghi_chu.setText("Ghi chú: " + lstDanhSachTapKet.get(position).getGhi_chu());
        id_khachhang.setText("Id khách hàng: " + lstDanhSachTapKet.get(position).getId_khachhang());
        ten_shop.setText("Shop: " + lstDanhSachTapKet.get(position).getTen_shop());
        ten_nguoi_gui.setText("Người gửi :" + lstDanhSachTapKet.get(position).getTen_nguoi_gui());
        sdt_nguoi_gui.setText("Sđt: " + lstDanhSachTapKet.get(position).getSdt_nguoi_gui());
        dia_chi_gui.setText("Địa chỉ: " + lstDanhSachTapKet.get(position).getDia_chi_gui());
        ten_nguoi_nhan_hang.setText("Người nhận: " + lstDanhSachTapKet.get(position).getTen_nguoi_nhan_hang());
        sdt_nguoi_nhan.setText("Sđt: " + lstDanhSachTapKet.get(position).getSdt_nguoi_nhan());
        dia_chi_nhan.setText("Địa chỉ: " + lstDanhSachTapKet.get(position).getDia_chi_nhan());
        id_nhanvien_nhan.setText("Id nhân viên nhận: " + lstDanhSachTapKet.get(position).getId_nhanvien_nhan());
        id_nhanvien_giao.setText("Id nhân viên giao: " + lstDanhSachTapKet.get(position).getId_nhanvien_giao());
        tinh_trang_don_hang.setText("Tình Trạng: " + lstDanhSachTapKet.get(position).getTinh_trang_don_hang());
        tien_thu_ho.setText("Tiền thu hộ: " + lstDanhSachTapKet.get(position).getTien_thu_ho());
        return view;
    }
}
