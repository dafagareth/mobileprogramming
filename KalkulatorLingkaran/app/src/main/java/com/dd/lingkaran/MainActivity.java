package com.dd.lingkaran;

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
    private EditText[] inputs;
    private TextView areaResult;
    private TextView perimeterResult;
    private boolean hasResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        areaResult = findViewById(R.id.tvResult);
        perimeterResult = findViewById(R.id.tvPerimeter);
        ViewCompat.setAccessibilityHeading(findViewById(R.id.tvTitle), true);
        ViewCompat.setAccessibilityHeading(findViewById(R.id.tvResultTitle), true);

        TextWatcher clearOnEdit = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                clearResult();
            }

            @Override
            public void afterTextChanged(Editable s) {}
        };

        int[] fieldIds = {R.id.inputRadius};
        inputs = new EditText[fieldIds.length];
        for (int i = 0; i < fieldIds.length; i++) {
            inputs[i] = findViewById(fieldIds[i]);
            inputs[i].addTextChangedListener(clearOnEdit);
        }

        findViewById(R.id.btnCalculate).setOnClickListener(view -> calculate());
        findViewById(R.id.btnReset).setOnClickListener(view -> {
            for (EditText input : inputs) {
                input.setText("");
                input.setError(null);
            }
            inputs[0].requestFocus();
        });
        inputs[inputs.length - 1].setOnEditorActionListener((view, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                calculate();
                return true;
            }
            return false;
        });
    }

    private void calculate() {
        BigDecimal[] dimensions = new BigDecimal[inputs.length];
        EditText firstInvalid = null;
        for (int i = 0; i < inputs.length; i++) {
            dimensions[i] = readDimension(inputs[i]);
            if (dimensions[i] == null && firstInvalid == null) {
                firstInvalid = inputs[i];
            }
        }
        if (firstInvalid != null) {
            clearResult();
            firstInvalid.requestFocus();
            return;
        }

        ShapeCalculator.Measurements measurements = ShapeCalculator.calculate(dimensions);
        areaResult.setText(getString(R.string.area_result,
                format(dimensions[0]), format(measurements.area)));
        perimeterResult.setText(getString(R.string.perimeter_result,
                format(dimensions[0]), format(measurements.perimeter)));
        hasResult = true;
    }

    private void clearResult() {
        hasResult = false;
        areaResult.setText(R.string.area_empty);
        perimeterResult.setText(R.string.perimeter_empty);
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
