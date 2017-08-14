package net.suntrans.smartbuilding.ui.presenter;
import net.suntrans.smartbuilding.data.SceneEntity;

import java.util.List;

/**
 * Created by Administrator on 2017/8/14.
 */
public interface SceneContract {
    interface View extends BaseView<Presenter> {
        void showContent(List<SceneEntity.Scene> datas);
    }

    interface Presenter extends BasePresenter {
        void loadData();
    }
}
