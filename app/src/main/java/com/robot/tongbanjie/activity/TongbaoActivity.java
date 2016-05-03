package com.robot.tongbanjie.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarLineChartBase;
import com.github.mikephil.charting.charts.Chart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.ChartData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.formatter.YAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.robot.tongbanjie.R;
import com.robot.tongbanjie.util.NetworkBroadcastReceiverHelper;
import com.robot.tongbanjie.util.NetworkUtils;
import com.robot.tongbanjie.util.TextViewUtils;
import com.robot.tongbanjie.widget.TitleBarView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;


public class TongbaoActivity extends BaseActivity implements OnClickListener {
    private static final String TAG = TongbaoActivity.class.getSimpleName();
    @Bind(R.id.titlebar)
    TitleBarView mTitleBar;
    @Bind(R.id.view_no_network)
    View mViewNoNetwork;
    @Bind(R.id.id_ptr_classic_frame_layout)
    PtrClassicFrameLayout mPtrClassicFrameLayout;
    @Bind(R.id.rl_info_about_tongbao)
    RelativeLayout infoAboutTongbao;
    @Bind(R.id.rl_know_about_tongbao)
    RelativeLayout knowAboutTongbao;
    @Bind(R.id.seven_annual_rate)
    LinearLayout sevenAnnualTate;
    @Bind(R.id.ten_thousand_income)
    LinearLayout tenThousandIncome;
    @Bind(R.id.lineChart)
    LineChart lineChart;
    private NetworkBroadcastReceiverHelper mNetworkBroadcastReceiverHelper;
    private static final int ONE_WEEK_DAYS = 7;
    private static final int ONE_DAY = 24 * 60 * 60 * 1000;
    private List<Float> mChartDatas = new ArrayList<>(ONE_WEEK_DAYS);
    private Typeface mTf;

