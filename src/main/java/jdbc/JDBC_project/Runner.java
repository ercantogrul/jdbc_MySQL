package jdbc.JDBC_project;

import java.util.Scanner;

public class Runner {
    static Kayit kayitObj;
    static Utilities utilities = new Utilities();
    static Scanner input = new Scanner(System.in);
    static Scanner inputLN = new Scanner(System.in);

    public static void main(String[] args) throws InterruptedException {
       // System.out.println(utilities.list_id());

        menu();
    }

    private static void menu() throws InterruptedException {

        System.out.println("\n-------------------------");
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
                menu();
        }
    }

    private static void updateRecord() throws InterruptedException {
        System.out.println("------------------\nUpdate edilecek Kayit ID : ");
        kayitObj = new Kayit();
        int updateId = inputLN.nextInt();
        boolean flag = false;
        for (int i = 0; i < utilities.listData().size(); i++) {
            if (updateId == utilities.listData().get(i).getId()) {
                System.out.printf("%5d%20s%15s%n", utilities.listData().get(i).getId(), utilities.listData().get(i).getIsim(), utilities.listData().get(i).getTel());
                System.out.print("Kayıt yenilemeyi onaylıyor musunuz (E:evet) :");
                String onay = input.next();
                if (onay.equalsIgnoreCase("e")) {
                    System.out.println("güncellemek istemediğiniz alana X giriniz");
                    System.out.print("yeni isim : ");
                    String yeniIsim = input.next();
                    System.out.print("yeni tel : ");
                    String yeniTel = input.next();
                    if (yeniIsim.equalsIgnoreCase("x")) {
                        yeniIsim = utilities.listData().get(i).getIsim();
                    }
                    kayitObj.setIsim(yeniIsim);
                    if (yeniTel.equalsIgnoreCase("x")) {
                        yeniTel = utilities.listData().get(i).getTel();
                    }
                    kayitObj.setTel(yeniTel);
                    kayitObj.setId(updateId);
                    utilities.updateData(kayitObj);
                    System.out.println("Guncelleme basarili");
                    flag=true;
                    break;
                }else {
                    System.out.println("Update islemi iptal edildi");
                    flag=true;
                    break;
                }
            }
        }
        if(!flag){
            System.out.println("Id bulunamadi");
        }
        menu();
    }

    private static void deleteRecord() throws InterruptedException {
        System.out.println("-------------------------\nsilinecek KAYIT ID : ");

        int silinecekId = input.nextInt();
        boolean flag=false;
        for (int i = 0; i < utilities.listData().size(); i++) {
            if (silinecekId == utilities.listData().get(i).getId()) {
                System.out.printf("%5d%20s%15s%n", utilities.listData().get(i).getId(), utilities.listData().get(i).getIsim(), utilities.listData().get(i).getTel());
                System.out.print("kayıt silmeyi onaylıyor musunuz (E:evet) ");
                String onay = input.next();
                if (onay.equalsIgnoreCase("E")) {
                    utilities.deleteData(silinecekId);
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
        menu();
    }

    private static void listRecords() throws InterruptedException {
        System.out.printf("%4S%20S%17S\n", "id", "isim soyisim", "telefon no");
        System.out.printf("%4S%20S%17S\n", "--", "------------", "------------");
        for (int i = 0; i < utilities.listData().size(); i++) {
            System.out.printf("%4d%20s%17s\n", utilities.listData().get(i).getId(),
                    utilities.listData().get(i).getIsim(), utilities.listData().get(i).getTel());
        }
        if (utilities.listData().isEmpty()) {
            System.out.printf("Listelenecek kayit bulunamadi");
        }
        Thread.sleep(2000);
        menu();
    }

    private static void addNewRecord() throws InterruptedException {
        System.out.println("---------------------\nRecord Ekleme : ");
        kayitObj = new Kayit();
        System.out.println("isim : ");
        String isim = inputLN.nextLine();
        System.out.println("tel : ");
        String tel = input.next();
        kayitObj.setIsim(isim);
        kayitObj.setTel(tel);
        utilities.addData(kayitObj);
        menu();
    }


}