package group.vulner.ghov_dieuvan.view.taodonhangmoi.views;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

import group.vulner.ghov_dieuvan.R;
import group.vulner.ghov_dieuvan.Utils;
import group.vulner.ghov_dieuvan.view.taodonhangmoi.CustomSpinnerAdapter;
import group.vulner.ghov_dieuvan.view.taodonhangmoi.CustomSpinnerAdapterQuan;
import group.vulner.ghov_dieuvan.view.taodonhangmoi.models.DataResponeDanhSachThanhPho;
import group.vulner.ghov_dieuvan.view.taodonhangmoi.variable.KhachHang;
import group.vulner.ghov_dieuvan.view.taodonhangmoi.variable.Quan;
import group.vulner.ghov_dieuvan.view.taodonhangmoi.variable.ThanhPho;


public class FragmentTaoDonHangMoi extends Fragment {
    private EditText edtHoTenNguoiNhan, edtSDTNguoiNhan, edtDiaChi, edtMaHangHoa, edtMoTaSanPham, edtGhiChu;
    private CheckBox ckNguoiNhanThanhToan, ckDieuKhoanSuDung;
    private TextView tvTongTien, tvHienThi;
    private Button btnTaoDonHang;
    private Spinner     spnTinhThanh, spnQuanHuyen;
    KhachHang khachHang;
//    DanhSachTinhThanh danhSachTinhThanh;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_taodonhangmoi, container, false);
        mapping(view);
        AsyncTaskLayDanhSachThanhPho asyncTaskLayDanhSachThanhPho = new AsyncTaskLayDanhSachThanhPho(getContext());
        asyncTaskLayDanhSachThanhPho.execute();
        return view;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case 101:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //granted
                } else {
                    //not granted
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    public void mapping(View view) {
        edtHoTenNguoiNhan = (EditText) view.findViewById(R.id.edtHoVaTenNguoiNhan);
        edtSDTNguoiNhan = (EditText) view.findViewById(R.id.edtSDTNguoiNhan);
        edtDiaChi = (EditText) view.findViewById(R.id.edtDiaChi);
        edtMaHangHoa = (EditText) view.findViewById(R.id.edtMaHangHoa);
        edtMoTaSanPham = (EditText) view.findViewById(R.id.edtMaHangHoa);
        edtGhiChu = (EditText) view.findViewById(R.id.edtGhiChu);
        ckDieuKhoanSuDung = (CheckBox) view.findViewById(R.id.ckDieuKhaonSuDung);
        ckNguoiNhanThanhToan = (CheckBox) view.findViewById(R.id.ckNguoiNhanThanhToanTruoc);
        tvTongTien = (TextView) view.findViewById(R.id.tvTongTiengDnHang);
        spnTinhThanh = (Spinner) view.findViewById(R.id.spnTinh);
        spnQuanHuyen = (Spinner) view.findViewById(R.id.spnQuan);
        btnTaoDonHang = (Button) view.findViewById(R.id.btnTaoDonHang);
    }

    public class AsyncTaskLayDanhSachThanhPho extends AsyncTask<Object, Object, String> {
        public AsyncTaskLayDanhSachThanhPho(Context context) {
        }

        @Override
        protected String doInBackground(Object... url) {
            String urlDanhSachThanhPho = Utils.URL_GHOV_API_DANH_SACH_THANH_PHO_QUAN;
            try {

                HttpClient client = new DefaultHttpClient();
                HttpPost httpPost = new HttpPost(urlDanhSachThanhPho);
                MultipartEntity entity = new MultipartEntity();
                String encode = "4104dfc7af2f3ae1f73d837d51715f79";

                entity.addPart("session", new StringBody(Utils.encodeBase64(encode)));
                Log.e("Lỗi entity", entity.toString());
                httpPost.setEntity(entity);
                HttpResponse response = client.execute(httpPost);
                Log.i("response", entity.toString() + "");
                if (response == null
                    || response.getStatusLine().getStatusCode() != 200) {
                    return null;
                } else {
                    ByteArrayOutputStream DataOut = new ByteArrayOutputStream();
                    response.getEntity().writeTo(DataOut);
                    String GiaTriTraVe = DataOut.toString();
                    Log.e("Ahihi", GiaTriTraVe.toString());
                    DataOut.close();
                    DataResponeDanhSachThanhPho data = new DataResponeDanhSachThanhPho(GiaTriTraVe);
                    return GiaTriTraVe;
                }
            }

            catch (Exception e) {
                Log.e("Gia tri", e.toString());
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(final String dataResponeDanhSachThanhPho) {
            super.onPostExecute(dataResponeDanhSachThanhPho);
            final String js = dataResponeDanhSachThanhPho;
            if (dataResponeDanhSachThanhPho != null) {
                try {
                    final ArrayList<ThanhPho> listThanhPho = new ArrayList<>();
                    //tao mot json object moi tu chuoi string tra ve
                    JSONObject objectJsonTraVe = new JSONObject(dataResponeDanhSachThanhPho);
                    //tao moi mot  jsonarray tu jsonobject o tren
                    JSONArray arrayDeTail = objectJsonTraVe.getJSONArray("detail");
                    for (int i = 0; i < arrayDeTail.length(); i++) {
                        JSONObject obThanhPho = arrayDeTail.getJSONObject(i);
                        ThanhPho thanhPho = new ThanhPho();
                        thanhPho.setId_thanh_pho(obThanhPho.getString("id_thanh_pho"));
                        thanhPho.setTen_thanh_pho(obThanhPho.getString("ten_thanh_pho"));
                        thanhPho.getDanhSachQuan(obThanhPho.getJSONArray("quan"));
                        listThanhPho.add(thanhPho);
                    }
                    CustomSpinnerAdapter customSpinnerAdapter = new CustomSpinnerAdapter(getContext(), R.layout.custom_spinner,
                            listThanhPho);
                    spnTinhThanh.setAdapter(customSpinnerAdapter);
                    spnTinhThanh.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                            try {
                                danhSachQuan(js, listThanhPho.get(position).getId_thanh_pho());
                                CustomSpinnerAdapterQuan customSpinnerAdapterQuan = new CustomSpinnerAdapterQuan(getContext(), R
                                        .layout.custom_spinner, danhSachQuan(js, listThanhPho.get(position).getId_thanh_pho()));
                                spnQuanHuyen.setAdapter(customSpinnerAdapterQuan);
                                Toast.makeText(getContext(), "" + danhSachQuan(js, listThanhPho.get(position).getId_thanh_pho()).size(),
                                        Toast.LENGTH_SHORT).show();
                            }
                            catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> parent) {

                        }
                    });
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                Toast.makeText(getContext(), "Vui lòng kiểm ra mạng", Toast.LENGTH_SHORT).show();
            }
        }

        public ArrayList<Quan> danhSachQuan(String js, String idThanhPho) throws JSONException {
            ArrayList<Quan> lstQuan = new ArrayList<>();
            JSONObject object = new JSONObject(js);
            JSONArray jsonArray = object.getJSONArray("detail");
            for (int a = 0; a < jsonArray.length(); a++) {
                JSONObject objectThanhPho = jsonArray.getJSONObject(a);
                if (idThanhPho.equals(objectThanhPho.getString("id_thanh_pho"))) {
                    JSONArray jArrQuan = objectThanhPho.getJSONArray("quan");
                    for (int b = 0; b < jArrQuan.length(); b++) {
                        JSONObject obQuan = jArrQuan.getJSONObject(b);
                        Quan quan = new Quan();
                        quan.setTen_quan(obQuan.getString("ten_quan"));
                        quan.setId_quan(obQuan.getString("id_quan"));
                        lstQuan.add(quan);
                    }
                }
            }
            return lstQuan;
        }

    }
}
