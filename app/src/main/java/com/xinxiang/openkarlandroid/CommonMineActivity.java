package com.xinxiang.openkarlandroid;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.karl.openandroid.widget.MyOneLineView;

/**
 * 通用的我的界面
 * 使用MyLineOne实现
 * author Fan.Huang
 * eamil  940523199@qq.com
 * created 2019/4/17 14:03
 */
public class CommonMineActivity extends AppCompatActivity {

    LinearLayout ll_mine;
    MyOneLineView.OnRootClickListener rootClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common_mine);

        //添加功能菜单
        MyOneLineView.OnRootClickListener rootClickListener = new MyOneLineView.OnRootClickListener() {
            @Override
            public void onRootClick(View view) {
                int position = 0;
                switch ((int) view.getTag()) {
                    case 1:
                        Toast.makeText(CommonMineActivity.this, "我的信息", Toast.LENGTH_SHORT).show();
                        break;
                    case 2:
                        Toast.makeText(CommonMineActivity.this, "关于系统", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        };

        ll_mine = findViewById(R.id.ll_mine);
        ll_mine.addView(new MyOneLineView(this)
                .initMine(R.drawable.ic_launcher_background, "我的信息", "", true)
                .setOnRootClickListener(rootClickListener, 1));
        ll_mine.addView(new MyOneLineView(this)
                .initMine(R.drawable.ic_launcher_background, "关于系统", "", true)
                .setOnRootClickListener(rootClickListener, 2));
    }


    public static void showActivity(Context context){
        Intent intent=new Intent(context,CommonMineActivity.class);
        context.startActivity(intent);
    }
}
