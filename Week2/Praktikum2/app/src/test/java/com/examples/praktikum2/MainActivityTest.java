package com.examples.praktikum2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import android.widget.TextView;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.android.controller.ActivityController;
import org.robolectric.annotation.Config;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = 35, qualifiers = "w320dp-h480dp")
public class MainActivityTest {
    @Test
    public void clickChangesNameOutputAndRecreationPreservesIt() {
        try (ActivityController<MainActivity> controller =
                     Robolectric.buildActivity(MainActivity.class).setup()) {
            MainActivity activity = controller.get();
            TextView output = activity.findViewById(R.id.textView2);
            assertEquals(activity.getString(R.string.name_prompt), output.getText().toString());
            assertTrue(activity.findViewById(R.id.button1).performClick());
            assertEquals(activity.getString(R.string.name_result), output.getText().toString());

            MainActivity recreated = controller.recreate().get();
            TextView restoredOutput = recreated.findViewById(R.id.textView2);
            assertEquals(recreated.getString(R.string.name_result), restoredOutput.getText().toString());
        }
    }
}
