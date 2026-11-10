package com.examples.praktikum6;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
public class MainActivityTest {
    @Test
    public void clickingCountryShowsItsCapitalAndRecreationPreservesIt() {
        try (ActivityController<MainActivity> controller =
                     Robolectric.buildActivity(MainActivity.class).setup()) {
            MainActivity activity = controller.get();
            ListView countries = activity.findViewById(R.id.listViewNegara);
            TextView capital = activity.findViewById(R.id.textViewIbukota);
            assertEquals(10, countries.getCount());
            assertEquals(activity.getString(R.string.capital_prompt), capital.getText().toString());

            int australiaPosition = 6;
            assertTrue(countries.performItemClick(null, australiaPosition, australiaPosition));
            String expected = "Negara Australia beribu kota di Canberra.";
            assertEquals(expected, capital.getText().toString());

            TextView restoredCapital = controller.recreate().get().findViewById(R.id.textViewIbukota);
            assertEquals(expected, restoredCapital.getText().toString());
        }
    }
}
