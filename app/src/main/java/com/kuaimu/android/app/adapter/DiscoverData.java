package com.kuaimu.android.app.adapter;

import java.util.List;

public class DiscoverData {

    /**
     * code : 200
     * msg : 成功
     * data : {"current_page":1,"data":[{"id":17,"created_at":"2020-07-09 14:01:46","updated_at":"2020-07-09 14:01:46","img":"2","desc":"好视频","tourist_id":1,"is_liker":false,"tourist":{"id":1,"name":"QWpX8E","phone":"18322233491","avatar":"users/default.png","created_at":"2020-07-09 10:40:45","updated_at":"2020-07-09 11:17:00","password":"$2y$10$utyjNAlYqrU9VnHIxOFWMO2aWqAA2tstYsn5ANQmsCg/klCk96QFa","weixin_bind":1,"qq_bind":2,"qq":"qq_11","weixin":"","tourist_id":null,"person_label":null,"bus_label":null},"discover_comment":[{"id":2,"content":"好","created_at":"2020-07-09 14:10:54","updated_at":"2020-07-09 14:10:54","tourist_id":1,"discover_id":17,"tourist":{"id":1,"name":"QWpX8E","phone":"18322233491","avatar":"users/default.png","created_at":"2020-07-09 10:40:45","updated_at":"2020-07-09 11:17:00","password":"$2y$10$utyjNAlYqrU9VnHIxOFWMO2aWqAA2tstYsn5ANQmsCg/klCk96QFa","weixin_bind":1,"qq_bind":2,"qq":"qq_11","weixin":"","tourist_id":null,"person_label":null,"bus_label":null}},{"id":3,"content":"可以来一个","created_at":"2020-08-08 16:44:31","updated_at":"2020-08-08 16:44:31","tourist_id":4,"discover_id":17,"tourist":{"id":4,"name":"sSxthv","phone":"13521614827","avatar":"upload/20200808111900DYxMk.jpg","created_at":"2020-08-06 08:27:21","updated_at":"2020-08-08 23:48:35","password":"$2y$10$0fdFMh97ICGkgGdzKxwtuO2nhwnEi/gT2Q9epNYmx6ore75f4KRKm","weixin_bind":2,"qq_bind":2,"qq":null,"weixin":null,"tourist_id":null,"person_label":"大数据","bus_label":"天成云"}},{"id":4,"content":"叽叽叽叽精明李金红","created_at":"2020-08-08 16:45:04","updated_at":"2020-08-08 16:45:04","tourist_id":4,"discover_id":17,"tourist":{"id":4,"name":"sSxthv","phone":"13521614827","avatar":"upload/20200808111900DYxMk.jpg","created_at":"2020-08-06 08:27:21","updated_at":"2020-08-08 23:48:35","password":"$2y$10$0fdFMh97ICGkgGdzKxwtuO2nhwnEi/gT2Q9epNYmx6ore75f4KRKm","weixin_bind":2,"qq_bind":2,"qq":null,"weixin":null,"tourist_id":null,"person_label":"大数据","bus_label":"天成云"}},{"id":5,"content":"啦啦啦","created_at":"2020-08-08 16:49:27","updated_at":"2020-08-08 16:49:27","tourist_id":4,"discover_id":17,"tourist":{"id":4,"name":"sSxthv","phone":"13521614827","avatar":"upload/20200808111900DYxMk.jpg","created_at":"2020-08-06 08:27:21","updated_at":"2020-08-08 23:48:35","password":"$2y$10$0fdFMh97ICGkgGdzKxwtuO2nhwnEi/gT2Q9epNYmx6ore75f4KRKm","weixin_bind":2,"qq_bind":2,"qq":null,"weixin":null,"tourist_id":null,"person_label":"大数据","bus_label":"天成云"}}]}]}
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
         * data : [{"id":17,"created_at":"2020-07-09 14:01:46","updated_at":"2020-07-09 14:01:46","img":"2","desc":"好视频","tourist_id":1,"is_liker":false,"tourist":{"id":1,"name":"QWpX8E","phone":"18322233491","avatar":"users/default.png","created_at":"2020-07-09 10:40:45","updated_at":"2020-07-09 11:17:00","password":"$2y$10$utyjNAlYqrU9VnHIxOFWMO2aWqAA2tstYsn5ANQmsCg/klCk96QFa","weixin_bind":1,"qq_bind":2,"qq":"qq_11","weixin":"","tourist_id":null,"person_label":null,"bus_label":null},"discover_comment":[{"id":2,"content":"好","created_at":"2020-07-09 14:10:54","updated_at":"2020-07-09 14:10:54","tourist_id":1,"discover_id":17,"tourist":{"id":1,"name":"QWpX8E","phone":"18322233491","avatar":"users/default.png","created_at":"2020-07-09 10:40:45","updated_at":"2020-07-09 11:17:00","password":"$2y$10$utyjNAlYqrU9VnHIxOFWMO2aWqAA2tstYsn5ANQmsCg/klCk96QFa","weixin_bind":1,"qq_bind":2,"qq":"qq_11","weixin":"","tourist_id":null,"person_label":null,"bus_label":null}},{"id":3,"content":"可以来一个","created_at":"2020-08-08 16:44:31","updated_at":"2020-08-08 16:44:31","tourist_id":4,"discover_id":17,"tourist":{"id":4,"name":"sSxthv","phone":"13521614827","avatar":"upload/20200808111900DYxMk.jpg","created_at":"2020-08-06 08:27:21","updated_at":"2020-08-08 23:48:35","password":"$2y$10$0fdFMh97ICGkgGdzKxwtuO2nhwnEi/gT2Q9epNYmx6ore75f4KRKm","weixin_bind":2,"qq_bind":2,"qq":null,"weixin":null,"tourist_id":null,"person_label":"大数据","bus_label":"天成云"}},{"id":4,"content":"叽叽叽叽精明李金红","created_at":"2020-08-08 16:45:04","updated_at":"2020-08-08 16:45:04","tourist_id":4,"discover_id":17,"tourist":{"id":4,"name":"sSxthv","phone":"13521614827","avatar":"upload/20200808111900DYxMk.jpg","created_at":"2020-08-06 08:27:21","updated_at":"2020-08-08 23:48:35","password":"$2y$10$0fdFMh97ICGkgGdzKxwtuO2nhwnEi/gT2Q9epNYmx6ore75f4KRKm","weixin_bind":2,"qq_bind":2,"qq":null,"weixin":null,"tourist_id":null,"person_label":"大数据","bus_label":"天成云"}},{"id":5,"content":"啦啦啦","created_at":"2020-08-08 16:49:27","updated_at":"2020-08-08 16:49:27","tourist_id":4,"discover_id":17,"tourist":{"id":4,"name":"sSxthv","phone":"13521614827","avatar":"upload/20200808111900DYxMk.jpg","created_at":"2020-08-06 08:27:21","updated_at":"2020-08-08 23:48:35","password":"$2y$10$0fdFMh97ICGkgGdzKxwtuO2nhwnEi/gT2Q9epNYmx6ore75f4KRKm","weixin_bind":2,"qq_bind":2,"qq":null,"weixin":null,"tourist_id":null,"person_label":"大数据","bus_label":"天成云"}}]}]
         */

