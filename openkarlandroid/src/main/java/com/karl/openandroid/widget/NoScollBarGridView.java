package com.karl.openandroid.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * 高度变化，永远不出现滚动条的GridView
 * author Fan.Huang
 * eamil  15209187329@qq.com
 * created 2019/3/22 17:36
 */
public class NoScollBarGridView extends GridView {
    public NoScollBarGridView(Context context) {
        super(context);
    }

    public NoScollBarGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NoScollBarGridView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);

    }

    @Override
    public int getNumColumns() {//可以不复写，但必须在xml中声明android:numColumns="3"
        return 3;
    }
}
