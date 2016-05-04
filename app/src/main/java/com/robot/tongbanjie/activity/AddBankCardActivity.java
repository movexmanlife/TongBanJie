package com.robot.tongbanjie.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.robot.tongbanjie.R;
import com.robot.tongbanjie.util.TextViewUtils;
import com.robot.tongbanjie.widget.TitleBarView;

import butterknife.Bind;
import butterknife.ButterKnife;


public class AddBankCardActivity extends BaseActivity implements OnClickListener {
    private static final String TAG = AddBankCardActivity.class.getSimpleName();

    @Bind(R.id.titlebar)
    TitleBarView mTitlebar;
    @Bind(R.id.card_no)
    EditText cardNo;
    @Bind(R.id.name)
    EditText name;
    @Bind(R.id.idcard)
    EditText idcard;
    @Bind(R.id.agree_protocal)
    CheckBox agreeProtocal;
    @Bind(R.id.next_btn)
    Button nextBtn;

    public static void start(Context context) {
        Intent intent = new Intent(context, AddBankCardActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_add_bank_card);
        ButterKnife.bind(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initTitle() {
        mTitlebar.initTitleWithLeftTxtRightTxt("添加银行卡", "返回", "支持银行");
        TextViewUtils.setLeftImage(mTitlebar.getLeftTxtView(), R.drawable.btn_back_sel, 5);
        mTitlebar.setOnLeftTxtClickListener(new TitleBarView.OnLeftTxtClickListener() {
            @Override
            public void onClick() {
                finish();
            }
        });
    }

    @Override
    public void initView() {
    }

    @Override
    public void setListener() {
        agreeProtocal.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                nextBtn.setEnabled(agreeProtocal.isChecked());
            }
        });

        nextBtn.setEnabled(agreeProtocal.isChecked());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.next_btn:
                break;
            default:
                break;
        }
    }

}
