package com.robot.tongbanjie.activity;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.utils.Utils;
import com.robot.tongbanjie.R;
import com.robot.tongbanjie.entity.BankInfo;
import com.robot.tongbanjie.widget.CheckBoxObservable;
import com.robot.tongbanjie.widget.TextViewObserver;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 测试用的Activity
 */
public class TestActivity extends FragmentActivity implements View.OnClickListener {
    TextView txt;
    Button btn;
    String assetsBankResourceFileName = "bankresource.txt";
    TextViewObserver textViewObserver;
    TextViewObserver textViewObserver2;
    private CheckBoxObservable checkBoxObservable;
    LineChart lineChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
//        txt = (TextView) findViewById(R.id.txt);
//        btn = (Button) findViewById(R.id.btn);
//        btn.setOnClickListener(this);
        textViewObserver = (TextViewObserver)findViewById(R.id.TextViewObserver);
        textViewObserver2 = (TextViewObserver)findViewById(R.id.TextViewObserver2);
        checkBoxObservable = (CheckBoxObservable)findViewById(R.id.CheckBoxObservable);
        checkBoxObservable.addObserver(textViewObserver);
        checkBoxObservable.addObserver(textViewObserver2);
    }

    private void m4902f() {
//        this.lineChart.setTouchEnabled(false);
//        this.lineChart.setBackgroundColor(getResources().getColor(R.color.white));
//        lineChart.setDrawBorders(true);
//        this.lineChart.setBorderPositions(new BorderPosition[]{BorderPosition.BOTTOM, BorderPosition.LEFT});
//        this.lineChart.setStartAtZero(true);
//        this.lineChart.setDescription(bq.f5351b);
//        this.lineChart.setDrawYValues(false);
//        this.lineChart.setDrawVerticalGrid(false);
//        this.lineChart.setDrawHorizontalGrid(false);
//        this.lineChart.setDrawGridBackground(false);
//        this.lineChart.setDrawLegend(false);
//        this.lineChart.getYLabels().setLabelCount(7);
//        this.lineChart.setDecimals(2);
//        this.lineChart.setOffsets(5.0f, 12.0f, 12.0f, 12.0f);
//        this.lineChart.setUnit("%");
//        this.lineChart.setNoDataText("\u6682\u65e0\u6570\u636e");
//        Paint paint = new Paint(1);
//        paint.setColor(Color.rgb(232, 54, 99));
//        paint.setTextAlign(Paint.Align.CENTER);
//        paint.setTextSize(Utils.convertDpToPixel(16.0f));
//        this.lineChart.setPaint(paint, 7);
//        paint = new Paint(1);
//        paint.setColor(getResources().getColor(R.color.gray_light4));
//        paint.setTextAlign(Paint.Align.CENTER);
//        paint.setTextSize(Utils.convertDpToPixel(10.0f));
//        this.lineChart.setPaint(paint, 6);
//        new Paint(1).setColor(getResources().getColor(R.color.gray_light4));
////        this.lineChart.setYLabelTextSize(getResources().getDimension(R.dimen.tongbao_y_label_size_seven_annual_rate));
//        this.lineChart.setPaint(paint, 5);
////        this.lineChart.getXLabels().setAdjustXLabels(false);
//        this.lineChart.getXAxis().setDrawAxisLine(true);
//        this.lineChart.getXAxis().setDrawGridLines(false);
//        paint = new Paint();
//        paint.setColor(getResources().getColor(R.color.txt_red));
//        paint.setStrokeWidth(2.0f);
//        paint.setStyle(Paint.Style.STROKE);
//        this.lineChart.setPaint(paint, 12);
//        paint = new Paint();
//        paint.setStyle(Paint.Style.FILL);
//        paint.setColor(getResources().getColor(R.color.white));
//        this.lineChart.setPaint(paint, 4);
//        XAxis xLabels = this.lineChart.getXAxis();
//        xLabels.setPosition(XAxis.XAxisPosition.BOTTOM);
////        xLabels.setCenterXLabelText(true);
    }

    @Override
    public void onClick(View v) {
        String content = getFromAssets(assetsBankResourceFileName);

        List<BankInfo> bankInfoList = new ArrayList<BankInfo>();
        try {
            JSONObject object = new JSONObject(content);
            Iterator<?> iterator = object.keys();
            while(iterator.hasNext()) {
                String code = iterator.next().toString();
                if (code != null) {
                    String name = object.getString(code);
                    bankInfoList.add(new BankInfo(code, name, false));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        txt.setText(content);
    }

    public String getFromAssets(String fileName) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            InputStreamReader inputReader = new InputStreamReader(getResources().getAssets().open(fileName));
            BufferedReader bufReader = new BufferedReader(inputReader);
            String line = "";
            while ((line = bufReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            return stringBuilder.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        checkBoxObservable.deleteObservers();
    }
}
