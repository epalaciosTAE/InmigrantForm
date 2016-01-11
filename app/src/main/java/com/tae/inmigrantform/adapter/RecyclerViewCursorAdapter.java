package com.tae.inmigrantform.adapter;

import android.content.ContentUris;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;

import java.util.List;

/**
 * Created by Eduardo on 12/12/2015.
 */
public abstract class RecyclerViewCursorAdapter <VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    private Cursor cursor;

    public void swapCursor(final Cursor cursor) {
        this.cursor = cursor;
        this.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return cursor != null
                ? cursor.getCount()
                : 0;
    }

    public Cursor getItem(final int position) {
        if (cursor != null && !cursor.isClosed()) {
            cursor.moveToPosition(position);
        }
        return cursor;
    }

    public Cursor getCursor() {
        return cursor;
    }

    public abstract void onBindViewHolder(final VH holder, final Cursor cursor);

    @Override
    public final void onBindViewHolder(final VH holder, final int position)
    {
        final Cursor cursor = this.getItem(position);
        this.onBindViewHolder(holder, cursor);
    }
}
