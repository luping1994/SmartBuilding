package net.suntrans.smartbuilding.ui.presenter.impl;

import net.suntrans.smartbuilding.data.MenuItemEntity;
import net.suntrans.smartbuilding.ui.presenter.MenuContract;
import net.suntrans.smartbuilding.utils.ListUtils;

import rx.Subscriber;

/**
 * Created by Administrator on 2017/8/14.
 */

public class MenuPresenter extends BasedPresenterImpl implements MenuContract.Presenter {

    private MenuContract.View mView;

    public MenuPresenter(MenuContract.View mView) {
        this.mView = mView;
        mView.setPresenter(this);
    }

    @Override
    public void loadData(String menuid) {
        mView.showLoading();
        addSubscription(api.getMenuItem(menuid), new Subscriber<MenuItemEntity>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                mView.showRetry();
            }

            @Override
            public void onNext(MenuItemEntity result) {
                if (ListUtils.isEmpty(result.getMenu())) {
                    mView.showEmpty();
                } else {
                    mView.showContent(result.getMenu());
                }
            }
        });
    }

    @Override
    public void start() {
    }
}
