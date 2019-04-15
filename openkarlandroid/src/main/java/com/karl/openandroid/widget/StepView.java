package com.karl.openandroid.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;


import com.karl.openandroid.utils.StringUtils;
import com.xinxiang.openkarlandroid.R;

import java.util.List;

/**
 * 自定义控件 步骤控件
 * author Fan.Huang
 * eamil  15209187329@qq.com
 * created 2019/2/28 14:23
 */
public class StepView extends View {

    private Context mContext;

    /**
     * 节点状态（0：没做  1：做了未完成  2：做了完成）
     */
    private int stepState;

    /**
     * 线的偏离方向，默认是中间
     * 0 左
     * 1 中间
     * 2 友
     */
    private int lineGravity;

    /**
     * 没做的填充颜色
     */
    private int undoColor;

    /**
     * 未完成的颜色
     */
    private int notOkColor;

    /**
     * 完成的颜色
     */
    private int okColor;

    /**
     * 线条颜色
     */
    private int lineColor;

    /**
     * 节点类型（0：第一个节点 1：中间节点 2：最后一个节点）
     */
    private int nodeType;

    /**
     * 线的宽度
     */
    private float lineWidth;

    /**
     * 圆的半径
     */
    private float circleRadius;

    /**
     * 文字
     */
    private String text;

    /**
     * 文字大小
     */
    private float textSize;

    /**
     * 描述文字
     */
    private String content;
    /**
     * 描述文字大小
     */
    private float contentSize;


    /**
     * 圆距离上方的边距
     */
    private float circleMarginTop;

    private Path path;
    private Paint paint;
    private TextPaint textPaint;

    public StepView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.mContext = context;

        getAttr(attrs);

