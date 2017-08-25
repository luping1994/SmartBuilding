package net.suntrans.smartbuilding.ui.presenter.impl;

import net.suntrans.smartbuilding.Constant;
import net.suntrans.smartbuilding.data.LightEntity;
import net.suntrans.smartbuilding.data.SceneEntity;
import net.suntrans.smartbuilding.ui.presenter.LightContract;
import net.suntrans.smartbuilding.ui.presenter.SceneContract;
import net.suntrans.smartbuilding.utils.ListUtils;

import rx.Subscriber;

/**
 * Created by Administrator on 2017/8/14.
 */

public class LightPresenter extends BasedPresenterImpl implements LightContract.Presenter {

    private LightContract.View mView;
    private String type;
    private String areaid;

    public LightPresenter(LightContract.View mView, String type, String areaid) {
        this.mView = mView;
        mView.setPresenter(this);
        this.type = type;
        this.areaid = areaid;
    }

    @Override
    public void loadData() {
        mView.showLoading();
        if (type .equals(Constant.TYPE_MYAREA))
        addSubscription(api.getMyareaLight(), new Subscriber<LightEntity>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                mView.showRetry();
            }

            @Override
            public void onNext(LightEntity o) {
                if (ListUtils.isEmpty(o.getData())) {
                    mView.showEmpty();
                } else {
                    mView.showContent(o.getData());
                }
            }
        });
        else if (type.equals(Constant.TYPE_GLOBAL))
            addSubscription(api.getGlobalLight(areaid), new Subscriber<LightEntity>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {
                    e.printStackTrace();
                    mView.showRetry();
                }

                @Override
                public void onNext(LightEntity o) {
                    if (ListUtils.isEmpty(o.getData())) {
                        mView.showEmpty();
                    } else {
                        mView.showContent(o.getData());
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
