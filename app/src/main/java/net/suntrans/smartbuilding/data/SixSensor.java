package net.suntrans.smartbuilding.data;

import java.util.List;

/**
 * Created by Administrator on 2017/8/22.
 */

public class SixSensor {

    /**
     * status : 1
     * msg : 请求成功
     * data : [{"id":"83","user_id":"1","de_areaid":"6","name":"第六感","area_name":null,"sort":"0","title":"展厅第六感","vtype":"6100","addr":"84000000","addr_link":null,"client_id":null,"port":null,"is_online":"1","client_ip":"120.236.173.254","updated_at":"2017-08-16 02:37:15","status":"1","create_time":null},{"id":"84","user_id":"1","de_areaid":"6","name":"第六感","area_name":null,"sort":"0","title":"员工办公区右第六感","vtype":"6100","addr":"16000000","addr_link":null,"client_id":null,"port":null,"is_online":"1","client_ip":"120.236.173.254","updated_at":"2017-08-16 02:37:17","status":"1","create_time":null}]
     */

    public int status;
    public String msg;
    public List<DataBean> data;

    public static class DataBean {
        /**
         * id : 83
         * user_id : 1
         * de_areaid : 6
         * name : 第六感
         * area_name : null
         * sort : 0
         * title : 展厅第六感
         * vtype : 6100
         * addr : 84000000
         * addr_link : null
         * client_id : null
         * port : null
         * is_online : 1
         * client_ip : 120.236.173.254
         * updated_at : 2017-08-16 02:37:15
         * status : 1
         * create_time : null
         */

        public String id;
        public String user_id;
        public String de_areaid;
        public String name;
        public Object area_name;
        public String sort;
        public String title;
        public String vtype;
        public String addr;
        public Object addr_link;
        public Object client_id;
        public Object port;
        public String is_online;
        public String client_ip;
        public String updated_at;
        public String status;
        public Object create_time;
    }
}
