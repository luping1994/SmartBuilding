package net.suntrans.smartbuilding.api;

import net.suntrans.smartbuilding.App;
import net.suntrans.smartbuilding.utils.LogUtil;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Looney on 2016/12/15.
 */

public class RetrofitHelper {


    //public static final String BASE_URL = "http://www.suntrans.net:8956";
    public static final String BASE_URL = "http://183.236.25.190:8090/interface/V1/Mobile_Data/";
//    public static final String BASE_URL2 = "http://adminiot.suntrans-cloud.com/";

    private static OkHttpClient mOkHttpClient;

    static {
        initOkHttpClient();
    }

    public static Api getApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit.create(Api.class);
    }


    private static void initOkHttpClient() {

        Interceptor netInterceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                String header = App.getSharedPreferences().getString("token", "-1");

                LogUtil.i(header);
                Request original = chain.request();

                HttpUrl.Builder builder = original.url()
                        .newBuilder()
                        .setEncodedQueryParameter("token", header);

                Request newRequest = original.newBuilder()
                        .method(original.method(), original.body())
                        .url(builder.build())
                        .build();
                Response response = chain.proceed(newRequest);
                return response;
            }
        };


        if (mOkHttpClient == null) {
            synchronized (RetrofitHelper.class) {
                if (mOkHttpClient == null) {
                    mOkHttpClient = new OkHttpClient.Builder()
                            .addInterceptor(netInterceptor)
                            .connectTimeout(10, TimeUnit.SECONDS)
                            .build();
                }
            }
        }
    }


}
