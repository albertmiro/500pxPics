package com.albertmiro.pics500px.ui.pictures;

import android.app.Fragment;
import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.view.ViewGroup;

import com.albertmiro.pics500px.R;
import com.albertmiro.pics500px.data.model.Photo;
import com.albertmiro.pics500px.data.model.Pictures;
import com.albertmiro.pics500px.data.ws.PicturesData;
import com.albertmiro.pics500px.data.ws.PicturesData_;
import com.albertmiro.pics500px.data.ws.RetrofitInit_;
import com.albertmiro.pics500px.ui.pictures.adapter.PicturesRVAdapter;
import com.albertmiro.pics500px.ui.pictures.adapter.PicturesRVAdapter_;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@EFragment(R.layout.pictures_fragment)
public class PicturesFragment extends Fragment {

    private static final String TAG = PicturesFragment.class.getSimpleName();

    Context context;

    @ViewById
    RecyclerView recyclerView;

    @ViewById(R.id.coordinator)
    CoordinatorLayout rootView;

    @ViewById
    ViewGroup loadingView;

    PicturesRVAdapter adapter;

    PicturesData picturesData;

    @AfterViews
    public void init() {

        context = getActivity();

        adapter = PicturesRVAdapter_.getInstance_(getActivity());

        picturesData = PicturesData_.getInstance_(context);

        showLoading();

        initListEntries();

        initRV();
    }

    private void initListEntries() {
        if(!picturesData.isEmpty()) {
            initRVWithPictures();
        } else {
            RetrofitInit_.getInstance_(getActivity()).initRetrofit();
            Call<Pictures> call = RetrofitInit_.getInstance_(getActivity()).getPictures();

            call.enqueue(new Callback<Pictures>() {
                @Override
                public void onResponse(Call<Pictures> call, Response<Pictures> response) {
                    int statusCode = response.code();
                    Pictures pictures = response.body();
                    if (statusCode != 200) {
                        showError(R.string.error);
                    } else {
                        if (pictures != null) {
                            picturesData.setPictures(pictures.getPhotos());
                            initRVWithPictures();
                        }
                    }
                }

                @Override
                public void onFailure(Call<Pictures> call, Throwable t) {
                    // Log error here since request failed
                    showError(R.string.error);
                }

            });
        }
    }

    private void initRVWithPictures() {
        if(picturesData.isEmpty()) {
            showError(R.string.no_items);
        } else {
            adapter.pushItems(picturesData.getAll());
            adapter.notifyDataSetChanged();
            hideLoading();
        }
    }

    private void showError(int idString) {
        Snackbar snackbar = Snackbar.make(rootView, idString, Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction(R.string.retry, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initListEntries();
            }
        });
        snackbar.show();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @UiThread
    void showLoading() {
        loadingView.setVisibility(View.VISIBLE);
    }

    @UiThread(delay = 1000)
    void hideLoading() {
        loadingView.setVisibility(View.GONE);
    }

    @UiThread
    void initRV() {
        ItemTouchHelper.SimpleCallback simpleCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                final int pos = viewHolder.getAdapterPosition();
                final Photo obj = picturesData.get(pos);
                picturesData.remove(pos);
                adapter.notifyDataSetChanged();
                Snackbar snackbar = Snackbar.make(rootView, R.string.item_deleted, Snackbar.LENGTH_LONG);
                snackbar.setAction(R.string.undo, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Snackbar snackbar = Snackbar.make(rootView, R.string.item_restored, Snackbar.LENGTH_SHORT);
                        snackbar.show();
                        picturesData.add(pos,obj);
                        adapter.notifyDataSetChanged();
                    }
                });
                snackbar.show();

                if(picturesData.isEmpty()) {
                    showError(R.string.no_items);
                }
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleCallback);
        itemTouchHelper.attachToRecyclerView(recyclerView);

        ((SimpleItemAnimator) recyclerView.getItemAnimator()).setSupportsChangeAnimations(true);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(context,2));
        recyclerView.setHasFixedSize(true);
    }

}
