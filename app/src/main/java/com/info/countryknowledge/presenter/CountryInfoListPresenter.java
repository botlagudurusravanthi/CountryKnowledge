package com.info.countryknowledge.presenter;

import android.content.Context;
import android.util.Log;

import com.info.countryknowledge.adapter.CountryInfoListRetrofitAdapter;
import com.info.countryknowledge.model.CountryInfo;
import com.info.countryknowledge.view.CountryInfoListView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * This class query the data and update the view through CountryInfoListView
 */

public class CountryInfoListPresenter extends BasePresenter<CountryInfoListView>{

    public void swipeRefresh(String url){
        queryListData(url);
    }

   public void queryListData(String url){
       final Call<CountryInfo> responseData = CountryInfoListRetrofitAdapter.getCountryInfoService(url).getCountryInfo();
       responseData.enqueue(new Callback<CountryInfo>() {
           @Override
           public void onResponse(Call<CountryInfo> call, Response<CountryInfo> response) {
               CountryInfo restResponse = response.body();
               getView().showListView(restResponse);
               getView().updateTitle(restResponse.getTitle());
           }

           @Override
           public void onFailure(Call<CountryInfo> call, Throwable t) {
               getView().showErrorView(t.getMessage());
           }
       });

   }
}
