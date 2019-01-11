package com.example.claudia.skyplotchart;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

public class DataPointView extends View {

    private String id = "";

    private final int defaultBorderWidth = 10;
    private final int defaultBackgroundColor = 0xff00ffff;
    private final int defaultBorderColor = 0xff000000;
    private final int defaultTextColor = 0xff000000;
    private final int defaultTextPercentage = 50;

    private Paint backgroundPaint, borderPaint, textPaint;
    private int borderWidth, textSizePercentage;
    private int backgroundColor, borderColor, textColor;

    private final int MARGIN = 3;

    private Rect rect;

    public DataPointView(Context context) {
        super(context);
        init(context, null);
    }

    public DataPointView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public DataPointView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    public void setBackgroundColor(int color){
        backgroundColor = color;
    }

    private void init(Context context, AttributeSet attrs){
        if (attrs != null) {
            TypedArray typedArray = context.getTheme().obtainStyledAttributes(
                    attrs,
                    R.styleable.DataPointView,
                    0, 0);
            try {
                configureValues(typedArray);
            } finally {
                typedArray.recycle();
            }
        }

        backgroundPaint = new Paint();
        backgroundPaint.setAntiAlias(true);
        backgroundPaint.setColor(backgroundColor);
        backgroundPaint.setStyle(Paint.Style.FILL);

        borderPaint = new Paint();
        borderPaint.setAntiAlias(true);
        borderPaint.setColor(borderColor);
        borderPaint.setStyle(Paint.Style.STROKE);
        borderPaint.setStrokeWidth(borderWidth);

        textPaint = new Paint();
        textPaint.setAntiAlias(true);
        textPaint.setColor(textColor);
        textPaint.setTextSize(calculateTextSize(defaultTextPercentage));
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setTypeface(Typeface.create("sans-serif-light", Typeface.NORMAL));

        rect = new Rect();
    }

    private void configureValues(TypedArray typedArray) {

        borderColor = typedArray.getColor(R.styleable.DataPointView_android_textColor, defaultBorderColor);
        borderWidth = typedArray.getDimensionPixelSize(R.styleable.DataPointView_data_borderWidth, defaultBorderWidth);
        backgroundColor = typedArray.getColor(R.styleable.DataPointView_data_backgroundColor, defaultBackgroundColor);
        textColor = typedArray.getColor(R.styleable.DataPointView_android_textColor, defaultTextColor);
        textSizePercentage = typedArray.getInt(R.styleable.DataPointView_data_textPercentage, defaultTextPercentage);

        String tmp = typedArray.getString(R.styleable.DataPointView_data_text);
        if (tmp != null) id = tmp;

    }

    private float calculateTextSize(int textSizePercentage) {
        if (textSizePercentage < 0 || textSizePercentage > 100) {
            textSizePercentage = 33;
        }
        return getHeight() * (float) textSizePercentage / 100;
    }


    public void setParams(int backgroundColor, String id){
        this.id = id;
        backgroundPaint.setColor(backgroundColor);

        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        setBackground(null);

        float centerX = getMeasuredWidth()/2;
        float centerY = getMeasuredHeight()/2;
        float radius;

        if (centerX>centerY) radius = centerY - MARGIN;
        else radius = centerX - MARGIN;

        canvas.drawCircle(centerX,centerY,radius,backgroundPaint);

        borderWidth = Math.round(centerX)/4;
        borderPaint.setStrokeWidth(borderWidth);

        canvas.drawCircle(centerX,centerY, radius - (borderWidth/2), borderPaint);

        textPaint.setTextSize(calculateTextSize(textSizePercentage));
        textPaint.getTextBounds(id,0,id.length(),rect);

        canvas.drawText(id,centerX,centerY + Math.abs(rect.height())/2, textPaint);

    }

    @Override
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        invalidate();
    }
}
