package com.okhttp;

import android.util.Log;

import com.baselibrary.utils.CommonUtil;
import com.okhttp.callbacks.Callback;
import com.okhttp.utils.APIUrls;
import com.okhttp.utils.OkHttpUtils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;


public class SendRequest {
    private static String TAG = "SendRequest";

    /**
     * 注册
     *
     * @param phone
     * @param password
     * @param authCode
     * @param call
     */
    public static void register(String phone, String password, String authCode, String key, String value, Callback call) {
        Map<String, String> map = new HashMap<>();
        map.put("phone", phone);
        map.put("password", password);
        map.put("password", password);
        map.put("authCode", authCode);
        OkHttpUtils.getInstance().post().params(map).url(APIUrls.url_register).build().execute(call);

    }

    /**
     * 手机注册
     *
     * @param phone
     * @param password
     * @param password_confirmation
     * @param authCode
     * @param call
     */
    public static void register(String phone, String password, String password_confirmation, String authCode, Callback call) {
        Map<String, String> map = new HashMap<>();
        map.put("phone", phone);
        map.put("password", password);
        map.put("password_confirmation", password_confirmation);
        map.put("authCode", authCode);
        OkHttpUtils.getInstance().post().params(map).url(APIUrls.url_register).build().execute(call);

    }

    /**
     * 密码登录
     *
     * @param phone
     * @param password
     * @param call
     */
    public static void login(String phone, String password, Callback call) {
        Map<String, String> map = new HashMap<>();
        map.put("phone", phone);
        map.put("password", password);
        OkHttpUtils.getInstance().post().params(map).url(APIUrls.url_login).build().execute(call);

    }

    /**
     * 绑定三方
     * <p>
     * phone	String
     * 手机号
     * <p>
     * password	String
     * 密码
     * <p>
     * authCode	String
     * 验证码
     * <p>
     * third_type	String
     * 1=>QQ 2=>微信
     * <p>
     * third_value	String
     * 三方唯一id
     */
    public static void bindThird(String phone, String password, String authCode, String third_type, String third_value, Callback call) {
        Map<String, String> map = new HashMap<>();
        map.put("phone", phone);
        map.put("password", password);
        map.put("authCode", authCode);
        map.put("third_type", third_type);
        map.put("third_value", third_value);
        OkHttpUtils.getInstance().post().params(map).url(APIUrls.bindThird).build().execute(call);

    }

    /**
     * 判断用户是否绑定过三方
     * <p>
     * third_type	String
     * 1=>QQ 2=>微信
     * <p>
     * third_value	String
     * 三方唯一id
     */
    public static void checkUserIsRegister(String third_type, String third_value, Callback call) {
        Map<String, String> map = new HashMap<>();
        map.put("third_type", third_type);
        map.put("third_value", third_value);
        OkHttpUtils.getInstance().post().params(map).url(APIUrls.checkUserIsRegister).build().execute(call);

    }

    /**
     * 用户第三方登录（微信）
     *
     * @param openid
     * @param nickname
     * @param headimgurl
     * @param sex
     * @param city
     * @param province
     * @param call
     */
    public static void WXLogin(String openid, String nickname, String headimgurl, String sex, String city, String province, Callback call) {
        Map<String, String> map = new HashMap<>();
        map.put("openid", openid);
        map.put("nickname", nickname);
        map.put("headimgurl", headimgurl);
        map.put("sex", sex);
        map.put("city", city);
        map.put("province", province);
        OkHttpUtils.getInstance().post().params(map).url(APIUrls.url_thirdLogin).build().execute(call);

    }

    /**
     * 发送短信验证码
     *
     * @param phone
     * @param key   register.code=>注册/forget.password=>忘记密码/phone.login=>手机号验证码登录/bind.third=>绑定三方）
     * @param call
     */
    public static void phoneCode(String phone, String key, Callback call) {
        Map<String, String> map = new HashMap<>();
        map.put("phone", phone);
        map.put("key", key);
        OkHttpUtils.getInstance().post().params(map).url(APIUrls.url_phoneCode).build().execute(call);

    }

    /**
     * 验证码登录
     *
     * @param phone
     * @param authCode
     * @param call
     */
    public static void phoneLogin(String phone, String authCode, Callback call) {
        Map<String, String> map = new HashMap<>();
        map.put("phone", phone);
        map.put("authCode", authCode);
        OkHttpUtils.getInstance().post().params(map).url(APIUrls.url_phoneLogin).build().execute(call);

    }

    /**
     * 修改密码
     *
     * @param phone
     * @param code
     * @param password
     * @param againPassword
     * @param call
     */
    public static void updatePasswordAndLogin(String phone, String code, String password, String againPassword, Callback call) {
        Map<String, String> map = new HashMap<>();
        map.put("phone", phone);
        map.put("code", code);
        map.put("password", password);
        map.put("againPassword", againPassword);
        OkHttpUtils.getInstance().post().params(map).url(APIUrls.url_updatePasswordAndLogin).build().execute(call);

    }

    /**
     * 用户忘记密码
     *
     * @param phone
     * @param authCode
     * @param password
     * @param password_confirmation
     * @param call
     */
    public static void forgetPassword(String phone, String authCode, String password, String password_confirmation, Callback call) {
        Map<String, String> map = new HashMap<>();
        map.put("phone", phone);
        map.put("authCode", authCode);
        map.put("password", password);
        map.put("password_confirmation", password_confirmation);
        OkHttpUtils.getInstance().post().params(map).url(APIUrls.url_forgetPassword).build().execute(call);

    }

    /**
     * 注销用户
     *
     * @param tourist_id 类型下的唯一标识
     * @param call
     */
    public static void cancel(String tourist_id, Callback call) {
        Map<String, String> map = new HashMap<>();
        map.put("tourist_id", tourist_id);
        OkHttpUtils.getInstance().post().params(map).url(APIUrls.url_cancel).build().execute(call);

    }

