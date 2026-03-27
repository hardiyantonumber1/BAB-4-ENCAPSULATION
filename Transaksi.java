class Transaksi {

    private static final double BATAS_CASHBACK = 1_000_000;

    public static void topUp(Pelanggan p, double jumlah) {
        p.tambahSaldo(jumlah);
        System.out.println("Top up berhasil.");
        System.out.printf("Saldo sekarang: Rp%,.0f%n", p.getSaldo());
    }

    public static void pembelian(Pelanggan p, double harga) {

        if (p.isDiblokir()) {
            System.out.println("Akun diblokir! Hubungi customer service.");
            return;
        }

        String jenis = p.getJenisRekening();
        double cashback = hitungCashback(jenis, harga);

        if (!p.kurangiSaldo(harga)) {
            System.out.println("Transaksi gagal! Saldo tidak mencukupi.");
            System.out.printf("Saldo saat ini : Rp%,.0f%n", p.getSaldo());
            System.out.printf("Total bayar    : Rp%,.0f%n", harga);
            System.out.printf("Saldo minimal  : Rp10.000%n");
            return;
        }

        p.tambahSaldo(cashback);

        System.out.println("=== TRANSAKSI BERHASIL ===");
        System.out.println("Jenis rekening : " + jenis);
        System.out.printf("Harga belanja  : Rp%,.0f%n", harga);
        if (cashback > 0)
            System.out.printf("Cashback saldo : Rp%,.0f%n", cashback);
        System.out.printf("Saldo akhir    : Rp%,.0f%n", p.getSaldo());
    }

    private static double hitungCashback(String jenis, double harga) {

        if (jenis.equals(JenisRekening.SILVER)) {
            if (harga > BATAS_CASHBACK) return harga * 0.05;
            return 0;
        }

        if (jenis.equals(JenisRekening.GOLD)) {
            if (harga > BATAS_CASHBACK) return harga * 0.07;
            return harga * 0.02;
        }

        if (jenis.equals(JenisRekening.PLATINUM)) {
            if (harga > BATAS_CASHBACK) return harga * 0.10;
            return harga * 0.05;
        }

        return 0;
    }
}