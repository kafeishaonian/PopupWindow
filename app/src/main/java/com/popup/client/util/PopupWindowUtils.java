package com.popup.client.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.widget.PopupWindow;

import com.popup.client.R;

/**
 */
public class PopupWindowUtils {

    public static PopupWindow buildPop(View contentView, int width, int height) {
        PopupWindow popupWindow = new PopupWindow(contentView, width, height, true);
        popupWindow.setTouchable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        return popupWindow;
    }
    
    public static PopupWindow buildPopWithAni(View contentView, int width, int height) {
        PopupWindow popupWindow = new PopupWindow(contentView, width, height, true);
        popupWindow.setTouchable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setAnimationStyle(R.style.popup_window_animation);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        return popupWindow;
    }

    /*****
     * show pop
     * 显示在view之上
     *
     * @param popupWindow
     * @param contentView
     * @param parent
     */
    public static void showPop(PopupWindow popupWindow, View contentView, View parent, Activity context) {
        int[] location = new int[2];
        parent.getLocationOnScreen(location);
        contentView.measure(View.MeasureSpec.makeMeasureSpec(2000, View.MeasureSpec.AT_MOST), View.MeasureSpec
                .makeMeasureSpec(2000, View.MeasureSpec.AT_MOST));
        int contentWidth = contentView.getMeasuredWidth() == 0 ? context.getResources().getDimensionPixelSize(R.dimen
                .live_share_pop_width) : contentView.getMeasuredWidth();
        int contentHeight = contentView.getMeasuredHeight() == 0 ? context.getResources().getDimensionPixelSize(R
                .dimen.live_share_pop_height) : contentView.getMeasuredHeight();
        if (context.isFinishing()) {
            return;
        }
        try {
            popupWindow.showAtLocation(parent, Gravity.NO_GRAVITY, location[0] - (contentWidth - parent.getWidth()) /
                    2, location[1] - contentHeight - 4);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 显示在某个view正上方,可偏移
     *
     * @param contentView popwindow对应的view 用于测量宽高
     * @param parent popWindow显示在该view之上
     * @param x x方向偏移量
     * @param y y方向偏移量
     */
    public static void showPop(PopupWindow popupWindow, View contentView, View parent, Activity context, int x, int y) {
        int[] location = new int[2];
        parent.getLocationOnScreen(location);
        contentView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        int contentWidth = contentView.getMeasuredWidth();
        int contentHeight = contentView.getMeasuredHeight();
        if (context.isFinishing()) {
            return;
        }
        try {
            popupWindow.showAtLocation(parent, Gravity.NO_GRAVITY, location[0] - contentWidth / 2 + parent.getWidth()
                    / 2 + x, location[1] - contentHeight / 2 - parent.getHeight() + y);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*****
     * show pop
     * 显示在view之上
     *
     * @param popupWindow
     * @param contentView
     * @param parent
     */
    public static void showPopBelow(PopupWindow popupWindow, View contentView, View parent, Context context) {
        int[] location = new int[2];
        parent.getLocationOnScreen(location);
        contentView.measure(View.MeasureSpec.makeMeasureSpec(2000, View.MeasureSpec.AT_MOST), View.MeasureSpec
                .makeMeasureSpec(2000, View.MeasureSpec.AT_MOST));
        int contentWidth = contentView.getMeasuredWidth() == 0 ? context.getResources().getDimensionPixelSize(R.dimen
                .live_share_pop_width) : contentView.getMeasuredWidth();
        int contentHeight = contentView.getMeasuredHeight() == 0 ? context.getResources().getDimensionPixelSize(R
                .dimen.live_share_pop_height) : contentView.getMeasuredHeight();
        popupWindow.showAtLocation(parent, Gravity.NO_GRAVITY, location[0] - (contentWidth - parent.getWidth()) / 2,
                location[1] + contentHeight);
    }
}
