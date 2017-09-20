package com.popup.client.ui;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * popupwindow横宽占比
 * 在RecyclerView为竖直方向的时候使用
 * Created by Hongmingwei on 2017/9/18.
 * Email: 648600445@qq.com
 */

public class PopCellLayout extends LinearLayout {
    public PopCellLayout(Context context) {
        super(context);
    }

    public PopCellLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(getDefaultSize(0, widthMeasureSpec), getDefaultSize(0, heightMeasureSpec));
        int childWidthSize = getMeasuredWidth();
        int childHeightSize = childWidthSize;
        widthMeasureSpec = MeasureSpec.makeMeasureSpec(childWidthSize >> 1, MeasureSpec.EXACTLY);
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(childHeightSize << 1, MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}