    /**
     * 读取本地身份证信息
     *
     * @param tourist_id 类型下的唯一标识
     * @param call
     */
    public static void baseInfo(int tourist_id, Callback call) {
        Map<String, String> map = new HashMap<>();
        map.put("tourist_id", String.valueOf(tourist_id));
        OkHttpUtils.getInstance().post().params(map).url(APIUrls.url_baseInfo).build().execute(call);

    }

    /**
     * 我的-个人资料-修改
     * <p>
     * tourist_id	String
     * 用户id
     * <p>
     * avatar	String
     * name=>用户昵称 person_label=>个人标签 bus_label=>企业标签
     * <p>
     * 默认值: >头像
     *
     * @param tourist_id
     * @param call
     */
    public static void editBase(int tourist_id, String key, String value, Callback call) {
        Map<String, String> map = new HashMap<>();
        map.put("tourist_id", String.valueOf(tourist_id));
        map.put(key, value);
        OkHttpUtils.getInstance().post().params(map).url(APIUrls.url_editBase).build().execute(call);

    }

    /**
     * 视频-同城
     * <p>
     * city	String
     * 同城
     * <p>
     * per_page	Number
     * 每页条数 默认10
     *
     * @param call
     */
    public static void videoCity(int tourist_id, String city, int per_page, int page, Callback call) {
        Map<String, String> map = new HashMap<>();
        map.put("tourist_id", String.valueOf(tourist_id));
        map.put("city", city);
        map.put("per_page", String.valueOf(per_page));
        map.put("page", String.valueOf(page));
        OkHttpUtils.getInstance().post().params(map).url(APIUrls.videoCity).build().execute(call);

    }

    /**
     * 视频-搜索
     *
     * @param tourist_id
     * @param keyword
     * @param page
     * @param call
     */
    public static void videoSearch(int tourist_id, String keyword, int page, Callback call) {
        Map<String, String> map = new HashMap<>();
        map.put("tourist_id", String.valueOf(tourist_id));
        map.put("keyword", keyword);
        map.put("per_page", String.valueOf(10));
        map.put("page", String.valueOf(page));
        OkHttpUtils.getInstance().post().params(map).url(APIUrls.videoSearch).build().execute(call);

    }

    /**
     * 视频-关注
     *
     * @param tourist_id
     * @param per_page
     * @param page
     * @param call
     */
    public static void videoAttention(int tourist_id, int per_page, int page, Callback call) {
        Map<String, String> map = new HashMap<>();
        map.put("tourist_id", String.valueOf(tourist_id));
        map.put("per_page", String.valueOf(per_page));
        map.put("page", String.valueOf(page));
        OkHttpUtils.getInstance().post().params(map).url(APIUrls.videoAttention).build().execute(call);

    }

    /**
     * 获取个人标签
     *
     * @param call
     */
    public static void personCategory(Callback call) {
        Map<String, String> map = new HashMap<>();
        OkHttpUtils.getInstance().post().params(map).url(APIUrls.personCategory).build().execute(call);

    }

    public static void walletSet(int tourist_id, Callback call) {
        Map<String, String> map = new HashMap<>();
        map.put("tourist_id", String.valueOf(tourist_id));
        OkHttpUtils.getInstance().get().params(map).url(APIUrls.walletSet).build().execute(call);

    }

    /**
     * tourist_id	String
     * 用户id
     * <p>
     * money	String
     * 充值金额
     * <p>
     * type	String
     * 充值渠道 wechat=>微信 alipay=>支付宝 ios=>苹果
     * <p>
     * wallet_token	Number
     * 钱包充值兑换的平台币数量
     *
     * @param call
     */
    public static void cashPay(int tourist_id, String money, String type, int wallet_token, Callback call) {
        Map<String, String> map = new HashMap<>();
        map.put("tourist_id", String.valueOf(tourist_id));
        map.put("money", money);
        map.put("type", type);
        map.put("wallet_token", String.valueOf(wallet_token));
        OkHttpUtils.getInstance().post().params(map).url(APIUrls.cashPay).build().execute(call);

    }

    /**
     * 获取开通会员价格相关信息
     *
     * @param tourist_id
     * @param call
     */
    public static void userPrice(int tourist_id, Callback call) {
        Map<String, String> map = new HashMap<>();
        map.put("tourist_id", String.valueOf(tourist_id));
        OkHttpUtils.getInstance().get().params(map).url(APIUrls.userPrice).build().execute(call);

    }

    public static void vipDetail(int tourist_id, Callback call) {
        Map<String, String> map = new HashMap<>();
        map.put("tourist_id", String.valueOf(tourist_id));
        OkHttpUtils.getInstance().post().params(map).url(APIUrls.vipDetail).build().execute(call);

    }

    /**
     * 我的-认证
     * <p>
     * tourist_id	String
     * 用户id
     * <p>
     * real_name	String
     * 真实姓名/法人姓名
     * <p>
     * idcard	String
     * 身份证号码
     * <p>
     * front_photo	String
     * 身份证正面照
     * <p>
     * back_photo	String
     * 身份证背面照
     * <p>
     * license_photo	String
     * 营业执照
     * <p>
     * auth	String
     * 1 商家 2个人
     */
    public static void personAuth(int tourist_id, String real_name, String idcard, String front_photo, String back_photo, String license_photo, int auth, Callback call) {
        Map<String, String> map = new HashMap<>();
        map.put("tourist_id", String.valueOf(tourist_id));
        map.put("real_name", real_name);
        map.put("idcard", idcard);
        map.put("front_photo", front_photo);
        map.put("back_photo", back_photo);
        map.put("license_photo", license_photo);
        map.put("auth", String.valueOf(auth));
        OkHttpUtils.getInstance().post().params(map).url(APIUrls.personAuth).build().execute(call);

    }

