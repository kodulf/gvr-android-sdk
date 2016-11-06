package com.google.vr.sdk.samples.simplepanowidget;

import android.content.Context;
import android.hardware.Sensor;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.vr.sdk.widgets.pano.VrPanoramaView;

import java.util.Date;

/**
 * Created by zhangyinshan on 16/11/5.
 */
public class MyVrPanoramaView extends VrPanoramaView
{
    Context context;
    private Date lastTime;
    private float lastY;
    private float lastX;
    private float valveLeng=200;//不能大于的值
    private FrameLayout childView;
    private OnTouchListener onTouchListener;

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

    }

    @Override
    public void onViewAdded(View child) {
        super.onViewAdded(child);
        if(child instanceof FrameLayout) {
            childView = (FrameLayout)child;
            childView.setOnClickListener(null);
//            childView.setOnTouchListener(new OnTouchListener() {
//                @Override
//                public boolean onTouch(View v, MotionEvent event) {
//                    boolean ret = false;
//                    Log.d("kodulf","childView added touch");
//                    return ret;
//                }
//            });

        }
    }

    @Override
    public void onViewRemoved(View child) {
        super.onViewRemoved(child);
        if(child instanceof FrameLayout) {
            childView = (FrameLayout)child;
            onTouchListener = new OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    boolean ret = true;
                    Log.d("kodulf", "childView removed touch"+event.getAction());
                    dealWithEventAction(event);
                    return ret;
                }
            };

            childView.setOnTouchListener(onTouchListener);


//            TextView textView = new TextView(context);
//            textView.setText("hello");
//            textView.setClickable(true);
//            textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
//            textView.setOnClickListener(new OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Log.d("kodulf","click hello");
//                }
//            });
//            childView.addView(textView,200 ,200);
//            childView.setOnTouchListener(new OnTouchListener() {
//                @Override
//                public boolean onTouch(View v, MotionEvent event) {
//                    boolean ret = true;
//                    Log.d("kodulf", "childView removed touch"+event.getAction());
//                    dealWithEventAction(event);
//                    return ret;
//                }
//            });
//            childView.setOnClickListener(new OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Log.d("kodulf","childView removed click");
//                    changeCallBack.change();
//                }
//            });

        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean ret = super.onTouchEvent(event);

        return ret;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        boolean b = super.dispatchTouchEvent(ev);
        Log.d("kodulf","touched"+ev.getAction());
        //TODO 当时全屏幕显示的时候没有调用这个方法
        dealWithEventAction(ev);
        return b;
    }

    private void dealWithEventAction(MotionEvent ev) {
        if(ev.getAction()==0){
                lastTime = new Date();
                lastX = ev.getX();
                lastY = ev.getY();
        }

        if(ev.getAction()==1) {
                Date currentTime = new Date();
                Float currentX = ev.getX();
                Float currentY = ev.getY();
                if(currentTime.getTime() - lastTime.getTime()>500&&Math.abs(currentX-lastX)<valveLeng&&Math.abs(currentY-lastY)<valveLeng) {
                    Log.d("kodulf", "currentTime=" + currentTime.getTime() + " lastTime=" + lastTime.getTime() +
                            " currentx=" + currentX +
                            " lastX=" + lastX +
                            " currenY=" + currentY + " " +
                            " lastY=" + lastY + " ");
                    changeCallBack.change();
                }


        }
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

    public static ChangeCallBack changeCallBack;
    public void setOnChangeCallBack(ChangeCallBack callBack){
        changeCallBack = callBack;

    }
    interface ChangeCallBack{
        public void change();
    }
}