        initPaint();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);


        //高度就是标题加上内容的高度乘以2再加100dp
        //宽度就是文字加园乘以2再加100dp，假定每行显示20个字符

        //获取一个文字的宽高
        Rect rect1 = new Rect();
        paint.getTextBounds("你", 0, 1, rect1);
        float oneWorldWidth = rect1.width();
        float oneWorldHeight = rect1.height();


        int wrapWidth = (int) (oneWorldWidth + circleRadius) * 2 + dp2px(100);
        int wrapHeight = (int) ((oneWorldHeight) * ((Math.ceil(content.length() / 20) + 1)) + dp2px(100));

        //如果宽高都是warp_content时，设置控件的宽高的大小
        if (widthSpecMode == MeasureSpec.AT_MOST && heightSpecMode == MeasureSpec.AT_MOST) {
            // 高度和宽度都是Wrap_content
            setMeasuredDimension(wrapWidth, wrapHeight);
        } else if (widthSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(wrapWidth, heightSpecSize);
        } else if (heightSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(widthSpecSize, wrapHeight);
        }
    }

    /**
     * 初始化画笔
     */
    private void initPaint() {
        path = new Path();
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setColor(this.lineColor);

        textPaint = new TextPaint();
        textPaint.setColor(Color.BLACK);
        textPaint.setTextAlign(Paint.Align.LEFT);
        textPaint.setTextSize(contentSize);
    }

    /**
     * 获取自定义属性
     *
     * @param attrs
     */
    private void getAttr(AttributeSet attrs) {
        TypedArray typedArray = mContext.obtainStyledAttributes(attrs, R.styleable.StepView);
        stepState = typedArray.getInt(R.styleable.StepView_state, 0);
        lineGravity = typedArray.getInt(R.styleable.StepView_line_gravity, 1);
        undoColor = typedArray.getColor(R.styleable.StepView_undo_color, Color.GRAY);
        notOkColor = typedArray.getColor(R.styleable.StepView_notok_color, Color.RED);
        okColor = typedArray.getColor(R.styleable.StepView_ok_color, Color.GREEN);
        lineColor = typedArray.getColor(R.styleable.StepView_line_color, Color.BLACK);
        nodeType = typedArray.getInt(R.styleable.StepView_node_type, 1);
        lineWidth = typedArray.getDimensionPixelSize(R.styleable.StepView_line_width, 1);
        circleRadius = typedArray.getDimensionPixelOffset(R.styleable.StepView_circle_radius, dp2px(20));
        text = typedArray.getString(R.styleable.StepView_text);
        if (TextUtils.isEmpty(text)) {
            text = "未知节点";
        }
        textSize = typedArray.getDimensionPixelSize(R.styleable.StepView_text_size, sp2px(22));
        content = typedArray.getString(R.styleable.StepView_text_content);
        if (TextUtils.isEmpty(content)) {
            content = "";
        }
        contentSize = typedArray.getDimensionPixelSize(R.styleable.StepView_text_content_size, sp2px(16));
        circleMarginTop = typedArray.getDimensionPixelSize(R.styleable.StepView_circle_margin_top, -1);
        typedArray.recycle();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //重置画笔
        paint.reset();
//        paint.setStyle(Paint.Style.STROKE);
//        paint.setStrokeWidth(lineWidth);
        //先到控件的中间(修改为放到3分之一处)
        float midedleX = 0;

        switch (lineGravity) {
            case 0://最左
                midedleX = circleRadius * 2 - 10;
                break;
            case 1://中
                midedleX = (float) (canvas.getWidth() / 5);
                break;
            case 2://右
                midedleX = canvas.getWidth() - circleRadius * 2 - 10;
                break;
        }
        float midedleY = 0;
        if (circleMarginTop == -1)
            midedleY = canvas.getHeight() / 2;
        else
            midedleY = circleMarginTop;
        paint.setColor(this.lineColor);
        if (nodeType != 0) {
            //先画一个直线，X轴是从中间到中间加线宽，Y轴是从0画到高度一半减半径再减线宽的位置
            canvas.drawLine(midedleX, 0, midedleX, midedleY - circleRadius - lineWidth, paint);
        }
        //再画一个空心圆()
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(midedleX, midedleY, circleRadius + lineWidth, paint);
        //再画一个实心圆
        switch (stepState) {
            case 0:
                paint.setColor(undoColor);
                break;
            case 1:
                paint.setColor(notOkColor);
                break;
            case 2:
                paint.setColor(okColor);
                break;
            default:
                paint.setColor(Color.YELLOW);
                break;
        }
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawCircle(midedleX, midedleY, circleRadius, paint);
        if (nodeType != 2) {
            //接着画下半条线(从X轴依旧那样，Y轴从Y轴中间+线宽+半径到Y轴最大值)
            paint.setColor(lineColor);
            canvas.drawLine(midedleX, midedleY + circleRadius + lineWidth, midedleX, canvas.getHeight(), paint);
        }
        //再画文字(注，文字的y轴未baseLine)

//***********************这里注释的是获取文字宽度的方法*********************
//        float measureText = paint.measureText(text);//获取文本的宽度，粗略结果
//        //获取文本的宽度，精确结果
//        float[] measuredWidth = new float[10];
//        //measuredWidth得到每一个字符的宽度；textWidths字符数
//        int textWidths = paint.getTextWidths(text, measuredWidth);
//***********************这里注释的是获取文字宽度的方法*********************


        Paint.FontMetrics fontMetrics = paint.getFontMetrics();

        float baselineY = midedleY - fontMetrics.top;
        paint.setTextSize(textSize);
        canvas.drawText(text, midedleX + circleRadius + lineWidth + 10, baselineY, paint);

        int contentTextHeight = 0;
//        画一大段内容（换行实现，方式三种）
        if (!TextUtils.isEmpty(content)) {
            paint.setTextSize(contentSize);
            //获取一个文字的宽度
            Rect rect1 = new Rect();
            paint.getTextBounds("你", 0, 1, rect1);
            float oneWorldWidth = rect1.width();
            float oneWorldHeight = rect1.height();

            //获取文本的宽度
            Rect rect = new Rect();
            paint.getTextBounds(content, 0, content.length(), rect);
            int textWidths = rect.width();
            int textHeight = rect.height();

            //文字开始X轴
            float startX = midedleX + circleRadius + lineWidth + 10;
            //文字画到右边距离10dp位置
            float endX = canvas.getWidth() - dp2px(10);
            //文字开始Y轴
            float startY = baselineY + dp2px(30);
            //文字可用的X轴长度
            float canUseX = endX - startX;
            //可用X轴可写的字数
            int num = (int) Math.floor(canUseX / oneWorldWidth);
            //将文字分行
            List<String> str = StringUtils.getStrList(content, num);
            //行距及文字间距
            float rowSpin = dp2px(10);
            float contentMargin = dp2px(30);
            //方式一，按照像素换行
//            for (int i = 0; i < str.size(); i++) {
//                String drawStr = str.get(i);
//                canvas.drawText(drawStr, startX, startY, paint);
//                startY += oneWorldHeight + rowSpin;
//            }

            //方拾贰：每行显示固定字数
            //文字的总行数
//            int rowCount = (int) Math.ceil(content.length() / num);
//            for (int i = 0; i < rowCount; i++) {
//                int x = i * num;//绘制起始字符
//                int y = num * ( i+ 1) - 1;//绘制终止字符
//                if (y > content.length()) {
//                    y = content.length();
//                }
//                canvas.drawText(content, x, y, startX, startY, paint);
//                startY += oneWorldHeight + rowSpin;
//
//            }


            //画一大段内容（第三种方式，通过StaticLayout实现文字换行）
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                canvas.save();
                StaticLayout.Builder builder = StaticLayout.Builder.obtain(content, 0, content.length(), textPaint, (int) canUseX);
                StaticLayout layout = builder.build();
                //将原点移到X,y
                canvas.translate((float) (midedleX * 1.6), midedleY + contentMargin);
                layout.draw(canvas);
                contentTextHeight = layout.getHeight();
                //把当前画布返回（调整）到上一个save()状态之前
                canvas.restore();
            } else {
                canvas.save();
                StaticLayout layout = new StaticLayout(content, textPaint, (int) canUseX,

                        Layout.Alignment.ALIGN_NORMAL, 1.5f, 0f, false);
                //将原点移到X,y
                canvas.translate(startX, startY);
                layout.draw(canvas);
                contentTextHeight = layout.getHeight();
                //把当前画布返回（调整）到上一个save()状态之前
                canvas.restore();
            }
        }
        canvas.save();
        //画照片
//        Bitmap map= BitmapFactory.decodeResource(getResources(),R.drawable.ic_holder);
//        //将原点移到X,y
//        canvas.translate((float) (midedleX * 1.6), midedleY + contentTextHeight);
//        canvas.drawBitmap(map,0,0,paint);


    }

    public void setText(String text) {
        this.text = text;
    }

    public void setStepState(int stepState) {
        this.stepState = stepState;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setNodeType(int nodeType) {
        this.nodeType = nodeType;
    }

    public void setTextSize(float textSize) {
        this.textSize = textSize;
    }

    /**
     * dp 2 px
     *
     * @param dpVal
     */
    protected int dp2px(int dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, getResources().getDisplayMetrics());
    }

    /**
     * sp转换px
     */
    public int sp2px(int spValue) {
        final float fontScale = getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }
}
