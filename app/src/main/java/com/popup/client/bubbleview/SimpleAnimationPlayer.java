package com.popup.client.bubbleview;

import android.animation.Animator;
import android.animation.AnimatorSet;

import com.popup.client.util.DisplayUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by duanxinmeng on 17/8/17.
 */

public class SimpleAnimationPlayer implements Animator.AnimatorListener {
    private String TAG = "";
    private Animator.AnimatorListener animatorListener;
    private List<AnimatorSet> sets;
    private int repeatModel = 0;
    public static int REPEAT_FOREVET = 1;
    private int currentPosition = -1;
    AnimatorSet set;
    private boolean isPlaying;

    public SimpleAnimationPlayer() {
        sets = new ArrayList<>();
    }

    public void add(AnimatorSet... sets) {
        for (AnimatorSet set :
                sets) {
            this.sets.add(set);
        }
    }

    public void setAnimatorListener(Animator.AnimatorListener animatorListener) {
        this.animatorListener = animatorListener;
    }

    public void start() {
        isPlaying = true;
        playAnimaition();
    }

    public void start(int repeatModel) {
        this.repeatModel = repeatModel;
        isPlaying = true;
        playAnimaition();
    }

    private void playAnimaition() {
//        PPLog.d(TAG, "playAnimaition  " + isPlaying);
        if (!isPlaying) {
            return;
        }
        if (repeatModel == 0) {
            if (DisplayUtils.isNotCollectionEmpty(sets)) {
                set = sets.remove(0);
                set.addListener(this);
                set.start();
            } else {
                if (animatorListener != null) {
                    animatorListener.onAnimationEnd(null);
                }
            }
        } else {
            currentPosition++;
            if (currentPosition >= sets.size()) {
                currentPosition = 0;
            }
//            PPLog.d("animationPlayer", "currentPosition " + currentPosition);
            //无限重复播放
            if (DisplayUtils.isNotCollectionEmpty(sets)) {
                set = sets.get(currentPosition).clone();
                set.addListener(this);
                set.start();
            } else {
                if (animatorListener != null) {
                    animatorListener.onAnimationEnd(null);
                }
            }
        }
    }

    public void stop() {
        isPlaying = false;
        if (set != null) {
            endAnimator(set);
        }
        if (DisplayUtils.isNotCollectionEmpty(sets)) {
            for (AnimatorSet currentSet : sets) {
                endAnimator(currentSet);
            }
            sets.clear();
        }
        animatorListener = null;
    }

    public void endAnimator(AnimatorSet set) {
        try {
            if (set != null) {
                set.cancel();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * <p>Notifies the start of the animation.</p>
     *
     * @param animation The started animation.
     */
    @Override
    public void onAnimationStart(Animator animation) {

    }

    /**
     * <p>Notifies the end of the animation. This callback is not invoked
     * for animations with repeat count set to INFINITE.</p>
     *
     * @param animation The animation which reached its end.
     */
    @Override
    public void onAnimationEnd(Animator animation) {

        if (isPlaying) {
            playAnimaition();
        }
    }

    /**
     * <p>Notifies the cancellation of the animation. This callback is not invoked
     * for animations with repeat count set to INFINITE.</p>
     *
     * @param animation The animation which was canceled.
     */
    @Override
    public void onAnimationCancel(Animator animation) {
        if (animatorListener != null) {
            animatorListener.onAnimationCancel(animation);
        }
    }

    /**
     * <p>Notifies the repetition of the animation.</p>
     *
     * @param animation The animation which was repeated.
     */
    @Override
    public void onAnimationRepeat(Animator animation) {

    }
}
