package net.suntrans.smartbuilding.ui.presenter.impl;
import net.suntrans.smartbuilding.data.SixSensor;
import net.suntrans.smartbuilding.ui.presenter.SixsensorContract;
import net.suntrans.smartbuilding.utils.ListUtils;
import rx.Subscriber;

/**
 * Created by Lp on 2017/8/24.
 */

public class SixsensorPresenter extends BasedPresenterImpl implements SixsensorContract.Presenter {
    private SixsensorContract.View mView;

    public SixsensorPresenter(SixsensorContract.View mView) {
        this.mView = mView;
        this.mView.setPresenter(this);
    }

    @Override
    public void loadData() {
        mView.showLoading();
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
                if (ListUtils.isEmpty(o.data)){
                    mView.showEmpty();
                }else {
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
