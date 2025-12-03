// MenuItem.java - Kelas Abstrak sebagai basis untuk semua menu item
package tugas3;
public abstract class MenuItem {
    // Encapsulation: atribut private
    private String nama;
    private double harga;
    private String kategori;
    
    // Constructor
    public MenuItem(String nama, double harga, String kategori) {
        this.nama = nama;
        this.harga = harga;
        this.kategori = kategori;
    }
    
    // Getter dan Setter (Encapsulation)
    public String getNama() {
        return nama;
    }
    
    public void setNama(String nama) {
        this.nama = nama;
    }
    
    public double getHarga() {
        return harga;
    }
    
    public void setHarga(double harga) {
        this.harga = harga;
    }
    
    public String getKategori() {
        return kategori;
    }
    
    public void setKategori(String kategori) {
        this.kategori = kategori;
    }
    
    // Metode abstrak yang harus diimplementasikan oleh subclass (Abstraksi)
    public abstract void tampilMenu();
    
    // Metode untuk format file
    public abstract String toFileFormat();
}