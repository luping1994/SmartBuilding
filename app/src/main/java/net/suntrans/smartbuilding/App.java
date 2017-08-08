package net.suntrans.smartbuilding;

import android.app.Application;
import android.content.SharedPreferences;
import android.content.Context;

/**
 * Created by Administrator on 2017/8/8.
 */

public class App extends Application {


    private static SharedPreferences mSharedPreferences;
    private static Application application;

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
        application = this;
    }
}
