package jdbc.JDBC_Study;
import java.sql.*;
import java.util.Arrays;

public class J04_DML {
    public static void main(String[] args) throws SQLException {

        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_sql", "root", "Et.1641"); //database baglantisi icin
        Statement statement = connection.createStatement();//sql sorgu(query) calistirmak (execute etmek) icin


        System.out.println("   ***   task01   ***   ");
        // Task01-> Calisanlar tablosuna yeni bir kayit ((1005, 'Yıldız Hanım' , 33000)  ekleyelip kaydi teyit icin sorgulayınız.
    // int executeSatirSayisi=statement.executeUpdate("insert into calisanlar values(1005, 'Yıldız Hanım' , 33000)");
    // System.out.println("Yildiz Hanim record eklendi = " + executeSatirSayisi);  //1

        // table son hali intelij de gorelim...
        ResultSet rs=statement.executeQuery("select * from calisanlar");
        while (rs.next()){
            System.out.printf("%-6d%-20s%-6d\n",rs.getInt(1),rs.getString(2), rs.getInt(3));
        }


        System.out.println("   ***   task02   ***   ");
        // Task02-> Calisanlar tablosuna birden fazla yeni kayıt ekleyelim.

        // 1.YONTEM
        // Ayri ayri sorgular ile veritabanina tekrar tekrar ulasmak islemlerin yavas yapilmasina yol acar.
        // 10000 tane veri kaydi yapildigi dusunuldugunde  bu kotu bir yaklasimdir.
//        String [] insertQuery = {
//                "insert into calisanlar values(1006, 'Nuray Hanım' , 35000)",
//                "insert into calisanlar values(1007, 'Ebru Hanım' , 36000)",
//                "insert into calisanlar values(1008, 'Beyza Hanım' , 37000)",
//                "insert into calisanlar values(1009, 'Rasit Bey' , 38000)",
//                "insert into calisanlar values(1010, 'Ecan Bey' , 39000)"
//        };
//        int insertSatirSayisi = 0;
//        for (String avuc:insertQuery) {
//            insertSatirSayisi+=statement.executeUpdate(avuc);  // tekli simsek i kullaniyor
//        }
//        System.out.println("DB'ye "+insertSatirSayisi+ " veri eklendi");


        // 2.YONTEM --> addBatch ve excuteBatch() metotlari ile
        // addBatch() -> metodu ile SQL ifadeleri gruplandirilabilir ve exucuteBatch()  metodu ile veritabanina bir kere gonderilebilir.
        // ***!!!!**** excuteBatch() metodu bir int [] dizi dondurur ve  Bu dizi her bir ifade sonucunda etkilenen satir sayisini return eder.

        String [] insertQuery = {
                "insert into calisanlar values(1011, 'Mahmut Bey' , 35000)",
                "insert into calisanlar values(1012, 'Yasin Bey' , 36000)",
                "insert into calisanlar values(1013, 'Asya Hanım' , 37000)"
        };
        for (String avuc:insertQuery) {
            statement.addBatch(avuc);  // bu kod stege area gibi bir ortamda topluyor. asagidaki kod toplu simsek ile ekliyor
        }
        int [] satir = statement.executeBatch();  // toplu simsek i kullaniyor
        System.out.println("DB'ye "+ Arrays.toString(satir)+ " veri eklendi");
        System.out.println("DB'ye "+ satir.length+ " veri eklendi");

        connection.close();
        statement.close();
    }
}
