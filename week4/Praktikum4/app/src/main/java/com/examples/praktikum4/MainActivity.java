package com.examples.praktikum4;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText editName = findViewById(R.id.editName);
        final RadioGroup radioGroup = findViewById(R.id.radioGroup);
        final CheckBox checkAgree = findViewById(R.id.checkAgree);
        Button btnSubmit = findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editName.getText().toString().trim();
                int selectedGenderId = radioGroup.getCheckedRadioButtonId();

                if (name.isEmpty()) {
                    Toast.makeText(getApplicationContext(), R.string.error_name,
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                if (selectedGenderId == -1) {
                    Toast.makeText(getApplicationContext(), R.string.error_gender,
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                RadioButton selectedGender = findViewById(selectedGenderId);

                if (!checkAgree.isChecked()) {
                    Toast.makeText(getApplicationContext(), R.string.error_agreement,
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                String message = getString(R.string.success_message, name, selectedGender.getText());
                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_LONG).show();
            }
        });
    }
}
