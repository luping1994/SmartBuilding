package net.suntrans.smartbuilding.data;

import java.util.List;

/**
 * Created by Administrator on 2017/8/22.
 */

public class SocketEntity {


    /**
     * status : 1
     * msg : 请求成功
     * data : [{"id":"2","dev_id":"62","number":"2","device_type":"2","name":"插座","group_id":"1","addr":"5c0001","xenon_show":"0","img_id":"0","cl_i":"","status":"0","cl_control":"1","create_at":"2017-08-07 22:25:12","update_at":"2017-08-08 20:26:42"},{"id":"5","dev_id":"62","number":"5","device_type":"2","name":"插座2","group_id":"2","addr":"5c0001","xenon_show":"0","img_id":"0","cl_i":"","status":"0","cl_control":"1","create_at":"2017-08-07 22:25:12","update_at":"2017-08-16 20:05:50"},{"id":"6","dev_id":"62","number":"6","device_type":"2","name":"插座3","group_id":"0","addr":"5c0001","xenon_show":"0","img_id":"0","cl_i":"","status":"0","cl_control":"1","create_at":"2017-08-07 22:25:12","update_at":"2017-08-08 20:26:44"}]
     */

    public int status;
    public String msg;
    public List<DataBean> data;

    public static class DataBean {
        /**
         * id : 2
         * dev_id : 62
         * number : 2
         * device_type : 2
         * name : 插座
         * group_id : 1
         * addr : 5c0001
         * xenon_show : 0
         * img_id : 0
         * cl_i :
         * status : 0
         * cl_control : 1
         * create_at : 2017-08-07 22:25:12
         * update_at : 2017-08-08 20:26:42
         */

        public String id;
        public String dev_id;
        public String number;
        public String device_type;
        public String name;
        public String group_id;
        public String addr;
        public String xenon_show;
        public String img_id;
        public String cl_i;
        public String status;
        public String cl_control;
        public String create_at;
        public String update_at;
    }
}
