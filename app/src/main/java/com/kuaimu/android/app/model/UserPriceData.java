package com.kuaimu.android.app.model;

import java.util.List;

public class UserPriceData {

    /**
     * code : 200
     * msg : 成功
     * data : [{"id":12,"purpose":2,"price":"0.01","desc":null,"created_at":"2020-08-31 17:27:10","updated_at":"2020-09-16 13:47:40","origin_price":"0.00","type":1},{"id":13,"purpose":1,"price":"0.02","desc":null,"created_at":"2020-08-31 17:27:45","updated_at":"2020-09-16 13:47:31","origin_price":"0.00","type":1}]
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
         * id : 12
         * purpose : 2
         * price : 0.01
         * desc : null
         * created_at : 2020-08-31 17:27:10
         * updated_at : 2020-09-16 13:47:40
         * origin_price : 0.00
         * type : 1
         */

        private int id;
        private int purpose;
        private String price;
        private Object desc;
        private String created_at;
        private String updated_at;
        private String origin_price;
        private int type;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getPurpose() {
            return purpose;
        }

        public void setPurpose(int purpose) {
            this.purpose = purpose;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public Object getDesc() {
            return desc;
        }

        public void setDesc(Object desc) {
            this.desc = desc;
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

        public String getOrigin_price() {
            return origin_price;
        }

        public void setOrigin_price(String origin_price) {
            this.origin_price = origin_price;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }
}
