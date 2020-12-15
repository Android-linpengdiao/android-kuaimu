package com.kuaimu.android.app.model;

import java.io.Serializable;

public class VideoDataBean implements Serializable {

    /**
     * id : 3
     * tourist_id : 2
     * desc : 这么多年都会过去
     * img : upload/20200730104449AeOkc.png
     * created_at : 2020-07-30 10:44:51
     * updated_at : 2020-09-02 10:52:58
     * video : upload/20200730104451fkiGM.mp4
     * assist_num : 0
     * sort : 9999
     * is_deleted : 1
     * bus_label : null
     * addr : null
     * is_assist : true
     * comment_num : 1
     * like_num : 1
     * share_num : 0
     * tourist : {"id":2,"name":"888888","phone":"15935964770","avatar":"upload/20200726045754HkGJ1.png","created_at":"2020-07-26 16:15:29","updated_at":"2020-07-26 16:57:57","password":"$2y$10$.Mj8htdKwsbJcnBmrmGzVe/KC/Mmw0JXuQSc980rHsWxZwaROMl5u","weixin_bind":2,"qq_bind":2,"qq":null,"weixin":null,"tourist_id":3213123,"person_label":"物联网","bus_label":"大数据","is_vip":2,"vip_type":0,"vip_time":null,"apple_identifier":null,"apple_email":null,"ios_bind":2}
     */

    private int id;
    private int tourist_id;
    private String desc;
    private String img;
    private String created_at;
    private String updated_at;
    private String video;
    private int assist_num;
    private int sort;
    private int is_deleted;
    private Object bus_label;
    private Object addr;
    private boolean is_liker;
    private boolean is_assist;
    private int comment_num;
    private int like_num;
    private int share_num;
    private TouristBean tourist;
    private boolean is_adv;
    private int browse_num;
    private boolean is_person_follow;
    private int relation_good;
    private String good_name;
    private String good_link;
    private String good_img;

    public int getRelation_good() {
        return relation_good;
    }

    public void setRelation_good(int relation_good) {
        this.relation_good = relation_good;
    }

    public String getGood_name() {
        return good_name;
    }

    public void setGood_name(String good_name) {
        this.good_name = good_name;
    }

    public String getGood_link() {
        return good_link;
    }

    public void setGood_link(String good_link) {
        this.good_link = good_link;
    }

    public String getGood_img() {
        return good_img;
    }

    public void setGood_img(String good_img) {
        this.good_img = good_img;
    }

    public int getBrowse_num() {
        return browse_num;
    }

    public void setBrowse_num(int browse_num) {
        this.browse_num = browse_num;
    }

    public boolean isIs_person_follow() {
        return is_person_follow;
    }

    public void setIs_person_follow(boolean is_person_follow) {
        this.is_person_follow = is_person_follow;
    }

    public boolean isIs_adv() {
        return is_adv;
    }

    public void setIs_adv(boolean is_adv) {
        this.is_adv = is_adv;
    }

    public boolean isIs_liker() {
        return is_liker;
    }

    public void setIs_liker(boolean is_liker) {
        this.is_liker = is_liker;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTourist_id() {
        return tourist_id;
    }

    public void setTourist_id(int tourist_id) {
        this.tourist_id = tourist_id;
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

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public int getAssist_num() {
        return assist_num;
    }

    public void setAssist_num(int assist_num) {
        this.assist_num = assist_num;
    }

    public int getSort() {
        return sort;
    }

    public void setSort(int sort) {
        this.sort = sort;
    }

    public int getIs_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(int is_deleted) {
        this.is_deleted = is_deleted;
    }

    public Object getBus_label() {
        return bus_label;
    }

    public void setBus_label(Object bus_label) {
        this.bus_label = bus_label;
    }

    public Object getAddr() {
        return addr;
    }

    public void setAddr(Object addr) {
        this.addr = addr;
    }

    public boolean isIs_assist() {
        return is_assist;
    }

    public void setIs_assist(boolean is_assist) {
        this.is_assist = is_assist;
    }

    public int getComment_num() {
        return comment_num;
    }

    public void setComment_num(int comment_num) {
        this.comment_num = comment_num;
    }

    public int getLike_num() {
        return like_num;
    }

    public void setLike_num(int like_num) {
        this.like_num = like_num;
    }

    public int getShare_num() {
        return share_num;
    }

    public void setShare_num(int share_num) {
        this.share_num = share_num;
    }

    public TouristBean getTourist() {
        return tourist;
    }

    public void setTourist(TouristBean tourist) {
        this.tourist = tourist;
    }

    public static class TouristBean implements Serializable{
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
        private Object qq;
        private Object weixin;
        private int tourist_id;
        private String person_label;
        private String bus_label;
        private int is_vip;
        private int vip_type;
        private Object vip_time;
        private Object apple_identifier;
        private Object apple_email;
        private int ios_bind;

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

        public Object getQq() {
            return qq;
        }

        public void setQq(Object qq) {
            this.qq = qq;
        }

        public Object getWeixin() {
            return weixin;
        }

        public void setWeixin(Object weixin) {
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

        public Object getVip_time() {
            return vip_time;
        }

        public void setVip_time(Object vip_time) {
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
    }
}
