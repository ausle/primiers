package asule.primiers.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by wcx on 2015/12/4.
 */
public class NoSlideViewPager extends ViewPager{

    private boolean isEnableScroll = true;

    public NoSlideViewPager(Context context) {
        super(context);
    }

    public NoSlideViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setScanScroll(boolean isCanScroll){
        this.isEnableScroll = isCanScroll;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        if (!isEnableScroll) {
            return false;
        }
        return super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (!isEnableScroll) {
            return false;
        }
        return super.onTouchEvent(ev);
    }



//ViewPager的滑动都用到了scrollTo，如要禁止滑动,复写scrollTo。禁止滑动带来的后果是，没法去切换ViewPager的各个页面。这里不符合实际场景。
//    @Override
//    public void scrollTo(int x, int y){
//        if (isCanScroll){
//            super.scrollTo(x, y);
//        }
//    }
}
