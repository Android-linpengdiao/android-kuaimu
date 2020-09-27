package com.kuaimu.android.app.model;

import java.io.Serializable;

public class GoodDetail implements Serializable {

    /**
     * code : 200
     * msg : 成功
     * data : {"id":12,"name":"新产品","created_at":"2020-08-30 23:12:11","updated_at":"2020-08-30 23:12:11","desc":"新产品就是好","img":"upload/20200830111211Owicj.jpg","tourist_id":4}
     */

    private int code;
    private String msg;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean implements Serializable{
        /**
         * id : 12
         * name : 新产品
         * created_at : 2020-08-30 23:12:11
         * updated_at : 2020-08-30 23:12:11
         * desc : 新产品就是好
         * img : upload/20200830111211Owicj.jpg
         * tourist_id : 4
         */

        private int id;
        private String name;
        private String created_at;
        private String updated_at;
        private String desc;
        private String img;
        private int tourist_id;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public int getTourist_id() {
            return tourist_id;
        }

        public void setTourist_id(int tourist_id) {
            this.tourist_id = tourist_id;
        }
    }
}
