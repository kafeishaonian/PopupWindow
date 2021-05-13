package com.popup.client.bubbleview;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.popup.client.R;
import com.popup.client.util.PopupWindowUtils;

import java.util.Calendar;

public class LiveHint {

    public static final String LIVE_HINT_COIN = "live_coin";
    public static final String LIVE_HINT_SHARE = "live_share";
    public static final String LIVE_HINT_TRANSLATE = "live_translate";
    public static final String LIVE_HINT_TRANSLATE_TXT = "live_translate";
    public static final String LIVE_HINT_TRANSLATE_VOICE_DATE = "live_translate_voice_date";
    public static final String LIVE_HINT_TRANSLATE_TXT_DATE = "live_translate_voice_date";
    public static final int LIVE_COINS_MAX_TIMES = 2;
    public static final int LIVE_SHARE_MAX_TIMES = 3;
    public static final int LIVE_TRANSLATE_MAX_TIMES = 3;
    public static final int LIVE_TRANSLATE_TXT_MAX_TIMES = 3;

    public Activity mActivity;
    public Handler manager;
    public Calendar calendar;
    public boolean isShowing = true;
    SharedPreferences sharedPreferences;
    boolean hasEnd = false;
    PopupWindow popupWindow;
    PopupWindow popupWindowShare;

    public LiveHint(Handler manager, Activity activity) {
        this.manager = manager;
        this.mActivity = activity;
        new Thread(new Runnable() {
            @Override
            public void run() {
                sharedPreferences = LiveHint.this.mActivity.getSharedPreferences("liveHint", Context
                        .MODE_PRIVATE);
            }
        }).start();
        calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
    }

    public void setShowing(boolean showing) {
        isShowing = showing;
    }

    public void dismiss() {
        hasEnd = true;
        if (popupWindow != null) {
            delayDismiss(popupWindow);
        }
        if (popupWindowShare != null) {
            delayDismiss(popupWindowShare);
        }
    }

    public String getDate() {
        return "" + calendar.get(Calendar.YEAR) + "-" + calendar.get(Calendar.MONTH) + "-" + calendar.get(Calendar
                .DAY_OF_MONTH);
    }

