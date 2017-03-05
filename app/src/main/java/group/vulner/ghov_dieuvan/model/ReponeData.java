package group.vulner.ghov_dieuvan.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by TuTV on 2/16/2017.
 */

public class ReponeData {
    private String status = "status";
    private String detail = "detail";

    public boolean CheckRespone(String jsonRespone) throws JSONException {
        JSONObject object = new JSONObject(jsonRespone);
        object.getString("status");

        if (object.getString("status").equals("success") && object.getString("detail") != "") {
            return true;
        }
        return false;
    }
}
