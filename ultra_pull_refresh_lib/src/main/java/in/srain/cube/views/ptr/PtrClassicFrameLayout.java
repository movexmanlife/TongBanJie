package in.srain.cube.views.ptr;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;

public class PtrClassicFrameLayout extends PtrFrameLayout {

    private static final int STYLE_NORMAL_BACKGROUD = 0;
    private static final int STYLE_RED_BACKGROUD = 1;
    private PtrClassicDefaultHeader mPtrClassicHeader;
    private int mStyle = STYLE_NORMAL_BACKGROUD;

    public PtrClassicFrameLayout(Context context) {
        super(context);
        initViews(null);
    }

    public PtrClassicFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViews(attrs);
    }

    public PtrClassicFrameLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initViews(attrs);
    }

    private void initViews(AttributeSet attrs) {
        TypedArray arr = getContext().obtainStyledAttributes(attrs, R.styleable.PtrClassicHeaderType, 0, 0);
        if (arr != null) {
            mStyle = arr.getInt(R.styleable.PtrClassicHeaderType_ptr_classic_header_type, STYLE_NORMAL_BACKGROUD);
            arr.recycle();
        }
        if (mStyle == STYLE_NORMAL_BACKGROUD) {
            mPtrClassicHeader = new PtrClassicDefaultHeader(getContext());
        } else {
            mPtrClassicHeader = new PtrClassicDefaultHeader(getContext(), attrs);
        }
        setHeaderView(mPtrClassicHeader);
        addPtrUIHandler(mPtrClassicHeader);
    }

    public PtrClassicDefaultHeader getHeader() {
        return mPtrClassicHeader;
    }

    /**
     * Specify the last update time by this key string
     *
     * @param key
     */
    public void setLastUpdateTimeKey(String key) {
        if (mPtrClassicHeader != null) {
        }
    }

    /**
     * Using an object to specify the last update time.
     *
     * @param object
     */
    public void setLastUpdateTimeRelateObject(Object object) {
        if (mPtrClassicHeader != null) {
        }
    }
}