    /**
     * tourist_id	Number
     * 用户id
     * <p>
     * name	String
     * 商家名称
     * <p>
     * industry	String
     * 行业
     * <p>
     * desc	String
     * 详情页
     * <p>
     * link	String
     * 联系方式
     * <p>
     * logo	String
     * logo
     * <p>
     * qrcode	String
     * 二维码
     */
    public static void businessBanner(int tourist_id, String name, String industry, String desc, String link, String logo, String qrcode, Callback call) {
        Map<String, String> map = new HashMap<>();
        map.put("tourist_id", String.valueOf(tourist_id));
        map.put("name", name);
        map.put("industry", industry);
        map.put("desc", desc);
        map.put("link", link);
        map.put("logo", logo);
        map.put("qrcode", qrcode);
        OkHttpUtils.getInstance().post().params(map).url(APIUrls.businessBanner).build().execute(call);

    }

    /**
     * 钱包-获取乐币和收入情况
     *
     * @param tourist_id
     * @param call
     */
    public static void walletInfo(int tourist_id, Callback call) {
        Map<String, String> map = new HashMap<>();
        map.put("tourist_id", String.valueOf(tourist_id));
        OkHttpUtils.getInstance().post().params(map).url(APIUrls.walletInfo).build().execute(call);

    }

    public static void homeVideosAssist(int tourist_id, int video_id, String url, Callback call) {
        Map<String, String> map = new HashMap<>();
        map.put("tourist_id", String.valueOf(tourist_id));
        map.put("video_id", String.valueOf(video_id));
        OkHttpUtils.getInstance().post().params(map).url(url).build().execute(call);

    }

    /**
     * 首页-推荐-视频详情
     *
     * @param tourist_id
     * @param video_id
     * @param call
     */
    public static void worksDetail(int tourist_id, int video_id, Callback call) {
        Map<String, String> map = new HashMap<>();
        map.put("browse_tourist_id", String.valueOf(tourist_id));
        map.put("video_id", String.valueOf(video_id));
        OkHttpUtils.getInstance().post().tag(APIUrls.worksDetail).params(map).url(APIUrls.worksDetail).build().execute(call);

    }

    /**
     * 分享数量
     *
     * @param tourist_id
     * @param video_id
     * @param call
     */
    public static void homeShare(int tourist_id, int video_id, Callback call) {
        Map<String, String> map = new HashMap<>();
        map.put("tourist_id", String.valueOf(tourist_id));
        map.put("video_id", String.valueOf(video_id));
        OkHttpUtils.getInstance().post().params(map).url(APIUrls.homeShare).build().execute(call);

    }


    public static void orders(int tourist_id, Callback call) {
        Map<String, String> map = new HashMap<>();
        map.put("tourist_id", String.valueOf(tourist_id));
        OkHttpUtils.getInstance().post().params(map).url(APIUrls.orders).build().execute(call);

    }

    /**
     * 钱包-获取明细
     *
     * @param tourist_id
     * @param call
     */
    public static void walletRecord(int tourist_id, Callback call) {
        Map<String, String> map = new HashMap<>();
        map.put("tourist_id", String.valueOf(tourist_id));
        OkHttpUtils.getInstance().post().params(map).url(APIUrls.walletRecord).build().execute(call);

    }


    //======================================= 咖佬 =================================================


    /**
     * tourist_id	String
     * 用户id
     * <p>
     * per_page	String
     * 每页条数（非必填 默认10）
     * <p>
     * page	String
     * 页数 （非必填 默认1）
     *
     * @param tourist_id
     * @param per_page
     * @param call
     */
    public static void homeRecommend(int tourist_id, int per_page, int page, Callback call) {
        Map<String, String> map = new HashMap<>();
        map.put("tourist_id", String.valueOf(tourist_id));
        map.put("per_page", String.valueOf(per_page));
        map.put("page", String.valueOf(page));
        OkHttpUtils.getInstance().post().params(map).url(APIUrls.homeRecommend).build().execute(call);

    }

    /**
     * category_id	String
     * 行业id
     */
    public static void homeCity(int category_id, Callback call) {
        Map<String, String> map = new HashMap<>();
        map.put("per_page", String.valueOf(10));
        map.put("category_id", String.valueOf(category_id));
        OkHttpUtils.getInstance().post().params(map).url(APIUrls.homeCity).build().execute(call);

    }

    /**
     * 获取行业分类
     *
     * @param call
     */
    public static void category(Callback call) {
        Map<String, String> map = new HashMap<>();
        OkHttpUtils.getInstance().post().params(map).url(APIUrls.category).build().execute(call);

    }

    /**
     * 首页-发现
     * <p>
     * 0.0.0
     *
     * @param tourist_id
     * @param call
     */
    public static void discover(int tourist_id, Callback call) {
        Map<String, String> map = new HashMap<>();
        map.put("tourist_id", String.valueOf(tourist_id));
        OkHttpUtils.getInstance().post().params(map).url(APIUrls.discover).build().execute(call);

    }

    /**
     * 首页-发现-发布动态
     * <p>
     * 0.0.0
     * tourist_id	String
     * 用户id
     * <p>
     * img	String
     * 图片
     * <p>
     * desc	String
     * 描述
     *
     * @param tourist_id
     * @param call
     */
    public static void publishDiscover(int tourist_id, String desc, String img, Callback call) {
        Map<String, String> map = new HashMap<>();
        map.put("tourist_id", String.valueOf(tourist_id));
        map.put("desc", desc);
        map.put("img", img);
        OkHttpUtils.getInstance().post().params(map).url(APIUrls.publishDiscover).build().execute(call);

    }

