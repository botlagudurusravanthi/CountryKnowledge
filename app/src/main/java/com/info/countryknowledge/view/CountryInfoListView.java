package com.info.countryknowledge.view;

import com.info.countryknowledge.model.CountryInfo;

/**
 * Created by Sravanthi_B01 on 5/30/2018.
 */

public interface  CountryInfoListView {

    void updateTitle(String title);

    void showListView(CountryInfo countryInfo);

    void showErrorView(String message);
}
