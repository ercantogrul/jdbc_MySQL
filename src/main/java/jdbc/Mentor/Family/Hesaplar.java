package jdbc.Mentor.Family;

public class Hesaplar {
    private int id;
    private String ay;
    private String aciklama;
    private int gelir;
    private int gider;

    public Hesaplar() {
    }

    public Hesaplar(String ay, String aciklama, int gelir, int gider) {
        this.ay = ay;
        this.aciklama = aciklama;
        this.gelir = gelir;
        this.gider = gider;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAy() {
        return ay;
    }

    public void setAy(String ay) {
        this.ay = ay;
    }

    public String getAciklama() {
        return aciklama;
    }

    public void setAciklama(String aciklama) {
        this.aciklama = aciklama;
    }

    public int getGelir() {
        return gelir;
    }

    public void setGelir(int gelir) {
        this.gelir = gelir;
    }

    public int getGider() {
        return gider;
    }

    public void setGider(int gider) {
        this.gider = gider;
    }

    @Override
    public String toString() {
        return
                "id=" + id +
                ", ay='" + ay + '\'' +
                ", aciklama='" + aciklama + '\'' +
                ", gelir=" + gelir +
                ", gider=" + gider ;
    }
}
