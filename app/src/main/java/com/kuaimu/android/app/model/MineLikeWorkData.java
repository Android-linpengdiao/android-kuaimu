package com.kuaimu.android.app.model;

import java.io.Serializable;
import java.util.List;

public class MineLikeWorkData implements Serializable {

    /**
     * code : 200
     * msg : 成功
     * data : {"current_page":1,"data":[{"id":69,"created_at":"2020-12-05 17:25:52","updated_at":"2020-12-05 17:25:52","tourist_id":27,"content_id":51,"content_tourist_id":27,"read":2,"tourist":{"id":27,"name":"安妮海瑟薇","phone":"13521614827","avatar":"upload/20201205042323qK8AO.jpg","password":"$2y$10$Dn9N0N7AYYJO/d7IWTjUC.O2x1e.NfubpXD0xo0J9DOMA..qL/.oa","remember_token":null,"created_at":"2020-11-08 12:54:44","updated_at":"2020-12-05 16:23:23","sex":1,"cancel":1,"wallet_token":0,"fan_number":0,"follow_number":0,"level":1,"age":0,"credit":100,"income_token":0,"tourist_id":"10358462"},"content":{"id":51,"created_at":"2020-11-08 20:58:49","updated_at":"2020-12-05 17:25:52","is_deleted":2,"tourist_id":27,"tourist_name":"LUSTOw","category_id":1,"category_name":"旅行","desc":"视频发布","img":"upload/20200922101026U4zjg.jpg","video":"http://oss-coffee.oss-cn-beijing.aliyuncs.com/1600679171274.mp4","relation_good":2,"good_name":"","good_link":"","good_img":"","assist_num":1,"comment_num":0,"addr":"北京市"}}],"first_page_url":"http://quickeye.fengyunguoyuan.com/api/message/assist?page=1","from":1,"last_page":1,"last_page_url":"http://quickeye.fengyunguoyuan.com/api/message/assist?page=1","next_page_url":null,"path":"http://quickeye.fengyunguoyuan.com/api/message/assist","per_page":10,"prev_page_url":null,"to":1,"total":1}
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
         * data : [{"id":69,"created_at":"2020-12-05 17:25:52","updated_at":"2020-12-05 17:25:52","tourist_id":27,"content_id":51,"content_tourist_id":27,"read":2,"tourist":{"id":27,"name":"安妮海瑟薇","phone":"13521614827","avatar":"upload/20201205042323qK8AO.jpg","password":"$2y$10$Dn9N0N7AYYJO/d7IWTjUC.O2x1e.NfubpXD0xo0J9DOMA..qL/.oa","remember_token":null,"created_at":"2020-11-08 12:54:44","updated_at":"2020-12-05 16:23:23","sex":1,"cancel":1,"wallet_token":0,"fan_number":0,"follow_number":0,"level":1,"age":0,"credit":100,"income_token":0,"tourist_id":"10358462"},"content":{"id":51,"created_at":"2020-11-08 20:58:49","updated_at":"2020-12-05 17:25:52","is_deleted":2,"tourist_id":27,"tourist_name":"LUSTOw","category_id":1,"category_name":"旅行","desc":"视频发布","img":"upload/20200922101026U4zjg.jpg","video":"http://oss-coffee.oss-cn-beijing.aliyuncs.com/1600679171274.mp4","relation_good":2,"good_name":"","good_link":"","good_img":"","assist_num":1,"comment_num":0,"addr":"北京市"}}]
         * first_page_url : http://quickeye.fengyunguoyuan.com/api/message/assist?page=1
         * from : 1
         * last_page : 1
         * last_page_url : http://quickeye.fengyunguoyuan.com/api/message/assist?page=1
         * next_page_url : null
         * path : http://quickeye.fengyunguoyuan.com/api/message/assist
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
        private List<VideoBean> data;

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

        public List<VideoBean> getData() {
            return data;
        }

        public void setData(List<VideoBean> data) {
            this.data = data;
        }
    }
}
