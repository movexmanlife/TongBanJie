package com.robot.tongbanjie.dialog;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.robot.tongbanjie.R;

public class TipsDialog {
    private Context mContext;
    private Dialog mDialog;
    private LinearLayout mLayout;
    private TextView mTitle;
    private TextView mContentMsg;
    private View mDividerLeftLine;
    private View mDividerRightLine;
    private Button mPositiveBtn;
    private Button mMiddleBtn;
    private Button mNegativeBtn;

    private boolean mIsShowTitle = false;
    private boolean mIsShowContentMsg = false;
    private boolean mIsShowPositiveBtn = false;
    private boolean mIsShowMiddleBtn = false;
    private boolean mIsShowNegativeBtn = false;

    public TipsDialog(Context context) {
        this.mContext = context;
    }

    public TipsDialog builder() {
        View view  = LayoutInflater.from(mContext).inflate(R.layout.dialog_tips, null);

        mLayout = (LinearLayout) view.findViewById(R.id.dialog_tips_layout);
        mTitle = (TextView) view.findViewById(R.id.dialog_tips_title);
        mContentMsg = (TextView) view.findViewById(R.id.dialog_tips_content_msg);
        mDividerLeftLine = view.findViewById(R.id.dialog_tips_divider_left_line);
        mDividerRightLine = view.findViewById(R.id.dialog_tips_divider_right_line);
        mPositiveBtn = (Button) view.findViewById(R.id.dialog_tips_positive_btn);
        mMiddleBtn = (Button) view.findViewById(R.id.dialog_tips_middle_btn);
        mNegativeBtn = (Button) view.findViewById(R.id.dialog_tips_negative_btn);

        mTitle.setVisibility(View.GONE);
        mContentMsg.setVisibility(View.GONE);
        mDividerLeftLine.setVisibility(View.GONE);
        mDividerRightLine.setVisibility(View.GONE);
        mPositiveBtn.setVisibility(View.GONE);
        mMiddleBtn.setVisibility(View.GONE);
        mNegativeBtn.setVisibility(View.GONE);

        int screenWidth = ((WindowManager)this.mContext.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay().getWidth();
        mDialog = new Dialog(mContext, R.style.TipsDialog);
        mDialog.setContentView(view);
        mDialog.getWindow().setLayout((int)(screenWidth * 0.8f), ViewGroup.LayoutParams.WRAP_CONTENT);
        mDialog.getWindow().setGravity(Gravity.CENTER);

        setCancelable(true);
        setCanceledOnTouchOutside(false);

        return this;
    }

    /**
     * 设置标题
     * @param title
     * @return
     */
    public TipsDialog setTitle(String title) {
        mIsShowTitle = true;
        if (TextUtils.isEmpty(title)) {
            mTitle.setText("提示");
        } else {
            mTitle.setText(title);
        }
        return this;
    }

    public TipsDialog setTitle(int titleResId) {
        mIsShowTitle = true;
        mTitle.setText(titleResId);
        return this;
    }

    /**
     * 设置内容
     * @param msg
     * @return
     */
    public TipsDialog setContentMsg(String msg) {
        mIsShowContentMsg = true;
        if (TextUtils.isEmpty(msg)) {
            mContentMsg.setText("内容");
        } else {
            mContentMsg.setText(msg);
        }
        return this;
    }

    public TipsDialog setContentMsg(int msgResId) {
        mIsShowContentMsg = true;
        mContentMsg.setText(msgResId);

        return this;
    }

    public TipsDialog setCancelable(boolean cancel) {
        mDialog.setCancelable(cancel);
        return this;
    }

    public TipsDialog setCanceledOnTouchOutside(boolean cancel) {
        mDialog.setCanceledOnTouchOutside(cancel);
        return this;
    }

    /**
     * 设置确定按钮
     * @param text
     * @param onPositiveListener
     * @return
     */
    public TipsDialog setPositiveBtn(String text, final OnPositiveListener onPositiveListener) {
        mIsShowPositiveBtn = true;
        if (TextUtils.isEmpty(text)) {
            mPositiveBtn.setText("确定");
        } else {
            mPositiveBtn.setText(text);
        }
        mPositiveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onPositiveListener != null) {
                    onPositiveListener.onPositive(v);
                }
                mDialog.dismiss();
            }
        });
        return this;
    }

    /**
     * 设置中间按钮
     * @param text
     * @param onMiddleListener
     * @return
     */
    public TipsDialog setMiddleBtn(String text, final OnMiddleListener onMiddleListener) {
        mIsShowMiddleBtn = true;
        if (TextUtils.isEmpty(text)) {
            mMiddleBtn.setText("选择");
        } else {
            mMiddleBtn.setText(text);
        }
        mMiddleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onMiddleListener != null) {
                    onMiddleListener.onMiddle(v);
                }
                mDialog.dismiss();
            }
        });
        return this;
    }

    /**
     * 设置取消按钮
     * @param text
     * @param onNegativeListener
     * @return
     */
    public TipsDialog setNegativeBtn(String text, final OnNegativeListener onNegativeListener) {
        mIsShowNegativeBtn = true;
        if (TextUtils.isEmpty(text)) {
            mNegativeBtn.setText("取消");
        } else {
            mNegativeBtn.setText(text);
        }
        mNegativeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onNegativeListener != null) {
                    onNegativeListener.onNegative(v);
                }
                mDialog.dismiss();
            }
        });
        return this;
    }

    private void setLayout() {
        // 是否设置了某个子组件
        boolean isSetAnyComponent = false;

        /**
         * 显示标题
         */
        if (mIsShowTitle) {
            isSetAnyComponent = true;
            mTitle.setVisibility(View.VISIBLE);
        }

        /**
         * 显示内容
         */
        if (mIsShowContentMsg) {
            isSetAnyComponent = true;
            mContentMsg.setVisibility(View.VISIBLE);
        }

        /**
         * 显示三个按钮
         */
        if (mIsShowPositiveBtn && mIsShowMiddleBtn && mIsShowNegativeBtn) {
            isSetAnyComponent = true;
            mPositiveBtn.setVisibility(View.VISIBLE);
            mPositiveBtn.setBackgroundResource(R.drawable.dialog_tips_positive_btn_sel);

            mMiddleBtn.setVisibility(View.VISIBLE);
            mMiddleBtn.setBackgroundResource(R.drawable.dialog_tips_middle_btn_sel);

            mNegativeBtn.setVisibility(View.VISIBLE);
            mNegativeBtn.setBackgroundResource(R.drawable.dialog_tips_negative_btn_sel);

            mDividerLeftLine.setVisibility(View.VISIBLE);
            mDividerRightLine.setVisibility(View.VISIBLE);
        }

        /**
         * 显示两个按钮
         */
        if (mIsShowPositiveBtn && !mIsShowMiddleBtn && mIsShowNegativeBtn) {
            isSetAnyComponent = true;
            mPositiveBtn.setVisibility(View.VISIBLE);
            mPositiveBtn.setBackgroundResource(R.drawable.dialog_tips_positive_btn_sel);
            mNegativeBtn.setVisibility(View.VISIBLE);
            mNegativeBtn.setBackgroundResource(R.drawable.dialog_tips_negative_btn_sel);
            mDividerLeftLine.setVisibility(View.VISIBLE);
        }

        /**
         * 显示两个按钮
         */
        if (mIsShowPositiveBtn && mIsShowMiddleBtn && !mIsShowNegativeBtn) {
            isSetAnyComponent = true;
            mPositiveBtn.setVisibility(View.VISIBLE);
            mPositiveBtn.setBackgroundResource(R.drawable.dialog_tips_positive_btn_sel);
            mMiddleBtn.setVisibility(View.VISIBLE);
            mMiddleBtn.setBackgroundResource(R.drawable.dialog_tips_negative_btn_sel);
            mDividerRightLine.setVisibility(View.VISIBLE);
        }

        /**
         * 显示两个按钮
         */
        if (!mIsShowPositiveBtn && mIsShowMiddleBtn && mIsShowNegativeBtn) {
            isSetAnyComponent = true;
            mMiddleBtn.setVisibility(View.VISIBLE);
            mMiddleBtn.setBackgroundResource(R.drawable.dialog_tips_positive_btn_sel);
            mNegativeBtn.setVisibility(View.VISIBLE);
            mNegativeBtn.setBackgroundResource(R.drawable.dialog_tips_negative_btn_sel);
            mDividerLeftLine.setVisibility(View.VISIBLE);
        }


        /**
         * 显示一个按钮
         */
        if (mIsShowPositiveBtn && !mIsShowMiddleBtn && !mIsShowNegativeBtn) {
            isSetAnyComponent = true;
            mPositiveBtn.setVisibility(View.VISIBLE);
            mPositiveBtn.setBackgroundResource(R.drawable.dialog_tips_only_one_btn_sel);
        }

        /**
         * 显示一个按钮
         */
        if (!mIsShowPositiveBtn && mIsShowMiddleBtn && !mIsShowNegativeBtn) {
            isSetAnyComponent = true;
            mMiddleBtn.setVisibility(View.VISIBLE);
            mMiddleBtn.setBackgroundResource(R.drawable.dialog_tips_only_one_btn_sel);
        }

        /**
         * 显示一个按钮
         */
        if (!mIsShowPositiveBtn && !mIsShowMiddleBtn && mIsShowNegativeBtn) {
            isSetAnyComponent = true;
            mNegativeBtn.setVisibility(View.VISIBLE);
            mNegativeBtn.setBackgroundResource(R.drawable.dialog_tips_only_one_btn_sel);
        }

        /**
         * 未设置任何组件的时候，默认一个标题
         */
        if (!isSetAnyComponent) {
            mTitle.setText("未设置任何组件!");
            mTitle.setVisibility(View.VISIBLE);
        }
    }

    public void show() {
        setLayout();
        mDialog.show();
    }

    /** start----------------------------------------- */
    public TipsDialog setTitleText(String text) {
        mIsShowTitle = true;
        if (TextUtils.isEmpty(text)) {
            mTitle.setText("提示");
        } else {
            mTitle.setText(text);
        }
        return this;
    }

    public TipsDialog setTitleText(int textId) {
        mIsShowTitle = true;
        mTitle.setText(textId);
        return this;
    }

    public TipsDialog setTitleTextColor(int colorId) {
        mIsShowTitle = true;
        mTitle.setTextColor(colorId);
        return this;
    }

    public TipsDialog setTitleTextSize(int textSize) {
        mIsShowTitle = true;
        mTitle.setTextSize(textSize);
        return this;
    }
    /** end----------------------------------------- */

    /** start----------------------------------------- */
    public TipsDialog setContentMsgText(String text) {
        mIsShowContentMsg = true;
        if (TextUtils.isEmpty(text)) {
            mContentMsg.setText("取消");
        } else {
            mContentMsg.setText(text);
        }
        return this;
    }

    public TipsDialog setContentMsgText(int textId) {
        mIsShowContentMsg = true;
        mContentMsg.setText(textId);
        return this;
    }

    public TipsDialog setContentMsgTextColor(int colorId) {
        mIsShowContentMsg = true;
        mContentMsg.setTextColor(colorId);
        return this;
    }

    public TipsDialog setContentMsgTextSize(int textSize) {
        mIsShowContentMsg = true;
        mContentMsg.setTextSize(textSize);
        return this;
    }
    /** end----------------------------------------- */

    /** start----------------------------------------- */
    public TipsDialog setNegativeBtnText(String text) {
        mIsShowNegativeBtn = true;
        if (TextUtils.isEmpty(text)) {
            mNegativeBtn.setText("取消");
        } else {
            mNegativeBtn.setText(text);
        }
        return this;
    }

    public TipsDialog setNegativeBtnText(int textId) {
        mIsShowNegativeBtn = true;
        mNegativeBtn.setText(textId);
        return this;
    }

    public TipsDialog setNegativeBtnTextColor(int colorId) {
        mIsShowNegativeBtn = true;
        mNegativeBtn.setTextColor(colorId);
        return this;
    }

    public TipsDialog setNegativeBtnTextSize(int textSize) {
        mIsShowNegativeBtn = true;
        mNegativeBtn.setTextSize(textSize);
        return this;
    }

    public TipsDialog setNegativeBtnListener(final OnNegativeListener onNegativeListener) {
        mIsShowPositiveBtn = true;
        mNegativeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onNegativeListener != null) {
                    onNegativeListener.onNegative(v);
                }
                mDialog.dismiss();
            }
        });
        return this;
    }
    /** end----------------------------------------- */

    /** start----------------------------------------- */
    public TipsDialog setMiddleBtnText(String text) {
        mIsShowMiddleBtn = true;
        if (TextUtils.isEmpty(text)) {
            mMiddleBtn.setText("取消");
        } else {
            mMiddleBtn.setText(text);
        }
        return this;
    }

    public TipsDialog setMiddleBtnText(int textId) {
        mIsShowMiddleBtn = true;
        mMiddleBtn.setText(textId);
        return this;
    }

    public TipsDialog setMiddleBtnTextColor(int colorId) {
        mIsShowMiddleBtn = true;
        mMiddleBtn.setTextColor(colorId);
        return this;
    }

    public TipsDialog setMiddleBtnTextSize(int textSize) {
        mIsShowMiddleBtn = true;
        mMiddleBtn.setTextSize(textSize);
        return this;
    }

    public TipsDialog setMiddleBtnListener(final OnMiddleListener onMiddleListener) {
        mIsShowPositiveBtn = true;
        mMiddleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onMiddleListener != null) {
                    onMiddleListener.onMiddle(v);
                }
                mDialog.dismiss();
            }
        });
        return this;
    }
    /** end----------------------------------------- */

    /** start----------------------------------------- */
    public TipsDialog setPositiveBtnText(String text) {
        mIsShowPositiveBtn = true;
        if (TextUtils.isEmpty(text)) {
            mPositiveBtn.setText("取消");
        } else {
            mPositiveBtn.setText(text);
        }
        return this;
    }

    public TipsDialog setPositiveBtnText(int textId) {
        mIsShowPositiveBtn = true;
        mPositiveBtn.setText(textId);
        return this;
    }

    public TipsDialog setPositiveBtnTextColor(int colorId) {
        mIsShowPositiveBtn = true;
        mPositiveBtn.setTextColor(colorId);
        return this;
    }

    public TipsDialog setPositiveBtnTextSize(int textSize) {
        mIsShowPositiveBtn = true;
        mPositiveBtn.setTextSize(textSize);
        return this;
    }

    public TipsDialog setPositiveBtnListener(final OnPositiveListener onPositiveListener) {
        mIsShowPositiveBtn = true;
        mPositiveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onPositiveListener != null) {
                    onPositiveListener.onPositive(v);
                }
                mDialog.dismiss();
            }
        });
        return this;
    }
    /** end----------------------------------------- */
    
    public TextView getTitleView() {
        return this.getTitleView();
    }

    public TextView getContentView() {
        return this.mContentMsg;
    }

    public Button getPositiveBtn() {
        return this.mPositiveBtn;
    }

    public Button getMiddleBtn() {
        return this.mMiddleBtn;
    }

    public Button getNegativeBtn() {
        return this.mNegativeBtn;
    }

    public interface OnPositiveListener {
        public void onPositive(View view);
    }

    public interface OnMiddleListener {
        public void onMiddle(View view);
    }

    public interface OnNegativeListener {
        public void onNegative(View view);
    }
}
