package com.example.claudia.skyplotchart;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.AttributeSet;

public class DataPoint {
    private float azimuth, elevation;
    String id;

    public float getAzimuth(){
        return azimuth;
    }
    public float getElevation(){
        return elevation;
    }

    public float elevationToPolar(){
        return (90f - elevation);
    }

    public float getX(){
        return elevationToPolar() * (float)Math.sin(Math.toRadians(azimuth));
    }

    public float getY(){
        return elevationToPolar() * (float)Math.cos(Math.toRadians(azimuth));
    }

    public float scaleAndCenter(float value, float originValue, float scaleConstraint, float margin){
//        if(value > 360){
//            value -= 360;
//        }

        float scaleFactor = (scaleConstraint/2f - margin)/90;
        return originValue + scaleFactor*value;
    }

    public String getId(){
        return id;
    }

    private int textSizePercentage = 50;
    private final float radius = 30;
    private Paint backgroundPaint, borderPaint, textPaint;
    private int borderWidth;

    private final int MARGIN = 3;

    private Rect rect;

    public DataPoint(String id, float azimuth, float elevation){
        this.id = id;
        this.azimuth = 180 - azimuth;
        this.elevation = elevation;

        initializePaints();
    }

    private void initializePaints(){

        final int defaultBackgroundColor = 0xff00ffff;
        final int defaultBorderColor = 0xff000000;
        final int defaultTextColor = 0xff000000;

        backgroundPaint = new Paint();
        backgroundPaint.setAntiAlias(true);
        backgroundPaint.setColor(defaultBackgroundColor);
        backgroundPaint.setStyle(Paint.Style.FILL);

        borderPaint = new Paint();
        borderPaint.setAntiAlias(true);
        borderPaint.setColor(defaultBorderColor);
        borderPaint.setStyle(Paint.Style.STROKE);
        borderPaint.setStrokeWidth(borderWidth);

        textPaint = new Paint();
        textPaint.setAntiAlias(true);
        textPaint.setColor(defaultTextColor);
        textPaint.setTextSize(0);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setTypeface(Typeface.create("sans-serif-light", Typeface.NORMAL));

        rect = new Rect();
    }

    private float calculateTextSize(Canvas canvas, int textSizePercentage) {
        if (textSizePercentage < 0 || textSizePercentage > 100) {
            textSizePercentage = 33;
        }
        return radius * (float) textSizePercentage / 50;
    }

    public void setBackgroundColor(int color){
        backgroundPaint.setColor(color);
    }

    public void drawDataPoint(Canvas canvas, int margin) {

//        float centerX = canvas.getWidth()/2;
//        float centerY = canvas.getHeight()/2;
//        float radius;
//
//        if (centerX>centerY) radius = centerY - MARGIN;
//        else radius = centerX - MARGIN;

//        float scale = (canvas.getWidth()/2 - margin)/90f;
        float width = canvas.getWidth();
        float height = canvas.getHeight();

        float scaleConstraint = width;
        if(width>height) scaleConstraint = height;

        float centerX = scaleAndCenter(getX(),width/2f,scaleConstraint,margin);
        float centerY = scaleAndCenter(getY(),height/2f,scaleConstraint,margin);

        canvas.drawCircle(centerX,centerY,radius,backgroundPaint);

        borderWidth = Math.round(radius)/4;
        borderPaint.setStrokeWidth(borderWidth);

//        canvas.drawCircle(centerX,centerY, radius - (borderWidth/2), borderPaint);
        canvas.drawCircle(centerX,centerY, radius, borderPaint);

        textPaint.setTextSize(calculateTextSize(canvas, textSizePercentage));
        textPaint.getTextBounds(id,0,id.length(),rect);

        canvas.drawText(id,centerX,centerY + Math.abs(rect.height())/2, textPaint);

    }
}
