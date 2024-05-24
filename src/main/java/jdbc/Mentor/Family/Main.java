package jdbc.Mentor.Family;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    static Hesaplar hesapObj;
    static DbaseHesaplar hesaplar =new DbaseHesaplar();
    static Scanner input=new Scanner(System.in);
    static Scanner inputLN=new Scanner(System.in);
    static List<String> aylar = new ArrayList<>(Arrays.asList("ocak","subat","martr","nisan","mayis","haziran","temmuz","agustos","eylul","ekim","kasim","aralik"));

    public static void main(String[] args) throws InterruptedException {
        menuHesaplar();
    }

    private static void menuHesaplar() throws InterruptedException {
        System.out.println("-------------------------");
        System.out.println("1 - List Records\n2 - Delete Records\n3 - UpDate Recod\n4 - Add new Record\nX - Exit\n" +
                "-------------------------\nSeçiminiz : ");
        String secim = input.next().toUpperCase();
        switch (secim) {
            case "1":
                listRecords();
                break;
            case "2":
                deleteRecord();
                break;
            case "3":
                updateRecord();
                break;
            case "4":
                addNewRecord();
                break;
            case "X":
                System.out.println("sistem çıkışınız yapılmıştır  ... ");
                break;
            default:
                System.out.println("hatalı giriş ");
                menuHesaplar();
        }

    }
    private static void updateRecord() throws InterruptedException {
        System.out.println("----------------------------------------------------------------\nUpdate edilecek Kayit ID : ");
        hesapObj=new Hesaplar();
        int updateId= inputLN.nextInt();
        boolean flag = false;
        for (int i = 0; i < hesaplar.listData().size(); i++) {
            if(updateId== hesaplar.listData().get(i).getId()){
                System.out.printf("%5d%20s%30s%10d%10d\n", hesaplar.listData().get(i).getId(), hesaplar.listData().get(i).getAy(), hesaplar.listData().get(i).getAciklama(),
                        hesaplar.listData().get(i).getGelir(),hesaplar.listData().get(i).getGider());
                System.out.print("kayıt yenilemeyi onaylıyor musunuz (E:evet) :");
                String onay = input.next();
                if (onay.equalsIgnoreCase("e")){
                    System.out.println("güncellemek istemediğiniz alana X giriniz");
                    System.out.print("yeni ay : ");
                    String yeniAy = inputLN.next();
                    System.out.print("yeni aciklama : ");
                    String yeniAciklama = input.next();
                    System.out.print("yeni gelir : ");
                    String yeniGelir = input.next();
                    System.out.print("yeni gider : ");
                    String yeniGider = input.next();

                    if (yeniAy.equalsIgnoreCase("x")) {
                        yeniAy = hesaplar.listData().get(i).getAy();
                    }
                    hesapObj.setAy(yeniAy);

                    if (yeniAciklama.equalsIgnoreCase("x")) {
                        yeniAciklama = hesaplar.listData().get(i).getAciklama();
                    }
                    hesapObj.setAciklama(yeniAciklama);

                    if (yeniGelir.equalsIgnoreCase("x")) {
                        yeniGelir= String.valueOf((hesaplar.listData().get(i).getGelir()));
                        hesapObj.setGelir(Integer.parseInt(yeniGelir));
                       // int intGelir = Integer.parseInt(yeniGelir);
                      //  intGelir = hesaplar.listData().get(i).getGelir();
                      //  hesapObj.setGelir(intGelir);
                    }else {
                        hesapObj.setGelir(Integer.parseInt(yeniGelir));
                    }

                    if (yeniGider.equalsIgnoreCase("x")) {
                        yeniGider= String.valueOf((hesaplar.listData().get(i).getGider()));
                        hesapObj.setGider(Integer.parseInt(yeniGider));

//                        int intGider = Integer.parseInt(yeniGider);
//                        intGider = hesaplar.listData().get(i).getGider();
//                        hesapObj.setGelir(intGider);
                    }else {
                        hesapObj.setGider(Integer.parseInt(yeniGider));
                    }

                    hesapObj.setId(updateId);
                    hesaplar.updateData(hesapObj);
                    System.out.println("Guncelleme basarili");
                    flag = true;
                    break;
                }else{
                    System.out.println("Update islemi iptal edildi");
                    flag = true;
                }
            }
        }
        if (!flag){
            System.out.println("Id bulunamadi...");
        }
        menuHesaplar();
    }

    private static void listRecords () throws InterruptedException {
        System.out.printf("%4s%20S%20S%10s%10s\n", "id", "     Ay     ", "    Aciklama    ", "   Gelir  ", "    Gider    ");
        System.out.printf("%4s%20S%20S%10s%10s\n", "--", "------------", "-----------------","------------","------------");
        for (int i = 0; i < hesaplar.listData().size(); i++) {
            System.out.printf("%4d%20S%20S%10d%10d\n", hesaplar.listData().get(i).getId(),
                    hesaplar.listData().get(i).getAy(), hesaplar.listData().get(i).getAciklama(),hesaplar.listData().get(i).getGelir(),hesaplar.listData().get(i).getGider());
        }
        if(hesaplar.listData().isEmpty()) {
            System.out.println("Listelenecek kayit bulunamadi.");
        }
        Thread.sleep(2000);
        menuHesaplar();
    }

    private static void addNewRecord() throws InterruptedException {
        System.out.println("----------------------------------------------------------------\nRecord Ekleme : ");
        hesapObj=new Hesaplar();
        System.out.println("Ay : ");
        String ay = input.next();
        hesapObj.setAy(ay);
        System.out.println("Aciklama : ");
        String aciklama =inputLN.nextLine();
        hesapObj.setAciklama(aciklama);
        System.out.println("Gelir : ");
        int gelir = input.nextInt();
        hesapObj.setGelir(gelir);
        System.out.println("Gider : ");
        int gider = input.nextInt();
        hesapObj.setGider(gider);
        try{
            hesaplar.addData(hesapObj);
        } catch (Exception e){
        System.out.println("Bu aya ait bilgiler mevcut. Baska bir ay giriniz");
        }
        menuHesaplar();

    }
    private static void deleteRecord() throws InterruptedException {
        System.out.println("-------------------------\nsilinecek KAYIT ID : ");

        int silinecekId = input.nextInt();
        boolean flag=false;
        for (int i = 0; i < hesaplar.listData().size(); i++) {
            if (silinecekId == hesaplar.listData().get(i).getId()) {
                System.out.printf("%4d%20S%20S%10d%10d\n", hesaplar.listData().get(i).getId(), hesaplar.listData().get(i).getAy(), hesaplar.listData().get(i).getAciklama()
                ,hesaplar.listData().get(i).getGelir(),hesaplar.listData().get(i).getGider());
                System.out.print("kayıt silmeyi onaylıyor musunuz (E:evet) ");
                String onay = input.next();
                if (onay.equalsIgnoreCase("E")) {
                    hesaplar.deleteData(silinecekId);
                    System.out.println("silme işlemi başarılı şekilde yapıldı :) ");
                    flag=true;
                    break;
                } else {
                    System.out.println("silme işlemi iptal edildi");
                    flag=true;
                    break;
                }
            }
        }
        if (!flag){
            System.out.println("silinecek ID bulunamadı");
        }
        menuHesaplar();
    }
}
