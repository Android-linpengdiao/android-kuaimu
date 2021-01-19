package com.kuaimu.android.app.model;

import java.util.List;

public class BusinessData {

    /**
     * code : 200
     * msg : 成功
     * data : [{"id":5,"tourist_id":28,"created_at":"2021-01-19 16:29:40","updated_at":"2021-01-19 16:29:40","name":"商家AAA","industry":"IT","desc":"北京","link":"13521614827","logo":"http://quickeye.oss-cn-beijing.aliyuncs.com/1611044969129_image.jpg","qrcode":"http://quickeye.oss-cn-beijing.aliyuncs.com/1611044973404_image.jpg"},{"id":6,"tourist_id":28,"created_at":"2021-01-19 16:51:30","updated_at":"2021-01-19 16:51:30","name":"商家AAA","industry":"IT","desc":"北京","link":"13521614827","logo":"http://quickeye.oss-cn-beijing.aliyuncs.com/1611046279596_image.jpg","qrcode":"http://quickeye.oss-cn-beijing.aliyuncs.com/1611046284532_image.jpg"}]
     */

    private Integer code;
    private String msg;
    private List<DataBean> data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
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
         * tourist_id : 28
         * created_at : 2021-01-19 16:29:40
         * updated_at : 2021-01-19 16:29:40
         * name : 商家AAA
         * industry : IT
         * desc : 北京
         * link : 13521614827
         * logo : http://quickeye.oss-cn-beijing.aliyuncs.com/1611044969129_image.jpg
         * qrcode : http://quickeye.oss-cn-beijing.aliyuncs.com/1611044973404_image.jpg
         */

        private Integer id;
        private Integer touristId;
        private String createdAt;
        private String updatedAt;
        private String name;
        private String industry;
        private String desc;
        private String link;
        private String logo;
        private String qrcode;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getTouristId() {
            return touristId;
        }

        public void setTouristId(Integer touristId) {
            this.touristId = touristId;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getIndustry() {
            return industry;
        }

        public void setIndustry(String industry) {
            this.industry = industry;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getLogo() {
            return logo;
        }

        public void setLogo(String logo) {
            this.logo = logo;
        }

        public String getQrcode() {
            return qrcode;
        }

        public void setQrcode(String qrcode) {
            this.qrcode = qrcode;
        }
    }
}
