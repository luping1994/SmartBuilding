package net.suntrans.smartbuilding.ui.presenter;

import net.suntrans.smartbuilding.data.SixSensor;

/**
 * Created by Administrator on 2017/8/14.
 * 我的区域->照明Contract
 */
public interface SixsensorContract {
    interface View extends BaseView<Presenter, SixSensor.DataBean> {

    }

    interface Presenter extends BasePresenter {

        /**
         * 加载数据
         */
        void loadData();

        /**
         * @param deviceId 设备id
         * @return
         */
        void onItemClick(String deviceId);
    }
}
