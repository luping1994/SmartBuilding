package net.suntrans.smartbuilding.api;
import net.suntrans.smartbuilding.data.AirConditionEntity;
import net.suntrans.smartbuilding.data.AreaEntity;
import net.suntrans.smartbuilding.data.LightEntity;
import net.suntrans.smartbuilding.data.LoginEntity;
import net.suntrans.smartbuilding.data.MenuItemEntity;
import net.suntrans.smartbuilding.data.SceneEntity;
import net.suntrans.smartbuilding.data.SixSensor;
import net.suntrans.smartbuilding.data.SocketEntity;
import net.suntrans.smartbuilding.data.XenonEntity;
import java.util.Map;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;
/**
 * Created by Looney on 2017/1/4.
 */

public interface Api {


    /**
     * 登录
     *
     * @param map
     * @return
     */
    @FormUrlEncoded
    @POST("Login/Login?response_type=code&client_id=testclient&state=xyz")
    Observable<LoginEntity> login(@FieldMap Map<String, String> map);

    /**
     * 获取菜单信息
     *
     * @param menuid
     * @return
     */
    @FormUrlEncoded
    @POST("Mobile_Data/menu_info")
    Observable<MenuItemEntity> getMenuItem(@Field("menuid") String menuid);



    /**
     * 我的区域->场景
     *
     * @return
     */
    @POST("Model/GetAllModel")
    Observable<SceneEntity> getMyareaScene();

    /**
     * 全局控制->..区域->场景
     *
     * @return
     */
    @FormUrlEncoded
    @POST("Model/VisitGetALLModel")
    Observable<SceneEntity> getGlobalScene(@Field("areaid") String areaid);


    /**
     * 我的区域->照明
     *
     * @return
     */
    @POST("Device/GetLight")
    Observable<LightEntity> getMyareaLight();

    /**
     * 全局控制->..区域->照明
     *
     * @return
     */
    @POST("Visit_Data/VisitLight")
    Observable<LightEntity> getGlobalLight();

    /**
     * 我的区域->空调列表
     *
     * @return
     */
    @POST("Device/GetAir")
    Observable<AirConditionEntity> getMyareaAir();

    /**
     * 全局控制->..区域->空调列表
     *
     * @return
     */
    @POST("Visit_Data/VisitAir")
    Observable<AirConditionEntity> getGlobalAir();

    /**
     * 我的区域->氙气灯列表
     *
     * @return
     */
    @POST("Device/GetXemon")
    Observable<XenonEntity> getMyareaXenon();

    /**
     * 全局控制->..区域->氙气灯列表
     *
     * @return
     */
    @POST("Visit_Data/VisitXemon")
    Observable<XenonEntity> getGlobalXenon();

    /**
     * 我的区域->插座列表
     *
     * @return
     */
    @POST("Device/GetSocket")
    Observable<SocketEntity> getMyareaSocket();

    /**
     * 全局控制->..区域->插座列表
     *
     * @return
     */
    @POST("Visit_Data/VisitSocket")
    Observable<SocketEntity> getGlobalSocket();

    /**
     * 我的区域->第六感列表
     *
     * @return
     */
    @POST("Device/GetSix")
    Observable<SixSensor> getMyareaSixSensor();

    /**
     * 全局控制->..区域->第六感列表
     *
     * @return
     */
    @POST("Visit_Data/VisitSix")
    Observable<SixSensor> getGlobalSixSensor();

    /**
     * 全局控制 区域列表
     * @return
     */
    @FormUrlEncoded
    @POST("Visit_Data/globalsarea")
    Observable<AreaEntity> getGlobalList(@Field("areaid") String areaid);

}
