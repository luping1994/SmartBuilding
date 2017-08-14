package net.suntrans.smartbuilding.ui.presenter.impl;

import net.suntrans.smartbuilding.Constant;
import net.suntrans.smartbuilding.data.MenuItemEntity;
import net.suntrans.smartbuilding.data.SceneEntity;
import net.suntrans.smartbuilding.ui.presenter.MenuContract;
import net.suntrans.smartbuilding.ui.presenter.SceneContract;
import net.suntrans.smartbuilding.utils.ListUtils;

import rx.Subscriber;

/**
 * Created by Administrator on 2017/8/14.
 */

public class ScenePresenter extends BasedPresenterImpl implements SceneContract.Presenter {

    private SceneContract.View mView;

    public ScenePresenter(SceneContract.View mView) {
        this.mView = mView;
        mView.setPresenter(this);
    }

    @Override
    public void loadData() {
        mView.showLoading();
        addSubscription(api.getMyAreaDetail("1"), new Subscriber<SceneEntity>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                mView.showRetry();
            }

            @Override
            public void onNext(SceneEntity o) {
                if (ListUtils.isEmpty(o.getData())){
                    mView.showEmpty();
                }else {
                    mView.showContent(o.getData());
                }
            }
        });
    }

    @Override
    public void start() {
    }
}
