package com.sumaiyamunira.serviceunilibrary.utils.infinitecycleviewpager;

import android.view.View;

public interface OnInfiniteCyclePageTransformListener {
    void onPreTransform(final View page, final float position);

    void onPostTransform(final View page, final float position);
}