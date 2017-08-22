package net.suntrans.smartbuilding.ui.presenter.impl;

import net.suntrans.smartbuilding.data.AirConditionEntity;
import net.suntrans.smartbuilding.data.LightEntity;
import net.suntrans.smartbuilding.ui.presenter.AirContract;
import net.suntrans.smartbuilding.ui.presenter.LightContract;
import net.suntrans.smartbuilding.utils.ListUtils;

import rx.Subscriber;

/**
 * Created by Administrator on 2017/8/14.
 * 我的区域->空调 Presenter
 */

public class AirPresenter extends BasedPresenterImpl implements AirContract.Presenter {

    private AirContract.View mView;

    public AirPresenter(AirContract.View mView) {
        this.mView = mView;
        mView.setPresenter(this);
    }

    @Override
    public void loadData() {
        mView.showLoading();
        addSubscription(api.getMyareaAir(), new Subscriber<AirConditionEntity>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                mView.showRetry();
            }

            @Override
            public void onNext(AirConditionEntity o) {
                if (ListUtils.isEmpty(o.data)){
                    mView.showEmpty();
                }else {
                    mView.showContent(o.data);
                }
            }
        });
    }

    @Override
    public void start() {
    }
    @Override
    public void stop() {
        onUnsubscribe();
    }

}
