package com.extendservice.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.perry.tabpage.ChildTabFragment;
/**
 * User: perry.li(172942410@qq.com)
 * Date: 2015-05-07
 * Time: 09:01
 * FIXME
 */
public abstract class BaseTabFragment extends ChildTabFragment {

    private static final String TAG = "BaseFragment";

    protected Activity mActivity;

    protected  Callback mCallback;

    protected Toolbar toolbar;
    protected TextView textViewTitle;

    /**
     * 布局文件对应的id
     */
    public abstract int initViewId();

    public abstract void initView();

    public View view;

    public View findViewById(int id){
        return  view.findViewById(id);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = activity;
        if (activity instanceof  Callback) {
            mCallback = (Callback) activity;
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        int id = initViewId();
        view = inflater.inflate(id, container, false);
        return view;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initToolBar();
        initView();
    }
    @Override
    public void onStart() {
        super.onStart();
    }
    @Override
    public void onResume() {
        super.onResume();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();

    }


    /**
     * 回调
     */
    public interface Callback {
        /**
         * 未读消息
         */
        public void setUnread();

    }

    //===============================取消预加载==============================================
    /** Fragment当前状态是否可见 */
    protected boolean isVisible;
    /**
     * 是否是第一次加载
     * 参数默认true
     * 只要运行过 lazyLoad()方法则变为false；
     *
     */
    protected boolean isFirstLoad = true;
    /**
     * onCreateView是否执行加载完毕
     * 是否可以开始加载数据
     * true 为加载完毕可继续执行；
     *
     */
    protected boolean isInit;
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);

        if(getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }
    /**
     * 可见
     * 延迟加载
     * 在页面显示时调用此方法；
     *
     */
    protected void onVisible() {
        if (!isVisible || !isFirstLoad || !isInit) {
            return;
        }
        if(isFirstLoad&&isVisible){
            lazyLoad();
        }
        isFirstLoad = false;
    }

    /**
     * 不可见
     */
    protected void onInvisible() {

    }

    /**
     * 延迟加载
     * 子类必须重写此方法
     * 在页面第一次显示时调用此方法；
     */
    protected abstract void lazyLoad();

    /**
     * 在子类中必须实现
     * super.initToolBar();和
     * 自定义toolbar的id必须定义成:
     * R.id.toolbar
     * 才可以继承tool bar 标题栏
     */
    protected  void initToolBar(){
        // 设置ToolBar
        toolbar = (Toolbar) getView().findViewById(com.perry.R.id.toolbar);
        if (toolbar != null) {
//            toolbar.setTitle("特使");//这个是歪的
            textViewTitle = (TextView) getView().findViewById(com.perry.R.id.toolbar_title);
            textViewTitle.setText(getTitle());
            toolbar.setNavigationIcon(android.R.color.transparent);
        }else{
            Log.e(TAG,"toolbar is null");
        }
    }

    /**
     * 设置显示标题
     * 在 super.onStart()之后调用
     * @param titleStr
     */
     public void setTitle(String titleStr){
         if(titleStr == null || titleStr.length() == 0){
             return;
         }
        if(textViewTitle != null) {
            textViewTitle.setText(titleStr);
        }
    }
}
