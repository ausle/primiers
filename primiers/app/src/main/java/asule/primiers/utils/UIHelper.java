package asule.primiers.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;

import asule.primiers.PrimiersApplication;

public class UIHelper {

    /**
     * dp2px
     * @param context
     * @return
     */
    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    /**
     * px2dp
     *
     * @param context
     * @return
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static float getDensity(Context context) {
        return context.getResources().getDisplayMetrics().density;
    }

    /**
     * @param context
     * @return
     */
    public static int getScreenWidthDip(Context context) {
        int widthPx = UIHelper.getScreenWidth(context);
        int widthDip = UIHelper.px2dip(context, widthPx);
        return widthDip;
    }

    /**
     * @param context
     * @return
     */
    public static int getScreenHeightDip(Context context) {
        int heightPx = UIHelper.getScreenHeight(context);
        int heightDip = UIHelper.px2dip(context, heightPx);
        return heightDip;
    }

    /**
     * @param context
     * @return
     */
    public static int getScreenWidth(Context context) {
        if (context != null) {
            return context.getResources().getDisplayMetrics().widthPixels;
        } else {
            return 0;
        }
    }

    /**
     * @param context
     * @return
     */
    public static int getScreenHeight(Context context) {
        if (context != null) {
            return context.getResources().getDisplayMetrics().heightPixels;
        } else {
            return 0;
        }
    }

    /**
     * @param context
     * @param oldScreenWidth 原有尺寸所在屏幕宽度
     * @param size           尺寸
     * @return 在现有屏幕中的尺寸
     */

    public static int getFitSize(Context context, int oldScreenWidth, int size) {
        if (context != null) {
            int thisScreenWidth = getScreenWidth(context);
            return size * thisScreenWidth / oldScreenWidth;
        } else {
            return 0;
        }
    }

    /** 获取状态栏高度
     * @param activity
     */
    public static int getStatusHeight(Activity activity) {
        int statusHeight = 0;
        Rect localRect = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(localRect);
        statusHeight = localRect.top;
        if (0 == statusHeight) {
            Class<?> localClass;
            try {
                localClass = Class.forName("com.android.internal.R$dimen");
                Object localObject = localClass.newInstance();
                int i5 = Integer.parseInt(localClass.getField("status_bar_height").get(localObject).toString());
                statusHeight = activity.getResources().getDimensionPixelSize(i5);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return statusHeight;
    }

    public static Context getContext() {
        return PrimiersApplication.getInstance();
    }

    public static View inflate(int resId){
        return LayoutInflater.from(getContext()).inflate(resId,null);
    }

    /** 获取资源 */
    public static Resources getResources() {
        return getContext().getResources();
    }

    /** 获取文字 */
    public static String getString(int resId) {
        return getResources().getString(resId);
    }

    /** 获取文字数组 */
    public static String[] getStringArray(int resId) {
        return getResources().getStringArray(resId);
    }

    /** 获取dimen */
    public static int getDimens(int resId) {
        return getResources().getDimensionPixelSize(resId);
    }

    /** 获取drawable */
    public static Drawable getDrawable(int resId) {
        return getResources().getDrawable(resId);
    }

    /** 获取颜色 */
    public static int getColor(int resId) {
        return getResources().getColor(resId);
    }

}
