package com.example.claudia.skyplotchart;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class SkyplotView extends View {

    private SkyplotGrid skyplotGrid = new SkyplotGrid();
    private List<DataPoint> dataPoints = new ArrayList<>();

    private final int MARGIN = 80;

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
        skyplotGrid.setMargin(MARGIN);

        DataPoint dataPoint;
        dataPoint = new DataPoint("90", 90,0);
        dataPoints.add(dataPoint);
        dataPoint = new DataPoint("60", 60,0);
        dataPoints.add(dataPoint);
        dataPoint = new DataPoint("30", 30,0);
        dataPoints.add(dataPoint);
        dataPoint = new DataPoint("00", 0,0);
        dataPoints.add(dataPoint);
        dataPoint = new DataPoint("180", 180,0);
        dataPoints.add(dataPoint);
        dataPoint = new DataPoint("270", 270,0);
        dataPoints.add(dataPoint);

        dataPoint = new DataPoint("90", 0,90);
        dataPoint.setBackgroundColor(Color.GREEN);
        dataPoints.add(dataPoint);
        dataPoint = new DataPoint("60", 0,60);
        dataPoint.setBackgroundColor(Color.GREEN);
        dataPoints.add(dataPoint);
        dataPoint = new DataPoint("30", 0,30);
        dataPoint.setBackgroundColor(Color.GREEN);
        dataPoints.add(dataPoint);
        dataPoint = new DataPoint("00", 0,0);
        dataPoint.setBackgroundColor(Color.GREEN);
        dataPoints.add(dataPoint);
        dataPoint = new DataPoint("", 30,30);
        dataPoint.setBackgroundColor(Color.RED);
        dataPoints.add(dataPoint);
    }

    public void addDataPoint(DataPoint dataPoint){
        dataPoints.add(dataPoint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


//        //TODO: Remove this paint and rectangle, this is just to position the view.
//        Paint paint = new Paint();
//        paint.setAntiAlias(true);
//        paint.setColor(Color.RED);
//        paint.setStyle(Paint.Style.FILL);
//
//        canvas.drawRect(0,0,getWidth(),getHeight(), paint);
//        paint.setColor(Color.GREEN);
//        Float margins = 40f;
//        canvas.drawRect(margins,margins,getWidth()-margins,getHeight()-margins, paint);

        skyplotGrid.drawGraph(canvas);
//        dataPoint.drawDataPoint(canvas);

        for (DataPoint data:dataPoints) {
            data.drawDataPoint(canvas, MARGIN);
        }

    }
}
