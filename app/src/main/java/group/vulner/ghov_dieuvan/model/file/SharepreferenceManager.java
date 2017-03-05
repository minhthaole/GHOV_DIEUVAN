package group.vulner.ghov_dieuvan.model.file;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by sev_user on 7/9/2016.
 */
public class SharepreferenceManager {
    private Context mContext;
    private SharedPreferences savefile;


    //======================
    public static String EMAIL_SAVE = "EMAIL_SAVE";
    public static String TENNHANVIEN_SAVE = "TENNHANVIEN_SAVE";
    public static String SESSION_SAVE = "SESSION_SAVE";
    public static String ID_THANHPHO_SAVE = "ID_THANHPHO_SAVE";
    public static String ID_QUAN_SAVE = "ID_QUAN_SAVE";
    public static String ID_PHUONG_SAVE = "ID_PHUONG_SAVE";

    public static String SAVE_NAME = "save_file";

    //====================== for fragment
    public static String FRAGMENT_NAME = "FRAGMENT_NAME";
    public static String FRAGMENT_DONNHAN = "FRAGMENT_DON_NHAN";
    public static String FRAGMENT_DONTON = "FRAGMENT_DON_TON";
    public static String FRAGMENT_DONGIAO = "FRAGMENT_DON_GIAO";
    public static String FRAGMENT_NHANDONGIAO = "FRAGMENT_NHAN_DON_GIAO";
    public static String FRAGMENT_NAME_NHANDONGIAO = "FRAGMENT_NAME_NHANDONGIAO";
    public static String FRAGMENT_NHANDONGIAO_BARCODE = "FRAGMENT_NHAN_DON_GIAO_BARCODE";
    public static String FRAGMENT_NHANDONGIAO_SDT = "FRAGMENT_NHAN_DON_GIAO_SDT";


    public SharepreferenceManager(Context context) {
        mContext = context;
        savefile = mContext.getSharedPreferences(SAVE_NAME, 0);
    }

    public void saveSession(String session) {
        SharedPreferences.Editor editor = savefile.edit();
        editor.putString(SESSION_SAVE, session);
        editor.commit();
    }

    public String getSession(String defaultValue) {
        return savefile.getString(SESSION_SAVE, defaultValue);
    }

    public void saveString(String nameSharePreference, String strSave) {
        SharedPreferences.Editor editor = savefile.edit();
        editor.putString(nameSharePreference, strSave);
        editor.commit();
    }

    public String getString(String nameSharePreference) {
        return savefile.getString(nameSharePreference, null);
    }

    //=========================== ho ten
    public void saveTenNhanVien(String tenNhanVien) {
        SharedPreferences.Editor editor = savefile.edit();
        editor.putString(TENNHANVIEN_SAVE, tenNhanVien);
        editor.commit();
    }

    public String getTenNhanVien() {
        return savefile.getString(TENNHANVIEN_SAVE, null);
    }

    //=========================== thanh pho
    public void saveIdThanhPho(String idThanhPho) {
        SharedPreferences.Editor editor = savefile.edit();
        editor.putString(ID_THANHPHO_SAVE, idThanhPho);
        editor.commit();
    }

    public String getIdThanhpho() {
        return savefile.getString(ID_THANHPHO_SAVE, null);
    }

    //=========================== id quan
    public void saveIdQuan(String idQuan) {
        SharedPreferences.Editor editor = savefile.edit();
        editor.putString(ID_QUAN_SAVE, idQuan);
        editor.commit();
    }

    public String getIdQuan() {
        return savefile.getString(ID_QUAN_SAVE, null);
    }

    //=========================== id phuong
    public void saveIdPhuong(String IdPhuong) {
        SharedPreferences.Editor editor = savefile.edit();
        editor.putString(ID_PHUONG_SAVE, IdPhuong);
        editor.commit();
    }

    public String getIdPhuong() {
        return savefile.getString(ID_PHUONG_SAVE, null);
    }

    //===========================email
    public void saveEmail(String email) {
        SharedPreferences.Editor editor = savefile.edit();
        editor.putString(EMAIL_SAVE, email);
        editor.commit();
    }

    public String getEmail() {
        return savefile.getString(EMAIL_SAVE, null);
    }

    //===========================fragment
    public void saveFragment(String fragmentName) {
        SharedPreferences.Editor editor = savefile.edit();
        editor.putString(FRAGMENT_NAME, fragmentName);
        editor.commit();
    }

    public String getFragment() {
        return savefile.getString(FRAGMENT_NAME, null);
    }

    //===========================fragment nhan don giao
    public void saveFragmentNhanDonGiao(String fragmentName) {
        SharedPreferences.Editor editor = savefile.edit();
        editor.putString(FRAGMENT_NAME_NHANDONGIAO, fragmentName);
        editor.commit();
    }

    public String getFragmentNhanDonGiao() {
        return savefile.getString(FRAGMENT_NAME_NHANDONGIAO, null);
    }
}
