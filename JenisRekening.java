public class JenisRekening {
    public static final String SILVER   = "SILVER";
    public static final String GOLD     = "GOLD";
    public static final String PLATINUM = "PLATINUM";

    public static String tentukanJenis(String nomorPelanggan) {
        String kode = nomorPelanggan.substring(0, 2);

        if (kode.equals("38")) return SILVER;
        if (kode.equals("56")) return GOLD;
        if (kode.equals("74")) return PLATINUM;

        return "TIDAK DIKETAHUI";
    }
}