    public void showTranslateVoiceHint(final View view) {
        manager.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mActivity.isFinishing()) {
                    return;
                }
                if (hasEnd) {
                    return;
                }
                View contentView = View.inflate(mActivity, R.layout.live_hint_translate_voice, null);
                popupWindow = PopupWindowUtils.buildPop(contentView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup
                        .LayoutParams.WRAP_CONTENT);
                int[] location = new int[2];
                view.getLocationOnScreen(location);
                if (contentView.getMeasuredWidth() == 0) {
                    contentView.measure(View.MeasureSpec.makeMeasureSpec(1000, View.MeasureSpec.AT_MOST), View
                            .MeasureSpec.makeMeasureSpec(1000, View.MeasureSpec.AT_MOST));
                }
                int contentWidth = contentView.getMeasuredWidth() == 0 ? mActivity.getResources()
                        .getDimensionPixelSize(R.dimen.live_share_pop_width) : contentView.getMeasuredWidth();
                int contentHeight = contentView.getMeasuredHeight() == 0 ? mActivity.getResources()
                        .getDimensionPixelSize(R.dimen.live_share_pop_height) : contentView.getMeasuredHeight();
                contentView.setOnClickListener(new BaseFastClickListener() {
                    @Override
                    public void onClicked(View v) {
                        super.onClicked(v);
                        popupWindow.dismiss();
                    }
                });
                popupWindow.setOutsideTouchable(false);
                if (!mActivity.isFinishing()) {
                    try {
                        popupWindow.showAtLocation(view, Gravity.NO_GRAVITY, location[0], location[1] - view
                                .getHeight());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
//                    popupWindow.showAtLocation(view, Gravity.TOP, location[0] - contentWidth + view.getWidth(),
// location[1]);//content.getContent()
                    delayDismiss(popupWindow);
                }
            }
        }, 3000);
    }

    public PopupWindow showCoinsHint(final View view) {
        int time = getRecordTime(LIVE_HINT_COIN);
        if (time >= LIVE_COINS_MAX_TIMES) {
            return null;
        }
        manager.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (mActivity.isFinishing()) {
                    return;
                }
                if (hasEnd || !isShowing) {
                    return;
                }
                View contentView = View.inflate(mActivity, R.layout.live_hint_coin, null);
                popupWindow = PopupWindowUtils.buildPop(contentView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup
                        .LayoutParams.WRAP_CONTENT);
                int[] location = new int[2];
                view.getLocationOnScreen(location);
                if (contentView.getMeasuredWidth() == 0) {
                    contentView.measure(View.MeasureSpec.makeMeasureSpec(1000, View.MeasureSpec.AT_MOST), View
                            .MeasureSpec.makeMeasureSpec(1000, View.MeasureSpec.AT_MOST));
                }
                int contentWidth = contentView.getMeasuredWidth() == 0 ? mActivity.getResources()
                        .getDimensionPixelSize(R.dimen.live_share_pop_width) : contentView.getMeasuredWidth();
                int contentHeight = contentView.getMeasuredHeight() == 0 ? mActivity.getResources()
                        .getDimensionPixelSize(R.dimen.live_share_pop_height) : contentView.getMeasuredHeight();
                contentView.setOnClickListener(new BaseFastClickListener() {
                    @Override
                    public void onClicked(View v) {
                        super.onClicked(v);
                        popupWindow.dismiss();
                    }
                });
                popupWindow.setOutsideTouchable(false);
                if (!mActivity.isFinishing()) {
                    try {
                        popupWindow.showAtLocation(view, Gravity.NO_GRAVITY, location[0] - contentWidth + view
                                .getWidth(), location[1] + view.getMeasuredHeight());
                        recordTime(LIVE_HINT_COIN);
                        delayDismiss(popupWindow);
                    } catch (Exception e) {
                        Log.d("", e.getMessage());
                    }
                }
            }
        }, 20000);
        return popupWindow;
    }

    public void delayDismiss(final PopupWindow popupWindow) {
        manager.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (!mActivity.isFinishing()) {
                    popupWindow.dismiss();
                }
            }
        }, 3000);
    }

    public void recordTime(String name) {
        if (sharedPreferences != null) {
            int hasHintTime = sharedPreferences.getInt(name, 0);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt(name, hasHintTime + 1);
            editor.commit();
        }
    }


    public void recordDate(String name) {
        if (sharedPreferences != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(name, getDate());
            editor.commit();
        }
    }

    public int getRecordTime(String name) {
        if (sharedPreferences != null) {
            return sharedPreferences.getInt(name, 0);
        }
        return 0;
    }

    public String getDate(String name) {
        if (sharedPreferences != null) {
            return sharedPreferences.getString(name, "");
        }
        return "";
    }

    public void showShareHint(final View view, final Activity context) {
        int time = getRecordTime(LIVE_HINT_SHARE);
        if (time >= LIVE_SHARE_MAX_TIMES) {
            return;
        }
        manager.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (context.isFinishing()) {
                    return;
                }
                if (hasEnd) {
                    return;
                }
                View contentView = View.inflate(context, R.layout.live_hint_share, null);
                popupWindowShare = PopupWindowUtils.buildPop(contentView, ViewGroup.LayoutParams.WRAP_CONTENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT);
                int[] location = new int[2];
                view.getLocationOnScreen(location);
                if (contentView.getMeasuredWidth() == 0) {
                    contentView.measure(View.MeasureSpec.makeMeasureSpec(1000, View.MeasureSpec.AT_MOST), View
                            .MeasureSpec.makeMeasureSpec(1000, View.MeasureSpec.AT_MOST));
                }
                int contentWidth = contentView.getMeasuredWidth() == 0 ? context.getResources()
                        .getDimensionPixelSize(R.dimen.live_share_pop_width) : contentView.getMeasuredWidth();
                int contentHeight = contentView.getMeasuredHeight() == 0 ? context.getResources()
                        .getDimensionPixelSize(R.dimen.live_share_pop_height) : contentView.getMeasuredHeight();
                contentView.setOnClickListener(new BaseFastClickListener() {
                    @Override
                    public void onClicked(View v) {
                        super.onClicked(v);
                        popupWindowShare.dismiss();
                    }
                });
                popupWindowShare.setOutsideTouchable(false);
//                if (!manager.getContext().isFinishing()) {
//                    try {
//                        popupWindowShare.showAtLocation(view, Gravity.NO_GRAVITY, location[0] - contentWidth + view
//                                .getWidth(), location[1] - contentHeight);
//                        recordTime(LIVE_HINT_SHARE);
//                        delayDismiss(popupWindowShare);
//                    } catch (Exception e) {
//                        e.printStackTrace();
//                    }
//                }
            }
        }, 10000);
    }

    /**
     * 显示一个简单的提示框
     *
     * @param anchor    指向的View
     * @param stringRes 显示的内容文字
     * @param direction 剪头方向，如在anchor的上方，则箭头的ArrowDirection.Down
     */
    public static BubblePopupWindow showSimpleHint(View anchor, int stringRes, BubbleStyle.ArrowDirection direction) {
        if (anchor != null) {
            View rootView = LayoutInflater.from(anchor.getContext()).inflate(R.layout.text_bubble, null);
            BubbleTextView bubbleView = (BubbleTextView) rootView.findViewById(R.id.popup_bubble);
            bubbleView.setText(stringRes);
            BubblePopupWindow window = new BubblePopupWindow(rootView, bubbleView);
            window.setCancelOnTouch(true);
            window.setCancelOnTouchOutside(true);
            window.showArrowTo(anchor, direction);
            return window;
        }
        return null;
    }

    /**
     * 显示一个简单的提示框 黑底白字
     *
     * @param anchor    指向的View
     * @param stringRes 显示的内容文字
     * @param direction 剪头方向，如在anchor的上方，则箭头的ArrowDirection.Down
     */
    public static BubblePopupWindow showSimpleHintBlackBg(View anchor, int stringRes, BubbleStyle.ArrowDirection direction, Context context) {
        if (anchor != null) {
            View rootView = LayoutInflater.from(anchor.getContext()).inflate(R.layout.text_bubble, null);
            BubbleTextView bubbleView = (BubbleTextView) rootView.findViewById(R.id.popup_bubble);
            bubbleView.setFillColor(context.getResources().getColor(R.color.c_333333));
            bubbleView.setTextColor(context.getResources().getColor(R.color.c_ffffff));
            bubbleView.setText(stringRes);
            BubblePopupWindow window = new BubblePopupWindow(rootView, bubbleView);
            window.setCancelOnTouch(true);
            window.setCancelOnTouchOutside(true);
            window.showArrowTo(anchor, direction);
            return window;
        }
        return null;
    }

    /**
     * 显示一个简单的提示框
     *
     * @param anchor    指向的View
     * @param stringRes 显示的内容文字
     * @param direction 剪头方向，如在anchor的上方，则箭头的ArrowDirection.Down
     */
    public static BubblePopupWindow showSimpleHintWithMargin(View anchor, int stringRes, BubbleStyle.ArrowDirection direction, int marginh, int marginV) {
        View rootView = LayoutInflater.from(anchor.getContext()).inflate(R.layout.text_bubble, null);
        BubbleTextView bubbleView = (BubbleTextView) rootView.findViewById(R.id.popup_bubble);
        bubbleView.setText(stringRes);
        BubblePopupWindow window = new BubblePopupWindow(rootView, bubbleView);
        window.setCancelOnTouch(true);
        window.setCancelOnTouchOutside(true);
        window.showArrowTo(anchor, direction, marginh, marginV);
        return window;
    }

    public static BubblePopupWindow showSimpleHint(View anchor, int stringRes, BubbleStyle.ArrowDirection direction, int color, int textColor) {
        View rootView = LayoutInflater.from(anchor.getContext()).inflate(R.layout.text_bubble, null);
        BubbleTextView bubbleView = (BubbleTextView) rootView.findViewById(R.id.popup_bubble);
        bubbleView.setText(stringRes);
        bubbleView.setBorderColor(color);
        bubbleView.setFillColor(color);
        bubbleView.setTextColor(textColor);
        BubblePopupWindow window = new BubblePopupWindow(rootView, bubbleView);
        window.setCancelOnTouch(true);
        window.setCancelOnTouchOutside(true);
        window.showArrowTo(anchor, direction);
        return window;
    }

    public static BubblePopupWindow showSimpleHintUpDownAnimation(Context context
            , View anchor, int stringRes
            , BubbleStyle.ArrowDirection direction) {
        View rootView = LayoutInflater.from(anchor.getContext()).inflate(R.layout.text_bubble_animation, null);
        TextView bubbleView = (TextView) rootView.findViewById(R.id.tvBubble);
        BubbleFrameLayout flBubble = (BubbleFrameLayout) rootView.findViewById(R.id.flBubble);
        flBubble.setBorderColor(context.getResources().getColor(R.color.transparent));
        flBubble.setFillColor(context.getResources().getColor(R.color.transparent));
        bubbleView.setText(stringRes);
        final SimpleAnimationPlayer animationPlayer = new SimpleAnimationPlayer();
        AnimatorSet set1 = new AnimatorSet();
        set1.setDuration(1000);
        ObjectAnimator obj1 = ObjectAnimator.ofFloat(bubbleView, "translationY", 0f, -40f);
        obj1.setRepeatMode(ValueAnimator.REVERSE);
        obj1.setRepeatCount(ValueAnimator.INFINITE);
        set1.play(obj1);
        animationPlayer.add(set1);
        animationPlayer.setAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                Log.d("chatGiftHolder", "stop animation");
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        BubblePopupWindow window = new BubblePopupWindow(rootView, flBubble);
        window.setCancelOnTouch(true);
        window.setCancelOnTouchOutside(true);
        window.showArrowTo(anchor, direction);
        window.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                animationPlayer.stop();
            }
        });
        animationPlayer.start();
        return window;
    }

    /**
     * 蓝底白字提示框,支持上下浮动动画
     *
     * @param context
     * @param anchor
     * @param stringRes
     * @param direction
     * @param marginH   显示对应anchor水平偏移量
     * @param marginV   显示对应anchor垂直偏移量
     * @param y         上线浮动偏移量
     * @return
     */
    public static BubblePopupWindow showSimpleHintUpDownAnimation(Context context
            , View anchor, int stringRes
            , BubbleStyle.ArrowDirection direction, int marginH, int marginV, int y) {
        View rootView = LayoutInflater.from(anchor.getContext()).inflate(R.layout.text_bubble_animation_common, null);
        TextView bubbleView = (TextView) rootView.findViewById(R.id.popup_bubble);
        BubbleFrameLayout flBubble = (BubbleFrameLayout) rootView.findViewById(R.id.flBubble);
        flBubble.setBorderColor(context.getResources().getColor(R.color.transparent));
        flBubble.setFillColor(context.getResources().getColor(R.color.transparent));
        bubbleView.setText(stringRes);
        bubbleView.setTextColor(context.getResources().getColor(R.color.c_ffffff));
        BubblePopupWindow window = new BubblePopupWindow(rootView, flBubble);
        window.setCancelOnTouch(true);
        window.setCancelOnTouchOutside(true);
        window.showArrowTo(anchor, direction, marginH, marginV);
        final SimpleAnimationPlayer animationPlayer = new SimpleAnimationPlayer();
        if (y != 0) {
            AnimatorSet set1 = new AnimatorSet();
            set1.setDuration(1000);
            ObjectAnimator obj1 = ObjectAnimator.ofFloat(bubbleView, "translationY", 0f, y);
            obj1.setRepeatMode(ValueAnimator.REVERSE);
            obj1.setRepeatCount(ValueAnimator.INFINITE);
            set1.play(obj1);
            animationPlayer.add(set1);
            animationPlayer.setAnimatorListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {

                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    Log.d("chatGiftHolder", "stop animation");
                }

                @Override
                public void onAnimationCancel(Animator animation) {
                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
        }
//        BubblePopupWindow window = new BubblePopupWindow(rootView,flBubble);
        window.setCancelOnTouch(true);
        window.setCancelOnTouchOutside(true);
        window.showArrowTo(anchor, direction);
        window.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                animationPlayer.stop();
            }
        });
        animationPlayer.start();
        return window;
    }
}
