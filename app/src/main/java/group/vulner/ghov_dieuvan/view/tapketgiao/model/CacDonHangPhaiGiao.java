package group.vulner.ghov_dieuvan.view.tapketgiao.model;

/**
 * Created by TuTV on 2/20/2017.
 */

public class CacDonHangPhaiGiao {
    private String maDonHang;
    private String quanNhan;
    private String tienThuHo;

    public CacDonHangPhaiGiao() {
    }

    public CacDonHangPhaiGiao(String maDonHang, String quanNhan, String tienThuHo) {
        this.maDonHang = maDonHang;
        this.quanNhan = quanNhan;
        this.tienThuHo = tienThuHo;
    }

    public String getMaDonHang() {
        return maDonHang;
    }

    public void setMaDonHang(String maDonHang) {
        this.maDonHang = maDonHang;
    }

    public String getQuanNhan() {
        return quanNhan;
    }

    public void setQuanNhan(String quanNhan) {
        this.quanNhan = quanNhan;
    }

    public String getTienThuHo() {
        return tienThuHo;
    }

    public void setTienThuHo(String tienThuHo) {
        this.tienThuHo = tienThuHo;
    }
}
