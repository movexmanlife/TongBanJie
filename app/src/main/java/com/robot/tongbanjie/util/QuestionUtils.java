package com.robot.tongbanjie.util;

import com.robot.tongbanjie.R;
import com.robot.tongbanjie.TbjApp;
import com.robot.tongbanjie.entity.Question;

import java.util.ArrayList;
import java.util.List;

public class QuestionUtils {
    public static int[] questionArray = new int[]{R.string.question1,
            R.string.question2, R.string.question3, R.string.question4, R.string.question5};

    public static int[] answerArray = new int[]{R.array.question1,
            R.array.question2, R.array.question3, R.array.question4, R.array.question5};

    public static final List<Question> questionList = new ArrayList<Question>(questionArray.length);
    static {
        for (int i = 0; i < questionArray.length; i++) {
            questionList.add(new Question(getString(questionArray[i]), getStrArray(answerArray[i])));
        }
    }

    private QuestionUtils() {
    }

    public static void reinitializeDatas() {
        for (int i = 0; i < questionList.size(); i++) {
            questionList.get(i).setIsSelected(false);
            questionList.get(i).setIndex(-1);
        }
    }

    private static String getString(int resId) {
        return TbjApp.getInstance().getResources().getString(resId);
    }

    private static String[] getStrArray(int arrayResId) {
        return TbjApp.getInstance().getResources().getStringArray(arrayResId);
    }
}
