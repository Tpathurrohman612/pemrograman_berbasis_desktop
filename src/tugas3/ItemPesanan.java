// ItemPesanan.java - Class untuk menyimpan item yang dipesan
package tugas3;
public class ItemPesanan {
    private MenuItem menuItem;
    private int jumlah;
    private boolean isGratis; // Penanda item gratis
    
    // Constructor
    public ItemPesanan(MenuItem menuItem, int jumlah) {
        this.menuItem = menuItem;
        this.jumlah = jumlah;
        this.isGratis = false;
    }
    
    // Constructor dengan parameter gratis
    public ItemPesanan(MenuItem menuItem, int jumlah, boolean isGratis) {
        this.menuItem = menuItem;
        this.jumlah = jumlah;
        this.isGratis = isGratis;
    }
    
    // Getter dan Setter
    public MenuItem getMenuItem() {
        return menuItem;
    }
    
    public void setMenuItem(MenuItem menuItem) {
        this.menuItem = menuItem;
    }
    
    public int getJumlah() {
        return jumlah;
    }
    
    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }
    
    public boolean isGratis() {
        return isGratis;
    }
    
    public void setGratis(boolean gratis) {
        isGratis = gratis;
    }
    
    // Hitung subtotal
    public double getSubtotal() {
        // Jika gratis, return 0
        if (isGratis) {
            return 0;
        }
        
        if (menuItem instanceof Diskon) {
            return ((Diskon) menuItem).getHargaSetelahDiskon() * jumlah;
        }
        return menuItem.getHarga() * jumlah;
    }
    
    // Tampilkan item pesanan
    public void tampilkanItem() {
        String namaItem = menuItem.getNama();
        double hargaSatuan = menuItem.getHarga();
        
        if (isGratis) {
            // Item gratis
            System.out.printf("%-25s %d x GRATIS (Promo) = Rp.0%n", namaItem, jumlah);
        } else if (menuItem instanceof Diskon) {
            Diskon diskonItem = (Diskon) menuItem;
            System.out.printf("%-25s %d x Rp.%.0f (Diskon %.0f%%) = Rp.%.0f%n",
                namaItem, jumlah, hargaSatuan, diskonItem.getPersenDiskon(), getSubtotal());
        } else {
            System.out.printf("%-25s %d x Rp.%.0f = Rp.%.0f%n",
                namaItem, jumlah, hargaSatuan, getSubtotal());
        }
    }
    
    // Format untuk save ke file
    public String toFileFormat() {
        if (isGratis) {
            return menuItem.getNama() + "," + jumlah + ",GRATIS (Promo),0";
        }
        return menuItem.getNama() + "," + jumlah + "," + getSubtotal();
    }
}