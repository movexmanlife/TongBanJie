package com.robot.tongbanjie.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.robot.tongbanjie.R;
import com.robot.tongbanjie.dialog.CommonDialog;
import com.robot.tongbanjie.dialog.LoadingDialog;
import com.robot.tongbanjie.util.IntentUtils;
import com.robot.tongbanjie.util.ToastUtils;
import com.robot.tongbanjie.widget.ClearEditText;
import com.robot.tongbanjie.widget.CounterDownTextView;
import com.robot.tongbanjie.widget.TitleBarView;

import butterknife.Bind;
import butterknife.ButterKnife;


public class FindPasswdActivity extends BaseActivity implements OnClickListener {
    private static final String TAG = FindPasswdActivity.class.getSimpleName();

    @Bind(R.id.titlebar)
    TitleBarView mTitleBar;
    @Bind(R.id.find_pwd_title)
    TextView findPwdTitle;
    @Bind(R.id.find_pwd_verify)
    ClearEditText findPwdVerify;
    @Bind(R.id.find_pwd_tips)
    TextView findPwdTips;
    @Bind(R.id.find_pwd_next)
    Button findPwdNext;
    @Bind(R.id.find_pwd_safe_tips)
    TextView findPwdSafeTips;
    @Bind(R.id.find_pwd_custom_service)
    TextView findPwdCustomService;
    @Bind(R.id.find_pwd_counter)
    CounterDownTextView findPwdCounter;

    private LoadingDialog mLoadingDialog;

    public static void start(Context context) {
        Intent intent = new Intent(context, FindPasswdActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_find_passwd);
        ButterKnife.bind(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initData() {
        mLoadingDialog = new LoadingDialog(this, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, R.style.LoadingDialogLight);
    }

    @Override
    public void initTitle() {
        mTitleBar.initTitleWithLeftTxtDrawable("找回交易密码", "返回", R.drawable.btn_back_sel, 5);
        mTitleBar.setOnLeftTxtClickListener(new TitleBarView.OnLeftTxtClickListener() {
            @Override
            public void onClick() {
                finish();
            }
        });
    }

    @Override
    public void initView() {
        findPwdCounter.setFinishText("重发");
        findPwdCounter.setTickTextPrefix("重发(");
        findPwdCounter.setTickTextPostfix("秒)");
        findPwdCounter.setTotalTime(60 * 1000);
        findPwdCounter.performClick();
    }

    @Override
    public void setListener() {
        findPwdVerify.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                showTips(s.toString());
            }
        });
        findPwdNext.setOnClickListener(this);
        findPwdCustomService.setOnClickListener(this);
    }

    private void showTips(String content) {
        if (TextUtils.isEmpty(content) || content.length() < 6) {
            findPwdNext.setEnabled(false);
        } else {
            findPwdNext.setEnabled(true);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.find_pwd_next:
                mLoadingDialog.show();
                findPwdNext.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        gotoFindPwd();
                    }
                }, 3000);
                break;
            case R.id.find_pwd_custom_service:
                new CommonDialog(this).builder()
                        .setTitle("提示")
                        .setContentMsg("拨打铜板街客服电话")
                        .setNegativeBtn("取消", null)
                        .setPositiveBtn("确定", new CommonDialog.OnPositiveListener() {
                            @Override
                            public void onPositive(View view) {
                                IntentUtils.gotoCall(FindPasswdActivity.this, "400-869-8686");
                            }
                        })
                        .show();

                break;
            default:
                break;
        }
    }

    private void gotoFindPwd() {
        mLoadingDialog.dismiss();
        int len = findPwdVerify.getText().length();
        if (len == getResources().getInteger(R.integer.phone_number_max_cnt)) {
        } else {
            ToastUtils.showShort("请输入正确的手机号码！");
        }
    }
}