        private int current_page;
        private List<DataBean> data;

        public int getCurrent_page() {
            return current_page;
        }

        public void setCurrent_page(int current_page) {
            this.current_page = current_page;
        }

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * id : 17
             * created_at : 2020-07-09 14:01:46
             * updated_at : 2020-07-09 14:01:46
             * img : 2
             * desc : 好视频
             * tourist_id : 1
             * is_liker : false
             * tourist : {"id":1,"name":"QWpX8E","phone":"18322233491","avatar":"users/default.png","created_at":"2020-07-09 10:40:45","updated_at":"2020-07-09 11:17:00","password":"$2y$10$utyjNAlYqrU9VnHIxOFWMO2aWqAA2tstYsn5ANQmsCg/klCk96QFa","weixin_bind":1,"qq_bind":2,"qq":"qq_11","weixin":"","tourist_id":null,"person_label":null,"bus_label":null}
             * discover_comment : [{"id":2,"content":"好","created_at":"2020-07-09 14:10:54","updated_at":"2020-07-09 14:10:54","tourist_id":1,"discover_id":17,"tourist":{"id":1,"name":"QWpX8E","phone":"18322233491","avatar":"users/default.png","created_at":"2020-07-09 10:40:45","updated_at":"2020-07-09 11:17:00","password":"$2y$10$utyjNAlYqrU9VnHIxOFWMO2aWqAA2tstYsn5ANQmsCg/klCk96QFa","weixin_bind":1,"qq_bind":2,"qq":"qq_11","weixin":"","tourist_id":null,"person_label":null,"bus_label":null}},{"id":3,"content":"可以来一个","created_at":"2020-08-08 16:44:31","updated_at":"2020-08-08 16:44:31","tourist_id":4,"discover_id":17,"tourist":{"id":4,"name":"sSxthv","phone":"13521614827","avatar":"upload/20200808111900DYxMk.jpg","created_at":"2020-08-06 08:27:21","updated_at":"2020-08-08 23:48:35","password":"$2y$10$0fdFMh97ICGkgGdzKxwtuO2nhwnEi/gT2Q9epNYmx6ore75f4KRKm","weixin_bind":2,"qq_bind":2,"qq":null,"weixin":null,"tourist_id":null,"person_label":"大数据","bus_label":"天成云"}},{"id":4,"content":"叽叽叽叽精明李金红","created_at":"2020-08-08 16:45:04","updated_at":"2020-08-08 16:45:04","tourist_id":4,"discover_id":17,"tourist":{"id":4,"name":"sSxthv","phone":"13521614827","avatar":"upload/20200808111900DYxMk.jpg","created_at":"2020-08-06 08:27:21","updated_at":"2020-08-08 23:48:35","password":"$2y$10$0fdFMh97ICGkgGdzKxwtuO2nhwnEi/gT2Q9epNYmx6ore75f4KRKm","weixin_bind":2,"qq_bind":2,"qq":null,"weixin":null,"tourist_id":null,"person_label":"大数据","bus_label":"天成云"}},{"id":5,"content":"啦啦啦","created_at":"2020-08-08 16:49:27","updated_at":"2020-08-08 16:49:27","tourist_id":4,"discover_id":17,"tourist":{"id":4,"name":"sSxthv","phone":"13521614827","avatar":"upload/20200808111900DYxMk.jpg","created_at":"2020-08-06 08:27:21","updated_at":"2020-08-08 23:48:35","password":"$2y$10$0fdFMh97ICGkgGdzKxwtuO2nhwnEi/gT2Q9epNYmx6ore75f4KRKm","weixin_bind":2,"qq_bind":2,"qq":null,"weixin":null,"tourist_id":null,"person_label":"大数据","bus_label":"天成云"}}]
             */

