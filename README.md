# PEMROGRAMAN BERBASIS DESKTOP
# UNIVERSITAS TERBUKA

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white) ![VS Code](https://img.shields.io/badge/Visual_Studio_Code-0078D4?style=for-the-badge&logo=visual%20studio%20code&logoColor=white) ![Status](https://img.shields.io/badge/Status-Active-success?style=for-the-badge)

Repository ini berisi kumpulan tugas praktikum untuk mata kuliah **Pemrograman Berbasis Desktop**. Project ini mendemonstrasikan progresi pembelajaran bahasa pemrograman Java mulai dari logika dasar, struktur data sederhana, hingga penerapan konsep *Object-Oriented Programming* (OOP) dan *File Handling*.

## 📂 Struktur Project

Repository ini dibagi menjadi beberapa paket (package) berdasarkan tugas praktikum:

- **`src/tugas1`**: Dasar pemrograman Java (Console App sederhana If Else & Nested If).
- **`src/tugas2`**: Manipulasi String, Collection (ArrayList), dan Logika Loop.
- **`src/tugas3`**: Implementasi OOP (Inheritance, Polymorphism, Encapsulation, Abstraction) dan File I/O.

---

## ✨ Fitur & Penjelasannya

### 1. Tugas 1: Restoran Sederhana (Basic)
Program kasir sederhana yang dibuat dengan batasan penggunaan logika perulangan (looping) yang minimal, lebih menekankan pada struktur keputusan (`if-else`, `switch-case`).

* **Fitur:**
    * Daftar menu statis (Hardcoded Array).
    * Perhitungan Subtotal, Pajak (10%), dan Biaya Layanan.
    * Logika Diskon jika belanja > Rp 100.000.
    * Logika Promo "Beli 1 Gratis 1" atau Gratis Item jika belanja > Rp 50.000.
    * Pencetakan struk sederhana di konsol.

### 2. Tugas 2: Manajemen Pesanan Dinamis
Pengembangan dari Tugas 1 dengan fitur yang lebih dinamis menggunakan `ArrayList` dan input string parsing.

* **Fitur:**
    * **CRUD Menu Sederhana:** User bisa menambah, mengubah, dan menghapus menu saat runtime.
    * **Parsing Input:** Memesan dengan format string `Nama Menu = Jumlah`.
    * **Looping:** Memungkinkan pemesanan berulang kali tanpa harus restart program.
    * Validasi input yang lebih ketat.

### 3. Tugas 3: Sistem Restoran OOP (Advanced)
Implementasi penuh konsep Pemrograman Berorientasi Objek.

* **Konsep OOP:**
    * **Inheritance:** `MenuItem` sebagai parent class, dengan child class `Makanan`, `Minuman`, dan `Diskon`.
    * **Abstraction:** `MenuItem` adalah *abstract class* yang memaksa subclass mengimplementasikan method `tampilMenu()`.
    * **Encapsulation:** Penggunaan *private access modifiers* dan *Getter/Setter*.
    * **Polymorphism:** Perbedaan perilaku method `tampilMenu()` pada setiap jenis item.
* **File I/O:**
    * Menu dimuat dari file `menu_restoran.txt`.
    * Struk pembayaran disimpan secara otomatis ke dalam folder `struk_pesanan/` dengan format `.txt`.

---

## 🛠️ Teknologi yang Digunakan

* **Bahasa:** Java (JDK 8 atau lebih baru)
* **IDE/Editor:** Visual Studio Code
* **Format Data:** Text File (.txt) untuk penyimpanan data sederhana.

---

## 🚀 Cara Menjalankan Project

Pastikan Anda sudah menginstall Java Development Kit (JDK) dan berada di direktori root project (folder tempat `src` berada).

### Menjalankan Tugas 1
Tugas 1 menggunakan package `tugas1`.
1.  **Compile:**
    ```bash
    javac -d bin src/tugas1/*.java
    ```
2.  **Run:**
    ```bash
    java -cp bin tugas1.Main
    ```

### Menjalankan Tugas 2
Tugas 2 menggunakan package `tugas2`.
1.  **Compile:**
    ```bash
    javac -d bin src/tugas2/*.java
    ```
2.  **Run:**
    ```bash
    java -cp bin tugas2.Main
    ```

### Menjalankan Tugas 3
Tugas 3 menggunakan package `tugas3`.
1.  **Compile:**
    ```bash
    javac -d bin src/tugas3/*.java
    ```
2.  **Run:**
    ```bash
    java -cp bin tugas3.Main
    ```

---

## 📸 Contoh Penggunaan

### 1. Contoh Interaksi Tugas 1 (Input Angka)
Pada Tugas 1, interaksi dilakukan dengan memilih nomor menu secara bertahap. 
```text
Daftar Menu Restoran:
------------------------------------------------------------------
MAKANAN:
1. Nasi Ayam Pop        : Rp.35000
...
------------------------------------------------------------------
Pilih angka menu yang ingin dipesan: 1
Berapa banyak porsi? 2
Pesan lagi? (ya/tidak): tidak

[INFO] Total belanja Anda > Rp 50.000
Selamat! Anda berhak Promo GRATIS 2 Minuman.
Ambil promo? (ya/tidak): ya
```


## 2. Contoh Interaksi TUGAS 2 (Input Format String)
Pada Tugas 2, interaksi dilakukan dengan nenasukan nama menu lalu masukan jumlah menu yang dipesan (Nasi Rendang = 2). Selain itu, bisa melakukan Manajemen Menu seperti Tambah Menu, Edit menu, atau Hapus Menu.
```text
Daftar Menu:
1. Nasi Ayam Pop, Harga: Rp.35000, Kategori: Makanan
...

Masukkan pesanan (Format: Nama Menu = Jumlah): Nasi Rendang = 2
✓ Nasi Rendang berhasil ditambahkan.

Masukkan pesanan (Format: Nama Menu = Jumlah): Es Teh = 1
✓ Es Teh berhasil ditambahkan.

========================================================================

```
## 3. Contoh Interaksi TUGAS 3 (Input Angka)
Pada Tugas 3, interaksi dilakukan dengan memilih nomor menu secara bertahap. Selain itu, bisa melakukan Manajemen Menu seperti Tambah Menu, Edit menu, atau Hapus Menu. Kemudian Struk Pesanan bisa disimpan di folder `struk_pesanan/` dengan format `.txt`.
```text
========== MENU UTAMA ==========
1. Manajemen Menu (Tambah/Edit/Hapus)
2. Tampilkan Daftar Menu
3. Terima Pesanan Pelanggan
4. Hitung Total Biaya Pesanan
5. Tampilkan & Simpan Struk Pesanan
6. Simpan Menu ke File
7. Keluar
================================
Pilih menu (1-7): 3

=== TERIMA PESANAN ===
1. Nasi Ayam Pop          | Rp.35000     | Jenis: Ayam
2. Jus Alpukat            | Rp.15000     | Jenis: Jus
...
Ketik 'selesai' untuk mengakhiri pesanan
Pilih nomor menu: 1
Jumlah pesanan: 2
```

