package asule.primiers.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import java.util.List;

import asule.primiers.BaseActivity;
import asule.primiers.R;
import asule.primiers.fragment.BaseFragment;
import asule.primiers.presenter.impl.MainPresenterImpl;
import asule.primiers.presenter.view.MainView;


public class MainActivity extends BaseActivity implements MainView{

    private ViewPager vpMain;
    private TabLayout tl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_content);
        initView();
        initData();
    }

    private void initView() {
        vpMain = (ViewPager) findViewById(R.id.vp_main);
        tl = (TabLayout) findViewById(R.id.tl_layout);
        tl.setTabMode(TabLayout.MODE_FIXED);
        tl.setTabGravity(TabLayout.GRAVITY_CENTER);
    }

    private void initData() {
        MainPresenterImpl mainPresenter = new MainPresenterImpl(this);
        mainPresenter.init();
    }

    @Override
    public void getTabTextAndFragment(final List<String> texts, final List<BaseFragment> fragments) {
            FragmentPagerAdapter mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return fragments.size();
            }
            @Override
            public Fragment getItem(int position) {
                return fragments.get(position);
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return texts.get(position);
            }
        };
        vpMain.setAdapter(mAdapter);
        tl.setupWithViewPager(vpMain);
    }
}



