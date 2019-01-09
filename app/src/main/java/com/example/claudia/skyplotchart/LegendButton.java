package com.example.claudia.skyplotchart;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

import android.graphics.Typeface;
import android.support.v7.widget.AppCompatButton;
import android.util.AttributeSet;

public class LegendButton extends AppCompatButton {

    private String id = "";

    private final int defaultBorderWidth = getWidth()/3;
    private final int defaultBackgroundColor = 0xff00ffff;
    private final int defaultBorderColor = 0xffffffff;;
    private final int defaultPressColor = 0x33ffffff;
    private final int defaultTextColor = 0xffffffff;
    private final int defaultTextPercentage = 65;

    private Paint backgroundPaint, borderPaint, pressPaint, textPaint;
    private int borderWidth, textSizePercentage;
    private int backgroundColor, borderColor, pressColor, textColor;

    private Rect rect;

    public LegendButton(Context context) {
        super(context);
        init(context, null);
    }

    public LegendButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public LegendButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs){
//        if (attrs != null) {
//            TypedArray typedArray = context.getTheme().obtainStyledAttributes(
//                    attrs,
//                    R.styleable.LegendButton,
//                    0, 0);
//            try {
//                configureValues(typedArray);
//            } finally {
//                typedArray.recycle();
//            }
//        }

        backgroundPaint = new Paint();
        backgroundPaint.setAntiAlias(true);
        backgroundPaint.setColor(backgroundColor);
        backgroundPaint.setStyle(Paint.Style.FILL);

        borderPaint = new Paint();
        borderPaint.setAntiAlias(true);
        borderPaint.setColor(borderColor);
        borderPaint.setStyle(Paint.Style.STROKE);
        borderPaint.setStrokeWidth(borderWidth);

        pressPaint = new Paint();
        pressPaint.setAntiAlias(true);
        pressPaint.setColor(pressColor);
        pressPaint.setStyle(Paint.Style.FILL);

        textPaint = new Paint();
        textPaint.setAntiAlias(true);
        textPaint.setColor(textColor);
        textPaint.setTextSize(calculateTextSize(defaultTextPercentage));
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setTypeface(Typeface.create("sans-serif-light", Typeface.NORMAL));

        rect = new Rect();
    }

    private void configureValues(TypedArray typedArray) {

//        borderColor = typedArray.getColor(R.styleable.LegendButton_legend_borderColor, defaultBorderColor);
//        borderWidth = typedArray.getDimensionPixelSize(R.styleable.LegendButton_legend_borderWidth, defaultBorderWidth);
//        backgroundColor = typedArray.getColor(R.styleable.LegendButton_legend_backgroundColor, defaultBackgroundColor);
//        pressColor = typedArray.getColor(R.styleable.LegendButton_legend_pressColor,defaultPressColor);
//        textColor = typedArray.getColor(R.styleable.LegendButton_android_textColor, defaultTextColor);
//        textSizePercentage = typedArray.getInt(R.styleable.LegendButton_legend_textPercentage, defaultTextPercentage);
//
//        String tmp = typedArray.getString(R.styleable.LegendButton_legend_text);
//        if (tmp != null) id = tmp;

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

        setBackground(null);
        canvas.drawOval(0,0,getMeasuredWidth(),getMeasuredHeight(),backgroundPaint);

        super.onDraw(canvas);
        canvas.drawOval(0,0,getMeasuredWidth(),getMeasuredHeight(),borderPaint);

        if(isPressed()){
            canvas.drawOval(0,0,getMeasuredWidth(),getMeasuredHeight(),pressPaint);
        }

        textPaint.setTextSize(calculateTextSize(textSizePercentage));
        textPaint.getTextBounds(id,0,id.length(),rect);

        canvas.drawText(id,canvas.getWidth()/2,canvas.getHeight()/2 + Math.abs(rect.height())/2, textPaint);

    }

    @Override
    protected void drawableStateChanged() {
        super.drawableStateChanged();
        invalidate();
    }
}
