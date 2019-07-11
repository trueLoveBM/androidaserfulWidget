package com.karl.openandroid.entity.signature;

import android.graphics.Canvas;
import android.view.MotionEvent;

/**
 * 签字画板的画笔的基类
 */
public abstract class BasePen {

    /**
     * 绘制
     *
     * @param canvas
     */
    public abstract  void draw(Canvas canvas);

    /**
     * 接受并处理onTouchEvent
     *
     * @param event
     * @return
     */
    public  boolean onTouchEvent(MotionEvent event, Canvas canvas){
        return false;
    }

}
