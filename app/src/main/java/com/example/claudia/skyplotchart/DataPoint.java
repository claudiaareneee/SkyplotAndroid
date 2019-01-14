package com.example.claudia.skyplotchart;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;

public class DataPoint {
    private float azimuth, elevation;
    private String id;
    private String shortenedId;

    private int textSizePercentage = 50;
    private final float radius = 30;
    private Paint backgroundPaint, borderPaint, textPaint;
    private int borderWidth;

    private Rect rect;

    public float getAzimuth(){
        return azimuth;
    }

    public float getElevation(){
        return elevation;
    }

    public String getId(){
        return id;
    }

    public void setValues(String id, float azimuth, float elevation){
        this.id = id;
        this.azimuth = 180 - azimuth;
        this.elevation = elevation;

        if(id.length()>3) shortenedId = id.substring(0,2) + "..";
        else shortenedId = id;

        initializePaints();
    }

    public float getX(){
        return elevationToPolar() * (float)Math.sin(Math.toRadians(azimuth));
    }

    public float getY(){
        return elevationToPolar() * (float)Math.cos(Math.toRadians(azimuth));
    }

    public float elevationToPolar(){
        return (90f - elevation);
    }

    public float scaleAndCenter(float value, float originValue, float scaleConstraint, float margin){
        float scaleFactor = (scaleConstraint/2f - margin)/90;
        return originValue + scaleFactor*value;
    }

    public DataPoint(String id, float azimuth, float elevation){
        setValues(id, azimuth, elevation);
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
        float width = canvas.getWidth();
        float height = canvas.getHeight();

        float scaleConstraint = width;
        if(width>height) scaleConstraint = height;

        float centerX = scaleAndCenter(getX(),width/2f,scaleConstraint,margin);
        float centerY = scaleAndCenter(getY(),height/2f,scaleConstraint,margin);

        canvas.drawCircle(centerX,centerY,radius,backgroundPaint);

        borderWidth = Math.round(radius)/4;
        borderPaint.setStrokeWidth(borderWidth);

        canvas.drawCircle(centerX,centerY, radius, borderPaint);

        textPaint.setTextSize(calculateTextSize(canvas, textSizePercentage));
        textPaint.getTextBounds(shortenedId,0, shortenedId.length(),rect);

        canvas.drawText(shortenedId,centerX,centerY + Math.abs(rect.height())/2, textPaint);

    }
}