            private int id;
            private String created_at;
            private String updated_at;
            private String img;
            private String desc;
            private int tourist_id;
            private boolean is_liker;
            private TouristBean tourist;
            private List<DiscoverCommentBean> discover_comment;

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

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getDesc() {
                return desc;
            }

            public void setDesc(String desc) {
                this.desc = desc;
            }

            public int getTourist_id() {
                return tourist_id;
            }

            public void setTourist_id(int tourist_id) {
                this.tourist_id = tourist_id;
            }

            public boolean isIs_liker() {
                return is_liker;
            }

            public void setIs_liker(boolean is_liker) {
                this.is_liker = is_liker;
            }

            public TouristBean getTourist() {
                return tourist;
            }

            public void setTourist(TouristBean tourist) {
                this.tourist = tourist;
            }

            public List<DiscoverCommentBean> getDiscover_comment() {
                return discover_comment;
            }

            public void setDiscover_comment(List<DiscoverCommentBean> discover_comment) {
                this.discover_comment = discover_comment;
            }

            public static class TouristBean {
                /**
                 * id : 1
                 * name : QWpX8E
                 * phone : 18322233491
                 * avatar : users/default.png
                 * created_at : 2020-07-09 10:40:45
                 * updated_at : 2020-07-09 11:17:00
                 * password : $2y$10$utyjNAlYqrU9VnHIxOFWMO2aWqAA2tstYsn5ANQmsCg/klCk96QFa
                 * weixin_bind : 1
                 * qq_bind : 2
                 * qq : qq_11
                 * weixin :
                 * tourist_id : null
                 * person_label : null
                 * bus_label : null
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
                private Object tourist_id;
                private Object person_label;
                private Object bus_label;

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

                public Object getTourist_id() {
                    return tourist_id;
                }

                public void setTourist_id(Object tourist_id) {
                    this.tourist_id = tourist_id;
                }

                public Object getPerson_label() {
                    return person_label;
                }

                public void setPerson_label(Object person_label) {
                    this.person_label = person_label;
                }

                public Object getBus_label() {
                    return bus_label;
                }

                public void setBus_label(Object bus_label) {
                    this.bus_label = bus_label;
                }
            }

            public static class DiscoverCommentBean {
                /**
                 * id : 2
                 * content : 好
                 * created_at : 2020-07-09 14:10:54
                 * updated_at : 2020-07-09 14:10:54
                 * tourist_id : 1
                 * discover_id : 17
                 * tourist : {"id":1,"name":"QWpX8E","phone":"18322233491","avatar":"users/default.png","created_at":"2020-07-09 10:40:45","updated_at":"2020-07-09 11:17:00","password":"$2y$10$utyjNAlYqrU9VnHIxOFWMO2aWqAA2tstYsn5ANQmsCg/klCk96QFa","weixin_bind":1,"qq_bind":2,"qq":"qq_11","weixin":"","tourist_id":null,"person_label":null,"bus_label":null}
                 */

