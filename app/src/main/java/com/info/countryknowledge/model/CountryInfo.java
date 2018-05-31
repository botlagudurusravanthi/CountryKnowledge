package com.info.countryknowledge.model;

/**
 * Created by Sravanthi_B01 on 5/30/2018.
 *
 * This class represents the country info response data
 */

public class CountryInfo {

    private String title;
    private RowData rows[];

    public String getTitle() {
        return title;
    }

    public RowData[] getRows() {
        return rows;
    }

}
