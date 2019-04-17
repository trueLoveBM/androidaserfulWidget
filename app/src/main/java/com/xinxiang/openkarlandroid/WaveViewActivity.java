package com.xinxiang.openkarlandroid;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.karl.openandroid.widget.WaveViewBySinCos;

public class WaveViewActivity extends AppCompatActivity {

    //波浪1
    WaveViewBySinCos waveViewBySin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wave_view);
        waveViewBySin=findViewById(R.id.waveviewbysin);
    }


    @Override
    protected void onResume() {
        super.onResume();
        waveViewBySin.resumeAnimation();
    }

    @Override
    protected void onPause() {
        super.onPause();
        waveViewBySin.pauseAnimation();
    }

    @Override
    protected void onStop() {
        super.onStop();
        waveViewBySin.stopAnimation();
    }

    public static void showActivity(Context context){
        Intent intent=new Intent(context,WaveViewActivity.class);
        context.startActivity(intent);
    }
}
