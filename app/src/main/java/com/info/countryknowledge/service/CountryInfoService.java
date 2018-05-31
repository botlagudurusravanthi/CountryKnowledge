package com.info.countryknowledge.service;

import com.info.countryknowledge.model.CountryInfo;

import retrofit2.http.GET;
import retrofit2.Call;

/**
 * Created by Sravanthi_B01 on 5/31/2018.
 */

public interface CountryInfoService {

    @GET("facts.json")
    Call<CountryInfo> getCountryInfo();
}
