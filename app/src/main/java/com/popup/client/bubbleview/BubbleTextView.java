package com.popup.client.bubbleview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

/**
 * 气泡样式的TextView控件
 * 支持自定义气泡样式
 *
 * Created by caijw on 2016/6/1.
 * https://github.com/cpiz/BubbleView
 */
public class BubbleTextView extends TextView implements BubbleStyle, BubbleCallback {
    private BubbleImpl mBubbleImpl = new BubbleImpl();

    public BubbleTextView(Context context) {
        super(context);
        init(context, null);
    }

    public BubbleTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public BubbleTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        mBubbleImpl.init(this, context, attrs);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        mBubbleImpl.updateDrawable(right - left, bottom - top, true);
    }

    @Override
    public void setArrowDirection(BubbleStyle.ArrowDirection arrowDirection) {
        mBubbleImpl.setArrowDirection(arrowDirection);
    }

    @Override
    public BubbleStyle.ArrowDirection getArrowDirection() {
        return mBubbleImpl.getArrowDirection();
    }

    @Override
    public void setArrowHeight(float arrowHeight) {
        mBubbleImpl.setArrowHeight(arrowHeight);
    }

    @Override
    public float getArrowHeight() {
        return mBubbleImpl.getArrowHeight();
    }

    @Override
    public void setArrowWidth(float arrowWidth) {
        mBubbleImpl.setArrowWidth(arrowWidth);
    }

    @Override
    public float getArrowWidth() {
        return mBubbleImpl.getArrowWidth();
    }

    @Override
    public void setArrowPosPolicy(ArrowPosPolicy policy) {
        mBubbleImpl.setArrowPosPolicy(policy);
    }

    @Override
    public ArrowPosPolicy getArrowPosPolicy() {
        return mBubbleImpl.getArrowPosPolicy();
    }

    @Override
    public void setArrowPosDelta(float delta) {
        mBubbleImpl.setArrowPosDelta(delta);
    }

    @Override
    public float getArrowPosDelta() {
        return mBubbleImpl.getArrowPosDelta();
    }

    @Override
    public void setArrowTo(int viewId) {
        mBubbleImpl.setArrowTo(viewId);
    }

    @Override
    public void setArrowTo(View view) {
        mBubbleImpl.setArrowTo(view);
    }

    public View getArrowTo() {
        return mBubbleImpl.getArrowTo();
    }

    @Override
    public void setFillColor(int fillColor) {
        mBubbleImpl.setFillColor(fillColor);
    }

    public void setFillColor(int[] fillColor) {
        mBubbleImpl.setFillColor(fillColor);
    }

    @Override
    public int getFillColor() {
        return mBubbleImpl.getFillColor();
    }

    @Override
    public void setBorderColor(int borderColor) {
        mBubbleImpl.setBorderColor(borderColor);
    }

    @Override
    public int getBorderColor() {
        return mBubbleImpl.getBorderColor();
    }

    @Override
    public void setBorderWidth(float borderWidth) {
        mBubbleImpl.setBorderWidth(borderWidth);
    }

    @Override
    public float getBorderWidth() {
        return mBubbleImpl.getBorderWidth();
    }

    @Override
    public void setFillPadding(float fillPadding) {
        mBubbleImpl.setFillPadding(fillPadding);
    }

    @Override
    public float getFillPadding() {
        return mBubbleImpl.getFillPadding();
    }

    @Override
    public void setCornerRadius(float topLeft, float topRight, float bottomRight, float bottomLeft) {
        mBubbleImpl.setCornerRadius(topLeft, topRight, bottomRight, bottomLeft);
    }

    @Override
    public void setCornerRadius(float radius) {
        mBubbleImpl.setCornerRadius(radius);
    }

    @Override
    public float getCornerTopLeftRadius() {
        return mBubbleImpl.getCornerTopLeftRadius();
    }

    @Override
    public float getCornerTopRightRadius() {
        return mBubbleImpl.getCornerTopRightRadius();
    }

    @Override
    public float getCornerBottomLeftRadius() {
        return mBubbleImpl.getCornerBottomLeftRadius();
    }

    @Override
    public float getCornerBottomRightRadius() {
        return mBubbleImpl.getCornerBottomRightRadius();
    }

    @Override
    public void setPadding(int left, int top, int right, int bottom) {
        if (mBubbleImpl == null) {
            Log.w("BubbleView", "mBubbleImpl == null on old Android platform");
            setSuperPadding(left, top, right, bottom);
            return;
        }

        mBubbleImpl.setPadding(left, top, right, bottom);
    }

    @Override
    public int getPaddingLeft() {
        return mBubbleImpl.getPaddingLeft();
    }

    @Override
    public int getPaddingTop() {
        return mBubbleImpl.getPaddingTop();
    }

    @Override
    public int getPaddingRight() {
        return mBubbleImpl.getPaddingRight();
    }

    @Override
    public int getPaddingBottom() {
        return mBubbleImpl.getPaddingBottom();
    }

    @Override
    public void setSuperPadding(int left, int top, int right, int bottom) {
        super.setPadding(left, top, right, bottom);
    }

    @Override
    public int getSuperPaddingLeft() {
        return super.getPaddingLeft();
    }

    @Override
    public int getSuperPaddingTop() {
        return super.getPaddingTop();
    }

    @Override
    public int getSuperPaddingRight() {
        return super.getPaddingRight();
    }

    @Override
    public int getSuperPaddingBottom() {
        return super.getPaddingBottom();
    }

    @Override
    public void requestUpdateBubble() {
        mBubbleImpl.requestUpdateBubble();
    }
}
