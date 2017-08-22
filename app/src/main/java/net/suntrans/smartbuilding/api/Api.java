package net.suntrans.smartbuilding.api;

import net.suntrans.smartbuilding.Constant;
import net.suntrans.smartbuilding.data.AirConditionEntity;
import net.suntrans.smartbuilding.data.LightEntity;
import net.suntrans.smartbuilding.data.LoginEntity;
import net.suntrans.smartbuilding.data.MenuItemEntity;
import net.suntrans.smartbuilding.data.SceneEntity;
import net.suntrans.smartbuilding.data.SocketEntity;
import net.suntrans.smartbuilding.data.XenonEntity;

import java.util.Map;

import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Url;
import rx.Observable;

/**
 * Created by Looney on 2017/1/4.
 */

public interface Api {


    /**
     * 登录
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST("Login/Login?response_type=code&client_id=testclient&state=xyz")
    Observable<LoginEntity> login(@FieldMap Map<String ,String> map);

    /**
     * 获取菜单信息
     * @param menuid
     * @return
     */
    @FormUrlEncoded
    @POST("Mobile_Data/menu_info")
    Observable<MenuItemEntity> getMenuItem(@Field("menuid") String menuid);

    /**
     * 我的区域->场景
     * @return
     */
    @POST("Model/GetAllModel")
    Observable<SceneEntity> getMyareaScene();


    /**
     * 我的区域->照明
     * @return
     */
    @POST("Device/GetLight")
    Observable<LightEntity> getMyareaLight();

    /**
     * 我的区域->空调列表
     *
     * @return
     */
    @POST("Device/GetAir")
    Observable<AirConditionEntity> getMyareaAir();

    /**
     * 我的区域->氙气灯列表
     *
     * @return
     */
    @POST("Device/GetXemon")
    Observable<XenonEntity> getMyareaXenon();

    /**
     * 我的区域->插座列表
     *
     * @return
     */
    @POST("Device/GetSocket")
    Observable<SocketEntity> getMyareaSocket();

    /**
     * 我的区域->第六感列表
     *
     * @return
     */
    @POST("Device/GetSix")
    Observable<SocketEntity> getMyareaSixSensor();

}
