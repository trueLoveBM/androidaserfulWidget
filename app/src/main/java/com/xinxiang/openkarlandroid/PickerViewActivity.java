package com.xinxiang.openkarlandroid;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.karl.openandroid.widget.PickerView;

import java.util.ArrayList;
import java.util.List;

public class PickerViewActivity extends AppCompatActivity {

    PickerView pickerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picker_view);
        pickerView = findViewById(R.id.picker);
        List<String> data = new ArrayList<>();
        data.add("中国");
        data.add("美国");
        data.add("俄罗斯");
        data.add("日本");
        pickerView.setCanScrollLoop(true);
        pickerView.setDataList(data);
        pickerView.setOnSelectListener(new PickerView.OnSelectListener() {
            @Override
            public void onSelect(View view, String selected) {
                Toast.makeText(PickerViewActivity.this, "选中了" + selected, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onDestroy() {
        pickerView.onDestroy();
        super.onDestroy();
    }

    public static void showActivity(Context context) {
        Intent intent = new Intent(context, PickerViewActivity.class);
        context.startActivity(intent);
    }
}
