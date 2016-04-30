package com.robot.tongbanjie.dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.robot.tongbanjie.R;
import com.robot.tongbanjie.adapter.ShareAdapter;
import com.robot.tongbanjie.util.ToastUtils;

import java.util.LinkedHashMap;

public class DeviceDialogFragment extends BaseDialogFragment {

    public static DeviceDialogFragment newInstance(Context context, String title, int height) {
        DeviceDialogFragment dialogFragment = new DeviceDialogFragment();
        dialogFragment.mContext = context;
        dialogFragment.setArguments(initArgs(title, height));

        return dialogFragment;
    }

    @Override
    public int setStyleId() {
        return R.style.CustomDialog;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_device_manager, container,
                false);
        initDialogTitle(view);
        LinearLayout delDevice = (LinearLayout) view.findViewById(R.id.del_device);
        TextView notMeLogin = (TextView) view.findViewById(R.id.not_me_login);
        TextView closeDialog = (TextView) view.findViewById(R.id.close);

        delDevice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        closeDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        notMeLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        return view;
    }
}
