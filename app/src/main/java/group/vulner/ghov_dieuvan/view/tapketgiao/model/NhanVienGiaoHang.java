package group.vulner.ghov_dieuvan.view.tapketgiao.model;

import java.util.ArrayList;

/**
 * Created by TuTV on 2/20/2017.
 */

public class NhanVienGiaoHang {
    private String idNhanVienGiaoHang;
    private ArrayList<DonHang> cacDonHangMaNhanVienGiao;

    public NhanVienGiaoHang(String idNhanVienGiaoHang, ArrayList<DonHang> cacDonHangMaNhanVienGiao) {
        this.idNhanVienGiaoHang = idNhanVienGiaoHang;
        this.cacDonHangMaNhanVienGiao = cacDonHangMaNhanVienGiao;
    }

    public NhanVienGiaoHang() {
    }

    public String getIdNhanVienGiaoHang() {
        return idNhanVienGiaoHang;
    }

    public void setIdNhanVienGiaoHang(String idNhanVienGiaoHang) {
        this.idNhanVienGiaoHang = idNhanVienGiaoHang;
    }

    public ArrayList<DonHang> getCacDonHangMaNhanVienGiao() {
        return cacDonHangMaNhanVienGiao;
    }

    public void setCacDonHangMaNhanVienGiao(ArrayList<DonHang> cacDonHangMaNhanVienGiao) {
        this.cacDonHangMaNhanVienGiao = cacDonHangMaNhanVienGiao;
    }
}
