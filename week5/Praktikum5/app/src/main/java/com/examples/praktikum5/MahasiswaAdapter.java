package com.examples.praktikum5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class MahasiswaAdapter extends ArrayAdapter<MahasiswaAdapter.Mahasiswa> {
    static final class Mahasiswa {
        final String nim;
        final String nama;
        final String jurusan;

        Mahasiswa(String nim, String nama, String jurusan) {
            this.nim = nim;
            this.nama = nama;
            this.jurusan = jurusan;
        }
    }

    public MahasiswaAdapter(Context context, Mahasiswa[] daftarMahasiswa) {
        super(context, R.layout.list_item_mahasiswa, daftarMahasiswa);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        if (row == null) {
            row = LayoutInflater.from(getContext())
                    .inflate(R.layout.list_item_mahasiswa, parent, false);
        }

        Mahasiswa mahasiswa = getItem(position);
        TextView nim = row.findViewById(R.id.teksNIM);
        TextView nama = row.findViewById(R.id.teksNama);
        TextView jurusan = row.findViewById(R.id.teksJurusan);
        nim.setText(getContext().getString(R.string.student_id, mahasiswa.nim));
        nama.setText(getContext().getString(R.string.student_name, mahasiswa.nama));
        jurusan.setText(getContext().getString(R.string.student_department, mahasiswa.jurusan));
        return row;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int position) {
        return false;
    }
}
