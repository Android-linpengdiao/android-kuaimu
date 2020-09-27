package com.baselibrary;

import java.io.Serializable;

public class UserInfo implements Serializable {

    /**
     * code : 200
     * msg : 成功
     * data : {"id":2,"name":"888888","phone":"15935964770","avatar":"upload/20200726045754HkGJ1.png","created_at":"2020-07-26 16:15:29","updated_at":"2020-07-26 16:57:57","password":"$2y$10$.Mj8htdKwsbJcnBmrmGzVe/KC/Mmw0JXuQSc980rHsWxZwaROMl5u","weixin_bind":2,"qq_bind":2,"qq":null,"weixin":null,"tourist_id":3213123,"person_label":"物联网","bus_label":"大数据","is_vip":2,"vip_type":0,"vip_time":null,"apple_identifier":null,"apple_email":null,"ios_bind":2,"attention_num":0,"follower_num":2,"good_num":1,"video_num":7,"like_video_num":3}
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
         * id : 2
         * name : 888888
         * phone : 15935964770
         * avatar : upload/20200726045754HkGJ1.png
         * created_at : 2020-07-26 16:15:29
         * updated_at : 2020-07-26 16:57:57
         * password : $2y$10$.Mj8htdKwsbJcnBmrmGzVe/KC/Mmw0JXuQSc980rHsWxZwaROMl5u
         * weixin_bind : 2
         * qq_bind : 2
         * qq : null
         * weixin : null
         * tourist_id : 3213123
         * person_label : 物联网
         * bus_label : 大数据
         * is_vip : 2
         * vip_type : 0
         * vip_time : null
         * apple_identifier : null
         * apple_email : null
         * ios_bind : 2
         * attention_num : 0
         * follower_num : 2
         * good_num : 1
         * video_num : 7
         * like_video_num : 3
         */

        private int id;
        private String name;
        private String phone;
        private String avatar;
        private String created_at;
        private String updated_at;
        private String password;
        private int weixin_bind;
        private int qq_bind;
        private String qq;
        private String weixin;
        private int tourist_id;
        private String person_label;
        private String bus_label;
        private int is_vip;
        private int vip_type;
        private String vip_time;
        private Object apple_identifier;
        private Object apple_email;
        private int ios_bind;
        private int attention_num;
        private int follower_num;
        private int good_num;
        private int video_num;
        private int like_video_num;

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

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
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

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public int getWeixin_bind() {
            return weixin_bind;
        }

        public void setWeixin_bind(int weixin_bind) {
            this.weixin_bind = weixin_bind;
        }

        public int getQq_bind() {
            return qq_bind;
        }

        public void setQq_bind(int qq_bind) {
            this.qq_bind = qq_bind;
        }

        public String getQq() {
            return qq;
        }

        public void setQq(String qq) {
            this.qq = qq;
        }

        public String getWeixin() {
            return weixin;
        }

        public void setWeixin(String weixin) {
            this.weixin = weixin;
        }

        public int getTourist_id() {
            return tourist_id;
        }

        public void setTourist_id(int tourist_id) {
            this.tourist_id = tourist_id;
        }

        public String getPerson_label() {
            return person_label;
        }

        public void setPerson_label(String person_label) {
            this.person_label = person_label;
        }

        public String getBus_label() {
            return bus_label;
        }

        public void setBus_label(String bus_label) {
            this.bus_label = bus_label;
        }

        public int getIs_vip() {
            return is_vip;
        }

        public void setIs_vip(int is_vip) {
            this.is_vip = is_vip;
        }

        public int getVip_type() {
            return vip_type;
        }

        public void setVip_type(int vip_type) {
            this.vip_type = vip_type;
        }

        public String getVip_time() {
            return vip_time;
        }

        public void setVip_time(String vip_time) {
            this.vip_time = vip_time;
        }

        public Object getApple_identifier() {
            return apple_identifier;
        }

        public void setApple_identifier(Object apple_identifier) {
            this.apple_identifier = apple_identifier;
        }

        public Object getApple_email() {
            return apple_email;
        }

        public void setApple_email(Object apple_email) {
            this.apple_email = apple_email;
        }

        public int getIos_bind() {
            return ios_bind;
        }

        public void setIos_bind(int ios_bind) {
            this.ios_bind = ios_bind;
        }

        public int getAttention_num() {
            return attention_num;
        }

        public void setAttention_num(int attention_num) {
            this.attention_num = attention_num;
        }

        public int getFollower_num() {
            return follower_num;
        }

        public void setFollower_num(int follower_num) {
            this.follower_num = follower_num;
        }

        public int getGood_num() {
            return good_num;
        }

        public void setGood_num(int good_num) {
            this.good_num = good_num;
        }

        public int getVideo_num() {
            return video_num;
        }

        public void setVideo_num(int video_num) {
            this.video_num = video_num;
        }

        public int getLike_video_num() {
            return like_video_num;
        }

        public void setLike_video_num(int like_video_num) {
            this.like_video_num = like_video_num;
        }
    }
}
