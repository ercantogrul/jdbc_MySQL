package jdbc.Mentor.Family;



import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbaseHesaplar {
    Connection connection = null;// sql query db'ye gondermek icin DB baglantisi yapacak olan obje
    Statement statement=null;// sql verilerinin result'unun return edecek obje
    PreparedStatement pStatement=null; // parametreli statement obje create edildi-> guven ve hiz icin var.

    private void dBaseConnection(){ //SQL connection
        String url="jdbc:mysql://localhost:3306/"; // mysql baglanti adresi
        String username="root"; // mysql baglanti adresi
        String password="Et.1641";// mysql icin baglanti password
        try{
            connection = DriverManager.getConnection(url,username,password); //mysql'e baglanacak obje parametreleri girilerek
        }
        catch (SQLException e) {
            System.out.println(e.toString());
        }

    }
    private void createDatabase(){ // sema (DB) ve table olusturan metod -> CREATE DB CREATE TABLE
        dBaseConnection();
        try {
            String query = "create database if not exists Budget"; // mysql'de db create icin sql query tanimlandi.
            statement=connection.createStatement();
            statement.executeUpdate(query);
            budget(); //DB'ye baglanan method call edildi.
            query="create table if not exists hesapBilgileri"+ //ilgili DB'e table create edecek query tanimlandi
                    "(id int primary key auto_increment,"+
                    "ay varchar(10) unique,"+
                    "aciklama  varchar(50),"+
                    "gelir  int,"+
                    "gider  int)";
            statement.executeUpdate(query); // bu statement.executeUpdate ile tablo olusturuldu
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            dBaseClosed(); // guvenlik icin DB kapatildi.
        }
    }
    public List<Hesaplar> listData(){
        List<Hesaplar> liste = new ArrayList<>(); //select'ten gelecek recordlarin atanacagi liste
        createDatabase(); //DB yoksa create edecek varsa etmeyecek
        dBaseConnection();
        budget();
        String query = "select * from hesapBilgileri"; // ilgili tablodan verileri getirecek query tanimlandi
        try {
            ResultSet resultSet =statement.executeQuery(query); // ilgili tablodaki verileri listelemek icin Resultset'e atandi.
            while (resultSet.next()) { // resultSet icine aldigimiz verileri liste icine almak icin asagidaki islemleri yaptik.
                Hesaplar k = new Hesaplar();
                k.setId(resultSet.getInt(1)); // iterator ile DB'den gelen result recordlar ilgili inst. variable'a atandi
                k.setAy(resultSet.getString(2)); // iterator ile DB'den gelen result recordlar ilgili inst. variable'a atandi
                k.setAciklama(resultSet.getString(3)); // iterator ile DB'den gelen result recordlar ilgili inst. variable'a atandi
                k.setGelir(resultSet.getInt(4)); // iterator ile DB'den gelen result recordlar ilgili inst. variable'a atandi
                k.setGider(resultSet.getInt(5)); // iterator ile DB'den gelen result recordlar ilgili inst. variable'a atandi
                liste.add(k); // iterator ile gelen recordlar listeye eklendi.
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            dBaseClosed();
        }
        return liste;
    }
    public void addData(Hesaplar hesap){
        createDatabase();
        dBaseConnection();
        budget();
        String query = "insert into hesapBilgileri(ay,aciklama,gelir,gider) values (?,?,?,?)";
        try {
            pStatement=connection.prepareStatement(query); // egere girilecek veriler belli degil veya kullanicidan alinacaksa pstatement kullanilir.
            pStatement.setString(1, hesap.getAy());
            pStatement.setString(2, hesap.getAciklama());
            pStatement.setInt(3, hesap.getGelir());
            pStatement.setInt(4, hesap.getGider());
            pStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            dBaseClosed();
        }
    }
    public void updateData (Hesaplar hesap){
        createDatabase();
        dBaseConnection();
        budget();
        String query = "update hesapBilgileri set ay=?,aciklama=?,gelir=?,gider=? where id=?";
        try {
            pStatement=connection.prepareStatement(query);
            pStatement.setString(1, hesap.getAy());
            pStatement.setString(2, hesap.getAciklama());
            pStatement.setInt(3, hesap.getGelir());
            pStatement.setInt(4, hesap.getGider());
            pStatement.setInt(5, hesap.getId());
            pStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            dBaseClosed();
        }
    }
    public void deleteData(int id){
        createDatabase();
        dBaseConnection();
        budget();
        String query = "delete from hesapBilgileri where id=?";
        try {
            pStatement=connection.prepareStatement(query);
            pStatement.setInt(1,id);
            pStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            dBaseClosed();
        }
    }

    private void dBaseClosed() {
        try {
            if(connection!=null) {//baglati acik mi kontrolu
                connection.close();//baglanti kapatildi
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }

    private void budget() {
        String query = "use budget"; //mysql baglantisi ile ilgili DB'ye baglanmasi icin sql query tanimlandi.
        try{
            statement=connection.createStatement(); //mysql baglantisindaki ilgili db icin statement atandi
            statement.executeUpdate(query); // mysql'deki DB'ye baglanmasi icin sql query execute edildi.(simsek)
        }catch (SQLException e){
            System.out.println(e.toString());
        }
    }
}
