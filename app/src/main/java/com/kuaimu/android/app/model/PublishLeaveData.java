package com.kuaimu.android.app.model;

public class PublishLeaveData {


    /**
     * code : 200
     * msg : 成功
     * data : {"tourist_id":"4","be_tourist_id":"2","content":"飒飒","updated_at":"2020-08-30 22:54:58","created_at":"2020-08-30 22:54:58","id":64}
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

    public static class DataBean {
        /**
         * tourist_id : 4
         * be_tourist_id : 2
         * content : 飒飒
         * updated_at : 2020-08-30 22:54:58
         * created_at : 2020-08-30 22:54:58
         * id : 64
         */

        private int tourist_id;
        private int be_tourist_id;
        private String content;
        private String updated_at;
        private String created_at;
        private int id;

        public int getTourist_id() {
            return tourist_id;
        }

        public void setTourist_id(int tourist_id) {
            this.tourist_id = tourist_id;
        }

        public int getBe_tourist_id() {
            return be_tourist_id;
        }

        public void setBe_tourist_id(int be_tourist_id) {
            this.be_tourist_id = be_tourist_id;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
