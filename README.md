# 🐤 Flappy Bird Java

Proyek ini merupakan implementasi sederhana dari game **Flappy Bird** menggunakan bahasa pemrograman **Java** dengan library **Swing** dan **AWT**.  
Game ini dibuat untuk melatih logika pergerakan objek (player, pipa, gravitasi), deteksi tabrakan, serta pengelolaan tampilan grafis dasar di Java.

---

## 🎨 Desain Program

### Komponen Utama:

1. **Model**
   - `Player.java` dan `Pipe.java` menyimpan data dan status setiap objek game.
2. **View**
   - `View.java` bertanggung jawab untuk menggambar semua elemen di layar.
3. **Controller / Logic**
   - `Logic.java` mengatur perilaku permainan seperti:
     - Gravitasi dan lompatan burung
     - Pergerakan pipa
     - Deteksi tabrakan
     - Perhitungan skor
     - Restart dan kondisi game over
4. **Menu Awal**
   - `MainMenuForm.java` sebagai menu pembuka yang memanggil `App` ketika tombol _Mainkan_ ditekan.

| File                          | Deskripsi                                                                                                    |
| ----------------------------- | ------------------------------------------------------------------------------------------------------------ |
| **App.java**                  | Kelas utama untuk menjalankan permainan (membuat JFrame dan memuat `View`).                                  |
| **MainMenuForm.java / .form** | Tampilan menu utama sebelum permainan dimulai (Play dan Exit). `.form` digunakan oleh IntelliJ GUI Designer. |
| **Logic.java**                | Mengatur seluruh logika permainan seperti gravitasi, gerakan pipa, deteksi tabrakan, dan skor.               |
| **View.java**                 | Bertanggung jawab untuk menggambar (render) objek di layar: latar belakang, burung, pipa, dan teks.          |
| **Player.java**               | Kelas model untuk objek burung (posisi, kecepatan, dan gambar).                                              |
| **Pipe.java**                 | Kelas model untuk objek pipa (posisi, status “passed”, dan gambar).                                          |

---

## ⚙️ Alur Program

1. **Tampilan Awal**
   - Game dimulai dalam keadaan _idle_ (burung diam di tengah).
   - Pemain menekan **spasi** untuk mulai bermain.

2. **Gameplay**
   - Setelah spasi ditekan, **gravitasi aktif**, dan player mulai jatuh.
   - Setiap kali spasi ditekan, player “terbang” sedikit ke atas.
   - Pipa bergerak dari kanan ke kiri, dan pemain harus melewati celah di antara pipa tanpa menabrak.

3. **Skor**
   - Skor bertambah setiap kali player berhasil melewati satu pipa.

4. **Game Over**
   - Jika player menabrak pipa atau tanah, status berubah menjadi `gameOver = true`.
   - Muncul tulisan **"GAME OVER"** di layar.
   - Pemain bisa menekan tombol tertentu (misalnya Enter) untuk restart.

---

## 🎮 Kontrol Permainan

| Tombol                 | Fungsi                                |
| ---------------------- | ------------------------------------- |
| **Spasi**              | Membuat burung melompat ke atas       |
| **R**                  | Restart permainan (setelah Game Over) |
| **Esc / Close Window** | Keluar dari permainan                 |

---

## 📊 Dokumentasi Program

https://github.com/user-attachments/assets/30bf8b43-685d-41f4-b19e-6177db92d860

