package com.xinxiang.openkarlandroid;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.karl.openandroid.entity.signature.IPenConfig;
import com.karl.openandroid.utils.BitmapUtils;
import com.karl.openandroid.utils.FileOpenUtils;
import com.karl.openandroid.widget.SignaturePadView;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 签名板
 */
public class SignaturePadActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBtnStrokePen;
    private Button mBtnClearCanvas;
    private Button mBrushPen;
    private Button btn_export;
    private TextView tv_export_name;
    private SignaturePadView signaturePad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signature_pad);

        mBtnStrokePen = (Button) findViewById(R.id.btn_stroke_pen);
        mBtnClearCanvas = (Button) findViewById(R.id.btn_clear_canvas);
        mBrushPen = (Button) findViewById(R.id.btn_brush_pen);
        btn_export = (Button) findViewById(R.id.btn_export);
        tv_export_name=findViewById(R.id.tv_export_name);
        mBtnStrokePen.setOnClickListener(this);
        mBtnClearCanvas.setOnClickListener(this);
        mBrushPen.setOnClickListener(this);
        btn_export.setOnClickListener(this);
        tv_export_name.setOnClickListener(this);
        signaturePad = findViewById(R.id.signaturePad);
    }

    public static void showActivity(Context context) {
        Intent intent = new Intent(context, SignaturePadActivity.class);
        context.startActivity(intent);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_stroke_pen:
                signaturePad.setCanvasCode(IPenConfig.STROKE_TYPE_PEN);
                break;
            case R.id.btn_clear_canvas:
                signaturePad.setCanvasCode(IPenConfig.STROKE_TYPE_ERASER);
                break;
            case R.id.btn_brush_pen:
                signaturePad.setCanvasCode(IPenConfig.STROKE_TYPE_BRUSH);
                break;
            case R.id.btn_export:
                try {
                    Bitmap bitmap = BitmapUtils.getViewBitmap(signaturePad);
                    Date date = new Date();
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_SS_mm");
                    String pictureName = simpleDateFormat.format(date) + ".jpg";
                    BitmapUtils.saveBitmapFile(bitmap, Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath(), pictureName);
                    Toast.makeText(this,"导出成功 "+pictureName,Toast.LENGTH_SHORT).show();
                    tv_export_name.setText(pictureName);
                }catch (Exception ex){
                    Toast.makeText(this,"导出失败"+ex.getMessage(),Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.tv_export_name:
                if(!TextUtils.isEmpty(tv_export_name.getText().toString())) {
                    FileOpenUtils.getInstance().openFile(this, Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath() + "/" + tv_export_name.getText().toString());
                }else{
                    Toast.makeText(this,"请先导出！",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
