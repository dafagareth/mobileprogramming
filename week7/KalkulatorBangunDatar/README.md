# Kalkulator Bangun Datar

Aplikasi Android Java + XML untuk menghitung luas dan keliling persegi panjang.

## Fitur

- Input panjang dan lebar dalam sentimeter.
- Angka desimal dengan koma atau titik, tanpa pemisah ribuan (maksimal 20 karakter per kolom).
- Hasil luas dalam cm² dan keliling dalam cm, beserta rumus dan substitusi angka.
- Validasi kolom kosong, angka nol, dan format angka yang salah.
- Tombol Kosongkan untuk menghapus input, hasil, dan pesan kesalahan.
- Tombol Done pada keyboard untuk menghitung.
- Hasil dibersihkan saat input berubah dan dipulihkan setelah Activity dibuat ulang, termasuk saat layar diputar.
- Tampilan mengikuti tema terang/gelap perangkat dan dapat digulir.

Perhitungan memakai `BigDecimal` agar operasi desimal tidak terkena pembulatan tipe `double`.

## Menjalankan

Buka folder ini di Android Studio, tunggu sinkronisasi Gradle selesai, lalu pilih Run.
Atau gunakan terminal:

```sh
./gradlew assembleDebug
```

Lihat [persyaratan build dan lokasi APK](../../README.md). Aplikasi mendukung Android 7.0 (API 24) ke atas.

## Berkas utama

- `app/src/main/java/com/dd/kalkulatorbangundatar/MainActivity.java`: validasi, perhitungan, dan aksi tombol.
- `app/src/main/res/layout/activity_main.xml`: formulir dan hasil.
- `app/src/main/res/values/strings.xml`: teks antarmuka.
- `app/src/test/java/com/dd/kalkulatorbangundatar/MainActivityTest.java`: pengujian otomatis dengan Robolectric.

## Pemeriksaan

```sh
./gradlew testDebugUnitTest lintDebug assembleDebug
```

Tes memeriksa desimal, input tidak valid, angka kecil, aksi keyboard, tombol Kosongkan,
perubahan input, dan pemulihan hasil setelah Activity dibuat ulang. Pemeriksaan pada
perangkat fisik tetap diperlukan untuk menilai tampilan dan perilaku keyboard perangkat.
