package com.robot.tongbanjie.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.robot.tongbanjie.R;

/**
 *
 */
public class AccountQuestionAdapter extends BaseExpandableListAdapter {
    private Context mContext;

    public AccountQuestionAdapter(Context context) {
        this.mContext = context;
    }

    // 设置组视图的显示文字
    private String[] summaryQuestion = new String[]{"注册流程是？",
            "手机端用户可以在网页端登录进行购买？",
            "忘记登录密码之后如何找回？",
            "忘记交易密码之后如何找回？",
            "如何设置手势密码，忘记手势密码怎么办？",
            "如何更换手机号？"
    };
    // 子视图显示文字
    private String[][] detailDesc = new String[][]{
            {"用户输入自己的手机号码，获取验证码验证通过后，设置登录密码并提交，即注册成功。"},
            {"手机端注册的用户也可以登录网页端进行购买或其他操作。"},
            {"在登录页面输入手机号码后，需要输入密码的文本边框上有“忘记密码”，需要输入您的身份证号码，输入验证码就可以重新设置登录密码。"},
            {"用户登录APP端进入“更多”页面的“设置”，点击“安全中心”中的“找回交易密码”，根据操作提示验证您的身份信息进行找回。"},
            {"用户登录APP端进入“更多”页面的“设置”，点击“安全中心”，选择开启收手势密码即可，根据操作提示可修改手势密码。忘记"+
            "时在启动页面，点击“忘记手势密码”，用户登出账户后，重新进行绘制。"},
            {"请使用原注册手机号码，致电客服咨询。"}

    };

    // 重写ExpandableListAdapter中的各个方法
    @Override
    public int getGroupCount() {
        return summaryQuestion.length;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return summaryQuestion[groupPosition];
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return detailDesc[groupPosition].length;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return detailDesc[groupPosition][childPosition];
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        GroupViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item_question_account, parent, false);
            viewHolder = new GroupViewHolder();
            viewHolder.textView = (TextView) convertView.findViewById(R.id.txt);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.img);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (GroupViewHolder)convertView.getTag();
        }

        viewHolder.textView.setText(getGroup(groupPosition).toString());
        if (isExpanded){
            viewHolder.imageView.setImageResource(R.drawable.arrow_down);
        } else {
            viewHolder.imageView.setImageResource(R.drawable.arrow_left);
        }
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        ChildViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item_question_account_down, parent, false);
            viewHolder = new ChildViewHolder();
            viewHolder.textView = (TextView) convertView.findViewById(R.id.txt);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ChildViewHolder)convertView.getTag();
        }

        viewHolder.textView.setText(getChild(groupPosition, childPosition).toString());

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition,
                                     int childPosition) {
        return true;
    }

    public static class GroupViewHolder {
        public TextView textView;
        public ImageView imageView;
    }

    public static class ChildViewHolder {
        public TextView textView;
    }
}
