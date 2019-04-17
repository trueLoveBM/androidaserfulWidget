package com.xinxiang.openkarlandroid;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.karl.openandroid.widget.CheckBoxSample;

public class CheckBoxActivity extends AppCompatActivity {
    LinearLayout llroot;
    CheckBoxSample cbs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_box);
        cbs=findViewById(R.id.cbs);
        llroot=findViewById(R.id.llroot);
        llroot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cbs.toggle();
            }
        });
    }

    public static void showActivity(Context context){
        Intent intent=new Intent(context,CheckBoxActivity.class);
        context.startActivity(intent);
    }
}
