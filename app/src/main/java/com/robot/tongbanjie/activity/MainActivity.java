package com.robot.tongbanjie.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.robot.tongbanjie.R;
import com.robot.tongbanjie.fragment.HomeFragment;
import com.robot.tongbanjie.fragment.MoreFragment;
import com.robot.tongbanjie.fragment.MyAssertFragment;
import com.robot.tongbanjie.fragment.ProductFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends FragmentActivity implements View.OnClickListener {
    private static final String KEY_FRAGMENT_TAG = "fragment_tag";

    private static final String FRAGMENT_TAG_HOME = "home";
    private static final String FRAGMENT_TAG_PRODUCT = "product";
    private static final String FRAGMENT_TAG_MY_ASSERT = "my_assert";
    private static final String FRAGMENT_TAG_MORE = "more";

    private HomeFragment mHomeFragment;
    private ProductFragment mProductFragment;
    private MyAssertFragment mMyAssertFragment;
    private MoreFragment mMoreFragment;
    private String[] mFragmentTags = new String[]{FRAGMENT_TAG_HOME, FRAGMENT_TAG_PRODUCT, FRAGMENT_TAG_MY_ASSERT, FRAGMENT_TAG_MORE};
    private String mFragmentCurrentTag = FRAGMENT_TAG_HOME;

    private LinearLayout[] mLayouts = null;
    private int[] NORMAL_BACKGROUD = new int[]{R.drawable.hot_product_commend_normal, R.drawable.finacing_product_normal,
            R.drawable.my_assets_normal, R.drawable.more_normal};
    private SparseIntArray mImgBgMap = new SparseIntArray(NORMAL_BACKGROUD.length);

    @Bind(R.id.img_home)
    ImageView imgHome;
    @Bind(R.id.img_product)
    ImageView imgProduct;
    @Bind(R.id.img_my_assert)
    ImageView imgMyAssert;
    @Bind(R.id.img_more)
    ImageView imgMore;

    @Bind(R.id.txt_home)
    TextView txtHome;
    @Bind(R.id.txt_product)
    TextView txtProduct;
    @Bind(R.id.txt_my_assert)
    TextView txtMyAssert;
    @Bind(R.id.txt_more)
    TextView txtMore;

    @Bind(R.id.layout_home)
    LinearLayout layoutHome;
    @Bind(R.id.layout_product)
    LinearLayout layoutProduct;
    @Bind(R.id.layout_my_assert)
    LinearLayout layoutMyAssert;
    @Bind(R.id.layout_more)
    LinearLayout layoutMore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            restoreFragments();
            mFragmentCurrentTag =  savedInstanceState.getString(KEY_FRAGMENT_TAG);
        }

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        initData();
        setListener();
    }

    private void initData() {
        layoutHome.setTag(new TabViewHolder(imgHome, txtHome));
        layoutProduct.setTag(new TabViewHolder(imgProduct, txtProduct));
        layoutMyAssert.setTag(new TabViewHolder(imgMyAssert, txtMyAssert));
        layoutMore.setTag(new TabViewHolder(imgMore, txtMore));
        mLayouts = new LinearLayout[]{layoutHome, layoutProduct, layoutMyAssert, layoutMore};

        mImgBgMap.put(R.id.img_home, R.drawable.hot_product_commend_pressed);
        mImgBgMap.put(R.id.img_product, R.drawable.finacing_product_pressed);
        mImgBgMap.put(R.id.img_my_assert, R.drawable.my_assets_pressed);
        mImgBgMap.put(R.id.img_more, R.drawable.more_pressed);
    }

    private void setListener() {
        for (int i = 0; i < mLayouts.length; i++) {
            mLayouts[i].setOnClickListener(this);
        }

        if (TextUtils.equals(FRAGMENT_TAG_HOME, mFragmentCurrentTag)) {
            layoutHome.performClick();
        } else if (TextUtils.equals(FRAGMENT_TAG_PRODUCT, mFragmentCurrentTag)) {
            layoutProduct.performClick();
        } else if (TextUtils.equals(FRAGMENT_TAG_MY_ASSERT, mFragmentCurrentTag)) {
            layoutMyAssert.performClick();
        } else if (TextUtils.equals(FRAGMENT_TAG_MORE, mFragmentCurrentTag)) {
            layoutMore.performClick();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString(KEY_FRAGMENT_TAG, mFragmentCurrentTag);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onClick(View v) {
        onTabSelect(v);
    }

    /**
     * 切换tab页
     * @param v
     */
    public void onTabSelect(View v) {
        int id = v.getId();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        hideFragments(manager, transaction);
        setNormalBackgrounds();
        //看着很繁琐吧

        if (id == R.id.layout_home) {
            setSelectedBackgroud((LinearLayout) v);
            mFragmentCurrentTag = FRAGMENT_TAG_HOME;

            if (mHomeFragment == null) {
                mHomeFragment = HomeFragment.newInstance();
                transaction.add(R.id.fragment_container, mHomeFragment, FRAGMENT_TAG_HOME);
            }
            transaction.show(mHomeFragment);
        } else if (id == R.id.layout_product) {
            setSelectedBackgroud((LinearLayout) v);
            mFragmentCurrentTag = FRAGMENT_TAG_PRODUCT;

            if (mProductFragment == null) {
                mProductFragment = ProductFragment.newInstance();
                transaction.add(R.id.fragment_container, mProductFragment, FRAGMENT_TAG_PRODUCT);
            }
            transaction.show(mProductFragment);
        } else if (id == R.id.layout_my_assert) {
            setSelectedBackgroud((LinearLayout) v);
            mFragmentCurrentTag = FRAGMENT_TAG_MY_ASSERT;

            if (mMyAssertFragment == null) {
                mMyAssertFragment = MyAssertFragment.newInstance();
                transaction.add(R.id.fragment_container, mMyAssertFragment, FRAGMENT_TAG_MY_ASSERT);
            }
            transaction.show(mMyAssertFragment);
        } else if (id == R.id.layout_more) {
            setSelectedBackgroud((LinearLayout)v);
            mFragmentCurrentTag = FRAGMENT_TAG_MORE;

            if (mMoreFragment == null) {
                mMoreFragment = MoreFragment.newInstance();
                transaction.add(R.id.fragment_container, mMoreFragment, FRAGMENT_TAG_MORE);
            }
            transaction.show(mMoreFragment);
        }

        transaction.commit();
    }


    /**
     * 设置底部背景为正常状态
     */
    private void setNormalBackgrounds() {
        for (int i = 0; i < mLayouts.length; i++) {
            setTabBackgroud(mLayouts[i], NORMAL_BACKGROUD[i], R.color.tab_txt_normal_color);
        }
    }

    /**
     * 设置底部背景为选中状态
     */
    private void setSelectedBackgroud(LinearLayout linearLayout) {
        TabViewHolder tabViewHolder = (TabViewHolder) linearLayout.getTag();
        int imgResId = mImgBgMap.get(tabViewHolder.img.getId());
        setTabBackgroud(linearLayout, imgResId, R.color.tab_txt_selected_color);
    }

    private void setTabBackgroud(LinearLayout linearLayout, int imgResId, int colorResId) {
        TabViewHolder tabViewHolder = (TabViewHolder) linearLayout.getTag();

        tabViewHolder.img.setImageResource(imgResId);
        tabViewHolder.txt.setTextColor(getResources().getColor(colorResId));
    }

    /**
     * 先全部隐藏
     * @param fragmentManager
     * @param transaction
     */
    private void hideFragments(FragmentManager fragmentManager, FragmentTransaction transaction) {
        for (int i = 0; i < mFragmentTags.length; i++) {
            Fragment fragment = fragmentManager.findFragmentByTag(mFragmentTags[i]);
            if (fragment != null && fragment.isVisible()) {
                transaction.hide(fragment);
            }
        }
    }

    private void restoreFragments() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        mHomeFragment = (HomeFragment) manager.findFragmentByTag(FRAGMENT_TAG_HOME);
        transaction.hide(mHomeFragment);
        mMyAssertFragment = (MyAssertFragment) manager.findFragmentByTag(FRAGMENT_TAG_PRODUCT);
        transaction.hide(mMyAssertFragment);
        mProductFragment = (ProductFragment) manager.findFragmentByTag(FRAGMENT_TAG_MY_ASSERT);
        transaction.hide(mProductFragment);
        mMoreFragment = (MoreFragment) manager.findFragmentByTag(FRAGMENT_TAG_MORE);
        transaction.hide(mMoreFragment);
        transaction.commit();
    }

    private static class TabViewHolder {
        public ImageView img;
        public TextView txt;

        public TabViewHolder(ImageView img, TextView txt) {
            this.img = img;
            this.txt = txt;
        }
    }
}