    /**
     * 首页-搜索
     * <p>
     * key_word	String
     * 关键词
     * <p>
     * bus_label	String
     * 行业标签
     * <p>
     * addr	String
     * 地址
     * <p>
     * tourist_id	String
     * 用户id
     *
     * @param call
     */
    public static void search(int tourist_id, String key_word, String bus_label, String person_label, String addr, Callback call) {
        Map<String, String> map = new HashMap<>();
        map.put("tourist_id", String.valueOf(tourist_id));
        map.put("key_word", key_word);
        if (!bus_label.equals("全部")) {
            map.put("bus_label", bus_label);
        }
        if (!person_label.equals("全部")) {
            map.put("person_label", person_label);
        }
        map.put("addr", addr);
        Log.i(TAG, "search: " + map.toString());
        OkHttpUtils.getInstance().post().params(map).url(APIUrls.search).build().execute(call);
    }

    /**
     * 我的-喜欢
     * <p>
     * 0.0.0
     *
     * @param tourist_id
     * @param call
     */
    public static void personAssist(int tourist_id, Callback call) {
        Map<String, String> map = new HashMap<>();
        map.put("tourist_id", String.valueOf(tourist_id));
        OkHttpUtils.getInstance().post().params(map).url(APIUrls.personAssist).build().execute(call);

    }

    public static void homeSelfAttention(int tourist_id, Callback call) {
        Map<String, String> map = new HashMap<>();
        map.put("tourist_id", String.valueOf(tourist_id));
        OkHttpUtils.getInstance().post().params(map).url(APIUrls.homeSelfAttention).build().execute(call);

    }

    /**
     * 首页-个人主页-获取留言
     *
     * @param tourist_id
     * @param call
     */
    public static void homeLeave(int tourist_id, Callback call) {
        Map<String, String> map = new HashMap<>();
        map.put("tourist_id", String.valueOf(tourist_id));
        OkHttpUtils.getInstance().post().params(map).url(APIUrls.homeLeave).build().execute(call);

    }

    public static void leaveUser(int tourist_id, Callback call) {
        Map<String, String> map = new HashMap<>();
        map.put("tourist_id", String.valueOf(tourist_id));
        OkHttpUtils.getInstance().post().params(map).url(APIUrls.leaveUser).build().execute(call);

    }

    public static void leaveUserDetail(int tourist_id, int leave_tourist_id, Callback call) {
        Map<String, String> map = new HashMap<>();
        map.put("tourist_id", String.valueOf(tourist_id));
        map.put("leave_tourist_id", String.valueOf(leave_tourist_id));
        OkHttpUtils.getInstance().post().params(map).url(APIUrls.leaveUserDetail).build().execute(call);

    }

    public static void delVideo(int video_id, Callback call) {
        Map<String, String> map = new HashMap<>();
        map.put("video_id", String.valueOf(video_id));
        OkHttpUtils.getInstance().post().params(map).url(APIUrls.delVideo).build().execute(call);

    }

    /**
     * tourist_id	String
     * 用户id
     * <p>
     * be_tourist_id	String
     * 被留言用户id
     * <p>
     * content	String
     * 留言内容
     * <p>
     * parent_id	String
     * 留言父级id
     *
     * @param tourist_id
     * @param call
     */
    public static void publishLeave(int tourist_id, int be_tourist_id, String content, int parent_id, Callback call) {
        Map<String, String> map = new HashMap<>();
        map.put("tourist_id", String.valueOf(tourist_id));
        map.put("be_tourist_id", String.valueOf(be_tourist_id));
        map.put("parent_id", String.valueOf(parent_id));
        map.put("content", content);
        OkHttpUtils.getInstance().post().params(map).url(APIUrls.publishLeave).build().execute(call);

    }

    /**
     * tourist_id	String
     * 用户id
     * <p>
     * discover_id	String
     * 发现id
     * <p>
     * content	String
     * 评论
     *
     * @param tourist_id
     * @param discover_id
     * @param content
     * @param call
     */
    public static void publishDiscoverComment(int tourist_id, int discover_id, String content, Callback call) {
        Map<String, String> map = new HashMap<>();
        map.put("tourist_id", String.valueOf(tourist_id));
        map.put("discover_id", String.valueOf(discover_id));
        map.put("content", content);
        OkHttpUtils.getInstance().post().params(map).url(APIUrls.publishDiscoverComment).build().execute(call);

    }

    public static void discoverComment(int tourist_id, int discover_id, int per_page, Callback call) {
        Map<String, String> map = new HashMap<>();
        map.put("tourist_id", String.valueOf(tourist_id));
        map.put("discover_id", String.valueOf(discover_id));
        map.put("per_page", String.valueOf(per_page));
        OkHttpUtils.getInstance().post().params(map).url(APIUrls.discoverComment).build().execute(call);

    }

    /**
     * 消息-点赞
     * <p>
     * 0.0.0
     *
     * @param tourist_id
     * @param call
     */
    public static void messageAssist(int tourist_id, Callback call) {
        Map<String, String> map = new HashMap<>();
        map.put("tourist_id", String.valueOf(tourist_id));
        OkHttpUtils.getInstance().post().params(map).url(APIUrls.messageAssist).build().execute(call);

    }

    /**
     * 消息-粉丝
     * <p>
     * 0.0.0
     *
     * @param tourist_id
     * @param call
     */
    public static void messageFlower(int tourist_id, Callback call) {
        Map<String, String> map = new HashMap<>();
        map.put("tourist_id", String.valueOf(tourist_id));
        OkHttpUtils.getInstance().post().params(map).url(APIUrls.messageFlower).build().execute(call);

    }

