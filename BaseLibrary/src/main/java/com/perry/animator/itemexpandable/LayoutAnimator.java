package com.perry.animator.itemexpandable;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by lipengjun on 2017/3/6.
 */

public class LayoutAnimator {
    public static class LayoutHeightUpdateListener implements ValueAnimator.AnimatorUpdateListener {

        private final View _view;

        public LayoutHeightUpdateListener(View view) {
            _view = view;
        }

        @Override
        public void onAnimationUpdate(ValueAnimator animation) {
            final ViewGroup.LayoutParams lp = _view.getLayoutParams();
            lp.height = (int) animation.getAnimatedValue();
            _view.setLayoutParams(lp);
        }

    }

    public static Animator ofHeight(View view, int start, int end) {
        final ValueAnimator animator = ValueAnimator.ofInt(start, end);
        animator.addUpdateListener(new LayoutHeightUpdateListener(view));
        return animator;
    }

    public static class LayoutWidthUpdateListener implements ValueAnimator.AnimatorUpdateListener {

        private final View _view;

        public LayoutWidthUpdateListener(View view) {
            _view = view;
        }

        @Override
        public void onAnimationUpdate(ValueAnimator animation) {
            final ViewGroup.LayoutParams lp = _view.getLayoutParams();
            lp.width = (int) animation.getAnimatedValue();
            _view.setLayoutParams(lp);
        }

    }

    public static Animator ofWidth(View view, int start, int end) {
        final ValueAnimator animator = ValueAnimator.ofInt(start, end);
        animator.addUpdateListener(new LayoutWidthUpdateListener(view));
        return animator;
    }
}
