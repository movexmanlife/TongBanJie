package com.robot.tongbanjie.widget;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.robot.tongbanjie.R;
import com.robot.tongbanjie.util.TextViewUtils;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 自定义标题栏
 */
public class TitleBarView extends LinearLayout {
	private Context mContext;
	private RelativeLayout mBackgroud;
	private ImageView mLeftImg;
	private TextView mLeftTxt;
	private TextView mTitle;
	private ImageView mTitleImg;
	private ImageView mRightImg;
	private TextView mRightTxt;
	private View mDivider;
	private Set<View> mViewSet = new HashSet<View>();
	private OnLeftTxtClickListener mLeftTxtClickListener;
	private OnLeftImgClickListener mLeftImgClickListener;
	private OnTitleImgClickListener mTitleImgClickListener;
	private OnRightTxtClickListener mRightTxtClickListener;
	private OnRightImgClickListener mRightImgClickListener;

	/**
	 * 注意：mLeftImg和mLeftTxt之间同时最多只有一个显示；
	 * mRightImg和mRightTxt之间同时最多只有一个显示；
	 *
	 * 实现mLeftTxt和mRightTxt左右两边的图片，使用DrawableLeft和DrawableRight。
	 */
	public enum TitleBarStyle {
		OnlyTitle,  /** 中间标题 */

		LeftTxt_Title,  /** 左边文字，中间标题 */
		Title_RightTxt,  /** 中间标题，右边文字 */
		LeftTxt_Title_RightTxt,  /** 左边文字，中间标题，右边文字 */
		LeftTxt_Title_TitleImg_RightTxt,  /** 左边文字，中间标题和标题的图片，右边文字 */

		LeftImg_Title,  /** 左边图片，中间标题 */
		Title_RightImg,  /** 中间标题，右边图片 */
		LeftImg_Title_RightImg,  /** 左边图片，中间标题，右边图片 */
		LeftImg_Title_TitleImg_RightImg,  /** 左边图片，中间标题和标题的图片，右边图片 */

		LeftTxt_Title_RightImg,  /** 左边文字，中间标题，右边图片 */
		LeftImg_Title_RightTxt,  /** 左边图片，中间标题，右边文字 */
		LeftTxt_Title_TitleImg_RightImg,  /** 左边文字，中间标题和标题的图片，右边图片 */
		LeftImg_Title_TitleImg_RightTxt   /** 左边图片，中间标题和标题的图片，右边文字 */
	}

	public TitleBarView(Context context) {
		super(context);
		mContext = context;
		initView();
		setListener();
	}

