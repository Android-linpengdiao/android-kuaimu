package com.kuaimu.android.app.utils;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.alibaba.sdk.android.oss.ClientConfiguration;
import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.OSS;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.callback.OSSProgressCallback;
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
import com.alibaba.sdk.android.oss.model.OSSRequest;
import com.alibaba.sdk.android.oss.model.OSSResult;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.okhttp.utils.APIUrls;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyOSSUtils {

    private static MyOSSUtils instance;

    public static String IMAGE_CDN_DOMAIN = "";  //最后拼接名字
    public static String ENDPOINT = "";  //最后拼接名字
    public static String BUCKETNAME = "";  //桶名

    private final String P_ENDPOINT = "http://oss-cn-shanghai.aliyuncs.com";//主机地址（OSS文档中有提到）

    // private final String P_STSSERVER = "http://xiuqiang-dev.ikid06.cn/storage/createSecurityToken";//UrlHelper.BASE_URL + "get_aliyun_oss_sts";   //（服务器域名）
    private final String P_STSSERVER = APIUrls.url_createSecurityToken;//UrlHelper.BASE_URL + "get_aliyun_oss_sts";   //（服务器域名）

//    private final String P_STSSERVER = NetUrlConstant.CREATE_SECURITY_TOKEN_URL;//UrlHelper.BASE_URL + "get_aliyun_oss_sts";   //（服务器域名）

    private final String P_BUCKETNAME = "mlaapp";  //桶名

    private final String P_IMAGE_CDN_DOMAIN = "http://mlaapp.ikid06.ltd/";  //最后拼接名字


    private OSS oss;

    private SimpleDateFormat simpleDateFormat;

    public MyOSSUtils() {

    }

    public static MyOSSUtils getInstance() {

        if (instance == null) {

            if (instance == null) {
                return new MyOSSUtils();

            }

        }

        return instance;

    }

    private OSS getOSs(Context context) {

//推荐使用OSSAuthCredentialsProvider。token过期可以及时更新


        OSSCredentialProvider credentialProvider = new STSGetter(P_STSSERVER);
//该配置类如果不设置，会有默认配置，具体可看该类

        ClientConfiguration conf = new ClientConfiguration();

        conf.setConnectionTimeout(15 * 1000);// 连接超时，默认15秒

        conf.setSocketTimeout(15 * 1000);// socket超时，默认15秒

        conf.setMaxConcurrentRequest(5);// 最大并发请求数，默认5个

        conf.setMaxErrorRetry(2);// 失败后最大重试次数，默认2次

        oss = new OSSClient(context, P_ENDPOINT, credentialProvider, conf);

        Log.e("kzx", P_ENDPOINT + "    " + P_STSSERVER + "  " + conf.toString() + "  ");


        if (simpleDateFormat == null) {

            simpleDateFormat = new SimpleDateFormat("yyyyMMdd");

        }
        return oss;
    }

    /**
     * 上传图片 上传文件
     *
     * @param context       application上下文对象
     * @param ossUpCallback 成功的回调
     * @param img_name      上传到oss后的文件名称，图片要记得带后缀 如：.jpg
     * @param imgPath       图片的本地路径
     */

    public void upImage(Context context, final OssUpCallback ossUpCallback, final String img_name, String imgPath) {

        OSS oSs = getOSs(context);

        final Date data = new Date();

        data.setTime(System.currentTimeMillis());

        final String imgName = simpleDateFormat.format(data) + "/" + img_name;

        PutObjectRequest putObjectRequest = new PutObjectRequest(P_BUCKETNAME, imgName, imgPath);

        putObjectRequest.setProgressCallback(new OSSProgressCallback() {

            @Override
            public void onProgress(Object o, long currentSize, long totalSize) {
                ossUpCallback.inProgress(currentSize, totalSize);
            }


        });

        oss.asyncPutObject(putObjectRequest, new OSSCompletedCallback() {

            @Override
            public void onSuccess(OSSRequest ossRequest, OSSResult result) {
                Log.e("MyOSSUtils", "------getRequestId:" + result.getRequestId());
                String imgUrl = TextUtils.isEmpty(IMAGE_CDN_DOMAIN) ? P_IMAGE_CDN_DOMAIN + imgName : IMAGE_CDN_DOMAIN + imgName;
                ossUpCallback.successImg(imgUrl);
            }

            @Override
            public void onFailure(OSSRequest ossRequest, ClientException clientException, ServiceException serviceException) {
                ossUpCallback.onFailure(clientException.getMessage());
            }
        });


    }

    /**
     * 上传图片 上传流
     *
     * @param context       application上下文对象
     * @param ossUpCallback 成功的回调
     * @param img_name      上传到oss后的文件名称，图片要记得带后缀 如：.jpg
     * @param imgbyte       图片的byte数组
     */

    public void upImage(Context context, final OssUpCallback ossUpCallback, final String img_name, byte[] imgbyte) {

        getOSs(context);

        final Date data = new Date();

        data.setTime(System.currentTimeMillis());

        final String imgName = simpleDateFormat.format(data) + "/" + img_name;

        PutObjectRequest putObjectRequest = new PutObjectRequest(P_BUCKETNAME, imgName, imgbyte);

        putObjectRequest.setProgressCallback(new OSSProgressCallback() {

            @Override
            public void onProgress(Object o, long currentSize, long totalSize) {
                ossUpCallback.inProgress(currentSize, totalSize);
            }


        });

        oss.asyncPutObject(putObjectRequest, new OSSCompletedCallback() {

            @Override
            public void onSuccess(OSSRequest ossRequest, OSSResult ossResult) {
                Log.e("MyOSSUtils", "------getRequestId:" + ossResult.getRequestId());
                String imgUrl = TextUtils.isEmpty(IMAGE_CDN_DOMAIN) ? P_IMAGE_CDN_DOMAIN + imgName : IMAGE_CDN_DOMAIN + imgName;
                ossUpCallback.successImg(imgUrl);
                //    ossUpCallback.successImg(oss.presignPublicObjectURL(P_BUCKETNAME, simpleDateFormat.format(data) + "/" + img_name));
            }

            @Override
            public void onFailure(OSSRequest ossRequest, ClientException e, ServiceException e1) {
                ossUpCallback.onFailure(e1.getErrorCode());
            }


        });

    }

    /**
     * 上传视频
     *
     * @param context       application上下文对象
     * @param ossUpCallback 成功的回调
     * @param video_name    上传到oss后的文件名称，视频要记得带后缀 如：.mp4
     * @param video_path    视频的本地路径
     */

    public void upVideo(Context context, final OssUpCallback ossUpCallback, final String video_name, String video_path) {

        getOSs(context);

        final Date data = new Date();

        data.setTime(System.currentTimeMillis());

        PutObjectRequest putObjectRequest = new PutObjectRequest(P_BUCKETNAME, simpleDateFormat.format(data) + "/" + video_name, video_path);

        putObjectRequest.setProgressCallback(new OSSProgressCallback() {

            @Override
            public void onProgress(Object o, long currentSize, long totalSize) {
                ossUpCallback.inProgress(currentSize, totalSize);
            }


        });

        oss.asyncPutObject(putObjectRequest, new OSSCompletedCallback() {

            @Override
            public void onSuccess(OSSRequest ossRequest, OSSResult ossResult) {
                ossUpCallback.successVideo(oss.presignPublicObjectURL(P_BUCKETNAME, simpleDateFormat.format(data) + "/" + video_name));
            }

            @Override
            public void onFailure(OSSRequest ossRequest, ClientException e, ServiceException e1) {
                ossUpCallback.successVideo(null);
            }


        });

    }

    public interface OssUpCallback {

        void successImg(String img_url);

        void successVideo(String video_url);

        void onFailure(String failureCode);

        void inProgress(long progress, long zong);

    }


}