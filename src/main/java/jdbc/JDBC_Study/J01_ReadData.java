package jdbc.JDBC_Study;

import java.sql.*;

public class J01_ReadData {
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/jdbc_sql";  // Mysql de kullandigimiz database uzantisi adresi
        String username = "root";
        String password = "Et.1641";
        // Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaCAN", "root", "12345678"); //database baglantisi icin

        Connection connection = DriverManager.getConnection(url, username, password); //database baglantisi icin
        Statement statement = connection.createStatement();//sql sorgu(query) calistirmak (execute etmek) icin

        System.out.println("   ***   task01   ***   ");
        // Task01-> talebeler table'daki record'ları listeleyen code create ediniz
        String query = "select * from talebeler";
       // ResultSet rs = statement.executeQuery(query);

        ResultSet rs = statement.executeQuery("select * from talebeler");
        while (rs.next()) {
            System.out.printf("%-6d%-20s%-10s%-6d\n", rs.getInt(1),rs.getString(2), rs.getString(3), rs.getInt(4));

        }

        System.out.println("   ***   task02   ***   ");
        // Task02-> talebeler table'daki notları 90 dan yuksek olan record'ları listeleyen code create ediniz
        rs = statement.executeQuery("select * from talebeler where yazili_notu>90");
        while (rs.next()) {
            System.out.printf("%-6d%-20s%-10s%-6d\n", rs.getInt(1),rs.getString(2), rs.getString(3), rs.getInt(4));
        }



        System.out.println("   ***   task03   ***   ");
        // Task03-> talebeler table'daki id değeri 124 olan record'ları listeleyen code create ediniz
        rs = statement.executeQuery(" select * from talebeler where id=124;");
        while (rs.next()){
            System.out.printf("%-6d%-20s%-10s%-6d\n", rs.getInt(1),rs.getString(2),
                    rs.getString(3),rs.getInt(4)); //soldan bosluk olmasi icin yuzde attik.
        }

        System.out.println("   ***   task04   ***   ");
        // Task04-> talebeler table'daki notu 70 ile 90 arasında  olan record'ları listeleyen code create ediniz.
        rs = statement.executeQuery(" select * from talebeler where yazili_notu>70 and yazili_notu<90;");
        while (rs.next()){
            System.out.printf("%-6d%-20s%-10s%-6d\n", rs.getInt(1),rs.getString(2),
                    rs.getString(3),rs.getInt(4)); //soldan bosluk olmasi icin yuzde attik.
        }

        System.out.println("   ***   task05   ***   ");
        // Task05-> talebeler table'daki ismin 3. harfi l   olan record'ları listeleyen code create ediniz.
        rs = statement.executeQuery(" select * from talebeler where isim like '__l%';");
        while (rs.next()){
            System.out.printf("%-6d%-20s%-10s%-6d\n", rs.getInt(1),rs.getString(2),
                    rs.getString(3),rs.getInt(4)); //soldan bosluk olmasi icin yuzde attik.
        }



        connection.close();
        statement.close();
    }












}
