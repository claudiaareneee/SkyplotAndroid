package com.example.claudia.skyplotchart;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class SkyplotView extends View {

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
        skyplotGrid.setMargin(80);
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
    }
}
