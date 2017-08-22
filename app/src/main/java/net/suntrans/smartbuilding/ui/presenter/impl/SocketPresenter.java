package net.suntrans.smartbuilding.ui.presenter.impl;

import net.suntrans.smartbuilding.data.LightEntity;
import net.suntrans.smartbuilding.data.SocketEntity;
import net.suntrans.smartbuilding.ui.presenter.LightContract;
import net.suntrans.smartbuilding.ui.presenter.SocketContract;
import net.suntrans.smartbuilding.utils.ListUtils;

import rx.Subscriber;

/**
 * Created by Administrator on 2017/8/14.
 */

public class SocketPresenter extends BasedPresenterImpl implements SocketContract.Presenter {

    private SocketContract.View mView;

    public SocketPresenter(SocketContract.View mView) {
        this.mView = mView;
        mView.setPresenter(this);
    }

    @Override
    public void loadData() {
        mView.showLoading();
        addSubscription(api.getMyareaSocket(), new Subscriber<SocketEntity>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                mView.showRetry();
            }

            @Override
            public void onNext(SocketEntity o) {
                if (ListUtils.isEmpty(o.data)){
                    mView.showEmpty();
                }else {
                    mView.showContent(o.data);
                }
            }
        });
    }

    @Override
    public boolean performSwitchAction(String deviceId) {
        return false;
    }


    @Override
    public void start() {

    }

    @Override
    public void stop() {
        onUnsubscribe();
    }

}
