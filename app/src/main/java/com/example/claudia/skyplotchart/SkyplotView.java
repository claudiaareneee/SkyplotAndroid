package com.example.claudia.skyplotchart;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class SkyplotView extends View {

    private static final String TAG = "SkyplotView";

    private int numberOfCircles = 5;
    private int numberOfLines = 12;

    Paint paint, textPaint;
    Rect rect = new Rect();
    float radius, centerX, centerY;

    public SkyplotView(Context context) {
        super(context);
        init();
    }

    public SkyplotView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SkyplotView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public SkyplotView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init(){
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.LTGRAY);
        paint.setStyle(Paint.Style.STROKE);

        textPaint = new Paint();
        textPaint.setAntiAlias(true);
        textPaint.setColor(Color.LTGRAY);
        textPaint.setTextSize(60 );
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setTypeface(Typeface.create("sans-serif-light", Typeface.NORMAL));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        centerX = canvas.getWidth()/2;
        centerY = canvas.getHeight()/2;

        if(centerX<centerY) radius = centerX;
        else radius = centerY;
        paint.setStrokeWidth(radius*(float)0.01);

        drawCircles(canvas);
        drawLines(canvas);
    }

    private void drawCircles(Canvas canvas){

        float radiusPercentage = radius/numberOfCircles;

        for (int i = 1; i <= numberOfCircles; i++) {
            canvas.drawCircle(centerX,centerY,radiusPercentage*i, paint);
        }
    }

    private void drawLines(Canvas canvas){
        double angleOfChange = Math.toRadians(360)/numberOfLines;
        double currentAngle = 0;

        double stopX, stopY;

        for (int i = 0; i < numberOfLines; i++) {
            stopX = centerX + radius*Math.cos(currentAngle);
            stopY = centerY + radius*Math.sin(currentAngle);

            canvas.drawLine(centerX,centerY,(float)stopX,(float)stopY,paint);

            if (i % 3 == 0) {
                drawLabel(canvas,(float)currentAngle,(float)stopX,(float)stopY);
            }

            Log.d(TAG, "drawLines: stopX: " + stopX + ", stopY: " + stopY );

            currentAngle += angleOfChange;
        }


    }

    private void drawLabel(Canvas canvas, float radians, float startX, float startY){
        Double currentAngle = Math.toDegrees(radians);
        currentAngle +=90;
        if(currentAngle > 360) currentAngle-=360;


        String text = String.format("%.2f", currentAngle);



//        canvas.save();
//        canvas.rotate(radians);

        textPaint.getTextBounds(text,0,text.length(),rect);
        canvas.drawText(text,startX,startY, textPaint);

//        canvas.restore();
    }
}
