// Nama : Mutiara Y.H. Sianturi
// NIM  : 12S24045


import java.util.*;

// =======================
// CLASS MENU
// =======================
class Menu {
    String kode;
    String nama;
    int harga;

    public Menu(String kode, String nama, int harga) {
        this.kode = kode;
        this.nama = nama;
        this.harga = harga;
    }
}

// =======================
// CLASS ORDER ITEM
// =======================
class OrderItem {
    Menu menu;
    int porsiButet;

    public OrderItem(Menu menu, int porsiButet) {
        this.menu = menu;
        this.porsiButet = porsiButet;
    }

    // Total porsi = Butet (1x) + Ucok (2x)
    public int getTotalPorsi() {
        return porsiButet * 3;
    }

    public int getTotalHarga() {
        return getTotalPorsi() * menu.harga;
    }
}

// =======================
// CLASS DRIVER (UTAMA)
// =======================
public class Driver1 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Daftar menu
        List<Menu> daftarMenu = new ArrayList<>();
        daftarMenu.add(new Menu("NGS", "Nasi Goreng Spesial", 15000));
        daftarMenu.add(new Menu("AP", "Ayam Penyet", 12000));
        daftarMenu.add(new Menu("GG", "Gado-Gado", 10000));

        List<OrderItem> orders = new ArrayList<>();

        // Input sampai END
        while (true) {

            String kode = sc.nextLine();

            if (kode.equalsIgnoreCase("END")) {
                break;
            }

            int porsiButet = sc.nextInt();
            sc.nextLine();

            for (Menu m : daftarMenu) {
                if (m.kode.equalsIgnoreCase(kode)) {
                    orders.add(new OrderItem(m, porsiButet));
                }
            }
        }

        int total = 0;

        System.out.println("===== STRUK PEMBAYARAN =====");

        for (OrderItem item : orders) {

            int totalItem = item.getTotalHarga();
            total += totalItem;

            System.out.println(
                item.menu.nama +
                " | Total Porsi: " + item.getTotalPorsi() +
                " | Total Harga: " + totalItem
            );
        }

        // Perhitungan Diskon
        double diskon = 0;

        if (total >= 500000) diskon = 0.25;
        else if (total >= 400000) diskon = 0.20;
        else if (total >= 300000) diskon = 0.15;
        else if (total >= 200000) diskon = 0.10;
        else if (total >= 100000) diskon = 0.05;

        int potongan = (int)(total * diskon);
        int totalBayar = total - potongan;

        System.out.println("============================");
        System.out.println("Total       : " + total);
        System.out.println("Diskon      : " + potongan);
        System.out.println("Total Bayar : " + totalBayar);
    }
}
