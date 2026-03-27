import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        ArrayList<Pelanggan> daftarPelanggan = new ArrayList<>();
        daftarPelanggan.add(new Pelanggan("3812345678", "Isa Maulana", "1234", 500000));
        daftarPelanggan.add(new Pelanggan("5612345678", "Naressuan dra",  "5678", 2000000));
        daftarPelanggan.add(new Pelanggan("7412345678", "Ahmad Sahroni",  "9012", 5000000));

        Scanner input = new Scanner(System.in);

        System.out.println("=== SWALAYAN TINY ===");

        while (true) {

            System.out.print("\nMasukkan nomor pelanggan (0 untuk keluar): ");
            String nomor = input.nextLine().trim();

            if (nomor.equals("0")) {
                System.out.println("Terima kasih telah menggunakan Swalayan Tiny!");
                break;
            }

            if (!nomor.matches("\\d{10}")) {
                System.out.println("Nomor pelanggan tidak valid! Harus 10 digit angka.");
                continue;
            }

            String prefix = nomor.substring(0, 2);
            if (!prefix.equals("38") && !prefix.equals("56") && !prefix.equals("74")) {
                System.out.println("Nomor pelanggan tidak valid! Prefix harus 38, 56, atau 74.");
                continue;
            }

            Pelanggan pelanggan = null;
            for (Pelanggan p : daftarPelanggan) {
                if (p.getNomorPelanggan().equals(nomor)) {
                    pelanggan = p;
                    break;
                }
            }

            if (pelanggan == null) {
                System.out.println("Nomor pelanggan tidak ditemukan!");
                continue;
            }

            if (pelanggan.isDiblokir()) {
                System.out.println("Akun diblokir! Hubungi customer service.");
                continue;
            }

            boolean pinBenar = false;
            while (!pelanggan.isDiblokir()) {
                System.out.print("Masukkan PIN: ");
                String pin = input.nextLine().trim();

                if (pelanggan.login(pin)) {
                    pinBenar = true;
                    break;
                }
            }

            if (!pinBenar) continue;

            System.out.println("\nSelamat datang, " + pelanggan.getNama() + "!");
            System.out.println("1. Top Up");
            System.out.println("2. Pembelian");
            System.out.print("Pilih menu: ");
            String pilihanStr = input.nextLine().trim();

            if (!pilihanStr.equals("1") && !pilihanStr.equals("2")) {
                System.out.println("Pilihan tidak valid!");
                continue;
            }
            int pilihan = Integer.parseInt(pilihanStr);

            System.out.print("Masukkan jumlah: Rp");
            double jumlah;
            try {
                jumlah = Double.parseDouble(input.nextLine().trim());
                if (jumlah <= 0) {
                    System.out.println("Jumlah harus lebih dari 0!");
                    continue;
                }
            } catch (NumberFormatException e) {
                System.out.println("Jumlah tidak valid!");
                continue;
            }

            if (pilihan == 1) {
                Transaksi.topUp(pelanggan, jumlah);
            } else {
                Transaksi.pembelian(pelanggan, jumlah);
            }
        }

        input.close();
    }
}