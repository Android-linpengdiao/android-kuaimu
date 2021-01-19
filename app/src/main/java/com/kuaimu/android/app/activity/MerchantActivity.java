package com.kuaimu.android.app.activity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;

import com.alibaba.sdk.android.oss.ClientConfiguration;
import com.alibaba.sdk.android.oss.ClientException;
import com.alibaba.sdk.android.oss.OSS;
import com.alibaba.sdk.android.oss.OSSClient;
import com.alibaba.sdk.android.oss.ServiceException;
import com.alibaba.sdk.android.oss.callback.OSSCompletedCallback;
import com.alibaba.sdk.android.oss.callback.OSSProgressCallback;
import com.alibaba.sdk.android.oss.common.OSSLog;
import com.alibaba.sdk.android.oss.common.auth.OSSCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSStsTokenCredentialProvider;
import com.alibaba.sdk.android.oss.internal.OSSAsyncTask;
import com.alibaba.sdk.android.oss.model.PutObjectRequest;
import com.alibaba.sdk.android.oss.model.PutObjectResult;
import com.baselibrary.Constants;
import com.baselibrary.manager.LoadingManager;
import com.baselibrary.utils.CommonUtil;
import com.baselibrary.utils.FileUtils;
import com.baselibrary.utils.GlideLoader;
import com.baselibrary.utils.LogUtil;
import com.baselibrary.utils.PermissionUtils;
import com.baselibrary.utils.ToastUtils;
import com.kuaimu.android.app.R;
import com.kuaimu.android.app.databinding.ActivityMerchantBinding;
import com.kuaimu.android.app.databinding.ActivityMessageBinding;
import com.kuaimu.android.app.databinding.ActivityPersonAuthBinding;
import com.kuaimu.android.app.model.BaseData;
import com.kuaimu.android.app.model.BusinessData;
import com.media.MediaActivity;
import com.media.image.ImageModel;
import com.okhttp.SendRequest;
import com.okhttp.callbacks.GenericsCallback;
import com.okhttp.callbacks.StringCallback;
import com.okhttp.sample_okhttp.JsonGenericsSerializator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

import okhttp3.Call;
import okhttp3.Request;

public class MerchantActivity extends BaseActivity {

    private static final String TAG = "MerchantActivity";
    private ActivityMerchantBinding binding;

    private static final int REQUEST_IMAGE_LOGO = 100;
    private static final int REQUEST_IMAGE_QRCODE = 200;

    private static final int REQUEST_CAMERA_LOGO = 400;
    private static final int REQUEST_CAMERA_QRCODE = 500;

    private String logoPhoto = "";
    private String qrcodePhoto = "";

