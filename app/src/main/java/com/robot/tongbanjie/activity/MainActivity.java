package com.robot.tongbanjie.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.robot.tongbanjie.R;
import com.robot.tongbanjie.fragment.HomeFragment;
import com.robot.tongbanjie.fragment.MoreFragment;
import com.robot.tongbanjie.fragment.MyAssertFragment;
import com.robot.tongbanjie.fragment.ProductFragment;
import com.robot.tongbanjie.service.NetworkStateService;
import com.robot.tongbanjie.widget.BaseTipsView;
import com.robot.tongbanjie.widget.ShowMemberTipsView;
import com.robot.tongbanjie.widget.ShowMoreTipsView;

import java.lang.reflect.Method;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends FragmentActivity implements View.OnClickListener {
    private static final String KEY_FRAGMENT_TAG = "fragment_tag";
    private static final String FRAGMENT_TAG_HOME = "fragment_home";
    private static final String FRAGMENT_TAG_PRODUCT = "fragment_product";
    private static final String FRAGMENT_TAG_MY_ASSERT = "fragment_my_assert";
    private static final String FRAGMENT_TAG_MORE = "fragment_more";

    @Bind(R.id.layout_home)
    LinearLayout mHomeLayout;
    @Bind(R.id.layout_product)
    LinearLayout mProductLayout;
    @Bind(R.id.layout_my_assert)
    LinearLayout mMyAssertLayout;
    @Bind(R.id.layout_more)
    LinearLayout mMoreLayout;

    private HomeFragment mHomeFragment;
    private ProductFragment mProductFragment;
    private MyAssertFragment mMyAssertFragment;
    private MoreFragment mMoreFragment;
    private String[] mFragmentTags = new String[]{FRAGMENT_TAG_HOME, FRAGMENT_TAG_PRODUCT, FRAGMENT_TAG_MY_ASSERT, FRAGMENT_TAG_MORE};
    private String mFragmentCurrentTag = FRAGMENT_TAG_HOME;
    private LinearLayout[] mLayouts = null;

    private ShowMoreTipsView mShowMoreTipsView;
    private ShowMemberTipsView mShowMemberTipsView;

    public static void start(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

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
        showMoreTipsView();
        Intent intent  = new Intent(this, NetworkStateService.class);
        startService(intent);
    }

    /**
     * 更多引导
     */
    private void showMoreTipsView() {
        if (mShowMoreTipsView == null) {
            mShowMoreTipsView = new ShowMoreTipsView(this);
        }
        mShowMoreTipsView.setOnCloseListener(new ShowMoreTipsView.OnCloseListener() {
            @Override
            public void onClose(BaseTipsView baseTipsView) {
                baseTipsView.dismiss(MainActivity.this);
            }
        });
        mShowMoreTipsView.setOnSureListener(new ShowMoreTipsView.OnSureListener() {
            @Override
            public void onSure(BaseTipsView baseTipsView) {
                baseTipsView.dismiss(MainActivity.this);
                mMoreLayout.performClick();
                showMemberTipsView();
            }
        });
        mShowMoreTipsView.show(this);
    }

    /**
     * 会员引导
     */
    private void showMemberTipsView() {
        if (mShowMemberTipsView == null) {
            mShowMemberTipsView = new ShowMemberTipsView(this);
        }
        mShowMemberTipsView.setOnCloseListener(new ShowMemberTipsView.OnCloseListener() {
            @Override
            public void onClose(BaseTipsView baseTipsView) {
                baseTipsView.dismiss(MainActivity.this);
            }
        });
        mShowMemberTipsView.setOnSureListener(new ShowMemberTipsView.OnSureListener() {
            @Override
            public void onSure(BaseTipsView baseTipsView) {
                baseTipsView.dismiss(MainActivity.this);
            }
        });
        mShowMemberTipsView.show(this);
    }

    /**
     *  重写返回键
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            /**
             * 有引导提示的时候，将引导去掉
             */
            if (mShowMoreTipsView != null) {
                if (mShowMoreTipsView.isShowing()) {
                    mShowMoreTipsView.dismiss(this);
                    return true;
                }
            }
            if (mShowMemberTipsView != null) {
                if (mShowMemberTipsView.isShowing()) {
                    mShowMemberTipsView.dismiss(this);
                    return true;
                }
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    private void initData() {
        mLayouts = new LinearLayout[]{mHomeLayout, mProductLayout, mMyAssertLayout, mMoreLayout};
    }

    private void setListener() {
        for (int i = 0; i < mLayouts.length; i++) {
            mLayouts[i].setOnClickListener(this);
        }

        if (TextUtils.equals(FRAGMENT_TAG_HOME, mFragmentCurrentTag)) {
            mHomeLayout.performClick();
        } else if (TextUtils.equals(FRAGMENT_TAG_PRODUCT, mFragmentCurrentTag)) {
            mProductLayout.performClick();
        } else if (TextUtils.equals(FRAGMENT_TAG_MY_ASSERT, mFragmentCurrentTag)) {
            mMyAssertLayout.performClick();
        } else if (TextUtils.equals(FRAGMENT_TAG_MORE, mFragmentCurrentTag)) {
            mMoreLayout.performClick();
        }

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putString(KEY_FRAGMENT_TAG, mFragmentCurrentTag);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onClick(View v) {
        onTabSelect((LinearLayout) v);
    }

    /**
     * 切换tab页
     * @param itemLayout
     */
    public void onTabSelect(LinearLayout itemLayout) {
        int id = itemLayout.getId();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        hideFragments(manager, transaction);

        for (int i = 0; i < mLayouts.length; i++) {
            mLayouts[i].setSelected(false);
        }
        itemLayout.setSelected(true);

        if (id == R.id.layout_home) {
            selectedFragment(transaction, mHomeFragment, HomeFragment.class, FRAGMENT_TAG_HOME);
        } else if (id == R.id.layout_product) {
            selectedFragment(transaction, mProductFragment, ProductFragment.class, FRAGMENT_TAG_PRODUCT);
        } else if (id == R.id.layout_my_assert) {
            selectedFragment(transaction, mMyAssertFragment, MyAssertFragment.class, FRAGMENT_TAG_MY_ASSERT);
        } else if (id == R.id.layout_more) {
            selectedFragment(transaction, mMoreFragment, MoreFragment.class, FRAGMENT_TAG_MORE);
        }
        transaction.commit();
    }

    private void selectedFragment(FragmentTransaction transaction, Fragment fragment, Class<?> clazz, String tag) {
        mFragmentCurrentTag = tag;
        if (fragment == null) {
            try {
                Method newInstanceMethod = clazz.getDeclaredMethod("newInstance");;
                fragment = (Fragment) newInstanceMethod.invoke(null);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            transaction.add(R.id.fragment_container, fragment, tag);
        }
        transaction.show(fragment);
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

    /**
     * 恢复fragment
     */
    private void restoreFragments() {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        for (int i = 0; i < mFragmentTags.length; i++) {
            Fragment fragment = manager.findFragmentByTag(mFragmentTags[i]);
            if (fragment instanceof HomeFragment) {
                mHomeFragment = (HomeFragment)fragment;
            } else if (fragment instanceof ProductFragment) {
                mProductFragment = (ProductFragment)fragment;
            } else if (fragment instanceof MyAssertFragment) {
                mMyAssertFragment = (MyAssertFragment)fragment;
            } else if (fragment instanceof MoreFragment) {
                mMoreFragment = (MoreFragment)fragment;
            }
            transaction.hide(fragment);
        }
        transaction.commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        Intent intent  = new Intent(this, NetworkStateService.class);
        stopService(intent);
    }
}
