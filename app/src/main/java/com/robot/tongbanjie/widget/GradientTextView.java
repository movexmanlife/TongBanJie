package com.robot.tongbanjie.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.widget.TextView;

public class GradientTextView extends TextView {
    //    private int[] mGradientColors = {Color.WHITE, Color.TRANSPARENT};
//    private float[] positions = {0, 1};
    private int[] mGradientColors = {Color.RED, Color.rgb(255, 165, 0),
            Color.YELLOW, Color.GREEN, Color.rgb(0, 255, 255), Color.BLUE,
            Color.rgb(160, 32, 240)};
    private float[] positions = null;

    public GradientTextView(Context context) {
        super(context);
    }

    public GradientTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GradientTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        getPaint().setShader(new LinearGradient(0, 0, getWidth(), getMeasuredHeight(),
                mGradientColors, positions, Shader.TileMode.REPEAT));
        super.onDraw(canvas);
    }

}