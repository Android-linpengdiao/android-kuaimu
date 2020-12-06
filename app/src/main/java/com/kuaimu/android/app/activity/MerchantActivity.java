package com.kuaimu.android.app.activity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;

import com.baselibrary.utils.CommonUtil;
import com.baselibrary.utils.FileUtils;
import com.baselibrary.utils.GlideLoader;
import com.baselibrary.utils.PermissionUtils;
import com.baselibrary.utils.ToastUtils;
import com.kuaimu.android.app.R;
import com.kuaimu.android.app.databinding.ActivityMerchantBinding;
import com.kuaimu.android.app.databinding.ActivityMessageBinding;
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

public class MerchantActivity extends BaseActivity {

    private ActivityMerchantBinding binding;

    private static final int REQUEST_IMAGE_LOGO = 100;
    private static final int REQUEST_IMAGE_QRCODE = 200;

    private static final int REQUEST_CAMERA_LOGO = 400;
    private static final int REQUEST_CAMERA_QRCODE = 500;

    private String logoPhoto = "";
    private String qrcodePhoto = "";

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


                businessBanner(name, industry,desc,link);
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
                                    uploadFile(requestCode, String.valueOf(files.get(0)));
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case REQUEST_CAMERA_LOGO:
                case REQUEST_CAMERA_QRCODE:
                    uploadFile(requestCode, outputImage.getPath());
                    break;
            }
        }
    }

    private void uploadFile(int requestCode, String file) {
        SendRequest.fileUpload(file, file.substring(file.lastIndexOf("/") + 1), new StringCallback() {
            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                try {
                    JSONObject object = new JSONObject(response);
                    String url = object.optString("data");
                    if (requestCode == 100 || requestCode == 400) {
                        logoPhoto = url;
                        GlideLoader.LoderImage(MerchantActivity.this, url, binding.logoImageView, 8);
                    } else if (requestCode == 200 || requestCode == 500) {
                        qrcodePhoto = url;
                        GlideLoader.LoderImage(MerchantActivity.this, url, binding.qrcodeImageView, 8);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }
}