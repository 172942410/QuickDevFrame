package com.perry.animator.itemexpandable;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by lipengjun on 2017/3/6.
 */

public class LayoutParamsAnimatorListener extends AnimatorListenerAdapter {
    private final View _view;
    private final int _paramsWidth;
    private final int _paramsHeight;

    public LayoutParamsAnimatorListener(View view, int paramsWidth, int paramsHeight) {
        _view = view;
        _paramsWidth = paramsWidth;
        _paramsHeight = paramsHeight;
    }

    @Override
    public void onAnimationEnd(Animator animation) {
        final ViewGroup.LayoutParams params = _view.getLayoutParams();
        params.width = _paramsWidth;
        params.height = _paramsHeight;
        _view.setLayoutParams(params);
    }
}