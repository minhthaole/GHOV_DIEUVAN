package group.vulner.ghov_dieuvan.model;

/**
 * Created by TuTV on 2/8/2017.
 */

public class DiaChi {
    private String tenThanhPho, tenQuan;

    public DiaChi(String tenThanhPho, String tenQuan) {
        this.tenThanhPho = tenThanhPho;
        this.tenQuan = tenQuan;
    }

    public String getTenThanhPho() {
        return tenThanhPho;
    }

    public void setTenThanhPho(String tenThanhPho) {
        this.tenThanhPho = tenThanhPho;
    }

    public String getTenQuan() {
        return tenQuan;
    }

    public void setTenQuan(String tenQuan) {
        this.tenQuan = tenQuan;
    }
}
