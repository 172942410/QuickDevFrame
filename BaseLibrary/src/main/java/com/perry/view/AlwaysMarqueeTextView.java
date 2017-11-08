package com.perry.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by lipengjun on 2017/7/11.
 */

public class AlwaysMarqueeTextView extends android.support.v7.widget.AppCompatTextView {

    public AlwaysMarqueeTextView(Context context) {
        super(context);
    }
    public AlwaysMarqueeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public AlwaysMarqueeTextView(Context context, AttributeSet attrs,
                                 int defStyle) {
        super(context, attrs, defStyle);
    }
    @Override
    public boolean isFocused() {
        return true;
    }
}
