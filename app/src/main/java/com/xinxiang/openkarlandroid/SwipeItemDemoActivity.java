package com.xinxiang.openkarlandroid;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.karl.openandroid.widget.RecycleViewDivider;
import com.karl.openandroid.widget.SwipeItemLayout;
import com.xinxiang.openkarlandroid.adapter.SwipeItemAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 可侧滑的RecycleView演示
 * author Fan.Huang
 * eamil  950523199@qq.com
 * created 2019/4/17 10:31
 */
public class SwipeItemDemoActivity extends AppCompatActivity {

    List<String> data;
    SwipeItemAdapter adapter;
    RecyclerView rvTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe_item_demo);
        rvTest = findViewById(R.id.rvTest);
        data = new ArrayList<>();
        data.add("助燃一血");
        data.add("神来祝我");
        data.add("倒背如流");
        data.add("哈雷克之火葬魔咒");
        data.add("席美尔的精华脉冲");
        adapter = new SwipeItemAdapter(data);

        adapter.setOnItemClickListener(new SwipeItemAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(String s) {
                Toast.makeText(SwipeItemDemoActivity.this, "点击了 " + s, Toast.LENGTH_SHORT).show();
            }
        });
        adapter.setOnMenuClickListener(new SwipeItemAdapter.OnMenuClickListener() {
            @Override
            public void onMenuClick(int menuId, String s) {
                switch (menuId) {
                    case R.id.tv_flag:
                        Toast.makeText(SwipeItemDemoActivity.this, "点击了侧滑菜单标记", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.tv_details:
                        Toast.makeText(SwipeItemDemoActivity.this, "点击了侧滑菜单详情", Toast.LENGTH_SHORT).show();
                        break;
                }

            }
        });
        rvTest.setAdapter(adapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvTest.setLayoutManager(linearLayoutManager);

        rvTest.addItemDecoration(new RecycleViewDivider(this, LinearLayoutManager.VERTICAL));

        //添加侧滑,必须执行添加这个监听才能侧滑
        rvTest.addOnItemTouchListener(new SwipeItemLayout.OnSwipeItemTouchListener(this));

    }


    public static void showActivity(Context context){
        Intent intent=new Intent(context,SwipeItemDemoActivity.class);
        context.startActivity(intent);
    }

}
