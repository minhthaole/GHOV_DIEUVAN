package group.vulner.ghov_dieuvan.view.tapketnhan;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import group.vulner.ghov_dieuvan.R;
import group.vulner.ghov_dieuvan.model.ReponeData;
import group.vulner.ghov_dieuvan.view.tapketnhan.model.DanhSachTapKetNhan;

import static group.vulner.ghov_dieuvan.Utils.CheckRespone;

/**
 * Created by TuTV on 2/16/2017.
 */

public class FragmentDanhSachTapKetNhan extends Fragment {
    ListView listHienThiDanhSachTapKetNhan;
    ReponeData reponeData;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tap_ket_nhan, container, false);
        reponeData = new ReponeData();
        listHienThiDanhSachTapKetNhan = (ListView) view.findViewById(R.id.lv_hien_thi_tap_ket_nhan);
        AsyncTaskDanhSachTapKetNhan asyncTaskDanhSachTapKetNhan = new AsyncTaskDanhSachTapKetNhan();
        asyncTaskDanhSachTapKetNhan.execute();
        return view;
    }

    private class AsyncTaskDanhSachTapKetNhan extends AsyncTask<Void, Void, ArrayList<DanhSachTapKetNhan>> {
        @Override
        protected ArrayList<DanhSachTapKetNhan> doInBackground(Void... params) {
            ArrayList<DanhSachTapKetNhan> lstDanhSachTapKet = new ArrayList<>();
            String GiaTriTraVe = "";
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(getContext().getAssets().open("DanhSachTapKetNhan")));
                String temp;
                while ((temp = reader.readLine()) != null) {
                    GiaTriTraVe += temp;
                }
                if (CheckRespone(GiaTriTraVe)) {
                    JSONObject object = new JSONObject(GiaTriTraVe);
                    JSONArray arrayDetail = object.getJSONArray("detail");
                    for (int Z = 0; Z < arrayDetail.length(); Z++) {
                        JSONObject obDanhSachTapKet = arrayDetail.getJSONObject(Z);
                        DanhSachTapKetNhan danhSachTapKet = new DanhSachTapKetNhan();
                        danhSachTapKet.setId(obDanhSachTapKet.getString("id"));
                        danhSachTapKet.setTracking_id(obDanhSachTapKet.getString("tracking_id"));
                        danhSachTapKet.setGhi_chu(obDanhSachTapKet.getString("ghi_chu"));
                        danhSachTapKet.setId_khachhang(obDanhSachTapKet.getString("id_khachhang"));
                        danhSachTapKet.setTen_shop(obDanhSachTapKet.getString("ten_shop"));
                        danhSachTapKet.setTen_nguoi_gui(obDanhSachTapKet.getString("ten_nguoi_gui"));
                        danhSachTapKet.setSdt_nguoi_gui(obDanhSachTapKet.getString("sdt_nguoi_gui"));
                        danhSachTapKet.setDia_chi_gui(obDanhSachTapKet.getString("dia_chi_gui"));
                        danhSachTapKet.setTen_nguoi_nhan_hang(obDanhSachTapKet.getString("ten_nguoi_nhan_hang"));
                        danhSachTapKet.setSdt_nguoi_nhan(obDanhSachTapKet.getString("sdt_nguoi_nhan"));
                        danhSachTapKet.setDia_chi_nhan(obDanhSachTapKet.getString("dia_chi_nhan"));
                        danhSachTapKet.setId_nhanvien_giao(obDanhSachTapKet.getString("id_nhanvien_giao"));
                        danhSachTapKet.setId_nhanvien_nhan(obDanhSachTapKet.getString("id_nhanvien_nhan"));
                        danhSachTapKet.setTinh_trang_don_hang(obDanhSachTapKet.getString("tinh_trang_don_hang"));
                        danhSachTapKet.setTien_thu_ho(obDanhSachTapKet.getString("tien_thu_ho"));
                        lstDanhSachTapKet.add(danhSachTapKet);

                    }
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            return lstDanhSachTapKet;
        }

        @Override
        protected void onPostExecute(ArrayList<DanhSachTapKetNhan> s) {
            if (s.size() > 0) {
                Custom_Listview_TapKetNhan custom_listview_tapKetNhan = new Custom_Listview_TapKetNhan(getContext(), R.layout
                        .custom_listview_tap_ket_nhan, s);
                listHienThiDanhSachTapKetNhan.setAdapter(custom_listview_tapKetNhan);

            } else {
                Toast.makeText(getContext(), "Không thành công", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
