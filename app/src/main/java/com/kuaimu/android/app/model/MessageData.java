package com.kuaimu.android.app.model;

import java.util.List;

public class MessageData {

    /**
     * code : 200
     * msg : 成功
     * data : {"current_page":1,"data":[{"id":5,"created_at":"2020-08-31 18:41:01","updated_at":"2020-08-31 18:41:01","body":"虎峪沟","tourist_id":14,"video_id":10,"video_tourist_id":4,"tourist":{"id":14,"name":"BGDlLs","phone":"19920026487","avatar":"users/default.png","created_at":"2020-08-31 16:03:02","updated_at":"2020-08-31 16:03:02","password":"$2y$10$aE.lFdiyMCNcwiSp0Ip6d.nq1bsCKemhCTVNfLG1rkMK7mkwMuCYW","weixin_bind":2,"qq_bind":2,"qq":null,"weixin":null,"tourist_id":null,"person_label":null,"bus_label":null,"is_vip":2,"vip_type":0,"vip_time":null,"apple_identifier":null,"apple_email":null,"ios_bind":2},"video":{"id":10,"tourist_id":4,"desc":"视频作品","img":"upload/20200830035140TomVV.jpg","created_at":"2020-08-30 15:51:42","updated_at":"2020-08-30 15:51:42","video":"http://diandou-test.oss-cn-beijing.aliyuncs.com/VID_20200830_141601.mp4","assist_num":0,"sort":9999,"is_deleted":2,"bus_label":null,"addr":null}}],"first_page_url":"http://kalao500q.com/api/message/comment?page=1","from":1,"last_page":1,"last_page_url":"http://kalao500q.com/api/message/comment?page=1","next_page_url":null,"path":"http://kalao500q.com/api/message/comment","per_page":10,"prev_page_url":null,"to":1,"total":1}
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
         * data : [{"id":5,"created_at":"2020-08-31 18:41:01","updated_at":"2020-08-31 18:41:01","body":"虎峪沟","tourist_id":14,"video_id":10,"video_tourist_id":4,"tourist":{"id":14,"name":"BGDlLs","phone":"19920026487","avatar":"users/default.png","created_at":"2020-08-31 16:03:02","updated_at":"2020-08-31 16:03:02","password":"$2y$10$aE.lFdiyMCNcwiSp0Ip6d.nq1bsCKemhCTVNfLG1rkMK7mkwMuCYW","weixin_bind":2,"qq_bind":2,"qq":null,"weixin":null,"tourist_id":null,"person_label":null,"bus_label":null,"is_vip":2,"vip_type":0,"vip_time":null,"apple_identifier":null,"apple_email":null,"ios_bind":2},"video":{"id":10,"tourist_id":4,"desc":"视频作品","img":"upload/20200830035140TomVV.jpg","created_at":"2020-08-30 15:51:42","updated_at":"2020-08-30 15:51:42","video":"http://diandou-test.oss-cn-beijing.aliyuncs.com/VID_20200830_141601.mp4","assist_num":0,"sort":9999,"is_deleted":2,"bus_label":null,"addr":null}}]
         * first_page_url : http://kalao500q.com/api/message/comment?page=1
         * from : 1
         * last_page : 1
         * last_page_url : http://kalao500q.com/api/message/comment?page=1
         * next_page_url : null
         * path : http://kalao500q.com/api/message/comment
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
             * id : 5
             * created_at : 2020-08-31 18:41:01
             * updated_at : 2020-08-31 18:41:01
             * body : 虎峪沟
             * tourist_id : 14
             * video_id : 10
             * video_tourist_id : 4
             * tourist : {"id":14,"name":"BGDlLs","phone":"19920026487","avatar":"users/default.png","created_at":"2020-08-31 16:03:02","updated_at":"2020-08-31 16:03:02","password":"$2y$10$aE.lFdiyMCNcwiSp0Ip6d.nq1bsCKemhCTVNfLG1rkMK7mkwMuCYW","weixin_bind":2,"qq_bind":2,"qq":null,"weixin":null,"tourist_id":null,"person_label":null,"bus_label":null,"is_vip":2,"vip_type":0,"vip_time":null,"apple_identifier":null,"apple_email":null,"ios_bind":2}
             * video : {"id":10,"tourist_id":4,"desc":"视频作品","img":"upload/20200830035140TomVV.jpg","created_at":"2020-08-30 15:51:42","updated_at":"2020-08-30 15:51:42","video":"http://diandou-test.oss-cn-beijing.aliyuncs.com/VID_20200830_141601.mp4","assist_num":0,"sort":9999,"is_deleted":2,"bus_label":null,"addr":null}
             */

            private int id;
            private String created_at;
            private String updated_at;
            private String body;
            private int tourist_id;
            private int video_id;
            private int video_tourist_id;
            private TouristBean tourist;
            private VideoBean video;

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

            public String getBody() {
                return body;
            }

            public void setBody(String body) {
                this.body = body;
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

            public int getVideo_tourist_id() {
                return video_tourist_id;
            }

            public void setVideo_tourist_id(int video_tourist_id) {
                this.video_tourist_id = video_tourist_id;
            }

            public TouristBean getTourist() {
                return tourist;
            }

            public void setTourist(TouristBean tourist) {
                this.tourist = tourist;
            }

            public VideoBean getVideo() {
                return video;
            }

            public void setVideo(VideoBean video) {
                this.video = video;
            }

            public static class TouristBean {
                /**
                 * id : 14
                 * name : BGDlLs
                 * phone : 19920026487
                 * avatar : users/default.png
                 * created_at : 2020-08-31 16:03:02
                 * updated_at : 2020-08-31 16:03:02
                 * password : $2y$10$aE.lFdiyMCNcwiSp0Ip6d.nq1bsCKemhCTVNfLG1rkMK7mkwMuCYW
                 * weixin_bind : 2
                 * qq_bind : 2
                 * qq : null
                 * weixin : null
                 * tourist_id : null
                 * person_label : null
                 * bus_label : null
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
                private Object tourist_id;
                private Object person_label;
                private Object bus_label;
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

            public static class VideoBean {
                /**
                 * id : 10
                 * tourist_id : 4
                 * desc : 视频作品
                 * img : upload/20200830035140TomVV.jpg
                 * created_at : 2020-08-30 15:51:42
                 * updated_at : 2020-08-30 15:51:42
                 * video : http://diandou-test.oss-cn-beijing.aliyuncs.com/VID_20200830_141601.mp4
                 * assist_num : 0
                 * sort : 9999
                 * is_deleted : 2
                 * bus_label : null
                 * addr : null
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
            }
        }
    }
}
