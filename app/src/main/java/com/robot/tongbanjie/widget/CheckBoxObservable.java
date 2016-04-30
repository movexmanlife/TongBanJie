package com.robot.tongbanjie.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import java.util.Observable;
import java.util.Observer;

/**
 * 实现观察者模式的被观察者
 */
public class CheckBoxObservable extends CheckBox implements CompoundButton.OnCheckedChangeListener{
    private ObservableInner observable;
    public CheckBoxObservable(Context context) {
        super(context);
        init();
    }

    public CheckBoxObservable(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public CheckBoxObservable(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void hide() {
        setChanged();  // 状态改变，必须调用
        notifyObservers(true);
    }

    private void show() {
        setChanged();  // 状态改变，必须调用
        notifyObservers(false);
    }

    public void init() {
        observable = new ObservableInner();
        setOnCheckedChangeListener(this);
    }

    public void addObserver(Observer observer){
        observable.addObserver(observer);
    }

    public void deleteObserver(Observer observer) {
        observable.deleteObserver(observer);
    }

    public void deleteObservers() {
        observable.deleteObservers();
    }

    public int countObservers(){
        return observable.countObservers();
    }

    public void notifyObservers(){
        observable.notifyObservers();
    }

    public void notifyObservers(Object data){
        observable.notifyObservers(data);
    }

    public boolean hasChanged() {
        return observable.hasChanged();
    }

    protected void setChanged() {
        observable.setChanged();
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            hide();
        } else {
            show();
        }
    }

    private class ObservableInner extends Observable {
        public void setChanged() {
            super.setChanged();
        }
    }
}
