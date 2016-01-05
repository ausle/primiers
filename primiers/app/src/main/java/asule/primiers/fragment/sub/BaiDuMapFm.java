package asule.primiers.fragment.sub;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import asule.primiers.R;
import asule.primiers.activity.BaiduMapActivity;
import asule.primiers.fragment.BaseFragment;

/**
 * Created by Administrator on 2016/1/5.
 */
public class BaiDuMapFm extends BaseFragment{

    private Button btnLocation;

    @Override
    protected View initView() {
        View mView = View.inflate(mActivity, R.layout.content_c_child, null);
        btnLocation = (Button) mView.findViewById(R.id.btn_location);
        return mView;
    }


    @Override
    public void initData() {
        super.initData();
        btnLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mActivity, BaiduMapActivity.class);
                startActivity(intent);
            }
        });
    }
}
