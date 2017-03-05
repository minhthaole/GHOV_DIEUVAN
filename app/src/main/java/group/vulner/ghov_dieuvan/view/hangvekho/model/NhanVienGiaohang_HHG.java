package group.vulner.ghov_dieuvan.view.hangvekho.model;

import java.util.ArrayList;

/**
 * Created by TuTV on 3/2/2017.
 */

public class NhanVienGiaohang_HHG {
    private String idNhanVienGiaoHang_HHG;
    private String tenNhanVienGiao;
    private ArrayList<DonHang_HHG> lstDonHangHHG;

    public NhanVienGiaohang_HHG(String idNhanVienGiaoHang_HHG, String tenNhanVienGiao, ArrayList<DonHang_HHG> lstDonHangHHG) {
        this.idNhanVienGiaoHang_HHG = idNhanVienGiaoHang_HHG;
        this.tenNhanVienGiao = tenNhanVienGiao;
        this.lstDonHangHHG = lstDonHangHHG;
    }

    public NhanVienGiaohang_HHG(String idNhanVienGiaoHang_HHG) {
        this.idNhanVienGiaoHang_HHG = idNhanVienGiaoHang_HHG;
    }

    public NhanVienGiaohang_HHG() {
    }

    public String getIdNhanVienGiaoHang_HHG() {
        return idNhanVienGiaoHang_HHG;
    }

    public void setIdNhanVienGiaoHang_HHG(String idNhanVienGiaoHang_HHG) {
        this.idNhanVienGiaoHang_HHG = idNhanVienGiaoHang_HHG;
    }

    public String getTenNhanVienGiao() {
        return tenNhanVienGiao;
    }

    public void setTenNhanVienGiao(String tenNhanVienGiao) {
        this.tenNhanVienGiao = tenNhanVienGiao;
    }

    public ArrayList<DonHang_HHG> getLstDonHangHHG() {
        return lstDonHangHHG;
    }

    public void setLstDonHangHHG(ArrayList<DonHang_HHG> lstDonHangHHG) {
        this.lstDonHangHHG = lstDonHangHHG;
    }
}
