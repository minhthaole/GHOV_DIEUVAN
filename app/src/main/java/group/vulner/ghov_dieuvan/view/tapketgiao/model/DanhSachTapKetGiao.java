package group.vulner.ghov_dieuvan.view.tapketgiao.model;

/**
 * Created by TuTV on 2/17/2017.
 */

public class DanhSachTapKetGiao {
    private String id_don_hang;
    private String ten_nguoi_nhan;
    private String sdt_nguoi_nhan;
    private String dia_chi_nguoi_nhan;
    private String ten_nguoi_gui;
    private String sdt_nguoi_gui;
    private String dia_chi_nguoi_gui;
    private String tinh_trang_don_hang;
    private String tien_thu_ho;
    private String id_quan;
    private String id_thanh_pho;
    private String ghi_chu;
    private String id_nhan_vien_giao;

    public DanhSachTapKetGiao(String id_don_hang, String ten_nguoi_nhan, String sdt_nguoi_nhan, String dia_chi_nguoi_nhan, String ten_nguoi_gui, String sdt_nguoi_gui, String dia_chi_nguoi_gui, String tinh_trang_don_hang, String tien_thu_ho, String id_quan, String id_thanh_pho, String ghi_chu, String id_nhan_vien_giao) {
        this.id_don_hang = id_don_hang;
        this.ten_nguoi_nhan = ten_nguoi_nhan;
        this.sdt_nguoi_nhan = sdt_nguoi_nhan;
        this.dia_chi_nguoi_nhan = dia_chi_nguoi_nhan;
        this.ten_nguoi_gui = ten_nguoi_gui;
        this.sdt_nguoi_gui = sdt_nguoi_gui;
        this.dia_chi_nguoi_gui = dia_chi_nguoi_gui;
        this.tinh_trang_don_hang = tinh_trang_don_hang;
        this.tien_thu_ho = tien_thu_ho;
        this.id_quan = id_quan;
        this.id_thanh_pho = id_thanh_pho;
        this.ghi_chu = ghi_chu;
        this.id_nhan_vien_giao = id_nhan_vien_giao;
    }

    public DanhSachTapKetGiao() {
    }

    public String getId_don_hang() {
        return id_don_hang;
    }

    public void setId_don_hang(String id_don_hang) {
        this.id_don_hang = id_don_hang;
    }

    public String getTen_nguoi_nhan() {
        return ten_nguoi_nhan;
    }

    public void setTen_nguoi_nhan(String ten_nguoi_nhan) {
        this.ten_nguoi_nhan = ten_nguoi_nhan;
    }

    public String getSdt_nguoi_nhan() {
        return sdt_nguoi_nhan;
    }

    public void setSdt_nguoi_nhan(String sdt_nguoi_nhan) {
        this.sdt_nguoi_nhan = sdt_nguoi_nhan;
    }

    public String getDia_chi_nguoi_nhan() {
        return dia_chi_nguoi_nhan;
    }

    public void setDia_chi_nguoi_nhan(String dia_chi_nguoi_nhan) {
        this.dia_chi_nguoi_nhan = dia_chi_nguoi_nhan;
    }

    public String getTen_nguoi_gui() {
        return ten_nguoi_gui;
    }

    public void setTen_nguoi_gui(String ten_nguoi_gui) {
        this.ten_nguoi_gui = ten_nguoi_gui;
    }

    public String getSdt_nguoi_gui() {
        return sdt_nguoi_gui;
    }

    public void setSdt_nguoi_gui(String sdt_nguoi_gui) {
        this.sdt_nguoi_gui = sdt_nguoi_gui;
    }

    public String getDia_chi_nguoi_gui() {
        return dia_chi_nguoi_gui;
    }

    public void setDia_chi_nguoi_gui(String dia_chi_nguoi_gui) {
        this.dia_chi_nguoi_gui = dia_chi_nguoi_gui;
    }

    public String getTinh_trang_don_hang() {
        return tinh_trang_don_hang;
    }

    public void setTinh_trang_don_hang(String tinh_trang_don_hang) {
        this.tinh_trang_don_hang = tinh_trang_don_hang;
    }

    public String getTien_thu_ho() {
        return tien_thu_ho;
    }

    public void setTien_thu_ho(String tien_thu_ho) {
        this.tien_thu_ho = tien_thu_ho;
    }

    public String getId_quan() {
        return id_quan;
    }

    public void setId_quan(String id_quan) {
        this.id_quan = id_quan;
    }

    public String getId_thanh_pho() {
        return id_thanh_pho;
    }

    public void setId_thanh_pho(String id_thanh_pho) {
        this.id_thanh_pho = id_thanh_pho;
    }

    public String getGhi_chu() {
        return ghi_chu;
    }

    public void setGhi_chu(String ghi_chu) {
        this.ghi_chu = ghi_chu;
    }

    public String getId_nhan_vien_giao() {
        return id_nhan_vien_giao;
    }

    public void setId_nhan_vien_giao(String id_nhan_vien_giao) {
        this.id_nhan_vien_giao = id_nhan_vien_giao;
    }
}
