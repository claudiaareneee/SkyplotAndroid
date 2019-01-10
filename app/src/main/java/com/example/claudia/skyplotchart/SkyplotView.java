package com.example.claudia.skyplotchart;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;

public class SkyplotView extends View {

    private static final String TAG = "SkyplotView";

    SkyplotGrid skyplotGrid = new SkyplotGrid();

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
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        skyplotGrid.drawGraph(canvas);
    }
}
