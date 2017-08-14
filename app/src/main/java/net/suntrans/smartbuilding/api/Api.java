package net.suntrans.smartbuilding.api;

import net.suntrans.smartbuilding.Constant;
import net.suntrans.smartbuilding.data.LoginEntity;
import net.suntrans.smartbuilding.data.MenuItemEntity;
import net.suntrans.smartbuilding.data.SceneEntity;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by Looney on 2017/1/4.
 */

public interface Api {


    @FormUrlEncoded
    @POST("Mobile_Data/login_info")
    Observable<LoginEntity> login(@Field("uname") String uname,
                                  @Field("upwd") String upwd
    );

    @FormUrlEncoded
    @POST("Mobile_Data/menu_info")
    Observable<MenuItemEntity> getMenuItem(@Field("menuid") String menuid);

    @FormUrlEncoded
    @POST("Model/GetAllModel")
    Observable<SceneEntity> getMyAreaDetail(@Field("ID") String s);

}
