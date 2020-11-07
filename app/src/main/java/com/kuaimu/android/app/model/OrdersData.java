package com.kuaimu.android.app.model;

import java.util.List;

public class OrdersData {

    /**
     * code : 200
     * msg : 成功
     * data : {"current_page":1,"data":[{"id":99,"price":"0.01","created_at":"2020-10-12 22:21:09","updated_at":"2020-10-12 22:22:12","tourist_id":46,"pay_status":2,"pay_remark":"支付成功","trans_no":"202010121602512469656","pay_trans_no":null,"pay_type":2,"purpose":2,"name":"1啊啊啊","idcard":"你几级","back_photo":"upload/20201012102104xxzpR.jpg","front_photo":"upload/20201012102107JHou6.jpg","license_photo":"","vip_time":"2021-10-12 22:22:12","tourist":{"id":46,"name":"仰望星空","phone":"19920026487","avatar":"https://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLFZtLbKMvIUggcY5IbeCVSseH0r2gJIupqxY9jpCHYuyXan7mVfI0HoIqoDk9uk0IhaVoTsPEb3A/132","created_at":"2020-10-11 15:50:30","updated_at":"2020-10-12 22:22:12","password":"$2y$10$AjHY7RPI9ESKSDGmdMLs/etL8IYREK88kHDRlaxLuiNBTVnDtwQQ2","weixin_bind":2,"qq_bind":1,"qq":"","weixin":"o4w8S6h-8NQFLzPMLYDpcMKWAEt4","tourist_id":81516647,"person_label":null,"bus_label":null,"is_vip":1,"vip_type":2,"vip_time":"2021-10-12 22:22:12","apple_identifier":null,"apple_email":null,"ios_bind":2,"qq_third_name":"","qq_third_icon":"","weixin_third_name":"仰望星空","weixin_third_icon":"https://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLFZtLbKMvIUggcY5IbeCVSseH0r2gJIupqxY9jpCHYuyXan7mVfI0HoIqoDk9uk0IhaVoTsPEb3A/132","desc":null}}],"first_page_url":"http://kalao500q.com/api/cash/orders?page=1","from":1,"last_page":1,"last_page_url":"http://kalao500q.com/api/cash/orders?page=1","next_page_url":null,"path":"http://kalao500q.com/api/cash/orders","per_page":10,"prev_page_url":null,"to":1,"total":1}
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
         * data : [{"id":99,"price":"0.01","created_at":"2020-10-12 22:21:09","updated_at":"2020-10-12 22:22:12","tourist_id":46,"pay_status":2,"pay_remark":"支付成功","trans_no":"202010121602512469656","pay_trans_no":null,"pay_type":2,"purpose":2,"name":"1啊啊啊","idcard":"你几级","back_photo":"upload/20201012102104xxzpR.jpg","front_photo":"upload/20201012102107JHou6.jpg","license_photo":"","vip_time":"2021-10-12 22:22:12","tourist":{"id":46,"name":"仰望星空","phone":"19920026487","avatar":"https://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLFZtLbKMvIUggcY5IbeCVSseH0r2gJIupqxY9jpCHYuyXan7mVfI0HoIqoDk9uk0IhaVoTsPEb3A/132","created_at":"2020-10-11 15:50:30","updated_at":"2020-10-12 22:22:12","password":"$2y$10$AjHY7RPI9ESKSDGmdMLs/etL8IYREK88kHDRlaxLuiNBTVnDtwQQ2","weixin_bind":2,"qq_bind":1,"qq":"","weixin":"o4w8S6h-8NQFLzPMLYDpcMKWAEt4","tourist_id":81516647,"person_label":null,"bus_label":null,"is_vip":1,"vip_type":2,"vip_time":"2021-10-12 22:22:12","apple_identifier":null,"apple_email":null,"ios_bind":2,"qq_third_name":"","qq_third_icon":"","weixin_third_name":"仰望星空","weixin_third_icon":"https://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLFZtLbKMvIUggcY5IbeCVSseH0r2gJIupqxY9jpCHYuyXan7mVfI0HoIqoDk9uk0IhaVoTsPEb3A/132","desc":null}}]
         * first_page_url : http://kalao500q.com/api/cash/orders?page=1
         * from : 1
         * last_page : 1
         * last_page_url : http://kalao500q.com/api/cash/orders?page=1
         * next_page_url : null
         * path : http://kalao500q.com/api/cash/orders
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
             * id : 99
             * price : 0.01
             * created_at : 2020-10-12 22:21:09
             * updated_at : 2020-10-12 22:22:12
             * tourist_id : 46
             * pay_status : 2
             * pay_remark : 支付成功
             * trans_no : 202010121602512469656
             * pay_trans_no : null
             * pay_type : 2
             * purpose : 2
             * name : 1啊啊啊
             * idcard : 你几级
             * back_photo : upload/20201012102104xxzpR.jpg
             * front_photo : upload/20201012102107JHou6.jpg
             * license_photo :
             * vip_time : 2021-10-12 22:22:12
             * tourist : {"id":46,"name":"仰望星空","phone":"19920026487","avatar":"https://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLFZtLbKMvIUggcY5IbeCVSseH0r2gJIupqxY9jpCHYuyXan7mVfI0HoIqoDk9uk0IhaVoTsPEb3A/132","created_at":"2020-10-11 15:50:30","updated_at":"2020-10-12 22:22:12","password":"$2y$10$AjHY7RPI9ESKSDGmdMLs/etL8IYREK88kHDRlaxLuiNBTVnDtwQQ2","weixin_bind":2,"qq_bind":1,"qq":"","weixin":"o4w8S6h-8NQFLzPMLYDpcMKWAEt4","tourist_id":81516647,"person_label":null,"bus_label":null,"is_vip":1,"vip_type":2,"vip_time":"2021-10-12 22:22:12","apple_identifier":null,"apple_email":null,"ios_bind":2,"qq_third_name":"","qq_third_icon":"","weixin_third_name":"仰望星空","weixin_third_icon":"https://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLFZtLbKMvIUggcY5IbeCVSseH0r2gJIupqxY9jpCHYuyXan7mVfI0HoIqoDk9uk0IhaVoTsPEb3A/132","desc":null}
             */

