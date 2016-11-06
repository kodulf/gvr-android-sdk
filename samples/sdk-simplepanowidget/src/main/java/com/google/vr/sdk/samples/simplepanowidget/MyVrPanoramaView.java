package com.google.vr.sdk.samples.simplepanowidget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.vr.sdk.widgets.pano.VrPanoramaView;

/**
 * Created by zhangyinshan on 16/11/5.
 */
public class MyVrPanoramaView extends VrPanoramaView
{
    Context context;
    public MyVrPanoramaView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    public MyVrPanoramaView(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    public void setOnTouchListener(OnTouchListener touchListener) {
        super.setOnTouchListener(touchListener);
        Toast.makeText(context,"touch" +
                "", Toast.LENGTH_SHORT).show();
    }

    //    panoWidgetView.setOnTouchListener(new View.OnTouchListener() {
//      @Override
//      public boolean onTouch(View v, MotionEvent event) {
//        Toast.makeText(getApplicationContext(),"touch" +
//                "",Toast.LENGTH_SHORT).show();
//        return true;
//      }
//    });
//
//    panoWidgetView.setOnLongClickListener(new View.OnLongClickListener() {
//      @Override
//      public boolean onLongClick(View v) {
//        Toast.makeText(getApplicationContext(),"long click" +
//                "",Toast.LENGTH_SHORT).show();
//        return true;
//      }
//    });
}
