package net.suntrans.smartbuilding.data;

import java.util.List;

/**
 * Created by Administrator on 2017/8/9.
 */

public class MenuItemEntity {

    /**
     * status : 1
     * msg : 请求成功
     * menu : [{"li_id":"5","li_name":"我的区域","li_level":"2","li_parent":"1","li_order":"1","li_url":"MyArea","li_img":null,"li_status":"1"},{"li_id":"5","li_name":"我的区域","li_level":"2","li_parent":"1","li_order":"1","li_url":"MyArea","li_img":null,"li_status":"1"},{"li_id":"6","li_name":"参观展示","li_level":"2","li_parent":"1","li_order":"2","li_url":"#","li_img":null,"li_status":"1"},{"li_id":"7","li_name":"设备运维","li_level":"2","li_parent":"1","li_order":"3","li_url":"#","li_img":null,"li_status":"1"}]
     */

    private int status;
    private String msg;
    private List<MenuBean> menu;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<MenuBean> getMenu() {
        return menu;
    }

    public void setMenu(List<MenuBean> menu) {
        this.menu = menu;
    }

    public static class MenuBean {
        /**
         * li_id : 5
         * li_name : 我的区域
         * li_level : 2
         * li_parent : 1
         * li_order : 1
         * li_url : MyArea
         * li_img : null
         * li_status : 1
         */

        private String li_id;
        private String li_name;
        private String li_level;
        private String li_parent;
        private String li_order;
        private String li_url;
        private Object li_img;
        private String li_status;

        public String getLi_id() {
            return li_id;
        }

        public void setLi_id(String li_id) {
            this.li_id = li_id;
        }

        public String getLi_name() {
            return li_name;
        }

        public void setLi_name(String li_name) {
            this.li_name = li_name;
        }

        public String getLi_level() {
            return li_level;
        }

        public void setLi_level(String li_level) {
            this.li_level = li_level;
        }

        public String getLi_parent() {
            return li_parent;
        }

        public void setLi_parent(String li_parent) {
            this.li_parent = li_parent;
        }

        public String getLi_order() {
            return li_order;
        }

        public void setLi_order(String li_order) {
            this.li_order = li_order;
        }

        public String getLi_url() {
            return li_url;
        }

        public void setLi_url(String li_url) {
            this.li_url = li_url;
        }

        public Object getLi_img() {
            return li_img;
        }

        public void setLi_img(Object li_img) {
            this.li_img = li_img;
        }

        public String getLi_status() {
            return li_status;
        }

        public void setLi_status(String li_status) {
            this.li_status = li_status;
        }
    }
}
