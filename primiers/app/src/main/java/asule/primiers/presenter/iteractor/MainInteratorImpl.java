package asule.primiers.presenter.iteractor;

import java.util.ArrayList;
import java.util.List;

import asule.primiers.R;
import asule.primiers.fragment.BaseFragment;
import asule.primiers.fragment.DrawerOneFragment;
import asule.primiers.fragment.DrawerThreeFragment;
import asule.primiers.fragment.DrawerTwoFragment;

import static asule.primiers.utils.UIHelper.getResources;

/**
 * Created by lenovo on 2016/11/2.
 */

public class MainInteratorImpl {

    public List<String> getTabText(){
        List<String> tabName = new ArrayList<>();
        tabName.add(getResources().getString(R.string.Jelly));
        tabName.add(getResources().getString(R.string.KitKat));
        tabName.add(getResources().getString(R.string.Lollipop));
        return tabName;
    }

    public List<BaseFragment> getFragments(){
        List<BaseFragment> fragments = new ArrayList<>();
        fragments.add(new DrawerOneFragment());
        fragments.add(new DrawerTwoFragment());
        fragments.add(new DrawerThreeFragment());
        return fragments;
    }
}
