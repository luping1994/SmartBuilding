package net.suntrans.smartbuilding.data;

import java.util.List;

/**
 * Created by Administrator on 2017/8/14.
 */

public class SceneEntity {

    /**
     * status : 1
     * msg : 请求成功
     * data : [{"id":"37","user_id":"2","name":"睡觉","name_en":null,"house_id":"6","img_url":null,"img_id":"223","sort":"0","status":"1","created_at":"2017-08-09 20:36:42","updates_at":"2017-08-09 20:36:42","deleted_at":null},{"id":"38","user_id":"2","name":"起床","name_en":null,"house_id":"6","img_url":null,"img_id":"225","sort":"0","status":"1","created_at":"2017-08-09 20:36:44","updates_at":"2017-08-09 20:36:44","deleted_at":null},{"id":"39","user_id":"2","name":"离家","name_en":null,"house_id":"6","img_url":null,"img_id":"226","sort":"0","status":"1","created_at":"2017-08-09 20:36:46","updates_at":"2017-08-09 20:36:46","deleted_at":null},{"id":"40","user_id":"4","name":"会议室全开","name_en":null,"house_id":"6","img_url":null,"img_id":"230","sort":"0","status":"1","created_at":"2017-08-09 20:37:36","updates_at":"2017-08-09 20:37:36","deleted_at":null}]
     */

    private int status;
    private String msg;
    private List<Scene> data;

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

    public List<Scene> getData() {
        return data;
    }

    public void setData(List<Scene> data) {
        this.data = data;
    }

    public static class Scene {
        /**
         * id : 37
         * user_id : 2
         * name : 睡觉
         * name_en : null
         * house_id : 6
         * img_url : null
         * img_id : 223
         * sort : 0
         * status : 1
         * created_at : 2017-08-09 20:36:42
         * updates_at : 2017-08-09 20:36:42
         * deleted_at : null
         */

        private String id;
        private String user_id;
        private String name;
        private Object name_en;
        private String house_id;
        private String img_url;
        private String img_id;
        private String sort;
        private String status;
        private String created_at;
        private String updates_at;
        private Object deleted_at;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUser_id() {
            return user_id;
        }

        public void setUser_id(String user_id) {
            this.user_id = user_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Object getName_en() {
            return name_en;
        }

        public void setName_en(Object name_en) {
            this.name_en = name_en;
        }

        public String getHouse_id() {
            return house_id;
        }

        public void setHouse_id(String house_id) {
            this.house_id = house_id;
        }

        public String getImg_url() {
            return img_url;
        }

        public void setImg_url(String img_url) {
            this.img_url = img_url;
        }

        public String getImg_id() {
            return img_id;
        }

        public void setImg_id(String img_id) {
            this.img_id = img_id;
        }

        public String getSort() {
            return sort;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getUpdates_at() {
            return updates_at;
        }

        public void setUpdates_at(String updates_at) {
            this.updates_at = updates_at;
        }

        public Object getDeleted_at() {
            return deleted_at;
        }

        public void setDeleted_at(Object deleted_at) {
            this.deleted_at = deleted_at;
        }
    }
}
