package com.example.lalala.mynews.Util;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

/**
 * Created by lalala on 2017/1/23.
 */

public class DimenisionUtil {

    /**
        获取屏幕相关参数
     **/
    public static DisplayMetrics getScreenSize(Context context){
        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        display.getMetrics(metrics);
        return  metrics;
    }

    /**
     * 获取屏幕Density
     */
    public static float getDeviceDensity(Context context){
        DisplayMetrics metrics = new DisplayMetrics();
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        display.getMetrics(metrics);
        return  metrics.density;
    }

    /**
     * 将px转化为dip或dp值保证大小不变
     *
     * @param context
     * @param px
     * @return
     */
    public static int pxToDip(Context context, float px){
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int)(px / scale + 0.5f);
    }

    /**
     * 将dip或dp转化为px值保证大小不变
     *
     * @param context
     * @param dip
     * @return
     */
    public static int dipToPx(Context context, float dip){
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int)(dip * scale + 0.5f);
    }

    /**
     * 将px值转换为sp值
     *
     * @param context
     * @param px
     * @return
     */
    public static int pxToSp(Context context, float px){
        final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int) (px / fontScale + 0.5f);
    }


    /**
     * 将sp值转换为px值
     *
     * @param context
     * @param sp
     * @return
     */
    public static int spToPx(Context context, float sp){
        final float scale = context.getResources().getDisplayMetrics().scaledDensity;
        return (int)(sp * scale + 0.5f);
    }
 }