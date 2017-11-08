package com.perry.animator.itemexpandable;

import android.animation.Animator;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by lipengjun on 2017/3/6.
 */

public class ViewHolderAnimator {
    private static final String TAG = "ViewHolderAnimator";

    public static Animator ofItemViewHeight(RecyclerView.ViewHolder holder) {
        View parent = (View) holder.itemView.getParent();
        if (parent == null)
            throw new IllegalStateException("Cannot animate the layout of a view that has no parent");

//        测量扩展动画的起始高度和结束高度
        int start = holder.itemView.getMeasuredHeight();
        holder.itemView.measure(View.MeasureSpec.makeMeasureSpec(parent.getMeasuredWidth(), View.MeasureSpec.AT_MOST), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        int end = holder.itemView.getMeasuredHeight();
//  6
        final Animator animator = LayoutAnimator.ofHeight(holder.itemView, start, end);
//        设定该item在动画开始结束和取消时能否被recycle
        animator.addListener(new ViewHolderAnimatorListener(holder));
//        设定结束时这个item的宽高
        animator.addListener(new LayoutParamsAnimatorListener(holder.itemView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        return animator;
    }

    public static Animator ofItemViewClose(RecyclerView.ViewHolder holder, View expandView) {
        View parent = (View) holder.itemView.getParent();
        if (parent == null)
            throw new IllegalStateException("Cannot animate the layout of a view that has no parent");

//        测量扩展动画的起始高度和结束高度
        int start = holder.itemView.getMeasuredHeight();
        holder.itemView.measure(View.MeasureSpec.makeMeasureSpec(parent.getMeasuredWidth(), View.MeasureSpec.AT_MOST), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        int end = start - expandView.getMeasuredHeight();
//  6
        final Animator animator = LayoutAnimator.ofHeight(holder.itemView, start, end);
//        设定该item在动画开始结束和取消时能否被recycle
        animator.addListener(new ViewHolderAnimatorListener(holder));
//        设定结束时这个item的宽高
        animator.addListener(new LayoutParamsAnimatorListener(holder.itemView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        return animator;
    }

    public static class ViewHolderAnimatorListener implements Animator.AnimatorListener {
        RecyclerView.ViewHolder _holder;
        public ViewHolderAnimatorListener(RecyclerView.ViewHolder holder) {
            _holder = holder;
        }

        @Override
        public void onAnimationStart(Animator animation) {
            _holder.setIsRecyclable(false);
        }

        @Override
        public void onAnimationEnd(Animator animation) {
            _holder.setIsRecyclable(true);
        }

        @Override
        public void onAnimationCancel(Animator animation) {
            _holder.setIsRecyclable(true);
        }

        @Override
        public void onAnimationRepeat(Animator animation) {

        }
    }

}
