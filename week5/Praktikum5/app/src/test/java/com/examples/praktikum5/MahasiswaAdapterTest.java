package com.examples.praktikum5;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import android.view.View;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.android.controller.ActivityController;
import org.robolectric.annotation.Config;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = 35, qualifiers = "w320dp-h480dp")
public class MahasiswaAdapterTest {
    @Test
    public void reusingARowReplacesAllThreeStudentFields() {
        try (ActivityController<MainActivity> controller =
                     Robolectric.buildActivity(MainActivity.class).setup()) {
            ListView list = controller.get().findViewById(R.id.listViewMahasiswa);
            ListAdapter adapter = list.getAdapter();
            assertEquals(5, adapter.getCount());

            View firstRow = adapter.getView(0, null, list);
            TextView name = firstRow.findViewById(R.id.teksNama);
            assertEquals("Nama: Aulia", name.getText().toString());

            View reusedRow = adapter.getView(4, firstRow, list);
            assertSame(firstRow, reusedRow);
            TextView nim = reusedRow.findViewById(R.id.teksNIM);
            TextView department = reusedRow.findViewById(R.id.teksJurusan);
            assertEquals("NIM: 2210115262001", nim.getText().toString());
            assertEquals("Nama: Eko", name.getText().toString());
            assertEquals("Jurusan: Sistem Komputer", department.getText().toString());
        }
    }
}
