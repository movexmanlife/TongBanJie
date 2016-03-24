package com.robot.tongbanjie.entity;

/**
 * 银行信息
 */
public class BankInfo {
    public String code;
    public String name;
    public boolean isSelected = false;

    public BankInfo(String code, String name, boolean isSelected) {
        this.code = code;
        this.name = name;
        this.isSelected = isSelected;
    }
}
