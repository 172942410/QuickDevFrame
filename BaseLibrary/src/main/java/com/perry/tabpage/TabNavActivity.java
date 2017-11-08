package com.perry.tabpage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.Log;

import com.perry.R;
import com.perry.activity.BaseCompatActivity;
import com.perry.animator.AnimatorUtils;
import com.perry.tabpage.indicator.FragmentAdapter;

import java.util.List;


public abstract class TabNavActivity extends BaseCompatActivity {

    private static final String TAG = "TabNavActivity";
    private ViewPager mViewPager;
    private IconTabPageIndicator mIndicator;
//    private String[] tabArray;//
//    private PowerManager.WakeLock wl;
    private FragmentAdapter adapter;
    private boolean isIndicatorHide;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_tab);//在此调用会顶到statubar上；造成标题重叠问题
        setSwipeBackEnable(false);//设置主页不可以滑动返回；
    }

    @Override
    public int initLayoutId() {
        return R.layout.activity_tab;
    }

    @Override
    public void findView() {

    }

    @Override
    public void initView() {
        mViewPager = (ViewPager) findViewById(R.id.view_pager);
        //设置viewpager预加载页面
        mIndicator = (IconTabPageIndicator) findViewById(R.id.indicator);
        List<ChildTabFragment> fragments = initFragments();
        adapter = new FragmentAdapter(fragments, this.getSupportFragmentManager());
        mViewPager.setAdapter(adapter);
        mViewPager.setOffscreenPageLimit(3);//设置viewpager中的fragment左右3以内的不被销毁
        mIndicator.setViewPager(mViewPager);
        mIndicator.setCurrentItemClick(0);
        mIndicator.setOnPageChangeListener(onPageChangeListener);
    }

    /**
     * 切换显示底部指示器的显示
     * @return true 显示了 ； false 隐藏了
     */
    public boolean switchIndicator(){
        if(mIndicator.isShown()){
            hideIndicator();
            return false;
        }else{
            showIndicator();
            return true;
        }
    }

    public boolean isIndicatorHide(){
        return isIndicatorHide;
    }

    /**
     * 底部指示器的动画执行高度
     */
    private int heightAnim;

    /**
     * 隐藏底部指示器的动画和结果
     */
    public void hideIndicator(){
        isIndicatorHide = true;
        heightAnim = AnimatorUtils.closeH(mIndicator);
    }

    /**
     * 从底部弹出底部指示器的动画以及结果
     */
    public void showIndicator(){
        isIndicatorHide = false;
//        AnimatorUtils.openH(mIndicator,heightAnim);
        AnimatorUtils.openH2(mIndicator,heightAnim);
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    /**
     * fragments = new ArrayList<ChildTabFragment>();
     * todayOrderFragment = new TodayOrderFragment();
     * todayOrderFragment.setIconIdShade(R.drawable.tabbar_msg_n,R.drawable.tabbar_msg_p);
     * fragments.add(todayOrderFragment);
     * historyOrderFragment =  new HistoryOrderFragment();
     * //mainContactsFragment.setIconId(R.drawable.tab_contacts);
     * historyOrderFragment.setIconIdShade(R.drawable.tabbar_contacts_n,R.drawable.tabbar_contacts_p);
     * fragments.add(historyOrderFragment);
     * mainMeFragment = new MainMeFragment();
     * mainMeFragment.setIconIdShade(R.drawable.tabbar_me_n,R.drawable.tabbar_me_p);
     * fragments.add(mainMeFragment);
     *
     * @return
     */
    protected abstract List<ChildTabFragment> initFragments();

    OnPageChangeListener onPageChangeListener = new OnPageChangeListener() {

        @Override
        public void onPageSelected(int arg0) {
            mIndicator.setCurrentItemClick(arg0);
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {
        }
    };

    /**
     * 当activity在后台没有被回收，则当被intent之后，跳到这里先走onNewIntent方法
     */
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.e(TAG, "onNewIntent 走没走");
    }

    @Override
    public void onBackPressed() {
        //返回到home界面
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

}