    /**
     * 消息-评论
     * <p>
     * 0.0.0
     *
     * @param tourist_id
     * @param call
     */
    public static void messageComment(int tourist_id, Callback call) {
        Map<String, String> map = new HashMap<>();
        map.put("tourist_id", String.valueOf(tourist_id));
        OkHttpUtils.getInstance().post().params(map).url(APIUrls.messageComment).build().execute(call);

    }

    /**
     * 消息-通知
     * <p>
     * 0.0.0
     *
     * @param tourist_id
     * @param call
     */
    public static void messageNotice(int tourist_id, Callback call) {
        Map<String, String> map = new HashMap<>();
        map.put("tourist_id", String.valueOf(tourist_id));
        OkHttpUtils.getInstance().post().params(map).url(APIUrls.messageNotice).build().execute(call);

    }

    /**
     * 添加我的作品
     * tourist_id	String
     * 用户id
     * <p>
     * name	String
     * 产品名称
     * <p>
     * desc	String
     * 描述
     * <p>
     * img	String
     * 图片
     *
     * @param tourist_id
     * @param call
     */
    public static void createGood(int tourist_id, String name, String desc, String img, Callback call) {
        Map<String, String> map = new HashMap<>();
        map.put("tourist_id", String.valueOf(tourist_id));
        map.put("name", name);
        map.put("desc", desc);
        map.put("img", img);
        OkHttpUtils.getInstance().post().params(map).url(APIUrls.createGood).build().execute(call);

    }

    /**
     * 我的-编辑产品
     * <p>
     * good_id	String
     * 商品id
     * <p>
     * name	String
     * 产品名称
     * <p>
     * desc	String
     * 描述
     * <p>
     * img	String
     * 图片
     */
    public static void editGood(int good_id, String name, String desc, String img, Callback call) {
        Map<String, String> map = new HashMap<>();
        map.put("good_id", String.valueOf(good_id));
        map.put("name", name);
        map.put("desc", desc);
        map.put("img", img);
        OkHttpUtils.getInstance().post().params(map).url(APIUrls.editGood).build().execute(call);

    }

    public static void deleteGood(int tourist_id, int good_id, Callback call) {
        Map<String, String> map = new HashMap<>();
        map.put("tourist_id", String.valueOf(tourist_id));
        map.put("good_id", String.valueOf(good_id));
        OkHttpUtils.getInstance().post().params(map).url(APIUrls.deleteGood).build().execute(call);

    }

    /**
     * 我的作品
     *
     * @param tourist_id
     * @param call
     */
    public static void personGood(int tourist_id, Callback call) {
        Map<String, String> map = new HashMap<>();
        map.put("tourist_id", String.valueOf(tourist_id));
        OkHttpUtils.getInstance().post().params(map).url(APIUrls.personGood).build().execute(call);

    }

    /**
     * 我的作品详情
     *
     * @param good_id
     * @param call
     */
    public static void goodDetail(int good_id, Callback call) {
        Map<String, String> map = new HashMap<>();
        map.put("good_id", String.valueOf(good_id));
        OkHttpUtils.getInstance().post().params(map).url(APIUrls.goodDetail).build().execute(call);

    }

    /**
     * 我的关注
     *
     * @param tourist_id
     * @param call
     */
    public static void attention(int tourist_id, Callback call) {
        Map<String, String> map = new HashMap<>();
        map.put("tourist_id", String.valueOf(tourist_id));
        OkHttpUtils.getInstance().post().params(map).url(APIUrls.attention).build().execute(call);

    }

    /**
     * 我的粉丝
     *
     * @param tourist_id
     * @param call
     */
    public static void follower(int tourist_id, Callback call) {
        Map<String, String> map = new HashMap<>();
        map.put("tourist_id", String.valueOf(tourist_id));
        OkHttpUtils.getInstance().post().params(map).url(APIUrls.follower).build().execute(call);

    }

    /**
     * 首页-搜索
     *
     * @param word
     * @param category_id
     * @param call
     */
    public static void searchWord(String word, int category_id, Callback call) {
        Map<String, String> map = new HashMap<>();
        map.put("word", word);
        map.put("category_id", String.valueOf(category_id));
        OkHttpUtils.getInstance().get().params(map).url(APIUrls.url_searchWork).build().execute(call);
    }

    /**
     * 首页-发现-点赞
     *
     * @param tourist_id
     * @param discover_id
     * @param call
     */
    public static void discoverLike(int tourist_id, int discover_id, String url, Callback call) {
        Map<String, String> map = new HashMap<>();
        map.put("tourist_id", String.valueOf(tourist_id));
        map.put("discover_id", String.valueOf(discover_id));
        OkHttpUtils.getInstance().post().params(map).url(url).build().execute(call);
    }

    //======================================= 点逗 =================================================

    public static void isFollow(int tourist_id, int follow_id, Callback call) {
        Map<String, String> map = new HashMap<>();
        map.put("tourist_id", String.valueOf(tourist_id));
        map.put("video_id", String.valueOf(follow_id));
        OkHttpUtils.getInstance().post().params(map).url(APIUrls.url_isFollow).build().execute(call);

    }

    public static void publishWork(int tourist_id, int follow_id, String nav_name, String link, String desc, String img, String addr, Callback call) {
        Map<String, String> map = new HashMap<>();
        map.put("tourist_id", String.valueOf(tourist_id));
        map.put("nav_id", String.valueOf(follow_id));
        map.put("nav_name", nav_name);
        map.put("link", link);
        map.put("desc", desc);
        map.put("img", img);
        map.put("addr", addr);
        OkHttpUtils.getInstance().post().params(map).url(APIUrls.url_publishWork).build().execute(call);
    }

