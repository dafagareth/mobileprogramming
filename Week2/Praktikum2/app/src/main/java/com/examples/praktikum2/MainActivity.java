package com.examples.praktikum2;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView nameOutput = findViewById(R.id.textView2);
        findViewById(R.id.button1).setOnClickListener(view ->
                nameOutput.setText(R.string.name_result));
    }
}
