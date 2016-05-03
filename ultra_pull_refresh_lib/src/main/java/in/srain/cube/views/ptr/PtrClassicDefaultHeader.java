package in.srain.cube.views.ptr;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.TypedArray;
import android.graphics.drawable.AnimationDrawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import in.srain.cube.views.ptr.indicator.PtrIndicator;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PtrClassicDefaultHeader extends FrameLayout implements PtrUIHandler {

    private TextView mTitleTextView;
    private View mRotateView;
    private ImageView mIndicatorView;
    private AnimationDrawable mAnimationDrawable;
    private static final int STYLE_NORMAL_BACKGROUD = 0;
    private static final int STYLE_RED_BACKGROUD = 1;
    private int mStyle = STYLE_NORMAL_BACKGROUD;

    public PtrClassicDefaultHeader(Context context) {
        super(context);
        initViews(null);
    }

    public PtrClassicDefaultHeader(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViews(attrs);
    }

    public PtrClassicDefaultHeader(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initViews(attrs);
    }

    protected void initViews(AttributeSet attrs) {
        TypedArray arr = getContext().obtainStyledAttributes(attrs, R.styleable.PtrClassicHeaderType, 0, 0);
        if (arr != null) {
            mStyle = arr.getInt(R.styleable.PtrClassicHeaderType_ptr_classic_header_type, STYLE_NORMAL_BACKGROUD);
            arr.recycle();
        }
        View header = null;
        if (mStyle == STYLE_NORMAL_BACKGROUD) {
            header = LayoutInflater.from(getContext()).inflate(R.layout.cube_ptr_classic_default_header, this);
        } else {
            header = LayoutInflater.from(getContext()).inflate(R.layout.cube_ptr_classic_default_header2, this);
        }

        mRotateView = header.findViewById(R.id.ptr_classic_header_rotate_view);
        mRotateView.setVisibility(View.GONE);
        mIndicatorView = (ImageView)header.findViewById(R.id.ptr_classic_header_indicator_view);
        mAnimationDrawable = (AnimationDrawable) mRotateView.getBackground();

        mTitleTextView = (TextView) header.findViewById(R.id.ptr_classic_header_rotate_view_header_title);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    @Override
    public void onUIReset(PtrFrameLayout frame) {
        mRotateView.setVisibility(View.GONE);
        if (mAnimationDrawable != null) {
            mAnimationDrawable.stop();
        }
        mIndicatorView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onUIRefreshPrepare(PtrFrameLayout frame) {

        mTitleTextView.setVisibility(VISIBLE);
        if (frame.isPullToRefresh()) {
            mTitleTextView.setText(getResources().getString(R.string.cube_ptr_pull_down_to_refresh));
        } else {
            mTitleTextView.setText(getResources().getString(R.string.cube_ptr_pull_down));
        }
        mRotateView.setVisibility(View.GONE);
        if (mAnimationDrawable != null) {
            mAnimationDrawable.stop();
        }
        mIndicatorView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onUIRefreshBegin(PtrFrameLayout frame) {
        mTitleTextView.setVisibility(VISIBLE);
        mTitleTextView.setText(R.string.cube_ptr_refreshing);

        mRotateView.setVisibility(View.VISIBLE);
        if (mAnimationDrawable != null) {
            mAnimationDrawable.start();
        }
        mIndicatorView.setVisibility(View.GONE);
    }

    @Override
    public void onUIRefreshComplete(PtrFrameLayout frame) {

        mTitleTextView.setVisibility(VISIBLE);
        mTitleTextView.setText(getResources().getString(R.string.cube_ptr_refresh_complete));

        mRotateView.setVisibility(View.GONE);
        if (mAnimationDrawable != null) {
            mAnimationDrawable.stop();
        }
        mIndicatorView.setVisibility(View.VISIBLE);
    }

    @Override
    public void onUIPositionChange(PtrFrameLayout frame, boolean isUnderTouch, byte status, PtrIndicator ptrIndicator) {

        final int mOffsetToRefresh = frame.getOffsetToRefresh();
        final int currentPos = ptrIndicator.getCurrentPosY();
        final int lastPos = ptrIndicator.getLastPosY();

        if (currentPos < mOffsetToRefresh && lastPos >= mOffsetToRefresh) {
            if (isUnderTouch && status == PtrFrameLayout.PTR_STATUS_PREPARE) {
                crossRotateLineFromBottomUnderTouch(frame);
                if (mRotateView != null) {
                }
            }
        } else if (currentPos > mOffsetToRefresh && lastPos <= mOffsetToRefresh) {
            if (isUnderTouch && status == PtrFrameLayout.PTR_STATUS_PREPARE) {
                crossRotateLineFromTopUnderTouch(frame);
                if (mRotateView != null) {
                }
            }
        }
    }

    private void crossRotateLineFromTopUnderTouch(PtrFrameLayout frame) {
        if (!frame.isPullToRefresh()) {
            mTitleTextView.setVisibility(VISIBLE);
            mTitleTextView.setText(R.string.cube_ptr_release_to_refresh);
        }
    }

    private void crossRotateLineFromBottomUnderTouch(PtrFrameLayout frame) {
        mTitleTextView.setVisibility(VISIBLE);
        if (frame.isPullToRefresh()) {
            mTitleTextView.setText(getResources().getString(R.string.cube_ptr_pull_down_to_refresh));
        } else {
            mTitleTextView.setText(getResources().getString(R.string.cube_ptr_pull_down));
        }
    }
}
