package com.info.countryknowledge.model;

import java.util.List;

/**
 * Created by Sravanthi_B01 on 5/30/2018.
 *
 * This class represents the country info response data
 */

public class CountryInfo {

    private String title;
    private List<RowData> rows;

    public String getTitle() {
        return title;
    }

    public List<RowData> getRows() {
        return rows;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setRows(List<RowData> rows) {
        this.rows = rows;
    }
}
