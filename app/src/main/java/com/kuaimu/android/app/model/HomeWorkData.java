package com.kuaimu.android.app.model;

import java.io.Serializable;
import java.util.List;

public class HomeWorkData implements Serializable {

    /**
     * code : 200
     * msg : 成功
     * data : {"current_page":1,"data":[{"id":14,"tourist_id":4,"desc":"北京北京","img":"upload/20200903061402qO345.jpg","created_at":"2020-09-03 18:14:04","updated_at":"2020-09-03 18:14:04","video":"http://oss-coffee.oss-cn-beijing.aliyuncs.com/VID_20200120_181457.mp4","assist_num":0,"sort":9999,"is_deleted":2,"bus_label":"物联网","addr":"北京市","tourist":{"id":4,"name":"sSxthv","phone":"13521614827","avatar":"upload/20200808111900DYxMk.jpg","created_at":"2020-08-06 08:27:21","updated_at":"2020-09-10 15:30:33","password":"$2y$10$m6XTuydE98mhuX2nVDvireMwf/LYSHEBjxU8pomI.3kwARcp1arhq","weixin_bind":2,"qq_bind":2,"qq":null,"weixin":null,"tourist_id":132131,"person_label":"天成云","bus_label":"解决方案","is_vip":1,"vip_type":2,"vip_time":"2021-09-02 18:29:14","apple_identifier":null,"apple_email":null,"ios_bind":2}}],"first_page_url":"http://kalao500q.com/api/home/city?page=1","from":1,"last_page":1,"last_page_url":"http://kalao500q.com/api/home/city?page=1","next_page_url":null,"path":"http://kalao500q.com/api/home/city","per_page":10,"prev_page_url":null,"to":1,"total":1}
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
         * data : [{"id":14,"tourist_id":4,"desc":"北京北京","img":"upload/20200903061402qO345.jpg","created_at":"2020-09-03 18:14:04","updated_at":"2020-09-03 18:14:04","video":"http://oss-coffee.oss-cn-beijing.aliyuncs.com/VID_20200120_181457.mp4","assist_num":0,"sort":9999,"is_deleted":2,"bus_label":"物联网","addr":"北京市","tourist":{"id":4,"name":"sSxthv","phone":"13521614827","avatar":"upload/20200808111900DYxMk.jpg","created_at":"2020-08-06 08:27:21","updated_at":"2020-09-10 15:30:33","password":"$2y$10$m6XTuydE98mhuX2nVDvireMwf/LYSHEBjxU8pomI.3kwARcp1arhq","weixin_bind":2,"qq_bind":2,"qq":null,"weixin":null,"tourist_id":132131,"person_label":"天成云","bus_label":"解决方案","is_vip":1,"vip_type":2,"vip_time":"2021-09-02 18:29:14","apple_identifier":null,"apple_email":null,"ios_bind":2}}]
         * first_page_url : http://kalao500q.com/api/home/city?page=1
         * from : 1
         * last_page : 1
         * last_page_url : http://kalao500q.com/api/home/city?page=1
         * next_page_url : null
         * path : http://kalao500q.com/api/home/city
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
