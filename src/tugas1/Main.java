package tugas1;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Selamat datang di Aplikasi Restoran (Tugas 1)");
        
        PesanMakanan pesanMakanan = new PesanMakanan(); 
        Scanner input = new Scanner(System.in);
        
        ItemPesanan[] keranjang = new ItemPesanan[10];
        int indexKeranjang = 0;
        double totalBiaya = 0;
        boolean gotDiscount = false;
        
        // Penanda index mana yang merupakan item GRATIS
        int indexItemPromo = -1; 

        pesanMakanan.tampilkanMenu();

        // --- PROSES PESANAN ---
        // Pesanan 1
        System.out.print("Pilih angka menu yang ingin dipesan: ");
        int pilihanMenu = input.nextInt();
        System.out.print("Berapa banyak porsi? ");
        int jumlahPesan = input.nextInt();
        totalBiaya += pesanMakanan.pesanMenu(pilihanMenu, jumlahPesan);
        Menu menuDipesan1 = pesanMakanan.getMenuByPilihan(pilihanMenu);
        if (menuDipesan1 != null) {
            keranjang[indexKeranjang] = new ItemPesanan(menuDipesan1, jumlahPesan);
            indexKeranjang++;
        }

        // Pesanan 2 (Manual If-Else)
        System.out.print("Pesan lagi? (ya/tidak): ");
        if (input.next().equalsIgnoreCase("ya")) {
            System.out.print("Pilih angka menu: ");
            int pil2 = input.nextInt();
            System.out.print("Banyak porsi? ");
            int jum2 = input.nextInt();
            totalBiaya += pesanMakanan.pesanMenu(pil2, jum2);
            Menu menu2 = pesanMakanan.getMenuByPilihan(pil2);
            if (menu2 != null) {
                keranjang[indexKeranjang] = new ItemPesanan(menu2, jum2);
                indexKeranjang++;
            }
            
            System.out.print("Pesan lagi? (ya/tidak): ");
            if (input.next().equalsIgnoreCase("ya")) {
                System.out.print("Pilih angka menu: ");
                int pil3 = input.nextInt();
                System.out.print("Banyak porsi? ");
                int jum3 = input.nextInt();
                totalBiaya += pesanMakanan.pesanMenu(pil3, jum3);
                Menu menu3 = pesanMakanan.getMenuByPilihan(pil3);
                if (menu3 != null) {
                    keranjang[indexKeranjang] = new ItemPesanan(menu3, jum3);
                    indexKeranjang++;
                }

                System.out.print("Pesan lagi? (ya/tidak): ");
                if (input.next().equalsIgnoreCase("ya")) {
                    System.out.print("Pilih angka menu: ");
                    int pil4 = input.nextInt();
                    System.out.print("Banyak porsi? ");
                    int jum4 = input.nextInt();
                    totalBiaya += pesanMakanan.pesanMenu(pil4, jum4);
                    Menu menu4 = pesanMakanan.getMenuByPilihan(pil4);
                    if (menu4 != null) {
                        keranjang[indexKeranjang] = new ItemPesanan(menu4, jum4);
                        indexKeranjang++;
                    }
                }
            }
        }

        // --- LOGIKA PROMO (GRATIS TOTAL jika > 50rb) ---
        if (totalBiaya >= 50000) {
            System.out.println("\n[INFO] Total belanja Anda > Rp 50.000");
            System.out.println("Selamat! Anda berhak Promo GRATIS 2 Minuman.");
            System.out.print("Ambil promo? (ya/tidak): ");
            
            if (input.next().equalsIgnoreCase("ya")) {
                System.out.println("Pilih minuman GRATIS (5-8): ");
                System.out.println("5. Es Teh | 6. Jus Alpukat | 7. Jeruk Hangat | 8. Air Mineral");
                System.out.print("Pilih angka menu minuman: ");
                int pilMinum = input.nextInt();
                
                Menu mMinum = pesanMakanan.getMenuByPilihan(pilMinum);
                
                if (mMinum != null && pilMinum >= 5 && pilMinum <= 8) {
                    
                    // Masukkan ke keranjang dengan jumlah 2
                    keranjang[indexKeranjang] = new ItemPesanan(mMinum, 2);
                    
                    // TANDAI index ini sebagai item GRATIS
                    indexItemPromo = indexKeranjang;
                    
                    indexKeranjang++;
                    
                    // PENTING: Total Biaya TIDAK DITAMBAH (Rp 0) sesuai permintaan
                    // totalBiaya += 0; 
                    
                    System.out.println("Berhasil! 2 porsi " + mMinum.namaMenu + " masuk keranjang (GRATIS / Rp 0).");
                } else {
                    System.out.println("Pilihan tidak valid/bukan minuman.");
                }
            }
        }

        if (totalBiaya >= 100000) gotDiscount = true;

        // --- CETAK STRUK ---
        System.out.println("\n=======================================");
        System.out.println("          STRUK PESANAN ANDA           ");
        System.out.println("=======================================");
        
        // Loop manual untuk mencetak isi keranjang
        for (int i = 0; i < indexKeranjang; i++) {
            if (keranjang[i] != null) {
                
                // CEK: Apakah ini item promo?
                if (i == indexItemPromo) {
                    // JIKA PROMO: Tampilkan Harga 0 dan Subtotal 0
                    System.out.println(
                        keranjang[i].menu.namaMenu + "\t " + 
                        keranjang[i].jumlah + "x @Rp.0" + // Harga per item ditulis 0
                        " \t = Rp.0 (GRATIS)" // Subtotal ditulis 0
                    );
                } else {
                    // JIKA BUKAN PROMO: Tampilkan normal
                    System.out.println(formatStruk(keranjang[i]));
                }
            }
        }

        System.out.println("---------------------------------------");
        System.out.println("Subtotal \t : Rp." + totalBiaya);

        double pajak = pesanMakanan.getTaxAmount(totalBiaya);
        double servis = pesanMakanan.getServiceAmount();
        double nominalDiskon = gotDiscount ? totalBiaya * 0.1 : 0;
        double tagihanAkhir = (totalBiaya - nominalDiskon) + pajak + servis;

        if (gotDiscount) System.out.println("Diskon 10% \t : -Rp." + nominalDiskon);
        System.out.println("Pajak (10%) \t : Rp." + pajak);
        System.out.println("Layanan \t : Rp." + servis);
        System.out.println("---------------------------------------");
        System.out.println("TOTAL BAYAR \t : Rp." + tagihanAkhir);
        System.out.println("=======================================");
        
        input.close();
    }

    public static String formatStruk(ItemPesanan item) {
        return item.menu.namaMenu + "\t " + item.jumlah + "x @" + item.menu.hargaMenu + " \t = Rp." + item.getSubtotal();
    }
}