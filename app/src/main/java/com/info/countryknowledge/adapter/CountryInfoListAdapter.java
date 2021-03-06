package com.info.countryknowledge.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.info.countryknowledge.R;
import com.info.countryknowledge.model.RowData;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import java.util.List;

/**
 * This class set the data to recycler view and displays the data.
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
    public void onBindViewHolder(final ListDataViewHolder holder, int position) {
        final RowData singleRowData =  rowData.get(position);
        holder.itemTitle.setText(singleRowData.getTitle());
        holder.description.setText(singleRowData.getDescription());
        setDescriptionWidth(holder.description);

        //  Picasso Builder which renders images from URL
        //  The placeholder image is a replacement for the time image is being loaded
        //  If the image couldn't be loaded the error image is displayed

        Picasso.with(context)
                    .load(singleRowData.getImageHref())
                    .resize(200, 150)
                    .centerCrop()
                    .into(holder.imageContainer, new Callback() {
                        @Override
                        public void onSuccess() {
                        }
                        @Override
                        public void onError() {
                          loadImage(holder,singleRowData);
                        }
                    });
    }

    @Override
    public int getItemCount() {
        return rowData.size();
    }
       /*
        * Set the row data and remove the row with empty title.
        * */
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

   //Setting the width of the description textview to 60% of the screen to look the image at the same
    private void setDescriptionWidth(TextView desc){
        DisplayMetrics displaymetrics = context.getResources().getDisplayMetrics();
        int width =(int)(displaymetrics.widthPixels * 0.6 );
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) desc.getLayoutParams();
        params.width =width;
        desc.setLayoutParams(params);
    }
    // Few images are not loading with http:// so replacing with https:// only when we get loaderror
    private void loadImage(ListDataViewHolder holder,RowData singleRowData){
        String url = singleRowData.getImageHref();
        if(url.contains("http://")) {
            url = url.replace("http://", "https://");
            Picasso.with(context)
                    .load(url)
                    .resize(200, 150)
                    .centerCrop()
                    .into(holder.imageContainer);
        }
    }
}
