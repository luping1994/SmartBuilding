package net.suntrans.smartbuilding.data.source;

import net.suntrans.smartbuilding.data.MenuItemEntity;

import java.util.List;

/**
 * Created by Administrator on 2017/8/18.
 */

public interface MenuDataSource {

    interface LoadMenusCallBack {

        void onMenusLoaded(List<MenuItemEntity.MenuBean> menus);

        void onDataNotAvailable();
    }


    interface GetMenusCallback {

        void onMenusLoaded(List<MenuItemEntity.MenuBean> menu);

        void onDataNotAvailable();
    }

    void getSubMenus(String menuid);
}
