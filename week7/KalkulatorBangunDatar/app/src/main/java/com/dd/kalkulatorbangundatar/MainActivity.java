package com.dd.kalkulatorbangundatar;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import java.math.BigDecimal;

public class MainActivity extends AppCompatActivity {
    private EditText inputLength;
    private EditText inputWidth;
    private TextView result;
    private TextView perimeterResult;
    private boolean hasResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inputLength = findViewById(R.id.inputLength);
        inputWidth = findViewById(R.id.inputWidth);
        result = findViewById(R.id.tvResult);
        perimeterResult = findViewById(R.id.tvPerimeter);
        ViewCompat.setAccessibilityHeading(findViewById(R.id.tvTitle), true);
        ViewCompat.setAccessibilityHeading(findViewById(R.id.tvResultTitle), true);

        TextWatcher clearResult = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                hasResult = false;
                result.setText(R.string.area_empty);
                perimeterResult.setText(R.string.perimeter_empty);
            }

            @Override
            public void afterTextChanged(Editable s) {}
        };
        inputLength.addTextChangedListener(clearResult);
        inputWidth.addTextChangedListener(clearResult);

        findViewById(R.id.btnCalculate).setOnClickListener(view -> calculate());
        findViewById(R.id.btnReset).setOnClickListener(view -> {
            inputLength.setText("");
            inputWidth.setText("");
            inputLength.setError(null);
            inputWidth.setError(null);
            inputLength.requestFocus();
        });
        inputWidth.setOnEditorActionListener((view, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                calculate();
                return true;
            }
            return false;
        });
    }

    private void calculate() {
        BigDecimal length = readDimension(inputLength);
        BigDecimal width = readDimension(inputWidth);
        if (length == null || width == null) {
            hasResult = false;
            result.setText(R.string.area_empty);
            perimeterResult.setText(R.string.perimeter_empty);
            (length == null ? inputLength : inputWidth).requestFocus();
            return;
        }

        BigDecimal area = length.multiply(width);
        BigDecimal perimeter = length.add(width).multiply(BigDecimal.valueOf(2));
        result.setText(getString(R.string.area_result, format(length), format(width), format(area)));
        perimeterResult.setText(getString(R.string.perimeter_result,
                format(length), format(width), format(perimeter)));
        hasResult = true;
    }

    private BigDecimal readDimension(EditText input) {
        input.setError(null);
        String text = input.getText().toString().trim();
        if (text.isEmpty()) {
            input.setError(getString(R.string.required_input));
            return null;
        }
        if (text.matches("(?:[0-9]+(?:[.,][0-9]*)?|[.,][0-9]+)")) {
            BigDecimal value = new BigDecimal(text.replace(',', '.'));
            if (value.signum() > 0) {
                return value;
            }
        }
        input.setError(getString(R.string.invalid_input));
        return null;
    }

    private String format(BigDecimal value) {
        return value.stripTrailingZeros().toPlainString().replace('.', ',');
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putBoolean("hasResult", hasResult);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState.getBoolean("hasResult")) {
            calculate();
        }
    }
}
