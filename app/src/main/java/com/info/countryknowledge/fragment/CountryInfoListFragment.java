package com.info.countryknowledge.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;

import com.info.countryknowledge.MainActivity;
import com.info.countryknowledge.R;
import com.info.countryknowledge.adapter.CountryInfoListAdapter;
import com.info.countryknowledge.model.CountryInfo;
import com.info.countryknowledge.model.RowData;
import com.info.countryknowledge.presenter.CountryInfoListPresenter;
import com.info.countryknowledge.view.CountryInfoListView;

/**
 * Created by Sravanthi_B01 on 5/29/2018.
 */

public class CountryInfoListFragment extends Fragment implements CountryInfoListView{

    @BindView(R.id.recycler_list)
    RecyclerView recyclerList;

    @BindView(R.id.swiperefresh)
    SwipeRefreshLayout swipeRefreshLayout;

    @BindView(R.id.error_message)
    TextView errorMessage;

    CountryInfoListPresenter countryInfoListPresenter;
    CountryInfoListAdapter adapter;
    Context context;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_fragment, container, false);
        ButterKnife.bind(this, view);
        errorMessage = view.findViewById(R.id.error_message);
        setRetainInstance(true);
        countryInfoListPresenter = new CountryInfoListPresenter();
        countryInfoListPresenter.queryListData(getString(R.string.country_info_url));
        context = this.getContext();
        ((MainActivity) getActivity()).setActionBarTitle("Country");
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
               countryInfoListPresenter.swipeRefresh(getString(R.string.country_info_url));
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.v("Fragment","Fragmen<<<<<<<<<<<<<<<<<<<<<<<<<<<   onActivityCreated    >>>>>>>>>>>>>>>>>>>>>");
        countryInfoListPresenter.setV(this);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void updateTitle(String title) {
        ((MainActivity) getActivity()).setActionBarTitle(title);
    }

    @Override
    public void showListView(CountryInfo countryInfo) {
       if(swipeRefreshLayout.isRefreshing()){
            swipeRefreshLayout.setRefreshing(false);
        }
        if(countryInfo != null) {
            setAdapter(countryInfo.getRows());
        }
    }

    @Override
    public void showErrorView(String message) {
        if(swipeRefreshLayout.isRefreshing()){
            swipeRefreshLayout.setRefreshing(false);
        }
        errorMessage.setText(message);
        errorMessage.setVisibility(View.VISIBLE);
    }

    private void setAdapter(RowData[] rowData){
        if(rowData != null){
            if(adapter == null){
                adapter = new CountryInfoListAdapter(context);
            }
            recyclerList.setVisibility(View.VISIBLE);
            recyclerList.setAdapter(adapter);
            recyclerList.setLayoutManager(new LinearLayoutManager(context));
            adapter.setRowData(rowData);
            adapter.notifyDataSetChanged();
        }
    }
}
