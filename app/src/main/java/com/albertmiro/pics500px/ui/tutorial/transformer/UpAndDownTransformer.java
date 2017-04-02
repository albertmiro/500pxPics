package com.albertmiro.pics500px.ui.tutorial.transformer;

import android.support.v4.view.ViewPager;
import android.view.View;

import com.albertmiro.pics500px.R;

public class UpAndDownTransformer implements ViewPager.PageTransformer {
    private static final float MIN_SCALE = 0.75f;

    public void transformPage(View view, float position) {
        int pageWidth = view.getWidth();

        if (position < -1) { // [-Infinity,-1)
            // This page is way off-screen to the left.
            view.setAlpha(0);

        } else if (position <= 0) { // [-1,0]
            // Use the default slide transition when moving to the left page
            View img = view.findViewById(R.id.tutorial_img);
            View text = view.findViewById(R.id.tutorial_text);
            if(img != null ) {
                text.setTranslationY(position * pageWidth * 1.25f);
                img.setTranslationY(-position * pageWidth * 1.25f);
            }
            view.setTranslationX(-position * pageWidth);
            view.setAlpha(1 + position);
        } else if (position <= 1) { // (0,1]
            // Fade the page out.
            view.setAlpha(1 - position);

        } else { // (1,+Infinity]
            // This page is way off-screen to the right.
            view.setAlpha(0);
        }


    }
}
