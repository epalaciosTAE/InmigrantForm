package com.tae.inmigrantform.listener;

import android.graphics.Canvas;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.tae.inmigrantform.adapter.ImmigrantDetailAdapter;
import com.tae.inmigrantform.adapter.ImmigrantDetailCursorAdapter;
import com.tae.inmigrantform.adapter.RecyclerViewCursorAdapter;

/**
 * Created by Eduardo on 16/12/2015.
 */
public class ItemTouchItemHelper  {

//
//    @Override
//    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
//        if (viewHolder instanceof ImmigrantDetailAdapter.ViewHolder) {
//            int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
//            return makeMovementFlags(0, swipeFlags);
//        } else
//            return 0;
//    }
//
//    @Override
//    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
//        return false;
//    }
//
//    @Override
//    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
//
//    }
//
//    @Override
//    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
//        getDefaultUIUtil().clearView(((ImmigrantDetailAdapter.ViewHolder) viewHolder).getSwipableView());
//    }
//
//    @Override
//    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
//        if (viewHolder != null) {
//            getDefaultUIUtil().onSelected(((ImmigrantDetailAdapter.ViewHolder) viewHolder).getSwipableView());
//        }
//    }
//
//    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
//        getDefaultUIUtil().onDraw(c, recyclerView, ((ImmigrantDetailAdapter.ViewHolder) viewHolder).getSwipableView(), dX, dY,    actionState, isCurrentlyActive);
//    }
//
//    public void onChildDrawOver(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
//        getDefaultUIUtil().onDrawOver(c, recyclerView, ((ImmigrantDetailAdapter.ViewHolder) viewHolder).getSwipableView(), dX, dY,    actionState, isCurrentlyActive);
//    }
}
