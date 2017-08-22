package net.suntrans.smartbuilding.ui.presenter;

import net.suntrans.smartbuilding.data.AirConditionEntity;

/**
 * Created by Administrator on 2017/8/14.
 * 我的区域->空调Contract
 */
public interface AirContract {
    interface View extends BaseView<Presenter, AirConditionEntity.DataBean> {

    }

    interface Presenter extends BasePresenter {

        /**
         * 加载数据
         */
        void loadData();


    }
}
