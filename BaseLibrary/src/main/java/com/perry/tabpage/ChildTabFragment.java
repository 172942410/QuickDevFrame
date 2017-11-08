package com.perry.tabpage;

import android.support.v4.app.Fragment;

/**
 * User: perry.li(172942410@qq.com)</br>
 * Date: 2014-08-27</br>
 * Time: 09:01</br>
 * FIXME
 */
public class ChildTabFragment extends Fragment {
    private String title;
    private int iconId;
    private int num;
    TabView mTabView;

    private int iconIdNormal;
    private int iconIdSelected;
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }
    /**
     * 满足渐变效果
     * @param iconIdNormal
     * @param iconIdSelected
     * 
     */
    public void setIconIdShade(int iconIdNormal,int iconIdSelected) {
        this.iconIdNormal = iconIdNormal;
        this.iconIdSelected = iconIdSelected;
    }
    public int getIconIdNormal() {
        return iconIdNormal;
    }
    public int getIconIdSelected() {
        return iconIdSelected;
    }
    /**
     * 设置显示的消息数量
     * -1时为小红点
     */
    public void setNum(int num) {
        this.num = num;
        if (mTabView != null) {
            mTabView.setLabel(num > 99 ? TabView.STR_MSG_MORE : num+ "");
        }
    }

    /**
     * 获得目前的消息数量
     */
    public int getNum() {
        return num;
    }
//    @Override
//    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View view = inflater.inflate(R.layout.fragment, null, false);
//        TextView textView = (TextView) view.findViewById(R.id.text);
//        textView.setText(getTitle());
//        return view;
//    }

    public void setTabView(TabView tabView) {
        // TODO Auto-generated method stub
        mTabView = tabView;
    }
	
}
