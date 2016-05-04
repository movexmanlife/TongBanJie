package com.robot.tongbanjie.activity;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.robot.tongbanjie.R;
import com.robot.tongbanjie.dialog.PwdDialogFragment;
import com.robot.tongbanjie.dialog.ShareDialogFragment;
import com.robot.tongbanjie.util.SoftKeyboardUtils;
import com.robot.tongbanjie.widget.InputPasswordView;
import com.robot.tongbanjie.widget.SafeKeyboardView;
import com.robot.tongbanjie.widget.TitleBarView;

import butterknife.Bind;
import butterknife.ButterKnife;


public class TradePwdModifyActivity extends BaseActivity implements OnClickListener {
    private static final String TAG = TradePwdModifyActivity.class.getSimpleName();
    @Bind(R.id.titlebar)
    TitleBarView mTitleBar;
    @Bind(R.id.input_pwd_view)
    InputPasswordView inputPasswordView;
    PwdDialogFragment pwdDialogFragment;
    @Bind(R.id.edittext)
    EditText edittext;

    public static void start(Context context) {
        Intent intent = new Intent(context, TradePwdModifyActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_trade_pwd_modify);
        ButterKnife.bind(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initData() {

    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void initTitle() {
        mTitleBar.initTitleWithLeftTxtDrawable("修改交易密码", "取消", R.drawable.btn_back_sel, 5);
        mTitleBar.setOnLeftTxtClickListener(new TitleBarView.OnLeftTxtClickListener() {
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
        edittext.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pwdDialogFragment != null) {
                    if (pwdDialogFragment != null && pwdDialogFragment.getDialog() != null
                            && pwdDialogFragment.getDialog().isShowing()) {
                        pwdDialogFragment.dismiss();

                        v.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                if (!isFinishing()) {
                                    SoftKeyboardUtils.showKeyboard(TradePwdModifyActivity.this);
                                }
                            }
                        }, 50);
                    }
                }
            }
        });
        inputPasswordView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                SoftKeyboardUtils.hideKeyboard(TradePwdModifyActivity.this);

                if (pwdDialogFragment == null) {
                    pwdDialogFragment = PwdDialogFragment.newInstance(TradePwdModifyActivity.this, null, 0);
                    pwdDialogFragment.setOnAddListener(inputPasswordView);
                    pwdDialogFragment.setOnConfirmListener(inputPasswordView);
                    pwdDialogFragment.setOnDeleteListener(inputPasswordView);
                }

                if (pwdDialogFragment != null && pwdDialogFragment.getDialog() != null
                        && pwdDialogFragment.getDialog().isShowing()) {
                } else {
                    pwdDialogFragment.show(getSupportFragmentManager(), null);
                }
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            default:
                break;
        }
    }
}
