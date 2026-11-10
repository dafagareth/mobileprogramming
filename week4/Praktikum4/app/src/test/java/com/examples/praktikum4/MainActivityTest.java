package com.examples.praktikum4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.android.controller.ActivityController;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowToast;

@RunWith(RobolectricTestRunner.class)
@Config(sdk = 35)
public class MainActivityTest {
    private ActivityController<MainActivity> controller;
    private EditText name;
    private RadioGroup gender;
    private CheckBox agreement;
    private Button submit;

    @Before
    public void setUp() {
        controller = Robolectric.buildActivity(MainActivity.class).setup();
        MainActivity activity = controller.get();
        name = activity.findViewById(R.id.editName);
        gender = activity.findViewById(R.id.radioGroup);
        agreement = activity.findViewById(R.id.checkAgree);
        submit = activity.findViewById(R.id.btnSubmit);
    }

    @After
    public void tearDown() {
        controller.pause().stop().destroy();
    }

    @Test
    public void formStartsEmpty() {
        assertEquals("", name.getText().toString());
        assertEquals(-1, gender.getCheckedRadioButtonId());
        assertFalse(agreement.isChecked());
    }

    @Test
    public void emptyNameShowsFirstError() {
        submit.performClick();

        assertEquals("Nama tidak boleh kosong!", ShadowToast.getTextOfLatestToast());
        assertEquals(Toast.LENGTH_SHORT, ShadowToast.getLatestToast().getDuration());
    }

    @Test
    public void whitespaceNameIsRejectedEvenWhenOtherFieldsAreValid() {
        name.setText("   ");
        gender.check(R.id.radioMale);
        agreement.performClick();
        submit.performClick();

        assertEquals("Nama tidak boleh kosong!", ShadowToast.getTextOfLatestToast());
    }

    @Test
    public void missingGenderIsCheckedBeforeAgreement() {
        name.setText("Budi");
        submit.performClick();

        assertEquals("Pilih jenis kelamin!", ShadowToast.getTextOfLatestToast());
    }

    @Test
    public void uncheckedAgreementShowsError() {
        name.setText("Budi");
        gender.check(R.id.radioMale);
        submit.performClick();

        assertEquals("Anda harus setuju!", ShadowToast.getTextOfLatestToast());
    }

    @Test
    public void validMaleFormShowsTwoLineLongToast() {
        name.setText("  Budi Santoso  ");
        controller.get().findViewById(R.id.radioMale).performClick();
        agreement.performClick();
        submit.performClick();

        assertEquals("Nama: Budi Santoso\nGender: Pria", ShadowToast.getTextOfLatestToast());
        assertEquals(Toast.LENGTH_LONG, ShadowToast.getLatestToast().getDuration());
    }

    @Test
    public void changingGenderKeepsOnlyOneChoiceAndUpdatesToast() {
        name.setText("Siti");
        RadioButton male = controller.get().findViewById(R.id.radioMale);
        RadioButton female = controller.get().findViewById(R.id.radioFemale);
        male.performClick();
        female.performClick();
        agreement.performClick();
        submit.performClick();

        assertFalse(male.isChecked());
        assertTrue(female.isChecked());
        assertEquals("Nama: Siti\nGender: Wanita", ShadowToast.getTextOfLatestToast());
    }

    @Test
    public void revokingAgreementRequiresConsentAgain() {
        name.setText("Siti");
        gender.check(R.id.radioFemale);
        agreement.performClick();
        agreement.performClick();
        submit.performClick();

        assertEquals("Anda harus setuju!", ShadowToast.getTextOfLatestToast());
    }
}
