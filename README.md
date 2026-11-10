# Mobile Programming

Repositori ini berisi praktikum Android minggu 2–7 dan sembilan kalkulator bangun datar. Setiap aplikasi adalah proyek Java dan XML yang bisa dibuka dan dibangun secara terpisah. Modul PDF ada di folder minggu masing-masing.

## Build APK debug

Pasang JDK 21 dan Android SDK Platform 35. Untuk Android Studio, buka folder proyek yang berisi `settings.gradle`, lalu pilih JDK 21 sebagai **Gradle JDK**.

Untuk terminal, arahkan `JAVA_HOME` ke folder JDK 21 dan `ANDROID_HOME` ke folder Android SDK. Dari folder proyek, misalnya `KalkulatorPersegi/`, jalankan:

```sh
./gradlew assembleDebug
```

Di Windows, gunakan `gradlew.bat assembleDebug`. Gradle mengunduh dependensi saat build pertama. APK tersedia di `app/build/outputs/apk/debug/app-debug.apk`.
