package asule.primiers.fragment.sub;

import android.view.View;
import android.widget.TextView;

import asule.primiers.R;
import asule.primiers.fragment.BaseFragment;
import asule.primiers.utils.UIHelper;

public class ActivityLiftCycleFm extends BaseFragment{

    private TextView tvOncreate;
    private TextView tvOnrestart;
    private TextView tvOnStart;
    private TextView tvOnResume;
    private TextView tvOnStop;
    private TextView tvOnPause;
    private TextView tvOnDestroy;

    @Override
    protected View initView() {
        View mView = View.inflate(mActivity, R.layout.content_b_child, null);

        tvOncreate = (TextView) mView.findViewById(R.id.tv_oncreate);
        tvOnrestart = (TextView) mView.findViewById(R.id.tv_onrestart);
        tvOnStart = (TextView) mView.findViewById(R.id.tv_onstart);
        tvOnResume = (TextView) mView.findViewById(R.id.tv_onresume);
        tvOnStop = (TextView) mView.findViewById(R.id.tv_onstop);
        tvOnPause = (TextView) mView.findViewById(R.id.tv_onpasue);
        tvOnDestroy = (TextView) mView.findViewById(R.id.tv_ondestory);

        return mView;
    }

    @Override
    public void initData() {
        super.initData();
        tvOncreate.setText(UIHelper.getString(R.string.onCreate));
        tvOnrestart.setText(UIHelper.getString(R.string.onRestart));
        tvOnStart.setText(UIHelper.getString(R.string.onStart));
        tvOnResume.setText(UIHelper.getString(R.string.onResume));
        tvOnStop.setText(UIHelper.getString(R.string.onPause));
        tvOnPause.setText(UIHelper.getString(R.string.onStop));
        tvOnDestroy.setText(UIHelper.getString(R.string.onDestrot));
    }
    /**

        onCreate()
            Activity创建就被调用，可以做一些初始化的工作，比如setContentView（）以及activity的初始化数据

        onReStart()
            Activity正在重新启动，activity从不可见变为可见。
            比如：按下Home，回到桌面，重新进入该activity就会调用。
            没有finish的情况，进入另一个界面(activity)，才返回回来也会调用。

        onStart()
            activity变得可见。但是没有位于前台。可以认为获得了焦点。

        onResume()
            activity位于前台，正在前台工作。

        onPause()
           activity正在停止，马上就要执行onStop，可以做一些存储数据，停止动画的工作。
           但切不可以做耗时的操作。

        onStop()
           activity停止，可以做一些重量级的回收工作

        onDestroy()
           activity销毁，可以做最后的回收和资源回收的工作

     */
}
