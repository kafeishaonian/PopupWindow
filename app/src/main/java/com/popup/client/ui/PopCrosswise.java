package com.popup.client.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * popupWindow计算横宽比
 * 在recyclerView横向的时候使用
 * Created by 64860 on 2017/9/18.
 */
public class PopCrosswise extends LinearLayout{
    public PopCrosswise(Context context) {
        super(context);
    }

    public PopCrosswise(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(getDefaultSize(0, widthMeasureSpec), getDefaultSize(0, heightMeasureSpec));
        int childWidthSize = getMeasuredWidth();
        int childHeightSize = childWidthSize;
        widthMeasureSpec = MeasureSpec.makeMeasureSpec((int) (childWidthSize * 1.25), MeasureSpec.UNSPECIFIED);
        heightMeasureSpec = MeasureSpec.makeMeasureSpec(childHeightSize >> 1, MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

}