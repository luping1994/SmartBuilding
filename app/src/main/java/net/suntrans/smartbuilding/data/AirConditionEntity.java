package net.suntrans.smartbuilding.data;

import java.util.List;

/**
 * Created by Administrator on 2017/8/10.
 */

public class AirConditionEntity {


    /**
     * status : 1
     * msg : 请求成功
     * data : [{"id":"2","name":"空调哒哒哒","addr":"1010101","dlgid":"83","status":"1","dlgaddr":"84000000","dlgaddr_link":null},{"id":"1","name":"空调","addr":"1010101","dlgid":"83","status":"1","dlgaddr":"84000000","dlgaddr_link":null}]
     */

    public int status;
    public String msg;
    public List<DataBean> data;

    public static class DataBean {
        /**
         * id : 2
         * name : 空调哒哒哒
         * addr : 1010101
         * dlgid : 83
         * status : 1
         * dlgaddr : 84000000
         * dlgaddr_link : null
         */
        public String id;
        public String name;
        public String addr;
        public String dlgid;
        public String status;
        public String dlgaddr;
        public Object dlgaddr_link;
    }
}
