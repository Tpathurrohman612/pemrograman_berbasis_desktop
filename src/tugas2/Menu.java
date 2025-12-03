package tugas2;
import java.util.ArrayList;
import java.util.List;

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

class PesanMakanan {
    List<Menu> daftarMenu;

    public PesanMakanan() {
        daftarMenu = new ArrayList<>();
        daftarMenu.add(new Menu("Nasi Ayam Pop", 35000, "Makanan"));
        daftarMenu.add(new Menu("Nasi Ayam Gulai", 30000, "Makanan"));
        daftarMenu.add(new Menu("Nasi Rendang", 45000, "Makanan"));
        daftarMenu.add(new Menu("Nasi Kalio", 40000, "Makanan"));
        daftarMenu.add(new Menu("Es Teh", 10000, "Minuman"));
        daftarMenu.add(new Menu("Jus Alpukat", 15000, "Minuman"));
        daftarMenu.add(new Menu("Jus Mangga", 15000, "Minuman"));
        daftarMenu.add(new Menu("Es Jeruk", 12000, "Minuman"));
    }
}