                private int id;
                private String content;
                private String created_at;
                private String updated_at;
                private int tourist_id;
                private int discover_id;
                private TouristBeanX tourist;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getContent() {
                    return content;
                }

                public void setContent(String content) {
                    this.content = content;
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

                public int getDiscover_id() {
                    return discover_id;
                }

                public void setDiscover_id(int discover_id) {
                    this.discover_id = discover_id;
                }

                public TouristBeanX getTourist() {
                    return tourist;
                }

                public void setTourist(TouristBeanX tourist) {
                    this.tourist = tourist;
                }

                public static class TouristBeanX {
                    /**
                     * id : 1
                     * name : QWpX8E
                     * phone : 18322233491
                     * avatar : users/default.png
                     * created_at : 2020-07-09 10:40:45
                     * updated_at : 2020-07-09 11:17:00
                     * password : $2y$10$utyjNAlYqrU9VnHIxOFWMO2aWqAA2tstYsn5ANQmsCg/klCk96QFa
                     * weixin_bind : 1
                     * qq_bind : 2
                     * qq : qq_11
                     * weixin :
                     * tourist_id : null
                     * person_label : null
                     * bus_label : null
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
                    private Object tourist_id;
                    private Object person_label;
                    private Object bus_label;

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

                    public Object getTourist_id() {
                        return tourist_id;
                    }

                    public void setTourist_id(Object tourist_id) {
                        this.tourist_id = tourist_id;
                    }

                    public Object getPerson_label() {
                        return person_label;
                    }

                    public void setPerson_label(Object person_label) {
                        this.person_label = person_label;
                    }

                    public Object getBus_label() {
                        return bus_label;
                    }

                    public void setBus_label(Object bus_label) {
                        this.bus_label = bus_label;
                    }
                }
            }
        }
    }
}
