package asule.primiers;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;

/**
 * Created by wcx on 2015/12/3.
 */
public class PrimiersApplication extends Application{

    private static PrimiersApplication sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        //在使用百度SDK之前，需要初始化Context的信息
        SDKInitializer.initialize(this);
    }

    public static synchronized PrimiersApplication getInstance() {
        return sInstance;
    }
}
