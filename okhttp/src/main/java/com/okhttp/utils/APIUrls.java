package com.okhttp.utils;

public class APIUrls {


    public final static String url_domain = "https://kuaimutj.com/";

    public final static String url_fileUpload = url_domain + "api/common/fileUpload";
    public final static String url_createSecurityToken = url_domain + "api/common/ossToken";

    //用户信息
    public final static String url_phoneCode = url_domain + "api/common/phoneCode";
    public final static String url_phoneLogin = url_domain + "api/common/phoneLogin";
    public final static String url_login = url_domain + "api/common/login";
    public final static String url_thirdLogin = url_domain + "api/common/thirdLogin";
    public final static String url_updatePasswordAndLogin = url_domain + "api/common/phoneCode";
    public final static String url_register = url_domain + "api/common/register";
    //判断用户是否绑定过三方
    public final static String bindThird = url_domain + "api/common/bindThird";
    public final static String checkUserIsRegister = url_domain + "api/common/checkUserIsRegister";
    public final static String url_forgetPassword = url_domain + "api/common/forgetPassword";
    public final static String url_cancel = url_domain + "api/center/cancel";
    public final static String url_baseInfo = url_domain + "api/person/info";
    public final static String url_editBase = url_domain + "api/person/editBase";
    public final static String url_resetPassword = url_domain + "api/common/resetPassword";
    public final static String url_editPersonal = url_domain + "api/center/editPersonal";
    public final static String url_report = url_domain + "api/center/report";
    public final static String url_profile = url_domain + "api/center/profile";


    //同城
    public final static String videoCity = url_domain + "api/video/city";
    public final static String videoSearch = url_domain + "api/video/search";
    //关注
    public final static String videoAttention = url_domain + "api/video/attention";



    //===================================== 咖佬 ===================================================

    public final static String homeRecommend = url_domain + "api/home/recommend";
//    public final static String homeCity = url_domain + "api/home/city";
    public final static String homeCity = url_domain + "api/video/line";
    public final static String category = url_domain + "api/common/category";

    //发现
    public final static String discover = url_domain + "api/home/discover";
    public final static String discoverComment = url_domain + "api/home/discoverComment";
    public final static String publishDiscover = url_domain + "api/home/publishDiscover";
    public final static String publishDiscoverComment = url_domain + "api/home/publishDiscoverComment";
    public final static String discoverLike = url_domain + "api/home/discoverLike";
    public final static String discoverUnLike = url_domain + "api/home/discoverUnLike";
    public final static String delVideo = url_domain + "api/person/delVideo";

    //推荐
    public final static String homeAssist = url_domain + "api/video/assist";
    public final static String homeDeleteAssist = url_domain + "api/video/cancelAssist";
    public final static String worksDetail = url_domain + "api/person/worksDetail";
    public final static String homeShare = url_domain + "api/home/share";

    public final static String search = url_domain + "api/home/search";

    public final static String personAssist = url_domain + "api/message/assist";
    public final static String homeSelfAttention = url_domain + "api/home/selfAttention";
    public final static String homeLeave = url_domain + "api/home/leave";
    public final static String publishLeave = url_domain + "api/message/publishLeave";
//    public final static String homeComment = url_domain + "api/home/comment";
    public final static String homeComment = url_domain + "api/video/comment";
    public final static String leaveUser = url_domain + "api/message/leaveUser";
    public final static String leaveUserDetail = url_domain + "api/message/leaveUserDetail";

    //消息
    public final static String messageAssist = url_domain + "api/message/assist";
    public final static String messageFlower = url_domain + "api/message/flower";
    public final static String messageComment = url_domain + "api/message/comment";
    public final static String messageNotice = url_domain + "api/message/notice";


    //我的
    public final static String createGood = url_domain + "api/person/createGood";
    public final static String editGood = url_domain + "api/person/editGood";
    public final static String deleteGood = url_domain + "api/person/deleteGood";
    public final static String personGood = url_domain + "api/person/good";
    public final static String goodDetail = url_domain + "api/person/goodDetail";
//    public final static String attention = url_domain + "api/person/attention";
    public final static String attention = url_domain + "api/message/flower";
//    public final static String follower = url_domain + "api/person/follower";
    public final static String follower = url_domain + "api/person/fans";
    public final static String personCategory = url_domain + "api/common/personCategory";
    public final static String cashPay = url_domain + "api/cash/pay";
    public final static String walletSet = url_domain + "api/common/walletSet";
    public final static String userPrice = url_domain + "api/common/userPrice";
    public final static String vipDetail = url_domain + "api/cash/vip/detail";
    public final static String orders = url_domain + "api/cash/orders";
    public final static String walletInfo = url_domain + "api/wallet/info";
    public final static String walletRecord = url_domain + "api/wallet/record";
    public final static String personAuth = url_domain + "api/person/auth";
    public final static String businessBanner = url_domain + "api/person/businessBanner";


