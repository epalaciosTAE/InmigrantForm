package com.tae.inmigrantform.adapter;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;

/**
 * Created by Eduardo on 12/12/2015.
 */
public class ImmigrantDetailAdapter extends RecyclerView.Adapter<ImmigrantDetailAdapter.ViewHolder> {

    private CursorAdapter cursorAdapter;
    private Context context;

    public ImmigrantDetailAdapter(Context context, Cursor cursor) {
        this.context = context;
        this.cursorAdapter = new CursorAdapter(context, cursor, 0) {
            @Override
            public View newView(Context context, Cursor cursor, ViewGroup parent) {
                return null;
            }

            @Override
            public void bindView(View view, Context context, Cursor cursor) {

            }
        };
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return cursorAdapter.getCount();
    }
}
