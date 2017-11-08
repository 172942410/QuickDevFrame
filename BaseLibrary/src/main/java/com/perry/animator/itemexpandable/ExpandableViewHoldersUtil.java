package com.perry.animator.itemexpandable;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by lipengjun on 2017/3/6.
 */

public class ExpandableViewHoldersUtil {

    public static void openH(final RecyclerView.ViewHolder holder, final View expandView, final boolean animate) {
//        animate参数为true，则有动画效果
        if (animate) {
            expandView.setVisibility(View.VISIBLE);
//            5
//            改变高度的动画，具体操作点进去看
            final Animator animator = ViewHolderAnimator.ofItemViewHeight(holder);
//            扩展的动画结束后透明度动画开始
            animator.addListener(new AnimatorListenerAdapter() {
                @Override public void onAnimationEnd(Animator animation) {
                    final ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(expandView, View.ALPHA, 1);
                    alphaAnimator.addListener(new ViewHolderAnimator.ViewHolderAnimatorListener(holder));
                    alphaAnimator.start();
                }
            });
            animator.start();
        }
//        animate参数为false，则直接设置为可见
        else {
            expandView.setVisibility(View.VISIBLE);
            expandView.setAlpha(1);
        }
    }

    public static void closeH(final RecyclerView.ViewHolder holder, final View expandView, boolean animate) {
        //        animate参数为true，则有动画效果
        if (animate) {
//            expandView.setVisibility(View.GONE);
//            改变高度的动画，具体操作点进去看
            final Animator animator = ViewHolderAnimator.ofItemViewClose(holder,expandView);
//            扩展的动画结束后透明度动画开始
            animator.addListener(new AnimatorListenerAdapter() {
                @Override public void onAnimationEnd(Animator animation) {
                    expandView.setVisibility(View.GONE);
//                    final ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(expandView, View.ALPHA, 0);
//                    alphaAnimator.addListener(new ViewHolderAnimator.ViewHolderAnimatorListener(holder));
//                    alphaAnimator.start();
                }
            });
            animator.start();
        }
//        animate参数为false，则直接设置为可见
        else {
            expandView.setVisibility(View.GONE);
            expandView.setAlpha(1);
        }
    }
}
