package com.example.lalala.mynews.Util;

import android.content.Context;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.facebook.imagepipeline.decoder.SimpleProgressiveJpegConfig;

import butterknife.ButterKnife;

/**
 * Created by lalala on 2017/2/3.
 */

public class MyApplication extends android.app.Application{
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
        initFresco();
    }

    private void initFresco() {
        ImagePipelineConfig config = ImagePipelineConfig.newBuilder(this)
                .setProgressiveJpegConfig(new SimpleProgressiveJpegConfig())
                .build();
        Fresco.initialize(this, config);
    }
}
