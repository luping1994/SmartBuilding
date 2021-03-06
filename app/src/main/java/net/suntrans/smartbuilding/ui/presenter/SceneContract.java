package net.suntrans.smartbuilding.ui.presenter;

import net.suntrans.smartbuilding.data.SceneEntity;

import java.util.List;

/**
 * Created by Administrator on 2017/8/14.
 * 我的区域->场景Contract
 */
public interface SceneContract {
    interface View extends BaseView<Presenter, SceneEntity.Scene> {
    }

    interface Presenter extends BasePresenter {

        /**
         * 加载数据
         */
        void loadData();

        /**
         * 执行场景
         *
         * @param sceneId
         * @return
         */
        boolean performSceneAction(String sceneId);
    }
}
