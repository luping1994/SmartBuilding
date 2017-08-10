package net.suntrans.smartbuilding.api;

import net.suntrans.smartbuilding.model.LoginEntity;
import net.suntrans.smartbuilding.model.MenuItemEntity;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Looney on 2017/1/4.
 */

public interface Api {


    @FormUrlEncoded
    @POST("login_info")
    Observable<LoginEntity> login(@Field("uname") String uname,
                                  @Field("upwd") String upwd
    );

    @FormUrlEncoded
    @POST("menu_info")
    Observable<MenuItemEntity> getMenuItem(@Field("id") String id,
                                           @Field("menuid") String menuid);    @FormUrlEncoded
    @POST("GetModel")
    Observable<MenuItemEntity> getMyAreaMode(@Field("id") String id,
                                           @Field("menuid") String menuid,
                                             @Field("status") String status,
                                             @Field("modelid") String modelid);

}
