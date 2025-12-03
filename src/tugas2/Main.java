package tugas2;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void tampilkanMenu(PesanMakanan pesan) {
        System.out.println("Daftar Menu:");
        System.out.println("------------------------------------------------------------------");
        for (int i = 0; i < pesan.daftarMenu.size(); i++) {
            int x = i + 1;
            Menu daftar = pesan.daftarMenu.get(i);
            System.out.println("Menu " + x + ". " + daftar.namaMenu + ", Harga: Rp."
                    + daftar.hargaMenu + ", Kategori: " + daftar.kategoriMenu);
        }
        System.out.println("------------------------------------------------------------------");
    }

    public static Menu getMenuByPilihan(PesanMakanan pesan, int pilihanMenu) {
        int index = pilihanMenu - 1;
        if (index >= 0 && index < pesan.daftarMenu.size()) {
            return pesan.daftarMenu.get(index);
        }
        return null;
    }

    public static Menu getMenuByNama(PesanMakanan pesan, String namaMenu) {
        for (Menu dafMenu : pesan.daftarMenu) {
            if (dafMenu.namaMenu.equalsIgnoreCase(namaMenu)) {
                return dafMenu;
            }
        }
        return null;
    }

    public static double getTaxAmount(double totalBiaya) {
        return totalBiaya * 0.1;
    }

    public static double getServiceAmount() {
        return 20000.0;
    }

    public static void showMenuMinuman(PesanMakanan pesan) {
        for (Menu menu : pesan.daftarMenu) {
            if (menu.kategoriMenu.equals("Minuman")) {
                System.out.printf("%-25s Harga: Rp.%d %n", menu.namaMenu, menu.hargaMenu);
            }
        }
    }

    public static void showMenuMakanan(PesanMakanan pesan) {
        for (Menu menu : pesan.daftarMenu) {
            if (menu.kategoriMenu.equals("Makanan")) {
                System.out.printf("%-25s Harga: Rp.%d %n", menu.namaMenu, menu.hargaMenu);
            }
        }
    }

    public static void editMenu(PesanMakanan pesan, Scanner input) {
        // PERBAIKAN: Loop untuk kembali ke menu manajemen setelah selesai
        boolean kembaliKeManajemen = true;
        
        while (kembaliKeManajemen) {
            System.out.println("\n--- Manajemen Menu ---");
            System.out.println("1. Tambah Menu Baru");
            System.out.println("2. Ubah Menu");
            System.out.println("3. Hapus Menu");
            System.out.println("4. Kembali ke Pemesanan");
            System.out.print("Pilih opsi (1-4): ");
            String opsi = input.nextLine();

            try {
                if (opsi.equals("1")) {
                    System.out.print("Masukkan Nama Menu Baru: ");
                    String nama = input.nextLine();
                    System.out.print("Masukkan Harga Menu Baru (cth: 50000): ");
                    int harga = Integer.parseInt(input.nextLine());
                    System.out.print("Masukkan Kategori Menu Baru (Makanan/Minuman): ");
                    String kategori = input.nextLine();

                    pesan.daftarMenu.add(new Menu(nama, harga, kategori));
                    System.out.println("BERHASIL: Menu '" + nama + "' telah ditambahkan!");
                    System.out.println("------------------------\n");

                } else if (opsi.equals("2")) {
                    tampilkanMenu(pesan);
                    System.out.print("Masukkan nomor menu yang ingin diubah: ");
                    int nomor = Integer.parseInt(input.nextLine());

                    Menu menu = getMenuByPilihan(pesan, nomor);
                    if (menu != null) {
                        System.out.println("Mengubah: " + menu.namaMenu);
                        System.out.print("Masukkan Nama Baru (kosongkan jika tidak ingin ubah): ");
                        String namaBaru = input.nextLine();
                        
                        System.out.print("Masukkan Harga Baru (kosongkan jika tidak ingin ubah): ");
                        String hargaBaruStr = input.nextLine();
                        
                        System.out.print("Masukkan Kategori Baru (kosongkan jika tidak ingin ubah): ");
                        String kategoriBaru = input.nextLine();
                        
                        // PERBAIKAN: Konfirmasi sebelum mengubah
                        System.out.print("Apakah Anda yakin ingin mengubah menu ini? (Ya/Tidak): ");
                        String konfirmasi = input.nextLine();
                        
                        if (konfirmasi.equalsIgnoreCase("Ya")) {
                            if (!namaBaru.isEmpty()) {
                                menu.namaMenu = namaBaru;
                            }
                            if (!hargaBaruStr.isEmpty()) {
                                menu.hargaMenu = Integer.parseInt(hargaBaruStr);
                            }
                            if (!kategoriBaru.isEmpty()) {
                                menu.kategoriMenu = kategoriBaru;
                            }
                            System.out.println("BERHASIL: Menu telah diubah.");
                        } else {
                            System.out.println("Perubahan dibatalkan.");
                        }
                    } else {
                        System.out.println("Nomor menu tidak valid.");
                    }
                    System.out.println("------------------------\n");
                    
                } else if (opsi.equals("3")) {
                    tampilkanMenu(pesan);
                    System.out.print("Masukkan nomor menu yang ingin dihapus: ");
                    int nomor = Integer.parseInt(input.nextLine());
                    int index = nomor - 1;

                    if (index >= 0 && index < pesan.daftarMenu.size()) {
                        Menu menuDihapus = pesan.daftarMenu.get(index);
                        System.out.print("Apakah anda yakin menghapus '" + menuDihapus.namaMenu + "'? (Ya/Tidak): ");
                        String hapus = input.nextLine();
                        if (hapus.equalsIgnoreCase("ya")) {
                            pesan.daftarMenu.remove(index);
                            System.out.println("BERHASIL: Menu '" + menuDihapus.namaMenu + "' telah dihapus.");
                        } else {
                            System.out.println("Penghapusan dibatalkan.");
                        }
                    } else {
                        System.out.println("Nomor Menu tidak valid.");
                    }
                    System.out.println("------------------------\n");
                    
                } else if (opsi.equals("4")) {
                    System.out.println("Kembali ke pemesanan...\n");
                    kembaliKeManajemen = false; // Keluar dari loop manajemen menu
                    
                } else {
                    System.out.println("Opsi tidak valid. Silakan pilih 1-4.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Input tidak valid. Harap masukkan angka yang benar.");
            }
        }
    }

    // ==================== MAIN PROGRAM ====================
    public static void main(String[] args) {

        System.out.println("Selamat datang di Aplikasi Pemesanan Makanan Katanya Sederhana!");
        PesanMakanan pesanMakanan = new PesanMakanan();
        Scanner input = new Scanner(System.in);
        
        // FITUR BARU: Loop utama untuk order kembali
        boolean orderLagi = true;
        
        while (orderLagi) {
            List<ItemPesanan> keranjang = new ArrayList<>();
            double totalBiaya = 0;
            boolean gotDiscount = false;

            // PERBAIKAN: Loop untuk meminta input ulang jika menu tidak valid
            while (true) {
                showMenuMakanan(pesanMakanan);
                showMenuMinuman(pesanMakanan);

                System.out.print("Masukkan pesanan (Format: Nama Menu = Jumlah) atau ketik 'Selesai': ");
                String pesanan = input.nextLine();

                if (pesanan.equalsIgnoreCase("Selesai")) {
                    break;
                } else if (pesanan.equalsIgnoreCase("EditMenu")) {
                    editMenu(pesanMakanan, input);
                    continue; // Setelah keluar dari editMenu, loop pemesanan berlanjut
                }

                try {
                    String[] parts = pesanan.split("=");
                    if (parts.length != 2) {
                        System.out.println("Format input salah! Gunakan format: Nama Menu = Jumlah");
                        continue; // PERBAIKAN: Meminta input ulang
                    }
                    
                    String pilihanMenu = parts[0].trim();
                    int jumlahPesan = Integer.parseInt(parts[1].trim());

                    Menu menuDipesan = getMenuByNama(pesanMakanan, pilihanMenu);
                    
                    // PERBAIKAN: Validasi menu dan loop jika tidak valid
                    if (menuDipesan != null) {
                        totalBiaya += menuDipesan.hargaMenu * jumlahPesan;
                        keranjang.add(new ItemPesanan(menuDipesan, jumlahPesan));
                        System.out.println("✓ " + menuDipesan.namaMenu + " berhasil ditambahkan.\n");
                    } else {
                        System.out.println("✗ Menu tidak tersedia. Silakan pilih menu yang ada.\n");
                        // Loop akan otomatis meminta input lagi
                    }

                } catch (NumberFormatException e) {
                    System.out.println("Format input salah! Jumlah harus berupa angka. Gunakan format: Nama Menu = Jumlah\n");
                    // Loop akan otomatis meminta input lagi
                } catch (Exception e) {
                    System.out.println("Format input salah! Gunakan format: Nama Menu = Jumlah\n");
                    // Loop akan otomatis meminta input lagi
                }
            }

            // ===== Promo & Perhitungan =====

            if (totalBiaya >= 50000) {
                System.out.println("\n🎉 Selamat! Total belanja Anda Rp." + String.format("%.0f", totalBiaya));
                System.out.println("Anda berhak atas promo Beli 1 Gratis 1 Minuman.");

                // PERBAIKAN: Loop untuk validasi input promo
                boolean promoValid = false;
                while (!promoValid) {
                    System.out.print("Gunakan promo? (ya/tidak): ");
                    String promoKonfirmasi = input.nextLine();

                    if (promoKonfirmasi.equalsIgnoreCase("ya")) {
                        promoValid = true;
                        System.out.println("\nSilakan pilih minuman:");
                        showMenuMinuman(pesanMakanan);

                        // PERBAIKAN: Loop untuk validasi pilihan minuman
                        boolean minumanValid = false;
                        while (!minumanValid) {
                            System.out.print("Masukkan nama minuman: ");
                            String pilihanMinuman = input.nextLine();

                            Menu minumanDipilih = getMenuByNama(pesanMakanan, pilihanMinuman);

                            if (minumanDipilih != null && minumanDipilih.kategoriMenu.equals("Minuman")) {
                                // Tambahkan minuman dengan keterangan beli 1 gratis 1
                                Menu minumanPromo = new Menu(minumanDipilih.namaMenu + " (Beli 1 Gratis 1)", minumanDipilih.hargaMenu, "Minuman");
                                keranjang.add(new ItemPesanan(minumanPromo, 1));
                                System.out.println("✓ " + minumanDipilih.namaMenu + " Beli 1 Gratis 1 ditambahkan.\n");
                                minumanValid = true;
                            } else {
                                System.out.println("✗ Pilihan tidak valid. Silakan pilih minuman yang tersedia.\n");
                                // Loop akan meminta input ulang
                            }
                        }
                    } else if (promoKonfirmasi.equalsIgnoreCase("tidak")) {
                        System.out.println("Promo tidak digunakan.\n");
                        promoValid = true;
                    } else {
                        System.out.println("Input tidak valid. Silakan ketik 'ya' atau 'tidak'.\n");
                        // Loop akan meminta input ulang
                    }
                }

                if (totalBiaya >= 100000) {
                    gotDiscount = true;
                }
            }

            // ===== STRUK PEMBAYARAN =====

            System.out.println("\n\n\t--- STRUK PESANAN ANDA ---");
            for (ItemPesanan item : keranjang) {
                // Cek apakah item adalah promo beli 1 gratis 1
                if (item.menu.namaMenu.contains("(Beli 1 Gratis 1)")) {
                    System.out.printf("%-17s %d pcs x Rp. %d = Rp. %.0f (Dapat 2 pcs) %n",
                            item.menu.namaMenu, item.jumlah, item.menu.hargaMenu, item.getSubtotal());
                } else {
                    System.out.printf("%-17s %d pcs x Rp. %d = Rp. %.0f %n",
                            item.menu.namaMenu, item.jumlah, item.menu.hargaMenu, item.getSubtotal());
                }
            }

            System.out.println("-----------------------------------------------");
            System.out.printf("%-25s Rp. %.0f %n", "Subtotal", totalBiaya);

            double pajak = getTaxAmount(totalBiaya);
            double servis = getServiceAmount();
            double tagihanAkhir;

            if (gotDiscount) {
                double diskon10Persen = totalBiaya * 0.1;
                tagihanAkhir = (totalBiaya - diskon10Persen) + pajak + servis;
                System.out.printf("%-25s Rp. %.0f %n", "Diskon 10%", -diskon10Persen);
            } else {
                tagihanAkhir = totalBiaya + pajak + servis;
            }

            System.out.printf("%-25s Rp. %.0f %n", "Pajak 10%", pajak);
            System.out.printf("%-25s Rp. %.0f %n", "Layanan", servis);
            System.out.println("-----------------------------------------------");
            System.out.printf("%-25s Rp. %.0f %n", "Total Tagihan", tagihanAkhir);
            
            System.out.println("\nTerima kasih telah memesan! Selamat menikmati hidangan Anda. 😊");
            
            // FITUR BARU: Keputusan untuk order kembali atau tidak
            System.out.println("\n===============================================");
            boolean inputValid = false;
            while (!inputValid) {
                System.out.print("Apakah Anda ingin memesan lagi? (ya/tidak): ");
                String pesanLagi = input.nextLine();
                
                if (pesanLagi.equalsIgnoreCase("ya")) {
                    System.out.println("\n🔄 Memulai pesanan baru...\n");
                    System.out.println("==============================================\n");
                    inputValid = true;
                    orderLagi = true; // Lanjutkan loop
                } else if (pesanLagi.equalsIgnoreCase("tidak")) {
                    System.out.println("\n👋 Terima kasih telah menggunakan aplikasi kami!");
                    System.out.println("Sampai jumpa kembali! 😊");
                    System.out.println("===============================================");
                    inputValid = true;
                    orderLagi = false; // Keluar dari loop utama
                } else {
                    System.out.println("✗ Input tidak valid. Silakan ketik 'ya' atau 'tidak'.\n");
                    // Loop akan meminta input ulang
                }
            }
        }

        input.close();
    }
}