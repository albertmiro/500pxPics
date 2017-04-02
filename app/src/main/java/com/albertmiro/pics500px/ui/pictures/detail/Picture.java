package com.albertmiro.pics500px.ui.pictures.detail;

import android.app.Fragment;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.albertmiro.pics500px.R;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import uk.co.senab.photoview.PhotoViewAttacher;

@EFragment(R.layout.picture)
public class Picture extends Fragment {

    private static final String TAG = Picture.class.getSimpleName();

    Context context;

    @FragmentArg
    String urlPicture;

    @ViewById
    ImageView image;

    @ViewById
    ViewGroup loadingView;

    PhotoViewAttacher mAttacher;

    @AfterViews
    public void init() {

        context = getActivity();

        showLoading();

        initPicture();
    }

    private void initPicture() {
        Picasso.with(context).load(urlPicture).into(image, new Callback() {
            @Override
            public void onSuccess() {
                mAttacher = new PhotoViewAttacher(image);
                mAttacher.setMaximumScale(4f);
                hideLoading();
            }

            @Override
            public void onError() {

            }
        });

    }

    @UiThread
    void showLoading() {
        loadingView.setVisibility(View.VISIBLE);
    }

    @UiThread(delay = 1000)
    void hideLoading() {
        loadingView.setVisibility(View.GONE);
    }

}
