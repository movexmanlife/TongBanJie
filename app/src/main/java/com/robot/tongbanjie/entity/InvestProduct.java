package com.robot.tongbanjie.entity;

import com.robot.tongbanjie.adapter.InvestProductAdapter;

public class InvestProduct {
    public int resId;
    public String title;
    public String desc;
    public String reservation;
    public boolean isReservation;
    public boolean hasTimeLimit;
    public int timeLimit;
    public int viewType = InvestProductAdapter.ITEM_TYPE_COMMON;
}
