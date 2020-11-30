package com.kuaimu.android.app.model;

public class WalletInfoData {

    /**
     * code : 200
     * msg : 成功
     * data : {"id":23,"name":"HFiEqZ","phone":"18322233490","avatar":"users/default.png","password":"$2y$10$YqCPXgBO5Ei5VLV.HSuCE.iFfcaJaQepeV0vSvA10yBPkUCvp7iaC","remember_token":null,"created_at":"2020-08-10 16:35:12","updated_at":"2020-08-11 11:35:13","sex":1,"cancel":1,"wallet_token":0,"fan_number":0,"follow_number":1,"level":1,"age":0,"credit":100,"income_token":0}
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
         * id : 23
         * name : HFiEqZ
         * phone : 18322233490
         * avatar : users/default.png
         * password : $2y$10$YqCPXgBO5Ei5VLV.HSuCE.iFfcaJaQepeV0vSvA10yBPkUCvp7iaC
         * remember_token : null
         * created_at : 2020-08-10 16:35:12
         * updated_at : 2020-08-11 11:35:13
         * sex : 1
         * cancel : 1
         * wallet_token : 0
         * fan_number : 0
         * follow_number : 1
         * level : 1
         * age : 0
         * credit : 100
         * income_token : 0
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
    }
}
