package com.example.claudia.skyplotchart;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;
import java.util.HashMap;
import java.util.Map;

public class SkyplotView extends View {

    private SkyplotGrid skyplotGrid = new SkyplotGrid();
    private HashMap<String, DataPoint> dataPoints = new HashMap<>();

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
//        DataPoint dataPoint;
//        dataPoint = new DataPoint("90", 90,0);
//        dataPoints.put("90",dataPoint);
//        dataPoint = new DataPoint("60", 60,0);
//        dataPoints.put("60",dataPoint);
//        dataPoint = new DataPoint("30", 30,0);
//        dataPoints.put("30",dataPoint);
//        dataPoint = new DataPoint("00", 0,0);
//        dataPoints.put("00",dataPoint);
//        dataPoint = new DataPoint("180", 180,0);
//        dataPoints.put("180",dataPoint);
//        dataPoint = new DataPoint("270", 270,0);
//        dataPoints.put("270",dataPoint);
//
//        dataPoint = new DataPoint("90", 0,90);
//        dataPoint.setBackgroundColor(Color.GREEN);
//        dataPoints.put("090",dataPoint);
//        dataPoint = new DataPoint("60", 0,60);
//        dataPoint.setBackgroundColor(Color.GREEN);
//        dataPoints.put("060",dataPoint);
//        dataPoint = new DataPoint("30", 0,30);
//        dataPoint.setBackgroundColor(Color.GREEN);
//        dataPoints.put("030",dataPoint);
//        dataPoint = new DataPoint("00", 0,0);
//        dataPoint.setBackgroundColor(Color.GREEN);
//        dataPoints.put("000",dataPoint);
//        dataPoint = new DataPoint("7001", 30,30);
//        dataPoint.setBackgroundColor(Color.RED);
//        dataPoints.put("7001",dataPoint);
    }

    public void addDataPoint(DataPoint dataPoint){
        dataPoints.put(dataPoint.getId(), dataPoint);
    }

    public DataPoint removeDataPoint(String id){
        return dataPoints.remove(id);
    }

    public void removeAll(String id){
        dataPoints.clear();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        skyplotGrid.drawGraph(canvas);

        for(Map.Entry<String, DataPoint> entry : dataPoints.entrySet()) {
            entry.getValue().drawDataPoint(canvas, MARGIN);
        }

    }
}
