package com.info.countryknowledge.presenter;

import com.info.countryknowledge.model.CountryInfo;
import com.info.countryknowledge.service.CountryInfoService;
import com.info.countryknowledge.view.CountryInfoListView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.cglib.proxy.Callback;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Sravanthi_B01 on 6/4/2018.
 */
public class CountryInfoListPresenterTest {
    @Mock
    private CountryInfoListView countryInfoListView;
    CountryInfoListPresenter presenter;
    @Mock
    Call<CountryInfo> call;
    @Mock
    Response<CountryInfo> response;

    @Test
    public void testSuccessfulResponse()throws Exception{
       presenter = new CountryInfoListPresenter();
        CountryInfoService mockedApiInterface = Mockito.mock(CountryInfoService.class);
        final Call<CountryInfo> mockedCall = Mockito.mock(Call.class);
        when(mockedApiInterface.getCountryInfo()).thenReturn(mockedCall);

        Mockito.doAnswer(new Answer() {
            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable {
                CountryInfo countryInfo = new CountryInfo();
                countryInfo.setTitle("title");
                when(response.body()).thenReturn(countryInfo);
                retrofit2.Callback<CountryInfo> callback = invocation.getArgumentAt(0, retrofit2.Callback.class);
                callback.onResponse(mockedCall, Response.success(countryInfo));
                verify(presenter).getView().showListView(countryInfo);
                verify(presenter).getView().updateTitle(countryInfo.getTitle());
                Throwable t = new IOException();
                callback.onFailure(mockedCall, t);
                verify(presenter).getView().showErrorView(t.getMessage());
                return null;
            }
        }).when(mockedCall).enqueue((retrofit2.Callback<CountryInfo>) any(Callback.class));
}

    @Test
    public void testApiCall() throws Exception {
        CountryInfoService mockedApiInterface = Mockito.mock(CountryInfoService.class);
        final Call<CountryInfo> mockedCall = Mockito.mock(Call.class);

        when(mockedApiInterface.getCountryInfo()).thenReturn(mockedCall);

        Mockito.doAnswer(new Answer() {
            @Override
            public Void answer(InvocationOnMock invocation) throws Throwable {
                retrofit2.Callback<CountryInfo> callback = invocation.getArgumentAt(0, retrofit2.Callback.class);

                callback.onResponse(mockedCall, Response.success(new CountryInfo()));
               // callback.onResponse(mockedCall, Response.error(404. ...);
                 callback.onFailure(mockedCall, new IOException());

                return null;
            }
        }).when(mockedCall).enqueue((retrofit2.Callback<CountryInfo>) any(Callback.class));

    }

}