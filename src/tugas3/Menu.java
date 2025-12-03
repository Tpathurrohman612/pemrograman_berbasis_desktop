// Menu.java - Class untuk mengelola semua item menu
package tugas3;
import java.util.ArrayList;
import java.io.*;

public class Menu {
    // ArrayList untuk menyimpan semua menu items
    private ArrayList<MenuItem> daftarMenu;
    private static final String MENU_FILE = "menu_restoran.txt";
    
    // Constructor
    public Menu() {
        daftarMenu = new ArrayList<>();
        inisialisasiMenuDefault();
    }
    
    // Inisialisasi menu default
    private void inisialisasiMenuDefault() {
        daftarMenu.add(new Makanan("Nasi Ayam Pop", 35000, "Ayam"));
        daftarMenu.add(new Makanan("Nasi Ayam Gulai", 30000, "Ayam"));
        daftarMenu.add(new Makanan("Nasi Rendang", 45000, "Daging"));
        daftarMenu.add(new Makanan("Nasi Kalio", 40000, "Daging"));
        daftarMenu.add(new Minuman("Es Teh", 10000, "Dingin"));
        daftarMenu.add(new Minuman("Jus Alpukat", 15000, "Jus"));
        daftarMenu.add(new Minuman("Jus Mangga", 15000, "Jus"));
        daftarMenu.add(new Minuman("Es Jeruk", 12000, "Dingin"));
    }
    
    // Tambah menu item
    public void tambahMenuItem(MenuItem item) {
        daftarMenu.add(item);
        System.out.println("Menu '" + item.getNama() + "' berhasil ditambahkan!");
    }
    
    // Tampilkan semua menu (Polymorphism)
    public void tampilkanSemuaMenu() {
        if (daftarMenu.isEmpty()) {
            System.out.println("Menu kosong!");
            return;
        }
        
        System.out.println("\n========== DAFTAR MENU RESTORAN ==========");
        System.out.println("------------------------------------------------------------------");
        
        // Tampilkan Makanan
        System.out.println("\n>>> MAKANAN <<<");
        int nomor = 1;
        for (MenuItem item : daftarMenu) {
            if (item instanceof Makanan) {
                System.out.print(nomor + ". ");
                item.tampilMenu();
                nomor++;
            }
        }
        
        // Tampilkan Minuman
        System.out.println("\n>>> MINUMAN <<<");
        for (MenuItem item : daftarMenu) {
            if (item instanceof Minuman) {
                System.out.print(nomor + ". ");
                item.tampilMenu();
                nomor++;
            }
        }
        
        // Tampilkan Menu Diskon
        System.out.println("\n>>> MENU DISKON <<<");
        for (MenuItem item : daftarMenu) {
            if (item instanceof Diskon) {
                System.out.print(nomor + ". ");
                item.tampilMenu();
                nomor++;
            }
        }
        
        System.out.println("------------------------------------------------------------------\n");
    }
    
    // Cari menu berdasarkan nomor
    public MenuItem getMenuByNomor(int nomor) throws Exception {
        if (nomor < 1 || nomor > daftarMenu.size()) {
            throw new Exception("Nomor menu tidak valid! Pilih nomor 1-" + daftarMenu.size());
        }
        return daftarMenu.get(nomor - 1);
    }
    
    // Cari menu berdasarkan nama
    public MenuItem getMenuByNama(String nama) throws Exception {
        for (MenuItem item : daftarMenu) {
            if (item.getNama().equalsIgnoreCase(nama)) {
                return item;
            }
        }
        throw new Exception("Menu '" + nama + "' tidak ditemukan!");
    }
    
    // Get jumlah menu
    public int getJumlahMenu() {
        return daftarMenu.size();
    }
    
    // Hapus menu berdasarkan nomor
    public void hapusMenu(int nomor) throws Exception {
        if (nomor < 1 || nomor > daftarMenu.size()) {
            throw new Exception("Nomor menu tidak valid!");
        }
        MenuItem menuDihapus = daftarMenu.remove(nomor - 1);
        System.out.println("Menu '" + menuDihapus.getNama() + "' berhasil dihapus!");
    }
    
    // Get ArrayList daftar menu (untuk edit)
    public ArrayList<MenuItem> getDaftarMenu() {
        return daftarMenu;
    }
    
    // Save menu ke file (Operasi File I/O)
    public void simpanMenuKeFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(MENU_FILE))) {
            for (MenuItem item : daftarMenu) {
                writer.write(item.toFileFormat());
                writer.newLine();
            }
            System.out.println("Menu berhasil disimpan ke file: " + MENU_FILE);
        } catch (IOException e) {
            System.out.println("Error saat menyimpan menu: " + e.getMessage());
        }
    }
    
    // Load menu dari file (Operasi File I/O)
    public void muatMenuDariFile() {
        File file = new File(MENU_FILE);
        if (!file.exists()) {
            System.out.println("File menu tidak ditemukan. Menggunakan menu default.");
            return;
        }
        
        try (BufferedReader reader = new BufferedReader(new FileReader(MENU_FILE))) {
            daftarMenu.clear();
            String line;
            
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String tipe = parts[0];
                String nama = parts[1];
                double harga = Double.parseDouble(parts[2]);
                
                if (tipe.equals("MAKANAN")) {
                    String jenis = parts[3];
                    daftarMenu.add(new Makanan(nama, harga, jenis));
                } else if (tipe.equals("MINUMAN")) {
                    String jenis = parts[3];
                    daftarMenu.add(new Minuman(nama, harga, jenis));
                } else if (tipe.equals("DISKON")) {
                    double diskon = Double.parseDouble(parts[3]);
                    daftarMenu.add(new Diskon(nama, harga, diskon));
                }
            }
            
            System.out.println("Menu berhasil dimuat dari file: " + MENU_FILE);
        } catch (IOException e) {
            System.out.println("Error saat memuat menu: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error format file: " + e.getMessage());
        }
    }
}