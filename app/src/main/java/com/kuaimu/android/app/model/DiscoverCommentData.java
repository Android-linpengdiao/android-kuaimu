package com.kuaimu.android.app.model;

import java.util.List;

public class DiscoverCommentData {

    /**
     * code : 200
     * msg : 成功
     * data : {"comment":{"current_page":1,"data":[{"id":15,"content":"来来","created_at":"2020-09-03 18:26:29","updated_at":"2020-09-03 18:26:29","tourist_id":4,"discover_id":20,"tourist":{"id":4,"name":"sSxthv","phone":"13521614827","avatar":"upload/20200808111900DYxMk.jpg","created_at":"2020-08-06 08:27:21","updated_at":"2020-09-02 19:01:58","password":"$2y$10$0fdFMh97ICGkgGdzKxwtuO2nhwnEi/gT2Q9epNYmx6ore75f4KRKm","weixin_bind":2,"qq_bind":2,"qq":null,"weixin":null,"tourist_id":132131,"person_label":"天成云","bus_label":"解决方案","is_vip":1,"vip_type":2,"vip_time":"2021-09-02 18:29:14","apple_identifier":null,"apple_email":null,"ios_bind":2}}],"first_page_url":"http://kalao500q.com/api/home/discoverComment?page=1","from":1,"last_page":1,"last_page_url":"http://kalao500q.com/api/home/discoverComment?page=1","next_page_url":null,"path":"http://kalao500q.com/api/home/discoverComment","per_page":"10","prev_page_url":null,"to":1,"total":1},"comment_tot":12}
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
         * comment : {"current_page":1,"data":[{"id":15,"content":"来来","created_at":"2020-09-03 18:26:29","updated_at":"2020-09-03 18:26:29","tourist_id":4,"discover_id":20,"tourist":{"id":4,"name":"sSxthv","phone":"13521614827","avatar":"upload/20200808111900DYxMk.jpg","created_at":"2020-08-06 08:27:21","updated_at":"2020-09-02 19:01:58","password":"$2y$10$0fdFMh97ICGkgGdzKxwtuO2nhwnEi/gT2Q9epNYmx6ore75f4KRKm","weixin_bind":2,"qq_bind":2,"qq":null,"weixin":null,"tourist_id":132131,"person_label":"天成云","bus_label":"解决方案","is_vip":1,"vip_type":2,"vip_time":"2021-09-02 18:29:14","apple_identifier":null,"apple_email":null,"ios_bind":2}}],"first_page_url":"http://kalao500q.com/api/home/discoverComment?page=1","from":1,"last_page":1,"last_page_url":"http://kalao500q.com/api/home/discoverComment?page=1","next_page_url":null,"path":"http://kalao500q.com/api/home/discoverComment","per_page":"10","prev_page_url":null,"to":1,"total":1}
         * comment_tot : 12
         */

        private CommentBean comment;
        private int comment_tot;

        public CommentBean getComment() {
            return comment;
        }

        public void setComment(CommentBean comment) {
            this.comment = comment;
        }

        public int getComment_tot() {
            return comment_tot;
        }

        public void setComment_tot(int comment_tot) {
            this.comment_tot = comment_tot;
        }

        public static class CommentBean {
            /**
             * current_page : 1
             * data : [{"id":15,"content":"来来","created_at":"2020-09-03 18:26:29","updated_at":"2020-09-03 18:26:29","tourist_id":4,"discover_id":20,"tourist":{"id":4,"name":"sSxthv","phone":"13521614827","avatar":"upload/20200808111900DYxMk.jpg","created_at":"2020-08-06 08:27:21","updated_at":"2020-09-02 19:01:58","password":"$2y$10$0fdFMh97ICGkgGdzKxwtuO2nhwnEi/gT2Q9epNYmx6ore75f4KRKm","weixin_bind":2,"qq_bind":2,"qq":null,"weixin":null,"tourist_id":132131,"person_label":"天成云","bus_label":"解决方案","is_vip":1,"vip_type":2,"vip_time":"2021-09-02 18:29:14","apple_identifier":null,"apple_email":null,"ios_bind":2}}]
             * first_page_url : http://kalao500q.com/api/home/discoverComment?page=1
             * from : 1
             * last_page : 1
             * last_page_url : http://kalao500q.com/api/home/discoverComment?page=1
             * next_page_url : null
             * path : http://kalao500q.com/api/home/discoverComment
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
            private String per_page;
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

            public String getPer_page() {
                return per_page;
            }

            public void setPer_page(String per_page) {
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
                 * id : 15
                 * content : 来来
                 * created_at : 2020-09-03 18:26:29
                 * updated_at : 2020-09-03 18:26:29
                 * tourist_id : 4
                 * discover_id : 20
                 * tourist : {"id":4,"name":"sSxthv","phone":"13521614827","avatar":"upload/20200808111900DYxMk.jpg","created_at":"2020-08-06 08:27:21","updated_at":"2020-09-02 19:01:58","password":"$2y$10$0fdFMh97ICGkgGdzKxwtuO2nhwnEi/gT2Q9epNYmx6ore75f4KRKm","weixin_bind":2,"qq_bind":2,"qq":null,"weixin":null,"tourist_id":132131,"person_label":"天成云","bus_label":"解决方案","is_vip":1,"vip_type":2,"vip_time":"2021-09-02 18:29:14","apple_identifier":null,"apple_email":null,"ios_bind":2}
                 */

                private int id;
                private String content;
                private String created_at;
                private String updated_at;
                private int tourist_id;
                private int discover_id;
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

                public int getDiscover_id() {
                    return discover_id;
                }

                public void setDiscover_id(int discover_id) {
                    this.discover_id = discover_id;
                }

                public TouristBean getTourist() {
                    return tourist;
                }

                public void setTourist(TouristBean tourist) {
                    this.tourist = tourist;
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
            }
        }
    }
}