    private int uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_merchant);

        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        binding.tvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = binding.nameEditText.getText().toString().trim();
                String industry = binding.industryEditText.getText().toString().trim();
                String desc = binding.descEditText.getText().toString().trim();
                String link = binding.linkEditText.getText().toString().trim();

                if (CommonUtil.isBlank(name)) {
                    ToastUtils.showShort(MerchantActivity.this, "请输入商家名称");
                    return;
                }

                if (CommonUtil.isBlank(industry)) {
                    ToastUtils.showShort(MerchantActivity.this, "请输入商家行业");
                    return;
                }

                if (CommonUtil.isBlank(logoPhoto)) {
                    ToastUtils.showShort(MerchantActivity.this, "上传商家LOGO");
                    return;
                }

                if (CommonUtil.isBlank(qrcodePhoto)) {
                    ToastUtils.showShort(MerchantActivity.this, "上传微信二维码");
                    return;
                }


                businessBanner(name, industry, desc, link);
            }
        });
        binding.logoImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaDialog(REQUEST_IMAGE_LOGO, REQUEST_CAMERA_LOGO);
            }
        });
        binding.qrcodeImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaDialog(REQUEST_IMAGE_QRCODE, REQUEST_CAMERA_QRCODE);
            }
        });

        if (getIntent().getExtras() != null) {
            uid = getIntent().getExtras().getInt("uid");
            if (getUid()!=uid){
                binding.nameEditText.setEnabled(false);
                binding.industryEditText.setEnabled(false);
                binding.descEditText.setEnabled(false);
                binding.linkEditText.setEnabled(false);
                binding.logoImageView.setEnabled(false);
                binding.qrcodeImageView.setEnabled(false);
                binding.tvConfirm.setVisibility(View.GONE);
            }else {
                binding.tvConfirm.setVisibility(View.VISIBLE);
            }
            SendRequest.showBusinessBanner(uid, new GenericsCallback<BusinessData>(new JsonGenericsSerializator()) {
                @Override
                public void onError(Call call, Exception e, int id) {

                }

                @Override
                public void onResponse(BusinessData response, int id) {
                    if (response.getCode() == 200) {
                        if (!CommonUtil.isBlank(response.getData()) && response.getData().size() > 0) {
                            logoPhoto = response.getData().get(0).getLogo();
                            qrcodePhoto = response.getData().get(0).getQrcode();
                            GlideLoader.LoderImage(MerchantActivity.this, logoPhoto, binding.logoImageView, 8);
                            GlideLoader.LoderImage(MerchantActivity.this, qrcodePhoto, binding.qrcodeImageView, 8);
                            binding.nameEditText.setText(!CommonUtil.isBlank(response.getData().get(0).getName()) ? response.getData().get(0).getName() : "");
                            binding.industryEditText.setText(!CommonUtil.isBlank(response.getData().get(0).getIndustry()) ? response.getData().get(0).getIndustry() : "");
                            binding.descEditText.setText(!CommonUtil.isBlank(response.getData().get(0).getDesc()) ? response.getData().get(0).getDesc() : "");
                            binding.linkEditText.setText(!CommonUtil.isBlank(response.getData().get(0).getLink()) ? response.getData().get(0).getLink() : "");
                        }
                    } else {
                        ToastUtils.showShort(MerchantActivity.this, response.getMsg());
                    }

                }
            });
        }else {
            binding.tvConfirm.setVisibility(View.GONE);
        }

    }

    private void businessBanner(String name, String industry, String desc, String link) {
        SendRequest.businessBanner(getUid(), name, industry, desc, link, logoPhoto, qrcodePhoto, new GenericsCallback<BaseData>(new JsonGenericsSerializator()) {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(BaseData response, int id) {
                if (response.getCode() == 200) {
                    baseInfo();
                    ToastUtils.showShort(getApplication(), "保存成功");
                    finish();
                } else {
                    ToastUtils.showShort(getApplication(), response.getMsg());
                }
            }
        });
    }

    private void mediaDialog(int requestCodeImage, int requestCodeCamera) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(MerchantActivity.this);
        dialog.setTitle("");
        dialog.setItems(R.array.media_list_dialog, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        if (checkPermissionsAll(PermissionUtils.STORAGE, requestCodeImage)) {
                            Intent intent = new Intent(MerchantActivity.this, MediaActivity.class);
                            intent.putExtra("type", ImageModel.TYPE_IMAGE);
                            intent.putExtra("number", 1);
                            startActivityForResult(intent, requestCodeImage);
                        }
                        break;
                    case 1:
                        if (checkPermissionsAll(PermissionUtils.CAMERA, requestCodeCamera)) {
                            openCamera(requestCodeCamera);
                        }
                        break;
                    case 2:

                        break;
                }
            }
        });
        dialog.show();
    }

    private File outputImage;

    private void openCamera(int requestCode) {
        String fileName = System.currentTimeMillis() + ".jpg";
        File file = FileUtils.createTempFile(fileName);
        if (null != file && file.exists()) {
            outputImage = file;
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            //系统7.0打开相机权限处理
            if (Build.VERSION.SDK_INT >= 24) {
                ContentValues contentValues = new ContentValues(1);
                contentValues.put(MediaStore.Images.Media.DATA, file.getAbsolutePath());
                Uri uri = getApplication().getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);
            } else {
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
            }
            startActivityForResult(intent, requestCode);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        boolean granted = true;
        switch (requestCode) {
            case REQUEST_IMAGE_LOGO:
            case REQUEST_IMAGE_QRCODE:
                for (int i = 0; i < PermissionUtils.storage.length; i++) {
                    if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                        granted = false;
                        break;
                    }
                }
                if (granted) {
                    Intent intent = new Intent(MerchantActivity.this, MediaActivity.class);
                    intent.putExtra("type", ImageModel.TYPE_IMAGE);
                    intent.putExtra("number", 1);
                    startActivityForResult(intent, requestCode);
                } else {
                    PermissionUtils.openAppDetails(MerchantActivity.this, "储存");
                }
                break;
            case REQUEST_CAMERA_LOGO:
            case REQUEST_CAMERA_QRCODE:
                for (int i = 0; i < PermissionUtils.camera.length; i++) {
                    if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                        granted = false;
                        break;
                    }
                }
                if (granted) {
                    openCamera(requestCode);
                } else {
                    PermissionUtils.openAppDetails(MerchantActivity.this, "储存和相机");
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_IMAGE_LOGO:
                case REQUEST_IMAGE_QRCODE:
                    if (data != null) {
                        String resultJson = data.getStringExtra("resultJson");
                        try {
                            JSONObject object = new JSONObject(resultJson);
                            if (object.optString("type").equals(ImageModel.TYPE_IMAGE)) {
                                JSONArray files = object.optJSONArray("imageList");
                                if (files.length() > 0) {
                                    createSecurityToken(requestCode, String.valueOf(files.get(0)));
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case REQUEST_CAMERA_LOGO:
                case REQUEST_CAMERA_QRCODE:
                    createSecurityToken(requestCode, outputImage.getPath());
                    break;
            }
        }
    }

//    private void uploadFile(int requestCode, String file) {
//        SendRequest.fileUpload(file, file.substring(file.lastIndexOf("/") + 1), new StringCallback() {
//            @Override
//            public void onError(Call call, Exception e, int id) {
//
//            }
//
//            @Override
//            public void onResponse(String response, int id) {
//                try {
//                    JSONObject object = new JSONObject(response);
//                    String url = object.optString("data");
//                    if (requestCode == 100 || requestCode == 400) {
//                        logoPhoto = url;
//                        GlideLoader.LoderImage(MerchantActivity.this, url, binding.logoImageView, 8);
//                    } else if (requestCode == 200 || requestCode == 500) {
//                        qrcodePhoto = url;
//                        GlideLoader.LoderImage(MerchantActivity.this, url, binding.qrcodeImageView, 8);
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//
//    }

    private void createSecurityToken(int requestCode, String file) {
        SendRequest.createSecurityToken(new StringCallback() {

            @Override
            public void onBefore(Request request, int id) {
                super.onBefore(request, id);
                LoadingManager.showLoadingDialog(MerchantActivity.this);
            }

            @Override
            public void onAfter(int id) {
                super.onAfter(id);
                LoadingManager.hideLoadingDialog(MerchantActivity.this);
            }

            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    JSONObject object = jsonObject.optJSONObject("data");
                    if (jsonObject.optInt("code") == 200) {
                        String accessKeyId = object.optString("AccessKeyId");
                        String accessKeySecret = object.optString("AccessKeySecret");
                        String securityToken = object.optString("SecurityToken");
                        String expriedTime = object.optString("Expiration");
                        uploadImage(accessKeyId, accessKeySecret, securityToken, requestCode, file);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private void uploadImage(String AccessKeyId, String SecretKeyId, String SecurityToken, int requestCode, String file) {

        String endpoint = "http://oss-cn-beijing.aliyuncs.com";

        //if null , default will be init
        ClientConfiguration conf = new ClientConfiguration();
        conf.setConnectionTimeout(15 * 1000); // connction time out default 15s
        conf.setSocketTimeout(15 * 1000); // socket timeout，default 15s
        conf.setMaxConcurrentRequest(5); // synchronous request number，default 5
        conf.setMaxErrorRetry(2); // retry，default 2
        OSSLog.enableLog(); //write local log file ,path is SDCard_path\OSSLog\logs.csv

        OSSCredentialProvider credentialProvider = new OSSStsTokenCredentialProvider(AccessKeyId, SecretKeyId, SecurityToken);

        OSS oss = new OSSClient(getApplicationContext(), endpoint, credentialProvider, conf);

        // Construct an upload request
        PutObjectRequest put = new PutObjectRequest("quickeye", file.substring(file.lastIndexOf("/") + 1), file);

        // You can set progress callback during asynchronous upload
        put.setProgressCallback(new OSSProgressCallback<PutObjectRequest>() {
            @Override
            public void onProgress(PutObjectRequest request, long currentSize, long totalSize) {
                LogUtil.d(TAG, "currentSize: " + currentSize + " totalSize: " + totalSize);
                String temp = "" + currentSize * 100 / totalSize;
                LoadingManager.updateProgress(MerchantActivity.this, String.format(Constants.str_updata_wait, temp + "%"));
            }
        });

        LoadingManager.showProgress(MerchantActivity.this, String.format(Constants.str_updata_wait, "0%"));
        final OSSAsyncTask task = oss.asyncPutObject(put, new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() {
            @Override
            public void onSuccess(PutObjectRequest request, PutObjectResult result) {
                LoadingManager.hideProgress(MerchantActivity.this);
                String url = "http://" + request.getBucketName() + ".oss-cn-beijing.aliyuncs.com/" + request.getObjectKey();
                Log.i(TAG, "onSuccess: " + url);
                if (requestCode == 100 || requestCode == 400) {
                    logoPhoto = url;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            GlideLoader.LoderImage(MerchantActivity.this, url, binding.logoImageView, 8);
                        }
                    });
                } else if (requestCode == 200 || requestCode == 500) {
                    qrcodePhoto = url;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            GlideLoader.LoderImage(MerchantActivity.this, url, binding.qrcodeImageView, 8);
                        }
                    });

                }
            }

            @Override
            public void onFailure(PutObjectRequest request, ClientException clientExcepion, ServiceException serviceException) {
                LoadingManager.hideProgress(MerchantActivity.this);
                ToastUtils.showShort(MerchantActivity.this, "上传失败");
                // Request exception
                if (clientExcepion != null) {
                    // Local exception, such as a network exception
                    clientExcepion.printStackTrace();
                }
                if (serviceException != null) {
                    // Service exception
                }
            }

            @Override
            protected Object clone() throws CloneNotSupportedException {
                return super.clone();
            }
        });
        LoadingManager.OnDismissListener(MerchantActivity.this, new LoadingManager.OnDismissListener() {
            @Override
            public void onDismiss() {
                task.cancel(); // Cancel the task
            }
        });

        // task.cancel(); // Cancel the task
        // task.waitUntilFinished(); // Wait till the task is finished
    }
}