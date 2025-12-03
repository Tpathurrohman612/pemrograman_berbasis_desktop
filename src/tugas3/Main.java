// Main.java - Program Utama Manajemen Restoran
package tugas3;
import java.util.Scanner;
import java.util.ArrayList;

public class Main {
    private static Menu menu = new Menu();
    private static Pesanan pesanan = new Pesanan();
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        System.out.println("========================================================");
        System.out.println("    SELAMAT DATANG DI APLIKASI MANAJEMEN RESTORAN");
        System.out.println("========================================================\n");
        
        // Load menu dari file (jika ada)
        menu.muatMenuDariFile();
        
        boolean running = true;
        
        while (running) {
            try {
                tampilkanMenuUtama();
                System.out.print("Pilih menu (1-7): ");
                String pilihan = scanner.nextLine().trim();
                
                switch (pilihan) {
                    case "1":
                        manajemenMenu();
                        break;
                    case "2":
                        menu.tampilkanSemuaMenu();
                        break;
                    case "3":
                        terimaPesanan();
                        break;
                    case "4":
                        hitungTotalBiaya();
                        break;
                    case "5":
                        tampilkanStruk();
                        break;
                    case "6":
                        menu.simpanMenuKeFile();
                        break;
                    case "7":
                        System.out.println("\nTerima kasih telah menggunakan aplikasi!");
                        System.out.println("Program ditutup.");
                        running = false;
                        break;
                    default:
                        System.out.println("Pilihan tidak valid! Silakan pilih 1-7.");
                }
                
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
            
            if (running) {
                System.out.println("\nTekan Enter untuk melanjutkan...");
                scanner.nextLine();
            }
        }
        
        scanner.close();
    }
    
    // Tampilkan menu utama
    private static void tampilkanMenuUtama() {
        System.out.println("\n========== MENU UTAMA ==========");
        System.out.println("1. Manajemen Menu (Tambah/Edit/Hapus)");
        System.out.println("2. Tampilkan Daftar Menu");
        System.out.println("3. Terima Pesanan Pelanggan");
        System.out.println("4. Hitung Total Biaya Pesanan");
        System.out.println("5. Tampilkan & Simpan Struk Pesanan");
        System.out.println("6. Simpan Menu ke File");
        System.out.println("7. Keluar");
        System.out.println("================================");
    }
    
