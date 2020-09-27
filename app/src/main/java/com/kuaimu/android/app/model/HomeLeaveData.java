package com.kuaimu.android.app.model;

import java.util.List;

public class HomeLeaveData {

    /**
     * code : 200
     * msg : 成功
     * data : [{"id":95,"tourist_id":4,"created_at":"2020-09-10 14:54:45","updated_at":"2020-09-10 14:54:45","be_tourist_id":2,"content":"你是","parent_id":null,"tourist":{"id":4,"name":"sSxthv","phone":"13521614827","avatar":"upload/20200808111900DYxMk.jpg","created_at":"2020-08-06 08:27:21","updated_at":"2020-09-02 19:01:58","password":"$2y$10$0fdFMh97ICGkgGdzKxwtuO2nhwnEi/gT2Q9epNYmx6ore75f4KRKm","weixin_bind":2,"qq_bind":2,"qq":null,"weixin":null,"tourist_id":132131,"person_label":"天成云","bus_label":"解决方案","is_vip":1,"vip_type":2,"vip_time":"2021-09-02 18:29:14","apple_identifier":null,"apple_email":null,"ios_bind":2},"be_tourist":{"id":2,"name":"888888","phone":"15935964770","avatar":"upload/20200726045754HkGJ1.png","created_at":"2020-07-26 16:15:29","updated_at":"2020-09-09 14:42:32","password":"$2y$10$.Mj8htdKwsbJcnBmrmGzVe/KC/Mmw0JXuQSc980rHsWxZwaROMl5u","weixin_bind":2,"qq_bind":2,"qq":null,"weixin":null,"tourist_id":3213123,"person_label":"物联网","bus_label":"大数据","is_vip":1,"vip_type":2,"vip_time":"2021-09-09 14:42:32","apple_identifier":null,"apple_email":null,"ios_bind":2}},{"id":93,"tourist_id":4,"created_at":"2020-09-10 14:50:29","updated_at":"2020-09-10 14:50:29","be_tourist_id":2,"content":"雷子","parent_id":null,"tourist":{"id":4,"name":"sSxthv","phone":"13521614827","avatar":"upload/20200808111900DYxMk.jpg","created_at":"2020-08-06 08:27:21","updated_at":"2020-09-02 19:01:58","password":"$2y$10$0fdFMh97ICGkgGdzKxwtuO2nhwnEi/gT2Q9epNYmx6ore75f4KRKm","weixin_bind":2,"qq_bind":2,"qq":null,"weixin":null,"tourist_id":132131,"person_label":"天成云","bus_label":"解决方案","is_vip":1,"vip_type":2,"vip_time":"2021-09-02 18:29:14","apple_identifier":null,"apple_email":null,"ios_bind":2},"be_tourist":{"id":2,"name":"888888","phone":"15935964770","avatar":"upload/20200726045754HkGJ1.png","created_at":"2020-07-26 16:15:29","updated_at":"2020-09-09 14:42:32","password":"$2y$10$.Mj8htdKwsbJcnBmrmGzVe/KC/Mmw0JXuQSc980rHsWxZwaROMl5u","weixin_bind":2,"qq_bind":2,"qq":null,"weixin":null,"tourist_id":3213123,"person_label":"物联网","bus_label":"大数据","is_vip":1,"vip_type":2,"vip_time":"2021-09-09 14:42:32","apple_identifier":null,"apple_email":null,"ios_bind":2}},{"id":92,"tourist_id":2,"created_at":"2020-09-09 18:32:14","updated_at":"2020-09-09 18:32:14","be_tourist_id":4,"content":"好吧！","parent_id":null,"tourist":{"id":2,"name":"888888","phone":"15935964770","avatar":"upload/20200726045754HkGJ1.png","created_at":"2020-07-26 16:15:29","updated_at":"2020-09-09 14:42:32","password":"$2y$10$.Mj8htdKwsbJcnBmrmGzVe/KC/Mmw0JXuQSc980rHsWxZwaROMl5u","weixin_bind":2,"qq_bind":2,"qq":null,"weixin":null,"tourist_id":3213123,"person_label":"物联网","bus_label":"大数据","is_vip":1,"vip_type":2,"vip_time":"2021-09-09 14:42:32","apple_identifier":null,"apple_email":null,"ios_bind":2},"be_tourist":{"id":4,"name":"sSxthv","phone":"13521614827","avatar":"upload/20200808111900DYxMk.jpg","created_at":"2020-08-06 08:27:21","updated_at":"2020-09-02 19:01:58","password":"$2y$10$0fdFMh97ICGkgGdzKxwtuO2nhwnEi/gT2Q9epNYmx6ore75f4KRKm","weixin_bind":2,"qq_bind":2,"qq":null,"weixin":null,"tourist_id":132131,"person_label":"天成云","bus_label":"解决方案","is_vip":1,"vip_type":2,"vip_time":"2021-09-02 18:29:14","apple_identifier":null,"apple_email":null,"ios_bind":2}}]
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
         * id : 95
         * tourist_id : 4
         * created_at : 2020-09-10 14:54:45
         * updated_at : 2020-09-10 14:54:45
         * be_tourist_id : 2
         * content : 你是
         * parent_id : null
         * tourist : {"id":4,"name":"sSxthv","phone":"13521614827","avatar":"upload/20200808111900DYxMk.jpg","created_at":"2020-08-06 08:27:21","updated_at":"2020-09-02 19:01:58","password":"$2y$10$0fdFMh97ICGkgGdzKxwtuO2nhwnEi/gT2Q9epNYmx6ore75f4KRKm","weixin_bind":2,"qq_bind":2,"qq":null,"weixin":null,"tourist_id":132131,"person_label":"天成云","bus_label":"解决方案","is_vip":1,"vip_type":2,"vip_time":"2021-09-02 18:29:14","apple_identifier":null,"apple_email":null,"ios_bind":2}
         * be_tourist : {"id":2,"name":"888888","phone":"15935964770","avatar":"upload/20200726045754HkGJ1.png","created_at":"2020-07-26 16:15:29","updated_at":"2020-09-09 14:42:32","password":"$2y$10$.Mj8htdKwsbJcnBmrmGzVe/KC/Mmw0JXuQSc980rHsWxZwaROMl5u","weixin_bind":2,"qq_bind":2,"qq":null,"weixin":null,"tourist_id":3213123,"person_label":"物联网","bus_label":"大数据","is_vip":1,"vip_type":2,"vip_time":"2021-09-09 14:42:32","apple_identifier":null,"apple_email":null,"ios_bind":2}
         */

