package group.vulner.ghov_dieuvan.view.danhsachhoanhang;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import group.vulner.ghov_dieuvan.R;
import group.vulner.ghov_dieuvan.view.danhsachhoanhang.model.DanhSachHangHoan;

import static group.vulner.ghov_dieuvan.Utils.CheckRespone;

/**
 * Created by TuTV on 2/17/2017.
 */

public class FragmentDanhSachHangHoan extends Fragment {
    private ListView lvDanhSachHangHoan;
    private ArrayList<DanhSachHangHoan> danhSachHangHoenAA;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_danh_sach_hang_hoan, container, false);
        lvDanhSachHangHoan = (ListView) view.findViewById(R.id.lv_danh_sach_hang_hoan);
        AsyncTaskDanhSachHangHoan asyncTaskDanhSachHangHoan = new AsyncTaskDanhSachHangHoan();
        asyncTaskDanhSachHangHoan.execute();

        return view;
    }

    private class AsyncTaskDanhSachHangHoan extends AsyncTask<Void, Void, ArrayList<DanhSachHangHoan>> {
        @Override
        protected ArrayList<DanhSachHangHoan> doInBackground(Void... params) {
            ArrayList<DanhSachHangHoan> danhSachHangHoen = new ArrayList<>();
            BufferedReader reader;
            String GiaTriTraVe = "";
            try {
                String temp;
                reader = new BufferedReader(new InputStreamReader(getContext().getAssets().open("DanhSachHangHoan")));
                while ((temp = reader.readLine()) != null) {
                    GiaTriTraVe += temp;
                }
                Log.e("gia tri", GiaTriTraVe.toString());
                if (CheckRespone(GiaTriTraVe)) {
                    JSONObject objectRoot = new JSONObject(GiaTriTraVe);
                    JSONArray arrayDetail = objectRoot.getJSONArray("detail");
                    Log.e("Ahihi", String.valueOf(arrayDetail.length()));
                    for (int a = 0; a < arrayDetail.length(); a++) {
                        //
                        JSONObject objectHangHoan = arrayDetail.getJSONObject(a);
                        DanhSachHangHoan danhSachHangHoan = new DanhSachHangHoan();
                        //
                        danhSachHangHoan.setMa_don_hang(objectHangHoan.getString("ma_don_hang"));
                        danhSachHangHoan.setTen_nguoi_gui(objectHangHoan.getString("ten_nguoi_gui"));
                        danhSachHangHoan.setSdt_nguoi_gui(objectHangHoan.getString("sdt_nguoi_gui"));
                        danhSachHangHoan.setDia_chi_nguoi_gui(objectHangHoan.getString("dia_chi_nguoi_gui"));
                        danhSachHangHoan.setTen_nguoi_nhan(objectHangHoan.getString("ten_nguoi_nhan"));
                        danhSachHangHoan.setSdt_nguoi_nhan(objectHangHoan.getString("sdt_nguoi_nhan"));
                        danhSachHangHoan.setDia_chi_nguoi_nhan(objectHangHoan.getString("dia_chi_nguoi_nhan"));
                        danhSachHangHoan.setGhi_chu(objectHangHoan.getString("ghi_chu"));
                        //
                        danhSachHangHoen.add(danhSachHangHoan);
                    }

                }
            }
            catch (Exception e) {
                Log.e("Exception Asynctask", e.toString());
                e.printStackTrace();

            }

            return danhSachHangHoen;
        }

        @Override
        protected void onPostExecute(ArrayList<DanhSachHangHoan> danhSachHangHoen) {
            if(danhSachHangHoen.size()>0){
            super.onPostExecute(danhSachHangHoen);
            danhSachHangHoenAA = danhSachHangHoen;
            Toast.makeText(getContext(), "" + danhSachHangHoenAA.size(), Toast.LENGTH_SHORT).show();
            CustomListViewDanhSachHangHoan customListViewDanhSachHangHoan = new CustomListViewDanhSachHangHoan(getContext(), R
                    .layout.custom_danh_sach_hang_hoan, danhSachHangHoenAA);
            lvDanhSachHangHoan.setAdapter(customListViewDanhSachHangHoan);
        }else{
                Toast.makeText(getContext(), "Không tải được danh sách hoàn hàng!", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private class CustomListViewDanhSachHangHoan extends ArrayAdapter<DanhSachHangHoan> {
        Context context;
        int resource;
        List<DanhSachHangHoan> lstDanhSachHangHoan;

        public CustomListViewDanhSachHangHoan(Context context, int resource, List<DanhSachHangHoan> lstDanhSachHangHoan) {
            super(context, resource, lstDanhSachHangHoan);
            this.context = context;
            this.resource = resource;
            this.lstDanhSachHangHoan = lstDanhSachHangHoan;
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = LayoutInflater.from(context).inflate(resource, parent, false);
            TextView tv_MaDonHangHoan = (TextView) view.findViewById(R.id.tv_ma_don_hang_hoan);
            TextView tv_ThongTinNguoiGui = (TextView) view.findViewById(R.id.tv_thong_tin_nguoi_gui);
            TextView tv_ThongTinNguoiNHan = (TextView) view.findViewById(R.id.tv_thong_tin_nguoi_nhan);
            TextView tv_GhiChu = (TextView) view.findViewById(R.id.tv_ghi_chu_ly_do_hoan_hang);
//
            tv_MaDonHangHoan.setText("Mã : " + lstDanhSachHangHoan.get(position).getMa_don_hang());
            tv_ThongTinNguoiGui.setText("Người gửi: " + lstDanhSachHangHoan.get(position).getTen_nguoi_gui() + "\nSĐT gửi: " +
                                        "" + lstDanhSachHangHoan.get(position).getSdt_nguoi_gui() + "\nĐịa chỉ: " +
                                        "" + lstDanhSachHangHoan.get(position).getDia_chi_nguoi_gui());
            tv_ThongTinNguoiNHan.setText("Người nhận: " + lstDanhSachHangHoan.get(position).getTen_nguoi_nhan() + "\nSĐT nhận: " +
                                         "" + lstDanhSachHangHoan.get(position).getSdt_nguoi_nhan() + "\nĐịa chỉ nhân: " +
                                         "" + lstDanhSachHangHoan.get(position).getDia_chi_nguoi_nhan());
            tv_GhiChu.setText("Lý do hoàn hàng: " + lstDanhSachHangHoan.get(position).getGhi_chu());
            return view;
        }
    }
}
