package com.examples.praktikum5;

import android.os.Bundle;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import com.examples.praktikum5.MahasiswaAdapter.Mahasiswa;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Mahasiswa[] daftarMahasiswa = {
                new Mahasiswa("2310115260001", "Aulia", "Manajemen Informatika"),
                new Mahasiswa("2110115261001", "Budi", "Sistem Informasi"),
                new Mahasiswa("2110115262001", "Citra", "Sistem Komputer"),
                new Mahasiswa("2210115263001", "Dian", "Teknik Informatika"),
                new Mahasiswa("2210115262001", "Eko", "Sistem Komputer")
        };
        ListView listView = findViewById(R.id.listViewMahasiswa);
        listView.setAdapter(new MahasiswaAdapter(this, daftarMahasiswa));
    }
}
