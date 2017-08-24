package net.suntrans.smartbuilding.data;

import java.util.List;

/**
 * Created by Lp on 2017/8/24.
 */

public class AreaEntity {
    /**
     * status : 1
     * msg : 请求成功
     * data : [{"ar_id":"18","ar_name":"管理区","ar_level":"2","ar_parent":"1","ar_order":"1","ar_url":"#","ar_img":null,"ar_status":"1"},{"ar_id":"3","ar_name":"员工区","ar_level":"2","ar_parent":"1","ar_order":"2","ar_url":"MyArea","ar_img":null,"ar_status":"1"},{"ar_id":"20","ar_name":"实验区","ar_level":"2","ar_parent":"1","ar_order":"3","ar_url":"#","ar_img":null,"ar_status":"1"},{"ar_id":"21","ar_name":"生产区","ar_level":"2","ar_parent":"1","ar_order":"4","ar_url":"#","ar_img":null,"ar_status":"1"},{"ar_id":"4","ar_name":"会议室","ar_level":"2","ar_parent":"1","ar_order":"5","ar_url":"MyArea","ar_img":null,"ar_status":"1"},{"ar_id":"19","ar_name":"公共区域","ar_level":"2","ar_parent":"1","ar_order":"7","ar_url":"#","ar_img":null,"ar_status":"1"}]
     */

    public int status;
    public String msg;
    public List<DataBean> data;

    public static class DataBean {
        /**
         * ar_id : 18
         * ar_name : 管理区
         * ar_level : 2
         * ar_parent : 1
         * ar_order : 1
         * ar_url : #
         * ar_img : null
         * ar_status : 1
         */
        public String ar_id;
        public String ar_name;
        public String ar_level;
        public String ar_parent;
        public String ar_order;
        public String ar_url;
        public Object ar_img;
        public String ar_status;
    }
}
