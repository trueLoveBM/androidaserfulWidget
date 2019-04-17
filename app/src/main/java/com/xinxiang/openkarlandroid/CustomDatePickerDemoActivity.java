package com.xinxiang.openkarlandroid;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.karl.openandroid.utils.DateFormatUtils;
import com.karl.openandroid.utils.DateWidgetUtils;
import com.karl.openandroid.widget.CustomDatePicker;

public class CustomDatePickerDemoActivity extends AppCompatActivity {


    TextView tvYMD;
    CustomDatePicker datePicker;
    TextView tvYMDHM;
    CustomDatePicker timePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_date_picker_demo);
        tvYMD = findViewById(R.id.tvYMD);
        datePicker = DateWidgetUtils.getDefaultDatePicker(this, new CustomDatePicker.Callback() {
            @Override
            public void onTimeSelected(long timestamp) {
                tvYMD.setText(DateFormatUtils.long2Str(timestamp, false));
            }
        });
        tvYMD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateWidgetUtils.showTextViewDate(tvYMD,datePicker);
            }
        });


        tvYMDHM = findViewById(R.id.tvYMDHM);
        timePicker = DateWidgetUtils.getDefaultTimerPicker(this, new CustomDatePicker.Callback() {
            @Override
            public void onTimeSelected(long timestamp) {
                tvYMDHM.setText(DateFormatUtils.long2Str(timestamp, true));
            }
        });
        tvYMDHM.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DateWidgetUtils.showTextViewTime(tvYMDHM, timePicker);
            }
        });
    }

    @Override
    protected void onDestroy() {
        datePicker.onDestroy();
        timePicker.onDestroy();
        super.onDestroy();
    }

    public static void showActivity(Context context){
        Intent intent=new Intent(context,CustomDatePickerDemoActivity.class);
        context.startActivity(intent);
    }
}
