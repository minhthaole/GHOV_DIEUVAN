package group.vulner.ghov_dieuvan.view.tapketgiao;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import group.vulner.ghov_dieuvan.R;
import group.vulner.ghov_dieuvan.view.tapketgiao.model.DanhSachTapKetGiao;
import group.vulner.ghov_dieuvan.view.tapketgiao.model.DonHang;
import group.vulner.ghov_dieuvan.view.tapketgiao.model.MyAdapterExpandableListView;
import group.vulner.ghov_dieuvan.view.tapketgiao.model.NhanVienGiaoHang;

import static group.vulner.ghov_dieuvan.Utils.CheckRespone;

/**
 * Created by TuTV on 2/17/2017.
 */

public class FragmentDanhSachTapKetGiao extends Fragment {
    ArrayList<DanhSachTapKetGiao> lstDanhSachTapKetGiao;
    ListView lvHienThiDanhSachTapKetGiao;
    ArrayList<NhanVienGiaoHang> lstNhanVienGiao = new ArrayList<>();
    ExpandableListView expandableListView;
    ArrayList<String> arrTenNhanVienGiao = new ArrayList<>();

    @SuppressLint("WrongViewCast")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_tap_ket_giao, container, false);
        lstDanhSachTapKetGiao = new ArrayList<>();
