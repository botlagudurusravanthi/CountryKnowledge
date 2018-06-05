package com.info.countryknowledge.model;

/**
 * Created by Sravanthi_B01 on 5/30/2018.
 *  Country info row data.
 */

public class RowData {

    private String title;
    private String description;
    private String imageHref;

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getImageHref() {
        //Few images with http:// not loading like beavers
        if(imageHref != null) {
            imageHref = imageHref.replaceFirst("http://", "https://");
        }
        return imageHref;
    }
}
