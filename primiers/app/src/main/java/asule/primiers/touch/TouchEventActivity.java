package asule.primiers.touch;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;

import asule.primiers.Constants;
import asule.primiers.R;

/**
 * Created by wcx on 2015/12/4.
 */
public class TouchEventActivity extends Activity{
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.touchevent);
    }

    public boolean dispatchTouchEvent(MotionEvent ev) {
        Log.i(Constants.LOG, "TouchEventActivity | dispatchTouchEvent --> " + TouchEventUtil.getTouchAction(ev.getAction()));
        return false;
//        return super.dispatchTouchEvent(ev);
    }

    public boolean onTouchEvent(MotionEvent event) {
        Log.i(Constants.LOG, "TouchEventActivity | onTouchEvent --> " + TouchEventUtil.getTouchAction(event.getAction()));
        return super.onTouchEvent(event);
    }
}
/**
    所有的事件首先由当前的Activity的dispatchTouchEvent进行分发，

    dispatchTouchEvent负责事件的分发

        如果dispatchTouchEvent返回的是true，事件会由dispatchTouchEvent进行消费，并且停止向下进行传递。
        如果返回的是false，分为两种情况：
            如果当前view的获取的事件直接来自于Activity，那么会返回给Activity的onTouchEvent进行处理。
            如果来自于它的父控件，那么由父控件的onTouchEvent进行处理。

        如果返回默认的super.dispatchTouchEvent(ev)，那么会自动的分发给当前View的onInterceptTouchEvent方法。
        //如果Activity返回super.dispatchTouchEvent(ev)，事件继续向下分发。


        onInterceptTouchEvent负责事件的拦截
        外层的view的dispatchTouchEvent返回的是默认的super.dispatchTouchEvent(ev)，那么就会自动的分发给当前的
        onInterceptTouchEvent。
        如果返回true,那么事件将会拦截，并由当前的onTouchEvent进行处理。
        如果返回false，那么事件将会传递，传递到子view的dispatchTouchEvent，由它来判断。
        如果返回super.onInterceptTouchEvent(ev)，事件会默认的拦截，并由当前的view的onTouchEvent进行处理。

        onTouchEvent
        dispatchEvent分发时，返回默认的super.dispatchTouchEvent(ev)并且当前view返回true或者super.dispatchTouchEvent(ev),
        那么就会由onTouchEvent处理。

        如果返回false，就会交由上层的onTouchEvent进行处理，如果上层的onTouchEvent同样返回false，那么接着向上传递。
        直到最上层，依旧会是false，那么该事件就会消失。
        如果返回true,事件将会交互。
        返回super.onTouchEvent(event)，默认处理逻辑与false一样。
 */