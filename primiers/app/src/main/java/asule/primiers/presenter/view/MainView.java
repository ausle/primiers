package asule.primiers.presenter.view;

import java.util.List;

import asule.primiers.fragment.BaseFragment;

/**
 * Created by lenovo on 2016/11/2.
 */

public interface MainView {

    void getTabTextAndFragment(List<String> texts,List<BaseFragment> fragments);

}