//        lvHienThiDanhSachTapKetGiao = (ListView) view.findViewById(R.id.lv_hien_thi_tap_ket_giao);
        expandableListView = (ExpandableListView) view.findViewById(R.id.el_hien_thi_danh_sach_tap_ket_giao);
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            int previousGroup = 1;

            @Override
            public void onGroupExpand(int groupPosition) {
                if (groupPosition != previousGroup)
                    expandableListView.collapseGroup(previousGroup);
                previousGroup = groupPosition;
            }
        });
        AsyncTaskDanhSachTapKetGiao asyncTaskDanhSachTapKetGiao = new AsyncTaskDanhSachTapKetGiao();

        asyncTaskDanhSachTapKetGiao.execute();

        return view;
    }

    public class AsyncTaskDanhSachTapKetGiao extends android.os.AsyncTask<Void, Void, ArrayList<DanhSachTapKetGiao>> {
        @Override
        protected ArrayList<DanhSachTapKetGiao> doInBackground(Void... params) {
            ArrayList<DanhSachTapKetGiao> danhSachTapKetGiaos = new ArrayList<>();
            BufferedReader reader;
            String GiaTriTraVe = "";
            String temp;
            try {
                reader = new BufferedReader(new InputStreamReader(getContext().getAssets().open("DanhSachTapKetGiao.txt")));
                while ((temp = reader.readLine()) != null) {
                    GiaTriTraVe += temp;
                }
                Log.e("Aâ", GiaTriTraVe.toString());
                if (CheckRespone(GiaTriTraVe)) {

                    JSONObject object = new JSONObject(GiaTriTraVe);
                    JSONArray array = object.getJSONArray("detail");
                    for (int i = 0; i < array.length(); i++) {
                        JSONObject objectTapKetGiao = array.getJSONObject(i);
                        danhSachTapKetGiaos.add(new DanhSachTapKetGiao(objectTapKetGiao.getString("id_don_hang"),
                                objectTapKetGiao.getString("ten_nguoi_nhan"), objectTapKetGiao.getString("sdt_nguoi_nhan"),
                                objectTapKetGiao.getString("dia_chi_nguoi_nhan"), objectTapKetGiao.getString("ten_nguoi_gui"),
                                objectTapKetGiao.getString("sdt_nguoi_gui"), objectTapKetGiao.getString("dia_chi_nguoi_gui"),
                                objectTapKetGiao.getString("tinh_trang_don_hang"), objectTapKetGiao.getString("tien_thu_ho"),
                                objectTapKetGiao.getString("id_quan"), objectTapKetGiao.getString("id_thanh_pho"),
                                objectTapKetGiao.getString("ghi_chu"), objectTapKetGiao.getString("id_nhan_vien_giao")));
                    }
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            Log.e("size", String.valueOf(danhSachTapKetGiaos.size()));
            return danhSachTapKetGiaos;
        }

        @Override
        protected void onPostExecute(ArrayList<DanhSachTapKetGiao> danhSachTapKetGiaos) {
            super.onPostExecute(danhSachTapKetGiaos);
            Toast.makeText(getContext(), ""+danhSachTapKetGiaos.size(), Toast.LENGTH_SHORT).show();
            lstDanhSachTapKetGiao = new ArrayList<>();
            lstDanhSachTapKetGiao = danhSachTapKetGiaos;
            if (danhSachTapKetGiaos.size() > 0) {
                DanhSachNhanVienGiao(lstDanhSachTapKetGiao);
                List<String> lstSoLuongNhanVien = DanhSachNhanVienGiao(lstDanhSachTapKetGiao);
                List<String> lstTenNhanVien = new ArrayList<>();
                HashMap<String, List<String>> listHashMap = new HashMap<String, List<String>>();
                for (int i = 0; i < lstSoLuongNhanVien.size(); i++) {
                    String tenNV = lstSoLuongNhanVien.get(i);
                    ArrayList<String> listThongTinChiTiet = new ArrayList<>();
                    Log.e("chay", "aaaa");
                    lstTenNhanVien.add(tenNV);
                    Log.e("chay", String.valueOf(lstDanhSachTapKetGiao.size()));
                    for (int a = 0; a < lstDanhSachTapKetGiao.size(); a++) {
                        if (tenNV.equals(lstDanhSachTapKetGiao.get(a).getId_nhan_vien_giao() + " - " + lstDanhSachTapKetGiao.get(a)
                                .getId_quan())) {
                            DonHang donHang = new DonHang();
                            donHang.setIdDonHang(lstDanhSachTapKetGiao.get(a).getId_don_hang());

                            listThongTinChiTiet.add("Mã đơn hàng: " + lstDanhSachTapKetGiao.get(a).getId_don_hang() + "\nTên " +
                                                    "người " +
                                                    "nhận:" + lstDanhSachTapKetGiao.get(a).getTen_nguoi_nhan() + "\nSđt: " +
                                                    "" + lstDanhSachTapKetGiao.get(a).getSdt_nguoi_nhan() + "\nĐịa chỉ nhận: " +
                                                    lstDanhSachTapKetGiao.get(a).getDia_chi_nguoi_nhan() +
                                                    "\n------------------------------\nTên " +
                                                    "người " +
                                                    "gửi:" + lstDanhSachTapKetGiao.get(a).getTen_nguoi_gui() + "\nSđt người " +
                                                    "gửi : " +
                                                    "" + lstDanhSachTapKetGiao.get(a).getSdt_nguoi_gui() + "\nĐịa chỉ gửi: " +
                                                    "" + lstDanhSachTapKetGiao.get(a).getDia_chi_nguoi_gui() + "\nTiền thu hộ: " +
                                                    "" + lstDanhSachTapKetGiao.get(a).getTien_thu_ho() + "\nTình trạng: " +
                                                    "" + lstDanhSachTapKetGiao.get(a).getTinh_trang_don_hang());


                        }
                        listHashMap.put(lstTenNhanVien.get(i), listThongTinChiTiet);
                        Log.e("asdasd", String.valueOf(listHashMap.size()));
                        MyAdapterExpandableListView myAdapterExpandableListView = new MyAdapterExpandableListView(getContext(),
                                lstTenNhanVien, listHashMap);
                        expandableListView.setAdapter(myAdapterExpandableListView);
                    }
                }

                Toast.makeText(getContext(), "Số lượng nhân viên giao " + lstDanhSachTapKetGiao.size() + "     " +
                                             DanhSachNhanVienGiao
                                                     (lstDanhSachTapKetGiao).get(0)
                                             + "\n" + DanhSachNhanVienGiao(lstDanhSachTapKetGiao).get(1) + "\n"
                                             + DanhSachNhanVienGiao(lstDanhSachTapKetGiao).get(2)
                        ,
                        Toast
                                .LENGTH_SHORT).show();


            } else {
                Toast.makeText(getContext(), "Tải dữ liệu danh sách tập kết không thành công!", Toast
                        .LENGTH_SHORT)
                        .show();
            }
        }
    }

    private class Custom_ListView_DanhSachTapKetGiao extends ArrayAdapter<DanhSachTapKetGiao> {
        Context context;
        int resource;
        List<DanhSachTapKetGiao> lstDanhSachTapKetGiao;

        public Custom_ListView_DanhSachTapKetGiao(Context context, int resource, List<DanhSachTapKetGiao> lstDanhSachTapKetGiao) {
            super(context, resource, lstDanhSachTapKetGiao);
            this.context = context;
            this.resource = resource;
            this.lstDanhSachTapKetGiao = lstDanhSachTapKetGiao;
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = LayoutInflater.from(context).inflate(resource, parent, false);
            TextView tvIdDonHang = (TextView) view.findViewById(R.id.tv_id_don_hang);
            TextView tvThongTinNguoiNhan = (TextView) view.findViewById(R.id.tv_thong_tin_nguoi_nhan1);
            TextView tvTinhTrangDonHang = (TextView) view.findViewById(R.id.tv_tinh_trang_don_hang);
            TextView tvTienThuHo = (TextView) view.findViewById(R.id.tv_tien_thu_ho);
            TextView tvQuan = (TextView) view.findViewById(R.id.tv_quan);
            TextView tvNhanVienGiao = (TextView) view.findViewById(R.id.tv_nhan_vien_giao0);
            //
            //
            tvIdDonHang.setText("Mã đơn hàng:" + lstDanhSachTapKetGiao.get(position).getId_don_hang());
            tvThongTinNguoiNhan.setText("Tên người nhận :" + lstDanhSachTapKetGiao.get(position).getTen_nguoi_nhan() + "\nSĐT " +
                                        "người " +
                                        "nhận: " +
                                        "" + lstDanhSachTapKetGiao.get(position).getSdt_nguoi_nhan() + "\nĐịa chỉ người nhận: " +
                                        "" + lstDanhSachTapKetGiao.get(position).getDia_chi_nguoi_nhan());
            tvTienThuHo.setText("Tiền thu hộ:" + lstDanhSachTapKetGiao.get(position).getTien_thu_ho());
            tvQuan.setText("Quận: " + lstDanhSachTapKetGiao.get(position).getId_quan());
            tvNhanVienGiao.setText("ID nhân viên giao: " + lstDanhSachTapKetGiao.get(position).getId_nhan_vien_giao());
            tvTinhTrangDonHang.setText("Tình trạng: " + lstDanhSachTapKetGiao.get(position).getTinh_trang_don_hang());

            return view;
        }
    }

    private ArrayList<String> DanhSachNhanVienGiao(ArrayList<DanhSachTapKetGiao> danhSachTapKetGiaoArrayList) {
        arrTenNhanVienGiao = new ArrayList<>();
        for (int i = 0; i < danhSachTapKetGiaoArrayList.size(); i++) {
            String ID = danhSachTapKetGiaoArrayList.get(i).getId_nhan_vien_giao();
            if (arrTenNhanVienGiao.contains(ID + " - " + danhSachTapKetGiaoArrayList.get(i).getId_quan())) {
            } else {
                arrTenNhanVienGiao.add(ID + " - " + danhSachTapKetGiaoArrayList.get(i).getId_quan());
            }
        }
        return arrTenNhanVienGiao;
    }


}
