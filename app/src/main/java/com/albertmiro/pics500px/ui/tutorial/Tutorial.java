package com.albertmiro.pics500px.ui.tutorial;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Context;
import android.support.v13.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import android.widget.TextView;

import com.albertmiro.pics500px.R;
import com.albertmiro.pics500px.ui.main.MainActivity_;
import com.albertmiro.pics500px.ui.tutorial.transformer.UpAndDownTransformer;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.ViewsById;

import java.util.List;

@EActivity(R.layout.activity_tutorial)
public class Tutorial extends Activity {

    private static final int NUM_PAGES = 3;

    @ViewById(R.id.pager)
    ViewPager mPager;

    @ViewsById({R.id.indicator_0, R.id.indicator_1, R.id.indicator_2})
    List<ImageView> pagerIndicators;

    @ViewById(R.id.btn_skip)
    TextView btnSkip;

    Context context;

    @AfterViews
    public void init() {

        context = this;

        PagerAdapter mPagerAdapter = new TutorialSlidePagerAdapter(getFragmentManager());

        mPager.setAdapter(mPagerAdapter);
        mPager.setPageTransformer(true, new UpAndDownTransformer());
        mPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i2) {
            }

            @Override
            public void onPageSelected(int i) {
                updateBottomIndicator(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        updateBottomIndicator(0);
    }

    @Click(R.id.btn_skip)
    public void onClickSkip() {
        MainActivity_.intent(context).start();
        finish();
    }


    private void updateBottomIndicator(int posSelected) {
        int pos = 0;
        for(ImageView indicator : pagerIndicators) {
            if(pos == posSelected) indicator.setSelected(true);
            else indicator.setSelected(false);
            pos++;
        }

        if(posSelected == NUM_PAGES -1) {
            btnSkip.setText(R.string.tutorial_continue);
        } else {
            btnSkip.setText(R.string.tutorial_skip);
        }
    }

    @Override
    public void onBackPressed() {
        if (mPager.getCurrentItem() == 0) {
            super.onBackPressed();
        } else {
            mPager.setCurrentItem(mPager.getCurrentItem() - 1);
        }
    }

    private class TutorialSlidePagerAdapter extends FragmentStatePagerAdapter {
        public TutorialSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0: return new TutorialFragment_Slide_0_().builder().build();
                case 1: return new TutorialFragment_Slide_1_().builder().build();
                case 2: return new TutorialFragment_Slide_2_().builder().build();
            }
            return null;
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }
}
