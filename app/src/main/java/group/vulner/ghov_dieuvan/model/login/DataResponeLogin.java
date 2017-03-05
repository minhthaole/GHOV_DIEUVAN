package group.vulner.ghov_dieuvan.model.login;


import org.json.JSONException;
import org.json.JSONObject;

import group.vulner.ghov_dieuvan.model.DataResponse;


public class DataResponeLogin extends DataResponse {
    public String session_id;
    public String ho_ten;
    public String id_thanh_pho;
    public String id_quan;
    public String id_phuong;

    public DataResponeLogin(String jsonRespone) throws JSONException {
        getStatusWarning(jsonRespone);
        if (!detail.equals("")) {
            JSONObject jsonObject = new JSONObject(detail);
            session_id = (String) jsonObject.getString("session_id");
            ho_ten = (String) jsonObject.getString("ho_ten");
            id_thanh_pho = (String) jsonObject.getString("id_thanh_pho");
            id_quan = (String) jsonObject.getString("id_quan");
            id_phuong = (String) jsonObject.getString("id_phuong");
        }
    }
}
