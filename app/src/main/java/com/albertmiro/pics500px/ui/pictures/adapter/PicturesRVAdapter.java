package com.albertmiro.pics500px.ui.pictures.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.albertmiro.pics500px.data.model.Photo;
import com.albertmiro.pics500px.ui.view.PictureView;
import com.albertmiro.pics500px.ui.view.PictureView_;
import com.albertmiro.pics500px.utils.rv.RecyclerViewAdapterBase;
import com.albertmiro.pics500px.utils.rv.ViewWrapper;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;

@EBean
public class PicturesRVAdapter extends RecyclerViewAdapterBase<Photo, PictureView> {

    private static final String TAG = PicturesRVAdapter.class.getSimpleName();

    @RootContext
    Context context;

    @Override
    protected PictureView onCreateItemView(ViewGroup parent, int viewType) {
        PictureView view = PictureView_.build(context);
        //BUGFIX: annotations does not make the subitems match parent width (100%)
//        view.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        return view;
    }

    @Override
    public void onBindViewHolder(ViewWrapper<PictureView> holder, int position) {
        PictureView pictureView = holder.getView();
        Photo itemPhoto = items.get(position);
        pictureView.bind(itemPhoto);
    }

}
