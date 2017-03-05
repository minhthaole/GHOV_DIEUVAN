package group.vulner.ghov_dieuvan.view.dangky.fragment_dangky.giadinh;

/**
 * Created by APC on 10/15/2016.
 */
public class ThongTinGiaDinh {
    public static String VO = "VO";
    public static String CHONG = "CHONG";
    public static String CON = "CON";
    public static String GIOI_TINH_NAM = "Nam";
    public static String GIOI_TINH_NU = "Nu";

    public String name;
    public String gioitinh;
    public String quan_he;
    public String ngay_sinh;

    public ThongTinGiaDinh() {
        name = "";
        gioitinh = "";
        quan_he = "";
        ngay_sinh = "";
    }

    @Override
    public boolean equals(Object obj) {
        ThongTinGiaDinh thongtin = (ThongTinGiaDinh) obj;
        if (name.equals(thongtin.name) && gioitinh.equals(thongtin.gioitinh) && gioitinh.equals(thongtin.quan_he) && gioitinh.equals(thongtin.ngay_sinh)) {
            return true;
        } else {
            return false;
        }
    }
}
