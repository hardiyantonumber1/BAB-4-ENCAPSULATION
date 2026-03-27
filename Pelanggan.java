class Pelanggan {
    private String nomorPelanggan;
    private String nama;
    private String pin;
    private double saldo;
    private int percobaanSalah;
    private boolean diblokir;

    public Pelanggan(String nomorPelanggan, String nama, String pin, double saldo) {
        this.nomorPelanggan = nomorPelanggan;
        this.nama = nama;
        this.pin = pin;
        this.saldo = saldo;
        this.percobaanSalah = 0;
        this.diblokir = false;
    }

    public String getNomorPelanggan() {
        return nomorPelanggan;
    }

    public String getNama() {
        return nama;
    }

    public double getSaldo() {
        return saldo;
    }

    public boolean isDiblokir() {
        return diblokir;
    }

    public String getJenisRekening() {
        return JenisRekening.tentukanJenis(nomorPelanggan);
    }

    public boolean login(String inputPin) {
        if (diblokir) {
            System.out.println("Akun diblokir! Hubungi customer service.");
            return false;
        }

        if (pin.equals(inputPin)) {
            percobaanSalah = 0;
            return true;
        } else {
            percobaanSalah++;
            System.out.println("PIN salah! Percobaan ke-" + percobaanSalah + " dari 3.");
            if (percobaanSalah >= 3) {
                diblokir = true;
                System.out.println("Akun diblokir karena 3x salah PIN.");
            }
            return false;
        }
    }

    public void tambahSaldo(double jumlah) {
        saldo += jumlah;
    }

    public boolean kurangiSaldo(double jumlah) {
        if (saldo - jumlah < 10000) {
            return false;
        }
        saldo -= jumlah;
        return true;
    }
}