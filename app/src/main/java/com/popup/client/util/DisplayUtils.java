package com.popup.client.util;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources;
import android.graphics.Point;
import android.os.Build;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

import java.util.List;

/**
 * Created by Hongmingwei on 2017/9/19.
 * Email: 648600445@qq.com
 */
public class DisplayUtils {

    /**
     * dip转换为px
     * @param context
     * @param dip
     * @return
     */
    public static int dipToPx(Context context, float dip) {
        return (int) (dip * getDisplayMetrics(context).density + 0.5f);
    }

    private static DisplayMetrics getDisplayMetrics(Context context) {
        Display display = getDisplay(context);
        DisplayMetrics dm = new DisplayMetrics();
        display.getMetrics(dm);
        return dm;
    }
    /**
     * 获取显示设备参数
     *
     * @param context
     * @return
     */
    public static Display getDisplay(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        return wm.getDefaultDisplay();
    }

    /**
     * 范围内取有效值
     *
     * @param min 最小值
     * @param val 原始值
     * @param max 最大值
     * @return 符合最大最小范围的有效值
     */
    public static float bound(float min, float val, float max) {
        return Math.min(Math.max(val, min), max);
    }

    /**
     * dp转为px
     *
     * @param dp dp值
     * @return px值
     */
    public static int dp2px(int dp) {
        return (int) (dp * Resources.getSystem().getDisplayMetrics().density);
    }

    /**
     * 获得View所在界面 NavigationBar 高度
     *
     * @param view 目标View
     * @return 如果存在NavigationBar则返回高度，否则0
     */
    public static int getNavigationBarHeight(View view) {
        Activity activity = getActivity(view);
        if (activity != null) {
            // 方法一
            {
                Display display = activity.getWindowManager().getDefaultDisplay();
                Point size = new Point();
                display.getSize(size);
                int usableHeight = size.y;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                    display.getRealSize(size); // getRealMetrics is only available with API 17 and +
                } else {
                    try {
                        size.x = (Integer) Display.class.getMethod("getRawWidth").invoke(display);
                        size.y = (Integer) Display.class.getMethod("getRawHeight").invoke(display);
                    } catch (Exception e) {
                        Log.w("TAG", "getNavigationBarHeight: error", e);
                    }
                }
                int realHeight = size.y;
                return realHeight > usableHeight ? realHeight - usableHeight : 0;
            }

            // 方法二：不能确定当前屏幕上是否有NaviBar
            // 不能
//                Resources resources = activity.getResources();
//                try {
//                    int resourceId = resources.getIdentifier("navigation_bar_height", "dimen", "android");
//                    if (resourceId > 0) {
//                        return resources.getDimensionPixelSize(resourceId);
//                    }
//                } catch (Exception ignored) {
//                    Log.w(TAG, "getNavigationBarHeight error", ignored);
//                }
        }

        return 0;
    }


    private static Activity getActivity(View view) {
        Context context = view.getContext();
        while (context instanceof ContextWrapper) {
            if (context instanceof Activity) {
                return (Activity) context;
            }
            context = ((ContextWrapper) context).getBaseContext();
        }
        return null;
    }


    public static boolean isNotCollectionEmpty(List<?> c) {
        return c != null && c.size() != 0;
    }
}
