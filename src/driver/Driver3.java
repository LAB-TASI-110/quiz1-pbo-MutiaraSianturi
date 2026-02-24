// Nama : Mutiara Y.H. Sianturi
// NIM  : 12S24045

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Driver3 {

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        LaundrySystem system = new LaundrySystem();

        System.out.println("=== SISTEM LAUNDRY DEL ===");

        // INPUT DATA MAHASISWA
        System.out.print("Masukkan Nama Mahasiswa: ");
        String nama = input.nextLine();

        System.out.print("Masukkan NIM: ");
        String nim = input.nextLine();

        Mahasiswa mhs = new Mahasiswa("U01", nama, "email@del.ac.id", nim);

        // INPUT TRANSAKSI
        System.out.print("Masukkan ID Transaksi: ");
        String idTransaksi = input.nextLine();

        TransaksiLaundry transaksi = new TransaksiLaundry(idTransaksi, mhs);

        // INPUT ITEM
        System.out.print("Masukkan Nama Item (contoh: Kaos): ");
        String namaItem = input.nextLine();

        System.out.print("Masukkan Jumlah: ");
        int jumlah = input.nextInt();

        System.out.print("Masukkan Harga per Item: ");
        double harga = input.nextDouble();

        ItemLaundry item = new ItemLaundry(namaItem, jumlah, harga);
        transaksi.tambahItem(item);

        system.tambahTransaksi(transaksi);

        System.out.println("\nTotal Bayar: Rp " + transaksi.hitungTotal());

        // UPDATE STATUS
        input.nextLine(); // clear buffer
        System.out.print("\nUpdate Status (DIPROSES/SELESAI/DIAMBIL): ");
        String statusBaru = input.nextLine();

        system.updateStatus(idTransaksi, statusBaru);

        input.close();
    }
}


/* ================= USER ================= */
class User {
    protected String id;
    protected String nama;
    protected String email;

    public User(String id, String nama, String email) {
        this.id = id;
        this.nama = nama;
        this.email = email;
    }

    public String getNama() {
        return nama;
    }
}


/* ================= MAHASISWA ================= */
class Mahasiswa extends User {

    private String nim;

    public Mahasiswa(String id, String nama, String email, String nim) {
        super(id, nama, email);
        this.nim = nim;
    }

    public String getNim() {
        return nim;
    }
}


/* ================= ITEM LAUNDRY ================= */
class ItemLaundry {

    private String namaItem;
    private int jumlah;
    private double harga;

    public ItemLaundry(String namaItem, int jumlah, double harga) {
        this.namaItem = namaItem;
        this.jumlah = jumlah;
        this.harga = harga;
    }

    public double hitungSubtotal() {
        return jumlah * harga;
    }
}


/* ================= TRANSAKSI ================= */
class TransaksiLaundry {

    private String idTransaksi;
    private Mahasiswa mahasiswa;
    private List<ItemLaundry> daftarItem;
    private String status;

    public TransaksiLaundry(String idTransaksi, Mahasiswa mahasiswa) {
        this.idTransaksi = idTransaksi;
        this.mahasiswa = mahasiswa;
        this.daftarItem = new ArrayList<>();
        this.status = "MENUNGGU";
    }

    public void tambahItem(ItemLaundry item) {
        daftarItem.add(item);
    }

    public double hitungTotal() {
        double total = 0;
        for (ItemLaundry item : daftarItem) {
            total += item.hitungSubtotal();
        }
        return total;
    }

    public void ubahStatus(String statusBaru) {
        this.status = statusBaru;
    }

    public String getIdTransaksi() {
        return idTransaksi;
    }

    public Mahasiswa getMahasiswa() {
        return mahasiswa;
    }
}


/* ================= NOTIFICATION ================= */
class Notification {

    private String pesan;

    public Notification(String pesan) {
        this.pesan = pesan;
    }

    public void kirim() {
        System.out.println("NOTIFIKASI: " + pesan);
    }
}


/* ================= MAIN CONTROLLER ================= */
class LaundrySystem {

    private List<TransaksiLaundry> daftarTransaksi;

    public LaundrySystem() {
        daftarTransaksi = new ArrayList<>();
    }

    public void tambahTransaksi(TransaksiLaundry transaksi) {
        daftarTransaksi.add(transaksi);
    }

    public TransaksiLaundry cariTransaksi(String id) {
        for (TransaksiLaundry t : daftarTransaksi) {
            if (t.getIdTransaksi().equals(id)) {
                return t;
            }
        }
        return null;
    }

    public void updateStatus(String id, String statusBaru) {
        TransaksiLaundry transaksi = cariTransaksi(id);

        if (transaksi != null) {
            transaksi.ubahStatus(statusBaru);

            Notification notif = new Notification(
                "Laundry milik " + transaksi.getMahasiswa().getNama() +
                " sekarang berstatus: " + statusBaru
            );

            notif.kirim();
        }
    }
}
