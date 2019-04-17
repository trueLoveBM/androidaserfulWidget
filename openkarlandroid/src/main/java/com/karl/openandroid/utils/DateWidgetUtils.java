package com.karl.openandroid.utils;

import android.content.Context;
import android.widget.TextView;

import com.karl.openandroid.widget.CustomDatePicker;

import java.util.Calendar;
import java.util.Date;

/**
 * 时间选择器的帮助类，帮助简易使用
 * author Fan.Huang
 * eamil  940523199@qq.com
 * created 2019/4/17 11:28
 */
public class DateWidgetUtils {

    /**
     * 读取TextView的值，转换为年月日，并在日期控件显示出来
     * @param tv
     * @param picker
     */
    public static void showTextViewDate(TextView tv, CustomDatePicker picker) {
        String s = tv.getText().toString();
        long currentDate = DateFormatUtils.str2Long(s, false);
        if (currentDate != 0)
            picker.show(currentDate);
        else
            picker.show(System.currentTimeMillis());
    }

    /**
     * 读取TextView的值，转换为年月日时分，并在日期控件显示出来
     * @param tv
     * @param picker
     */
    public static void showTextViewTime(TextView tv, CustomDatePicker picker) {
        String s = tv.getText().toString();
        long currentDate = DateFormatUtils.str2Long(s, true);
        if (currentDate != 0)
            picker.show(currentDate);
        else
            picker.show(System.currentTimeMillis());
    }

    public static Date getTextViewDate(TextView tv) {
        String s = tv.getText().toString();
        long currentDate = DateFormatUtils.str2Long(s, true);
        if (currentDate != 0)
            return new Date(currentDate);
        else
            return null;
    }

    public static Date getTextViewDateWithoutHourAndSeconds(TextView tv) {
        String s = tv.getText().toString();
        long currentDate = DateFormatUtils.str2Long(s, false);
        if (currentDate != 0)
            return new Date(currentDate);
        else
            return null;
    }


    /**
     * 获取当前时间所在年份的时间选择器
     *
     * @param context
     * @param callback
     * @return
     */
    public static CustomDatePicker getDefaultTimerPicker(Context context, CustomDatePicker.Callback callback) {
        Calendar date = Calendar.getInstance();
        int Year = date.get(Calendar.YEAR);
        long beginTime = DateFormatUtils.str2Long(Year + "-01-01 00:00", false);
        long endTime = DateFormatUtils.str2Long(Year + "-12-31 11:59", false);
        return getTimerPicker(context, beginTime, endTime, callback);
    }

    /**
     * 获取起止时间自定义的时间选择器
     *
     * @param context
     * @param beginTime
     * @param endTime
     * @param callback
     * @return
     */
    public static CustomDatePicker getTimerPicker(Context context, long beginTime, long endTime, CustomDatePicker.Callback callback) {
        CustomDatePicker mTimerPicker;
//        String beginTime = "2018-10-17 18:00";
//        String endTime = DateFormatUtils.long2Str(System.currentTimeMillis(), true);


        // 通过日期字符串初始化日期，格式请用：yyyy-MM-dd HH:mm
        mTimerPicker = new CustomDatePicker(context, callback, beginTime, endTime);
        // 允许点击屏幕或物理返回键关闭
        mTimerPicker.setCancelable(true);
        // 显示时和分
        mTimerPicker.setCanShowPreciseTime(true);
        // 允许循环滚动
        mTimerPicker.setScrollLoop(true);
        // 允许滚动动画
        mTimerPicker.setCanShowAnim(true);
        return mTimerPicker;
    }

    /**
     * 获取当前时间所在年份的时间选择器
     *
     * @param context
     * @param callback
     * @return
     */
    public static CustomDatePicker getDefaultDatePicker(Context context, CustomDatePicker.Callback callback) {
        Calendar date = Calendar.getInstance();
        int Year = date.get(Calendar.YEAR);
        long beginTime = DateFormatUtils.str2Long(Year + "-01-01", false);
        long endTime = DateFormatUtils.str2Long(Year + "-12-31", false);
        return getDatePicker(context, beginTime, endTime, callback);
    }


    public static CustomDatePicker getDatePicker(Context context, long beginTime, long endTime, CustomDatePicker.Callback callback) {
        CustomDatePicker mTimerPicker;
//        String beginTime = "2018-10-17";
//        String endTime = DateFormatUtils.long2Str(System.currentTimeMillis(), false);


        // 通过日期字符串初始化日期，格式请用：yyyy-MM-dd HH:mm
        mTimerPicker = new CustomDatePicker(context, callback, beginTime, endTime);
        // 允许点击屏幕或物理返回键关闭
        mTimerPicker.setCancelable(true);
        // 显示时和分
        mTimerPicker.setCanShowPreciseTime(false);
        // 允许循环滚动
        mTimerPicker.setScrollLoop(true);
        // 允许滚动动画
        mTimerPicker.setCanShowAnim(true);
        return mTimerPicker;
    }
}
