package jdbc.JDBC_Study;

public class App_JDBCnedir {

}


/**
 * Hello world!
 *
 */

/*
Soru :  JDBC  Nedir ?
  - Veritabanlarının automation yapmamıza yardımcı oluyor
  - Sorgularımı gonderıyorum, gelen sonuclarla, UI da ki gozuken sonucları karsılastırıyorum (UI : user interface)


Soru :  JDBC yi nasıl kullanırsın ?
  - URL , username ve password vererek bir connection olusturur.
    Bu connection uzerinden statement olusturarak, sorgularimi calistiririm.
    Sonuclari ResultSet e atarak kullanirim.

    /*
1.KISIM: MAVEN PROJE OLUSTURMA
file/new project/mavenproject(wizarddan), cikmazsa other dan bak
/next/catalogdan internal i sec,filter da maven-archetype-quickstart 1.1 i sec(en yuksek versiyon ne ise)
/next/groupid(FIRMAADI):techproed
artifactid: (PROJEADI):JdbcDersler
/finish

2.KISIM:POM AYARLARI ve PROJE UPDATE ETMEK
    <dependencies>
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.11</version>
        </dependency>
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>8.0.28</version>
        </dependency>
    </dependencies>

    POM AYARLARINA ORACLE DATABASEJDBC yi ekliyoruz
    ben scope kismini sildim junitde cunku kullanmiyacagiz.
    Maven project olusturup Pom lari duzenledikten sonra save all,yapiyoruz
    sonra source yapip FORMAT yapiyoruz yine save varsa save edin.
    PROJE GUNCELLEMESI: projenin ustune sag tiklayip/Maven/UpdateProject i secin.

    3.KISIM) projenize gidin src deki sizin icin olusturulan paeti silin siz kendiniz
    istediginiz isimde paket olusturun.Ornek:JdbcTurkceDersler
    Paketin altina main method secerek class olusturun. Ornek:JdbcQuery01

    4.KISIM) SQL Structure nasil olacak?
    */
    /* how can you create a connection with a database by using jdbc?
              STRUCTURE OF JDBC
  1)import java.sql.*; (under package,import)
  2)Class.forName("oracle.jdbc.driver.OracleDriver");(choose throw declaration)
  ----class ismini ekle demek...
  3)for Mac: Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaCAN", "root", "12345678"); //database baglantisi icin
  for Windows:Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaCAN", "root", "12345678"); //database baglantisi icin
  (choose throw declaration)
  (in jdbc, we only have one exception which is SQLException)
  4)  Statement st = con.createStatement();
  5)  ResultSet rs=st.executeQuery("SELECT * FROM tableName");
  6)  //table da 1.olusturulan sutun string se rs.getString(1), numbersa rs.getInt(1) 1--->sutunnosu
       //date ise rs.getDate(sutunno)
      //ornek: 2.sutun numbersa rs.getInt(2);
      //yada soyle yapabilirsin
       //ornek: Select isim, id, maas;
                       rs.getString("isim"), rs.getString("id"),rs.getDouble("maas")
      //resultlarim birden cok loop la almam gerekli.
       //bir deger olsa bile kursor donmesi icin next() olmasi lazim yani loop
     while(rs.next()) {
     System.out.println(rs.getString(1)+"-"+rs.getString(2)+"-"+rs.getInt(3));
     }
  7)  don't forget to close connections:)
      con.close();
      st.close();
      rs.close();
  //her zaman ilk 4 asama ayni....

  */

















