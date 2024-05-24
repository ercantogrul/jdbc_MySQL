package jdbc.JDBC_project;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Utilities {//DB action alan JDBC Meth class
    Connection connection = null;//sql query db'ye gondermek icin DB baglantisi yapacak olan obj
    Statement statement = null;//sql verilerinin resultunu return edecek obj
    PreparedStatement pStatement = null;//p'li statement obje create edildi->guven ve hiz icin var

    private void dBaseConnect() {//SQL connection
        String url = "jdbc:mysql://localhost:3306/";//mysql baglanti adresi
        String username = "root";//mysql baglanti username
        String password = "Et.1641";//mysql icin baglanti password
        try {
            connection = DriverManager.getConnection(url, username, password);//mysql'e baglacak obje parametreleri girilerek baglanti yapildi
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }

    private void createDBase(){//shema(DB) ve table olusturan meth->CREATE DB CREATE TABLE
        dBaseConnect();
        try {
            String query="Create database if not exists telefon_rehberi";//mysql'de db create icin sql query tanimlandi
            statement=connection.createStatement();
            statement.executeUpdate(query);
            useTelRehberi();//db'ye baglanan meth call edildi
            query="create table if not exists tel_nolar"+//ilgili db'ye table create edecek query tanimlandi
                    "(id int primary key auto_increment," +
                    "isim varchar(50)," +
                    "tel varchar(20))";
            statement.executeUpdate(query);//table cretae sql query calistirildi
        } catch (SQLException e) {
            System.out.println(e.toString());
        }finally {
            dBaseClosed();//guvenlik icin db kapatildi
        }
    }

    //connection->statement->execute
    private void useTelRehberi() {
        String query = "use telefon_rehberi";//mysql baglantisi ile ilgili db'ye baglanmasi icin sql query tanimlandi
        try {
            statement = connection.createStatement();//mysql baglantisindaki ilgili db icin statement atandi
            statement.executeUpdate(query);//mysql'deki db'ye baglanmasi icin sql query execute edildi(simsek)
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }

    public List<Kayit> listData(){
        List<Kayit> liste=new ArrayList<>();//select'ten gelen recorlarin atanacagi liste
        createDBase();//DB yoksa create edecek varsa etmeyecek
        dBaseConnect();
        useTelRehberi();
        String query="select * from tel_nolar";//ilgili tablodan verileri getirecek query tanimlandi
        try {
            ResultSet resultSet=statement.executeQuery(query);//ilgili tablodaki veriler listelenmek icin resulsete atandi
            while (resultSet.next()){
                Kayit k=new Kayit();
                k.setId(resultSet.getInt(1));//iterator ile db gelen resulr recordlar ilgili inst variableye atandi
                k.setIsim(resultSet.getString(2));//iterator ile db gelen resulr recordlar ilgili inst variableye atandi
                k.setTel(resultSet.getString(3));//iterator ile db gelen resulr recordlar ilgili inst variableye atandi
                liste.add(k);//eterator ile gelen recordlar liste eklendi
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            dBaseClosed();
        }
        return liste;
    }

    public ArrayList<Integer> list_id() {// table'daki tum column record'ları return eden meth. -> SELECT
        ArrayList<Integer> liste_id = new ArrayList<>();//select'tn gelen record'ları atanacagı boş liste
        createDBase();//DB yoksa create edecek varsa etmeyecek .listelenecek DB önce create edilmeli.
        // schema ve table oluşturan meth. -> CREATE TABLE, CREATE DATABASE meth call...
        dBaseConnect();// mysql connection-> mysql baglanan meth. call
        useTelRehberi();// mysql'de db(schema)'ye baglanan meth.(USE javacan) call...
        ResultSet resultSet = null;//*** sql query sonuçlarını return edecek obj. create edildi.
        String sql_query = "SELECT id FROM tel_nolar ;";// mysql'deki db'deki ilgili table ve column'a record listelemek (read) sql query tanımlandı
        try {
            resultSet = statement.executeQuery(sql_query);// mysql'deki db'deki ilgili table ve column'a record listelemek (read) sql query run edildi(şimşek)
            while (resultSet.next()) {//iterator loop ile datalar listelendi
                //kayıt class yeni bir obj
                liste_id.add(resultSet.getInt(1));
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        } finally {
            dBaseClosed();//mysql  bağlantısı kapatıldı
        }
        return liste_id;
    }




    public void addData(Kayit kayit){
        createDBase();
        dBaseConnect();
        useTelRehberi();
        String query="insert into tel_nolar(isim,tel) values(?,?)";
        try {
            pStatement=connection.prepareStatement(query);//eger girlecek veriler belli degil veya kullanicidan alinacaksa pstatement kullanilir
            pStatement.setString(1,kayit.getIsim());
            pStatement.setString(2, kayit.getTel());
            pStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            dBaseClosed();
        }
    }

    public void updateData(Kayit kayit){
        createDBase();
        dBaseConnect();
        useTelRehberi();
        String query="update tel_nolar set isim=?,tel=? where id=?";
        try {
            pStatement= connection.prepareStatement(query);
            pStatement.setString(1, kayit.getIsim());
            pStatement.setString(2, kayit.getTel());
            pStatement.setInt(3,kayit.getId());
            pStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            dBaseClosed();
        }
    }
    public void deleteData(int id){
        createDBase();
        dBaseConnect();
        useTelRehberi();
        String query="delete from tel_nolar where id=?";
        try {
            pStatement= connection.prepareStatement(query);
            pStatement.setInt(1,id);
            pStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            dBaseClosed();
        }
    }

    private void dBaseClosed() {//db baglantisini kapatan meth
        try {
            if (connection != null) {//baglati acik mi kontrolu
                connection.close();//baglanti kapatildi
            }
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }



}
