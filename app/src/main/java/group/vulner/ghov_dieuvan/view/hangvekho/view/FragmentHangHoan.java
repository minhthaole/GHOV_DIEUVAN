package group.vulner.ghov_dieuvan.view.hangvekho.view;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import group.vulner.ghov_dieuvan.R;
import group.vulner.ghov_dieuvan.Utils;
import group.vulner.ghov_dieuvan.model.file.SharepreferenceManager;
import group.vulner.ghov_dieuvan.view.hangvekho.Presenter.ExpandableListViewHangHoan;
import group.vulner.ghov_dieuvan.view.hangvekho.model.DonHang_Hoan;
import group.vulner.ghov_dieuvan.view.hangvekho.model.NhanVienGiaoHang_Hoan;

import static group.vulner.ghov_dieuvan.Utils.CheckRespone;

/**
 * Created by TuTV on 2/28/2017.
 */

public class FragmentHangHoan extends Fragment {

    public static String key = "key";
    ExpandableListView my_expandableListView;
    private List<DonHang_Hoan> hangVeKhosAA;

    public static FragmentHangHoan newInstace(int position) {
        FragmentHangHoan fragment = new FragmentHangHoan();
        Bundle bundle = new Bundle();
        bundle.putInt(key, position);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_hang_ve_kho, container, false);
        my_expandableListView = (ExpandableListView) view.findViewById(R.id.epl_hang_ve_kho);
        AsyntaskHangHoan asyntaskDanhSachHangVeKho = new AsyntaskHangHoan(getContext());
        asyntaskDanhSachHangVeKho.execute();

