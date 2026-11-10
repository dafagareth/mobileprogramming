package com.examples.praktikum6;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView countries = findViewById(R.id.listViewNegara);
        TextView capital = findViewById(R.id.textViewIbukota);
        String[] capitals = getResources().getStringArray(R.array.capitals);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this, R.array.countries, android.R.layout.simple_list_item_1);
        countries.setAdapter(adapter);
        countries.setOnItemClickListener((parent, row, position, id) ->
                capital.setText(getString(R.string.capital_result,
                        adapter.getItem(position), capitals[position])));
    }
}
