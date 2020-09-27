package com.kuaimu.android.app.model;

import java.io.Serializable;
import java.util.List;

public class BannerData implements Serializable{


    /**
     * code : 200
     * msg : 成功
     * data : [{"id":1,"created_at":"2020-08-19 22:15:00","updated_at":"2020-09-18 09:59:49","title":"轮播图","img":"users/default.png","desc":"<p>12<\/p>","link_type":2,"video_id":null},{"id":2,"created_at":"2020-09-18 09:58:00","updated_at":"2020-09-18 09:59:35","title":"重金属","img":"banners/September2020/mbd2yUOpGuUzUsRmwh4n.png","desc":"<p>这是一个神奇的重金属<\/p>","link_type":1,"video_id":4}]
     */

    private int code;
    private String msg;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean implements Serializable {
        /**
         * id : 1
         * created_at : 2020-08-19 22:15:00
         * updated_at : 2020-09-18 09:59:49
         * title : 轮播图
         * img : users/default.png
         * desc : <p>12</p>
         * link_type : 2
         * video_id : null
         */

        private int id;
        private String created_at;
        private String updated_at;
        private String title;
        private String img;
        private String desc;
        private int link_type;
        private int video_id;

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

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public int getLink_type() {
            return link_type;
        }

        public void setLink_type(int link_type) {
            this.link_type = link_type;
        }

        public int getVideo_id() {
            return video_id;
        }

        public void setVideo_id(int video_id) {
            this.video_id = video_id;
        }
    }
}
