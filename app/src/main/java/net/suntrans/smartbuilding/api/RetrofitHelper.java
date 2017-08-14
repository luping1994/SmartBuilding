package net.suntrans.smartbuilding.api;

import net.suntrans.smartbuilding.App;
import net.suntrans.smartbuilding.R;
import net.suntrans.smartbuilding.utils.LogUtil;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.internal.Util;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.chad.library.adapter.base.listener.SimpleClickListener.TAG;

/**
 * Created by Looney on 2016/12/15.
 */

public class RetrofitHelper {


    //public static final String BASE_URL = "http://www.suntrans.net:8956";
    public static final String BASE_URL = "http://183.236.25.190:8090/interface/V1/";
//    public static final String BASE_URL2 = "http://adminiot.suntrans-cloud.com/";

    private static OkHttpClient mOkHttpClient;

    static {
        initOkHttpClient();
    }

    public static Api getApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(mOkHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit.create(Api.class);
    }


    private static void initOkHttpClient() {

        Interceptor netInterceptor =
                new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {


                        Request original = chain.request();
                        RequestBody newBody = original.body();
                        if (original.body() instanceof FormBody) {
                            newBody = addParamsToFormBody((FormBody) original.body());
                        }
                        Request newRequest = original.newBuilder()
                                .method(original.method(), newBody)
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

    /**
     * 为FormBody类型请求体添加参数
     *
     * @param body
     * @return
     */
    private static FormBody addParamsToFormBody(FormBody body) {
        FormBody.Builder builder = new FormBody.Builder();
        String header = App.getSharedPreferences().getString("token", "raVnKIh8Rv");
        String group = App.getSharedPreferences().getString("group", "raVnKIh8Rv");
        String id = App.getSharedPreferences().getString("id", "-1");
        LogUtil.i("token",header);
        builder.add("token", header);
        builder.add("group", group);
        builder.add("id", id);

        //添加原请求体
        for (int i = 0; i < body.size(); i++) {
            builder.addEncoded(body.encodedName(i), body.encodedValue(i));
        }
        return builder.build();
    }
}
