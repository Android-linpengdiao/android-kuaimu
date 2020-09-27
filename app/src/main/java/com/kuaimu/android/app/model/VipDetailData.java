package com.kuaimu.android.app.model;

public class VipDetailData {

    /**
     * code : 200
     * msg : 成功
     * data : {"id":18,"price":"0.01","created_at":"2020-09-02 18:29:07","updated_at":"2020-09-02 18:29:14","tourist_id":4,"pay_status":2,"pay_remark":"支付成功","trans_no":"202009021599042547358","pay_trans_no":null,"pay_type":2,"purpose":2,"name":"Android","idcard":"1234567890"}
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
         * id : 18
         * price : 0.01
         * created_at : 2020-09-02 18:29:07
         * updated_at : 2020-09-02 18:29:14
         * tourist_id : 4
         * pay_status : 2
         * pay_remark : 支付成功
         * trans_no : 202009021599042547358
         * pay_trans_no : null
         * pay_type : 2
         * purpose : 2
         * name : Android
         * idcard : 1234567890
         */

        private int id;
        private String price;
        private String created_at;
        private String updated_at;
        private int tourist_id;
        private int pay_status;
        private String pay_remark;
        private String trans_no;
        private Object pay_trans_no;
        private int pay_type;
        private int purpose;
        private String name;
        private String idcard;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
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

        public int getTourist_id() {
            return tourist_id;
        }

        public void setTourist_id(int tourist_id) {
            this.tourist_id = tourist_id;
        }

        public int getPay_status() {
            return pay_status;
        }

        public void setPay_status(int pay_status) {
            this.pay_status = pay_status;
        }

        public String getPay_remark() {
            return pay_remark;
        }

        public void setPay_remark(String pay_remark) {
            this.pay_remark = pay_remark;
        }

        public String getTrans_no() {
            return trans_no;
        }

        public void setTrans_no(String trans_no) {
            this.trans_no = trans_no;
        }

        public Object getPay_trans_no() {
            return pay_trans_no;
        }

        public void setPay_trans_no(Object pay_trans_no) {
            this.pay_trans_no = pay_trans_no;
        }

        public int getPay_type() {
            return pay_type;
        }

        public void setPay_type(int pay_type) {
            this.pay_type = pay_type;
        }

        public int getPurpose() {
            return purpose;
        }

        public void setPurpose(int purpose) {
            this.purpose = purpose;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getIdcard() {
            return idcard;
        }

        public void setIdcard(String idcard) {
            this.idcard = idcard;
        }
    }
}
