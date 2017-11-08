package com.perry.tabpage.indicator;

import android.support.v4.view.ViewPager;

import com.perry.tabpage.ChildTabFragment;

/**
 * User: perry.li(172942410@qq.com)</br>
 * Date: 2015-05-05</br>
 * Time: 09:01</br>
 * FIXME
 */
public interface IconPagerAdapter {
    /**
     * Get icon representing the page at {@code index} in the adapter.
     */
    int getIconResId(int index);

    /**
     * 获取渐变图标id的正常颜色状态
     * 
     */
    int getIconResIdNormal(int index);
    /**
     * 获取渐变图标id的选中颜色状态
     * 
     */
    int getIconResIdSelected(int index);
    
    // From PagerAdapter
    int getCount();

    int getIconNum(int index);

    ChildTabFragment getChildTabFragment(int index);
    
    /**
     * 可以从adapter设置viewpager;做对viewpager的操作
     * @param view
     */
    public void setViewPager(ViewPager view);
}