        return view;

    }

   public class AsyntaskHangHoan extends AsyncTask<Void, Void, ArrayList<DonHang_Hoan>> {
        Context context;

        public AsyntaskHangHoan(Context context) {
            this.context = context;
        }


        @Override
        protected ArrayList<DonHang_Hoan> doInBackground(Void... params) {
            SharepreferenceManager manager = new SharepreferenceManager(context);
            String sesstion = manager.getSession("giá trị mặc định");
            ArrayList<DonHang_Hoan> lstDonHangHoan = new ArrayList<>();
            String urlHangHenGiao = "http://www.giaohangongvang.com/api/dieuvan/list-xac-nhan-hang-hoan";

            HttpClient client = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(urlHangHenGiao);
            MultipartEntity entity = new MultipartEntity();
            try {
                entity.addPart("session", new StringBody(Utils.encodeBase64(sesstion)));
                Log.e("entity hàng hoàn", entity.toString());
                httpPost.setEntity(entity);
                HttpResponse response = client.execute(httpPost);
//                Log.i("response", entity.toString() + "");
                if (response == null
                    || response.getStatusLine().getStatusCode() != 200) {
                    return null;
                } else {
                    ByteArrayOutputStream DataOut = new ByteArrayOutputStream();
                    response.getEntity().writeTo(DataOut);
                    String GiaTriTraVe = DataOut.toString();
                    Log.e("Json  Hàng hoàn", GiaTriTraVe.toString());
                    DataOut.close();
                    if (CheckRespone(GiaTriTraVe)) {
                        JSONObject objectRoot = new JSONObject(GiaTriTraVe);
                        JSONArray arrayDetail = objectRoot.getJSONArray("detail");
                        for (int o = 0; o < arrayDetail.length(); o++) {
                            JSONObject objectCurrent = arrayDetail.getJSONObject(o);
                            DonHang_Hoan donHangHoan = new DonHang_Hoan();
                            donHangHoan.setId_Hoan(objectCurrent.getString("id"));
//                            Log.e("id hen ngay",donHangHoan.setId(objectCurrent.getString("id")).t);
                            donHangHoan.setTrackingId_Hoan(objectCurrent.getString("tracking_id"));
                            donHangHoan.setIdKhachHang_Hoan(objectCurrent.getString("id_khachhang"));
                            donHangHoan.setMaDonHang_Hoan(objectCurrent.getString("ma_don_hang"));
                            donHangHoan.setIdNhanVienGiao_Hoan(objectCurrent.getString("id_nhanvien_giao"));
                            donHangHoan.setTenNhanVienGiao_Hoan(objectCurrent.getString("ten_nhanvien_giao"));
                            donHangHoan.setTenNguoiNhanHang_Hoan(objectCurrent.getString("ten_nguoi_nhan_hang"));
                            donHangHoan.setSdtNguoiNhan_Hoan(objectCurrent.getString("sdt_nguoi_nhan"));
                            donHangHoan.setDoiDiaChiNhan_Hoan(objectCurrent.getString("doi_dia_chi_nhan"));
                            donHangHoan.setTienThuHo_Hoan(objectCurrent.getString("tien_thu_ho"));
                            donHangHoan.setGhiChu_Hoan(objectCurrent.getString("ghi_chu"));

                            lstDonHangHoan.add(donHangHoan);
//                            Log.e("donHangHoan", String.valueOf(lstDonHangHoan.size()));
                        }

                    }
                    return lstDonHangHoan;
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            return lstDonHangHoan;
        }

        //
        //
        //
        @Override
        protected void onPostExecute(ArrayList<DonHang_Hoan> donHangHoen) {
            FragmentManager manager=getFragmentManager();
            if (donHangHoen.size() > 0) {
                listNhanVienGiaoHang(donHangHoen);
                List<NhanVienGiaoHang_Hoan> temp = listNhanVienGiaoHang(donHangHoen);
                HashMap<NhanVienGiaoHang_Hoan, List<DonHang_Hoan>> hashMapDonHang_Hoan = new HashMap<>();
                for (int i = 0; i < temp.size(); i++) {
                    List<DonHang_Hoan> donHang_Hoan = new ArrayList<>();

                    if (i == 0) {
                        for (int a = 0; a < temp.get(0).getLstDonHangHHG().size(); a++) {
                            donHang_Hoan.add(temp.get(i).getLstDonHangHHG().get(a));
                        }
                    } else {
                        for (int a = 0; a < temp.get(i).getLstDonHangHHG().size(); a++) {
                            donHang_Hoan.add(temp.get(i).getLstDonHangHHG().get(a));
                        }
                    }
                    hashMapDonHang_Hoan.put(temp.get(i), donHang_Hoan);
                    ExpandableListViewHangHoan expandableListViewHangHoan = new ExpandableListViewHangHoan(context, temp,
                            hashMapDonHang_Hoan,manager);
                    my_expandableListView.setAdapter(expandableListViewHangHoan);
                    my_expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
                        int previousGroup = -1;

                        @Override
                        public void onGroupExpand(int groupPosition) {
                            if (groupPosition != previousGroup)
                                my_expandableListView.collapseGroup(previousGroup);
                            previousGroup = groupPosition;
                        }
                    });
                }
            }
        }
    }

    public ArrayList<NhanVienGiaoHang_Hoan> listNhanVienGiaoHang(ArrayList<DonHang_Hoan> donHangHoen) {

        ArrayList<NhanVienGiaoHang_Hoan> lstNhanVienGiaoHang_Hoan_ = new ArrayList<>();


        List<String> lstIdNhanVienGiaoHang_Hoan = new ArrayList<>();
        for (int i = 0; i < donHangHoen.size(); i++) {
            if (lstIdNhanVienGiaoHang_Hoan.contains(donHangHoen.get(i).getIdNhanVienGiao_Hoan())) {
            } else {
                lstIdNhanVienGiaoHang_Hoan.add(donHangHoen.get(i).getIdNhanVienGiao_Hoan());
            }
        }


        for (int i = 0; i < lstIdNhanVienGiaoHang_Hoan.size(); i++) {
            NhanVienGiaoHang_Hoan nhanVienGiaohang_Hoan_ = new NhanVienGiaoHang_Hoan();
            String idNhanVienGiaoHang_Hoan = lstIdNhanVienGiaoHang_Hoan.get(i);
            nhanVienGiaohang_Hoan_.setIdNhanVienGiaoHang_HHG(idNhanVienGiaoHang_Hoan);

            ArrayList<DonHang_Hoan> lstDonHang_Hoan = new ArrayList<>();


            for (int j = 0; j < donHangHoen.size(); j++) {

                if (idNhanVienGiaoHang_Hoan.equals(donHangHoen.get(j).getIdNhanVienGiao_Hoan())) {

                    String tenNhanVienGiaoHang_HHG = donHangHoen.get(j).getTenNhanVienGiao_Hoan();
                    //set ten nhan vien giap hang (Hang hen giao)
                    nhanVienGiaohang_Hoan_.setTenNhanVienGiao(tenNhanVienGiaoHang_HHG);
                    DonHang_Hoan donHang_Hoan = new DonHang_Hoan();
                    donHang_Hoan.setId_Hoan(donHangHoen.get(j).getId_Hoan());
                    donHang_Hoan.setTrackingId_Hoan(donHangHoen.get(j).getTrackingId_Hoan());
                    donHang_Hoan.setIdKhachHang_Hoan(donHangHoen.get(j).getIdKhachHang_Hoan());
                    donHang_Hoan.setMaDonHang_Hoan(donHangHoen.get(j).getMaDonHang_Hoan());
                    donHang_Hoan.setIdNhanVienGiao_Hoan(donHangHoen.get(j).getIdNhanVienGiao_Hoan());
                    donHang_Hoan.setTenNhanVienGiao_Hoan(donHangHoen.get(j).getTenNhanVienGiao_Hoan());
                    donHang_Hoan.setTenNguoiNhanHang_Hoan(donHangHoen.get(j).getTenNguoiNhanHang_Hoan());
                    donHang_Hoan.setSdtNguoiNhan_Hoan(donHangHoen.get(j).getSdtNguoiNhan_Hoan());
                    donHang_Hoan.setDoiDiaChiNhan_Hoan(donHangHoen.get(j).getDoiDiaChiNhan_Hoan());
                    donHang_Hoan.setTienThuHo_Hoan(donHangHoen.get(j).getTienThuHo_Hoan());
                    donHang_Hoan.setGhiChu_Hoan(donHangHoen.get(j).getGhiChu_Hoan());
                    lstDonHang_Hoan.add(donHang_Hoan);

//                    Log.e("hang hoàn size: ", String.valueOf(lstDonHang_HHG_.size()));
                }
                nhanVienGiaohang_Hoan_.setLstDonHangHHG(lstDonHang_Hoan);

            }
            lstNhanVienGiaoHang_Hoan_.add(nhanVienGiaohang_Hoan_);
        }
        return lstNhanVienGiaoHang_Hoan_;
    }
}