    /**
     * tourist_id	Number
     * 用户id
     * <p>
     * video	String
     * 视频连接
     * <p>
     * category_id	Number
     * 一线分类id
     * <p>
     * img	String
     * 封面图
     * <p>
     * relation_good	String
     * 是否关联商品 1关联 2不关联
     * <p>
     * good_name	String
     * 商品名称
     * <p>
     * good_link	String
     * 商品购买链接
     * <p>
     * good_img	String
     * 商品主图
     */
    public static void publishVideo(int tourist_id, String desc, String img, String video,
                                    String category_id, String addr,
                                    int relation_good, String good_name, String good_link, String good_img, Callback call) {
        Map<String, String> map = new HashMap<>();
        map.put("tourist_id", String.valueOf(tourist_id));
        map.put("video", video);
        map.put("desc", desc);
        map.put("img", img);
        map.put("category_id", category_id);
        map.put("addr", addr);
        map.put("relation_good", String.valueOf(relation_good));
        if (relation_good == 1) {
            map.put("good_name", good_name);
            map.put("good_link", good_link);
            map.put("good_img", good_img);
        }
        Log.i(TAG, "publishVideo: " + map.toString());
        OkHttpUtils.getInstance().post().params(map).url(APIUrls.publishVideo).build().execute(call);
    }

    /**
     * 重置密码
     *
     * @param tourist_id
     * @param old_password
     * @param password
     * @param call
     */
    public static void resetPassword(String tourist_id, String old_password, String password, String confirm_password, Callback call) {
        Map<String, String> map = new HashMap<>();
        map.put("tourist_id", tourist_id);
        map.put("old_password", old_password);
        map.put("password", password);
        map.put("confirm_password", password);
        OkHttpUtils.getInstance().post().params(map).url(APIUrls.url_resetPassword).build().execute(call);

    }

    public static void commonStartUp(Callback call) {
        Map<String, String> map = new HashMap<>();
        OkHttpUtils.getInstance().get().params(map).url(APIUrls.url_commonStartUp).build().execute(call);

    }

    /**
     * 获取轮播图
     *
     * @param call
     */
    public static void commonBanner(Callback call) {
        Map<String, String> map = new HashMap<>();
        OkHttpUtils.getInstance().get().params(map).url(APIUrls.url_commonBanner).build().execute(call);

    }

    /**
     * 获取首页导航分类
     *
     * @param call
     */
    public static void commonNav(Callback call) {
        Map<String, String> map = new HashMap<>();
        OkHttpUtils.getInstance().get().params(map).url(APIUrls.url_commonNav).build().execute(call);

    }

    /**
     * 用户搜索作品
     *
     * @param tourist_id 搜索人id(非必填)
     * @param type       1 最热 ；2 推荐
     * @param nav_id     分类id(非必填)
     * @param word       关键词搜索(非必填)
     * @param per_page   每页条数(非必填 默认10)
     * @param page       页数(非必填 默认1)
     * @param call
     */
    public static void searchWork(int tourist_id, int type, int nav_id, String word, int per_page, int page, Callback call) {
        Map<String, String> map = new HashMap<>();
        if (!CommonUtil.isBlank(String.valueOf(tourist_id))) {
            map.put("tourist_id", String.valueOf(tourist_id));
        }
        if (!CommonUtil.isBlank(String.valueOf(type))) {
            map.put("type", String.valueOf(type));
        }
        if (!CommonUtil.isBlank(String.valueOf(type))) {
            map.put("nav_id", String.valueOf(nav_id));
        }
        if (!CommonUtil.isBlank(word)) {
            map.put("word", word);
        }
        map.put("per_page", String.valueOf(per_page));
        map.put("page", String.valueOf(page));
        OkHttpUtils.getInstance().get().params(map).url(APIUrls.url_searchWork).build().execute(call);

    }

    public static void searchWorkHome(int type, int nav_id, int per_page, int page, Callback call) {
        Map<String, String> map = new HashMap<>();
        map.put("type", String.valueOf(type));
        map.put("nav_id", nav_id == 0 ? "hot" : String.valueOf(nav_id));
        map.put("per_page", String.valueOf(per_page));
        map.put("page", String.valueOf(page));
        OkHttpUtils.getInstance().get().params(map).url(APIUrls.url_searchWork).build().execute(call);

    }

    public static void searchWorkType(int nav_id, int per_page, int page, Callback call) {
        Map<String, String> map = new HashMap<>();
        map.put("nav_id", String.valueOf(nav_id));
        map.put("per_page", String.valueOf(per_page));
        map.put("page", String.valueOf(page));
        OkHttpUtils.getInstance().get().params(map).url(APIUrls.url_searchWork).build().execute(call);

    }

    public static void searchWorkWord(String word, int per_page, int page, Callback call) {
        Map<String, String> map = new HashMap<>();
        map.put("word", word);
        map.put("per_page", String.valueOf(per_page));
        map.put("page", String.valueOf(page));
        map.put("page", String.valueOf(page));
        OkHttpUtils.getInstance().get().params(map).url(APIUrls.url_searchWork).build().execute(call);
    }

    /**
     * 首页-视频-创建评论
     *
     * @param tourist_id
     * @param video_id
     * @param body
     * @param call
     */
    public static void homePageVideosCreateComment(int tourist_id, int video_id, String body, Callback call) {
        Map<String, String> map = new HashMap<>();
        map.put("tourist_id", String.valueOf(tourist_id));
        map.put("video_id", String.valueOf(video_id));
        map.put("body", body);
        OkHttpUtils.getInstance().post().params(map).url(APIUrls.url_homePageVideosCreateComment).build().execute(call);

    }

    /**
     * 首页-视频-获取评论
     *
     * @param video_id
     * @param call
     */
    public static void homeComment(int video_id, Callback call) {
        Map<String, String> map = new HashMap<>();
//        map.put("perPage", String.valueOf(perPage));
        map.put("video_id", String.valueOf(video_id));
        OkHttpUtils.getInstance().post().params(map).url(APIUrls.homeComment).build().execute(call);

    }

