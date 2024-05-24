package jdbc.Mentor;
import javax.xml.crypto.dsig.spec.XSLTTransformParameterSpec;
import java.sql.*;
import java.sql.SQLException;

public class day1_ {
    public static void main(String[] args) throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mentor_jdbc", "root", "Et.1641");//catıya kurulan anten tv'ye(DB) ya baglandi
        // Step2
        Statement statement = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);// sql query(sorgu) execute edecek (şimşeklemek için tv kumanda) tanımlandı
        System.out.println("   ****************   task01   ****************   ");
        // Task01-> mentor database daki books table'daki record'ları listeleyen code create ediniz
                //String alter2Query=" alter table books_list rename to books ";
                //System.out.println("statement.execute(alterQuery) = " + statement.executeUpdate(alter2Query));

        String query = "select * from books";
        ResultSet rs = statement.executeQuery(query);// tanımlana sql query execute edilerek(şimşeklenerek) output rs'ye atandı
        while (rs.next()) { // table' den gelen record'lari tekrara ala loop tanimlandi
            System.out.printf("%-6d%-40s%-25s%-20s%-10d\n", rs.getInt(1), rs.getString(2),
                    rs.getString(3), rs.getString(4), rs.getInt(5));
        }
        System.out.println("   *** ******* ***   ");
        System.out.println("birinci column u yaziyoruz ");
        rs.absolute(0);  // cursor'u 0 a atadik
        while (rs.next()) {
            System.out.print("id: " + rs.getInt(1) + "\n");
        }

        System.out.println("   **************** task2 *****************   ");
        // Task02-> mentor table'daki 1950 sonrası  record'ları yıllara göre  listeleyen code create ediniz
        rs.absolute(0);  // cursor'u 0 a atadik
        rs = statement.executeQuery(" select * from books where year_published > 1950;");
        while (rs.next()) {
            System.out.printf("%-6d%-30.20s%-30.20s%-20.15s%-6d\n",
                    rs.getInt(1), rs.getString(2),
                    rs.getString(3), rs.getString(4), rs.getInt(5));
        }

        // ***************calistiginiz result setteki sutunlar hakkinda bilgi verir****************
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();  // bulundugunuz resulttaki sutun sayisini verir

        for (int i = 1; i <= columnCount; i++){
            String columnName = metaData.getColumnName(i);// sutunun ismini verir
            System.out.println("Column Name: " + columnName);
        }

       /* Column Name: id
        Column Name: title
        Column Name: author
        Column Name: genre
        Column Name: year_published*/

        System.out.println("   **************** task3 *****************   ");
        // Task03-> mentor table'daki id si 7 olan kitabın adını yazdırın listeleyen code create ediniz
        rs.absolute(0);  // cursor'u 0 a atadik
        rs = statement.executeQuery(" select title from books where id= 7;");
        while (rs.next()) {
            System.out.printf("%-30.20s\n",
                   rs.getString("title"));
                 //  rs.getObject("title");
        }

        System.out.println("   **************** task4 *****************   ");
        // Task04-> books  table'daki notu Classic ve Drama türünde olan kitapların yazarının record'ları listeleyen code create ediniz.
        rs.absolute(0);  // cursor'u 0 a atadik
        rs = statement.executeQuery("select * from books where genre in ('Classic','Drama')");
        while (rs.next()) {
            System.out.printf("%-3d%-30.20s%-20.8s%-20.8s%-6d\n", rs.getInt(1), rs.getString(2),
                    rs.getString(3), rs.getString(4), rs.getInt(5));
        }


        System.out.println("   '********************* task5 **************   ");
        // Task05-> ilk satırdaki ilk obj print eden code create ediniz.Cursor'ın konumunu yazdırın
        rs.first();
        System.out.println(rs.getObject(1));


        System.out.println("   **************** task6 *****************   ");
        // Task06->ilk sutunun 5. değerini print eden code create ediniz->
        rs = statement.executeQuery("select * from books");
        rs.absolute(5);
        System.out.println("ilk sutunun 5. değeri  : " + rs.getObject(1));


        System.out.println("   **************** task7 *****************   ");
        // Task07-> books tablosunun books_list   olarak degistiriniz

        //System.out.println("books tablosunun adi books_list yapildi  "+statement.execute("alter table books rename to books_list"));



        System.out.println("   **************** task8 *****************   ");
        // Task08 -> yeni tabloya yeni bir kitap ekleyiniz
       // statement.executeUpdate("insert into books values (9, 'Sinekli Bakkal', 'Noname', 'Hikaye' , 1957)");

        rs.absolute(0);  // cursor'u 0 a atadik
        rs = statement.executeQuery("select * from books");
        while (rs.next()) {
            System.out.printf("%-3d%-30.20s%-20.8s%-20.8s%-6d\n", rs.getInt(1), rs.getString(2),
                    rs.getString(3), rs.getString(4), rs.getInt(5));
        }


    }
}
