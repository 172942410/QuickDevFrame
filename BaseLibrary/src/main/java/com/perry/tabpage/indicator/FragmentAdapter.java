package com.perry.tabpage.indicator;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.ViewGroup;

import com.perry.tabpage.ChildTabFragment;

import java.util.List;


/**
 * User: perry.li(172942410@qq.com)</br>
 * Date: 2015-05-05</br>
 * Time: 09:01</br>
 * FIXME
 */
public class FragmentAdapter extends FragmentPagerAdapter implements IconPagerAdapter {

    private List<ChildTabFragment> mFragments;
    private ViewPager mViewPager;
    
    public FragmentAdapter(List<ChildTabFragment> fragments, FragmentManager fm) {
        super(fm);
        mFragments = fragments;
    }

    @Override
    public Fragment getItem(int i) {
        return mFragments.get(i);
    }

    @Override
    public int getIconResId(int index) {
        return mFragments.get(index).getIconId();
    }

    @Override
    public int getCount() {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFragments.get(position).getTitle();
    }

    @Override
    public int getIconNum(int position) {
        // TODO Auto-generated method stub
        return mFragments.get(position).getNum();
    }

    @Override
    public ChildTabFragment getChildTabFragment(int index) {
        // TODO Auto-generated method stub
    	ChildTabFragment childTabFragment= mFragments.get(index);
//    	mViewPager.setObjectForPosition(childTabFragment, index);
        return childTabFragment;
    }

    @Override
	public void destroyItem(ViewGroup container, int position, Object obj) {
    	//此代码需配合 instantiateItem 中的添加代码使用；否则会导致也没无法显示
//		container.removeView(mViewPager.findViewFromObject(position));
    	
	}

//    @Override
//    public Object instantiateItem(ViewGroup container, int position) {
//    	// TODO Auto-generated method stub
//    	Object obj = super.instantiateItem(container, position);
//    	mViewPager.setObjectForPosition(obj, position);
//    	return obj;
//    }

    @Override
    public void setViewPager(ViewPager view){
    	 if (mViewPager == view) {
             return;
         }
    	 mViewPager =  view;
    }

	@Override
	public int getIconResIdNormal(int index) {
		// TODO Auto-generated method stub
		  return mFragments.get(index).getIconIdNormal();
	}

	@Override
	public int getIconResIdSelected(int index) {
		// TODO Auto-generated method stub
		return mFragments.get(index).getIconIdSelected();
	}
    
}
