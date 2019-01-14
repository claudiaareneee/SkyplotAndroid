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
    }

    public void addDataPoint(int constellation, DataPoint dataPoint){
        String uniqueId = dataPoint.getId();

        uniqueId = constellation + uniqueId;

        dataPoints.put(uniqueId, dataPoint);
        invalidate();
    }

    public DataPoint removeDataPoint(int constellation, String id){
        String uniqueId = constellation + id;
        DataPoint dataPoint = dataPoints.remove(uniqueId);
        invalidate();
        return dataPoint;
    }

    public void removeAll(){
        dataPoints.clear();
        invalidate();
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
