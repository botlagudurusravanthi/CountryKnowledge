package com.info.countryknowledge.activity;

import android.support.test.rule.ActivityTestRule;
import android.widget.FrameLayout;

import com.info.countryknowledge.MainActivity;
import com.info.countryknowledge.R;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Sravanthi_B01 on 6/3/2018.
 */
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> activityActivityTestRule = new ActivityTestRule<MainActivity>(MainActivity.class);
   private MainActivity mMainActivity = null;


    @Before
    public void setUp() throws Exception {
        mMainActivity = activityActivityTestRule.getActivity();
    }
    @Test
    public void testLaunch(){
        FrameLayout view = mMainActivity.findViewById(R.id.fragment_container);
        assertNotNull(view);
    }


    @After
    public void tearDown() throws Exception {
          mMainActivity = null;
    }

}