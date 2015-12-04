package asule.primiers.touch;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import asule.primiers.Constants;

/**
 * Created by wcx on 2015/12/4.
 */
public class TouchEventChild extends LinearLayout{
    public TouchEventChild(Context context) {
        super(context);
    }

    public TouchEventChild(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TouchEventChild(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.i(Constants.LOG, "TouchEventChild | dispatchTouchEvent --> " + TouchEventUtil.getTouchAction(ev.getAction()));
        return super.dispatchTouchEvent(ev);
    }

    public boolean onInterceptTouchEvent(MotionEvent ev) {
        Log.i(Constants.LOG, "TouchEventChild | onInterceptTouchEvent --> " + TouchEventUtil.getTouchAction(ev.getAction()));
        return super.onInterceptTouchEvent(ev);
    }

    public boolean onTouchEvent(MotionEvent ev) {
        Log.i(Constants.LOG, "TouchEventChild | onTouchEvent --> " + TouchEventUtil.getTouchAction(ev.getAction()));
        return super.onTouchEvent(ev);
    }
}
