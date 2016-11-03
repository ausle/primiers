package asule.primiers.fragment;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;

import asule.primiers.R;
import asule.primiers.activity.IChartActivity;


/**
 * Created by wcx on 2015/12/3.
 */
public class DrawerOneFragment extends BaseFragment implements View.OnClickListener {

    private View mView;
    private LinearLayout ll;
    private Intent intent;

    @Override
    protected View initView() {
        mView = View.inflate(mActivity, R.layout.fragment_one, null);
        ll = (LinearLayout) mView.findViewById(R.id.ll);
        return mView;
    }

    @Override
    public void initData() {
        super.initData();
    }

    @Override
    public void initListener() {
        super.initListener();
        mView.findViewById(R.id.card0).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.card0:
                intent = new Intent(mActivity,IChartActivity.class);
                startActivity(intent);
                break;
        }
    }
}
