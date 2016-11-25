package asule.primiers.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import asule.primiers.R;

/**
 * Created by lenovo on 2016/11/24.
 */

public class ManualListView extends ListView{

    private LayoutInflater inflater;

    public ManualListView(Context context) {
        super(context);
    }

    public ManualListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflater = LayoutInflater.from(context);
        init();
    }

    private void init() {
        View headView = inflater.inflate(R.layout.head_view, null);
        addHeaderView(headView);
    }
}
