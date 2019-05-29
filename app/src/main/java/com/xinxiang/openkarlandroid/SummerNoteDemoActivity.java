package com.xinxiang.openkarlandroid;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.karl.openandroid.widget.Summernote;

public class SummerNoteDemoActivity extends AppCompatActivity {

    Summernote summernote;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summer_note_demo);
        summernote=findViewById(R.id.summernote);
        summernote.setRequestCodeforFilepicker(5);//Any Number which is not being used by other OnResultActivity

        //获取到编辑后的html内容
//        String html_data = summernote.getText()
        //设置html内容到文件
//        summernote.setText("<h2>Hello World.<h2><br><h3> I'am Summernote</h3>");
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        summernote.onActivityResult(requestCode, resultCode, intent);
    }


    public static void showActivity(Context context){
        Intent intent=new Intent(context,SummerNoteDemoActivity.class);
        context.startActivity(intent);
    }
}