    // Fitur 1: Manajemen Menu (Tambah/Edit/Hapus)
    private static void manajemenMenu() {
        boolean kembali = false;
        
        while (!kembali) {
            try {
                System.out.println("\n========== MANAJEMEN MENU ==========");
                System.out.println("1. Tambah Menu Baru");
                System.out.println("2. Edit/Ubah Menu");
                System.out.println("3. Hapus Menu");
                System.out.println("4. Kembali ke Menu Utama");
                System.out.println("====================================");
                System.out.print("Pilih opsi (1-4): ");
                String pilihan = scanner.nextLine().trim();
                
                switch (pilihan) {
                    case "1":
                        tambahMenuBaru();
                        break;
                    case "2":
                        editMenu();
                        break;
                    case "3":
                        hapusMenu();
                        break;
                    case "4":
                        System.out.println("Kembali ke menu utama...");
                        kembali = true;
                        break;
                    default:
                        System.out.println("Pilihan tidak valid! Silakan pilih 1-4.");
                }
                
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
            
            if (!kembali) {
                System.out.println("\nTekan Enter untuk melanjutkan...");
                scanner.nextLine();
            }
        }
    }
    
    // Sub-menu: Tambah menu baru
    private static void tambahMenuBaru() {
        try {
            System.out.println("\n=== TAMBAH MENU BARU ===");
            System.out.println("Pilih jenis menu:");
            System.out.println("1. Makanan");
            System.out.println("2. Minuman");
            System.out.println("3. Menu Diskon");
            System.out.print("Pilihan (1-3): ");
            String jenis = scanner.nextLine().trim();
            
            System.out.print("Nama Menu: ");
            String nama = scanner.nextLine().trim();
            
            if (nama.isEmpty()) {
                throw new Exception("Nama menu tidak boleh kosong!");
            }
            
            System.out.print("Harga: Rp. ");
            double harga = Double.parseDouble(scanner.nextLine().trim());
            
            if (harga <= 0) {
                throw new Exception("Harga harus lebih dari 0!");
            }
            
            MenuItem item = null;
            
            switch (jenis) {
                case "1":
                    System.out.print("Jenis Makanan (contoh: Ayam, Daging, Seafood): ");
                    String jenisMakanan = scanner.nextLine().trim();
                    item = new Makanan(nama, harga, jenisMakanan);
                    break;
                    
                case "2":
                    System.out.print("Jenis Minuman (contoh: Dingin, Panas, Jus): ");
                    String jenisMinuman = scanner.nextLine().trim();
                    item = new Minuman(nama, harga, jenisMinuman);
                    break;
                    
                case "3":
                    System.out.print("Persentase Diskon (contoh: 10 untuk 10%): ");
                    double diskon = Double.parseDouble(scanner.nextLine().trim());
                    if (diskon < 0 || diskon > 100) {
                        throw new Exception("Diskon harus antara 0-100%!");
                    }
                    item = new Diskon(nama, harga, diskon);
                    break;
                    
                default:
                    throw new Exception("Jenis menu tidak valid!");
            }
            
            menu.tambahMenuItem(item);
            
        } catch (NumberFormatException e) {
            System.out.println("Error: Input harus berupa angka!");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    // Sub-menu: Edit/Ubah menu
    private static void editMenu() {
        try {
            System.out.println("\n=== EDIT/UBAH MENU ===");
            menu.tampilkanSemuaMenu();
            
            System.out.print("Masukkan nomor menu yang ingin diubah: ");
            int nomor = Integer.parseInt(scanner.nextLine().trim());
            
            MenuItem menuDipilih = menu.getMenuByNomor(nomor);
            
            System.out.println("\nMenu yang akan diubah: " + menuDipilih.getNama());
            System.out.println("Biarkan kosong jika tidak ingin mengubah field tertentu");
            System.out.println("----------------------------------------------------");
            
            // Edit Nama
            System.out.print("Nama baru (sekarang: " + menuDipilih.getNama() + "): ");
            String namaBaru = scanner.nextLine().trim();
            if (!namaBaru.isEmpty()) {
                menuDipilih.setNama(namaBaru);
            }
            
            // Edit Harga
            System.out.print("Harga baru (sekarang: Rp." + menuDipilih.getHarga() + "): ");
            String hargaBaruStr = scanner.nextLine().trim();
            if (!hargaBaruStr.isEmpty()) {
                double hargaBaru = Double.parseDouble(hargaBaruStr);
                if (hargaBaru > 0) {
                    menuDipilih.setHarga(hargaBaru);
                } else {
                    System.out.println("Harga tidak valid, tidak diubah.");
                }
            }
            
            // Edit field khusus berdasarkan tipe menu
            if (menuDipilih instanceof Makanan) {
                Makanan makanan = (Makanan) menuDipilih;
                System.out.print("Jenis Makanan baru (sekarang: " + makanan.getJenisMakanan() + "): ");
                String jenisBaru = scanner.nextLine().trim();
                if (!jenisBaru.isEmpty()) {
                    makanan.setJenisMakanan(jenisBaru);
                }
            } else if (menuDipilih instanceof Minuman) {
                Minuman minuman = (Minuman) menuDipilih;
                System.out.print("Jenis Minuman baru (sekarang: " + minuman.getJenisMinuman() + "): ");
                String jenisBaru = scanner.nextLine().trim();
                if (!jenisBaru.isEmpty()) {
                    minuman.setJenisMinuman(jenisBaru);
                }
            } else if (menuDipilih instanceof Diskon) {
                Diskon diskon = (Diskon) menuDipilih;
                System.out.print("Persentase Diskon baru (sekarang: " + diskon.getPersenDiskon() + "%): ");
                String diskonBaruStr = scanner.nextLine().trim();
                if (!diskonBaruStr.isEmpty()) {
                    double diskonBaru = Double.parseDouble(diskonBaruStr);
                    if (diskonBaru >= 0 && diskonBaru <= 100) {
                        diskon.setPersenDiskon(diskonBaru);
                    } else {
                        System.out.println("Persentase diskon tidak valid, tidak diubah.");
                    }
                }
            }
            
            // Konfirmasi perubahan
            System.out.print("\nSimpan perubahan? (ya/tidak): ");
            String konfirmasi = scanner.nextLine().trim();
            
            if (konfirmasi.equalsIgnoreCase("ya")) {
                System.out.println("✅ Menu berhasil diubah!");
            } else {
                System.out.println("❌ Perubahan dibatalkan.");
            }
            
        } catch (NumberFormatException e) {
            System.out.println("Error: Input harus berupa angka!");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    // Sub-menu: Hapus menu
    private static void hapusMenu() {
        try {
            System.out.println("\n=== HAPUS MENU ===");
            menu.tampilkanSemuaMenu();
            
            System.out.print("Masukkan nomor menu yang ingin dihapus: ");
            int nomor = Integer.parseInt(scanner.nextLine().trim());
            
            MenuItem menuDipilih = menu.getMenuByNomor(nomor);
            
            // Konfirmasi penghapusan
            System.out.println("\nAnda akan menghapus menu: " + menuDipilih.getNama());
            System.out.print("Apakah Anda yakin? (ya/tidak): ");
            String konfirmasi = scanner.nextLine().trim();
            
            if (konfirmasi.equalsIgnoreCase("ya")) {
                menu.hapusMenu(nomor);
                System.out.println("✅ Menu berhasil dihapus!");
            } else {
                System.out.println("❌ Penghapusan dibatalkan.");
            }
            
        } catch (NumberFormatException e) {
            System.out.println("Error: Input harus berupa angka!");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    // Fitur 3: Terima pesanan pelanggan
    private static void terimaPesanan() {
        try {
            System.out.println("\n=== TERIMA PESANAN ===");
            
            boolean memesanLagi = true;
            
            while (memesanLagi) {
                menu.tampilkanSemuaMenu();
                
                System.out.println("Ketik 'selesai' untuk mengakhiri pesanan");
                System.out.print("Pilih nomor menu: ");
                String input = scanner.nextLine().trim();
                
                if (input.equalsIgnoreCase("selesai")) {
                    memesanLagi = false;
                    continue;
                }
                
                try {
                    int nomorMenu = Integer.parseInt(input);
                    MenuItem menuDipilih = menu.getMenuByNomor(nomorMenu);
                    
                    System.out.print("Jumlah pesanan: ");
                    int jumlah = Integer.parseInt(scanner.nextLine().trim());
                    
                    pesanan.tambahItem(menuDipilih, jumlah);
                    
                    System.out.print("\nTambah pesanan lagi? (ya/tidak): ");
                    String lagi = scanner.nextLine().trim();
                    if (!lagi.equalsIgnoreCase("ya")) {
                        memesanLagi = false;
                    }
                    
                } catch (NumberFormatException e) {
                    System.out.println("Error: Input harus berupa angka!");
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
            
            // Cek promo minuman setelah selesai memesan
            if (!pesanan.isEmpty() && pesanan.berhakPromoMinuman()) {
                tawarkanPromoMinuman();
            }
            
            if (!pesanan.isEmpty()) {
                System.out.println("\nPesanan berhasil dicatat!");
            } else {
                System.out.println("\nTidak ada pesanan yang dicatat.");
            }
            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    // Fitur 4: Hitung total biaya pesanan
    private static void hitungTotalBiaya() {
        try {
            if (pesanan.isEmpty()) {
                throw new Exception("Belum ada pesanan! Silakan pesan terlebih dahulu.");
            }
            
            System.out.println("\n=== RINCIAN BIAYA ===");
            System.out.printf("Subtotal       : Rp.%.0f%n", pesanan.hitungSubtotal());
            
            double diskon = pesanan.hitungDiskon();
            if (diskon > 0) {
                System.out.printf("Diskon (10%%)   : -Rp.%.0f%n", diskon);
            }
            
            System.out.printf("Pajak (10%%)    : Rp.%.0f%n", pesanan.hitungPajak());
            System.out.printf("Biaya Layanan  : Rp.%.0f%n", 20000.0);
            System.out.println("=====================");
            System.out.printf("TOTAL          : Rp.%.0f%n", pesanan.hitungTotalAkhir());
            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    // Fitur 5: Tampilkan dan simpan struk
    private static void tampilkanStruk() {
        try {
            if (pesanan.isEmpty()) {
                throw new Exception("Belum ada pesanan! Silakan pesan terlebih dahulu.");
            }
            
            pesanan.tampilkanStruk();
            
            System.out.print("Simpan struk ke file? (ya/tidak): ");
            String simpan = scanner.nextLine().trim();
            
            if (simpan.equalsIgnoreCase("ya")) {
                pesanan.simpanStrukKeFile();
            }
            
            System.out.print("\nMulai pesanan baru? (ya/tidak): ");
            String baru = scanner.nextLine().trim();
            
            if (baru.equalsIgnoreCase("ya")) {
                pesanan.clearPesanan();
                System.out.println("Pesanan telah direset. Siap untuk pesanan baru!");
            }
            
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    // Method untuk tawarkan promo minuman (FITUR BARU)
    private static void tawarkanPromoMinuman() {
        try {
            System.out.println("\n╔════════════════════════════════════════════════════╗");
            System.out.println("║          🎉 SELAMAT! ANDA BERHAK PROMO! 🎉        ║");
            System.out.println("╚════════════════════════════════════════════════════╝");
            System.out.printf("Total belanja Anda: Rp.%.0f%n", pesanan.hitungSubtotal());
            System.out.println("Anda mendapat PROMO BELI 1 GRATIS 1 MINUMAN!");
            System.out.println("----------------------------------------------------");
            
            System.out.print("\nGunakan promo sekarang? (ya/tidak): ");
            String jawaban = scanner.nextLine().trim();
            
            if (!jawaban.equalsIgnoreCase("ya")) {
                System.out.println("Promo tidak digunakan.");
                return;
            }
            
            // Buat ArrayList untuk menyimpan minuman saja
            ArrayList<MenuItem> daftarMinuman = new ArrayList<>();
            for (int i = 0; i < menu.getJumlahMenu(); i++) {
                try {
                    MenuItem item = menu.getMenuByNomor(i + 1);
                    if (item instanceof Minuman) {
                        daftarMinuman.add(item);
                    }
                } catch (Exception e) {
                    // Skip jika error
                }
            }
            
            // Tampilkan daftar minuman yang tersedia
            System.out.println("\n=== PILIH MINUMAN GRATIS ===");
            System.out.println("--------------------------------------------------");
            for (int i = 0; i < daftarMinuman.size(); i++) {
                System.out.print((i + 1) + ". ");
                daftarMinuman.get(i).tampilMenu();
            }
            System.out.println("--------------------------------------------------");
            
            System.out.print("\nMasukkan nomor minuman yang dipilih (1-" + daftarMinuman.size() + "): ");
            int pilihanMinuman = Integer.parseInt(scanner.nextLine().trim());
            
            // Validasi pilihan
            if (pilihanMinuman < 1 || pilihanMinuman > daftarMinuman.size()) {
                throw new Exception("Nomor minuman tidak valid!");
            }
            
            // Ambil minuman yang dipilih dari ArrayList minuman
            MenuItem minumanDipilih = daftarMinuman.get(pilihanMinuman - 1);
            
            // Tambahkan 1 minuman GRATIS (tidak dihitung dalam subtotal)
            pesanan.getDaftarPesanan().add(new ItemPesanan(minumanDipilih, 1, true));
            pesanan.gunakanPromoMinuman();
            
            System.out.println("\n✅ Promo berhasil digunakan!");
            System.out.println("1 porsi " + minumanDipilih.getNama() + " GRATIS ditambahkan ke pesanan!");
            
        } catch (NumberFormatException e) {
            System.out.println("Error: Input harus berupa angka!");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}