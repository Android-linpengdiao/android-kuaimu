package com.kuaimu.android.app.model;

import java.util.List;

public class FansData {


    /**
     * code : 200
     * msg : 成功
     * data : {"current_page":1,"data":[{"id":3,"created_at":"2020-08-30 21:22:45","updated_at":"2020-08-30 21:22:45","liker_id":5,"likeable_id":4,"tourist":{"id":5,"name":"TfMhEH","phone":"19920026487","avatar":"upload/20200830093218ra56e.jpg","created_at":"2020-08-16 21:17:34","updated_at":"2020-08-30 21:32:18","password":"$2y$10$fjJNahmv2CLHltRMnCVFduHixsJcf.neUSOhZDBqNw/ZUCfSElzXa","weixin_bind":2,"qq_bind":2,"qq":null,"weixin":null,"tourist_id":null,"person_label":null,"bus_label":null}}],"first_page_url":"http://kalao500q.com/api/message/flower?page=1","from":1,"last_page":1,"last_page_url":"http://kalao500q.com/api/message/flower?page=1","next_page_url":null,"path":"http://kalao500q.com/api/message/flower","per_page":10,"prev_page_url":null,"to":1,"total":1}
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
         * data : [{"id":3,"created_at":"2020-08-30 21:22:45","updated_at":"2020-08-30 21:22:45","liker_id":5,"likeable_id":4,"tourist":{"id":5,"name":"TfMhEH","phone":"19920026487","avatar":"upload/20200830093218ra56e.jpg","created_at":"2020-08-16 21:17:34","updated_at":"2020-08-30 21:32:18","password":"$2y$10$fjJNahmv2CLHltRMnCVFduHixsJcf.neUSOhZDBqNw/ZUCfSElzXa","weixin_bind":2,"qq_bind":2,"qq":null,"weixin":null,"tourist_id":null,"person_label":null,"bus_label":null}}]
         * first_page_url : http://kalao500q.com/api/message/flower?page=1
         * from : 1
         * last_page : 1
         * last_page_url : http://kalao500q.com/api/message/flower?page=1
         * next_page_url : null
         * path : http://kalao500q.com/api/message/flower
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

        public List<DataBean> getData() {
            return data;
        }

        public void setData(List<DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * id : 3
             * created_at : 2020-08-30 21:22:45
             * updated_at : 2020-08-30 21:22:45
             * liker_id : 5
             * likeable_id : 4
             * tourist : {"id":5,"name":"TfMhEH","phone":"19920026487","avatar":"upload/20200830093218ra56e.jpg","created_at":"2020-08-16 21:17:34","updated_at":"2020-08-30 21:32:18","password":"$2y$10$fjJNahmv2CLHltRMnCVFduHixsJcf.neUSOhZDBqNw/ZUCfSElzXa","weixin_bind":2,"qq_bind":2,"qq":null,"weixin":null,"tourist_id":null,"person_label":null,"bus_label":null}
             */

            private int id;
            private String created_at;
            private String updated_at;
            private int liker_id;
            private int likeable_id;
            private TouristBean tourist;

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

            public int getLiker_id() {
                return liker_id;
            }

            public void setLiker_id(int liker_id) {
                this.liker_id = liker_id;
            }

            public int getLikeable_id() {
                return likeable_id;
            }

            public void setLikeable_id(int likeable_id) {
                this.likeable_id = likeable_id;
            }

            public TouristBean getTourist() {
                return tourist;
            }

            public void setTourist(TouristBean tourist) {
                this.tourist = tourist;
            }

            public static class TouristBean {
                /**
                 * id : 5
                 * name : TfMhEH
                 * phone : 19920026487
                 * avatar : upload/20200830093218ra56e.jpg
                 * created_at : 2020-08-16 21:17:34
                 * updated_at : 2020-08-30 21:32:18
                 * password : $2y$10$fjJNahmv2CLHltRMnCVFduHixsJcf.neUSOhZDBqNw/ZUCfSElzXa
                 * weixin_bind : 2
                 * qq_bind : 2
                 * qq : null
                 * weixin : null
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
                private Object qq;
                private Object weixin;
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
