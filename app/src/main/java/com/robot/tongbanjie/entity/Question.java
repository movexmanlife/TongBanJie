package com.robot.tongbanjie.entity;

public class Question {
    private String title;
    private String[] answerArray;

    private boolean isSelected = false;
    private int index = -1;

    public Question(String title, String[] answerArray) {
        this.title = title;
        this.answerArray = answerArray;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String[] getAnswerArray() {
        return answerArray;
    }

    public void setAnswerArray(String[] answerArray) {
        this.answerArray = answerArray;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setIsSelected(boolean isSelected) {
        this.isSelected = isSelected;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
