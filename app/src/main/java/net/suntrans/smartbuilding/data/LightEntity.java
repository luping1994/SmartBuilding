package net.suntrans.smartbuilding.data;

import java.util.List;

/**
 * Created by Administrator on 2017/8/10.
 */

public class LightEntity {


    /**
     * status : 1
     * msg : 请求成功
     * data : [{"id":"1","dev_id":"62","number":"1","device_type":"1","name":"照明","group_id":"1","addr":"5c0001","xenon_show":"0","img_id":"areaimg/light/light_","cl_i":"","status":"0","cl_control":"1","create_at":"2017-08-07 22:25:12","update_at":"2017-08-15 22:06:59"},{"id":"7","dev_id":"62","number":"7","device_type":"1","name":"照明1","group_id":"0","addr":"5c0001","xenon_show":"0","img_id":"areaimg/light/light_","cl_i":"","status":"0","cl_control":"1","create_at":"2017-08-07 22:25:12","update_at":"2017-08-15 22:09:56"}]
     */

    private int status;
    private String msg;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1
         * dev_id : 62
         * number : 1
         * device_type : 1
         * name : 照明
         * group_id : 1
         * addr : 5c0001
         * xenon_show : 0
         * img_id : areaimg/light/light_
         * cl_i :
         * status : 0
         * cl_control : 1
         * create_at : 2017-08-07 22:25:12
         * update_at : 2017-08-15 22:06:59
         */

        private String id;
        private String dev_id;
        private String number;
        private String device_type;
        private String name;
        private String group_id;
        private String addr;
        private String xenon_show;
        private String img_id;
        private String cl_i;
        private String status;
        private String cl_control;
        private String create_at;
        private String update_at;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getDev_id() {
            return dev_id;
        }

        public void setDev_id(String dev_id) {
            this.dev_id = dev_id;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getDevice_type() {
            return device_type;
        }

        public void setDevice_type(String device_type) {
            this.device_type = device_type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getGroup_id() {
            return group_id;
        }

        public void setGroup_id(String group_id) {
            this.group_id = group_id;
        }

        public String getAddr() {
            return addr;
        }

        public void setAddr(String addr) {
            this.addr = addr;
        }

        public String getXenon_show() {
            return xenon_show;
        }

        public void setXenon_show(String xenon_show) {
            this.xenon_show = xenon_show;
        }

        public String getImg_id() {
            return img_id;
        }

        public void setImg_id(String img_id) {
            this.img_id = img_id;
        }

        public String getCl_i() {
            return cl_i;
        }

        public void setCl_i(String cl_i) {
            this.cl_i = cl_i;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getCl_control() {
            return cl_control;
        }

        public void setCl_control(String cl_control) {
            this.cl_control = cl_control;
        }

        public String getCreate_at() {
            return create_at;
        }

        public void setCreate_at(String create_at) {
            this.create_at = create_at;
        }

        public String getUpdate_at() {
            return update_at;
        }

        public void setUpdate_at(String update_at) {
            this.update_at = update_at;
        }
    }
}
