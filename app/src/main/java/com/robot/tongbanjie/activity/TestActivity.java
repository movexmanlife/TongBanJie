package com.robot.tongbanjie.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.robot.tongbanjie.R;
import com.robot.tongbanjie.entity.BankInfo;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
//        txt = (TextView) findViewById(R.id.txt);
//        btn = (Button) findViewById(R.id.btn);
//        btn.setOnClickListener(this);
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
}
