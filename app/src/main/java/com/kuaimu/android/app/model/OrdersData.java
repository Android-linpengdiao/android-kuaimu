package com.kuaimu.android.app.model;

import java.util.List;

public class OrdersData {

    /**
     * code : 200
     * msg : 成功
     * data : {"current_page":1,"data":[{"id":7,"price":"0.01","created_at":"2020-12-13 17:05:47","updated_at":"2020-12-13 17:05:47","tourist_id":28,"trans_no":"202012131607850340898","type":1,"trans_type":2,"wallet_record":60,"content_id":null,"content_tourist_id":null,"extend":null}],"first_page_url":"https://kuaimutj.com/api/wallet/record?page=1","from":1,"last_page":1,"last_page_url":"https://kuaimutj.com/api/wallet/record?page=1","next_page_url":null,"path":"https://kuaimutj.com/api/wallet/record","per_page":10,"prev_page_url":null,"to":1,"total":1}
     */

    private int code;
    private String msg;
    private DataBeanX data;

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

    public DataBeanX getData() {
        return data;
    }

    public void setData(DataBeanX data) {
        this.data = data;
    }

    public static class DataBeanX {
        /**
         * current_page : 1
         * data : [{"id":7,"price":"0.01","created_at":"2020-12-13 17:05:47","updated_at":"2020-12-13 17:05:47","tourist_id":28,"trans_no":"202012131607850340898","type":1,"trans_type":2,"wallet_record":60,"content_id":null,"content_tourist_id":null,"extend":null}]
         * first_page_url : https://kuaimutj.com/api/wallet/record?page=1
         * from : 1
         * last_page : 1
         * last_page_url : https://kuaimutj.com/api/wallet/record?page=1
         * next_page_url : null
         * path : https://kuaimutj.com/api/wallet/record
         * per_page : 10
         * prev_page_url : null
         * to : 1
         * total : 1
         */

        private int current_page;
        private String first_page_url;
        private int from;
        private int last_page;
        private String last_page_url;
        private Object next_page_url;
        private String path;
        private int per_page;
        private Object prev_page_url;
        private int to;
        private int total;
        private List<DataBean> data;

        public int getCurrent_page() {
            return current_page;
        }

        public void setCurrent_page(int current_page) {
            this.current_page = current_page;
        }

        public String getFirst_page_url() {
            return first_page_url;
        }

        public void setFirst_page_url(String first_page_url) {
            this.first_page_url = first_page_url;
        }

        public int getFrom() {
            return from;
        }

        public void setFrom(int from) {
            this.from = from;
        }

        public int getLast_page() {
            return last_page;
        }

        public void setLast_page(int last_page) {
            this.last_page = last_page;
        }

        public String getLast_page_url() {
            return last_page_url;
        }

        public void setLast_page_url(String last_page_url) {
            this.last_page_url = last_page_url;
        }

        public Object getNext_page_url() {
            return next_page_url;
        }

        public void setNext_page_url(Object next_page_url) {
            this.next_page_url = next_page_url;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public int getPer_page() {
            return per_page;
        }

        public void setPer_page(int per_page) {
            this.per_page = per_page;
        }

        public Object getPrev_page_url() {
            return prev_page_url;
        }

        public void setPrev_page_url(Object prev_page_url) {
            this.prev_page_url = prev_page_url;
        }

        public int getTo() {
            return to;
        }

        public void setTo(int to) {
            this.to = to;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * id : 7
             * price : 0.01
             * created_at : 2020-12-13 17:05:47
             * updated_at : 2020-12-13 17:05:47
             * tourist_id : 28
             * trans_no : 202012131607850340898
             * type : 1
             * trans_type : 2
             * wallet_record : 60
             * content_id : null
             * content_tourist_id : null
             * extend : null
             */

            private int id;
            private String price;
            private String created_at;
            private String updated_at;
            private int tourist_id;
            private String trans_no;
            private int type;
            private int trans_type;
            private int wallet_record;
            private Object content_id;
            private Object content_tourist_id;
            private Object extend;

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

            public String getTrans_no() {
                return trans_no;
            }

            public void setTrans_no(String trans_no) {
                this.trans_no = trans_no;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public int getTrans_type() {
                return trans_type;
            }

            public void setTrans_type(int trans_type) {
                this.trans_type = trans_type;
            }

            public int getWallet_record() {
                return wallet_record;
            }

            public void setWallet_record(int wallet_record) {
                this.wallet_record = wallet_record;
            }

            public Object getContent_id() {
                return content_id;
            }

            public void setContent_id(Object content_id) {
                this.content_id = content_id;
            }

            public Object getContent_tourist_id() {
                return content_tourist_id;
            }

            public void setContent_tourist_id(Object content_tourist_id) {
                this.content_tourist_id = content_tourist_id;
            }

            public Object getExtend() {
                return extend;
            }

            public void setExtend(Object extend) {
                this.extend = extend;
            }
        }
    }
}