    public static void contentIsAssist(int tourist_id, int content_id, Callback call) {
        Map<String, String> map = new HashMap<>();
        map.put("tourist_id", String.valueOf(tourist_id));
        map.put("content_id", String.valueOf(content_id));
        OkHttpUtils.getInstance().get().params(map).url(APIUrls.url_contentIsAssist).build().execute(call);
    }

    public static void publishContentAssist(int tourist_id, int content_id, String assistUrl, Callback call) {
        Map<String, String> map = new HashMap<>();
        map.put("tourist_id", String.valueOf(tourist_id));
        map.put("content_id", String.valueOf(content_id));
        OkHttpUtils.getInstance().post().params(map).url(assistUrl).build().execute(call);
    }

    public static void showContentComment(int content_id, Callback call) {
        Map<String, String> map = new HashMap<>();
        map.put("content_id", String.valueOf(content_id));
        OkHttpUtils.getInstance().get().params(map).url(APIUrls.url_showContentComment).build().execute(call);
    }

    public static void publishComment(int tourist_id, int content_id, String body, Callback call) {
        Map<String, String> map = new HashMap<>();
        map.put("tourist_id", String.valueOf(tourist_id));
        map.put("content_id", String.valueOf(content_id));
        map.put("body", body);
        OkHttpUtils.getInstance().post().params(map).url(APIUrls.url_publishComment).build().execute(call);
    }

    /**
     * 获取作品详情
     *
     * @param content_id 作品ID
     * @param call
     */
    public static void workDetail(int tourist_id, int content_id, Callback call) {
        Map<String, String> map = new HashMap<>();
        if (tourist_id != -1) {
            map.put("tourist_id", String.valueOf(tourist_id));
        }
        map.put("content_id", String.valueOf(content_id));
        OkHttpUtils.getInstance().get().params(map).url(APIUrls.url_workDetail).build().execute(call);

    }

    public static void delWorks(int tourist_id, int video_id, Callback call) {
        Map<String, String> map = new HashMap<>();
        map.put("tourist_id", String.valueOf(tourist_id));
        map.put("video_id", String.valueOf(video_id));
        OkHttpUtils.getInstance().post().params(map).url(APIUrls.url_delWorks).build().execute(call);

    }

    /**
     * 首页-视频-点赞
     *
     * @param tourist_id
     * @param video_id
     * @param url
     * @param call
     */
    public static void homePageVideosAssist(int tourist_id, int video_id, String url, Callback call) {
        Map<String, String> map = new HashMap<>();
        map.put("tourist_id", String.valueOf(tourist_id));
        map.put("video_id", String.valueOf(video_id));
        OkHttpUtils.getInstance().post().params(map).url(url).build().execute(call);

    }

    /**
     * 首页-用户关注用户
     *
     * @param tourist_id
     * @param follow_id  要关注的用户id
     * @param call
     */
    public static void homePagePersonFollow(int tourist_id, int follow_id, String url, Callback call) {
        Map<String, String> map = new HashMap<>();
        map.put("tourist_id", String.valueOf(tourist_id));
        map.put("follow_id", String.valueOf(follow_id));
        OkHttpUtils.getInstance().post().params(map).url(url).build().execute(call);

    }

    /**
     * 删除自己作品
     *
     * @param tourist_id
     * @param contentIds
     * @param call
     */
    public static void deleteContent(int tourist_id, String contentIds, Callback call) {
        Map<String, String> map = new HashMap<>();
        map.put("tourist_id", String.valueOf(tourist_id));
        map.put("content_ids", contentIds);
        OkHttpUtils.getInstance().post().params(map).url(APIUrls.url_deleteContent).build().execute(call);

    }

    /**
     * 删除点赞作品
     *
     * @param tourist_id
     * @param contentIds
     * @param call
     */
    public static void deleteAssist(int tourist_id, String contentIds, Callback call) {
        Map<String, String> map = new HashMap<>();
        map.put("tourist_id", String.valueOf(tourist_id));
        map.put("content_ids", contentIds);
        OkHttpUtils.getInstance().post().params(map).url(APIUrls.url_deleteAssist).build().execute(call);

    }

    public static void playTime(int content_id, Callback call) {
        Map<String, String> map = new HashMap<>();
        map.put("content_id", String.valueOf(content_id));
        OkHttpUtils.getInstance().post().params(map).url(APIUrls.url_playTime).build().execute(call);

    }

    /**
     * 获取我关注的人
     *
     * @param tourist_id 用户id（必填）.
     * @param per_page   每页条数（非必填 默认10）.
     * @param page       页数（非必填 默认1）.
     * @param call
     */
    public static void centerConcern(int tourist_id, int per_page, int page, Callback call) {
        Map<String, String> map = new HashMap<>();
        map.put("tourist_id", String.valueOf(tourist_id));
        map.put("per_page", String.valueOf(per_page));
        map.put("page", String.valueOf(page));
        OkHttpUtils.getInstance().get().params(map).url(APIUrls.url_centerConcern).build().execute(call);

    }

    /**
     * 获取关注我的人
     *
     * @param tourist_id 用户id（必填）.
     * @param per_page   每页条数（非必填 默认10）.
     * @param page       页数（非必填 默认1）.
     * @param call
     */
    public static void centerAttention(int tourist_id, int per_page, int page, Callback call) {
        Map<String, String> map = new HashMap<>();
        map.put("tourist_id", String.valueOf(tourist_id));
        map.put("per_page", String.valueOf(per_page));
        map.put("page", String.valueOf(page));
        OkHttpUtils.getInstance().get().params(map).url(APIUrls.url_centerAttention).build().execute(call);

    }