    public static void start(Context context) {
        Intent intent = new Intent(context, TongbaoActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_tongbao);
        ButterKnife.bind(this);
        registerBroadcast();
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initData() {
        mChartDatas.add(3.72F);
        mChartDatas.add(3.72F);
        mChartDatas.add(3.72F);
        mChartDatas.add(3.72F);
        mChartDatas.add(3.72F);
        mChartDatas.add(3.70F);
        mChartDatas.add(3.66F);
    }

    @Override
    public void initTitle() {
        mTitleBar.initTitleWithLeftTxtRightImg("铜宝", "返回", R.drawable.btn_tongbao_setting_sel);
        TextViewUtils.setLeftImage(mTitleBar.getLeftTxtView(), R.drawable.btn_back_sel, 5);
        mTitleBar.setOnLeftTxtClickListener(new TitleBarView.OnLeftTxtClickListener() {
            @Override
            public void onClick() {
                finish();
            }
        });
        mTitleBar.setOnRightImgClickListener(new TitleBarView.OnRightImgClickListener() {
            @Override
            public void onClick() {
                TongbaoAutoSettingActivity.start(TongbaoActivity.this, TongbaoAutoSettingActivity.TITLE_SETTING);
            }
        });
    }

    @Override
    public void initView() {
        mPtrClassicFrameLayout.setPtrHandler(new PtrHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                mPtrClassicFrameLayout.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mPtrClassicFrameLayout.refreshComplete();
                    }
                }, 3000);
            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }
        });
        showNetworkTips();
        initChartLine();
        setLineChartData(ONE_WEEK_DAYS, mChartDatas);
    }

    /**
     * 显示网络提示
     */
    private void showNetworkTips() {
        if (NetworkUtils.getConnectivityStatus(this) == NetworkUtils.TYPE_NOT_CONNECTED) {
            mViewNoNetwork.setVisibility(View.VISIBLE);
        } else {
            mViewNoNetwork.setVisibility(View.GONE);
        }
    }

    @Override
    public void setListener() {
        infoAboutTongbao.setOnClickListener(this);
        knowAboutTongbao.setOnClickListener(this);
        sevenAnnualTate.setOnClickListener(this);
        tenThousandIncome.setOnClickListener(this);
    }

    private void initChartLine() {
        lineChart.setExtraBottomOffset(15f);
        lineChart.getAxisLeft().setDrawGridLines(false);
        lineChart.getXAxis().setDrawGridLines(false);
        setup(lineChart);
    }

    protected void setup(Chart<?> chart) {
        mTf = Typeface.createFromAsset(getAssets(), "OpenSans-Regular.ttf");

        // no description text
        chart.setDescription("");
        chart.setNoDataTextDescription("You need to provide data for the chart.");

        // enable touch gestures
        chart.setTouchEnabled(false);

        if (chart instanceof BarLineChartBase) {

            BarLineChartBase mChart = (BarLineChartBase) chart;

            mChart.setDrawGridBackground(false);

            // enable scaling and dragging
            mChart.setDragEnabled(true);
            mChart.setScaleEnabled(true);

            // if disabled, scaling can be done on x- and y-axis separately
            mChart.setPinchZoom(false);

            YAxis leftAxis = mChart.getAxisLeft();
            leftAxis.removeAllLimitLines(); // reset all limit lines to avoid overlapping lines
            leftAxis.setTypeface(mTf);
            leftAxis.setTextSize(8f);
            leftAxis.setTextColor(Color.DKGRAY);
            leftAxis.setValueFormatter(new PercentFormatter());

            XAxis xAxis = mChart.getXAxis();
            xAxis.setTypeface(mTf);
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
            xAxis.setTextSize(8f);
            xAxis.setTextColor(Color.DKGRAY);

            mChart.getAxisRight().setEnabled(false);
        }
    }

    protected void styleData(ChartData data) {
        data.setValueTypeface(mTf);
        data.setValueTextSize(8f);
        data.setValueTextColor(Color.DKGRAY);
        data.setValueFormatter(new PercentFormatter());
    }

    private void setLineChartData(int count, List<Float> datas) {
        float axisWidth = 1.2f;
        float axisTextSize = 8f;
        ArrayList<String> xVals = new ArrayList<String>();
        Date date = new Date();
        long now = date.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
        for (int i = 0; i < count; i++) {
            String str = sdf.format(now + i * ONE_DAY);
            xVals.add(String.valueOf(str));
        }

        ArrayList<Entry> yVals = new ArrayList<Entry>();
        for (int i = 0; i < count; i++) {
            yVals.add(new Entry(datas.get(i), i));
        }
        LineDataSet lineDataSet = new LineDataSet(yVals, null);
        lineDataSet.setDrawCubic(false);
//        lineDataSet.setLabel("fffffffff");
        lineDataSet.setDrawCircleHole(false);
        lineDataSet.setColor(getResources().getColor(R.color.slash_line_color));
        lineDataSet.setCircleColor(ColorTemplate.rgb("#000000"));
        lineDataSet.setLineWidth(1.8f);
        // 不画圈圈
        lineDataSet.setDrawCircles(false);
        // 圈圈大小
        lineDataSet.setCircleSize(1.8f);
        // 不显示线上面的值
        lineDataSet.setDrawValues(false);
        lineDataSet.setFillColor(getResources().getColor(R.color.chart_fill_color));
        lineDataSet.setDrawFilled(true);

        ArrayList<ILineDataSet> dataSets = new ArrayList<ILineDataSet>();
        dataSets.add(lineDataSet);

        // set data
        YAxis leftAxis = lineChart.getAxisLeft();
        // 左边Y坐标标签的个数
        leftAxis.setAxisLineWidth(axisWidth);
        leftAxis.setAxisLineColor(getResources().getColor(R.color.line_color));
        leftAxis.setTextColor(getResources().getColor(R.color.txt_gray));
        // 左边标签的个数，以及是否显示
        leftAxis.setLabelCount(6, true);

        leftAxis.setValueFormatter(new YAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value, YAxis yAxis) {
                return String.format("%.2f", value) + "%";
            }
        });
        leftAxis.setAxisMaxValue(3.83f);
        leftAxis.setAxisMinValue(3.29f); // this replaces setStartAtZero(true)
        // 设置X轴标签的颜色
        lineChart.getXAxis().setAxisLineWidth(axisWidth);
        lineChart.getXAxis().setAxisLineColor(getResources().getColor(R.color.line_color));
        lineChart.getXAxis().setTextColor(getResources().getColor(R.color.txt_gray));
        // 设置X轴标签的字体的大小
        lineChart.getXAxis().setTextSize(axisTextSize);

        lineChart.getXAxis().setLabelsToSkip(0);

        // 设置左边Y轴
        lineChart.getAxisLeft().setTextSize(axisTextSize);
        LineData lineData = new LineData(xVals, dataSets);
        styleData(lineData);
        lineChart.setData(lineData);
        lineChart.animateY(1400, Easing.EasingOption.EaseInOutQuart);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_info_about_tongbao:
                TongbaoInstructionActivity.start(this);
                break;
            case R.id.rl_know_about_tongbao:
                TongbaoAssertsSettingActivity.start(this);
                break;
            case R.id.seven_annual_rate:
                break;
            case R.id.ten_thousand_income:
                break;
            default:
                break;
        }
    }

    private void registerBroadcast() {
        mNetworkBroadcastReceiverHelper = new NetworkBroadcastReceiverHelper(this,
                new NetworkBroadcastReceiverHelper.OnNetworkStateChangedListener(){
                    @Override
                    public void onConnected() {
                        if (mViewNoNetwork != null) {
                            mViewNoNetwork.setVisibility(View.GONE);
                        }
                    }
                    @Override
                    public void onDisConnected() {
                        if (mViewNoNetwork != null) {
                            mViewNoNetwork.setVisibility(View.VISIBLE);
                        }
                    }
                });
        mNetworkBroadcastReceiverHelper.register();
    }

    private void unregisterBroadcast() {
        mNetworkBroadcastReceiverHelper.unregister();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterBroadcast();
    }
}
