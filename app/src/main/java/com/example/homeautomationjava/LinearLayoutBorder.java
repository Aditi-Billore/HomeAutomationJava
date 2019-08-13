package com.example.homeautomationjava;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class LinearLayoutBorder extends LinearLayout {

    Paint paint;

    public LinearLayoutBorder(Context context){
        super(context);
        setWillNotDraw(false);
        paint = new Paint();

    }

    public LinearLayoutBorder(Context context, AttributeSet attr){
        super(context,attr);
        setWillNotDraw(false);
        paint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {


        Paint strokePaint = paint;
        strokePaint.setARGB(255, 255, 0, 0);
        strokePaint.setStyle(Paint.Style.STROKE);
        strokePaint.setStrokeWidth(2);
        Rect r = canvas.getClipBounds() ;
        Rect outline = new Rect( 1,1,r.right-1, r.bottom-1) ;
        canvas.drawRect(outline, strokePaint) ;
    }

}