    public static void centerDiscuss(int tourist_id, Callback call) {
        Map<String, String> map = new HashMap<>();
        map.put("tourist_id", String.valueOf(tourist_id));
        OkHttpUtils.getInstance().get().params(map).url(APIUrls.url_centerDiscuss).build().execute(call);

    }

    public static void centerFabulous(int tourist_id, Callback call) {
        Map<String, String> map = new HashMap<>();
        map.put("tourist_id", String.valueOf(tourist_id));
        OkHttpUtils.getInstance().get().params(map).url(APIUrls.url_centerFabulous).build().execute(call);

    }

    public static void commonMessage(Callback call) {
        Map<String, String> map = new HashMap<>();
        OkHttpUtils.getInstance().get().params(map).url(APIUrls.url_commonMessage).build().execute(call);

    }

    public static void centerSelfWork(int tourist_id, Callback call) {
        Map<String, String> map = new HashMap<>();
        map.put("tourist_id", String.valueOf(tourist_id));
        OkHttpUtils.getInstance().post().params(map).url(APIUrls.personWorks).build().execute(call);
    }

    public static void centerSelfWork(int tourist_id, int per_page, int page, Callback call) {
        Map<String, String> map = new HashMap<>();
        map.put("tourist_id", String.valueOf(tourist_id));
        map.put("per_page", String.valueOf(per_page));
        map.put("page", String.valueOf(page));
        OkHttpUtils.getInstance().post().params(map).url(APIUrls.personWorks).build().execute(call);
    }

    public static void favouriteContent(int tourist_id, int per_page, int page, Callback call) {
        Map<String, String> map = new HashMap<>();
        map.put("tourist_id", String.valueOf(tourist_id));
        map.put("per_page", String.valueOf(per_page));
        map.put("page", String.valueOf(page));
        OkHttpUtils.getInstance().get().params(map).url(APIUrls.url_favouriteContent).build().execute(call);
    }

    public static void personAbout(Callback call) {
        Map<String, String> map = new HashMap<>();
        OkHttpUtils.getInstance().post().params(map).url(APIUrls.url_personAbout).build().execute(call);
    }

    public static void centerComment(int tourist_id, String comment, Callback call) {
        Map<String, String> map = new HashMap<>();
        map.put("tourist_id", String.valueOf(tourist_id));
        map.put("comment", comment);
        OkHttpUtils.getInstance().post().params(map).url(APIUrls.url_centerComment).build().execute(call);
    }

    public static void centerFollow(int tourist_id, int follow_id, String followUrl, Callback call) {
        Map<String, String> map = new HashMap<>();
        map.put("tourist_id", String.valueOf(tourist_id));
        map.put("follow_id", String.valueOf(follow_id));
        OkHttpUtils.getInstance().post().params(map).url(followUrl).build().execute(call);
    }

    public static void centerAssist(int tourist_id, int per_page, int page, Callback call) {
        Map<String, String> map = new HashMap<>();
        map.put("tourist_id", String.valueOf(tourist_id));
        map.put("per_page", String.valueOf(per_page));
        map.put("page", String.valueOf(page));
        OkHttpUtils.getInstance().get().params(map).url(APIUrls.url_centerAssist).build().execute(call);
    }

    /**
     * 修改个人信息
     *
     * @param avatar
     * @param name
     * @param birth
     * @param sex
     * @param autograph
     * @param weibo
     * @param call
     */
    public static void editPersonal(String avatar, String name, String birth, String sex, String autograph, String weibo, Callback call) {
        Map<String, String> map = new HashMap<>();
        map.put("avatar", avatar);
        map.put("name", name);
        map.put("birth", birth);
        map.put("sex", sex);
        map.put("autograph", autograph);
        map.put("weibo", weibo);
        OkHttpUtils.getInstance().post().params(map).url(APIUrls.url_editPersonal).build().execute(call);

    }

    public static void editPersonal(int tourist_id, int base_id, String avatar, String name, Callback call) {
        Map<String, String> map = new HashMap<>();
        map.put("tourist_id", String.valueOf(tourist_id));
        map.put("base_id", String.valueOf(base_id));
        map.put("avatar", avatar);
        map.put("name", name);
        OkHttpUtils.getInstance().post().params(map).url(APIUrls.url_editPersonal).build().execute(call);

    }

    /**
     * 举报
     *
     * @param tourist_id
     * @param report_id
     * @param report
     * @param call
     */
    public static void report(String tourist_id, String report_id, String report, Callback call) {
        Map<String, String> map = new HashMap<>();
        map.put("tourist_id", tourist_id);
        map.put("report_id", report_id);
        map.put("report", report);
        OkHttpUtils.getInstance().post().params(map).url(APIUrls.url_report).build().execute(call);

    }


    /**
     * 举报
     *
     * @param tourist_id
     * @param face_photo //身份证正面照片
     * @param back_photo //身份证反面照片
     * @param call
     */
    public static void profile(String tourist_id, String face_photo, String back_photo, Callback call) {
        Map<String, String> map = new HashMap<>();
        map.put("tourist_id", tourist_id);
        map.put("face_photo", face_photo);
        map.put("back_photo", back_photo);
        OkHttpUtils.getInstance().post().params(map).url(APIUrls.url_profile).build().execute(call);

    }

    /**
     * 上传文件
     *
     * @param file
     * @param call
     */
    public static void fileUpload(String file, String name, Callback call) {
        OkHttpUtils.getInstance().post().addFile("file", name, new File(file)).url(APIUrls.url_fileUpload).build().execute(call);

    }

    public static void createSecurityToken(Callback call) {
        Map<String, String> map = new HashMap<>();
        OkHttpUtils.getInstance().get().params(map).url(APIUrls.url_createSecurityToken).build().execute(call);

    }
}
