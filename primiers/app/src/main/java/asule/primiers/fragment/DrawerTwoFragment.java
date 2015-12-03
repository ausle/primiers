package asule.primiers.fragment;

import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by wcx on 2015/12/3.
 */
public class DrawerTwoFragment extends BaseFragment{
    @Override
    protected View initView() {
        TextView textView = new TextView(mActivity);
        textView.setText("DrawerTwoFragment");
        textView.setGravity(Gravity.CENTER);
        return textView;
    }
}
