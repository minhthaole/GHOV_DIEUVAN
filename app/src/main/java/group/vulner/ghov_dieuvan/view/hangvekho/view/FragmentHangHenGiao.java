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
import group.vulner.ghov_dieuvan.view.hangvekho.Presenter.hanghengiao.ExpandableListViewHangHenGiao;
import group.vulner.ghov_dieuvan.view.hangvekho.model.DonHang_HHG;
import group.vulner.ghov_dieuvan.view.hangvekho.model.NhanVienGiaohang_HHG;

import static group.vulner.ghov_dieuvan.Utils.CheckRespone;

/**
 * Created by TuTV on 2/28/2017.
 */

public class FragmentHangHenGiao extends Fragment {
    public static ArrayList<DonHang_HHG> lstDonHang_HHG = new ArrayList<>();
    ExpandableListView my_expandableListView;
    public static String key = "key";
    public static FragmentHangHenGiao newInstace(int position) {
        FragmentHangHenGiao fragment = new FragmentHangHenGiao();
        Bundle bundle = new Bundle();
        bundle.putInt(key, position);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_hang_hen_giao, container, false);
        AsyntaskHangHenGiao asyntaskHangHenGiao = new AsyntaskHangHenGiao(getContext());
        my_expandableListView = (ExpandableListView) view.findViewById(R.id.epl_hang_hen_giao);
        asyntaskHangHenGiao.execute();
        return view;
    }

  public class AsyntaskHangHenGiao extends AsyncTask<Void, Void, ArrayList<DonHang_HHG>> {
        Context context;

        public AsyntaskHangHenGiao(Context context) {
            this.context = context;
        }

        @Override
        protected ArrayList<DonHang_HHG> doInBackground(Void... params) {
            SharepreferenceManager manager = new SharepreferenceManager(context);
            String sesstion = manager.getSession("giá trị mặc định");
            ArrayList<DonHang_HHG> lstDonHang_HHG = new ArrayList<>();

            String urlHangHenGiao = "http://www.giaohangongvang.com/api/dieuvan/list-xac-nhan-hang-hen-giao";

            HttpClient client = new DefaultHttpClient();
            HttpPost httpPost = new HttpPost(urlHangHenGiao);
            MultipartEntity entity = new MultipartEntity();
            try {
                entity.addPart("session", new StringBody(Utils.encodeBase64(sesstion)));
//                Log.e("entity hàng hẹn giao", entity.toString());
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
                    Log.e("Json hangHenGiao", GiaTriTraVe.toString());
                    DataOut.close();
                    if (CheckRespone(GiaTriTraVe)) {
                        JSONObject objectRoot = new JSONObject(GiaTriTraVe);
                        JSONArray arrayDetail = objectRoot.getJSONArray("detail");
                        for (int o = 0; o < arrayDetail.length(); o++) {
                            JSONObject objectCurrent = arrayDetail.getJSONObject(o);
                            DonHang_HHG donHang_HHG = new DonHang_HHG();

                            donHang_HHG.setId_HHG(objectCurrent.getString("id"));
//                            Log.e("id hen ngay",donHangHoan.setId(objectCurrent.getString("id")).t);
                            donHang_HHG.setIdTracking_HHG(objectCurrent.getString("tracking_id"));
                            donHang_HHG.setIdKhachHang_HHG(objectCurrent.getString("id_khachhang"));
                            donHang_HHG.setMaDonHang_HHG(objectCurrent.getString("ma_don_hang"));
                            donHang_HHG.setIdNhanVienGiao_HHG(objectCurrent.getString("id_nhanvien_giao"));
                            donHang_HHG.setTenNhanVienGiao_HHG(objectCurrent.getString("ten_nhanvien_giao"));
                            donHang_HHG.setTenNguoiNhan_HHG(objectCurrent.getString("ten_nguoi_nhan_hang"));
                            donHang_HHG.setSdtNguoiNhan_HHG(objectCurrent.getString("sdt_nguoi_nhan"));
                            donHang_HHG.setDoiDiaChiNhan_HHG(objectCurrent.getString("doi_dia_chi_nhan"));
                            donHang_HHG.setTienThuHo_HHG(objectCurrent.getString("tien_thu_ho"));
                            donHang_HHG.setGhiChu_HHG(objectCurrent.getString("ghi_chu"));

                            lstDonHang_HHG.add(donHang_HHG);
//                            Log.e("donHangHoan", String.valueOf(lstDonHangHoan.size()));
                        }

                    }
                    return lstDonHang_HHG;
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            return lstDonHang_HHG;
        }

        //
        //
        //
        @Override
        protected void onPostExecute(ArrayList<DonHang_HHG> donHangHoen){
            if (donHangHoen.size() > 0) {
                FragmentManager manager=getFragmentManager();
                lstDonHang_HHG=donHangHoen;
                listNhanVienGiaoHang(donHangHoen);
                List<NhanVienGiaohang_HHG> temp = listNhanVienGiaoHang(donHangHoen);
                HashMap<NhanVienGiaohang_HHG, List<DonHang_HHG>> hashMapDonHang_HHG = new HashMap<>();
                for (int i = 0; i < temp.size(); i++) {
                    List<DonHang_HHG> donHang_HHG_s = new ArrayList<>();
                    Log.e("sizea", String.valueOf(temp.get(0).getLstDonHangHHG().size()));
                    if (i == 0) {
                        for (int a = 0; a < temp.get(0).getLstDonHangHHG().size(); a++) {
                            donHang_HHG_s.add(temp.get(i).getLstDonHangHHG().get(a));
                        }
                    } else {
                        for (int a = 0; a < temp.get(i).getLstDonHangHHG().size(); a++) {
                            donHang_HHG_s.add(temp.get(i).getLstDonHangHHG().get(a));
                        }
                    }
                    hashMapDonHang_HHG.put(temp.get(i), donHang_HHG_s);
                    ExpandableListViewHangHenGiao expandableListViewHangHenGiao = new ExpandableListViewHangHenGiao(getContext(),
                            temp, hashMapDonHang_HHG,manager);
                    my_expandableListView.setAdapter(expandableListViewHangHenGiao);
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

    public static ArrayList<NhanVienGiaohang_HHG> listNhanVienGiaoHang(ArrayList<DonHang_HHG> donHangHoen) {

        ArrayList<NhanVienGiaohang_HHG> lstNhanVienGiaoHang_HHG_ = new ArrayList<>();

        List<String> lstIdNhanVienGiaoHang_HHG = new ArrayList<>();
        for (int i = 0; i < donHangHoen.size(); i++) {
            if (lstIdNhanVienGiaoHang_HHG.contains(donHangHoen.get(i).getIdNhanVienGiao_HHG())) {
            } else {

                lstIdNhanVienGiaoHang_HHG.add(donHangHoen.get(i).getIdNhanVienGiao_HHG());
            }
        }
        Log.e("lstIDNhanVien", String.valueOf(lstIdNhanVienGiaoHang_HHG.size()));

        for (int i = 0; i < lstIdNhanVienGiaoHang_HHG.size(); i++) {
            NhanVienGiaohang_HHG nhanVienGiaohang_HHG_ = new NhanVienGiaohang_HHG();
            String idNhanVienGiaoHang_HHG = lstIdNhanVienGiaoHang_HHG.get(i);
            nhanVienGiaohang_HHG_.setIdNhanVienGiaoHang_HHG(idNhanVienGiaoHang_HHG);


            ArrayList<DonHang_HHG> lstDonHang_HHG_ = new ArrayList<>();
            for (int j = 0; j < donHangHoen.size(); j++) {

                if (idNhanVienGiaoHang_HHG.equals(donHangHoen.get(j).getIdNhanVienGiao_HHG())) {
                    String tenNhanVienGiaoHang_HHG = donHangHoen.get(j).getTenNhanVienGiao_HHG();
                    //set ten nhan vien giap hang (Hang hen giao)
                    nhanVienGiaohang_HHG_.setTenNhanVienGiao(tenNhanVienGiaoHang_HHG);
                    DonHang_HHG donHang_HHG_ = new DonHang_HHG();
                    donHang_HHG_.setId_HHG(donHangHoen.get(j).getId_HHG());
                    donHang_HHG_.setIdTracking_HHG(donHangHoen.get(j).getIdTracking_HHG());
                    donHang_HHG_.setIdKhachHang_HHG(donHangHoen.get(j).getIdKhachHang_HHG());
                    donHang_HHG_.setMaDonHang_HHG(donHangHoen.get(j).getMaDonHang_HHG());
                    donHang_HHG_.setIdNhanVienGiao_HHG(donHangHoen.get(j).getIdNhanVienGiao_HHG());
                    donHang_HHG_.setTenNhanVienGiao_HHG(donHangHoen.get(j).getTenNhanVienGiao_HHG());
                    donHang_HHG_.setTenNguoiNhan_HHG(donHangHoen.get(j).getTenNguoiNhan_HHG());
                    donHang_HHG_.setSdtNguoiNhan_HHG(donHangHoen.get(j).getSdtNguoiNhan_HHG());
                    donHang_HHG_.setDoiDiaChiNhan_HHG(donHangHoen.get(j).getDoiDiaChiNhan_HHG());
                    donHang_HHG_.setTienThuHo_HHG(donHangHoen.get(j).getTienThuHo_HHG());
                    donHang_HHG_.setGhiChu_HHG(donHangHoen.get(j).getGhiChu_HHG());
                    lstDonHang_HHG_.add(donHang_HHG_);
                    nhanVienGiaohang_HHG_.setLstDonHangHHG(lstDonHang_HHG_);
                }
            }
            lstNhanVienGiaoHang_HHG_.add(nhanVienGiaohang_HHG_);
        }
        return lstNhanVienGiaoHang_HHG_;
    }
}
