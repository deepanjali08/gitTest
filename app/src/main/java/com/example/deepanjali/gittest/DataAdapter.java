package com.example.deepanjali.gittest;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by deepanjali on 14/7/16.
 */
public class DataAdapter  extends RecyclerView.Adapter<DataAdapter.ViewHolder>{
    private List<StackOverflowXmlParser.Entry> countries;


    public String names;
    public String desc;

    public DataAdapter(List<StackOverflowXmlParser.Entry> countries) {
        this.countries = countries;
    }

//

    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_row, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DataAdapter.ViewHolder viewHolder, int i) {

        viewHolder.tv_country.setText(countries.get(i).title);
        viewHolder.tv_desc.setText(countries.get(i).summary);
//        viewHolder.tv_image.setImageResource(countries.get(i));

//
    }

    @Override
    public int getItemCount() {
        return countries.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_country;
        private TextView tv_desc;
//        private ImageView tv_image;

        public ViewHolder(View view) {
            super(view);

            tv_country = (TextView)view.findViewById(R.id.tv_country);
            tv_desc=(TextView)view.findViewById(R.id.tv_desc);
//            tv_image=(ImageView)view.findViewById(R.id.tv_image);


        }
    }

}


