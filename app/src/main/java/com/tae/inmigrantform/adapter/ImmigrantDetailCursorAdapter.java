package com.tae.inmigrantform.adapter;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tae.inmigrantform.R;
import com.tae.inmigrantform.database.ImmigrantContract;
import com.tae.inmigrantform.listener.OnRV_ClickListener;

/**
 * Created by Eduardo on 12/12/2015.
 */
public class ImmigrantDetailCursorAdapter extends RecyclerViewCursorAdapter<ImmigrantDetailCursorAdapter.ViewHolder> {

    private final LayoutInflater layoutInflater;
    private Context context;

    public ImmigrantDetailCursorAdapter(Context context) {
        this.context = context;
        layoutInflater = LayoutInflater.from(context);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, Cursor cursor) {

        holder.bindData(cursor);
//        holder.setClickListener(new OnRV_ClickListener() {
//            @Override
//            public void onClick(View view, int position, boolean isLongClick) {
//                // TODO start new activity here
//                Log.i("CursorAdapter", "click view" + view.getId() + " position " + position);
////                Immigrant immigrant = getImmigrant(position);
////                context.startActivity(new Intent(context, DetailActivity.class).putExtra(Constants.EXTRA_IMMIGRANT, immigrant));
//            }
//        });
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = this.layoutInflater.inflate(R.layout.activity_list_immigrant_list_card_view, parent, false);
        return new ViewHolder(view);
    }

//    public Immigrant getImmigrant(int position) {
//        return (Immigrant) getItem(position).;
//    }




    protected static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        //        private TextView tvId;
        private ImageView imageViewBackground;
        private TextView tvFamilyNameBackground;

        private TextView tvFamilyName;
        //        private TextView tvId;
        private ImageView imageView;
        private OnRV_ClickListener onRVClickListener; // to make this guy works we need to set the listener, and then get the view of the implemented listeners

        public ViewHolder(View itemView) {
            super(itemView);
//            tvId = (TextView) itemView.findViewById(R.id.tv_detail_item_title);

            tvFamilyNameBackground = (TextView) itemView.findViewById(R.id.card_view_detail_family_name_confirm);
            imageViewBackground = (ImageView) itemView.findViewById(R.id.card_view_detail_image_confirm);


            tvFamilyName = (TextView) itemView.findViewById(R.id.card_view_detail_family_name);
            imageView = (ImageView) itemView.findViewById(R.id.card_view_detail_image);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);
            onRVClickListener = (OnRV_ClickListener) itemView.getContext();
        }

        public void bindData(final Cursor cursor) {
            final String familyName = cursor.getString(cursor.getColumnIndex(ImmigrantContract.ImmigrantEntry.COLUMN_NAME_FAMILY_NAME));
            final String imagePath = cursor.getString(cursor.getColumnIndex(ImmigrantContract.ImmigrantEntry.COLUMN_NAME_IMAGE));
            if (imagePath != null) {
                imageView.setImageURI(Uri.parse(imagePath));
                imageViewBackground.setImageURI(Uri.parse(imagePath));
            }
            tvFamilyName.setText(familyName);
            tvFamilyNameBackground.setText(familyName);
        }

//        public void setClickListener(OnRV_ClickListener onRVClickListener) {
//            this.onRVClickListener = onRVClickListener;
//        }

        @Override
        public void onClick(View v) {
            onRVClickListener.onClick(v, getAdapterPosition(), false);
        }

        @Override
        public boolean onLongClick(View v) {
            onRVClickListener.onClick(v, getAdapterPosition(), false);
            return true;
        }

//        public View getSwipableView() {
//            return cardView;
//        }
    }


}
