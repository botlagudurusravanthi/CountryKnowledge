package com.info.countryknowledge.fragment;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.swipeDown;
import static android.support.test.espresso.action.ViewActions.swipeUp;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.Visibility.VISIBLE;
import static android.support.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.allOf;

import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.ViewAction;
//import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v7.widget.RecyclerView;
import android.widget.FrameLayout;

import com.info.countryknowledge.MainActivity;
import com.info.countryknowledge.R;
import com.info.countryknowledge.RestServiceTestHelper;
import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.MockWebServer;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import butterknife.ButterKnife;

import static org.hamcrest.CoreMatchers.not;
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
    }

    @Test
    public void checkFragmentTitle()  {
       onView(withText(mMainActivity.getSupportActionBar().getTitle().toString())).check(matches(isDisplayed()));

    }


    @Test
    public void pullToRefresh_shouldPass() throws Exception {
        onView(withId(R.id.swiperefresh)).perform(swipeDown());
    }

    @Test
    public void loadMore_shouldPass() throws Exception {
         onView(withId(R.id.recycler_list)).perform(RecyclerViewActions.scrollToPosition(8)).perform(swipeUp());
    }

    @Test
    public void orientation_support() throws Exception {
        onView(withId(R.id.recycler_list)).check(matches(isDisplayed()));
        rotateScreen();
        onView(withId(R.id.recycler_list)).check(matches(isDisplayed()));
    }

    @After
    public void tearDown() throws Exception {
        mMainActivity = null;
    }
    private void rotateScreen() {
        Context context = InstrumentationRegistry.getTargetContext();
        int orientation = context.getResources().getConfiguration().orientation;

        mMainActivity.setRequestedOrientation(
                (orientation == Configuration.ORIENTATION_PORTRAIT) ?
                        ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE : ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
    }
}