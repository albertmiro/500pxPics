package com.albertmiro.pics500px.ui.splash;


import android.app.Activity;

import com.albertmiro.pics500px.R;
import com.albertmiro.pics500px.ui.tutorial.Tutorial_;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;

@EActivity(R.layout.splash)
public class Splash extends Activity {

    @AfterViews
    public void init() {
        start();
    }

    @UiThread(delay = 2000)
    void start() {
        Tutorial_.intent(this).start();
        finish();
    }


}
