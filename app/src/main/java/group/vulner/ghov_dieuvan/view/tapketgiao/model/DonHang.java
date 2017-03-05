package group.vulner.ghov_dieuvan.view.tapketgiao.model;

/**
 * Created by TuTV on 2/20/2017.
 */

public class DonHang {
    private String idDonHang;
    private String idNhanVienGiaoHang;
    private String tenNguoiNhan;
    private String sdtNguoiNhan;
    private String diaChiNguoiNhan;
    private String quanHoatDong;

    public DonHang() {
    }

    public DonHang(String idDonHang, String idNhanVienGiaoHang, String tenNguoiNhan, String sdtNguoiNhan, String diaChiNguoiNhan, String quanHoatDong) {
        this.idDonHang = idDonHang;
        this.idNhanVienGiaoHang = idNhanVienGiaoHang;
        this.tenNguoiNhan = tenNguoiNhan;
        this.sdtNguoiNhan = sdtNguoiNhan;
        this.diaChiNguoiNhan = diaChiNguoiNhan;
        this.quanHoatDong = quanHoatDong;
    }

    public String getIdDonHang() {
        return idDonHang;
    }

    public void setIdDonHang(String idDonHang) {
        this.idDonHang = idDonHang;
    }

    public String getIdNhanVienGiaoHang() {
        return idNhanVienGiaoHang;
    }

    public void setIdNhanVienGiaoHang(String idNhanVienGiaoHang) {
        this.idNhanVienGiaoHang = idNhanVienGiaoHang;
    }

    public String getTenNguoiNhan(String ten_nguoi_nhan) {
        return tenNguoiNhan;
    }

    public void setTenNguoiNhan(String tenNguoiNhan) {
        this.tenNguoiNhan = tenNguoiNhan;
    }

    public String getSdtNguoiNhan() {
        return sdtNguoiNhan;
    }

    public void setSdtNguoiNhan(String sdtNguoiNhan) {
        this.sdtNguoiNhan = sdtNguoiNhan;
    }

    public String getDiaChiNguoiNhan() {
        return diaChiNguoiNhan;
    }

    public void setDiaChiNguoiNhan(String diaChiNguoiNhan) {
        this.diaChiNguoiNhan = diaChiNguoiNhan;
    }

    public String getQuanHoatDong() {
        return quanHoatDong;
    }

    public void setQuanHoatDong(String quanHoatDong) {
        this.quanHoatDong = quanHoatDong;
    }
}
