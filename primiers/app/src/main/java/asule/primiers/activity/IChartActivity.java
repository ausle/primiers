package asule.primiers.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import asule.primiers.BaseActivity;
import asule.primiers.R;
import asule.primiers.bean.PointEntity;
import asule.primiers.view.IChartView;

/**
 * Created by lenovo on 2016/11/2.
 */

public class IChartActivity extends BaseActivity{

    private String[] dates=new String[]{"11-07","11-08","11-09","11-10"};
    private int[] colors= new int[]{R.color.city1,R.color.city2,R.color.city3,R.color.city0};
    private int max=20;
    private int min=0;
    private int stroke=4;
    private List<List<PointEntity>> points=new ArrayList<>();
    private List<PointEntity> point;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ichart);
        IChartView chartView= (IChartView) findViewById(R.id.chartview);
        chartView.setxValues(dates);
        chartView.setyValues(20);
        for (int j = 0; j<10;j++) {
            point = new ArrayList<>();
            for (int i = 0; i <dates.length; i++) {
                point.add(new PointEntity(colors[i],new Random().nextInt(max -min+1) + min,stroke));
            }
            points.add(point);
        }
        chartView.setPoints(points);
    }
}
