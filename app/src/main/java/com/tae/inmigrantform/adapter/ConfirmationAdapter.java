package com.tae.inmigrantform.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tae.inmigrantform.R;
import com.tae.inmigrantform.model.Immigrant;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Eduardo on 11/12/2015.
 */
public class ConfirmationAdapter extends RecyclerView.Adapter<ConfirmationAdapter.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvTitle, tvData;

        public ViewHolder(View itemView) {
            super(itemView);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_detail_item_title);
            tvData = (TextView) itemView.findViewById(R.id.tv_detail_item_data);
        }
    }

    private Context context;
    private LayoutInflater inflater;
    private String [] titles;
    private List<String> immigrantDataList;
    private Immigrant immigrant;

    public ConfirmationAdapter(Context context, Immigrant immigrant) {
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.immigrant = immigrant;
        titles = context.getResources().getStringArray(R.array.confirmation_titles_array);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.activity_list_immigrant_list_item, parent, false);
//        titles = context.getResources().getStringArray(R.array.confirmation_titles_array);
        immigrantDataList = new ArrayList<>(titles.length);
        for (String title : titles) {
//            immigrantDataList.add();
        }
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        for (int i = 0 ; i < titles.length; i++) {
            holder.tvTitle.setText(titles[i]); // TODO this is just getting the last value
//            holder.tvData.setText(immigrantDataList.get(i));

        }
    }

    @Override
    public int getItemCount() {
        return titles.length;
    }
}
