package com.robot.tongbanjie.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.robot.tongbanjie.R;

public class CommonItem extends LinearLayout {
	private ImageView mSummaryImg;
	private TextView mSummaryText;
	private TextView mDetailText;
	private ImageView mDetailImg;
	private Type mCurrentType = Type.All;

	public enum Type {
		All,
		SummaryImgSummaryTxt_DetailTxt,
		SummaryImgSummaryTxt_DetailImg,
		SummaryTxt_DetailTxtDetailImg,
		SummaryTxt_DetailTxt,
		SummaryTxt_DetailImg
	}

	public CommonItem(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	private void init() {
		LayoutInflater inflater = (LayoutInflater) getContext()
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View view = inflater.inflate(R.layout.view_common_item, this,
				true);
		mSummaryImg = (ImageView) view.findViewById(R.id.summary_img);
		mSummaryText = (TextView) view.findViewById(R.id.summary_text);
		mDetailText = (TextView) view.findViewById(R.id.detail_text);
		mDetailImg = (ImageView) view.findViewById(R.id.detail_img);
	}

	public void setType(Type type) {
		mCurrentType = type;
		switch (type) {
			case All:
				setStyleAll();
				break;
			case SummaryImgSummaryTxt_DetailTxt:
				mSummaryImg.setVisibility(View.VISIBLE);
				mSummaryText.setVisibility(View.VISIBLE);
				mDetailText.setVisibility(View.VISIBLE);
				mDetailImg.setVisibility(View.GONE);
				setDetailTxtLp();
				break;
			case SummaryImgSummaryTxt_DetailImg:
				mSummaryImg.setVisibility(View.VISIBLE);
				mSummaryText.setVisibility(View.VISIBLE);
				mDetailText.setVisibility(View.GONE);
				mDetailImg.setVisibility(View.VISIBLE);
				break;
			case SummaryTxt_DetailTxtDetailImg:
				mSummaryImg.setVisibility(View.GONE);
				mSummaryText.setVisibility(View.VISIBLE);
				mDetailText.setVisibility(View.VISIBLE);
				mDetailImg.setVisibility(View.VISIBLE);
				setDefaultDetailTxtLp();
				break;
			case SummaryTxt_DetailTxt:
				mSummaryImg.setVisibility(View.GONE);
				mSummaryText.setVisibility(View.VISIBLE);
				mDetailText.setVisibility(View.VISIBLE);
				mDetailImg.setVisibility(View.GONE);
				setDetailTxtLp();
				break;
			case SummaryTxt_DetailImg:
				mSummaryImg.setVisibility(View.GONE);
				mSummaryText.setVisibility(View.VISIBLE);
				mDetailText.setVisibility(View.GONE);
				mDetailImg.setVisibility(View.VISIBLE);
				break;
			default:
				setStyleAll();
				break;
		}
	}

	public Type getType() {
		return mCurrentType;
	}

	/**
	 * 当动态改变了CommonItem的Type之后需要设置LayoutParams
	 */
	private void setDefaultDetailTxtLp() {
		RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		lp.addRule(RelativeLayout.CENTER_VERTICAL);
		lp.addRule(RelativeLayout.LEFT_OF, R.id.detail_img);
		mDetailText.setLayoutParams(lp);
	}

	/**
	 * 当动态改变了CommonItem的Type之后需要设置LayoutParams
	 */
	private void setDetailTxtLp() {
		RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		lp.addRule(RelativeLayout.CENTER_VERTICAL);
		lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		mDetailText.setLayoutParams(lp);
	}

	/**
	 * 注意：不提供参数为resId类型的函数，因为在Type为某些类型，无需传入值
	 * @param summaryBitmap 摘要图片
	 * @param summaryTxt 摘要文字
	 * @param detailTxt 详情文字
	 * @param detailBitmap 详情图片
	 */
	public void initData(Bitmap summaryBitmap, String summaryTxt, String detailTxt, Bitmap detailBitmap) {
		mSummaryImg.setImageBitmap(summaryBitmap);
		mSummaryText.setText(summaryTxt);
		mDetailText.setText(detailTxt);
		mDetailImg.setImageBitmap(detailBitmap);
	}

	/**
	 *
	 * @param summaryDrawable 摘要图片
	 * @param summaryTxt 摘要文字
	 * @param detailTxt 详情文字
	 * @param detailDrawable 详情图片
	 */
	public void initData(Drawable summaryDrawable, String summaryTxt, String detailTxt, Drawable detailDrawable) {
		mSummaryImg.setImageDrawable(summaryDrawable);
		mSummaryText.setText(summaryTxt);
		mDetailText.setText(detailTxt);
		mDetailImg.setImageDrawable(detailDrawable);
	}

	/**
	 * 微调SummaryImg和SummaryTxt的距离
	 * @param dp
	 */
	public void updateSummaryImgTxtDistance(int dp) {
		setSummaryImgRightMargin(dp);
	}

	/**
	 * 修改SummaryImg距离SummaryTxt的margin值
	 * @param dp
	 */
	private void setSummaryImgRightMargin(int dp) {
		RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		lp.addRule(RelativeLayout.CENTER_VERTICAL);
		lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT);
		lp.setMargins(0, 0, dip2px(getContext(), dp), 0);
		mSummaryImg.setLayoutParams(lp);
	}

