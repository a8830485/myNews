package com.example.lalala.mynews.Util;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.Log;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeController;
import com.facebook.drawee.view.DraweeView;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.Calendar;

/**
 * Created by lalala on 2017/1/22.
 */

public class BaseUtil {
    /*
    电视节目key
     */
    public final static String key = "6499d5b8c07811c32f160fd70a004868";


    private static String date = "";
    /*
    获取当前日期字符串
     */
    public static String getDate(){
        if(date.equals("")) {
            Calendar calendar = Calendar.getInstance();
            int mYear = calendar.get(Calendar.YEAR);
            int mMonth = calendar.get(Calendar.MONTH) + 1;
            int mDay = calendar.get(calendar.DAY_OF_MONTH);
            date = String.format("%4d-%02d-%2d", mYear, mMonth, mDay);
        }
        return date;
    }

    /*
    通过日期字符串返回时今天，昨天，前天，

    public static String getStr
    */


    public static Bitmap getBitMBitmap(String urlpath) {
        Bitmap map = null;
        try {
            URL url = new URL(urlpath);
            URLConnection conn = url.openConnection();
            conn.connect();
            InputStream in;
            in = conn.getInputStream();
            map = BitmapFactory.decodeStream(in);
            // TODO Auto-generated catch block
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

    public static Drawable loadImageFromNetwork(String imageUrl)
    {
        Drawable drawable = null;
        try {
            // 可以在这里通过文件名来判断，是否本地有此图片
            drawable = Drawable.createFromStream(
                    new URL(imageUrl).openStream(), "image.jpg");
        } catch (IOException e) {
            Log.d("test", e.getMessage());
        }
        if (drawable == null) {
            Log.d("test", "null drawable");
        } else {
            Log.d("test", "not null drawable");
        }

        return drawable ;
    }

    public static PipelineDraweeController getController(String url, SimpleDraweeView draweeView) {
        ImageRequest request = ImageRequestBuilder
                .newBuilderWithSource(Uri.parse(url))
                .setProgressiveRenderingEnabled(true)
                .build();
        PipelineDraweeController controller = (PipelineDraweeController) Fresco.newDraweeControllerBuilder()
                .setImageRequest(request)
                .setOldController(draweeView.getController())
                .build();

        return controller;
    }
}
