package com.assesment.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.assesment.magazineapp.R;
import com.assesment.model.Row;
import com.squareup.picasso.Picasso;
import java.util.List;


// RecyclerView Adapter used to show the list of facts received from the webservice

public class FactsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Row> mDataSet = null;
    private Context mContext;

    //Constructor takes two parameter Context and the List of Row Objects

    public FactsAdapter(Context context, List<Row> dataSet) {
        this.mDataSet = dataSet;
        this.mContext = context;
    }


    // ViewHolder represent the View of the each row items in the list . it holds three objects. Two TextView and one ImageView
    //txtFactHead used to show title of the view
    //txtFactDescription used to shows the description
    //imgFactImage used to show the Image
    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtFactHead = null;
        TextView txtFactDescription = null;
        ImageView imgFactImage = null;

        public ViewHolder(View itemView) {
            super(itemView);
            txtFactHead = ((TextView) itemView.findViewById(R.id.txtFactHead));
            txtFactDescription = ((TextView) itemView.findViewById(R.id.txtFactDescription));
            imgFactImage = ((ImageView) itemView.findViewById(R.id.imgFact));

        }
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        // inflating view for the row
        View mView = LayoutInflater.from(parent.getContext()).inflate(R.layout.facts_row_layout, parent, false);


        return new ViewHolder(mView);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        Row row = mDataSet.get(position);

        // Setting the Tittle and the description by checking the null values

        ((ViewHolder) holder).txtFactHead.setText(null != row.getTitle() ? row.getTitle() : mContext.getResources().getString(R.string.no_titile));

        ((ViewHolder) holder).txtFactDescription.setText(null != row.getDescription() ? row.getDescription() : mContext.getResources().getString(R.string.no_description));

       //Picasso library is used loading the images from the url to the specified imageview

        Picasso.with(mContext)
                .load(row.getImageHref())
                 .into(((ViewHolder) holder).imgFactImage);


    }

    @Override
    public int getItemCount() {
        return null != mDataSet ? mDataSet.size() : 0;
    }

    // Method used for adding new facts to the adapter or if we need refresh the facts
    public void addFacts(List<Row> facts)
    {
        mDataSet.clear();
        mDataSet.addAll(facts);
        notifyDataSetChanged();
    }
}
