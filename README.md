# Pemrograman Berbasis Desktop ☕

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white) ![VS Code](https://img.shields.io/badge/Visual_Studio_Code-0078D4?style=for-the-badge&logo=visual%20studio%20code&logoColor=white) ![Status](https://img.shields.io/badge/Status-Active-success?style=for-the-badge)

Repository ini berisi kumpulan tugas praktikum untuk mata kuliah **Pemrograman Berbasis Desktop**. Project ini mendemonstrasikan progresi pembelajaran bahasa pemrograman Java mulai dari logika dasar, struktur data sederhana, hingga penerapan konsep *Object-Oriented Programming* (OOP) dan *File Handling*.

## 📂 Struktur Project

Repository ini dibagi menjadi beberapa paket (package) berdasarkan tugas praktikum:

- **`src/tugas1`**: Dasar pemrograman Java (Console App sederhana).
- **`src/tugas2`**: Manipulasi String, Collection (ArrayList), dan Logika Loop.
- **`src/tugas3`**: Implementasi OOP (Inheritance, Polymorphism, Encapsulation, Abstraction) dan File I/O.

---

## ✨ Fitur & Penjelasan Tugas

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

Pastikan Anda sudah menginstall Java Development Kit (JDK).
Apabila menggunakan Text Visual Studio Code, Pasang Extension **Pack For Java**

1.  **Clone Repository**
    ```bash
    git clone [https://github.com/Tpathurrohman612/pemrograman_berbasis_desktop.git](https://github.com/Tpathurrohman612/pemrograman_berbasis_desktop.git)
    cd pemrograman_berbasis_desktop
    ```

2.  **Compile Codingan**
    Disarankan melakukan compile dari root folder (`src`). Contoh untuk menjalankan **Tugas 3**:
    ```bash
    javac -d bin src/tugas3/*.java
    ```

3.  **Jalankan Program**
    ```bash
    java -cp bin tugas3.Main
    ```

---

## 📸 Contoh Penggunaan

### Contoh Input Pemesanan (Tugas 2)
Pada Tugas 2, sistem menerima input dengan format khusus:
```text
Masukkan pesanan: Nasi Goreng = 2
