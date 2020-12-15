package com.kuaimu.android.app.model;

import java.util.List;

public class LevelData {

    /**
     * code : 200
     * msg : 成功
     * data : [{"id":1,"created_at":"2020-05-25 13:41:00","updated_at":"2020-08-02 18:17:33","title":"倔强青铜","wallet_token":10},{"id":2,"created_at":"2020-07-29 16:50:01","updated_at":"2020-07-29 16:50:01","title":"不屈白银","wallet_token":200},{"id":3,"created_at":"2020-07-29 16:50:24","updated_at":"2020-07-29 16:50:24","title":"荣耀黄金","wallet_token":520},{"id":4,"created_at":"2020-07-29 16:50:56","updated_at":"2020-07-29 16:50:56","title":"华贵铂金","wallet_token":680},{"id":5,"created_at":"2020-07-29 16:51:14","updated_at":"2020-07-29 16:51:14","title":"璀璨砖石","wallet_token":880},{"id":6,"created_at":"2020-07-29 16:51:31","updated_at":"2020-07-29 16:51:31","title":"最强王者","wallet_token":1880}]
     */

    private int code;
    private String msg;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1
         * created_at : 2020-05-25 13:41:00
         * updated_at : 2020-08-02 18:17:33
         * title : 倔强青铜
         * wallet_token : 10
         */

        private int id;
        private String created_at;
        private String updated_at;
        private String title;
        private int wallet_token;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getWallet_token() {
            return wallet_token;
        }

        public void setWallet_token(int wallet_token) {
            this.wallet_token = wallet_token;
        }
    }
}
