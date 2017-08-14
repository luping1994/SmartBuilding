package net.suntrans.smartbuilding.ui.presenter;

/**
 * Created by Administrator on 2017/8/14.
 */

public interface BaseView<T> {
    void setPresenter(T t);

    void showEmpty();

    void showLoading();

    void showRetry();

}
