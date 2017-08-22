package net.suntrans.smartbuilding.ui.presenter.impl;

import net.suntrans.smartbuilding.data.XenonEntity;
import net.suntrans.smartbuilding.ui.presenter.XenonContract;
import net.suntrans.smartbuilding.utils.ListUtils;

import rx.Subscriber;

/**
 * Created by Administrator on 2017/8/14.
 * 我的区域->氙气灯 Presenter
 */

public class XenonPresenter extends BasedPresenterImpl implements XenonContract.Presenter {

    private XenonContract.View mView;

    public XenonPresenter(XenonContract.View mView) {
        this.mView = mView;
        mView.setPresenter(this);
    }

    @Override
    public void loadData() {
        mView.showLoading();
        addSubscription(api.getMyareaXenon(), new Subscriber<XenonEntity>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                mView.showRetry();
            }

            @Override
            public void onNext(XenonEntity o) {
                if (ListUtils.isEmpty(o.data)){
                    mView.showEmpty();
                }else {
                    mView.showContent(o.data);
                }
            }
        });
    }

    @Override
    public void onItemClick(String sceneId) {

    }

    @Override
    public void start() {
    }

    @Override
    public void stop() {
        onUnsubscribe();
    }
}
