package com.robot.tongbanjie.util;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.text.InputFilter;
import android.text.InputFilter.LengthFilter;
import android.util.TypedValue;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public final class TextViewUtils {
    /**
     * 获取TextView的高度
     * @param context
     * @param text
     * @param textSize
     * @param deviceWidth
     * @param typeface
     * @param padding
     * @return
     */
    public static int getHeight(Context context, CharSequence text, int textSize, int deviceWidth, Typeface typeface, int padding) {
        TextView textView = new TextView(context);
        textView.setPadding(padding,0,padding,padding);
        textView.setTypeface(typeface);
        textView.setText(text, TextView.BufferType.SPANNABLE);
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, textSize);
        int widthMeasureSpec = View.MeasureSpec.makeMeasureSpec(deviceWidth, View.MeasureSpec.AT_MOST);
        int heightMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        textView.measure(widthMeasureSpec, heightMeasureSpec);
        return textView.getMeasuredHeight();
    }

    /**
     * 设置左侧图片
     * @param textView
     * @param drawable
     * @param padding
     */
    public static void setLeftImage(TextView textView, Drawable drawable, float padding) {
        if (textView != null) {
            if (drawable != null) {
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            }
            textView.setCompoundDrawablePadding(DensityUtils.dip2px(textView.getContext(), padding));
            textView.setCompoundDrawables(drawable, null, null, null);
        }
    }

    /**
     * 设置左侧图片
     * @param textView
     * @param resId
     * @param padding
     */
    public static void setLeftImage(TextView textView, int resId, float padding) {
        if (textView != null) {
            Drawable drawable = textView.getContext().getResources().getDrawable(resId);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            textView.setCompoundDrawablePadding(DensityUtils.dip2px(textView.getContext(), padding));
            textView.setCompoundDrawables(drawable, null, null, null);
        }
    }

    /**
     * 设置顶部图片
     * @param textView
     * @param drawable
     * @param padding
     */
    public static void setTopImage(TextView textView, Drawable drawable, float padding) {
        if (textView != null) {
            if (drawable != null) {
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            }
            textView.setCompoundDrawablePadding(DensityUtils.dip2px(textView.getContext(), padding));
            textView.setCompoundDrawables(null, drawable, null, null);
        }
    }

    /**
     * 设置顶部图片
     * @param textView
     * @param resId
     * @param padding
     */
    public static void setTopImage(TextView textView, int resId, float padding) {
        if (textView != null) {
            Drawable drawable = textView.getContext().getResources().getDrawable(resId);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            textView.setCompoundDrawablePadding(DensityUtils.dip2px(textView.getContext(), padding));
            textView.setCompoundDrawables(null, drawable, null, null);
        }
    }

    /**
     * 设置右侧图片
     * @param textView
     * @param drawable
     * @param padding
     */
    public static void setRightImage(TextView textView, Drawable drawable, float padding) {
        if (textView != null) {
            if (drawable != null) {
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            }
            textView.setCompoundDrawablePadding(DensityUtils.dip2px(textView.getContext(), padding));
            textView.setCompoundDrawables(null, null, drawable, null);
        }
    }

    /**
     * 设置右侧图片
     * @param textView
     * @param resId
     * @param padding
     */
    public static void setRightImage(TextView textView, int resId, float padding) {
        if (textView != null) {
            Drawable drawable = textView.getContext().getResources().getDrawable(resId);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            textView.setCompoundDrawablePadding(DensityUtils.dip2px(textView.getContext(), padding));
            textView.setCompoundDrawables(null, null, drawable, null);
        }
    }

    /**
     * 设置底部图片
     * @param textView
     * @param drawable
     * @param padding
     */
    public static void setBottomImage(TextView textView, Drawable drawable, float padding) {
        if (textView != null) {
            if (drawable != null) {
                drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            }
            textView.setCompoundDrawablePadding(DensityUtils.dip2px(textView.getContext(), padding));
            textView.setCompoundDrawables(null, null, null, drawable);
        }
    }

    /**
     * 设置底部图片
     * @param textView
     * @param resId
     * @param padding
     */
    public static void setBottomImage(TextView textView, int resId, float padding) {
        if (textView != null) {
            Drawable drawable = textView.getContext().getResources().getDrawable(resId);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            textView.setCompoundDrawablePadding(DensityUtils.dip2px(textView.getContext(), padding));
            textView.setCompoundDrawables(null, null, null, drawable);
        }
    }

    public static void drawMidLine(TextView textView) {
        textView.setPaintFlags(textView.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
    }

    public static void undrawMidLine(TextView textView) {
        textView.setPaintFlags(textView.getPaintFlags() | ~Paint.STRIKE_THRU_TEXT_FLAG);
    }

    public static void drawUnderLine(TextView textView) {
        textView.setPaintFlags(textView.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);
    }

    public static void undrawUnderLine(TextView textView) {
        textView.setPaintFlags(textView.getPaintFlags() | ~Paint.UNDERLINE_TEXT_FLAG);
    }

    public static void setBold(TextView textView, boolean work) {
        int flags = textView.getPaintFlags();
        if (work) {
            textView.setPaintFlags(flags | Paint.FAKE_BOLD_TEXT_FLAG);
        } else {
            textView.setPaintFlags(flags | ~Paint.FAKE_BOLD_TEXT_FLAG);
        }
    }

    public static void setMaxLength(TextView textView, int length) {
        InputFilter[] inputFilters = textView.getFilters();
        ArrayList<InputFilter> inputFilterArray = new ArrayList<InputFilter>();

        if (inputFilters != null) {
            for (int i = 0; i < inputFilters.length; i++) {
                InputFilter inputFilter = inputFilters[i];

                if (!(inputFilter instanceof LengthFilter))
                    inputFilterArray.add(inputFilter);
            }

        }
        inputFilterArray.add(new LengthFilter(length));
        textView.setFilters(inputFilterArray.toArray(new InputFilter[0]));
    }
}