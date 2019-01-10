package com.example.claudia.skyplotchart;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;

public class SkyplotGrid {
    private int numberOfCircles = 3;
    private int numberOfLines = 12;

    private Paint paint, textPaint;
    private Rect rect = new Rect();
    private Rect bounds = new Rect();
    private int margin = 0;
    private float radius, centerX, centerY;

    public SkyplotGrid(){
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.LTGRAY);
        paint.setStyle(Paint.Style.STROKE);

        textPaint = new Paint();
        textPaint.setAntiAlias(true);
        textPaint.setColor(Color.LTGRAY);
        textPaint.setTextSize(60);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setTypeface(Typeface.create("sans-serif-light", Typeface.NORMAL));
    }

    public void setMargin(int margin){
        this.margin = margin;
    }

    public void drawGraph(Canvas canvas) {
        bounds.set(margin,margin,canvas.getWidth()-margin,canvas.getHeight()-margin);

        centerX = bounds.centerX();
        centerY = bounds.centerY();

        if(centerX<centerY) radius = centerX - margin;
        else radius = centerY - margin;
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

            currentAngle += angleOfChange;
        }
    }

    private void drawLabel(Canvas canvas, float radians, float startX, float startY){
        Double currentAngle = Math.toDegrees(radians);
        currentAngle +=90;
        if(currentAngle > 360) currentAngle-=360;

        String text = String.format("%.0fº", currentAngle);

//        textPaint.getTextBounds(text,0,text.length(),rect);
        canvas.drawText(text,startX,startY, textPaint);
    }
}
