package net.suntrans.smartbuilding.data;

import java.util.List;

/**
 * Created by Administrator on 2017/8/10.
 */

public class XenonEntity {

    /**
     * status : 1
     * msg : 请求成功
     * data : [{"id":"1","name":"氙气灯","addr":"212121","luraid":"111","status":null,"luraaddr":"00000155","luraaddr_link":null}]
     */

    public int status;
    public String msg;
    public List<DataBean> data;

    public static class DataBean {
        /**
         * id : 1
         * name : 氙气灯
         * addr : 212121
         * luraid : 111
         * status : null
         * luraaddr : 00000155
         * luraaddr_link : null
         */

        public String id;
        public String name;
        public String addr;
        public String luraid;
        public Object status;
        public String luraaddr;
        public Object luraaddr_link;
    }
}
