package com.dd.kalkulatorbangundatar;

import static org.junit.Assert.*;

import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.android.controller.ActivityController;
import org.robolectric.annotation.Config;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = 35)
public class MainActivityTest {
    @Test
    public void calculatesAreaAndPerimeterWithBothDecimalSeparators() {
        try (ActivityController<MainActivity> controller = Robolectric.buildActivity(MainActivity.class).setup()) {
            MainActivity activity = controller.get();
            fill(activity, "12,5", "4.2");
            activity.findViewById(R.id.btnCalculate).performClick();
            String result = result(activity);
            assertTrue(result.contains("52,5 cm²"));
            assertTrue(result.contains("33,4 cm"));
            assertTrue(((TextView) activity.findViewById(R.id.tvResult)).getText().toString()
                    .startsWith("Luas:"));
            assertTrue(((TextView) activity.findViewById(R.id.tvPerimeter)).getText().toString()
                    .startsWith("Keliling:"));
        }
    }

    @Test
    public void rejectsEmptyZeroAndMalformedInput() {
        try (ActivityController<MainActivity> controller = Robolectric.buildActivity(MainActivity.class).setup()) {
            MainActivity activity = controller.get();
            for (String invalid : new String[] {"", "0", "0,0", "1..2", "1,2.3"}) {
                fill(activity, invalid, "4");
                activity.findViewById(R.id.btnCalculate).performClick();
                EditText length = activity.findViewById(R.id.inputLength);
                assertNotNull("Input: " + invalid, length.getError());
                assertEquals(emptyResult(activity), result(activity));
            }
            fill(activity, "4", "0");
            activity.findViewById(R.id.btnCalculate).performClick();
            EditText width = activity.findViewById(R.id.inputWidth);
            assertNotNull(width.getError());
            assertTrue(width.hasFocus());
        }
    }

    @Test
    public void handlesSmallDecimalsWithoutRoundingToZero() {
        try (ActivityController<MainActivity> controller = Robolectric.buildActivity(MainActivity.class).setup()) {
            MainActivity activity = controller.get();
            fill(activity, "0.00001", "0.00001");
            activity.findViewById(R.id.btnCalculate).performClick();
            assertTrue(result(activity).contains("0,0000000001 cm²"));
        }
    }

    @Test
    public void keyboardDoneCalculatesAndEditingClearsOldResult() {
        try (ActivityController<MainActivity> controller = Robolectric.buildActivity(MainActivity.class).setup()) {
            MainActivity activity = controller.get();
            fill(activity, "10", "5");
            EditText width = activity.findViewById(R.id.inputWidth);
            width.onEditorAction(EditorInfo.IME_ACTION_DONE);
            assertTrue(result(activity).contains("50 cm²"));
            width.setText("6");
            assertEquals(emptyResult(activity), result(activity));
        }
    }

    @Test
    public void resetClearsValuesResultsAndErrors() {
        try (ActivityController<MainActivity> controller = Robolectric.buildActivity(MainActivity.class).setup()) {
            MainActivity activity = controller.get();
            fill(activity, "10", "5");
            activity.findViewById(R.id.btnCalculate).performClick();
            activity.findViewById(R.id.btnReset).performClick();
            EditText length = activity.findViewById(R.id.inputLength);
            EditText width = activity.findViewById(R.id.inputWidth);
            assertEquals("", length.getText().toString());
            assertEquals("", width.getText().toString());
            assertEquals(emptyResult(activity), result(activity));
            activity.findViewById(R.id.btnCalculate).performClick();
            activity.findViewById(R.id.btnReset).performClick();
            assertNull(length.getError());
            assertNull(width.getError());
        }
    }

    @Test
    public void preservesInputsAndResultWhenActivityIsRecreated() {
        try (ActivityController<MainActivity> controller = Robolectric.buildActivity(MainActivity.class).setup()) {
            MainActivity activity = controller.get();
            fill(activity, "10", "5");
            activity.findViewById(R.id.btnCalculate).performClick();
            controller.recreate();
            assertTrue(result(controller.get()).contains("50 cm²"));
            EditText length = controller.get().findViewById(R.id.inputLength);
            assertEquals("10", length.getText().toString());
        }
    }

    private void fill(MainActivity activity, String length, String width) {
        ((EditText) activity.findViewById(R.id.inputLength)).setText(length);
        ((EditText) activity.findViewById(R.id.inputWidth)).setText(width);
    }

    private String result(MainActivity activity) {
        return ((TextView) activity.findViewById(R.id.tvResult)).getText().toString()
                + "\n\n" + ((TextView) activity.findViewById(R.id.tvPerimeter)).getText();
    }

    private String emptyResult(MainActivity activity) {
        return activity.getString(R.string.area_empty) + "\n\n"
                + activity.getString(R.string.perimeter_empty);
    }
}
