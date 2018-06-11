package com.info.countryknowledge.adapter;

import com.info.countryknowledge.MainActivity;
import com.info.countryknowledge.service.CountryInfoService;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * This class creates the service to get the country data.
 */

public class CountryInfoListRetrofitAdapter {

    private static CountryInfoService countryInfoService = null;
    static final int cacheSize = 10 * 1024 * 1024; // 10 MB
    static Cache cache = new Cache(MainActivity.cacheDir, cacheSize);

    //Creating static retrofit builder object
    public static CountryInfoService getCountryInfoService(String url){
        if(countryInfoService==null){
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .cache(cache)
                    .build();
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            countryInfoService = retrofit.create(CountryInfoService.class);
        }
        return countryInfoService;
    }
}
