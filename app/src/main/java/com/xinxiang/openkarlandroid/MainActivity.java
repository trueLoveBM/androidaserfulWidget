package com.xinxiang.openkarlandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.karl.openandroid.widget.SignaturePadView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnStepTest;
    Button btnWaveView;
    Button btnSwipeItem;
    Button btnCustomDatePicker;
    Button btnPicker;
    Button btnCheckBox;
    Button btnCommonMine;
    Button btnSummerNote;
    Button btnSignaturePad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnStepTest = findViewById(R.id.btnStepTest);
        btnWaveView = findViewById(R.id.btnWaveView);
        btnSwipeItem = findViewById(R.id.btnSwipeItem);
        btnCustomDatePicker= findViewById(R.id.btnCustomDatePicker);
        btnCheckBox=findViewById(R.id.btnCheckBox);
        btnPicker= findViewById(R.id.btnPicker);
        btnCommonMine= findViewById(R.id.btnCommonMine);
        btnSummerNote= findViewById(R.id.btnSummerNote);
        btnSignaturePad= findViewById(R.id.btnSignaturePad);
        btnStepTest.setOnClickListener(this);
        btnWaveView.setOnClickListener(this);
        btnSwipeItem.setOnClickListener(this);
        btnCustomDatePicker.setOnClickListener(this);
        btnPicker.setOnClickListener(this);
        btnCheckBox.setOnClickListener(this);
        btnCommonMine.setOnClickListener(this);
        btnSummerNote.setOnClickListener(this);
        btnSignaturePad.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnStepTest://步骤控件
                SimpleStepViewActivity.showActivity(this);
                break;
            case R.id.btnWaveView://波浪控件
                WaveViewActivity.showActivity(this);
                break;
            case R.id.btnSwipeItem://侧滑菜单
                SwipeItemDemoActivity.showActivity(this);
                break;
            case R.id.btnCustomDatePicker://时间选择器
                CustomDatePickerDemoActivity.showActivity(this);
                break;
            case R.id.btnPicker:
                PickerViewActivity.showActivity(this);
                break;
            case R.id.btnCheckBox:
                CheckBoxActivity.showActivity(this);
                break;
            case R.id.btnCommonMine:
                CommonMineActivity.showActivity(this);
                break;
            case R.id.btnSummerNote://富文本编辑器
                SummerNoteDemoActivity.showActivity(this);
                break;
            case R.id.btnSignaturePad:
                SignaturePadActivity.showActivity(this);
                break;
        }

    }
}
