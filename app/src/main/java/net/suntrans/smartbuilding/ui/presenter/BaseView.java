package net.suntrans.smartbuilding.ui.presenter;

import net.suntrans.smartbuilding.data.SceneEntity;

import java.util.List;

/**
 * Created by Administrator on 2017/8/14.
 */

public interface BaseView<T,D> {
    void setPresenter(T t);

    void showEmpty();

    void showLoading();

    void showRetry();

    void receiveWebSocketMsg(String msg);

    void showContent(List<D> datas);

}
