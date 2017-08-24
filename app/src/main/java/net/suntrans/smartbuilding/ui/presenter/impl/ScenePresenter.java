package net.suntrans.smartbuilding.ui.presenter.impl;

import net.suntrans.smartbuilding.Constant;
import net.suntrans.smartbuilding.data.SceneEntity;
import net.suntrans.smartbuilding.ui.presenter.SceneContract;
import net.suntrans.smartbuilding.utils.ListUtils;

import rx.Subscriber;

/**
 * Created by Administrator on 2017/8/14.
 */

public class ScenePresenter extends BasedPresenterImpl implements SceneContract.Presenter {

    private SceneContract.View mView;
    private String type;
    private String areaid;

    public ScenePresenter(SceneContract.View mView, String type,String areaid) {
        this.mView = mView;
        mView.setPresenter(this);
        this.type = type;
        this.areaid = areaid;
    }

    @Override
    public void loadData() {
        mView.showLoading();
        if (type.equals(Constant.TYPE_MYAREA))
            addSubscription(api.getMyareaScene(), new Subscriber<SceneEntity>() {
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
                    if (ListUtils.isEmpty(o.getData())) {
                        mView.showEmpty();
                    } else {
                        mView.showContent(o.getData());
                    }
                }
            });
        else
            addSubscription(api.getGlobalScene(areaid), new Subscriber<SceneEntity>() {
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
                    if (ListUtils.isEmpty(o.getData())) {
                        mView.showEmpty();
                    } else {
                        mView.showContent(o.getData());
                    }
                }
            });
    }

    @Override
    public boolean performSceneAction(String sceneId) {
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
