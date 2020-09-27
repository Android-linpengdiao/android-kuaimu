package com.kuaimu.android.app.model;

import java.io.Serializable;
import java.util.List;

public class FollowData implements Serializable {

    /**
     * code : 200
     * msg : 成功
     * data : {"current_page":1,"data":[{"id":2,"tourist_id":1,"desc":"18322233491","img":"ce","created_at":"2020-07-09 11:33:00","updated_at":"2020-07-09 14:38:06","video":"123","assist_num":0,"sort":0,"is_deleted":1,"bus_label":null,"addr":null,"tourist":{"id":1,"name":"QWpX8E","phone":"18322233491","avatar":"users/default.png","created_at":"2020-07-09 10:40:45","updated_at":"2020-07-09 11:17:00","password":"$2y$10$utyjNAlYqrU9VnHIxOFWMO2aWqAA2tstYsn5ANQmsCg/klCk96QFa","weixin_bind":1,"qq_bind":2,"qq":"qq_11","weixin":"","tourist_id":12313,"person_label":null,"bus_label":null,"is_vip":2,"vip_type":0,"vip_time":null,"apple_identifier":null,"apple_email":null,"ios_bind":2}}],"first_page_url":"http://kalao500q.com/api/home/selfAttention?page=1","from":1,"last_page":1,"last_page_url":"http://kalao500q.com/api/home/selfAttention?page=1","next_page_url":null,"path":"http://kalao500q.com/api/home/selfAttention","per_page":10,"prev_page_url":null,"to":1,"total":1}
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

    public static class DataBeanX implements Serializable{
        /**
         * current_page : 1
         * data : [{"id":2,"tourist_id":1,"desc":"18322233491","img":"ce","created_at":"2020-07-09 11:33:00","updated_at":"2020-07-09 14:38:06","video":"123","assist_num":0,"sort":0,"is_deleted":1,"bus_label":null,"addr":null,"tourist":{"id":1,"name":"QWpX8E","phone":"18322233491","avatar":"users/default.png","created_at":"2020-07-09 10:40:45","updated_at":"2020-07-09 11:17:00","password":"$2y$10$utyjNAlYqrU9VnHIxOFWMO2aWqAA2tstYsn5ANQmsCg/klCk96QFa","weixin_bind":1,"qq_bind":2,"qq":"qq_11","weixin":"","tourist_id":12313,"person_label":null,"bus_label":null,"is_vip":2,"vip_type":0,"vip_time":null,"apple_identifier":null,"apple_email":null,"ios_bind":2}}]
         * first_page_url : http://kalao500q.com/api/home/selfAttention?page=1
         * from : 1
         * last_page : 1
         * last_page_url : http://kalao500q.com/api/home/selfAttention?page=1
         * next_page_url : null
         * path : http://kalao500q.com/api/home/selfAttention
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
        private List<VideoDataBean> data;

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

        public List<VideoDataBean> getData() {
            return data;
        }

        public void setData(List<VideoDataBean> data) {
            this.data = data;
        }
    }
}
