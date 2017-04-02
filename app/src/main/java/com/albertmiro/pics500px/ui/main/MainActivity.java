package com.albertmiro.pics500px.ui.main;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.widget.FrameLayout;

import com.albertmiro.pics500px.R;
import com.albertmiro.pics500px.data.model.Photo;
import com.albertmiro.pics500px.ui.pictures.PicturesFragment_;
import com.albertmiro.pics500px.ui.pictures.detail.Picture_;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.main)
public class MainActivity extends Activity {

    @ViewById
    FrameLayout container;

    Fragment currentFragment;

    @AfterViews
    public void init() {
        openHome();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    public void openHome() {
        replaceFragment(PicturesFragment_.builder().build(),false);
    }

    public void showPicture(Photo item) {
        replaceFragment(Picture_.builder().urlPicture(item.getImageUrl()).build(),true);
    }

    void replaceFragment(Fragment fragment, boolean addToBackStack) {
        this.currentFragment = fragment;
        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container,fragment);
        if(addToBackStack) {
            fragmentTransaction.addToBackStack(fragment.getClass().getName());
        }
        fragmentTransaction.commit();
    }
}
