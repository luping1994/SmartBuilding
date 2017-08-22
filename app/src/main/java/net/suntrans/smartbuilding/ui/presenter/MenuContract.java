package net.suntrans.smartbuilding.ui.presenter;

import net.suntrans.smartbuilding.data.MenuItemEntity;

import java.util.List;

/**
 * Created by Administrator on 2017/8/14.
 */

public interface MenuContract {
    interface View extends BaseView<Presenter,MenuItemEntity.MenuBean> {
    }

    interface Presenter extends BasePresenter {
        void loadData(String menuid);
    }
}
