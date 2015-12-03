package asule.primiers.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import asule.primiers.R;
import asule.primiers.bean.DrawerEntity;
import asule.primiers.utils.UIHelper;

/**
 * Created by wcx on 2015/12/3.
 */
public class DrawerHolder extends BaseHolder<DrawerEntity>{

    private ImageView ivDrawer;
    private TextView tvDrawer;

    @Override
    public View initView() {
        View view = UIHelper.inflate(R.layout.item_drawer);
        ivDrawer = (ImageView) view.findViewById(R.id.iv_drawer);
        tvDrawer = (TextView) view.findViewById(R.id.tv_drawer);
        return view;
    }

    @Override
    public void refreshView() {
        DrawerEntity data = getData();
        ivDrawer.setImageResource(data.getIconId());
        tvDrawer.setText(data.getContent());
    }
}
