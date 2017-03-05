package group.vulner.ghov_dieuvan.view.hangvekho.model;

import java.util.ArrayList;

/**
 * Created by TuTV on 3/4/2017.
 */

public class NhanVienGiaoHang_Hoan {
    private String idNhanVienGiaoHang_HHG;
    private String tenNhanVienGiao;
    private ArrayList<DonHang_Hoan> lstDonHangHHG;

    public NhanVienGiaoHang_Hoan(String idNhanVienGiaoHang_HHG, String tenNhanVienGiao, ArrayList<DonHang_Hoan> lstDonHangHHG) {
        this.idNhanVienGiaoHang_HHG = idNhanVienGiaoHang_HHG;
        this.tenNhanVienGiao = tenNhanVienGiao;
        this.lstDonHangHHG = lstDonHangHHG;
    }

    public NhanVienGiaoHang_Hoan(String idNhanVienGiaoHang_HHG) {
        this.idNhanVienGiaoHang_HHG = idNhanVienGiaoHang_HHG;
    }

    public NhanVienGiaoHang_Hoan() {
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

    public ArrayList<DonHang_Hoan> getLstDonHangHHG() {
        return lstDonHangHHG;
    }

    public void setLstDonHangHHG(ArrayList<DonHang_Hoan> lstDonHangHHG) {
        this.lstDonHangHHG = lstDonHangHHG;
    }
}

