package asule.primiers.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import asule.primiers.R;

/**
 * Created by lenovo on 2016/11/4.
 */

public class FloatLayout extends RelativeLayout{

    private Context mContext;
    private View loadingView;
    private View netErrorView;
    private View dataEmptyView;
    private ImageView ivLoading;

    public enum DisplayType{
        LOADING,NETERROR,NORESULE
    }

    public FloatLayout(Context context) {
        this(context,null);
    }

    public FloatLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mContext=context;
        init();
    }

    private void init(){
        LayoutInflater inflater=LayoutInflater.from(mContext);
        loadingView = inflater.inflate(R.layout.layout_loading, null);
        ivLoading = (ImageView) loadingView.findViewById(R.id.iv_loading);
        netErrorView = inflater.inflate(R.layout.layout_neterror, null);
        dataEmptyView = inflater.inflate(R.layout.layout_dataempty, null);
        addView(loadingView);
        addView(netErrorView);
        addView(dataEmptyView);
        hideView();
    }

    private void hideView() {
        for (int i = 0; i <getChildCount(); i++) {
            if(getChildAt(i).getVisibility()==VISIBLE){
                getChildAt(i).setVisibility(GONE);
            }
        }
    }

    public void show(DisplayType type){
        hideView();
        if (type==DisplayType.LOADING){
            loadingView.setVisibility(VISIBLE);
            ivLoading.startAnimation(getRotateAnimation());
        }else if (type== DisplayType.NETERROR){
            netErrorView.setVisibility(VISIBLE);
        }else if (type==DisplayType.NORESULE){
            dataEmptyView.setVisibility(VISIBLE);
        }
    }

    private static Animation getRotateAnimation() {
        final RotateAnimation rotateAnimation = new RotateAnimation(0, 360,
                Animation.RELATIVE_TO_SELF, .5f,
                Animation.RELATIVE_TO_SELF, .5f);
        rotateAnimation.setDuration(1500);
        rotateAnimation.setInterpolator(new LinearInterpolator());
        rotateAnimation.setRepeatCount(Animation.INFINITE);
        return rotateAnimation;
    }
}
