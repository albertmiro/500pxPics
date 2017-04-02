package com.albertmiro.pics500px.ui.view;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.albertmiro.pics500px.R;
import com.albertmiro.pics500px.data.model.Photo;
import com.albertmiro.pics500px.ui.main.MainActivity;
import com.albertmiro.pics500px.utils.Utils;
import com.squareup.picasso.Picasso;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

@EViewGroup(R.layout.item_picture)
public class PictureView extends FrameLayout {

    private static final String TAG = PictureView.class.getSimpleName();

    @ViewById
    ImageView picture;

    private Photo item;

    public PictureView(Context context) {
        super(context);
    }

    public PictureView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void bind(Photo itemImage) {
        this.item = itemImage;
        int halfWidth = Utils.getSceenWidthSize((Activity) getContext()) / 2;
        Picasso.with(getContext())
                .load(this.item.getImageUrl())
                .resize(halfWidth, halfWidth)
                .centerCrop()
                .into(picture);

    }

    @Click(R.id.picture)
    public void onPictureCliked() {
        ((MainActivity) getContext()).showPicture(item);
    }



}

