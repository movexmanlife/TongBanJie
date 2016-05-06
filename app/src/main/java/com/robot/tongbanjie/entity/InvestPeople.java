package com.robot.tongbanjie.entity;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

/**
 * 投资人列表
 */
@Table(name="investPeople")
public class InvestPeople extends Model {
    @Column(name="date")
    public String date;
    @Column(name="sum")
    public Float sum;
    @Column(name="phone")
    public String phone;

    public int viewType;

    public InvestPeople() {
    }

    /**
     * @param date 日期
     * @param sum 金额
     * @param phone 手机号码
     */
    public InvestPeople(String date, Float sum, String phone) {
        super();
        this.date = date;
        this.sum = sum;
        this.phone = phone;
    }
}
