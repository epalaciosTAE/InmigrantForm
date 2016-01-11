package com.tae.inmigrantform.activity;

import android.annotation.TargetApi;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import com.tae.inmigrantform.R;
import com.tae.inmigrantform.adapter.ImmigrantDetailCursorAdapter;
import com.tae.inmigrantform.constants.Constants;
import com.tae.inmigrantform.database.ImmigrantContract;
import com.tae.inmigrantform.listener.OnRV_ClickListener;
import com.tae.inmigrantform.model.Immigrant;
import com.tae.inmigrantform.provider.ImmigrantProvider;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by Eduardo on 12/12/2015.
 * This activity display the list of users and the id from the database.
 */
public class ImmigrantListActivity extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<Cursor>, OnRV_ClickListener, View.OnClickListener {

    private static final int LOADER_IMMIGRANT_DETAILS = 1;
    private ImmigrantDetailCursorAdapter cursorAdapter;
    private RecyclerView recyclerView;
    @Bind(R.id.toolbar) protected Toolbar toolbar;

    float historicX = Float.NaN, historicY = Float.NaN;
    private static final int DELTA = 50;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_immigrant);
        ButterKnife.bind(this);
        setToolbar();

        recyclerView = (RecyclerView) findViewById(R.id.list_confirmation);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        cursorAdapter = new ImmigrantDetailCursorAdapter(ImmigrantListActivity.this);
        recyclerView.setAdapter(cursorAdapter);
        getLoaderManager().restartLoader(LOADER_IMMIGRANT_DETAILS, null, this);


        ItemTouchHelper.SimpleCallback simpleTouchCallback =
                new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
                //Available swipe directions
                int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;
                Log.i("ImmigrantList", "swipeFlags " + swipeFlags + " // dragFlags" +dragFlags );
                return makeMovementFlags(dragFlags, swipeFlags) != 0;
//                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                // remove from adapter
//                    Log.i("ListActivity", "direction " + direction);
//                final TextView tvFamilyName = (TextView) viewHolder.itemView.findViewById(R.id.card_view_detail_family_name);
//                Log.i("ListActivity", "tvFamilyName " + tvFamilyName.getText().toString());
//                if (tvFamilyName != null) {
//
//                }


            }

//            @TargetApi(Build.VERSION_CODES.M)
//            @Override
//            public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder,
//                                    float dX, float dY, int actionState, boolean isCurrentlyActive) {
//                View itemView = viewHolder.itemView;
//                Drawable d = ContextCompat.getDrawable(ImmigrantListActivity.this, R.drawable.ic_toilet_bowl_24x24);
//                d.setBounds(itemView.getLeft(), itemView.getTop(), (int) dX, itemView.getBottom());
//                d.draw(c);

