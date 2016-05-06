package com.robot.tongbanjie.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.robot.tongbanjie.R;
import com.robot.tongbanjie.dialog.LoadingDialog;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 */
public class BottomDetailProductFragment extends BaseFragment {
    @Bind(R.id.tab_title)
    TabLayout tabTitle;
    @Bind(R.id.view_pager)
    ViewPager viewPager;
    private View rootView;
    private LoadingDialog mLoadingDialog;
    private List<BaseFragment> listFragment;
    private List<String> listTitle;
    private FindTabAdapter mAdapter;

    public static BottomDetailProductFragment newInstance() {
        BottomDetailProductFragment fragment = new BottomDetailProductFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_detail_product2, null);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void initData() {
        mLoadingDialog = new LoadingDialog(getActivity(), ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, R.style.LoadingDialogLight);
        listFragment = new ArrayList<>();
        listFragment.add(DetailProductCategoryFragment.newInstance());
        listFragment.add(DetailProjectCategoryFragment.newInstance());
        listFragment.add(DetailSafeCategoryFragment.newInstance());

        listTitle = new ArrayList<>();
        listTitle.add("产品详情");
        listTitle.add("项目描述");
        listTitle.add("安全保障");
    }

    @Override
    public void initTitle() {
    }

    @Override
    public void initView() {
        // 设置TabLayout的模式
        tabTitle.setTabMode(TabLayout.MODE_FIXED);
        tabTitle.addTab(tabTitle.newTab().setText(listTitle.get(0)));
        tabTitle.addTab(tabTitle.newTab().setText(listTitle.get(1)));
        tabTitle.addTab(tabTitle.newTab().setText(listTitle.get(2)));

        mAdapter = new FindTabAdapter(getActivity().getSupportFragmentManager(), listFragment, listTitle);

        //viewpager加载adapter
        viewPager.setAdapter(mAdapter);
        //tab_FindFragment_title.setViewPager(vp_FindFragment_pager);
        //TabLayout加载viewpager
        tabTitle.setupWithViewPager(viewPager);
        //tab_FindFragment_title.set
    }

    @Override
    public void setListener() {

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    public void beginLoadData() {
        mLoadingDialog.show();
        rootView.postDelayed(new Runnable() {
            @Override
            public void run() {
                mLoadingDialog.dismiss();
            }
        }, 2000);
    }

    public class FindTabAdapter extends FragmentPagerAdapter {

        private List<BaseFragment> listFragment;  // fragment列表
        private List<String> listTitle;  // tab名的列表

        public FindTabAdapter(FragmentManager fm, List<BaseFragment> list_fragment, List<String> listTitle) {
            super(fm);
            this.listFragment = list_fragment;
            this.listTitle = listTitle;
        }

        @Override
        public Fragment getItem(int position) {
            return listFragment.get(position);
        }

        @Override
        public int getCount() {
            return listTitle.size();
        }

        // 此方法用来显示tab上的名字
        @Override
        public CharSequence getPageTitle(int position) {
            return listTitle.get(position % listTitle.size());
        }
    }

}
