package com.robot.tongbanjie.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.robot.tongbanjie.R;


/**
 * 倒计时控件
 */
public class CounterDownTextView extends TextView implements OnClickListener{
	public static final int DEFAULT_TOTAL_TIME = 5 * 1000;
	public static final int DEFAULT_INTERVAL_TIME = 1000;
	public static final String DEFAULT_FINISH_TEXT = "获取验证码";
	public static final String DEFAULT_TICK_TEXT_PREFIX = "";
	public static final String DEFAULT_TICK_TEXT_POSTFIX = "秒";
	public static final int DEFAULT_FINISH_COLOR = Color.parseColor("#ff6600");
	public static final int DEFAULT_TICK_TEXT_COLOR = Color.parseColor("#999999");

	private boolean mIsTicking = false;
	private CustomCountDown mCustomCountDown;
	private OnCountDownListener mOnCountDownListener;

	/**
	 * 倒计时总时间
	 */
	private int mTotalTime = DEFAULT_TOTAL_TIME;
	/**
	 * 倒计时间隔时间
	 */
	private int mIntervalTime = DEFAULT_INTERVAL_TIME;
	/**
	 * 倒计时结束的文字
	 */
	private String mFinishText = DEFAULT_FINISH_TEXT;
	/**
	 * 倒计时前缀：如“还剩20秒”的“还剩”
	 */
	private String mTickTextPrefix = DEFAULT_TICK_TEXT_PREFIX;
	/**
	 * 倒计时前缀：如“还剩20秒”的“秒”
	 */
	private String mTickTextPostfix = DEFAULT_TICK_TEXT_POSTFIX;
	/**
	 * 倒计时结束的文字颜色
	 */
	private int mFinishTextColor = DEFAULT_FINISH_COLOR;
	/**
	 * 倒计时的文字颜色
	 */
	private int mTickTextColor = DEFAULT_TICK_TEXT_COLOR;


	public CounterDownTextView(Context context) {
		super(context);
		init(context, null);
	}

	public CounterDownTextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context, attrs);
	}

	public CounterDownTextView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init(context, attrs);
	}

	private void init(Context context, AttributeSet attrs) {
		TypedArray array = context.obtainStyledAttributes(attrs,
				R.styleable.CountDownTextView);
		mFinishTextColor = array.getColor(R.styleable.CountDownTextView_finishTextColor, DEFAULT_FINISH_COLOR);
		mTickTextColor = array.getColor(R.styleable.CountDownTextView_tickTextColor, DEFAULT_TICK_TEXT_COLOR);
		mTotalTime = array.getColor(R.styleable.CountDownTextView_totalTime, DEFAULT_TOTAL_TIME);
		mIntervalTime = array.getColor(R.styleable.CountDownTextView_intervalTime, DEFAULT_INTERVAL_TIME);
		array.recycle();

		setDefaultStatus();
		setOnClickListener(this);
		mCustomCountDown = new CustomCountDown(this, mTotalTime, mIntervalTime);
	}

	@Override
	public void onClick(View v) {
		start();
	}

	public OnCountDownListener getOnCountDownListener() {
		return mOnCountDownListener;
	}

	public void setOnCountDownListener(OnCountDownListener onCountDownListener) {
		this.mOnCountDownListener = onCountDownListener;
	}

	/**
	 * 开始倒计时
	 */
	private void start() {
		if (!mIsTicking) {
			mIsTicking = true;
			mCustomCountDown.start();
			if (mOnCountDownListener != null) {
				mOnCountDownListener.onStart(this);
			}
		}
	}

	/**
	 * 停止倒计时
	 */
	public void stop() {
		mCustomCountDown.cancel();
		mIsTicking = false;
	}

	final class CustomCountDown extends CountDownTimer {
		private View mTargetView;

		public CustomCountDown(View view, long millisInFuture, long countDownInterval) {
			super(millisInFuture, countDownInterval);
			mTargetView = view;
		}

		@Override
		public void onTick(long millisUntilFinished) {
			String tickText = makeTickText(millisUntilFinished);
			setText(tickText);
			setTextColor(mTickTextColor);

			if (mOnCountDownListener != null) {
				mOnCountDownListener.onTick(mTargetView, millisUntilFinished);
			}
		}

		@Override
		public void onFinish() {
			setDefaultStatus();

			if (mOnCountDownListener != null) {
				mOnCountDownListener.onFinish(mTargetView);
			}
		}
	}

	/**
	 * 设置默认状态
	 */
	private void setDefaultStatus() {
		setText(mFinishText);
		setTextColor(mFinishTextColor);
		mIsTicking = false;
	}

	private String makeTickText(long millisUntilFinished) {
		int leftTime = (int) ((millisUntilFinished / 1000) - 1);
		return mTickTextPrefix + leftTime + mTickTextPostfix;
	}

	public String getFinishText() {
		return mFinishText;
	}

	public void setFinishText(String finishText) {
		this.mFinishText = finishText;
	}

	public String getTickTextPrefix() {
		return mTickTextPrefix;
	}

	public void setTickTextPrefix(String tickTextPrefix) {
		this.mTickTextPrefix = tickTextPrefix;
	}

	public String getTickTextPostfix() {
		return mTickTextPostfix;
	}

	public void setTickTextPostfix(String tickTextPostfix) {
		this.mTickTextPostfix = tickTextPostfix;
	}

	public int getFinishTextColor() {
		return mFinishTextColor;
	}

	public void setFinishTextColor(int finishTextColor) {
		this.mFinishTextColor = finishTextColor;
	}

	public int getTickTextColor() {
		return mTickTextColor;
	}

	public void setTickTextColor(int tickTextColor) {
		this.mTickTextColor = tickTextColor;
	}

	public int getTotalTime() {
		return mTotalTime;
	}

	public void setTotalTime(int totalTime) {
		this.mTotalTime = totalTime;
	}

	public int getIntervalTime() {
		return mIntervalTime;
	}

	public void setIntervalTime(int intervalTime) {
		this.mIntervalTime = intervalTime;
	}

	public interface OnCountDownListener {
		/**
		 * 倒计时开始回调
		 */
		public void onStart(View view);
		/**
		 * 倒计时计时回调
		 * @param millisUntilFinished
		 */
		public void onTick(View view, long millisUntilFinished);
		/**
		 * 倒计时结束时回调
		 */
		public void onFinish(View view);
	}

	@Override
	protected void onDetachedFromWindow() {
		super.onDetachedFromWindow();
		if (mCustomCountDown != null) {
			mCustomCountDown.cancel();
		}
	}
}
