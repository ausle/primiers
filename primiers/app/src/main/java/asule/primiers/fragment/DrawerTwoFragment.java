package asule.primiers.fragment;

import android.view.View;

import asule.primiers.view.ManualListView;

/**
 * Created by wcx on 2015/12/3.
 */
public class DrawerTwoFragment extends BaseFragment{
    @Override
    protected View initView() {
        ManualListView manualListView = new ManualListView(mActivity);
        return manualListView;
    }
}
