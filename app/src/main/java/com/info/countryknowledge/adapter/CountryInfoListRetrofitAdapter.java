package com.info.countryknowledge.adapter;

import com.info.countryknowledge.service.CountryInfoService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * This class creates the service to get the country data.
 */

public class CountryInfoListRetrofitAdapter {

    private static CountryInfoService countryInfoService = null;

    //Creating static retrofit builder object
    public static CountryInfoService getCountryInfoService(String url){
        if(countryInfoService==null){
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            countryInfoService = retrofit.create(CountryInfoService.class);
        }
        return countryInfoService;
    }
}