        private int id;
        private int tourist_id;
        private String created_at;
        private String updated_at;
        private int be_tourist_id;
        private String content;
        private Object parent_id;
        private TouristBean tourist;
        private BeTouristBean be_tourist;

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

        public int getBe_tourist_id() {
            return be_tourist_id;
        }

        public void setBe_tourist_id(int be_tourist_id) {
            this.be_tourist_id = be_tourist_id;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public Object getParent_id() {
            return parent_id;
        }

        public void setParent_id(Object parent_id) {
            this.parent_id = parent_id;
        }

        public TouristBean getTourist() {
            return tourist;
        }

        public void setTourist(TouristBean tourist) {
            this.tourist = tourist;
        }

        public BeTouristBean getBe_tourist() {
            return be_tourist;
        }

        public void setBe_tourist(BeTouristBean be_tourist) {
            this.be_tourist = be_tourist;
        }

        public static class TouristBean {
            /**
             * id : 4
             * name : sSxthv
             * phone : 13521614827
             * avatar : upload/20200808111900DYxMk.jpg
             * created_at : 2020-08-06 08:27:21
             * updated_at : 2020-09-02 19:01:58
             * password : $2y$10$0fdFMh97ICGkgGdzKxwtuO2nhwnEi/gT2Q9epNYmx6ore75f4KRKm
             * weixin_bind : 2
             * qq_bind : 2
             * qq : null
             * weixin : null
             * tourist_id : 132131
             * person_label : 天成云
             * bus_label : 解决方案
             * is_vip : 1
             * vip_type : 2
             * vip_time : 2021-09-02 18:29:14
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
            private String vip_time;
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
        }

        public static class BeTouristBean {
            /**
             * id : 2
             * name : 888888
             * phone : 15935964770
             * avatar : upload/20200726045754HkGJ1.png
             * created_at : 2020-07-26 16:15:29
             * updated_at : 2020-09-09 14:42:32
             * password : $2y$10$.Mj8htdKwsbJcnBmrmGzVe/KC/Mmw0JXuQSc980rHsWxZwaROMl5u
             * weixin_bind : 2
             * qq_bind : 2
             * qq : null
             * weixin : null
             * tourist_id : 3213123
             * person_label : 物联网
             * bus_label : 大数据
             * is_vip : 1
             * vip_type : 2
             * vip_time : 2021-09-09 14:42:32
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
            private String vip_time;
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
        }
    }
}
