package com.robot.tongbanjie.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.OnScrollListener;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.bartoszlipinski.recyclerviewheader.RecyclerViewHeader;
import com.robot.tongbanjie.R;
import com.robot.tongbanjie.activity.TongbaoInstructionActivity;
import com.robot.tongbanjie.adapter.ImagePagerAdapter;
import com.robot.tongbanjie.adapter.PrimeProductAdapter;
import com.robot.tongbanjie.entity.PrimeProduct;
import com.robot.tongbanjie.util.DensityUtils;
import com.robot.tongbanjie.widget.TitleBarView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import cn.trinea.android.view.autoscrollviewpager.AutoScrollViewPager;


/**
 * 精品推荐
 */
public class HomeFragment extends BaseFragment {
    @Bind(R.id.titlebar)
    TitleBarView mTitleBar;
    @Bind(R.id.recyclerView)
    RecyclerView mRecyclerView;
    private RecyclerViewHeader mRecyclerHeader;
    private PrimeProductAdapter mAdapter;
    private List<PrimeProduct> mDatas;
    private List<Integer> imageIdList;
    private TextSwitcher mTextSwitcher;

    private int mTitleBarHeight;
    private int mRecyclerHeaderHeight;
    /**
     * 首页图片广告栏的高度
     */
    private int mRecyclerHeaderBannerHeight;

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, null);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void initData() {
        mDatas = new ArrayList<PrimeProduct>();
        PrimeProduct product = new PrimeProduct();
        product.productName = "新手专享163期";
        product.productDesc = "注册理财金可用-限APP";
        product.annualRate = "预期年化";
        product.annualRateValue = 12.00f;
        product.operator = "立即抢购";
        product.timeLimit = 7;
        product.isNowBuy = true;
        mDatas.add(product);

        PrimeProduct product2 = new PrimeProduct();
        product2.productName = "新手专享221期";
        product2.productDesc = "1元起购";
        product2.annualRate = "预期年化";
        product2.annualRateValue = 18.00f;
        product2.operator = "立即抢购";
        product2.timeLimit = 43;
        product2.isNowBuy = true;
        mDatas.add(product2);

        PrimeProduct product3 = new PrimeProduct();
        product3.productName = "一铜金A1257期";
        product3.productDesc = "仅限购宝购买";
        product3.annualRate = "预期年化";
        product3.annualRateValue = 6.00f;
        product3.operator = "立即抢购";
        product3.timeLimit = 28;
        product3.isNowBuy = false;
        mDatas.add(product3);

        PrimeProduct product4 = new PrimeProduct();
        product4.productName = "一铜天下1310期";
        product4.productDesc = "抢购";
        product4.annualRate = "预期年化";
        product4.annualRateValue = 5.80f;
        product4.operator = "立即抢购";
        product4.timeLimit = 20;
        product4.isNowBuy = false;
        mDatas.add(product4);

        PrimeProduct bottom = new PrimeProduct();
        mDatas.add(bottom);

        mAdapter = new PrimeProductAdapter(getActivity(), mDatas);
    }

    @Override
    public void initTitle() {
        mTitleBar.setTitle("首页");
    }



    @Override
    public void initView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerHeaderBannerHeight = (int)getActivity().getResources().getDimension(R.dimen.home_page_banner_height);
        final int listItemMarginTop = (int)getActivity().getResources().getDimension(R.dimen.home_page_list_item_margin_top);
        mRecyclerView.setOnScrollListener(new OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                float alpha = 0;
                int scrollY = getScollY(true, mRecyclerHeaderHeight);
                int baseHeight = mRecyclerHeaderBannerHeight - mTitleBarHeight - listItemMarginTop;
                if (scrollY >= baseHeight) {
                    alpha = 1;
                } else {
                    alpha = scrollY / (baseHeight * 1.0f);
                }
                mTitleBar.setAlpha(alpha);
                super.onScrolled(recyclerView, dx, dy);
            }
        });
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerHeader = RecyclerViewHeader.fromXml(getActivity(), R.layout.list_item_prime_product_header);
        mTextSwitcher = (TextSwitcher) mRecyclerHeader.findViewById(R.id.mTextSwitcher);
        mTextSwitcher.setFactory(new ViewSwitcher.ViewFactory() {

            @Override
            public View makeView() {
                TextView tv = new TextView(getActivity());
                return tv;
            }
        });

        nextInvestInfo();

        AutoScrollViewPager viewPager = (AutoScrollViewPager) mRecyclerHeader.findViewById(R.id.view_pager);
        imageIdList = new ArrayList<Integer>();
        imageIdList.add(R.drawable.banner1);
        imageIdList.add(R.drawable.banner2);
        imageIdList.add(R.drawable.banner3);
        imageIdList.add(R.drawable.banner4);
        viewPager.setAdapter(new ImagePagerAdapter(getActivity(), imageIdList).setInfiniteLoop(true));

        viewPager.setInterval(2000);
        viewPager.startAutoScroll();
        viewPager.setCurrentItem(Integer.MAX_VALUE / 2 - Integer.MAX_VALUE / 2 % ImagePagerAdapter.getSize(imageIdList));
        viewPager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TongbaoInstructionActivity.start(getActivity());
            }
        });
        mRecyclerHeader.attachTo(mRecyclerView);

        mRecyclerHeader.post(new Runnable() {
            @Override
            public void run() {
                mRecyclerHeaderHeight = mRecyclerHeader.getHeight();
                mTitleBarHeight = mTitleBar.getHeight();
                mTitleBar.setVisibility(View.VISIBLE);
                mTitleBar.setAlpha(0);
            }
        });
    }

    /**
     * 计算RecyclerView滑动的距离
     * @param hasHead 是否有头部
     * @param headerHeight RecyclerView的头部高度
     * @return 滑动的距离
     */
    public int getScollY(boolean hasHead, int headerHeight) {
        LinearLayoutManager layoutManager = (LinearLayoutManager) mRecyclerView.getLayoutManager();
        int position = layoutManager.findFirstVisibleItemPosition();
        View firstVisiableChildView = layoutManager.findViewByPosition(position);
        int itemHeight = firstVisiableChildView.getHeight();
        if (hasHead) {
            return headerHeight + (position) * itemHeight - firstVisiableChildView.getTop();
        } else {
            return (position) * itemHeight - firstVisiableChildView.getTop();
        }
    }

    @Override
    public void setListener() {

    }

    private boolean flag = true;
    public void nextInvestInfo() {
        String totalInvestCnt;
        String totalInvestValue;
        String investContent;
        if (flag) {
            totalInvestCnt = "累计投资人数";
            totalInvestValue = "9,408,567";
            investContent = totalInvestCnt + "\n" + totalInvestValue;
        } else {
            totalInvestCnt = "累计投资金额(亿)";
            totalInvestValue = "886.4878";
            investContent = totalInvestCnt + "\n" + totalInvestValue;
        }
        flag = !flag;

        SpannableString spannableString = new SpannableString(totalInvestCnt + "\n" + totalInvestValue);
        ForegroundColorSpan colorSpan = new ForegroundColorSpan(Color.GRAY);
        AbsoluteSizeSpan fontSpan = new AbsoluteSizeSpan(DensityUtils.sp2px(getActivity(), 12));
        spannableString.setSpan(colorSpan, 0, totalInvestCnt.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(fontSpan, 0, totalInvestCnt.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

        ForegroundColorSpan colorSpan2 = new ForegroundColorSpan(Color.RED);
        AbsoluteSizeSpan fontSpan2 = new AbsoluteSizeSpan(DensityUtils.sp2px(getActivity(), 26));
        spannableString.setSpan(colorSpan2, totalInvestCnt.length() + "\n".length(), investContent.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
        spannableString.setSpan(fontSpan2, totalInvestCnt.length() + "\n".length(), investContent.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

        mTextSwitcher.setText(spannableString);
        mTextSwitcher.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (getActivity() != null) {
                    if (!getActivity().isFinishing()) {
                        nextInvestInfo();
                    }
                }
            }
        }, 1500);
    }

}
