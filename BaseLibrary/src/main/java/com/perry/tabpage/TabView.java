package com.perry.tabpage;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.perry.R;


@SuppressLint("NewApi") public class TabView extends LinearLayout {

    public static final String STR_MSG_MORE = "...";

    private static final String TAG = "TabView";
    private int mIndex;
    private ImageView mImageView;
    private TextView mTextView;
    //标记；角标；
    private TextView mTextViewLabel;
//    private BadgeView badge;

    private View normalLayout;//没选中的tabview
    private View selectedLayout;//选中的tabview
    private ImageView mImageViewSelected;
    private TextView mTextViewSelected;
    
    public TabView(Context context) {
        super(context, null, R.attr.tabView);
        View view = View.inflate(context, R.layout.tab_view_label, null);
        
        normalLayout = view.findViewById(R.id.tab_normal_layout);
        normalLayout.setAlpha(1f);// 透明度显示
        selectedLayout = view.findViewById(R.id.tab_selected_layout);
        selectedLayout.setAlpha(0f);// 透明的隐藏
        mImageViewSelected = (ImageView) view.findViewById(R.id.tab_image_selected);
        mTextViewSelected = (TextView) view.findViewById(R.id.tab_text_selected);
        
        mImageView = (ImageView) view.findViewById(R.id.tab_image);
        mTextView = (TextView) view.findViewById(R.id.tab_text);
        mTextViewLabel = (TextView) view.findViewById(R.id.tab_label);
//    	badge = new BadgeView(context, mTextViewLabel);
//    	int dip_corner = DensityUtil.dip2px(context, 8);
//		badge.setBackgroundResource(R.drawable.icon_red_point);
//		badge.setBadgePosition(BadgeView.POSITION_TOP_RIGHT);
//		badge.setBadgeMargin(0);
//		badge.setPadding(0, 0, 0, 0);
//		badge.setSingleLine();
//		badge.setHeight(dip_corner);
//		badge.setWidth(dip_corner);
//		badge.setGravity(Gravity.CENTER);
//		badge.hide();
        mTextViewLabel.setBackgroundResource(R.drawable.icon_corner);
        mTextViewLabel.setPadding(0, 0, 0, 0);
        mTextViewLabel.setSingleLine();
        mTextViewLabel.setGravity(Gravity.CENTER);
        mTextViewLabel.setVisibility(View.INVISIBLE);
        this.addView(view);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        // Re-measure if we went beyond our maximum size.
//        if (mTabWidth > 0) {
//            super.onMeasure(MeasureSpec.makeMeasureSpec(mTabWidth, MeasureSpec.EXACTLY),
//                    heightMeasureSpec);
//        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    public void setText(CharSequence text) {
        mTextView.setText(text);
    }

    public void setIcon(int resId) {
        if (resId > 0) {
            mImageView.setImageResource(resId);
        }
    }

    public void setIconSelected(int resId) {
        if (resId > 0) {
        	mImageViewSelected.setImageResource(resId);
        }
    }
    
    /**
     * 设置消息数量
     *
     * @param text
     */
    public void setLabel(CharSequence text) {
//    	if(badge != null){
        if (mTextViewLabel != null) {
            if (text.equals("-1")) {
//    			int dip_corner = DensityUtil.dip2px(getContext(), 1);
//    			Log.e(TAG, "dip_corner:"+dip_corner);
//    			badge.setHeight(dip_corner);
//        		badge.setWidth(dip_corner);
//        		mTextViewLabel.setHeight(dip_corner);
//        		mTextViewLabel.setWidth(dip_corner);
//        		FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) badge.getLayoutParams();
//        		params.width = dip_corner;
//        		params.height = dip_corner;
//        		badge.setLayoutParams(params);
//        		badge.setBackgroundResource(R.drawable.icon_red_point_small);
//    			badge.setText("");
//    			badge.show();
                int dip_corner = DensityUtil.dip2px(getContext(), 11);
                RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mTextViewLabel.getLayoutParams();
                params.width = dip_corner;
                params.height = dip_corner;
                int topMargin = DensityUtil.dip2px(getContext(), 5);
                params.topMargin = topMargin;
//        		Log.e(TAG,"dip_corner="+dip_corner+",topMargin="+topMargin);
                mTextViewLabel.setLayoutParams(params);
//    			mTextViewLabel.setHeight(dip_corner);
//    			mTextViewLabel.setWidth(dip_corner);
//    			mTextViewLabel.setBackgroundResource(R.drawable.icon_red_point_small);
                mTextViewLabel.setText("");
                mTextViewLabel.setVisibility(View.VISIBLE);
            } else if (text.equals("0")) {
//    			badge.hide();
                mTextViewLabel.setVisibility(View.INVISIBLE);
            } else {
//    			badge.setBackgroundResource(R.drawable.icon_red_point);
//    			badge.setText(text); 
//    			badge.show();

                if (STR_MSG_MORE.equals(text)) {
//                    mTextViewLabel.setTextSize(8);
                    RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)mTextViewLabel.getLayoutParams();
                    params.height = DensityUtil.dip2px(getContext(),12);
                    params.width = DensityUtil.dip2px(getContext(),18);
                    mTextViewLabel.setLayoutParams(params);
                    mTextViewLabel.setText("");
                    mTextViewLabel.setBackgroundResource(R.drawable.icon_red_point1);
                } else {
                    int dip_corner = DensityUtil.dip2px(getContext(), 18);
                    RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mTextViewLabel.getLayoutParams();
                    params.width = dip_corner;
                    params.height = dip_corner;
                    mTextViewLabel.setLayoutParams(params);
//    			mTextViewLabel.setHeight(dip_corner);
//    			mTextViewLabel.setWidth(dip_corner);
                    mTextViewLabel.setBackgroundResource(R.drawable.icon_corner);
                    mTextViewLabel.setText(text);
//                    mTextViewLabel.setTextSize(12);
                    mTextViewLabel.setTextSize(10);
                }
                mTextViewLabel.setVisibility(View.VISIBLE);
            }
        }
    }

    /**
     * @return 消息数量的标记数量
     */
    public CharSequence getLabel() {

//    	return badge.getText();
        return mTextViewLabel.getText();
    }

    public int getIndex() {
        return mIndex;
    }

    public void setIndex(int index) {
        this.mIndex = index;
    }

	public View getSelected() {
		// TODO Auto-generated method stub
		return selectedLayout;
	}

	public View getNormal() {
		// TODO Auto-generated method stub
		return normalLayout;
	}

}