	public TitleBarView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
		initView();
		setListener();
	}

	public TitleBarView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		mContext = context;
		initView();
		setListener();
	}

	private void initView() {
		LayoutInflater.from(mContext).inflate(R.layout.title_bar_common, this);
		mBackgroud = (RelativeLayout) findViewById(R.id.titlebar_bg);
		mLeftImg = (ImageView) findViewById(R.id.titlebar_left_img);
		mLeftTxt = (TextView) findViewById(R.id.titlebar_left_txt);
		mTitle = (TextView) findViewById(R.id.titlebar_title);
		mTitleImg = (ImageView) findViewById(R.id.titlebar_title_img);
		mRightImg = (ImageView) findViewById(R.id.titlebar_right_img);
		mRightTxt = (TextView) findViewById(R.id.titlebar_right_txt);
		mDivider = findViewById(R.id.divider);

		mViewSet.add(mLeftImg);
		mViewSet.add(mLeftTxt);
		mViewSet.add(mTitle);
		mViewSet.add(mTitleImg);
		mViewSet.add(mRightImg);
		mViewSet.add(mRightTxt);
	}
	
	private void setListener() {
		mLeftTxt.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (mLeftTxtClickListener != null) {
					mLeftTxtClickListener.onClick();
				}
			}
		});
		mLeftImg.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (mLeftImgClickListener != null) {
					mLeftImgClickListener.onClick();
				}
			}
		});
		mTitleImg.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (mTitleImgClickListener != null) {
					mTitleImgClickListener.onClick();
				}
			}
		});
		mRightTxt.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (mRightTxtClickListener != null) {
					mRightTxtClickListener.onClick();
				}
			}
		});
		mRightImg.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (mRightImgClickListener != null) {
					mRightImgClickListener.onClick();
				}
			}
		});
	}

	/**
	 * 初始化titlebar样式
	 * @param style
	 */
	private void initStyle(TitleBarStyle style) {
		switch (style) {
			/* ------------------------------- */
			case OnlyTitle:
				setViewsVisibleAndOtherGone(mTitle);
				break;

			case LeftTxt_Title:
				setViewsVisibleAndOtherGone(mLeftTxt, mTitle);
				break;
			case Title_RightTxt:
				setViewsVisibleAndOtherGone(mTitle, mRightTxt);
				break;

			case LeftTxt_Title_RightTxt:
				setViewsVisibleAndOtherGone(mLeftTxt, mTitle, mRightTxt);
				break;
			case LeftTxt_Title_TitleImg_RightTxt:
				setViewsVisibleAndOtherGone(mLeftTxt, mTitle, mTitleImg, mRightTxt);
				break;

			/* ------------------------------- */
			case LeftImg_Title:
				setViewsVisibleAndOtherGone(mLeftImg, mTitle);
				break;
			case Title_RightImg:
				setViewsVisibleAndOtherGone(mTitle, mRightImg);
				break;
			case LeftImg_Title_RightImg:
				setViewsVisibleAndOtherGone(mLeftImg, mTitle, mRightImg);
				break;
			case LeftImg_Title_TitleImg_RightImg:
				setViewsVisibleAndOtherGone(mLeftImg, mTitle, mTitleImg, mRightImg);
				break;

			/* ------------------------------- */
			case LeftTxt_Title_RightImg:
				setViewsVisibleAndOtherGone(mLeftTxt, mTitle, mRightImg);
				break;
			case LeftImg_Title_RightTxt:
				setViewsVisibleAndOtherGone(mLeftImg, mTitle, mRightTxt);
				break;
			case LeftTxt_Title_TitleImg_RightImg:
				setViewsVisibleAndOtherGone(mLeftTxt, mTitle, mTitleImg, mRightImg);
				break;
			case LeftImg_Title_TitleImg_RightTxt:
				setViewsVisibleAndOtherGone(mLeftImg, mTitle, mTitleImg, mRightTxt);
				break;
		}
	}

	/**
	 * 隐藏或显示所有的View
	 * @param isVisiable
	 */
	private void setAllViewsVisibleOrGone(boolean isVisiable) {
		int result = View.VISIBLE;
		if (isVisiable) {
			result = View.VISIBLE;
		} else {
			result = View.GONE;
		}

		for (View view : mViewSet) {
			view.setVisibility(result);
		}

	}

	/**
	 * 设置相关Views显示，除此之外Views隐藏
	 * @param targetViews
	 */
	private void setViewsVisibleAndOtherGone(View... targetViews) {
		for (View view : mViewSet) {
			List<View> targetViewList = Arrays.asList(targetViews);

			// 判断View是否在targetViews目标集合中
			if (targetViewList.contains(view)) {
				view.setVisibility(View.VISIBLE);
			} else {
				view.setVisibility(View.GONE);
			}
		}
	}

	/**
	 * 只有标题
	 * @param resId
	 */
	public void initOnlyTitle(int resId) {
		initStyle(TitleBarStyle.OnlyTitle);
		setTitle(resId);
	}

	/**
	 * 只有标题
	 * @param txt
	 */
	public void initOnlyTitle(String txt) {
		initStyle(TitleBarStyle.OnlyTitle);
		setTitle(txt);
	}

	/**
	 *
	 * @param title  标题
	 * @param leftTxt  左边文字
	 */
	public void initTitleWithLeftTxt(String title, String leftTxt) {
		initStyle(TitleBarStyle.LeftTxt_Title);
		setTitle(title);
		setLeftTxt(leftTxt);
	}

	/**
	 * 
	 * @param title  标题
	 * @param leftTxt  左边文字
	 * @param leftTxtDrawableRes  利用TextView的Drawable属性，设置“左边文字”的左侧Drawable
	 * @param padding  “左边文字”的左侧Drawable的padding
	 */
	public void initTitleWithLeftTxtDrawable(String title, String leftTxt, int leftTxtDrawableRes, int padding) {
		initStyle(TitleBarStyle.LeftTxt_Title);
		setTitle(title);
		setLeftTxt(leftTxt);
		TextViewUtils.setLeftImage(mLeftTxt, leftTxtDrawableRes, padding);
	}

	/**
	 *
	 * @param title  标题
	 * @param rightTxt  右边文字
	 */
	public void initTitleWithRightTxt(String title, String rightTxt) {
		initStyle(TitleBarStyle.Title_RightTxt);
		setTitle(title);
		setRightTxt(rightTxt);
	}

	/**
	 * 
	 * @param title  标题
	 * @param rightTxt  右边文字
	 * @param rightTxtDrawableRes  利用TextView的Drawable属性，设置“右边文字”的左侧Drawable
	 * @param padding  “右边文字”的左侧Drawable的padding
	 */
	public void initTitleWithRightTxtDrawable(String title, String rightTxt, int rightTxtDrawableRes, int padding) {
		initStyle(TitleBarStyle.Title_RightTxt);
		setTitle(title);
		setRightTxt(rightTxt);
		TextViewUtils.setLeftImage(mRightTxt, rightTxtDrawableRes, padding);
	}

	/**
	 *
	 * @param title  标题
	 * @param leftTxt  左边文字
	 * @param rightTxt  右边文字
	 */
	public void initTitleWithLeftTxtRightTxt(String title, String leftTxt, String rightTxt) {
		initStyle(TitleBarStyle.LeftTxt_Title_RightTxt);
		setLeftTxt(leftTxt);
		setTitle(title);
		setRightTxt(rightTxt);
	}

	/**
	 *
	 * @param title  标题
	 * @param titleImgId  标题图片
	 * @param leftTxt  左边文字
	 * @param rightTxt  右边文字
	 */
	public void setTitleTitleImgWithLeftTxtRightTxt(String title, int titleImgId, String leftTxt, String rightTxt) {
		initStyle(TitleBarStyle.LeftTxt_Title_TitleImg_RightTxt);
		setLeftTxt(leftTxt);
		setTitle(title);
		setTitleImg(titleImgId);
		setRightTxt(rightTxt);
	}

	/**
	 *
	 * @param title  标题
	 * @param leftImgId  左边图片
	 */
	public void initTitleWithLeftImg(String title, int leftImgId) {
		initStyle(TitleBarStyle.LeftImg_Title);
		setTitle(title);
		setLeftImg(leftImgId);
	}

	/**
	 *
	 * @param title  标题
	 * @param rightImgId  右边图片
	 */
	public void initTitleWithRightImg(String title, int rightImgId) {
		initStyle(TitleBarStyle.Title_RightImg);
		setTitle(title);
		setRightImg(rightImgId);
	}

	/**
	 *
	 * @param title  标题
	 * @param leftImgId  左边图片
	 * @param rightImgId  右边图片
	 */
	public void initTitleWithLeftImgRightImg(String title, int leftImgId, int rightImgId) {
		initStyle(TitleBarStyle.LeftImg_Title_RightImg);
		setLeftImg(leftImgId);
		setTitle(title);
		setRightImg(rightImgId);
	}

	/**
	 *
	 * @param title  标题
	 * @param titleImgId  标题图片
	 * @param leftImgId  左边图片
	 * @param rightImgId  右边图片
	 */
	public void initTitleTitleImgWithLeftImgRightImg(String title, int titleImgId, int leftImgId, int rightImgId) {
		initStyle(TitleBarStyle.LeftImg_Title_TitleImg_RightImg);
		setLeftImg(leftImgId);
		setTitle(title);
		setTitleImg(titleImgId);
		setRightImg(rightImgId);;
	}

	/* ---start----------------------------------------------- */

	/**
	 *
	 * @param title  标题
	 * @param leftTxt  左边文字
	 * @param rightImgId  右边图片
	 */
	public void initTitleWithLeftTxtRightImg(String title, String leftTxt, int rightImgId) {
		initStyle(TitleBarStyle.LeftTxt_Title_RightImg);
		setLeftTxt(leftTxt);
		setTitle(title);
		setRightImg(rightImgId);
	}

	/**
	 *
	 * @param title  标题
	 * @param leftImgId  左边图片
	 * @param rightTxt  右边文字
	 */
	public void initTitleWithLeftImgRightTxt(String title, int leftImgId, String rightTxt) {
		initStyle(TitleBarStyle.LeftImg_Title_RightTxt);
		setLeftImg(leftImgId);
		setTitle(title);
		setRightTxt(rightTxt);
	}

	/**
	 *
	 * @param title  标题
	 * @param titleImgId  标题图片
	 * @param leftTxt  左边文字
	 * @param rightImgId  右边图片
	 */
	public void initTitleTitleImgWithLeftTxtRightImg(String title, int titleImgId, String leftTxt, int rightImgId) {
		initStyle(TitleBarStyle.LeftTxt_Title_TitleImg_RightImg);
		setLeftTxt(leftTxt);
		setTitle(title);
		setTitleImg(titleImgId);
		setRightImg(rightImgId);
	}

	/**
	 *
	 * @param title  标题
	 * @param titleImgId  标题图片
	 * @param leftImgId  左边图片
	 * @param rightTxt  右边文字
	 */
	public void initTitleTitleImgWithLeftImgRightTxt(String title, int titleImgId, int leftImgId, String rightTxt) {
		initStyle(TitleBarStyle.LeftImg_Title_TitleImg_RightTxt);
		setLeftImg(leftImgId);
		setTitle(title);
		setTitleImg(titleImgId);
		setRightTxt(rightTxt);
	}
	/* ---end----------------------------------------------- */

	public TextView getLeftTxtView() {
		return this.mLeftTxt;
	}

	public ImageView getLeftImgView() {
		return this.mLeftImg;
	}

	public TextView getTitleView() {
		return this.mTitle;
	}

	public ImageView getTitleImgView() {
		return this.mTitleImg;
	}

	public TextView getRightTxtView() {
		return this.mRightTxt;
	}

	public ImageView getRightImgView() {
		return this.mRightImg;
	}

	@Override
	public void setBackgroundResource(int resid) {
		setTitleBarBackground(resid);
	}

	/**
	 * 设置TitleBar背景
	 * @param resId
	 */
	public void setTitleBarBackground(int resId) {
		mBackgroud.setBackgroundResource(resId);
	}

	public void setLeftImg(int resId) {
		mLeftImg.setImageResource(resId);
	}

	public void setLeftTxt(int resId) {
		mLeftTxt.setText(resId);
	}

	public void setLeftTxt(String txt) {
		mLeftTxt.setText(txt);
	}

	public void setRightImg(int resId) {
		mRightImg.setImageResource(resId);
	}

	public void setRightTxt(int resId) {
		mRightTxt.setText(resId);
	}

	public void setRightTxt(String txt) {
		mRightTxt.setText(txt);
	}

	public void setTitle(int txtRes) {
		mTitle.setText(txtRes);
	}

	public void setTitle(String txt) {
		mTitle.setText(txt);
	}

	public void setDividerBg(int resId) {
		mDivider.setBackgroundColor(resId);
	}

	/**
	 * 设置title右边的图片
	 * @param resId
	 */
	public void setTitleImg(int resId) {
		mTitleImg.setImageResource(resId);
	}

	public void setOnLeftTxtClickListener(
			OnLeftTxtClickListener listener) {
		mLeftTxtClickListener = listener;
	}

	public void setOnLeftImgClickListener(
			OnLeftImgClickListener listener) {
		mLeftImgClickListener = listener;
	}

	public void setOnTileImgClickListener(
			OnTitleImgClickListener listener) {
		mTitleImgClickListener = listener;
	}

	public void setOnRightImgClickListener(
			OnRightImgClickListener listener) {
		mRightImgClickListener = listener;
	}

	public void setOnRightTxtClickListener(
			OnRightTxtClickListener listener) {
		mRightTxtClickListener = listener;
	}

	public interface OnLeftTxtClickListener {
		void onClick();
	}

	public interface OnLeftImgClickListener {
		void onClick();
	}

	public interface OnTitleImgClickListener {
		void onClick();
	}

	public interface OnRightTxtClickListener {
		void onClick();
	}

	public interface OnRightImgClickListener {
		void onClick();
	}

}