//                if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {
//
//                    View itemView = viewHolder.itemView;
//
//                    Paint paint = new Paint();
//                    Bitmap bitmap;
//
//                    if (dX > 0) { // swiping right
//                        paint.setColor(getResources().getColor(R.color.colorAccentDark));
//                        bitmap = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.mipmap.ic_launcher);
//                        float height = (itemView.getHeight() / 2) - (bitmap.getHeight() / 2);
//
//                        c.drawRect((float) itemView.getLeft(), (float) itemView.getTop(), dX, (float) itemView.getBottom(), paint);
//                        c.drawBitmap(bitmap, 96f, (float) itemView.getTop() + height, null);
//
//                    } else { // swiping left
//                        paint.setColor(getResources().getColor(R.color.colorAccentDark));
//                        bitmap = BitmapFactory.decodeResource(getApplicationContext().getResources(), R.drawable.ic_toilet_bowl_24x24);
//                        float height = (itemView.getHeight() / 2) - (bitmap.getHeight() / 2);
//                        float bitmapWidth = bitmap.getWidth();
//
//                        c.drawRect((float) itemView.getRight() + dX, (float) itemView.getTop(), (float) itemView.getRight(), (float) itemView.getBottom(), paint);
//                        c.drawBitmap(bitmap, ((float) itemView.getRight() - bitmapWidth) - 96f, (float) itemView.getTop() + height, null);
//                    }
//
//
//
//                }
//            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleTouchCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    private void setToolbar() {
        setSupportActionBar(toolbar);
        toolbar.setTitle(R.string.toolbar_title_list);
        toolbar.setTitle(R.string.toolbar_title_detail);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_left_bold_pink_24x24);
        toolbar.setNavigationOnClickListener(this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        String[] projection = {
                ImmigrantContract.ImmigrantEntry._ID,
                ImmigrantContract.ImmigrantEntry.COLUMN_NAME_FAMILY_NAME,
                ImmigrantContract.ImmigrantEntry.COLUMN_NAME_LAST_NAME,
                ImmigrantContract.ImmigrantEntry.COLUMN_NAME_IMAGE,
                ImmigrantContract.ImmigrantEntry.COLUMN_NAME_DATE_OF_BIRTH,
                ImmigrantContract.ImmigrantEntry.COLUMN_NAME_GENDER,
                ImmigrantContract.ImmigrantEntry.COLUMN_NAME_COUNTRY,
                ImmigrantContract.ImmigrantEntry.COLUMN_NAME_ADDRESS,
                ImmigrantContract.ImmigrantEntry.COLUMN_NAME_EMAIL,
                ImmigrantContract.ImmigrantEntry.COLUMN_NAME_PHONE
        };

        if (LOADER_IMMIGRANT_DETAILS == id) {
            return new CursorLoader(this, ImmigrantProvider.CONTENT_URI_IMMIGRANTS, projection, null, null, null);
        }
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        if (LOADER_IMMIGRANT_DETAILS == loader.getId()) {
            cursorAdapter.swapCursor(data);
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        if (LOADER_IMMIGRANT_DETAILS == loader.getId()) {
            cursorAdapter.swapCursor(null);
        }
    }


    @Override
    public void onClick(View view, int position, boolean isLongClick) {
        Cursor cursor = cursorAdapter.getItem(position);

        Immigrant immigrant = new Immigrant(
            cursor.getInt(cursor.getColumnIndex(ImmigrantContract.ImmigrantEntry._ID)),
            cursor.getString(cursor.getColumnIndex(ImmigrantContract.ImmigrantEntry.COLUMN_NAME_FAMILY_NAME)),
            cursor.getString(cursor.getColumnIndex(ImmigrantContract.ImmigrantEntry.COLUMN_NAME_LAST_NAME)),
            cursor.getString(cursor.getColumnIndex(ImmigrantContract.ImmigrantEntry.COLUMN_NAME_IMAGE)),
            cursor.getString(cursor.getColumnIndex(ImmigrantContract.ImmigrantEntry.COLUMN_NAME_DATE_OF_BIRTH)),
            cursor.getString(cursor.getColumnIndex(ImmigrantContract.ImmigrantEntry.COLUMN_NAME_GENDER)),
            cursor.getString(cursor.getColumnIndex(ImmigrantContract.ImmigrantEntry.COLUMN_NAME_COUNTRY)),
            cursor.getString(cursor.getColumnIndex(ImmigrantContract.ImmigrantEntry.COLUMN_NAME_ADDRESS)),
            cursor.getString(cursor.getColumnIndex(ImmigrantContract.ImmigrantEntry.COLUMN_NAME_EMAIL)),
            cursor.getString(cursor.getColumnIndex(ImmigrantContract.ImmigrantEntry.COLUMN_NAME_PHONE))

        );

//        Immigrant immigrant = cursorAdapter.getImmigrant();
//        Log.i(this.getLocalClassName(), "colum name" + cursor.getColumnName(0));
//        startActivity(new Intent(ImmigrantListActivity.this, DetailActivity.class).putExtra(Constants.EXTRA_IMMIGRANT, immigrant));

        ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(
                // the context of the activity
                this,

                // For each shared element, add to this method a new Pair item,
                // which contains the reference of the view we are transitioning *from*,
                // and the value of the transitionName attribute
                new Pair<View, String>(view.findViewById(R.id.card_view_detail_family_name),
                        getString(R.string.transition_immigrant_list_title))
        );

        Intent intent = new Intent(ImmigrantListActivity.this, DetailActivity.class)
                .putExtra(Constants.EXTRA_IMMIGRANT, immigrant);

        ActivityCompat.startActivity(this, intent, options.toBundle());
    }

    @Override
    public void onClick(View v) {
        finish();
    }
}
