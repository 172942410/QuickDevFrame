package com.perry.animator;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.view.View;

import com.perry.animator.itemexpandable.LayoutAnimator;

/**
 * Created by lipengjun on 2017/4/17.
 */

public class AnimatorUtils {

    private static final String TAG = "AnimatorUtils";

    private static int height ;
    /**
     * 逆时针从0旋转180度
     */
    public static void RotationDown(View view){
        ObjectAnimator
                .ofFloat(view, "rotation", 0.0F, -180.0F)//
                .setDuration(300)//
                .start();
    }
    /**
     * 顺时针从180旋转0度
     */
    public static void RotationUp(View view){
        ObjectAnimator
                .ofFloat(view, "rotation", -180.0F, 0.0F)//
                .setDuration(300)//
                .start();
    }

    public static int closeH(final View view) {
//        测量扩展动画的起始高度和结束高度
        int start = view.getMeasuredHeight();
        view.measure(View.MeasureSpec.makeMeasureSpec(view.getMeasuredWidth(), View.MeasureSpec.AT_MOST), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        int end = start - view.getMeasuredHeight();
        height = start;
        Animator animator = LayoutAnimator.ofHeight(view, start, end);
//            扩展的动画结束后透明度动画开始
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                view.setVisibility(View.GONE);
//                    final ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(expandView, View.ALPHA, 0);
//                    alphaAnimator.addListener(new ViewHolderAnimator.ViewHolderAnimatorListener(holder));
//                    alphaAnimator.start();
            }
        });
        animator.start();
        return height;
    }

    public static void openH(final View view){
        view.setVisibility(View.VISIBLE);
//            改变高度的动画，具体操作点进去看
//        测量扩展动画的起始高度和结束高度
        int start = view.getMeasuredHeight();
        view.measure(View.MeasureSpec.makeMeasureSpec(view.getMeasuredWidth(), View.MeasureSpec.AT_MOST), View.MeasureSpec.makeMeasureSpec(height, View.MeasureSpec.EXACTLY));
        int end = view.getMeasuredHeight();
        final Animator animator = LayoutAnimator.ofHeight(view, start, end);
//            扩展的动画结束后透明度动画开始
        animator.addListener(new AnimatorListenerAdapter() {
            @Override public void onAnimationEnd(Animator animation) {
                final ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(view, View.ALPHA, 1);
                alphaAnimator.start();
            }
        });
        animator.start();
    }

    public static void openH(final View view,int viewHeight){
        if(viewHeight<=0){
            viewHeight = height;
        }
        view.setVisibility(View.VISIBLE);
//            改变高度的动画，具体操作点进去看
//        测量扩展动画的起始高度和结束高度
        int start = view.getMeasuredHeight();
        view.measure(View.MeasureSpec.makeMeasureSpec(view.getMeasuredWidth(), View.MeasureSpec.AT_MOST), View.MeasureSpec.makeMeasureSpec(viewHeight, View.MeasureSpec.EXACTLY));
        int end = view.getMeasuredHeight();
        final Animator animator = LayoutAnimator.ofHeight(view, start, end);
//            扩展的动画结束后透明度动画开始
        animator.addListener(new AnimatorListenerAdapter() {
            @Override public void onAnimationEnd(Animator animation) {
                final ObjectAnimator alphaAnimator = ObjectAnimator.ofFloat(view, View.ALPHA, 1);
                alphaAnimator.start();
            }
        });
        animator.start();
    }
    public static void openH2(final View view){
        view.setVisibility(View.VISIBLE);
//            改变高度的动画，具体操作点进去看
//        测量扩展动画的起始高度和结束高度
        int start = view.getMeasuredHeight();
        view.measure(View.MeasureSpec.makeMeasureSpec(view.getMeasuredWidth(), View.MeasureSpec.AT_MOST), View.MeasureSpec.makeMeasureSpec(height, View.MeasureSpec.UNSPECIFIED));
        int end = view.getMeasuredHeight();
//        Log.e(TAG,"start:"+start+",end:"+end);
        final Animator animator = LayoutAnimator.ofHeight(view, start, end);
        animator.start();
    }
    public static void openH2(final View view,int viewHeight){
        if(viewHeight<=0){
            viewHeight = height;
        }
        view.setVisibility(View.VISIBLE);
//            改变高度的动画，具体操作点进去看
//        测量扩展动画的起始高度和结束高度
        int start = view.getMeasuredHeight();
        view.measure(View.MeasureSpec.makeMeasureSpec(view.getMeasuredWidth(), View.MeasureSpec.AT_MOST), View.MeasureSpec.makeMeasureSpec(viewHeight, View.MeasureSpec.UNSPECIFIED));
        int end = view.getMeasuredHeight();
//        Log.e(TAG,"start:"+start+",end:"+end);
        final Animator animator = LayoutAnimator.ofHeight(view, start, end);
        animator.start();
    }
    //horizontal

    private static int width ;
    public static int closeHorizontal(final View view) {
//        测量扩展动画的起始高度和结束高度
        int start = view.getMeasuredWidth();
        view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(view.getMeasuredWidth(), View.MeasureSpec.AT_MOST));
        int end = start - view.getMeasuredWidth();
        width = start;
        Animator animator = LayoutAnimator.ofWidth(view, start, end);
//            扩展的动画结束后透明度动画开始
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                view.setVisibility(View.GONE);
            }
        });
        animator.setDuration(300);
        animator.start();
        return width;
    }
    /**
     * 横着打开
     * @param view
     */
    public static void openHorizontal(final View view){

        int start = view.getMeasuredWidth();
        view.setVisibility(View.VISIBLE);
//            改变高度的动画，具体操作点进去看
//        测量扩展动画的起始高度和结束高度
//        Log.e(TAG, "openHorizontal: "+start);
        view.measure(View.MeasureSpec.makeMeasureSpec(width, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(view.getMeasuredWidth(), View.MeasureSpec.AT_MOST));
        int end = view.getMeasuredWidth();
//        Log.e(TAG,"start:"+start+",end:"+end);
        if (start == end){
            start = 1;
        }
        final Animator animator = LayoutAnimator.ofWidth(view, start, end);
        animator.setDuration(300);
        animator.start();
    }
    /**
     * 横着打开
     * @param view
     * @param viewWidth
     */
    public static void openHorizontal(final View view,int viewWidth){
    if(viewWidth<=0){
        viewWidth = width;
    }
    view.setVisibility(View.VISIBLE);
//            改变高度的动画，具体操作点进去看
//        测量扩展动画的起始高度和结束高度
    int start = view.getMeasuredWidth();
    view.measure(View.MeasureSpec.makeMeasureSpec(viewWidth, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(view.getMeasuredHeight(), View.MeasureSpec.AT_MOST));
    int end = view.getMeasuredWidth();
//    Log.e(TAG,"start:"+start+",end:"+end);
    final Animator animator = LayoutAnimator.ofHeight(view, start, end);
    animator.start();
}
}
