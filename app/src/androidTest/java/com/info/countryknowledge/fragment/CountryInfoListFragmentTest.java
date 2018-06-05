package com.info.countryknowledge.fragment;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;

import android.support.test.espresso.ViewAction;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.FrameLayout;

import com.info.countryknowledge.MainActivity;
import com.info.countryknowledge.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import butterknife.ButterKnife;

import static org.junit.Assert.*;

/**
 * Created by Sravanthi_B01 on 6/5/2018.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class CountryInfoListFragmentTest {
    @Rule
    public ActivityTestRule<MainActivity> activityActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);
    private MainActivity mMainActivity = null;


    @Before
    public void setUp() throws Exception {
        mMainActivity = activityActivityTestRule.getActivity();
        mMainActivity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                CountryInfoListFragment fragment = new CountryInfoListFragment();
                mMainActivity.getSupportFragmentManager().beginTransaction()
                        .add(R.id.fragment_container, fragment).commit();
            }
        });
    }

    @Test
    public void checkFragmentTitle()  {
       onView(withText("Country")).check(matches(isDisplayed()));
    }

    @After
    public void tearDown() throws Exception {
        mMainActivity = null;
    }



}