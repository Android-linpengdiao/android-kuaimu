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
import com.kuaimu.android.app.databinding.ActivityPersonAuthBinding;
import com.kuaimu.android.app.model.BaseData;
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

public class PersonAuthActivity extends BaseActivity {

    private static final String TAG = "PersonAuthActivity";
    private ActivityPersonAuthBinding binding;
    private int auth = 1; //1 商家   2 个人

    private static final int REQUEST_IMAGE_BACK = 100;
    private static final int REQUEST_IMAGE_FRONT = 200;
    private static final int REQUEST_IMAGE_LICENSE = 300;

    private static final int REQUEST_CAMERA_BACK = 400;
    private static final int REQUEST_CAMERA_FRONT = 500;
    private static final int REQUEST_CAMERA_LICENSE = 600;

    private String backPhoto = "";
    private String frontPhoto = "";
    private String licensePhoto = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_person_auth);
        if (getIntent().getExtras() != null) {
            auth = getIntent().getExtras().getInt("auth");

            //1通过 2正在审核 3审核未通过 4未认证
            if (auth == 1 && getUserInfo().getData().getBusiness_auth_status() != 4
                    && getUserInfo().getData().getProfiles() != null && getUserInfo().getData().getProfiles().size() > 0) {

                for (int i = 0; i < getUserInfo().getData().getProfiles().size(); i++) {
                    if (getUserInfo().getData().getProfiles().get(i).getAuth() == 1) {
                        binding.etName.setText(getUserInfo().getData().getProfiles().get(i).getReal_name());
                        binding.etIDCard.setText(getUserInfo().getData().getProfiles().get(i).getIdcard());
                        GlideLoader.LoderImage(getApplication(), getUserInfo().getData().getProfiles().get(i).getFront_photo(), binding.frontPhotoView);
                        GlideLoader.LoderImage(getApplication(), getUserInfo().getData().getProfiles().get(i).getBack_photo(), binding.backPhotoView);
                        GlideLoader.LoderImage(getApplication(), getUserInfo().getData().getProfiles().get(i).getLicense_photo(), binding.licensePhotoView);
                    }
                }

            } else if (auth == 2 && getUserInfo().getData().getPerson_auth_status() != 4
                    && getUserInfo().getData().getProfiles() != null && getUserInfo().getData().getProfiles().size() > 0) {
                for (int i = 0; i < getUserInfo().getData().getProfiles().size(); i++) {
                    if (getUserInfo().getData().getProfiles().get(i).getAuth() == 2) {
                        binding.etName.setText(getUserInfo().getData().getProfiles().get(i).getReal_name());
                        binding.etIDCard.setText(getUserInfo().getData().getProfiles().get(i).getIdcard());
                        GlideLoader.LoderImage(getApplication(), getUserInfo().getData().getProfiles().get(i).getFront_photo(), binding.frontPhotoView);
                        GlideLoader.LoderImage(getApplication(), getUserInfo().getData().getProfiles().get(i).getBack_photo(), binding.backPhotoView);
                    }
                }

            }

            if (auth == 1) {
//                binding.tvConfirm.setText(
//                        getUserInfo().getData().getBusiness_auth_status() == 1 ? "完成认证" :
//                                getUserInfo().getData().getBusiness_auth_status() == 2 ? "正在审核" :
//                                        getUserInfo().getData().getBusiness_auth_status() == 3 ? "审核未通过" : "提交认证");

                binding.tvConfirm.setText(
                        getUserInfo().getData().getBusiness_auth_status() == 4 ? "提交认证" :"完成认证");
                binding.tvConfirm.setEnabled(getUserInfo().getData().getBusiness_auth_status() == 4 ? true :false);

            } else if (auth == 2) {
//                binding.tvConfirm.setText(
//                        getUserInfo().getData().getPerson_auth_status() == 1 ? "完成认证" :
//                                getUserInfo().getData().getPerson_auth_status() == 2 ? "正在审核" :
//                                        getUserInfo().getData().getPerson_auth_status() == 3 ? "审核未通过" : "提交认证");

                binding.tvConfirm.setText(
                        getUserInfo().getData().getPerson_auth_status() == 4 ? "提交认证" :"完成认证");
                binding.tvConfirm.setEnabled(getUserInfo().getData().getPerson_auth_status() == 4 ? true :false);
            }

        } else {
            finish();
        }

        binding.title.setText(auth == 1 ? "商家认证" : "我的认证");

        binding.licensePhotoLayout.setVisibility(auth == 1 ? View.VISIBLE : View.GONE);
        binding.authHintView.setVisibility(auth == 1 ? View.VISIBLE : View.GONE);

        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        binding.tvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = binding.etName.getText().toString().trim();
                String idcard = binding.etIDCard.getText().toString().trim();

                if (CommonUtil.isBlank(name)) {
                    ToastUtils.showShort(PersonAuthActivity.this, auth == 1 ? "请输入真实姓名" : "请输入真实姓名");
                    return;
                }

                if (CommonUtil.isBlank(idcard)) {
                    ToastUtils.showShort(PersonAuthActivity.this, auth == 1 ? "请输入身份证号码" : "请输入身份证号码");
                    return;
                }

                if (CommonUtil.isBlank(frontPhoto)) {
                    ToastUtils.showShort(PersonAuthActivity.this, "上传您的身份证正面照片");
                    return;
                }

                if (CommonUtil.isBlank(backPhoto)) {
                    ToastUtils.showShort(PersonAuthActivity.this, "上传您的身份证背面照片");
                    return;
                }

                if (auth == 1) {
                    if (CommonUtil.isBlank(licensePhoto)) {
                        ToastUtils.showShort(PersonAuthActivity.this, "上传营业执照照片");
                        return;
                    }

                }


                personAuth(name, idcard);
            }
        });
        binding.backPhotoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaDialog(REQUEST_IMAGE_BACK, REQUEST_CAMERA_BACK);
            }
        });
        binding.frontPhotoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaDialog(REQUEST_IMAGE_FRONT, REQUEST_CAMERA_FRONT);
            }
        });
        binding.licensePhotoView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mediaDialog(REQUEST_IMAGE_LICENSE, REQUEST_CAMERA_LICENSE);
            }
        });

    }

    private void personAuth(String name, String idcard) {
        SendRequest.personAuth(getUid(), name, idcard, frontPhoto, backPhoto, licensePhoto, auth, new GenericsCallback<BaseData>(new JsonGenericsSerializator()) {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(BaseData response, int id) {
                if (response.getCode() == 200) {
                    baseInfo();
                    ToastUtils.showShort(getApplication(), "提交成功");
                    finish();
                } else {
                    ToastUtils.showShort(getApplication(), response.getMsg());
                }
            }
        });
    }

    private void mediaDialog(int requestCodeImage, int requestCodeCamera) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(PersonAuthActivity.this);
        dialog.setTitle("");
        dialog.setItems(R.array.media_list_dialog, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        if (checkPermissionsAll(PermissionUtils.STORAGE, requestCodeImage)) {
                            Intent intent = new Intent(PersonAuthActivity.this, MediaActivity.class);
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
            case REQUEST_IMAGE_BACK:
            case REQUEST_IMAGE_FRONT:
            case REQUEST_IMAGE_LICENSE:
                for (int i = 0; i < PermissionUtils.storage.length; i++) {
                    if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                        granted = false;
                        break;
                    }
                }
                if (granted) {
                    Intent intent = new Intent(PersonAuthActivity.this, MediaActivity.class);
                    intent.putExtra("type", ImageModel.TYPE_IMAGE);
                    intent.putExtra("number", 1);
                    startActivityForResult(intent, requestCode);
                } else {
                    PermissionUtils.openAppDetails(PersonAuthActivity.this, "储存");
                }
                break;
            case REQUEST_CAMERA_BACK:
            case REQUEST_CAMERA_FRONT:
            case REQUEST_CAMERA_LICENSE:
                for (int i = 0; i < PermissionUtils.camera.length; i++) {
                    if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                        granted = false;
                        break;
                    }
                }
                if (granted) {
                    openCamera(requestCode);
                } else {
                    PermissionUtils.openAppDetails(PersonAuthActivity.this, "储存和相机");
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_IMAGE_BACK:
                case REQUEST_IMAGE_FRONT:
                case REQUEST_IMAGE_LICENSE:
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
                case REQUEST_CAMERA_BACK:
                case REQUEST_CAMERA_FRONT:
                case REQUEST_CAMERA_LICENSE:
//                    uploadFile(requestCode, outputImage.getPath());
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
//                        backPhoto = url;
//                        GlideLoader.LoderImage(PersonAuthActivity.this, url, binding.backPhotoView, 8);
//                    } else if (requestCode == 200 || requestCode == 500) {
//                        frontPhoto = url;
//                        GlideLoader.LoderImage(PersonAuthActivity.this, url, binding.frontPhotoView, 8);
//                    } else if (requestCode == 300 || requestCode == 600) {
//                        licensePhoto = url;
//                        GlideLoader.LoderImage(PersonAuthActivity.this, url, binding.licensePhotoView, 8);
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//
//    }
    
    private void createSecurityToken(int requestCode,String file) {
        SendRequest.createSecurityToken(new StringCallback() {

            @Override
            public void onBefore(Request request, int id) {
                super.onBefore(request, id);
                LoadingManager.showLoadingDialog(PersonAuthActivity.this);
            }

            @Override
            public void onAfter(int id) {
                super.onAfter(id);
                LoadingManager.hideLoadingDialog(PersonAuthActivity.this);
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
                        uploadImage(accessKeyId, accessKeySecret, securityToken,requestCode,file);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private void uploadImage(String AccessKeyId, String SecretKeyId, String SecurityToken,int requestCode,String file) {

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
                LoadingManager.updateProgress(PersonAuthActivity.this, String.format(Constants.str_updata_wait, temp + "%"));
            }
        });

        LoadingManager.showProgress(PersonAuthActivity.this, String.format(Constants.str_updata_wait, "0%"));
        final OSSAsyncTask task = oss.asyncPutObject(put, new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() {
            @Override
            public void onSuccess(PutObjectRequest request, PutObjectResult result) {
                LoadingManager.hideProgress(PersonAuthActivity.this);
                String url = "http://" + request.getBucketName() + ".oss-cn-beijing.aliyuncs.com/" + request.getObjectKey();
                Log.i(TAG, "onSuccess: " + url);
                if (requestCode == 100 || requestCode == 400) {
                    backPhoto = url;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            GlideLoader.LoderImage(PersonAuthActivity.this, url, binding.backPhotoView, 8);
                        }
                    });

                } else if (requestCode == 200 || requestCode == 500) {
                    frontPhoto = url;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            GlideLoader.LoderImage(PersonAuthActivity.this, url, binding.frontPhotoView, 8);
                        }
                    });

                } else if (requestCode == 300 || requestCode == 600) {
                    licensePhoto = url;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            GlideLoader.LoderImage(PersonAuthActivity.this, url, binding.licensePhotoView, 8);
                        }
                    });

                }
            }

            @Override
            public void onFailure(PutObjectRequest request, ClientException clientExcepion, ServiceException serviceException) {
                LoadingManager.hideProgress(PersonAuthActivity.this);
                ToastUtils.showShort(PersonAuthActivity.this, "上传失败");
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
        LoadingManager.OnDismissListener(PersonAuthActivity.this, new LoadingManager.OnDismissListener() {
            @Override
            public void onDismiss() {
                task.cancel(); // Cancel the task
            }
        });

        // task.cancel(); // Cancel the task
        // task.waitUntilFinished(); // Wait till the task is finished
    }
}