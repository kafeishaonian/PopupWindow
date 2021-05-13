package com.popup.client.bubbleview;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;

/***
 *
 */
public class BaseFastClickListener implements OnClickListener, OnItemClickListener {
    private static long lastClickApp;
    public String TAG = "BaseFastClickListener";
    /***
     * 最近上一次点击时间记录
     */
    public long lastClick;
    /***
     * 是否拦截快速点击.默认为true
     */
    public boolean lanjieFastClick = true;
    /***
     * 拦截间隔时间.默认与BaseDC中的间隔时间保持一致
     */
    public long CLICK_INTERVAL_TIME = 500;
    /**
     * 是否app级别的拦截，在adapter中，每次刷新会new新的实例，第一次点击响应后，adpter会刷新，产生下一个本实例，使用此参数实现全局拦截
     */
    private boolean isAppLevel = false;

    public BaseFastClickListener() {

    }

    public BaseFastClickListener(boolean isAppLevel) {
        this.isAppLevel = isAppLevel;
    }

    @Override
    public void onClick(View v) {
        if (isFastClick()) {
            return;
        }
        onClicked(v);
    }

    public boolean isFastClick() {
        return lanjieFastClick && canNotClick();
    }

    public void onClicked(View v) {
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (canNotClick()) {
            return;
        }
        onItemClicked(parent, view, position, id);
    }

    public void onItemClicked(AdapterView<?> parent, View view, int position, long id) {

    }

    /**
     */
    public boolean notAnimition() {
        if (isAppLevel && Math.abs(System.currentTimeMillis() - lastClickApp) > CLICK_INTERVAL_TIME) {
            lastClickApp = System.currentTimeMillis();
            return true;
        }
        if (Math.abs(System.currentTimeMillis() - lastClick) > CLICK_INTERVAL_TIME) {
            lastClick = System.currentTimeMillis();
            lastClickApp = lastClick;
            return true;
        } else {
            return false;
        }
    }

    public boolean canNotClick() {
        return !notAnimition();
    }
}
