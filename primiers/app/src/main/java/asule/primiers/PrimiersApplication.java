package asule.primiers;

import android.app.Application;

/**
 * Created by wcx on 2015/12/3.
 */
public class PrimiersApplication extends Application{

    private static PrimiersApplication sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
    }

    public static synchronized PrimiersApplication getInstance() {
        return sInstance;
    }
}
