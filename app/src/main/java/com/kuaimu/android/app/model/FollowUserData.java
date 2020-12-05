package com.kuaimu.android.app.model;

import java.util.List;

public class FollowUserData {

    /**
     * code : 200
     * msg : 成功
     * data : {"current_page":1,"data":[{"id":8,"created_at":"2020-12-05 18:17:23","updated_at":"2020-12-05 18:17:23","liker_id":28,"likeable_id":27,"tourist":{"id":28,"name":"r2ohl1","phone":"13521614825","avatar":"users/default.png","password":"$2y$10$njMziUBuj9a9VXcjJGY81.ATN8asQUarIobcuwZ3cfqfnKhOu5tPK","remember_token":null,"created_at":"2020-12-05 18:17:18","updated_at":"2020-12-05 18:17:23","sex":1,"cancel":1,"wallet_token":0,"fan_number":0,"follow_number":1,"level":1,"age":0,"credit":100,"income_token":0,"tourist_id":"74733021"}}],"first_page_url":"http://quickeye.fengyunguoyuan.com/api/message/flower?page=1","from":1,"last_page":1,"last_page_url":"http://quickeye.fengyunguoyuan.com/api/message/flower?page=1","next_page_url":null,"path":"http://quickeye.fengyunguoyuan.com/api/message/flower","per_page":10,"prev_page_url":null,"to":1,"total":1}
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
         * data : [{"id":8,"created_at":"2020-12-05 18:17:23","updated_at":"2020-12-05 18:17:23","liker_id":28,"likeable_id":27,"tourist":{"id":28,"name":"r2ohl1","phone":"13521614825","avatar":"users/default.png","password":"$2y$10$njMziUBuj9a9VXcjJGY81.ATN8asQUarIobcuwZ3cfqfnKhOu5tPK","remember_token":null,"created_at":"2020-12-05 18:17:18","updated_at":"2020-12-05 18:17:23","sex":1,"cancel":1,"wallet_token":0,"fan_number":0,"follow_number":1,"level":1,"age":0,"credit":100,"income_token":0,"tourist_id":"74733021"}}]
         * first_page_url : http://quickeye.fengyunguoyuan.com/api/message/flower?page=1
         * from : 1
         * last_page : 1
         * last_page_url : http://quickeye.fengyunguoyuan.com/api/message/flower?page=1
         * next_page_url : null
         * path : http://quickeye.fengyunguoyuan.com/api/message/flower
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
             * id : 8
             * created_at : 2020-12-05 18:17:23
             * updated_at : 2020-12-05 18:17:23
             * liker_id : 28
             * likeable_id : 27
             * tourist : {"id":28,"name":"r2ohl1","phone":"13521614825","avatar":"users/default.png","password":"$2y$10$njMziUBuj9a9VXcjJGY81.ATN8asQUarIobcuwZ3cfqfnKhOu5tPK","remember_token":null,"created_at":"2020-12-05 18:17:18","updated_at":"2020-12-05 18:17:23","sex":1,"cancel":1,"wallet_token":0,"fan_number":0,"follow_number":1,"level":1,"age":0,"credit":100,"income_token":0,"tourist_id":"74733021"}
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
                 * id : 28
                 * name : r2ohl1
                 * phone : 13521614825
                 * avatar : users/default.png
                 * password : $2y$10$njMziUBuj9a9VXcjJGY81.ATN8asQUarIobcuwZ3cfqfnKhOu5tPK
                 * remember_token : null
                 * created_at : 2020-12-05 18:17:18
                 * updated_at : 2020-12-05 18:17:23
                 * sex : 1
                 * cancel : 1
                 * wallet_token : 0
                 * fan_number : 0
                 * follow_number : 1
                 * level : 1
                 * age : 0
                 * credit : 100
                 * income_token : 0
                 * tourist_id : 74733021
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
        }
    }
}
