package asule.primiers.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

import asule.primiers.BaseActivity;
import asule.primiers.R;
import asule.primiers.fragment.BaseFragment;
import asule.primiers.fragment.DrawerOneFragment;
import asule.primiers.fragment.DrawerThreeFragment;
import asule.primiers.fragment.DrawerTwoFragment;
import asule.primiers.utils.UIHelper;
import asule.primiers.view.NoSlideViewPager;

public class MainActivity extends BaseActivity {

    private Toolbar toolbarMain;
    private NoSlideViewPager vpMain;
    private DrawerLayout mDrawer;
    private NavigationView mNavigation;
    private FloatingActionButton btnFloting;
    private ArrayList<BaseFragment> mFragments;
    private String[] drawerTextArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        initListener();
    }

    private void initData() {
        drawerTextArray = UIHelper.getStringArray(R.array.drawer_text);
        mFragments = new ArrayList<>();
        mFragments.add(new DrawerOneFragment());
        mFragments.add(new DrawerTwoFragment());
        mFragments.add(new DrawerThreeFragment());
        if (null != mFragments && !mFragments.isEmpty()) {
            vpMain.setScanScroll(false);
            vpMain.setOffscreenPageLimit(mFragments.size());
            vpMain.setAdapter(new MainFragmentAdapter(getSupportFragmentManager(), mFragments));
        }
    }

    class MainFragmentAdapter extends FragmentPagerAdapter {
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

    private void initListener() {
        mNavigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            private MenuItem tempItem;
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                if (tempItem != null)tempItem.setChecked(false);
                menuItem.setChecked(true);
                int position=0;
                for (int i = 0; i <drawerTextArray.length; i++) {
                    if (menuItem.getTitle().equals(drawerTextArray[i])){
                        position=i;
                    }
                }
                toolbarMain.setTitle(menuItem.getTitle());
                vpMain.setCurrentItem(position,false);
                mDrawer.closeDrawer(Gravity.LEFT);
                tempItem = menuItem;
                return true;//返回true，表示显示该MenuItem，并选中
            }
        });
        btnFloting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(v, "Google come", Snackbar.LENGTH_LONG)
                        .setAction("Close", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Snackbar.make(v, "i am come", Snackbar.LENGTH_SHORT).show();
                            }
                        })
                        .setActionTextColor(UIHelper.getColor(R.color.colorAccent))
                        .show();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawer.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void initView() {
        mDrawer = (DrawerLayout) findViewById(R.id.drawer);
        toolbarMain = (Toolbar) findViewById(R.id.toolbar_main);
        mNavigation = (NavigationView) findViewById(R.id.drawer_navigation);
        vpMain = (NoSlideViewPager) findViewById(R.id.vp_main);
        btnFloting = (FloatingActionButton) findViewById(R.id.btn_floating);
        setSupportActionBar(toolbarMain);//取代ActionBar
        final ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.mipmap.ic_menu);
        ab.setDisplayHomeAsUpEnabled(true);
        setTitle(UIHelper.getString(R.string.Jelly));
    }
}
/**
     android.support.design.widget.CoordinatorLayout
     它可以实现Meterial design中提到的动画效果，并且不需要写相关的动画代码。

     第一种动画效果就是：自动产生向上移动的动画。
     点击FloatingActionButton弹出SnackBar
     FloatingActionButton的onClick传递的view，实际上会对SnackBar进行addView的操作。

     第二种ToolBar的扩展和缩放
        1，ToolBar随着手指滚动而滚动。
           首先AppBarLayout包含ToolBar，让ToolBar响应滚动。
        2，建立AppBarLayout和滚动之间的联系

            @string/appbar_scrolling_view_behavior是一个特殊的字符串资源，可以与AppBarLayout.ScrollingViewBehavior相匹配。
            它可以被绑定在事件发生的view上，当事件被触发后，只要AppBarLayout里定义了app:layout_scrollFlags属性，就可以在事件
            发生后被触发。

            app:layout_scrollFlags必须启动scroll这个属性
                enterAlways指的是当view向上滑动，这个view就显示。

        ToolBar的扩展需要放在CoordinatorLayout中。

     第三种，可以在CollapsingToolbarLayout放入ImageView，可以在它折叠时渐渐淡出，用户滚动时ToolBar的高度也会改变。
 */



