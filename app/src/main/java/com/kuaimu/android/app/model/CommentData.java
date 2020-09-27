package com.kuaimu.android.app.model;

import java.util.List;

public class CommentData {

    /**
     * code : 200
     * msg : 成功
     * data : {"comment":[{"id":1,"content":"好视频","created_at":"2020-07-09 12:03:33","updated_at":"2020-07-09 12:03:33","tourist_id":1,"video_id":2,"tourist":{"id":1,"name":"QWpX8E","phone":"18322233491","avatar":"users/default.png","created_at":"2020-07-09 10:40:45","updated_at":"2020-07-09 11:17:00","password":"$2y$10$utyjNAlYqrU9VnHIxOFWMO2aWqAA2tstYsn5ANQmsCg/klCk96QFa","weixin_bind":1,"qq_bind":2,"qq":"qq_11","weixin":"","tourist_id":null,"person_label":null,"bus_label":null,"is_vip":2,"vip_type":0,"vip_time":null,"apple_identifier":null}}],"comment_tot":1}
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
         * comment : [{"id":1,"content":"好视频","created_at":"2020-07-09 12:03:33","updated_at":"2020-07-09 12:03:33","tourist_id":1,"video_id":2,"tourist":{"id":1,"name":"QWpX8E","phone":"18322233491","avatar":"users/default.png","created_at":"2020-07-09 10:40:45","updated_at":"2020-07-09 11:17:00","password":"$2y$10$utyjNAlYqrU9VnHIxOFWMO2aWqAA2tstYsn5ANQmsCg/klCk96QFa","weixin_bind":1,"qq_bind":2,"qq":"qq_11","weixin":"","tourist_id":null,"person_label":null,"bus_label":null,"is_vip":2,"vip_type":0,"vip_time":null,"apple_identifier":null}}]
         * comment_tot : 1
         */

        private int comment_tot;
        private List<CommentBean> comment;

        public int getComment_tot() {
            return comment_tot;
        }

        public void setComment_tot(int comment_tot) {
            this.comment_tot = comment_tot;
        }

        public List<CommentBean> getComment() {
            return comment;
        }

        public void setComment(List<CommentBean> comment) {
            this.comment = comment;
        }

        public static class CommentBean {
            /**
             * id : 1
             * content : 好视频
             * created_at : 2020-07-09 12:03:33
             * updated_at : 2020-07-09 12:03:33
             * tourist_id : 1
             * video_id : 2
             * tourist : {"id":1,"name":"QWpX8E","phone":"18322233491","avatar":"users/default.png","created_at":"2020-07-09 10:40:45","updated_at":"2020-07-09 11:17:00","password":"$2y$10$utyjNAlYqrU9VnHIxOFWMO2aWqAA2tstYsn5ANQmsCg/klCk96QFa","weixin_bind":1,"qq_bind":2,"qq":"qq_11","weixin":"","tourist_id":null,"person_label":null,"bus_label":null,"is_vip":2,"vip_type":0,"vip_time":null,"apple_identifier":null}
             */

            private int id;
            private String content;
            private String created_at;
            private String updated_at;
            private int tourist_id;
            private int video_id;
            private TouristBean tourist;

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

            public int getVideo_id() {
                return video_id;
            }

            public void setVideo_id(int video_id) {
                this.video_id = video_id;
            }

            public TouristBean getTourist() {
                return tourist;
            }

            public void setTourist(TouristBean tourist) {
                this.tourist = tourist;
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
                 * is_vip : 2
                 * vip_type : 0
                 * vip_time : null
                 * apple_identifier : null
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
                private int is_vip;
                private int vip_type;
                private Object vip_time;
                private Object apple_identifier;

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
            }
        }
    }
}
