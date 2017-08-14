package net.suntrans.smartbuilding;

import android.app.Application;
import android.content.SharedPreferences;
import android.content.Context;

import com.squareup.leakcanary.LeakCanary;

/**
 * Created by Administrator on 2017/8/8.
 */

public class App extends Application {


    private static SharedPreferences mSharedPreferences;
    private static Application application;
    private static Application application2;

    public static SharedPreferences getSharedPreferences() {
        if (mSharedPreferences==null){
            mSharedPreferences = getApplication().getSharedPreferences("SBConfig",Context.MODE_PRIVATE);
        }
        return mSharedPreferences;
    }
    public static Application getApplication() {
        return application;
    }


    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
        application = this;
    }
}
