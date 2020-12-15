package com.baselibrary;

import java.io.Serializable;
import java.util.List;

public class UserInfo implements Serializable {

    /**
     * code : 200
     * msg : 成功
     * data : {"id":27,"name":"qalhlH","phone":"15935964770","avatar":"upload/20201129054614BNefy.png","password":"$2y$10$Zj1iT7WobtsmDGOnNeNUoOgrWeVCUahqu1eXS9UvBdDOgtkH5Bs4.","remember_token":null,"created_at":"2020-11-29 15:04:54","updated_at":"2020-11-29 17:46:15","sex":1,"cancel":1,"wallet_token":0,"fan_number":0,"follow_number":0,"level":1,"age":0,"credit":100,"income_token":0,"tourist_id":"56307618","profiles":[{"id":3,"tourist_id":27,"created_at":"2020-11-29 16:28:01","updated_at":"2020-11-29 16:28:01","real_name":"是啊","idcard":"142729199008066623","front_photo":"upload/20201129042628ZisNp.png","back_photo":"upload/20201129042635UYvZ3.png","auth":2,"license_photo":""},{"id":4,"tourist_id":27,"created_at":"2020-11-29 17:22:07","updated_at":"2020-11-29 17:22:07","real_name":"是因为他在","idcard":null,"front_photo":"upload/20201129052140jSIec.png","back_photo":"upload/20201129052150IwhZg.png","auth":1,"license_photo":"upload/2020112905215809a6w.png"},{"id":5,"tourist_id":27,"created_at":"2020-11-29 17:28:18","updated_at":"2020-11-29 17:28:18","real_name":"是啊真的要","idcard":null,"front_photo":"upload/20201129052755zdGyH.png","back_photo":"upload/20201129052804qwMtE.png","auth":1,"license_photo":"upload/20201129052816qb7Ig.png"}]}
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

    public static class DataBean implements Serializable{
        /**
         * id : 27
         * name : qalhlH
         * phone : 15935964770
         * avatar : upload/20201129054614BNefy.png
         * password : $2y$10$Zj1iT7WobtsmDGOnNeNUoOgrWeVCUahqu1eXS9UvBdDOgtkH5Bs4.
         * remember_token : null
         * created_at : 2020-11-29 15:04:54
         * updated_at : 2020-11-29 17:46:15
         * sex : 1
         * cancel : 1
         * wallet_token : 0
         * fan_number : 0
         * follow_number : 0
         * level : 1
         * age : 0
         * credit : 100
         * income_token : 0
         * tourist_id : 56307618
         * profiles : [{"id":3,"tourist_id":27,"created_at":"2020-11-29 16:28:01","updated_at":"2020-11-29 16:28:01","real_name":"是啊","idcard":"142729199008066623","front_photo":"upload/20201129042628ZisNp.png","back_photo":"upload/20201129042635UYvZ3.png","auth":2,"license_photo":""},{"id":4,"tourist_id":27,"created_at":"2020-11-29 17:22:07","updated_at":"2020-11-29 17:22:07","real_name":"是因为他在","idcard":null,"front_photo":"upload/20201129052140jSIec.png","back_photo":"upload/20201129052150IwhZg.png","auth":1,"license_photo":"upload/2020112905215809a6w.png"},{"id":5,"tourist_id":27,"created_at":"2020-11-29 17:28:18","updated_at":"2020-11-29 17:28:18","real_name":"是啊真的要","idcard":null,"front_photo":"upload/20201129052755zdGyH.png","back_photo":"upload/20201129052804qwMtE.png","auth":1,"license_photo":"upload/20201129052816qb7Ig.png"}]
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
        private List<ProfilesBean> profiles;

        private int video_num;
        private int like_video_num;

        private int person_auth_status;
        private int  business_auth_status;
        

        public int getPerson_auth_status() {
            return person_auth_status;
        }

        public void setPerson_auth_status(int person_auth_status) {
            this.person_auth_status = person_auth_status;
        }

        public int getBusiness_auth_status() {
            return business_auth_status;
        }

        public void setBusiness_auth_status(int business_auth_status) {
            this.business_auth_status = business_auth_status;
        }

        public int getVideo_num() {
            return video_num;
        }

        public void setVideo_num(int video_num) {
            this.video_num = video_num;
        }

        public int getLike_video_num() {
            return like_video_num;
        }

        public void setLike_video_num(int like_video_num) {
            this.like_video_num = like_video_num;
        }




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

        public List<ProfilesBean> getProfiles() {
            return profiles;
        }

        public void setProfiles(List<ProfilesBean> profiles) {
            this.profiles = profiles;
        }

        public static class ProfilesBean implements Serializable{
            /**
             * id : 3
             * tourist_id : 27
             * created_at : 2020-11-29 16:28:01
             * updated_at : 2020-11-29 16:28:01
             * real_name : 是啊
             * idcard : 142729199008066623
             * front_photo : upload/20201129042628ZisNp.png
             * back_photo : upload/20201129042635UYvZ3.png
             * auth : 2
             * license_photo :
             */

            private int id;
            private int tourist_id;
            private String created_at;
            private String updated_at;
            private String real_name;
            private String idcard;
            private String front_photo;
            private String back_photo;
            private int auth;
            private String license_photo;

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

            public String getReal_name() {
                return real_name;
            }

            public void setReal_name(String real_name) {
                this.real_name = real_name;
            }

            public String getIdcard() {
                return idcard;
            }

            public void setIdcard(String idcard) {
                this.idcard = idcard;
            }

            public String getFront_photo() {
                return front_photo;
            }

            public void setFront_photo(String front_photo) {
                this.front_photo = front_photo;
            }

            public String getBack_photo() {
                return back_photo;
            }

            public void setBack_photo(String back_photo) {
                this.back_photo = back_photo;
            }

            public int getAuth() {
                return auth;
            }

            public void setAuth(int auth) {
                this.auth = auth;
            }

            public String getLicense_photo() {
                return license_photo;
            }

            public void setLicense_photo(String license_photo) {
                this.license_photo = license_photo;
            }
        }
    }
}
