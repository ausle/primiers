package asule.primiers.presenter.impl;

import asule.primiers.presenter.Presenter;
import asule.primiers.presenter.iteractor.MainInteratorImpl;
import asule.primiers.presenter.view.MainView;

/**
 * Created by lenovo on 2016/11/2.
 */

public class MainPresenterImpl implements Presenter{

    private MainView mainView;
    private final MainInteratorImpl mainInterator;

    public MainPresenterImpl(MainView mainView) {
        this.mainView=mainView;
        mainInterator = new MainInteratorImpl();
    }

    @Override
    public void init() {
        mainView.getTabTextAndFragment(mainInterator.getTabText(),mainInterator.getFragments());
    }
}
