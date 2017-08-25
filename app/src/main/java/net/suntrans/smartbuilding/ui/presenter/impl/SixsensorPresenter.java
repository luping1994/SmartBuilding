package net.suntrans.smartbuilding.ui.presenter.impl;

import android.util.Log;

import net.suntrans.smartbuilding.Constant;
import net.suntrans.smartbuilding.data.SixSensor;
import net.suntrans.smartbuilding.ui.presenter.SixsensorContract;
import net.suntrans.smartbuilding.utils.ListUtils;
import net.suntrans.smartbuilding.utils.LogUtil;

import rx.Subscriber;

/**
 * Created by Lp on 2017/8/24.
 */

public class SixsensorPresenter extends BasedPresenterImpl implements SixsensorContract.Presenter {
    private SixsensorContract.View mView;
    private String type;
    private String areaid;

    public SixsensorPresenter(SixsensorContract.View mView, String type, String areaid) {
        this.mView = mView;
        this.mView.setPresenter(this);
        this.type = type;
        this.areaid = areaid;
    }

    @Override
    public void loadData() {
        mView.showLoading();
        if (type.equals(Constant.TYPE_MYAREA))
            addSubscription(api.getMyareaSixSensor(), new Subscriber<SixSensor>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {
                    e.printStackTrace();
                    mView.showRetry();
                }

                @Override
                public void onNext(SixSensor o) {
                    if (ListUtils.isEmpty(o.data)) {
                        mView.showEmpty();
                    } else {
                        mView.showContent(o.data);
                    }
                }
            });
        else if (type.equals(Constant.TYPE_GLOBAL))
            addSubscription(api.getGlobalSixSensor(areaid), new Subscriber<SixSensor>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {
                    e.printStackTrace();
                    mView.showRetry();
                }

                @Override
                public void onNext(SixSensor o) {
                    if (ListUtils.isEmpty(o.data)) {
                        mView.showEmpty();
                    } else {
                        mView.showContent(o.data);
                    }
                }
            });

    }

    @Override
    public void onItemClick(String deviceId) {

    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {
        onUnsubscribe();
    }
}