            private int id;
            private String price;
            private String created_at;
            private String updated_at;
            private int tourist_id;
            private int pay_status;
            private String pay_remark;
            private String trans_no;
            private Object pay_trans_no;
            private int pay_type;
            private int purpose;
            private String name;
            private String idcard;
            private String back_photo;
            private String front_photo;
            private String license_photo;
            private String vip_time;
            private TouristBean tourist;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
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

            public int getPay_status() {
                return pay_status;
            }

            public void setPay_status(int pay_status) {
                this.pay_status = pay_status;
            }

            public String getPay_remark() {
                return pay_remark;
            }

            public void setPay_remark(String pay_remark) {
                this.pay_remark = pay_remark;
            }

            public String getTrans_no() {
                return trans_no;
            }

            public void setTrans_no(String trans_no) {
                this.trans_no = trans_no;
            }

            public Object getPay_trans_no() {
                return pay_trans_no;
            }

            public void setPay_trans_no(Object pay_trans_no) {
                this.pay_trans_no = pay_trans_no;
            }

            public int getPay_type() {
                return pay_type;
            }

            public void setPay_type(int pay_type) {
                this.pay_type = pay_type;
            }

            public int getPurpose() {
                return purpose;
            }

            public void setPurpose(int purpose) {
                this.purpose = purpose;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getIdcard() {
                return idcard;
            }

            public void setIdcard(String idcard) {
                this.idcard = idcard;
            }

            public String getBack_photo() {
                return back_photo;
            }

            public void setBack_photo(String back_photo) {
                this.back_photo = back_photo;
            }

            public String getFront_photo() {
                return front_photo;
            }

            public void setFront_photo(String front_photo) {
                this.front_photo = front_photo;
            }

            public String getLicense_photo() {
                return license_photo;
            }

            public void setLicense_photo(String license_photo) {
                this.license_photo = license_photo;
            }

            public String getVip_time() {
                return vip_time;
            }

            public void setVip_time(String vip_time) {
                this.vip_time = vip_time;
            }

            public TouristBean getTourist() {
                return tourist;
            }

            public void setTourist(TouristBean tourist) {
                this.tourist = tourist;
            }

            public static class TouristBean {
                /**
                 * id : 46
                 * name : 仰望星空
                 * phone : 19920026487
                 * avatar : https://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLFZtLbKMvIUggcY5IbeCVSseH0r2gJIupqxY9jpCHYuyXan7mVfI0HoIqoDk9uk0IhaVoTsPEb3A/132
                 * created_at : 2020-10-11 15:50:30
                 * updated_at : 2020-10-12 22:22:12
                 * password : $2y$10$AjHY7RPI9ESKSDGmdMLs/etL8IYREK88kHDRlaxLuiNBTVnDtwQQ2
                 * weixin_bind : 2
                 * qq_bind : 1
                 * qq :
                 * weixin : o4w8S6h-8NQFLzPMLYDpcMKWAEt4
                 * tourist_id : 81516647
                 * person_label : null
                 * bus_label : null
                 * is_vip : 1
                 * vip_type : 2
                 * vip_time : 2021-10-12 22:22:12
                 * apple_identifier : null
                 * apple_email : null
                 * ios_bind : 2
                 * qq_third_name :
                 * qq_third_icon :
                 * weixin_third_name : 仰望星空
                 * weixin_third_icon : https://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTLFZtLbKMvIUggcY5IbeCVSseH0r2gJIupqxY9jpCHYuyXan7mVfI0HoIqoDk9uk0IhaVoTsPEb3A/132
                 * desc : null
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
                private Object person_label;
                private Object bus_label;
                private int is_vip;
                private int vip_type;
                private String vip_time;
                private Object apple_identifier;
                private Object apple_email;
                private int ios_bind;
                private String qq_third_name;
                private String qq_third_icon;
                private String weixin_third_name;
                private String weixin_third_icon;
                private Object desc;

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

                public String getQq_third_name() {
                    return qq_third_name;
                }

                public void setQq_third_name(String qq_third_name) {
                    this.qq_third_name = qq_third_name;
                }

                public String getQq_third_icon() {
                    return qq_third_icon;
                }

                public void setQq_third_icon(String qq_third_icon) {
                    this.qq_third_icon = qq_third_icon;
                }

                public String getWeixin_third_name() {
                    return weixin_third_name;
                }

                public void setWeixin_third_name(String weixin_third_name) {
                    this.weixin_third_name = weixin_third_name;
                }

                public String getWeixin_third_icon() {
                    return weixin_third_icon;
                }

                public void setWeixin_third_icon(String weixin_third_icon) {
                    this.weixin_third_icon = weixin_third_icon;
                }

                public Object getDesc() {
                    return desc;
                }

                public void setDesc(Object desc) {
                    this.desc = desc;
                }
            }
        }
    }
}
