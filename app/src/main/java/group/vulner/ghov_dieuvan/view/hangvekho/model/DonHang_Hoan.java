package group.vulner.ghov_dieuvan.view.hangvekho.model;

/**
 * Created by TuTV on 3/2/2017.
 */

public class DonHang_Hoan {
    private String id_Hoan;
    private String trackingId_Hoan;
    private String idKhachHang_Hoan;
    private String maDonHang_Hoan;
    private String idNhanVienGiao_Hoan;
    private String tenNhanVienGiao_Hoan;
    private String tenNguoiNhanHang_Hoan;
    private String sdtNguoiNhan_Hoan;
    private String doiDiaChiNhan_Hoan;
    private String tienThuHo_Hoan;

    public DonHang_Hoan(String id_Hoan, String trackingId_Hoan, String idKhachHang_Hoan, String maDonHang_Hoan, String idNhanVienGiao_Hoan, String tenNhanVienGiao_Hoan, String tenNguoiNhanHang_Hoan, String sdtNguoiNhan_Hoan, String doiDiaChiNhan_Hoan, String tienThuHo_Hoan, String ghiChu_Hoan) {
        this.id_Hoan = id_Hoan;
        this.trackingId_Hoan = trackingId_Hoan;
        this.idKhachHang_Hoan = idKhachHang_Hoan;
        this.maDonHang_Hoan = maDonHang_Hoan;
        this.idNhanVienGiao_Hoan = idNhanVienGiao_Hoan;
        this.tenNhanVienGiao_Hoan = tenNhanVienGiao_Hoan;
        this.tenNguoiNhanHang_Hoan = tenNguoiNhanHang_Hoan;
        this.sdtNguoiNhan_Hoan = sdtNguoiNhan_Hoan;
        this.doiDiaChiNhan_Hoan = doiDiaChiNhan_Hoan;
        this.tienThuHo_Hoan = tienThuHo_Hoan;
        this.ghiChu_Hoan = ghiChu_Hoan;
    }

    private String ghiChu_Hoan;

    public DonHang_Hoan(String id_Hoan) {
        this.id_Hoan = id_Hoan;
    }

    public DonHang_Hoan() {
    }

    public String getId_Hoan() {
        return id_Hoan;
    }

    public void setId_Hoan(String id_Hoan) {
        this.id_Hoan = id_Hoan;
    }

    public String getTrackingId_Hoan() {
        return trackingId_Hoan;
    }

    public void setTrackingId_Hoan(String trackingId_Hoan) {
        this.trackingId_Hoan = trackingId_Hoan;
    }

    public String getIdKhachHang_Hoan() {
        return idKhachHang_Hoan;
    }

    public void setIdKhachHang_Hoan(String idKhachHang_Hoan) {
        this.idKhachHang_Hoan = idKhachHang_Hoan;
    }

    public String getMaDonHang_Hoan() {
        return maDonHang_Hoan;
    }

    public void setMaDonHang_Hoan(String maDonHang_Hoan) {
        this.maDonHang_Hoan = maDonHang_Hoan;
    }

    public String getIdNhanVienGiao_Hoan() {
        return idNhanVienGiao_Hoan;
    }

    public void setIdNhanVienGiao_Hoan(String idNhanVienGiao_Hoan) {
        this.idNhanVienGiao_Hoan = idNhanVienGiao_Hoan;
    }

    public String getTenNhanVienGiao_Hoan() {
        return tenNhanVienGiao_Hoan;
    }

    public void setTenNhanVienGiao_Hoan(String tenNhanVienGiao_Hoan) {
        this.tenNhanVienGiao_Hoan = tenNhanVienGiao_Hoan;
    }

    public String getTenNguoiNhanHang_Hoan() {
        return tenNguoiNhanHang_Hoan;
    }

    public void setTenNguoiNhanHang_Hoan(String tenNguoiNhanHang_Hoan) {
        this.tenNguoiNhanHang_Hoan = tenNguoiNhanHang_Hoan;
    }

    public String getSdtNguoiNhan_Hoan() {
        return sdtNguoiNhan_Hoan;
    }

    public void setSdtNguoiNhan_Hoan(String sdtNguoiNhan_Hoan) {
        this.sdtNguoiNhan_Hoan = sdtNguoiNhan_Hoan;
    }

    public String getDoiDiaChiNhan_Hoan() {
        return doiDiaChiNhan_Hoan;
    }

    public void setDoiDiaChiNhan_Hoan(String doiDiaChiNhan_Hoan) {
        this.doiDiaChiNhan_Hoan = doiDiaChiNhan_Hoan;
    }

    public String getTienThuHo_Hoan() {
        return tienThuHo_Hoan;
    }

    public void setTienThuHo_Hoan(String tienThuHo_Hoan) {
        this.tienThuHo_Hoan = tienThuHo_Hoan;
    }

    public String getGhiChu_Hoan() {
        return ghiChu_Hoan;
    }

    public void setGhiChu_Hoan(String ghiChu_Hoan) {
        this.ghiChu_Hoan = ghiChu_Hoan;
    }
}
