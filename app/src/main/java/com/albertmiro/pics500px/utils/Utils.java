package com.albertmiro.pics500px.utils;

import android.app.Activity;
import android.graphics.Point;

public class Utils {

    private static Point size = new Point();

    public static int getSceenWidthSize(Activity activity) {
        if(size.x > 0) return size.x;
        activity.getWindowManager().getDefaultDisplay().getSize(size);
        return size.x;
    }

    public static int getSceenHeightSize(Activity activity) {
        if(size.y > 0) return size.y;
        activity.getWindowManager().getDefaultDisplay().getSize(size);
        return size.y;
    }
}
