package jdbc.JDBC_project;

//pojo class
public class Kayit {
    //1. adim fields->db column name
    private int id;
    private String isim;
    private String tel;

    //2.adim cons

    public Kayit() {
    }

    public Kayit(int id, String isim, String tel) {
        this.id = id;
        this.isim = isim;
        this.tel = tel;
    }

    //3.adim setter-getter

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIsim() {
        return isim;
    }

    public void setIsim(String isim) {
        this.isim = isim;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    //4.adim toString

    @Override
    public String toString() {
        return
                "id=" + id +
                        ", isim='" + isim + '\'' +
                        ", tel='" + tel + '\'';
    }
}