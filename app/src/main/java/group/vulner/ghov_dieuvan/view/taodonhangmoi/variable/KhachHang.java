package group.vulner.ghov_dieuvan.view.taodonhangmoi.variable;

/**
 * Created by TuTV on 2/9/2017.
 */

public class KhachHang {
    private String ten;
    private String email;
    private String sdt;
    private String diaChi;
    private String thanhPho;
    private String loaiKhachHang;

    public KhachHang(String ten, String email, String sdt, String diaChi, String thanhPho, String loaiKhachHang) {
        this.ten = ten;
        this.email = email;
        this.sdt = sdt;
        this.diaChi = diaChi;
        this.thanhPho = thanhPho;
        this.loaiKhachHang = loaiKhachHang;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getThanhPho() {
        return thanhPho;
    }

    public void setThanhPho(String thanhPho) {
        this.thanhPho = thanhPho;
    }

    public String getLoaiKhachHang() {
        return loaiKhachHang;
    }

    public void setLoaiKhachHang(String loaiKhachHang) {
        this.loaiKhachHang = loaiKhachHang;
    }
}
