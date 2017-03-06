package group.vulner.ghov_dieuvan;

import android.Manifest;
import android.content.Context;
import android.hardware.Camera;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.FragmentManager;
import android.util.Base64;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.text.DateFormat;
import java.text.Normalizer;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


public class Utils {
    private static final String TAG = Utils.class.getSimpleName();

    public static String[] PERMISSION_REQUEST = new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_PHONE_STATE,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.CALL_PHONE,
            Manifest.permission.RECORD_AUDIO,
            Manifest.permission.CAMERA,
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.SEND_SMS, Manifest.permission.WRITE_SETTINGS};


    //  firebase
    public static String URL_FIREBASE_STORAGE = "gs://firebase-ghov.appspot.com";
    public static String URL_FIREBASE_DATABASE = "https://ghov.firebaseio.com/";
    public static String URL_FIREBASE_THONGTINNHANVIEN = URL_FIREBASE_DATABASE + "thongtinnhanvien/";

    public static String RESPONSE_SUCCESS = "success";
    public static String RESPONSE_FAIL = "fail";
    public static String RESPONSE_FILE_EXISTED = "file_existed";
    public static String RESPONSE_FAIL_CONNECT = "fail_connect";
    public static String LOGIN_FAIL = "login_fail";
    public static String NULL_STRING = "null";

    // API Server
    public static String STR_LIENKET = "&";
    public static String STR_EMAIL = "email=";
    public static String STR_PASSWORD = "password=";
    public static String STR_SESSION = "session_id=";
    public static String CODE_DEFAULT = "3d64859749e98c6ca234638cce8b7d69";
    public static String URL_GHOV_API = "http://api.giaohangongvang.vn/api/";
    public static String URL_GHOV_API_LOGIN = URL_GHOV_API + "login/login?api=" + CODE_DEFAULT;
    public static String URL_GHOV_API_DANGKY = "http://api.giaohangongvang.vn/api/login/register?api=3d64859749e98c6ca234638cce8b7d69";
    public static String URL_GHOV_API_DANGKY_NHANVIEN = "http://www.giaohangongvang.com/nhanvien/register-from-mobile";
    public static String URL_GHOV_API_LOGIN_NHANVIEN_MOBILE = "http://www.giaohangongvang.com/api/nhanvien/login";
    public static String URL_GHOV_API_UPLOAD_DONNHAN = "http://www.giaohangongvang.com/files/upload";
    public static String URL_GHOV_API_UPLOAD_APP_MOBILE_VER = "http://www.giaohangongvang.com/files/apkupdate/release.apk";

    //==============tạo đơn hàng======================
    public static String URL_GHOV_API_NHANVIEN_TAODONHANG_SDT = "http://www.giaohangongvang.com/api/donhang/create-donhang-sdt";
    //==============danh sách thành phố quận===========
    public static String URL_GHOV_API_DANH_SACH_THANH_PHO_QUAN = "http://www.giaohangongvang.com/api/khuvuc/get-thanhpho-quan";
    //==============danh sach nhan vien

    public static String URL_GHOV_API_DANHSACH_NHANVIEN = "http://www.giaohangongvang.com/nhanvien/get-list-nhanvien";
    //==============don nhan
    public static String URL_GHOV_API_NHANVIEN_DONNHAN_DANH_SACH_DON = "http://www.giaohangongvang.com/api/nhanvien/list-donhang-nhan";
    public static String URL_GHOV_API_NHANVIEN_DONNHAN_CHUYEN_DON = "http://www.giaohangongvang.com/api/nhanvien/chuyen-don";
    public static String URL_GHOV_API_NHANVIEN_DONNHAN_HUY_DON = "http://www.giaohangongvang.com/nhanvien/huy-donhang";
    public static String URL_GHOV_API_NHANVIEN_DONNHAN_NHAN_DON = "http://www.giaohangongvang.com/nhanvien/nhan-donhang";
    //==============don ton
    public static String URL_GHOV_API_NHANVIEN_DONTON_DANH_SACH_DON = "http://www.giaohangongvang.com/api/nhanvien/list-donhang-ton";
    public static String URL_GHOV_API_NHANVIEN_DONTON_NHAN_DON = "http://www.giaohangongvang.com/api/nhanvien/nhan-donhang-ton";
    public static String URL_GHOV_API_NHANVIEN_DONTON_CHUYEN_DON = "http://www.giaohangongvang.com/api/nhanvien/chuyen-donhang-ton";
    public static String URL_GHOV_API_NHANVIEN_DONGIAO_TIMKIEMSDT = "http://www.giaohangongvang.com/api/donhang/search-don-ton";
    public static String URL_GHOV_API_NHANVIEN_DONGIAO_TIMKIEMBARCODE = "http://www.giaohangongvang.com/api/donhang/scan-barcode";

    //==============don giao
    public static String URL_GHOV_API_NHANVIEN_DONGIAO_DANH_SACH_DON = "http://www.giaohangongvang.com/api/nhanvien/list-donhang-giao";
    public static String URL_GHOV_API_NHANVIEN_DONGIAO_HOAN_THANH_DON = "http://www.giaohangongvang.com/api/nhanvien/hoan-thanh-don";
    public static String URL_GHOV_API_NHANVIEN_DONGIAO_HUY_DON = "http://www.giaohangongvang.com/api/nhanvien/hoan-don";
    public static String URL_GHOV_API_NHANVIEN_DONGIAO_GHI_CHU_DON = "http://www.giaohangongvang.com/api/nhanvien/ghichu";
    public static String URL_GHOV_API_NHANVIEN_DONGIAO_DOI_DIA_CHI_DON = "http://www.giaohangongvang.com/api/nhanvien/doi-diachi";
    public static String URL_GHOV_API_NHANVIEN_DONGIAO_HEN_GIO_DON = "http://www.giaohangongvang.com/api/nhanvien/hengio";
    public static String URL_GHOV_API_NHANVIEN_DONGIAO_CHUPANH = "";

    //==============hang ve kho
    public static String URL_GHOV_API_NHANVIEN_HANGVEKHO_DANH_SACH_HOAN = "http://www.giaohangongvang.com/api/nhanvien/list-donhang-hoan";
    public static String URL_GHOV_API_NHANVIEN_HANGVEKHO_DANH_SACH_HEN_NGAY = "http://www.giaohangongvang.com/api/nhanvien/list-donhang-hen";
    public static String URL_GHOV_API_NHANVIEN_THANHTOAN_DANH_SACH = "http://www.giaohangongvang.com/api/nhanvien/list-donhang-thanhtoan";
    //===========================Thanh toán
    public static String URL_GHOV_API_NHANVIEN_THANHTOAN_CANTHANHTOAN = "http://www.giaohangongvang.com/api/thanhtoan/thanhtoan";

    //==============update location
    public static String URL_GHOV_API_NHANVIEN_UPDATE_LOCATION = "";

    //==============save Log========================
    public static String URL_GHOV_API_NHANVIEN_SAVE_LOG = "";

    // sdcard
    public static String JPG = ".jpg";
    public static String ROOT_FOLDER = "/sdcard/GHOV/";
    public static String URI_IMG = ROOT_FOLDER + "IMG/";
    public static String URI_IMG_NHAN = URI_IMG + "QUAN_LY_DON_NHAN/";
    public static String URI_IMG_GIAO = URI_IMG + "QUAN_LY_DON_GIAO/";
    public static String DIR_IMG_NHAN = ROOT_FOLDER + "IMG/QUAN_LY_DON_NHAN/";
    public static String DIR_IMG_GIAO = ROOT_FOLDER + "IMG/QUAN_LY_DON_GIAO/";
    public static String APK_UPDATE = ROOT_FOLDER + "app.apk";
    public static String FILE_THU_TU_DON_GIAO = ROOT_FOLDER + "DON_GIAO/thu_tu_don_giao.txt";
    public static String FILE_LOCATION = ROOT_FOLDER + "LOCATION/location.txt";


    public static String TYPE_CREATE_NEW_FILE = "create_new_file";

    public static String donnhan = "donnhan/";
    public FragmentManager getFragmentManager(){
        FragmentManager manager=getFragmentManager();
        return manager;
    }
    // for intent
    public static String INTENT_NHANVIEN = "INTENT_NHANVIEN";
    public static String INTENT_CUAHANG = "INTENT_CUAHANG";
    public static String INTENT_SODON = "INTENT_SODON";
    public static String INTENT_DATE_TIME = "INTENT_DATE_TIME";
    public static String INTENT_ID_DONHANG = "INTENT_ID_DONHANG";
    public static String INTENT_DIACHI_DONHANG = "INTENT_DIACHI_DONHANG";
    public static String INTENT_SDT_DONHANG = "INTENT_SDT_DONHANG";

    public static boolean CheckRespone(String jsonRespone) throws JSONException {
        JSONObject object = new JSONObject(jsonRespone);
        object.getString("status");
        if (object.getString("status").equals("success")) {
            return true;
        }
        return false;
    }
    public static boolean checkEmail(String email) {
        if (email.endsWith("@gmail.com"))
            return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
        else
            return false;
    }

    public static boolean checkInternetIsConnected(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }

    public static void threadSleep(long time) {
        try {
            Thread.sleep(time);
        } catch (Exception e) {
            Log.e(TAG, "Exception:" + e.toString());
        }
    }

    public static void createDirectoryRecursively(String path) {
        File root_dir_app = new File(path);
        if (!root_dir_app.exists())
            root_dir_app.mkdirs();

    }

    public static boolean writeToFile(String typeCreate, String data, String dirFileSoThuTu) {

        String nameFile = dirFileSoThuTu.split("/")[dirFileSoThuTu.split("/").length - 1];
        String dirFolder = dirFileSoThuTu.replace(nameFile, "");
        createDirectoryRecursively(dirFolder);

        try {
            File myFile = new File(dirFileSoThuTu);
            if (!myFile.exists() || typeCreate.equals(TYPE_CREATE_NEW_FILE)) {
                myFile.createNewFile();
            }
            FileOutputStream fOut = new FileOutputStream(myFile);
            OutputStreamWriter myOutWriter = new OutputStreamWriter(fOut);
            myOutWriter.append(data);
            myOutWriter.close();
            fOut.close();
            return true;
        } catch (Exception e) {
            Log.e("Exception", "" + e.toString());
            return false;
        }
    }

    public static String readFile(String dirFile) {
        StringBuilder text = new StringBuilder();
        try {
            File myFile = new File(dirFile);
            if (!myFile.exists())
                return "";

            BufferedReader br = new BufferedReader(new FileReader(myFile));
            String line;
            while ((line = br.readLine()) != null) {
                text.append(line);
                text.append('\n');
            }
            br.close();
        } catch (Exception e) {
            Log.e(TAG, "Exception: " + e.toString());
            return "";
        }
        return text.toString();
    }

    public static String getTimeString() {
        DateFormat df = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
        return df.format(Calendar.getInstance().getTime());
    }

    public static Camera getCameraInstance() {
        Camera c = null;
        try {
            c = Camera.open(); // attempt to get a Camera instance
        } catch (Exception e) {
            Log.e("Utils", e.toString());
        }
        return c; // returns null if camera is unavailable
    }

    public static File getOutputIMGFile(String dateTime, String nhanVien, String cuaHang, String donTong, int don) {
        String nameFolder = dateTime + "_" + nhanVien + "_" + cuaHang + "_" + donTong;
        File mediaStorageDir = new File(DIR_IMG_NHAN + nameFolder + "/");
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                return null;
            }
        }
        // Create a media file name
        File mediaFile;
        mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                nameFolder + "_" + don + ".jpg");

        return mediaFile;
    }

    public static File getOutputIMGFileDonGiao(String id_dongiao) {
        File mediaStorageDir = new File(DIR_IMG_GIAO + "/");
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                return null;
            }
        }
        // Create a media file name
        File mediaFile;
        mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                id_dongiao + ".jpg");

        return mediaFile;
    }

    public static String getCurrentDate() {
        return new SimpleDateFormat("dd-MM-yyyy").format(new Date());
    }

    public static String getCurrentDateYMD() {
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }

    public static String getCurrentTime() {
        return new SimpleDateFormat("HH:mm").format(new Date());
    }

    // remove
    public static String stripAccents(String s) {
        s = Normalizer.normalize(s, Normalizer.Form.NFD);
        s = s.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
        return s;
    }

    public static boolean checkTimeDay1SmallerOrEqualDay2(String day1, String day2) {
        String[] arrD1 = day1.split("-");
        String[] arrD2 = day2.split("-");

        int nam1 = Integer.parseInt(arrD1[0]), thang1 = Integer.parseInt(arrD1[1]), ngay1 = Integer.parseInt(arrD1[2]);
        int nam2 = Integer.parseInt(arrD2[0]), thang2 = Integer.parseInt(arrD2[1]), ngay2 = Integer.parseInt(arrD2[2]);

        if (nam1 < nam2) {
            return true;
        } else if (nam1 == nam2) {
            if (thang1 < thang2) {
                return true;
            } else if (thang1 == thang2) {
                if (ngay1 <= ngay2) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public static boolean checkTimeDay1SmallerDay2(String day1, String day2) {
        String[] arrD1 = day1.split("-");
        String[] arrD2 = day2.split("-");
        int nam1 = Integer.parseInt(arrD1[0]), thang1 = Integer.parseInt(arrD1[1]), ngay1 = Integer.parseInt(arrD1[2]);
        int nam2 = Integer.parseInt(arrD2[0]), thang2 = Integer.parseInt(arrD2[1]), ngay2 = Integer.parseInt(arrD2[2]);

        if (nam1 < nam2) {
            return true;
        } else if (nam1 == nam2) {
            if (thang1 < thang2) {
                return true;
            } else if (thang1 == thang2) {
                if (ngay1 < ngay2) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public static String convertDoubleToMoney(double num) {
        return NumberFormat.getNumberInstance(Locale.US).format(num);
    }

    public static String convertYMDToDM(String date) {
        String[] arrD1 = date.split("-");
        int nam1 = Integer.parseInt(arrD1[0]), thang1 = Integer.parseInt(arrD1[1]), ngay1 = Integer.parseInt(arrD1[2]);
        return ngay1 + "/" + thang1;
    }

    public static boolean deleteFile(String deleteFile) {
        File file = new File(deleteFile);
        return file.delete();
    }

    public static String encodeBase64(String input) throws UnsupportedEncodingException {
        return Base64.encodeToString(input.getBytes("UTF-8"), Base64.DEFAULT);
    }


}
