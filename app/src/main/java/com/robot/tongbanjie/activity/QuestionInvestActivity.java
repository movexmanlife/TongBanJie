package com.robot.tongbanjie.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.SparseIntArray;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.robot.tongbanjie.R;
import com.robot.tongbanjie.dialog.ShareDialogFragment;
import com.robot.tongbanjie.entity.Question;
import com.robot.tongbanjie.util.QuestionUtils;
import com.robot.tongbanjie.util.TextViewUtils;
import com.robot.tongbanjie.widget.TitleBarView;

import butterknife.Bind;
import butterknife.ButterKnife;


public class QuestionInvestActivity extends BaseActivity implements OnClickListener {
    private static final String TAG = QuestionInvestActivity.class.getSimpleName();
    @Bind(R.id.titlebar)
    TitleBarView mTitleBar;
    @Bind(R.id.question_index)
    TextView questionIndex;
    @Bind(R.id.question)
    TextView questionTitle;
    @Bind(R.id.radiobtn1)
    RadioButton radiobtn1;
    @Bind(R.id.radiobtn2)
    RadioButton radiobtn2;
    @Bind(R.id.radiobtn3)
    RadioButton radiobtn3;
    @Bind(R.id.radioGroup)
    RadioGroup radioGroup;
    @Bind(R.id.pre_btn)
    Button preBtn;
    @Bind(R.id.next_btn)
    Button nextBtn;

    private SparseIntArray radioArray = new SparseIntArray(3);
    private int mIndexCurrent = 0;


    public static void start(Context context) {
        Intent intent = new Intent(context, QuestionInvestActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_evaluate_start);
        ButterKnife.bind(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void initData() {
        radioArray.append(0, R.id.radiobtn1);
        radioArray.append(1, R.id.radiobtn2);
        radioArray.append(2, R.id.radiobtn3);
    }

    @Override
    public void initTitle() {
        mTitleBar.initTitleWithLeftTxtRightImg("评估结果", "返回", R.drawable.icon_share);
        TextViewUtils.setLeftImage(mTitleBar.getLeftTxtView(), R.drawable.btn_back_sel, 5);
        mTitleBar.setOnLeftTxtClickListener(new TitleBarView.OnLeftTxtClickListener() {
            @Override
            public void onClick() {
                QuestionUtils.reinitializeDatas();
                finish();
            }
        });
        mTitleBar.setOnRightImgClickListener(new TitleBarView.OnRightImgClickListener() {
            @Override
            public void onClick() {
                ShareDialogFragment shareDialogFragment = ShareDialogFragment.newInstance(QuestionInvestActivity.this, null, 0);
                shareDialogFragment.show(getSupportFragmentManager(), null);
            }
        });
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            QuestionUtils.reinitializeDatas();
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void initView() {
        preQuestion();
    }

    @Override
    public void setListener() {
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                Question question = QuestionUtils.questionList.get(mIndexCurrent);
                if (checkedId == R.id.radiobtn1) {
                    question.setIsSelected(true);
                    question.setIndex(0);
                } else if (checkedId == R.id.radiobtn2) {
                    question.setIsSelected(true);
                    question.setIndex(1);
                } else if (checkedId == R.id.radiobtn3) {
                    question.setIsSelected(true);
                    question.setIndex(2);
                }
            }
        });
        preBtn.setOnClickListener(this);
        nextBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.pre_btn:
                preQuestion();
                break;
            case R.id.next_btn:
                nextQuestion();
                break;
            default:
                break;
        }
    }

    private void preQuestion() {
        nextBtn.setText("下一题");

        if (mIndexCurrent == 0) {
            mIndexCurrent = 0;
            preBtn.setVisibility(View.GONE);
        } else {
            preBtn.setVisibility(View.VISIBLE);
            mIndexCurrent--;
        }

        if (mIndexCurrent == 0) {
            preBtn.setVisibility(View.GONE);
        }

        Question question = QuestionUtils.questionList.get(mIndexCurrent);
        refreshQuestionView(mIndexCurrent, question);
    }

    private void nextQuestion() {
        preBtn.setVisibility(View.VISIBLE);

        if (mIndexCurrent >= QuestionUtils.questionList.size() - 1) {  // 到最后一题了。
            mIndexCurrent = QuestionUtils.questionList.size() - 1;
            nextBtn.setText("提交");
        } else {
            nextBtn.setText("下一题");
            mIndexCurrent++;
        }

        Question question = QuestionUtils.questionList.get(mIndexCurrent);
        refreshQuestionView(mIndexCurrent, question);
    }

    private void refreshQuestionView(int index, Question question) {
        questionIndex.setText((index + 1) + " / " + QuestionUtils.questionList.size());
        questionTitle.setText(question.getTitle());
        radiobtn1.setText(question.getAnswerArray()[0]);
        radiobtn2.setText(question.getAnswerArray()[1]);
        radiobtn3.setText(question.getAnswerArray()[2]);

        if (question.isSelected()) {
            int checkId = radioArray.get(question.getIndex());
            radioGroup.check(checkId);  // 选中
        } else {
            radioGroup.clearCheck();  // 清除选中
        }
    }
}
