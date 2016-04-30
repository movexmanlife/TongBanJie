package com.robot.tongbanjie.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.robot.tongbanjie.R;

/**
 * 会员提示引导View
 */
public class ShowMemberTipsView extends BaseTipsView {
    private static final String TAG = ShowMemberTipsView.class.getSimpleName();
    private static final String UNIQUE_KEY = TAG;
    private ImageView mImgClose;
    private Button mBtnSure;

    public ShowMemberTipsView(Context context) {
        super(context);
        init();
    }

    public ShowMemberTipsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ShowMemberTipsView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    @Override
    public String getUniquekey() {
        return UNIQUE_KEY;
    }

    private void init() {
        initLayoutParams();
        addStatusBarView();
        addContentView();
    }

    private void initLayoutParams() {
        LayoutParams lp = new LayoutParams(
                LayoutParams. MATCH_PARENT, LayoutParams.MATCH_PARENT);
        setOrientation(VERTICAL);
        setLayoutParams(lp);
    }

    private void addContentView() {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.view_show_member_tips, this, false);

        mImgClose = (ImageView) view.findViewById(R.id.img_close);
        mBtnSure = (Button) view.findViewById(R.id.btn_sure);
        mImgClose.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnCloseListener != null) {
                    mOnCloseListener.onClose(ShowMemberTipsView.this);
                }
            }
        });
        mBtnSure.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnSureListener != null) {
                    mOnSureListener.onSure(ShowMemberTipsView.this);
                }
            }
        });
        addView(view);
    }
}
