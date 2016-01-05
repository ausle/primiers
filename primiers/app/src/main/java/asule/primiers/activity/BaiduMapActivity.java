package asule.primiers.activity;

import android.os.Bundle;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;

import asule.primiers.BaseActivity;
import asule.primiers.R;

/**
 * Created by Administrator on 2016/1/5.
 */
public class BaiduMapActivity extends BaseActivity{

    private MapView mMapView;
    private BaiduMap mBaiduMap;


    private boolean isFirst=true;
    private LocationClient locationClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_baidumap);
        initView();
        zoomAndExpandMap();
        location();
    }

    private void initView() {
        mMapView = (MapView) findViewById(R.id.map);
        mBaiduMap = mMapView.getMap();
    }


    //定位最核心的api         LocationClient
    //                       接口BDLocationListener
    private void location() {
        locationClient = new LocationClient(this);
        MyLocationListener myLocationListener = new MyLocationListener();
        locationClient.registerLocationListener(myLocationListener);//注册监听

        //Location的一些选项设置
        LocationClientOption clientOption = new LocationClientOption();

        clientOption.setCoorType("bd09ll");
        clientOption.setIsNeedAddress(true);
        clientOption.setOpenGps(true);
        clientOption.setScanSpan(1000);
        locationClient.setLocOption(clientOption);
    }


    private void zoomAndExpandMap() {
        //自定义百度地图的缩放和扩大比例
        //默认的比例是5公里，缩放15f后比例为500米
        MapStatusUpdate zoomTo = MapStatusUpdateFactory.zoomTo(15f);
        mBaiduMap.setMapStatus(zoomTo);
    }

    class MyLocationListener implements BDLocationListener{
        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            MyLocationData locationData = new MyLocationData.Builder()
                    .accuracy(bdLocation.getRadius())
                    .latitude(bdLocation.getAltitude())
                    .longitude(bdLocation.getLongitude())
                    .build();

            //位置信息传递给百度Map
              mBaiduMap.setMyLocationData(locationData);
//            MyLocationConfiguration myLocationConfiguration =
//                    new MyLocationConfiguration(MyLocationConfiguration.LocationMode.NORMAL);


            //如果第一次定位时，定位到当前的位置
            if (isFirst){
                LatLng latLng =
                        new LatLng(bdLocation.getLatitude(), bdLocation.getLongitude());
                MapStatusUpdate mapStatusUpdate
                        = MapStatusUpdateFactory.newLatLng(latLng);
                mBaiduMap.animateMapStatus(mapStatusUpdate);
                isFirst=false;
                Toast.makeText
                        (BaiduMapActivity.this, bdLocation.getAddrStr(), Toast.LENGTH_SHORT)
                        .show();
            }

            //回到我的位置，得到最新的经纬度，就可以切换回去
//            LatLng latLng =
//                    new LatLng(bdLocation.getLatitude(), bdLocation.getLongitude());
//            MapStatusUpdate mapStatusUpdate
//                    = MapStatusUpdateFactory.newLatLng(latLng);
//            mBaiduMap.animateMapStatus(mapStatusUpdate);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mBaiduMap.setMyLocationEnabled(true); //开启定位图层
        if (locationClient.isStarted()){
            locationClient.start();//定位没启动，就启动
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mMapView.onPause();
    }


    @Override
    protected void onStop() {//退出时或按Home键，定位需要关闭。
        super.onStop();
        mBaiduMap.setMyLocationEnabled(false);//关闭定位图层
        locationClient.stop();//定位关闭
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }
}
