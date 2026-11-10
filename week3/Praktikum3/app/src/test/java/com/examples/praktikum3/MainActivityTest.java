package com.examples.praktikum3;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.robolectric.Shadows.shadowOf;

import android.content.Intent;
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
    public void nextButtonOpensRegisteredSecondActivity() {
        try (ActivityController<MainActivity> main =
                     Robolectric.buildActivity(MainActivity.class).setup()) {
            assertTrue(main.get().findViewById(R.id.btnNext).performClick());
            Intent next = shadowOf(main.get()).getNextStartedActivity();
            assertEquals(SecondActivity.class.getName(), next.getComponent().getClassName());
            assertEquals(next.getComponent(), next.resolveActivity(main.get().getPackageManager()));

            try (ActivityController<SecondActivity> second =
                         Robolectric.buildActivity(SecondActivity.class, next).setup()) {
                TextView message = second.get().findViewById(R.id.textView1);
                assertEquals(second.get().getString(R.string.second_activity_message),
                        message.getText().toString());
            }
        }
    }
}
