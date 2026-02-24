// Nama : Mutiara Y.H. Sianturi
// NIM  : 12S24045


import java.util.Scanner;

public class Driver2 {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.print("Masukkan jumlah data (N): ");
        int N = input.nextInt();

        int[] nilai = new int[N];

        System.out.println("Masukkan deret nilai:");
        for (int i = 0; i < N; i++) {
            nilai[i] = input.nextInt();
        }

        System.out.print("Masukkan jumlah kelompok: ");
        int jumlahKelompok = input.nextInt();

        System.out.print("Masukkan kode kelompok yang ingin dijumlahkan: ");
        int kodeKelompok = input.nextInt();

        int total = 0;

        for (int i = 0; i < N; i++) {
            if (i % jumlahKelompok == kodeKelompok - 1) {
                total += nilai[i];
            }
        }

        System.out.println("Total nilai kelompok " + kodeKelompok + " adalah: " + total);

        input.close();
    }
}
