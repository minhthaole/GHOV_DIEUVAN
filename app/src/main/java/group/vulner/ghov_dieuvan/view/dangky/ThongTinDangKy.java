package group.vulner.ghov_dieuvan.view.dangky;

/**
 * Created by dungtrihp on 10/13/2016.
 */
public class ThongTinDangKy {

    public static String DA_KET_HON = "DA_KET_HON";
    public static String CHUA_KET_HON = "CHUA_KET_HON";
    public static String KHAC = "KHAC";
    public static String GIOI_TINH_NAM = "Nam";
    public static String GIOI_TINH_NU = "Nu";

    public String email;
    public String password;
    public String nhaplaipassword;

    public String anh_ca_nhan;
    public String name;
    public String gender;
    public String ngay_sinh;
    public String cmnd;
    public String ngay_cap;
    public String noi_cap;

    public String phone;
    public String dia_chi;
    public String nguoi_lien_he;
    public String phone_lien_he;

    public String tinh_trang_ket_hon;

    public ThongTinDangKy() {
        email = "";
        password = "";
        nhaplaipassword = "";

        name = "";
        gender = "";
        ngay_sinh = "";
        cmnd = "";
        ngay_cap = "";
        noi_cap = "";

        phone = "";
        dia_chi = "";
        nguoi_lien_he = "";
        phone_lien_he = "";

        tinh_trang_ket_hon = CHUA_KET_HON;

    }

}
