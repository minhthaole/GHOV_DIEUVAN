package group.vulner.ghov_dieuvan.view.taodonhangmoi.variable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by TuTV on 2/9/2017.
 */

public class ThanhPho {
    private String id_thanh_pho;
    private String ten_thanh_pho;
    private ArrayList<Quan> danhSachQuan;

    public ThanhPho() {
    }

    public ThanhPho(String id_thanh_pho, String ten_thanh_pho, ArrayList<Quan> danhSachQuan) {
        this.id_thanh_pho = id_thanh_pho;
        this.ten_thanh_pho = ten_thanh_pho;
        this.danhSachQuan = danhSachQuan;
    }

    public String getId_thanh_pho() {
        return id_thanh_pho;
    }

    public void setId_thanh_pho(String id_thanh_pho) {
        this.id_thanh_pho = id_thanh_pho;
    }

    public String getTen_thanh_pho() {
        return ten_thanh_pho;
    }

    public void setTen_thanh_pho(String ten_thanh_pho) {
        this.ten_thanh_pho = ten_thanh_pho;
    }

    public ArrayList<Quan> getDanhSachQuan(JSONArray quan) {
        return danhSachQuan;
    }

    public void setDanhSachQuan(JSONArray danhSachQuan) {
        ArrayList<Quan> lstQuan = new ArrayList<>();
        for (int i = 0; i < danhSachQuan.length(); i++) {
            try {
                JSONObject object = danhSachQuan.getJSONObject(i);
                lstQuan.add(new Quan(object.getString("id_quan"), object.getString("ten_quan")));
            }
            catch (JSONException e) {
                e.printStackTrace();
            }
        }
        this.danhSachQuan = lstQuan;
    }
}

