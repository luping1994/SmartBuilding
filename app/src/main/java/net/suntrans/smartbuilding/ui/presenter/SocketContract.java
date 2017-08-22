package net.suntrans.smartbuilding.ui.presenter;

import net.suntrans.smartbuilding.data.SocketEntity;

/**
 * Created by Administrator on 2017/8/14.
 * 我的区域->照明Contract
 */
public interface SocketContract {
    interface View extends BaseView<Presenter, SocketEntity.DataBean> {
    }

    interface Presenter extends BasePresenter {

        /**
         * 加载数据
         */
        void loadData();

        /**
         * 执行设备开/关 动作
         *
         * @param deviceId 设备id
         * @return
         */
        boolean performSwitchAction(String deviceId);
    }
}
