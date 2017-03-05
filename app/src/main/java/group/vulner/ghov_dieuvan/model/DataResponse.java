package group.vulner.ghov_dieuvan.model;


import org.json.JSONException;
import org.json.JSONObject;

public class    DataResponse {
    public static String RESPONSE_SUCCESS = "success";
    public static String RESPONSE_FAIL = "fail";
    public String status;
    public String warning;
    public String detail;

    public void getStatusWarning(String jsonResponse) throws JSONException {
        JSONObject jsonObject = new JSONObject(jsonResponse);
        status = (String) jsonObject.getString("status");
        warning = (String) jsonObject.getString("warning");
        detail = (String) jsonObject.getString("detail");
    }


    public boolean checkStatusSuccess() {
        if (RESPONSE_SUCCESS.equals(status))//???
            return true;
        return false;
    }

}
