package group.vulner.ghov_dieuvan.view.taodonhangmoi.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import group.vulner.ghov_dieuvan.model.DataResponse;
import group.vulner.ghov_dieuvan.view.taodonhangmoi.variable.Quan;
import group.vulner.ghov_dieuvan.view.taodonhangmoi.variable.ThanhPho;

/**
 * Created by TuTV on 2/13/2017.
 */

public class DataResponeDanhSachThanhPho extends DataResponse {
    public ArrayList<ThanhPho> lstThanhPho;
    public ArrayList<Quan> lstQuan;
    public final String chuoiJSonTraVe;

    public DataResponeDanhSachThanhPho(String chuoiJSonTraVe) {
        this.chuoiJSonTraVe = chuoiJSonTraVe;
    }

    public ArrayList<ThanhPho> DataResponeDanhSachThanhPho() throws JSONException {
        getStatusWarning(chuoiJSonTraVe);
        lstThanhPho = new ArrayList<>();
        if (!detail.equals("")) {
            try {
                JSONArray array = new JSONArray(chuoiJSonTraVe);
                for (int i = 0; i < array.length(); i++) {
                    JSONObject obThanhPho = array.getJSONObject(i);
                    ThanhPho thanhPho = new ThanhPho();
                    thanhPho.setId_thanh_pho(obThanhPho.getString("id_thanh_pho"));
                    thanhPho.setTen_thanh_pho("ten_thanh_pho");
                    thanhPho.setDanhSachQuan(obThanhPho.getJSONArray("quan"));
                    lstThanhPho.add(thanhPho);
                    DanhSachQuan(chuoiJSonTraVe, obThanhPho.getString("id_thanh_pho"));
                }
            }
            catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return lstThanhPho;
    }

    public ArrayList<ThanhPho> DanhSachThanhPho() {
        try {
            lstThanhPho = new ArrayList<>();
            JSONArray array = new JSONArray(chuoiJSonTraVe);
            for (int i = 0; i < array.length(); i++) {
                JSONObject obThanhPho = array.getJSONObject(i);
                ThanhPho thanhPho = new ThanhPho();
                thanhPho.setId_thanh_pho(obThanhPho.getString("id_thanh_pho"));
                thanhPho.setTen_thanh_pho("ten_thanh_pho");
                thanhPho.setDanhSachQuan(obThanhPho.getJSONArray("quan"));
                lstThanhPho.add(thanhPho);
                DanhSachQuan(chuoiJSonTraVe, obThanhPho.getString("id_thanh_pho"));
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        return lstThanhPho;
    }


    public ArrayList<Quan> DanhSachQuan(String chuoiJsonTraVe, String IdThanhPho) {
        lstQuan = new ArrayList<>();
        try {
            JSONArray arrayThanhPho = new JSONArray(chuoiJsonTraVe);
            for (int i = 0; i < arrayThanhPho.length(); i++) {
                JSONObject obThanhPho = arrayThanhPho.getJSONObject(i);
                if (IdThanhPho.equals(obThanhPho.getJSONObject("id_thanh_pho"))) {
                    JSONArray arrayQuan = obThanhPho.getJSONArray("quan");
                    for (int a = 0; a < arrayQuan.length(); a++) {
                        JSONObject obQuan = arrayQuan.getJSONObject(i);
                        Quan quan = new Quan();
                        quan.setId_quan(obQuan.getString("id_quan"));
                        quan.setTen_quan(obQuan.getString("ten_quan"));
                        lstQuan.add(quan);
                    }

                }
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        return lstQuan;
    }
}
