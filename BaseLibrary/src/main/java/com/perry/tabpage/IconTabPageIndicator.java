package com.perry.tabpage;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.nineoldandroids.view.ViewHelper;
import com.perry.R;
import com.perry.tabpage.indicator.IconPagerAdapter;
import com.perry.tabpage.indicator.PageIndicator;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;

/**
 * User: perry.li(172942410@qq.com)</br>
 * Date: 2015-05-06</br>
 * Time: 17:11</br>
 * FIXME
 */
@SuppressLint("NewApi") public class IconTabPageIndicator extends LinearLayout implements PageIndicator {
    /**
     * Title text used when no title is provided by the adapter.</br>
     * 标题 使用的无标题在适配器中设置。
     */
    private static final CharSequence EMPTY_TITLE = "";

    private static final String TAG = "IconTabPageIndicator";

    private String orgnames;

    /**
     * Interface for a callback when the selected tab has been reselected.</br>
     * 接口回调时选定的标签被重新选择。
     */
    public interface OnTabReselectedListener {
        /**
         * Callback when the selected tab has been reselected.
         *
         * @param position Position of the current center item.
         */
        void onTabReselected(int position);
    }

    private Runnable mTabSelector;

    private final View.OnClickListener mTabClickListener = new View.OnClickListener() {
        public void onClick(View view) {

            TabView tabView = (TabView) view;
            final int oldSelected = mViewPager.getCurrentItem();
            final int newSelected = tabView.getIndex();
            mViewPager.setCurrentItem(newSelected, false);// false表示 代码切换 pager 的时候不带scroll动画
            setCurrentItemClick(newSelected);//设置点击tab的时候底部颜色切换
            if (oldSelected == newSelected && mTabReselectedListener != null) {
                mTabReselectedListener.onTabReselected(newSelected);
            }
        }
    };

    private final LinearLayout mTabLayout;

    private ViewPager mViewPager;
    private ViewPager.OnPageChangeListener mListener;

    private int mSelectedTabIndex;

    private OnTabReselectedListener mTabReselectedListener;

    private int mTabWidth;
    private Context context;

    public IconTabPageIndicator(Context context) {
        this(context, null);
        this.context = context;
    }

