package com.kuaimu.android.app.activity;

import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;

import androidx.databinding.DataBindingUtil;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;

import androidx.annotation.NonNull;

import android.view.View;

import com.baselibrary.utils.CommonUtil;
import com.baselibrary.utils.FileUtils;
import com.baselibrary.utils.GlideLoader;
import com.baselibrary.utils.PermissionUtils;
import com.baselibrary.utils.ToastUtils;
import com.kuaimu.android.app.R;
import com.kuaimu.android.app.databinding.ActivityReleaseProductBinding;
import com.kuaimu.android.app.model.GoodDetail;
import com.media.MediaActivity;
import com.media.image.ImageModel;
import com.okhttp.SendRequest;
import com.okhttp.callbacks.StringCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

import okhttp3.Call;

public class ReleaseProductActivity extends BaseActivity implements View.OnClickListener {

    private ActivityReleaseProductBinding binding;
    private static final int REQUEST_TYPE = 100;
    private static final int REQUEST_IMAGE = 200;
    private static final int REQUEST_CAMERA = 300;
    private static final int REQUEST_CROP = 400;
    private String coverPath;

    private int type = 0;// 0 添加作品 ; 1 编辑作品

    private GoodDetail goodDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_release_product);

        binding.back.setOnClickListener(this);
        binding.tvConfirm.setOnClickListener(this);
        binding.cover.setOnClickListener(this);

        if (getIntent().getExtras() != null) {
            type = getIntent().getExtras().getInt("type");
            binding.title.setText(type == 0 ? "添加产品" : "编辑产品");
            binding.tvConfirm.setText(type == 0 ? "确认添加" : "确认提交");
            if (type == 1) {
                goodDetail = (GoodDetail) getIntent().getExtras().getSerializable("goodDetail");
                binding.name.setText(goodDetail.getData().getName());
                binding.content.setText(goodDetail.getData().getDesc());
                coverPath = goodDetail.getData().getImg();
                GlideLoader.LoderImage(this, coverPath, binding.cover, 10);
            }
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.cover:

                Intent intent = new Intent(ReleaseProductActivity.this, MediaActivity.class);
                intent.putExtra("type", ImageModel.TYPE_IMAGE);
                intent.putExtra("number", 1);
                startActivityForResult(intent, REQUEST_IMAGE);

//                AlertDialog.Builder dialog = new AlertDialog.Builder(ReleaseProductActivity.this);
//                dialog.setTitle("");
//                dialog.setItems(R.array.media_list_dialog, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        switch (which) {
//                            case 0:
//                                if (checkPermissionsAll(PermissionUtils.STORAGE, REQUEST_IMAGE)) {
//                                    Intent intent = new Intent(ReleaseProductActivity.this, MediaActivity.class);
//                                    intent.putExtra("type", ImageModel.TYPE_IMAGE);
//                                    intent.putExtra("number", 1);
//                                    startActivityForResult(intent, REQUEST_IMAGE);
//                                }
//                                break;
//                            case 1:
//                                if (checkPermissionsAll(PermissionUtils.CAMERA, REQUEST_CAMERA)) {
//                                    openCamera();
//                                }
//                                break;
//                            case 2:
//
//                                break;
//                        }
//                    }
//                });
//                dialog.show();
                break;
            case R.id.video_type:
                Intent intent2 = new Intent(ReleaseProductActivity.this, VideoTypeActivity.class);
                startActivityForResult(intent2, REQUEST_TYPE);
                break;
            case R.id.tv_confirm:
                String name = binding.name.getText().toString().trim();
                String desc = binding.content.getText().toString().trim();
                if (CommonUtil.isBlank(name)) {
                    ToastUtils.showShort(ReleaseProductActivity.this, "请输入产品名称");
                    return;
                }
                if (CommonUtil.isBlank(desc)) {
                    ToastUtils.showShort(ReleaseProductActivity.this, "请输入产品描述");
                    return;
                }
                if (CommonUtil.isBlank(coverPath)) {
                    ToastUtils.showShort(ReleaseProductActivity.this, "请选择封面图");
                    return;
                }
                if (type == 0) {
                    uploadFile(name, desc, coverPath);
                } else if (type == 1 && goodDetail != null) {
                    if (coverPath.startsWith("/storage")) {
                        uploadFile(name, desc, coverPath);
                    } else {
                        editGood(name, desc, coverPath);
                    }
                }
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        boolean granted = true;
        switch (requestCode) {
            case REQUEST_IMAGE:
                for (int i = 0; i < PermissionUtils.storage.length; i++) {
                    if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                        granted = false;
                        break;
                    }
                }
                if (granted) {
                    Intent intent = new Intent(ReleaseProductActivity.this, MediaActivity.class);
                    intent.putExtra("type", ImageModel.TYPE_IMAGE);
                    intent.putExtra("number", 1);
                    startActivityForResult(intent, REQUEST_IMAGE);
                } else {
                    PermissionUtils.openAppDetails(ReleaseProductActivity.this, "储存");
                }
                break;
            case REQUEST_CAMERA:
                for (int i = 0; i < PermissionUtils.camera.length; i++) {
                    if (grantResults[i] != PackageManager.PERMISSION_GRANTED) {
                        granted = false;
                        break;
                    }
                }
                if (granted) {
                    openCamera();
                } else {
                    PermissionUtils.openAppDetails(ReleaseProductActivity.this, "储存和相机");
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case REQUEST_IMAGE:
                    if (data != null) {
                        String resultJson = data.getStringExtra("resultJson");
                        try {
                            JSONObject object = new JSONObject(resultJson);
                            if (object.optString("type").equals(ImageModel.TYPE_IMAGE)) {
                                JSONArray files = object.optJSONArray("imageList");
                                if (files.length() > 0) {
                                    coverPath = String.valueOf(files.get(0));
                                    clipPicture(coverPath);
                                    GlideLoader.LoderLoadImage(this, coverPath, binding.cover, 10);
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case REQUEST_CAMERA:
                    coverPath = String.valueOf(outputImage.getPath());
                    clipPicture(coverPath);
                    GlideLoader.LoderLoadImage(this, coverPath, binding.cover, 10);
                    break;
                case REQUEST_CROP:
                    if (null != data) {
                        coverPath = data.getStringExtra(ClipImageActivity.ARG_CLIP_PATH);
                        GlideLoader.LoderLoadImage(this, coverPath, binding.cover, 10);
                    }
                    break;
            }
        }
    }

    private File outputImage;

    private void openCamera() {
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
            startActivityForResult(intent, REQUEST_CAMERA);
        }
    }

    private void clipPicture(String path) {
        Intent intent = new Intent(ReleaseProductActivity.this, ClipImageActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(ClipImageActivity.ARG_PATH, path);
        bundle.putBoolean(ClipImageActivity.ARG_FIXED_RATIO, false);
        bundle.putFloat(ClipImageActivity.ARG_WIDTH, 1);
        bundle.putFloat(ClipImageActivity.ARG_HEIGHT, 1);
        intent.putExtras(bundle);
        startActivityForResult(intent, REQUEST_CROP);
    }

    private void uploadFile(final String name, final String desc, String file) {
        SendRequest.fileUpload(file, file.substring(file.lastIndexOf("/") + 1), new StringCallback() {

            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                try {
                    JSONObject object = new JSONObject(response);
                    String url = object.optString("data");
                    if (!CommonUtil.isBlank(url)) {
                        if (type == 0) {
                            createGood(name, desc, url);
                        } else if (type == 1) {
                            editGood(name, desc, url);
                        }
                    } else {
                        ToastUtils.showShort(ReleaseProductActivity.this, "封面上传失败");
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    private void createGood(String name, String desc, final String coverUrl) {
        SendRequest.createGood(getUid(), name, desc, coverUrl, new StringCallback() {


            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.optInt("code") == 200) {
                        ToastUtils.showShort(ReleaseProductActivity.this, "发布成功");
                        finish();
                    } else {
                        ToastUtils.showShort(ReleaseProductActivity.this, "发布失败 :" + jsonObject.optString("msg"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    ToastUtils.showShort(ReleaseProductActivity.this, "发布失败");
                }
            }
        });

    }

    private void editGood(String name, String desc, final String coverUrl) {
        SendRequest.editGood(goodDetail.getData().getId(), name, desc, coverUrl, new StringCallback() {


            @Override
            public void onError(Call call, Exception e, int id) {

            }

            @Override
            public void onResponse(String response, int id) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    if (jsonObject.optInt("code") == 200) {
                        ToastUtils.showShort(ReleaseProductActivity.this, "编辑成功");
                        finish();
                    } else {
                        ToastUtils.showShort(ReleaseProductActivity.this, "发布失败 :" + jsonObject.optString("msg"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    ToastUtils.showShort(ReleaseProductActivity.this, "发布失败");
                }
            }
        });

    }
}