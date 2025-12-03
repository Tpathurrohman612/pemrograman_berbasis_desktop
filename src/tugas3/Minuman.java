// Minuman.java - Inheritance dari MenuItem
package tugas3;
public class Minuman extends MenuItem {
    // Atribut tambahan khusus Minuman
    private String jenisMinuman;
    
    // Constructor
    public Minuman(String nama, double harga, String jenisMinuman) {
        super(nama, harga, "Minuman");
        this.jenisMinuman = jenisMinuman;
    }
    
    // Getter dan Setter
    public String getJenisMinuman() {
        return jenisMinuman;
    }
    
    public void setJenisMinuman(String jenisMinuman) {
        this.jenisMinuman = jenisMinuman;
    }
    
    // Implementasi metode abstrak (Polymorphism)
    @Override
    public void tampilMenu() {
        System.out.printf("%-25s | Rp.%-10.0f | Jenis: %-15s | Kategori: %s%n", 
            getNama(), getHarga(), jenisMinuman, getKategori());
    }
    
    // Format untuk save ke file
    @Override
    public String toFileFormat() {
        return "MINUMAN," + getNama() + "," + getHarga() + "," + jenisMinuman;
    }
}