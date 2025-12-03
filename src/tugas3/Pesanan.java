// Pesanan.java - Class untuk mengelola pesanan pelanggan
package tugas3;
import java.util.ArrayList;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Pesanan {
    private ArrayList<ItemPesanan> daftarPesanan;
    private static final String STRUK_FOLDER = "struk_pesanan/";
    private static final double PAJAK = 0.10; // 10%
    private static final double BIAYA_LAYANAN = 20000;
    private static final double DISKON_THRESHOLD = 100000;
    private static final double DISKON_BESAR = 0.10; // 10%
    private static final double PROMO_MINUMAN_THRESHOLD = 50000;
    private boolean promoMinumanDigunakan = false;
    
    // Constructor
    public Pesanan() {
        daftarPesanan = new ArrayList<>();
        promoMinumanDigunakan = false;
        // Buat folder struk jika belum ada
        File folder = new File(STRUK_FOLDER);
        if (!folder.exists()) {
            folder.mkdir();
        }
    }
    
    // Tambah item ke pesanan
    public void tambahItem(MenuItem menuItem, int jumlah) throws Exception {
        if (jumlah <= 0) {
            throw new Exception("Jumlah pesanan harus lebih dari 0!");
        }
        
        // Cek apakah item sudah ada di pesanan
        for (ItemPesanan item : daftarPesanan) {
            if (item.getMenuItem().getNama().equals(menuItem.getNama())) {
                item.setJumlah(item.getJumlah() + jumlah);
                System.out.println("Jumlah " + menuItem.getNama() + " diperbarui!");
                return;
            }
        }
        
        // Tambah item baru
        daftarPesanan.add(new ItemPesanan(menuItem, jumlah));
        System.out.println(menuItem.getNama() + " berhasil ditambahkan ke pesanan!");
    }
    
    // Hitung subtotal
    public double hitungSubtotal() {
        double subtotal = 0;
        for (ItemPesanan item : daftarPesanan) {
            subtotal += item.getSubtotal();
        }
        return subtotal;
    }
    
    // Hitung pajak
    public double hitungPajak() {
        return hitungSubtotal() * PAJAK;
    }
    
    // Hitung diskon (jika memenuhi syarat)
    public double hitungDiskon() {
        double subtotal = hitungSubtotal();
        if (subtotal >= DISKON_THRESHOLD) {
            return subtotal * DISKON_BESAR;
        }
        return 0;
    }
    
    // Hitung total akhir
    public double hitungTotalAkhir() {
        double subtotal = hitungSubtotal();
        double diskon = hitungDiskon();
        double pajak = hitungPajak();
        
        return subtotal - diskon + pajak + BIAYA_LAYANAN;
    }
    
    // Cek apakah pesanan kosong
    public boolean isEmpty() {
        return daftarPesanan.isEmpty();
    }
    
    // Cek apakah berhak promo minuman
    public boolean berhakPromoMinuman() {
        return hitungSubtotal() >= PROMO_MINUMAN_THRESHOLD && !promoMinumanDigunakan;
    }
    
    // Tandai promo minuman sudah digunakan
    public void gunakanPromoMinuman() {
        promoMinumanDigunakan = true;
    }
    
    // Cek status promo minuman
    public boolean isPromoMinumanDigunakan() {
        return promoMinumanDigunakan;
    }
    
    // Tampilkan struk pesanan
    public void tampilkanStruk() {
        if (daftarPesanan.isEmpty()) {
            System.out.println("Tidak ada pesanan!");
            return;
        }
        
        System.out.println("\n");
        System.out.println("========================================================");
        System.out.println("           STRUK PESANAN RESTORAN");
        System.out.println("========================================================");
        System.out.println("Tanggal: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
        System.out.println("--------------------------------------------------------");
        
        // Tampilkan item pesanan
        for (ItemPesanan item : daftarPesanan) {
            item.tampilkanItem();
        }
        
        System.out.println("--------------------------------------------------------");
        System.out.printf("%-35s Rp.%.0f%n", "Subtotal:", hitungSubtotal());
        
        // Tampilkan info promo minuman jika digunakan
        if (promoMinumanDigunakan) {
            System.out.println(">>> PROMO BELI 1 GRATIS 1 MINUMAN DIGUNAKAN <<<");
        }
        
        // Tampilkan diskon jika ada
        double diskon = hitungDiskon();
        if (diskon > 0) {
            System.out.printf("%-35s -Rp.%.0f (10%%)%n", "Diskon:", diskon);
        }
        
        System.out.printf("%-35s Rp.%.0f (10%%)%n", "Pajak:", hitungPajak());
        System.out.printf("%-35s Rp.%.0f%n", "Biaya Layanan:", BIAYA_LAYANAN);
        System.out.println("========================================================");
        System.out.printf("%-35s Rp.%.0f%n", "TOTAL PEMBAYARAN:", hitungTotalAkhir());
        System.out.println("========================================================");
        System.out.println("\n      Terima kasih atas kunjungan Anda!");
        System.out.println("========================================================\n");
    }
    
    // Simpan struk ke file (Operasi File I/O)
    public void simpanStrukKeFile() {
        if (daftarPesanan.isEmpty()) {
            System.out.println("Tidak ada pesanan untuk disimpan!");
            return;
        }
        
        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        String filename = STRUK_FOLDER + "struk_" + timestamp + ".txt";
        
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write("========================================================");
            writer.newLine();
            writer.write("           STRUK PESANAN RESTORAN");
            writer.newLine();
            writer.write("========================================================");
            writer.newLine();
            writer.write("Tanggal: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
            writer.newLine();
            writer.write("--------------------------------------------------------");
            writer.newLine();
            
            // Tulis item pesanan
            for (ItemPesanan item : daftarPesanan) {
                writer.write(item.toFileFormat());
                writer.newLine();
            }
            
            writer.write("--------------------------------------------------------");
            writer.newLine();
            writer.write(String.format("Subtotal: Rp.%.0f", hitungSubtotal()));
            writer.newLine();
            
            // Info promo minuman
            if (promoMinumanDigunakan) {
                writer.write(">>> PROMO BELI 1 GRATIS 1 MINUMAN DIGUNAKAN <<<");
                writer.newLine();
            }
            
            double diskon = hitungDiskon();
            if (diskon > 0) {
                writer.write(String.format("Diskon: -Rp.%.0f (10%%)", diskon));
                writer.newLine();
            }
            
            writer.write(String.format("Pajak: Rp.%.0f (10%%)", hitungPajak()));
            writer.newLine();
            writer.write(String.format("Biaya Layanan: Rp.%.0f", BIAYA_LAYANAN));
            writer.newLine();
            writer.write("========================================================");
            writer.newLine();
            writer.write(String.format("TOTAL PEMBAYARAN: Rp.%.0f", hitungTotalAkhir()));
            writer.newLine();
            writer.write("========================================================");
            writer.newLine();
            
            System.out.println("Struk berhasil disimpan ke: " + filename);
        } catch (IOException e) {
            System.out.println("Error saat menyimpan struk: " + e.getMessage());
        }
    }
    
    // Clear pesanan
    public void clearPesanan() {
        daftarPesanan.clear();
        promoMinumanDigunakan = false;
    }
    
    // Get daftar pesanan (untuk akses dari luar)
    public ArrayList<ItemPesanan> getDaftarPesanan() {
        return daftarPesanan;
    }
}