    public IconTabPageIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        setHorizontalScrollBarEnabled(false);
        this.context = context;
        mTabLayout = new LinearLayout(context, null, R.attr.tabPageIndicator);
        addView(mTabLayout, new ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT));
    }

    public void setOnTabReselectedListener(OnTabReselectedListener listener) {
        mTabReselectedListener = listener;
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        final int widthMode = View.MeasureSpec.getMode(widthMeasureSpec);
        final boolean lockedExpanded = widthMode == View.MeasureSpec.EXACTLY;

        final int childCount = mTabLayout.getChildCount();

        if (childCount > 1 && (widthMode == MeasureSpec.EXACTLY || widthMode == MeasureSpec.AT_MOST)) {
            mTabWidth = MeasureSpec.getSize(widthMeasureSpec) / childCount;
        } else {
            mTabWidth = -1;
        }

        final int oldWidth = getMeasuredWidth();
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        final int newWidth = getMeasuredWidth();

        if (lockedExpanded && oldWidth != newWidth) {
            // Recenter the tab display if we're at a new (scrollable) size.
            setCurrentItem(mSelectedTabIndex);
        }
    }

    private void animateToTab(final int position) {
        final View tabView = mTabLayout.getChildAt(position);
        if (mTabSelector != null) {
            removeCallbacks(mTabSelector);
        }
        mTabSelector = new Runnable() {
            public void run() {
                final int scrollPos = tabView.getLeft() - (getWidth() - tabView.getWidth()) / 2;
                mTabSelector = null;
            }
        };
        post(mTabSelector);
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (mTabSelector != null) {
            // Re-post the selector we saved
            post(mTabSelector);
        }
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (mTabSelector != null) {
            removeCallbacks(mTabSelector);
        }
    }

    private void addTab(int index, CharSequence text, int iconResIdNormal, int iconResIdSelected , int iconNum, ChildTabFragment childTabFragment) {

        final TabView tabView = new TabView(getContext());
        tabView.setIndex(index);
        tabView.setOnClickListener(mTabClickListener);
        tabView.setText(text);

        if (iconResIdNormal > 0) {
            tabView.setIcon(iconResIdNormal);
        }
        if (iconResIdSelected > 0) {
            tabView.setIconSelected(iconResIdSelected);
        }
        if (iconNum != 0) {
            tabView.setLabel(iconNum + "");
        }
        childTabFragment.setTabView(tabView);
        mTabLayout.addView(tabView, new LinearLayout.LayoutParams(0, MATCH_PARENT, 1));

    }

    private void addTab(int index, CharSequence text, int iconResId, int iconNum, ChildTabFragment childTabFragment) {

        final TabView tabView = new TabView(getContext());
        tabView.setIndex(index);
        tabView.setOnClickListener(mTabClickListener);
        tabView.setText(text);

        if (iconResId > 0) {
            tabView.setIcon(iconResId);
        }

        if (iconNum != 0) {
            tabView.setLabel(iconNum + "");
        }
        childTabFragment.setTabView(tabView);
        mTabLayout.addView(tabView, new LinearLayout.LayoutParams(0, MATCH_PARENT, 1));

    }

    private void addTab(int index, CharSequence text, int iconResId) {
        final TabView tabView = new TabView(getContext());
        tabView.setIndex(index);
        tabView.setOnClickListener(mTabClickListener);
        tabView.setText(text);

        if (iconResId > 0) {
            tabView.setIcon(iconResId);
        }

        mTabLayout.addView(tabView, new LinearLayout.LayoutParams(0, MATCH_PARENT, 1));
    }

    @Override
    public void onPageScrollStateChanged(int arg0) {
        if (mListener != null) {
            mListener.onPageScrollStateChanged(arg0);
        }
    }

    @Override
    public void onPageScrolled(int arg0, float arg1, int arg2) {
    	float effectOffset = isSmall(arg1) ? 0 : arg1;
//    	animateFade(mLeft, mRight, effectOffset, arg0);
//    	slideCallback.callBack(arg0, 1 - effectOffset);//left
//    	slideCallback.callBack(arg0 + 1, effectOffset);//right
    	initShadePager(arg0, 1-effectOffset);
    	initShadePager(arg0+1, effectOffset);
        if (mListener != null) {
            mListener.onPageScrolled(arg0, arg1, arg2);
        }
    }

    @Override
    public void onPageSelected(int arg0) {
        setCurrentItem(arg0);
        if (mListener != null) {
            mListener.onPageSelected(arg0);
        }
    }

    public void setViewPager(ViewPager view, String orgname) {
        this.orgnames = orgname;
        if (mViewPager == view) {
            return;
        }
        if (mViewPager != null) {
            mViewPager.setOnPageChangeListener(null);
        }
        final PagerAdapter adapter = view.getAdapter();
        if (adapter == null) {
            throw new IllegalStateException("ViewPager does not have adapter instance.");
        }
        mViewPager =  view;
        view.setOnPageChangeListener(this);
        notifyDataSetChanged();
    }

    @Override
    public void setViewPager(ViewPager view) {
        if (mViewPager == view) {
            return;
        }
        if (mViewPager != null) {
            mViewPager.setOnPageChangeListener(null);
        }
        final PagerAdapter adapter = view.getAdapter();
        if (adapter == null) {
            throw new IllegalStateException("ViewPager does not have adapter instance.");
        }
        mViewPager =  view;
        view.setOnPageChangeListener(this);
        notifyDataSetChanged();
    }

    public void notifyDataSetChanged() {
        mTabLayout.removeAllViews();
        PagerAdapter adapter = mViewPager.getAdapter();
        IconPagerAdapter iconAdapter = null;
        if (adapter instanceof IconPagerAdapter) {
            iconAdapter = (IconPagerAdapter) adapter;
            iconAdapter.setViewPager(mViewPager);
        }
        final int count = adapter.getCount();
        for (int i = 0; i < count; i++) {
            CharSequence title = adapter.getPageTitle(i);
            if (title == null) {
                title = EMPTY_TITLE;
            }
            int iconResId = 0;
            int iconResIdSelected = 0;
            int iconNum = 0;
            if (iconAdapter != null) {
                iconResId = iconAdapter.getIconResIdNormal(i);
                iconResIdSelected = iconAdapter.getIconResIdSelected(i);
                iconNum = iconAdapter.getIconNum(i);
            }
//            addTab(i, title, iconResId);
//            addTab(i, title, iconResId, iconNum, iconAdapter.getChildTabFragment(i));
            addTab(i, title, iconResId, iconResIdSelected,iconNum,iconAdapter.getChildTabFragment(i));
            //添加显示标记数量；

        }
        if (mSelectedTabIndex > count) {
            mSelectedTabIndex = count - 1;
        }
        setCurrentItem(mSelectedTabIndex);
        requestLayout();
    }

    @Override
    public void setViewPager(ViewPager view, int initialPosition) {
        setViewPager(view);
        setCurrentItem(initialPosition);
    }

    @Override
    public void setCurrentItem(int item) {
        if (mViewPager == null) {
            throw new IllegalStateException("ViewPager has not been bound.");
        }
        mSelectedTabIndex = item;
        mViewPager.setCurrentItem(item, false);

        final int tabCount = mTabLayout.getChildCount();
        for (int i = 0; i < tabCount; i++) {
            final TabView child = (TabView) mTabLayout.getChildAt(i);
            final boolean isSelected = (i == item);
            child.setSelected(isSelected);
            if (isSelected) {
                animateToTab(item);
            }
        }
    }
    /**
     * 设置点击tab的时候底部颜色切换
     * @param item
     */
    public void setCurrentItemClick(int item) {
        if (mViewPager == null) {
            throw new IllegalStateException("ViewPager has not been bound.");
        }
        mSelectedTabIndex = item;
//        mViewPager.setCurrentItem(item, false);

        final int tabCount = mTabLayout.getChildCount();
        for (int i = 0; i < tabCount; i++) {
            final TabView child = (TabView) mTabLayout.getChildAt(i);
            child.getNormal().setAlpha(1f);
            child.getSelected().setAlpha(0f);
            final boolean isSelected = (i == item);
            child.setSelected(isSelected);
            if (isSelected) {
            	child.getNormal().setAlpha(0f);
                child.getSelected().setAlpha(1f);
                animateToTab(item);
            }
        }
    }

    @Override
    public void setOnPageChangeListener(ViewPager.OnPageChangeListener listener) {
        mListener = listener;
    }

	
	 /**
     * 初始化滑动渐变
     * @param position
     */
	private void initShadePager(int position, float positionOffset) {
		 final TabView tabView = 
				 (TabView) mTabLayout.getChildAt(position);
		 if(tabView == null){
			 return;
		 }
		 ViewHelper.setAlpha(tabView.getSelected(), positionOffset);
		 ViewHelper.setAlpha(tabView.getNormal(), 1 - positionOffset);
		//设置底部tab的滑动渐变
	}

	private boolean isSmall(float positionOffset) {
		return Math.abs(positionOffset) < 0.0001;
	}
	
}

