# Praktikum 4: UI Widget dan Toast

Menggunakan Java, layout XML, dan widget bawaan Android sesuai modul dosen.

## Perilaku

Tombol **Kirim** memeriksa nama, jenis kelamin, lalu checkbox **Setuju**.
Kesalahan pertama ditampilkan lewat Toast singkat, dengan teks persis seperti modul.
Nama yang hanya berisi spasi juga dianggap kosong.
Jika valid, Toast panjang menampilkan `Nama: ...` dan `Gender: ...` dalam dua baris.
Jenis kelamin belum dipilih ketika aplikasi pertama kali dibuka agar validasinya dapat dicoba.

## Tampilan

Acuan: contoh halaman 3 modul dosen. Latar hijau, judul di atas formulir,
pilihan Pria/Wanita vertikal, checkbox Setuju, dan tombol Kirim di tengah.
Tema terang dipertahankan agar mengikuti contoh. Font bawaan Android menjaga
keterbacaan dan konsistensi widget. Warna hijau tua membedakan kontrol dari latar.
ENERGY 2 / RHYTHM 1 / MOTION 1: warna mengikuti modul, susunan satu kolom,
dan hanya umpan balik interaksi bawaan. Formulir dapat digulir pada layar pendek.

## Build dan pengujian

Lihat [persyaratan build](../../README.md). Jalankan perintah ini dari folder proyek.

```sh
./gradlew assembleDebug testDebugUnitTest lintDebug
```

APK: `app/build/outputs/apk/debug/app-debug.apk`.
Tes Robolectric menjalankan Activity dan klik kontrol tanpa memerlukan emulator.
Untuk membuka aplikasi di perangkat, pilih **Run** di Android Studio.

## File utama

- `app/src/main/res/layout/activity_main.xml`: susunan widget.
- `app/src/main/java/com/examples/praktikum4/MainActivity.java`: klik dan validasi.
- `app/src/main/res/values/strings.xml`: teks formulir dan Toast.
- `app/src/test/java/com/examples/praktikum4/MainActivityTest.java`: tes interaksi formulir.
