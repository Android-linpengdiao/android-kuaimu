package com.kuaimu.android.app.model;

import java.io.Serializable;

public class VideoBean implements Serializable {

    /**
     * id : 69
     * created_at : 2020-12-05 17:25:52
     * updated_at : 2020-12-05 17:25:52
     * tourist_id : 27
     * content_id : 51
     * content_tourist_id : 27
     * read : 2
     * tourist : {"id":27,"name":"安妮海瑟薇","phone":"13521614827","avatar":"upload/20201205042323qK8AO.jpg","password":"$2y$10$Dn9N0N7AYYJO/d7IWTjUC.O2x1e.NfubpXD0xo0J9DOMA..qL/.oa","remember_token":null,"created_at":"2020-11-08 12:54:44","updated_at":"2020-12-05 16:23:23","sex":1,"cancel":1,"wallet_token":0,"fan_number":0,"follow_number":0,"level":1,"age":0,"credit":100,"income_token":0,"tourist_id":"10358462"}
     * content : {"id":51,"created_at":"2020-11-08 20:58:49","updated_at":"2020-12-05 17:25:52","is_deleted":2,"tourist_id":27,"tourist_name":"LUSTOw","category_id":1,"category_name":"旅行","desc":"视频发布","img":"upload/20200922101026U4zjg.jpg","video":"http://oss-coffee.oss-cn-beijing.aliyuncs.com/1600679171274.mp4","relation_good":2,"good_name":"","good_link":"","good_img":"","assist_num":1,"comment_num":0,"addr":"北京市"}
     */

    private int id;
    private String created_at;
    private String updated_at;
    private int tourist_id;
    private int content_id;
    private int content_tourist_id;
    private int read;
    private TouristBean tourist;
    private ContentBean content;

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

    public int getTourist_id() {
        return tourist_id;
    }

    public void setTourist_id(int tourist_id) {
        this.tourist_id = tourist_id;
    }

    public int getContent_id() {
        return content_id;
    }

    public void setContent_id(int content_id) {
        this.content_id = content_id;
    }

    public int getContent_tourist_id() {
        return content_tourist_id;
    }

    public void setContent_tourist_id(int content_tourist_id) {
        this.content_tourist_id = content_tourist_id;
    }

    public int getRead() {
        return read;
    }

    public void setRead(int read) {
        this.read = read;
    }

    public TouristBean getTourist() {
        return tourist;
    }

    public void setTourist(TouristBean tourist) {
        this.tourist = tourist;
    }

    public ContentBean getContent() {
        return content;
    }

    public void setContent(ContentBean content) {
        this.content = content;
    }

    public static class TouristBean {
        /**
         * id : 27
         * name : 安妮海瑟薇
         * phone : 13521614827
         * avatar : upload/20201205042323qK8AO.jpg
         * password : $2y$10$Dn9N0N7AYYJO/d7IWTjUC.O2x1e.NfubpXD0xo0J9DOMA..qL/.oa
         * remember_token : null
         * created_at : 2020-11-08 12:54:44
         * updated_at : 2020-12-05 16:23:23
         * sex : 1
         * cancel : 1
         * wallet_token : 0
         * fan_number : 0
         * follow_number : 0
         * level : 1
         * age : 0
         * credit : 100
         * income_token : 0
         * tourist_id : 10358462
         */

        private int id;
        private String name;
        private String phone;
        private String avatar;
        private String password;
        private Object remember_token;
        private String created_at;
        private String updated_at;
        private int sex;
        private int cancel;
        private int wallet_token;
        private int fan_number;
        private int follow_number;
        private int level;
        private int age;
        private int credit;
        private int income_token;
        private String tourist_id;

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

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public Object getRemember_token() {
            return remember_token;
        }

        public void setRemember_token(Object remember_token) {
            this.remember_token = remember_token;
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

        public int getSex() {
            return sex;
        }

        public void setSex(int sex) {
            this.sex = sex;
        }

        public int getCancel() {
            return cancel;
        }

        public void setCancel(int cancel) {
            this.cancel = cancel;
        }

        public int getWallet_token() {
            return wallet_token;
        }

        public void setWallet_token(int wallet_token) {
            this.wallet_token = wallet_token;
        }

        public int getFan_number() {
            return fan_number;
        }

        public void setFan_number(int fan_number) {
            this.fan_number = fan_number;
        }

        public int getFollow_number() {
            return follow_number;
        }

        public void setFollow_number(int follow_number) {
            this.follow_number = follow_number;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public int getCredit() {
            return credit;
        }

        public void setCredit(int credit) {
            this.credit = credit;
        }

        public int getIncome_token() {
            return income_token;
        }

        public void setIncome_token(int income_token) {
            this.income_token = income_token;
        }

        public String getTourist_id() {
            return tourist_id;
        }

        public void setTourist_id(String tourist_id) {
            this.tourist_id = tourist_id;
        }
    }

    public static class ContentBean {
        /**
         * id : 51
         * created_at : 2020-11-08 20:58:49
         * updated_at : 2020-12-05 17:25:52
         * is_deleted : 2
         * tourist_id : 27
         * tourist_name : LUSTOw
         * category_id : 1
         * category_name : 旅行
         * desc : 视频发布
         * img : upload/20200922101026U4zjg.jpg
         * video : http://oss-coffee.oss-cn-beijing.aliyuncs.com/1600679171274.mp4
         * relation_good : 2
         * good_name :
         * good_link :
         * good_img :
         * assist_num : 1
         * comment_num : 0
         * addr : 北京市
         */

        private int id;
        private String created_at;
        private String updated_at;
        private int is_deleted;
        private int tourist_id;
        private String tourist_name;
        private int category_id;
        private String category_name;
        private String desc;
        private String img;
        private String video;
        private int relation_good;
        private String good_name;
        private String good_link;
        private String good_img;
        private int assist_num;
        private int comment_num;
        private String addr;

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

        public int getIs_deleted() {
            return is_deleted;
        }

        public void setIs_deleted(int is_deleted) {
            this.is_deleted = is_deleted;
        }

        public int getTourist_id() {
            return tourist_id;
        }

        public void setTourist_id(int tourist_id) {
            this.tourist_id = tourist_id;
        }

        public String getTourist_name() {
            return tourist_name;
        }

        public void setTourist_name(String tourist_name) {
            this.tourist_name = tourist_name;
        }

        public int getCategory_id() {
            return category_id;
        }

        public void setCategory_id(int category_id) {
            this.category_id = category_id;
        }

        public String getCategory_name() {
            return category_name;
        }

        public void setCategory_name(String category_name) {
            this.category_name = category_name;
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

        public String getVideo() {
            return video;
        }

        public void setVideo(String video) {
            this.video = video;
        }

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

        public int getAssist_num() {
            return assist_num;
        }

        public void setAssist_num(int assist_num) {
            this.assist_num = assist_num;
        }

        public int getComment_num() {
            return comment_num;
        }

        public void setComment_num(int comment_num) {
            this.comment_num = comment_num;
        }

        public String getAddr() {
            return addr;
        }

        public void setAddr(String addr) {
            this.addr = addr;
        }
    }
}