	/**
	 * 微调DetailImg和DetailTxt的距离
	 * @param dp
	 */
	public void updateDetailImgTxtDistance(int dp) {
		setDetailImgLeftMargin(dp);
	}

	/**
	 * 修改DetailImg距离DetailTxt的margin值
	 * @param dp
	 */
	private void setDetailImgLeftMargin(int dp) {
		RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		lp.addRule(RelativeLayout.CENTER_VERTICAL);
		lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
		lp.setMargins(dip2px(getContext(), dp), 0, 0, 0);
		mDetailImg.setLayoutParams(lp);
	}

	public void setStyleAll() {
		mSummaryImg.setVisibility(View.VISIBLE);
		mSummaryText.setVisibility(View.VISIBLE);
		mDetailText.setVisibility(View.VISIBLE);
		mDetailImg.setVisibility(View.VISIBLE);

		setDefaultDetailTxtLp();
	}

	public void setSummaryImg(int resId) {
		mSummaryImg.setImageResource(resId);
	}

	public ImageView getSummaryImageView() {
		return mSummaryImg;
	}

	public void setSummaryImgScaleType(ScaleType scaleType) {
		mSummaryImg.setScaleType(scaleType);
	}

	/* Start------SummaryText------ */
	public void setSummaryText(int resId) {
		mSummaryText.setText(resId);
	}

	public CharSequence getSummaryText() {
		return mSummaryText.getText();
	}

	public void setSummaryText(String text) {
		mSummaryText.setText(text);
	}

	public void setSummaryText(CharSequence text) {
		mSummaryText.setText(text);
	}

	public TextView getSummaryTextView() {
		return mSummaryText;
	}

	public void setSummaryTextSize(float size) {
		mSummaryText.setTextSize(size);
	}

	public void setSummaryTextColor(int color) {
		mSummaryText.setTextColor(color);
	}
	/* End------SummaryText------ */

	/* Start------DetailText------ */
	public TextView getDetailTextView() {
		return mDetailText;
	}

	public void setDetailText(int resId) {
		mDetailText.setText(resId);
	}

	public void setDetailText(String text) {
		mDetailText.setText(text);
	}

	public CharSequence getDetailText() {
		return mDetailText.getText();
	}

	public void setDetailTextSize(float size) {
		mDetailText.setTextSize(size);
	}

	public void setDetailTextColor(int color) {
		mDetailText.setTextColor(color);
	}
	/* End------DetailText------ */

	public void setDetailImg(int resId) {
		mDetailImg.setImageResource(resId);
	}

	public ImageView getDetailImageView() {
		return mDetailImg;
	}

	public void setDetailImgScaleType(ScaleType scaleType) {
		mDetailImg.setScaleType(scaleType);
	}

	/**
	 * dip 2 px
	 * @param context
	 * @param dipValue
	 * @return
	 */
	public static int dip2px(Context context, float dipValue) {
		return (int) (dipValue * context.getResources().getDisplayMetrics().density + 0.5f);
	}

}