    //首页-用户关注用户
    public final static String url_homePagePersonFollow = url_domain + "api/homepage/personFollow";
    //首页-用户取消关注用户
    public final static String url_homePagePersonUnFollow = url_domain + "api/homepage/personUnFollow";
    //首页-用户是否关注用户
    public final static String url_homePagePersonIsFollow = url_domain + "api/homepage/personIsFollow";
    //首页-视频-是否点赞
    public final static String url_homePageVideosIsAssist = url_domain + "api/homepageVideos/isAssist";
    //首页-视频-点赞
    public final static String url_homePageVideosAssist = url_domain + "api/homepageVideos/assist";
    //首页-视频-点赞取消
    public final static String url_homePageVideosCancelAssist = url_domain + "api/homepageVideos/cancelAssist";

    public final static String url_commonStartUp = url_domain + "api/common/startUp";
    public final static String url_commonNav = url_domain + "api/common/nav";
    public final static String url_commonBanner = url_domain + "api/common/banner";

    //用户搜索作品
    public final static String url_searchWork = url_domain + "api/center/searchWork";
    //获取作品详情
    public final static String url_workDetail = url_domain + "api/center/workDetail";
    //删除自己作品
    public final static String url_deleteContent = url_domain + "api/center/deleteContent";
    //我的-删除我的作品
    public final static String url_delWorks = url_domain + "api/personInform/delWorks";
    //删除点赞作品
    public final static String url_deleteAssist = url_domain + "api/center/deleteAssist";
    //增加播放次数
    public final static String url_playTime = url_domain + "api/center/playTime";
    //获取我关注的人
    public final static String url_centerConcern = url_domain + "api/center/concern";
    //查看谁评论了我
    public final static String url_centerDiscuss = url_domain + "api/center/discuss";
    // 查看谁赞了我我的作品
    public final static String url_centerFabulous = url_domain + "api/center/fabulous";
    // 获取关注我的人
    public final static String url_centerAttention = url_domain + "api/center/attention";
    //取消关注用户接口
    public final static String url_centerUnFollow = url_domain + "api/attention/unFollow";
    //关注用户接口
    public final static String url_centerFollow = url_domain + "api/attention/follow";
    //用户是否关注用户
    public final static String url_isFollow = url_domain + "api/attention/isFollow";

    //查看作品是否被点赞
    public final static String url_contentIsAssist = url_domain + "api/center/contentIsAssist";
    //对作品取消点赞
    public final static String url_publishCommentDeleteAssist = url_domain + "api/center/publishCommentDeleteAssist";
    //对作品点赞
    public final static String url_publishCommentAssist = url_domain + "api/center/publishCommentAssist";
    //展示作品的评论
    public final static String url_showContentComment = url_domain + "api/center/showContentComment";
    //发布对作品的评论
    public final static String url_publishComment = url_domain + "api/center/publishComment";
    //获取用户作品
//    public final static String url_centerSelfWork = url_domain + "api/center/selfWork";
//    public final static String url_personVideos = url_domain + "api/person/videos";
    public final static String personWorks = url_domain + "api/person/works";
    //获取用户最爱的作品
    public final static String url_favouriteContent = url_domain + "api/center/favouriteContent";
    //用户发表作品
    public final static String url_publishWork = url_domain + "api/center/publishWork";
//    public final static String url_publishVideo = url_domain + "api/publish/video";
    public final static String publishVideo = url_domain + "api/video/publish";
    //获取用户的点赞
    public final static String url_centerAssist = url_domain + "api/center/assist";
    //首页-视频-创建评论
//    public final static String url_homePageVideosCreateComment = url_domain + "api/homepageVideos/createComment";
//    public final static String url_homePageVideosCreateComment = url_domain + "api/home/publishComment";
    public final static String url_homePageVideosCreateComment = url_domain + "api/video/createComment";
    //首页-视频-获取评论
    public final static String url_homePageVideosComment = url_domain + "api/homepageVideos/comment";

    //获取系统消息
    public final static String url_commonMessage = url_domain + "api/common/message";

    //关于我们
    public final static String url_personAbout = url_domain + "api/person/about";
    //意见反馈
    public final static String url_centerComment = url_domain + "api/center/comment";


}
