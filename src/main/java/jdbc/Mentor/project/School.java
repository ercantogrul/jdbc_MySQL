package jdbc.Mentor.project;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class School {
    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_sql", "root", "Et.1641"); //database baglantisi icin
        Statement statement = connection.createStatement();//sql sorgu(query) calistirmak (execute etmek) icin

        String queryLessonCreate = "create table lesson ( id INT PRIMARY KEY AUTO_INCREMENT,lesson_name varchar(15))";
       // statement.execute(queryLessonCreate);  // simsek

//        List<Lesson> dersler = new ArrayList<Lesson>();
//        dersler.add(new Lesson("java",1));
//        dersler.add(new Lesson("selenium",2));
//        dersler.add(new Lesson("jdbc",3));
//
//        PreparedStatement lesson = connection.prepareStatement("insert into lesson (id,lesson_name) values (?,?)");
//
//
//
//        for (Lesson each : dersler) {
//            lesson.setInt(1,each.getLesson_id());
//            lesson.setString(2,each.getLesson_name());
//            lesson.addBatch();  // mavilik tamamini secmek icin
//        }
//        lesson.executeBatch(); // secilenlerin tamamini isleme koymak icin


//        String queryStudent= "create table student ( id INT PRIMARY KEY AUTO_INCREMENT,name varchar(15),lesson_id int , FOREIGN KEY (lesson_id) REFERENCES lesson(id) )";
//        statement.execute(queryStudent); // şimsek

//        List<Student> ogrenciler=new ArrayList<Student>();
//        ogrenciler.add(new Student("ali", 1));
//        ogrenciler.add(new Student("veli", 2));
//        ogrenciler.add(new Student("ayse", 3));
//
//        PreparedStatement student = connection.prepareStatement("insert into student (id,name,lesson_id) values (?,?,?)");
//        for (Student each:ogrenciler) {
//
//            student.setInt(1, each.getId());
//            student.setString(2,each.getName());
//            student.setInt(3,each.getLesson_id());
//            student.addBatch(); // mavilik tamamını seçmek için
//
//        }
//        student.executeBatch();



        // *****ayse isimli ogrencinin aldigi ders adini yazdiriniz?
        String query1 = "select name,lesson_id,(select lesson_name from lesson where lesson.id=student.lesson_id) as ders_name from student where name='ayse'";
        ResultSet rs = statement.executeQuery(query1);
        while (rs.next()) {
            System.out.printf("%-20.20s- %-6d %-20.10s\n", rs.getString(1), rs.getInt(2), rs.getString(3));
        }

        // *****student tablosuna surname sutunu ekleyiniz
//        String alterQuery = "alter table student add column surname varchar(20)";
//        statement.execute(alterQuery);
        //System.out.println("Markalar tablosuna surname eklendi  "+statement.execute("alter table student add column surname varchar(20)")); // veya bu sekilde

        // *****Her öğrencinin ismini numarası ile birleştirerek ve yanında aldığı  dersi ismini yazdırın
        String query = "select concat(student.id,'- ',student.name),(select lesson_name from lesson where lesson.id=student.lesson_id) as ders_name from student";
        rs = statement.executeQuery(query);
        while (rs.next()) {
            System.out.printf("%-20.20s- %-20.20s\n", rs.getString(1), rs.getString(2));
        }


        String query2 ="select concat(lesson_id,' ',name) as ogrenci_Numarasi, lesson_name as dersler\n" +
                "from student\n" +
                "left join lesson\n" +
                "on student.id=lesson.id";
        rs = statement.executeQuery(query2);
        while (rs.next()) {
            System.out.print(rs.getObject(1) + "   ");
            System.out.println(rs.getObject(2));

        }


    }




}
