package com.example.ravisundar.ullala_driver;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertEquals;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */


@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    public static final String USERNAME_TYPED = "7904934446";

    public static final String LOGIN_TEXT = "correct user";
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();

        assertEquals("com.example.ravisundar.ullala_driver", appContext.getPackageName());
    }
        @Rule
        public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(
                MainActivity.class);

    public void  loginClicedSucess(){

        onView(withId(R.id.number))
                .perform(typeText("7904934446"));
    }


}
