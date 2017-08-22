package net.suntrans.smartbuilding.ui.presenter;

import net.suntrans.smartbuilding.data.XenonEntity;

/**
 * Created by Administrator on 2017/8/14.
 * 我的区域->氙气灯Contract
 */
public interface XenonContract {
    interface View extends BaseView<Presenter, XenonEntity.DataBean> {
    }

    interface Presenter extends BasePresenter {

        /**
         * 加载数据
         */
        void loadData();

        /**
         *
         * @param sceneId
         * @return
         */
        void onItemClick(String sceneId);
    }
}
