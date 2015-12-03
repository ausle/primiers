package asule.primiers.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;
import asule.primiers.BaseActivity;
import asule.primiers.R;
import asule.primiers.adapter.MyListViewAdapter;
import asule.primiers.bean.DrawerEntity;
import asule.primiers.fragment.BaseFragment;
import asule.primiers.fragment.DrawerOneFragment;
import asule.primiers.fragment.DrawerThreeFragment;
import asule.primiers.fragment.DrawerTwoFragment;
import asule.primiers.holder.BaseHolder;
import asule.primiers.holder.DrawerHolder;
import asule.primiers.utils.UIHelper;

public class MainActivity extends BaseActivity {

    private Toolbar toolbarMain;
    private ViewPager vpMain;
    private ListView lvDrawer;
    private ArrayList<DrawerEntity> drawerEntity;
    private DrawerLayout mDrawer;

    private int mCurrentPosition=0;
    private DrawerAdapter drawerAdapter;
    private ArrayList<BaseFragment> mFragments;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        initLister();
    }

    private void initLister() {
        //把ToolBar构成一个新的actionbar
        ActionBarDrawerToggle mDrawerToggle = new ActionBarDrawerToggle(this, mDrawer, toolbarMain, R.string.app_name, R.string.app_name){
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                setTitle(drawerEntity.get(mCurrentPosition).getContent());
            }
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        //将toolbar和ActionBarDrawerToggle的监听绑定
        mDrawerToggle.syncState();//DrawerLayout的indicator与其安全连接
        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawer.setDrawerListener(mDrawerToggle);
        lvDrawer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mCurrentPosition=position;
                drawerAdapter.notifyDataSetChanged();
                mDrawer.closeDrawer(Gravity.LEFT);
                for (int i = 0; i <parent.getCount(); i++) {
                    if (i==position){
                        view.setBackgroundResource(R.color.down_pre);
                    }else{
                        parent.getChildAt(i).setBackgroundResource(R.color.down_nor);
                    }
                }
                vpMain.setCurrentItem(mCurrentPosition,false);
            }
        });
    }

    private void initData() {
        drawerEntity = new ArrayList<>();
        drawerEntity.add(new DrawerEntity("android_1", R.mipmap.growup_nor));
        drawerEntity.add(new DrawerEntity("android_2", R.mipmap.explore_nor));
        drawerEntity.add(new DrawerEntity("android_3", R.mipmap.my_nor));
        drawerAdapter = new DrawerAdapter(drawerEntity,true);
        lvDrawer.setAdapter(drawerAdapter);
        setTitle(drawerEntity.get(mCurrentPosition).getContent());

        mFragments = new ArrayList<>();
        mFragments.add(new DrawerOneFragment());
        mFragments.add(new DrawerTwoFragment());
        mFragments.add(new DrawerThreeFragment());
        if (null != mFragments && !mFragments.isEmpty()) {
            vpMain.setOffscreenPageLimit(mFragments.size());
            vpMain.setAdapter(new MainFragmentAdapter(getSupportFragmentManager(), mFragments));
        }
    }

    private void initView() {
        mDrawer = (DrawerLayout) findViewById(R.id.drawer);
        toolbarMain = (Toolbar) findViewById(R.id.toolbar_main);
        setTitle("一行白鹭");
        setSupportActionBar(toolbarMain);//取代ActionBar
        toolbarMain.setPadding(0, UIHelper.getStatusHeight(this), 0, 0);
        vpMain = (ViewPager) findViewById(R.id.vp_main);
        lvDrawer = (ListView) findViewById(R.id.lv_drawer);
    }

    class MainFragmentAdapter extends FragmentPagerAdapter{
        private ArrayList<BaseFragment> mFragments;
        public MainFragmentAdapter(FragmentManager fm, ArrayList<BaseFragment> fragments) {
            super(fm);
            this.mFragments=fragments;
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }
    }

    class DrawerAdapter extends MyListViewAdapter<DrawerEntity>{
        public DrawerAdapter(List<DrawerEntity> mListData,boolean isDrawer) {
            super(mListData,isDrawer);
        }
        @Override
        public BaseHolder getHolder() {
            return new DrawerHolder();
        }
    }
}
