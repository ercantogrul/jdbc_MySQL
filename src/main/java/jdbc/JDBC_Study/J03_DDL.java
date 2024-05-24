package jdbc.JDBC_Study;
import java.sql.*;

public class J03_DDL {
          /*
               A) CREATE TABLE, DROP TABLE, ALTER TABLE gibi DDL ifadeleri icin sonuc kümesi (ResultSet)
                  dondurmeyen metotlar kullanilmalidir. Bunun icin JDBC'de 2 alternatif bulunmaktadir.
                     1) execute() metodu
                     2) excuteUpdate() metodu.

               B)   - execute() metodu her tur SQL ifadesiyle kullanibilen genel bir komuttur.
                    - execute(), Boolean bir deger dondurur.
                    - DDL islemlerin false dondururken, DML islemlerinde true deger dondurur.(ResultSet olusturur)
                    - Ozellikle hangi tip SQL ifadesinin kullanilmasinin gerektiginin belli olmadigi
                      durumlarda tercih edilmektedir.

               C) - executeUpdate() metodu ise INSERT, Update gibi DML islemlerinde yaygin kullanilir.
                  -  bu islemlerde islemden etkilenen satir sayisini return eder.
                  - Ayrıca, DDL islemlerinde de kullanilabilir ve bu islemlerde 0 return eder.

                execute() her turlu SQL kjomutuyla kullanilir .......  DDL ---> False    DML----> True
                executeUpdate()  DDL ----> 0           DML ----->etkilenen satir sayisini verir
           */
          public static void main(String[] args) throws SQLException {
              Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_sql", "root", "Et.1641"); //database baglantisi icin
              Statement statement = connection.createStatement();//sql sorgu(query) calistirmak (execute etmek) icin

              System.out.println("   ***   task01   ***   ");
              // Task01-> markalar adında bir tablo oluşturunuz. marka_id int, marka_isim VARCHAR(15), calisan_sayisi int
              String query = "create table markalar (marka_id int, marka_isim VARCHAR(15), calisan_sayisi int)";
              // 1. yöntem-> execute() methodu ile
              //boolean s1 = statement.execute(query);  // false döndürünce tablo olustu demektir
              //System.out.println("Markalar tablosu create edldi "+ s1);  // 2. yöntem ile table create ederken hata olusmamasi (2 defa) icin bu iki satiri yoruma aldik
              //System.out.println("Markalar tablosu create edldi "+ statement.execute(query); // yukardaki kod ile ayni

          // execute();-> Boolean DDL:false DML:True bir deger return eder.
          // DDL islemlerin false dondururken(ResultSet olusturmadigi icin),  DML islemlerinde true deger dondurur.(ResultSet olusturur)

              // 2. yöntem-> executeUpdate() methodu ile
             // int s2 = statement.executeUpdate(query);
             // System.out.println("Markalar tablosu create edldi "+ s2);


              System.out.println("   ***   task02   ***   ");
              // Task02->  markalar tablosunu siliniz
             // System.out.println("Markalar tablosu silindi " + statement.execute("drop table markalar"));

              System.out.println("   ***   task03   ***   ");
              // Task03-> markalar tablosuna yeni bir sutun {sube_sayisi int} ekleyiniz.
             // String alterQuery = "alter table markalar add column sube_sayisi int";
             // statement.execute(alterQuery);
             // System.out.println("Markalar tablosuna sube sayisi eklendi  "+statement.execute("alter table markalar add column sube_sayisi int"));

              System.out.println("   ***   task04   ***   ");
              // Task04-> markalar tablosuna yeni bir sutun {sube_sayisi int} ekleyiniz, ancak bu sutunun yeri marka_id den sonra olsun
             // System.out.println("Markalar tablosuna sube sayisi eklendi  "+statement.execute("alter table markalar add column sube_sayisi_1 int after marka_id"));

              System.out.println("   ***   task5   ***   ");
              // Task05-> markalar tablosunun adini  brands olarak degistiriniz
            // System.out.println("Markalar tablosundaki marka_isim colonu isim yapildi  "+statement.execute("alter table markalar rename to brands"));
            // System.out.println("Markalar tablosundaki marka_isim colonu isim yapildi  "+statement.execute("alter table brands rename to markalar"));

              System.out.println("   ***   task06   ***   ");
              // Task06-> markalar tablosunda marka_isim sutununu isim olarak degistiriniz
             // System.out.println("Markalar tablosundaki marka_isim colonu isim yapildi  "+statement.execute("alter table markalar rename column marka_isim to isim"));
             // System.out.println("Markalar tablosundaki marka_isim colonu isim yapildi  "+statement.execute("alter table markalar rename column isim to marka_isim"));

              System.out.println("   ***   task07   ***   ");
              // Task07-> markalar tablosunda marka_isim sutununun data type ini char(20) olarak degistiriniz
              System.out.println("Markalar tablosundaki marka_isim sutununun data type ini char(20) yapildi  "+
                      statement.execute("alter table markalar modify marka_isim char(20)"));

              connection.close();
              statement.close();
          }
}
