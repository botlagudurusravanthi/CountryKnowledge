package com.info.countryknowledge.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.info.countryknowledge.R;
import com.info.countryknowledge.model.RowData;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sravanthi_B01 on 5/29/2018.
 */

public class CountryInfoListAdapter extends RecyclerView.Adapter<CountryInfoListAdapter.ListDataViewHolder> {

    private final Context context;
    private List<RowData> rowData;

    public CountryInfoListAdapter(Context context) {
        this.context = context;
       }

    @Override
    public ListDataViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.row_item, parent, false);
        return new ListDataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListDataViewHolder holder, int position) {
        RowData singleRowData =  rowData.get(position);
        holder.itemTitle.setText(singleRowData.getTitle());
        holder.description.setText(singleRowData.getDescription());
        setDescriptionWidth(holder.description);
        //  Picasso Builder which renders images from URL
        //  The placeholder image is a replacement for the time image is being loaded
        //  If the image couldn't be loaded the error image is displayed
        Picasso.with(context)
                .load(singleRowData.getImageHref())
                .resize(200,150)
                .centerCrop()
                .into(holder.imageContainer);
    }

    @Override
    public int getItemCount() {
        return rowData.size();
    }

    public void setRowData(List<RowData> countryInfoRows) {
        this.rowData = countryInfoRows;
        for(int i=0;i<rowData.size();i++){
           if(rowData.get(i).getTitle() == null){
               rowData.remove(i);
           }
        }
    }

    /*
    * Inner ViewHolder class to hold data for each item in RecyclerView
    * */
    public class ListDataViewHolder extends RecyclerView.ViewHolder {

        final TextView itemTitle, description;
        final ImageView imageContainer;

        public ListDataViewHolder(View itemView) {
            super(itemView);
            itemTitle = itemView.findViewById(R.id.title);
            description = itemView.findViewById(R.id.description);
            imageContainer = itemView.findViewById(R.id.imageContainer);
        }
    }

    private void setDescriptionWidth(TextView desc){
        DisplayMetrics displaymetrics = context.getResources().getDisplayMetrics();
        int width =(int)(displaymetrics.widthPixels * 0.6 );
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) desc.getLayoutParams();
        params.width =width;
        desc.setLayoutParams(params);
    }
}
