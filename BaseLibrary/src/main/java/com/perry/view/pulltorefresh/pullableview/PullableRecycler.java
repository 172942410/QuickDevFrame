package com.perry.view.pulltorefresh.pullableview;

import android.content.Context;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

public class PullableRecycler extends RecyclerView implements Pullable {

    private static final String TAG = "PullableRecycler";

    AppBarLayout appBarLayout;
    AppBarChangeState state;

    public PullableRecycler(Context context) {
        super(context);
    }

    public PullableRecycler(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PullableRecycler(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public boolean canPullDown() {

        int viewCount = getChildCount();
        if (viewCount == 0) {
            // 没有item的时候也可以下拉刷新
            return true;
        } else if (!canScrollVertically(-1)) {
            if (appBarLayout != null) {
                //需要确认是否有需要滚动的头部
                if(state == AppBarChangeState.OPEN){
                    return true;
                }else{
                    return false;
                }
            }
            // 滑到RecyclerView的顶部了
            return true;
        } else
            return false;
    }

    @Override
    public boolean canPullUp() {
        if (getChildCount() == 0) {
            // 没有item的时候也可以上拉加载
            return true;
        } else if (!canScrollVertically(1)) {
            // 滑到底部了
            return true;
        }
        return false;
    }

    public void addAppBarLayoutScroll(AppBarLayout appBarLayout) {
        this.appBarLayout = appBarLayout;
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (verticalOffset == 0) {
                    state = AppBarChangeState.OPEN;
                } else if (Math.abs(verticalOffset) >= appBarLayout.getTotalScrollRange()) {
                    state = AppBarChangeState.CLOSE;
                } else {
                    state = AppBarChangeState.SCROLL;
                }
            }
        });
    }

    public enum AppBarChangeState {
        OPEN, CLOSE, SCROLL;
    }
}
