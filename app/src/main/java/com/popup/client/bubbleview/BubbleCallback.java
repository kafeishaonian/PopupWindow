package com.popup.client.bubbleview;

/**
 * 给BubbleImpl使用的回调接口，方便在BubbleImpl类中调用真正View的父类接口
 *
 */
interface BubbleCallback {
    void setSuperPadding(int left, int top, int right, int bottom);

    int getSuperPaddingLeft();

    int getSuperPaddingTop();

    int getSuperPaddingRight();

    int getSuperPaddingBottom();
}
