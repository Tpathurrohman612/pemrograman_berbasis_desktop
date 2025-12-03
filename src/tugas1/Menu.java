package tugas1;

public class Menu {
    String namaMenu;
    int hargaMenu;
    String kategoriMenu;

    public Menu(String namaMenu, int hargaMenu, String kategoriMenu) {
        this.namaMenu = namaMenu;
        this.hargaMenu = hargaMenu;
        this.kategoriMenu = kategoriMenu;
    }
}

// --- KELAS ITEM PESANAN ---
// Hapus "static" karena sekarang ini adalah class yang berdiri sendiri dalam file Menu.java
class ItemPesanan {
    Menu menu;
    int jumlah;

    public ItemPesanan(Menu menu, int jumlah) {
        this.menu = menu;
        this.jumlah = jumlah;
    }

    public double getSubtotal() {
        return menu.hargaMenu * jumlah;
    }
}

// --- KELAS LOGIKA PESAN MAKANAN ---
class PesanMakanan {
    Menu[] daftarMenu;

    public PesanMakanan() {
        // PERBAIKAN: Menyiapkan array untuk minimal 8 menu
        daftarMenu = new Menu[10];
        
        // Kategori Makanan (4 Item) - Sudah OK
        daftarMenu[0] = new Menu("Nasi Ayam Pop", 35000, "Makanan");
        daftarMenu[1] = new Menu("Nasi Ayam Gulai", 30000, "Makanan");
        daftarMenu[2] = new Menu("Nasi Rendang", 45000, "Makanan");
        daftarMenu[3] = new Menu("Nasi Kalio", 40000, "Makanan");
        
        // Kategori Minuman (DIUBAH MENJADI 4 ITEM AGAR SESUAI INSTRUKSI)
        daftarMenu[4] = new Menu("Es Teh Manis", 10000, "Minuman");
        daftarMenu[5] = new Menu("Jus Alpukat", 15000, "Minuman");
        daftarMenu[6] = new Menu("Jeruk Hangat", 12000, "Minuman"); // Baru
        daftarMenu[7] = new Menu("Air Mineral", 5000, "Minuman");   // Baru
    }

    // Method ini harus ada di sini agar class PesanMakanan lengkap
    public void tampilkanMenu() {
        System.out.println("Daftar Menu Restoran:");
        System.out.println("------------------------------------------------------------------");
        System.out.println("MAKANAN:");
        System.out.println("1. " + daftarMenu[0].namaMenu + "\t: Rp." + daftarMenu[0].hargaMenu);
        System.out.println("2. " + daftarMenu[1].namaMenu + "\t: Rp." + daftarMenu[1].hargaMenu);
        System.out.println("3. " + daftarMenu[2].namaMenu + "\t: Rp." + daftarMenu[2].hargaMenu);
        System.out.println("4. " + daftarMenu[3].namaMenu + "\t: Rp." + daftarMenu[3].hargaMenu);
        System.out.println("\nMINUMAN:");
        System.out.println("5. " + daftarMenu[4].namaMenu + "\t: Rp." + daftarMenu[4].hargaMenu);
        System.out.println("6. " + daftarMenu[5].namaMenu + "\t: Rp." + daftarMenu[5].hargaMenu);
        System.out.println("7. " + daftarMenu[6].namaMenu + "\t: Rp." + daftarMenu[6].hargaMenu);
        System.out.println("8. " + daftarMenu[7].namaMenu + "\t: Rp." + daftarMenu[7].hargaMenu);
        System.out.println("------------------------------------------------------------------");
    }

    public double pesanMenu(int namaMenu, int jumlahPesan) {
        double totalBiaya = 0;
        switch (namaMenu) {
            case 1: totalBiaya += daftarMenu[0].hargaMenu * jumlahPesan; break;
            case 2: totalBiaya += daftarMenu[1].hargaMenu * jumlahPesan; break;
            case 3: totalBiaya += daftarMenu[2].hargaMenu * jumlahPesan; break;
            case 4: totalBiaya += daftarMenu[3].hargaMenu * jumlahPesan; break;
            case 5: totalBiaya += daftarMenu[4].hargaMenu * jumlahPesan; break;
            case 6: totalBiaya += daftarMenu[5].hargaMenu * jumlahPesan; break;
            case 7: totalBiaya += daftarMenu[6].hargaMenu * jumlahPesan; break;
            case 8: totalBiaya += daftarMenu[7].hargaMenu * jumlahPesan; break;
            default: System.out.println("Menu tidak tersedia."); break;
        }
        return totalBiaya;
    }

    public Menu getMenuByPilihan(int pilihanMenu) {
        int index = pilihanMenu - 1;
        if (index >= 0 && index <= 7) {
            return daftarMenu[index];
        }
        return null;
    }

    public double getTaxAmount(double totalBiaya) {
        return totalBiaya * 0.1;
    }

    public double getServiceAmount() {
        return 20000.0;
    }
}