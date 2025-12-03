// Diskon.java - Inheritance dari MenuItem
package tugas3;
public class Diskon extends MenuItem {
    // Atribut tambahan untuk diskon (dalam persen)
    private double persenDiskon;
    
    // Constructor
    public Diskon(String nama, double harga, double persenDiskon) {
        super(nama, harga, "Diskon");
        this.persenDiskon = persenDiskon;
    }
    
    // Getter dan Setter
    public double getPersenDiskon() {
        return persenDiskon;
    }
    
    public void setPersenDiskon(double persenDiskon) {
        this.persenDiskon = persenDiskon;
    }
    
    // Menghitung harga setelah diskon
    public double getHargaSetelahDiskon() {
        return getHarga() * (1 - persenDiskon / 100);
    }
    
    // Implementasi metode abstrak (Polymorphism)
    @Override
    public void tampilMenu() {
        System.out.printf("%-25s | Rp.%-10.0f | Diskon: %.0f%% | Harga Diskon: Rp.%.0f%n", 
            getNama(), getHarga(), persenDiskon, getHargaSetelahDiskon());
    }
    
    // Format untuk save ke file
    @Override
    public String toFileFormat() {
        return "DISKON," + getNama() + "," + getHarga() + "," + persenDiskon;
    }
}