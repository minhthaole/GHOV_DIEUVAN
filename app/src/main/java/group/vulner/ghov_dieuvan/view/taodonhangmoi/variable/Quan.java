package group.vulner.ghov_dieuvan.view.taodonhangmoi.variable;

/**
 * Created by TuTV on 2/9/2017.
 */

public class Quan {
    private String id_quan;
    private String ten_quan;

    public Quan() {
    }

    public Quan(String id_quan, String ten_quan) {
        this.id_quan = id_quan;
        this.ten_quan = ten_quan;
    }

    public String getId_quan() {
        return id_quan;
    }

    public void setId_quan(String id_quan) {
        this.id_quan = id_quan;
    }

    public String getTen_quan() {
        return ten_quan;
    }

    public void setTen_quan(String ten_quan) {
        this.ten_quan = ten_quan;
    }

}
