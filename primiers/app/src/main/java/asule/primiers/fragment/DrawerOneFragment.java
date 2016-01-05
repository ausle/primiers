package asule.primiers.fragment;

import android.support.v4.view.ViewPager;
import android.view.View;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;
import asule.primiers.R;
import asule.primiers.fragment.sub.ActivityLiftCycleFm;
import asule.primiers.fragment.sub.BaiDuMapFm;
import asule.primiers.fragment.sub.MeterialDemoFm;

/**
 * Created by wcx on 2015/12/3.
 */
public class DrawerOneFragment extends BaseFragment{

    @Override
    protected View initView() {
        View mView = View.inflate(mActivity, R.layout.fragment_one, null);
        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                getChildFragmentManager(), FragmentPagerItems.with(mActivity)
                .add(R.string.meterial_design_demo, MeterialDemoFm.class)
                .add(R.string.activity_init,ActivityLiftCycleFm.class)
                .add(R.string.baidu_map,BaiDuMapFm.class)
                .create());
        ViewPager viewPager = (ViewPager)mView.findViewById(R.id.viewpager);
        viewPager.setAdapter(adapter);
        SmartTabLayout viewPagerTab=(SmartTabLayout) mView.findViewById(R.id.viewpagertab);
        viewPagerTab.setViewPager(viewPager);
        return mView;
    }

    @Override
    public void initData() {
        super.initData();
    }
}
