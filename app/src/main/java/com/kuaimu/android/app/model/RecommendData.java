package com.kuaimu.android.app.model;

import java.util.List;

public class RecommendData {

    /**
     * code : 200
     * msg : 成功
     * data : {"current_page":1,"data":[{"id":2,"tourist_id":1,"desc":"18322233491","img":"ce","created_at":"2020-07-09 11:33:00","updated_at":"2020-07-09 14:38:06","video":"123","assist_num":1,"sort":0,"is_deleted":1,"is_assist":false,"tourist":{"id":1,"name":"QWpX8E","phone":"18322233491","avatar":"users/default.png","created_at":"2020-07-09 10:40:45","updated_at":"2020-07-09 11:17:00","password":"$2y$10$utyjNAlYqrU9VnHIxOFWMO2aWqAA2tstYsn5ANQmsCg/klCk96QFa","weixin_bind":1,"qq_bind":2,"qq":"qq_11","weixin":"","tourist_id":null,"person_label":null,"bus_label":null}}],"first_page_url":"http://localhost/coffee/public/api/home/discover?page=1","from":1,"last_page":1,"last_page_url":"http://localhost/coffee/public/api/home/discover?page=1","next_page_url":null,"path":"http://localhost/coffee/public/api/home/discover","per_page":10,"prev_page_url":null,"to":2,"total":2}
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
         * data : [{"id":2,"tourist_id":1,"desc":"18322233491","img":"ce","created_at":"2020-07-09 11:33:00","updated_at":"2020-07-09 14:38:06","video":"123","assist_num":1,"sort":0,"is_deleted":1,"is_assist":false,"tourist":{"id":1,"name":"QWpX8E","phone":"18322233491","avatar":"users/default.png","created_at":"2020-07-09 10:40:45","updated_at":"2020-07-09 11:17:00","password":"$2y$10$utyjNAlYqrU9VnHIxOFWMO2aWqAA2tstYsn5ANQmsCg/klCk96QFa","weixin_bind":1,"qq_bind":2,"qq":"qq_11","weixin":"","tourist_id":null,"person_label":null,"bus_label":null}}]
         * first_page_url : http://localhost/coffee/public/api/home/discover?page=1
         * from : 1
         * last_page : 1
         * last_page_url : http://localhost/coffee/public/api/home/discover?page=1
         * next_page_url : null
         * path : http://localhost/coffee/public/api/home/discover
         * per_page : 10
         * prev_page_url : null
         * to : 2
         * total : 2
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

//        public static class DataBean {
//            /**
//             * id : 2
//             * tourist_id : 1
//             * desc : 18322233491
//             * img : ce
//             * created_at : 2020-07-09 11:33:00
//             * updated_at : 2020-07-09 14:38:06
//             * video : 123
//             * assist_num : 1
//             * sort : 0
//             * is_deleted : 1
//             * is_assist : false
//             * tourist : {"id":1,"name":"QWpX8E","phone":"18322233491","avatar":"users/default.png","created_at":"2020-07-09 10:40:45","updated_at":"2020-07-09 11:17:00","password":"$2y$10$utyjNAlYqrU9VnHIxOFWMO2aWqAA2tstYsn5ANQmsCg/klCk96QFa","weixin_bind":1,"qq_bind":2,"qq":"qq_11","weixin":"","tourist_id":null,"person_label":null,"bus_label":null}
//             */
//
//            private int id;
//            private int tourist_id;
//            private String desc;
//            private String img;
//            private String created_at;
//            private String updated_at;
//            private String video;
//            private int assist_num;
//            private int sort;
//            private int is_deleted;
//            private boolean is_assist;
//            private TouristBean tourist;
//
//            public int getId() {
//                return id;
//            }
//
//            public void setId(int id) {
//                this.id = id;
//            }
//
//            public int getTourist_id() {
//                return tourist_id;
//            }
//
//            public void setTourist_id(int tourist_id) {
//                this.tourist_id = tourist_id;
//            }
//
//            public String getDesc() {
//                return desc;
//            }
//
//            public void setDesc(String desc) {
//                this.desc = desc;
//            }
//
//            public String getImg() {
//                return img;
//            }
//
//            public void setImg(String img) {
//                this.img = img;
//            }
//
//            public String getCreated_at() {
//                return created_at;
//            }
//
//            public void setCreated_at(String created_at) {
//                this.created_at = created_at;
//            }
//
//            public String getUpdated_at() {
//                return updated_at;
//            }
//
//            public void setUpdated_at(String updated_at) {
//                this.updated_at = updated_at;
//            }
//
//            public String getVideo() {
//                return video;
//            }
//
//            public void setVideo(String video) {
//                this.video = video;
//            }
//
//            public int getAssist_num() {
//                return assist_num;
//            }
//
//            public void setAssist_num(int assist_num) {
//                this.assist_num = assist_num;
//            }
//
//            public int getSort() {
//                return sort;
//            }
//
//            public void setSort(int sort) {
//                this.sort = sort;
//            }
//
//            public int getIs_deleted() {
//                return is_deleted;
//            }
//
//            public void setIs_deleted(int is_deleted) {
//                this.is_deleted = is_deleted;
//            }
//
//            public boolean isIs_assist() {
//                return is_assist;
//            }
//
//            public void setIs_assist(boolean is_assist) {
//                this.is_assist = is_assist;
//            }
//
//            public TouristBean getTourist() {
//                return tourist;
//            }
//
//            public void setTourist(TouristBean tourist) {
//                this.tourist = tourist;
//            }
//
//            public static class TouristBean {
//                /**
//                 * id : 1
//                 * name : QWpX8E
//                 * phone : 18322233491
//                 * avatar : users/default.png
//                 * created_at : 2020-07-09 10:40:45
//                 * updated_at : 2020-07-09 11:17:00
//                 * password : $2y$10$utyjNAlYqrU9VnHIxOFWMO2aWqAA2tstYsn5ANQmsCg/klCk96QFa
//                 * weixin_bind : 1
//                 * qq_bind : 2
//                 * qq : qq_11
//                 * weixin :
//                 * tourist_id : null
//                 * person_label : null
//                 * bus_label : null
//                 */
//
//                private int id;
//                private String name;
//                private String phone;
//                private String avatar;
//                private String created_at;
//                private String updated_at;
//                private String password;
//                private int weixin_bind;
//                private int qq_bind;
//                private String qq;
//                private String weixin;
//                private Object tourist_id;
//                private Object person_label;
//                private Object bus_label;
//
//                public int getId() {
//                    return id;
//                }
//
//                public void setId(int id) {
//                    this.id = id;
//                }
//
//                public String getName() {
//                    return name;
//                }
//
//                public void setName(String name) {
//                    this.name = name;
//                }
//
//                public String getPhone() {
//                    return phone;
//                }
//
//                public void setPhone(String phone) {
//                    this.phone = phone;
//                }
//
//                public String getAvatar() {
//                    return avatar;
//                }
//
//                public void setAvatar(String avatar) {
//                    this.avatar = avatar;
//                }
//
//                public String getCreated_at() {
//                    return created_at;
//                }
//
//                public void setCreated_at(String created_at) {
//                    this.created_at = created_at;
//                }
//
//                public String getUpdated_at() {
//                    return updated_at;
//                }
//
//                public void setUpdated_at(String updated_at) {
//                    this.updated_at = updated_at;
//                }
//
//                public String getPassword() {
//                    return password;
//                }
//
//                public void setPassword(String password) {
//                    this.password = password;
//                }
//
//                public int getWeixin_bind() {
//                    return weixin_bind;
//                }
//
//                public void setWeixin_bind(int weixin_bind) {
//                    this.weixin_bind = weixin_bind;
//                }
//
//                public int getQq_bind() {
//                    return qq_bind;
//                }
//
//                public void setQq_bind(int qq_bind) {
//                    this.qq_bind = qq_bind;
//                }
//
//                public String getQq() {
//                    return qq;
//                }
//
//                public void setQq(String qq) {
//                    this.qq = qq;
//                }
//
//                public String getWeixin() {
//                    return weixin;
//                }
//
//                public void setWeixin(String weixin) {
//                    this.weixin = weixin;
//                }
//
//                public Object getTourist_id() {
//                    return tourist_id;
//                }
//
//                public void setTourist_id(Object tourist_id) {
//                    this.tourist_id = tourist_id;
//                }
//
//                public Object getPerson_label() {
//                    return person_label;
//                }
//
//                public void setPerson_label(Object person_label) {
//                    this.person_label = person_label;
//                }
//
//                public Object getBus_label() {
//                    return bus_label;
//                }
//
//                public void setBus_label(Object bus_label) {
//                    this.bus_label = bus_label;
//                }
//            }
//        }
    }
}
