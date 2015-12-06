package asule.primiers.detail;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;

import asule.primiers.BaseActivity;
import asule.primiers.Constants;
import asule.primiers.R;

/**
 * Created by Administrator on 2015/12/6.
 */
public class MeterialDemoDetetail extends BaseActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        String cheeseName = getIntent().getStringExtra(Constants.INTENT_FLAG);
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        CollapsingToolbarLayout collapsingToolbar =(CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(cheeseName);

        ImageView viewById = (ImageView) findViewById(R.id.backdrop);
        viewById.setBackgroundResource(R.mipmap.drawer_top);
    }
}
