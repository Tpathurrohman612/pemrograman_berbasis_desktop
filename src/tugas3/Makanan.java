// Makanan.java - Inheritance dari MenuItem
package tugas3;
public class Makanan extends MenuItem {
    // Atribut tambahan khusus Makanan
    private String jenisMakanan;
    
    // Constructor
    public Makanan(String nama, double harga, String jenisMakanan) {
        super(nama, harga, "Makanan");
        this.jenisMakanan = jenisMakanan;
    }
    
    // Getter dan Setter
    public String getJenisMakanan() {
        return jenisMakanan;
    }
    
    public void setJenisMakanan(String jenisMakanan) {
        this.jenisMakanan = jenisMakanan;
    }
    
    // Implementasi metode abstrak (Polymorphism)
    @Override
    public void tampilMenu() {
        System.out.printf("%-25s | Rp.%-10.0f | Jenis: %-15s | Kategori: %s%n", 
            getNama(), getHarga(), jenisMakanan, getKategori());
    }
    
    // Format untuk save ke file
    @Override
    public String toFileFormat() {
        return "MAKANAN," + getNama() + "," + getHarga() + "," + jenisMakanan;
    }
}