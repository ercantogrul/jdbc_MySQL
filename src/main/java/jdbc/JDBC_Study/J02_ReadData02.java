package jdbc.JDBC_Study;

import java.sql.*;

public class J02_ReadData02 {
    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_sql", "root", "Et.1641"); //database baglantisi icin
        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);//sql sorgu(query) calistirmak (execute etmek) icin

        // rs resultest'i cursoru scroll'a duyarsız sadece read yapma tanımlandı.
        // bu parametre statement'e tanımlanmzasa default olarak cursor-1 imleç iterator tanımlar...
        ResultSet rs = statement.executeQuery("select * from personel ");


        System.out.println("   ************ task00 ********************************************************     ");
        Statement statement1 = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);//sql sorgu(query) calistirmak (execute etmek) icin
        ResultSet rs1 = statement1.executeQuery("select * from personel ");
        while (rs1.next()) {
            System.out.printf("%-6d%-20s%-20s%-6d%-20s\n", rs1.getInt(1),rs1.getString(2),
                    rs1.getString(3), rs1.getInt(4),rs1.getString(5));
        }
        System.out.println("   ************ task00 ********************************************************     ");


        System.out.println("   ************ task01 ************     ");
        // task01-> ilk satırdaki ilk obj print eden code create ediniz.
        int mevcutSatir = rs.getRow();
        System.out.println("mevcutSatir = " + mevcutSatir);
        rs.first();
        System.out.println("rs.getObject(1) = " + rs.getObject(1));

        System.out.println("   ************ *** task02 ******************     ");
        // task02->ilk sutunun 5. değerini print eden code create ediniz-> 5.satırın ilk sutunundaki record
        System.out.println("rs.getRow() = " + rs.getRow());  // getRow() bana imlecin nerede olduguinu gösterir
        rs.absolute(5); // cursor u 5. satira atadi.
        // absolute(a); -> cursor parametre girilen a. satıra atanır
        System.out.println("rs.getObject(1) = " + rs.getObject(1));


        System.out.println("   *************** task03 ***************      ");
        // task03->tum id listeyi print eden code create ediniz.
        mevcutSatir = rs.getRow();
        System.out.println("mevcutSatir = " + mevcutSatir);
        rs.absolute(0);  // cursor u 0' a atadik

        while (rs.next()) {
            System.out.println("rs.getObject(1) = " + rs.getObject(1));
        }


        System.out.println("   *************** task04 ***************      ");
        // task04->tum isim listeyi print eden code create ediniz.
        rs.absolute(0);  // cursor u 0' a atadik
        // *** default olarak curser 0 da dir
        while (rs.next()) {
            // System.out.println("rs.getObject(2) = " + rs.getObject(2));
            System.out.println("rs.getObject(2) = " + rs.getObject("isim"));
        }

        System.out.println("   ***************  task05 ************     ");
        // task05->tum sehir listeyi print eden code create ediniz.
        rs.absolute(0);  // cursor u 0' a atadik
        while (rs.next()) {
           // System.out.println("rs.getObject(3) = " + rs.getObject(3));
            System.out.println("rs.getObject(\"sehir\") = " + rs.getObject("sehir"));
        }


        System.out.println("   ************ task06 ************     ");
        // task06->tum maas listeyi print eden code create ediniz.
        rs.absolute(0);  // cursor u 0' a atadik
        while (rs.next()) {
            // System.out.println("rs.getObject(4) = " + rs.getObject(4));
            System.out.println("rs.getObject(\"maas\") = " + rs.getObject("maas"));
        }


        System.out.println("   ************ task07 ************     ");
        // task07->tum sirket listeyi print eden code create ediniz.
        rs.absolute(0);  // cursor u 0' a atadik
        while (rs.next()) {
            // System.out.println("rs.getObject(5) = " + rs.getObject(5));
            System.out.println("rs.getObject(\"sirket\") = " + rs.getObject("sirket"));
        }

        connection.close();
        statement.close();
        rs.close();
    }
}
