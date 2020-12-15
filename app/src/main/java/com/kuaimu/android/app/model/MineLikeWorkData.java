package com.kuaimu.android.app.model;

import java.io.Serializable;
import java.util.List;

public class MineLikeWorkData implements Serializable {

    /**
     * code : 200
     * msg : 成功
     * data : [{"id":54,"created_at":"2020-12-07 22:34:30","updated_at":"2020-12-07 22:38:39","is_deleted":2,"tourist_id":29,"tourist_name":"秋","category_id":1,"category_name":"旅行","desc":"秋日日日","img":"upload/20201207103429Ges4Z.jpg","video":"http://oss-coffee.oss-cn-beijing.aliyuncs.com/VID_20200830_141601.mp4","relation_good":2,"good_name":"","good_link":"","good_img":"","assist_num":1,"comment_num":0,"addr":"北京市","tourist":{"id":29,"name":"秋","phone":"19920026487","avatar":"upload/20201207103051PigBi.jpg","password":"$2y$10$deLuumD0.zHBAQu7iZiELuNL4c1a7iCXeR16uUyssM9dXlX7Y3Aw.","remember_token":null,"created_at":"2020-12-07 22:30:15","updated_at":"2020-12-09 16:15:26","sex":1,"cancel":1,"wallet_token":0,"fan_number":1,"follow_number":1,"level":1,"age":0,"credit":100,"income_token":0,"tourist_id":"35738890"}},{"id":51,"created_at":"2020-11-29 15:16:56","updated_at":"2020-12-13 12:56:34","is_deleted":2,"tourist_id":27,"tourist_name":"qalhlH","category_id":1,"category_name":"旅行","desc":"好","img":"upload/20201129031643SFaz2.png","video":"upload/20201129031648KIVuK.mp4","relation_good":1,"good_name":"商品名","good_link":"http://www.baidu.com","good_img":"upload/20201129031639B4qx2.png","assist_num":2,"comment_num":5,"addr":"北京市","tourist":{"id":27,"name":"qalhlH","phone":"15935964770","avatar":"upload/20201129054614BNefy.png","password":"$2y$10$Zj1iT7WobtsmDGOnNeNUoOgrWeVCUahqu1eXS9UvBdDOgtkH5Bs4.","remember_token":null,"created_at":"2020-11-29 15:04:54","updated_at":"2020-12-07 22:14:29","sex":1,"cancel":1,"wallet_token":0,"fan_number":1,"follow_number":0,"level":1,"age":0,"credit":100,"income_token":0,"tourist_id":"56307618"}}]
     */

    private int code;
    private String msg;
    private List<VideoDataBean> data;

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

    public List<VideoDataBean> getData() {
        return data;
    }

    public void setData(List<VideoDataBean> data) {
        this.data = data;
    }
}
