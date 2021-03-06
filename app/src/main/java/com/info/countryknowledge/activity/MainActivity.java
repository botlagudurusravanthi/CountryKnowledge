package com.info.countryknowledge;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;


import com.info.countryknowledge.fragment.CountryInfoListFragment;

import java.io.File;

//This is mainclass which launches the fragment.
public class MainActivity extends AppCompatActivity {

    private CountryInfoListFragment countryInfoListFragment;
    public static File cacheDir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Adding the ListView Fragment to MainActivity
        * This also queries data for the first time on activity creation
        * on Orientation change, fragment's old instance is reused */
        if (findViewById(R.id.fragment_container) != null) {
            countryInfoListFragment = (CountryInfoListFragment)getSupportFragmentManager().findFragmentByTag(getResources().getString(R.string.list_fragment_tag));
            if (countryInfoListFragment == null) {
                initiateFragment();
            }
        }
    }


    // This methods creates a new instance of CountryListViewFragment and attaches it to MainActivity
    private void initiateFragment() {
        countryInfoListFragment = new CountryInfoListFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, countryInfoListFragment, getResources().getString(R.string.list_fragment_tag))
                .commit();
        cacheDir = getCacheDir();
    }
    //Set the Action bar title
    public void setActionBarTitle(String title) {
        getSupportActionBar().setTitle(title);
    }
 }
