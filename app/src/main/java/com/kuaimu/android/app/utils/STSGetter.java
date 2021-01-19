package com.kuaimu.android.app.utils;

import android.util.Log;

import com.alibaba.sdk.android.oss.common.auth.OSSFederationCredentialProvider;
import com.alibaba.sdk.android.oss.common.auth.OSSFederationToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by Administrator on 2015/12/9 0009.
 * 重载OSSFederationCredentialProvider生成自己的获取STS的功能
 */
public class STSGetter extends OSSFederationCredentialProvider {

    String stsServer;

    public STSGetter(String stsServer) {
        this.stsServer = stsServer;
    }
    public OSSFederationToken getFederationToken() {

        String stsJson;
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(stsServer).build();
        try {
            okhttp3.Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                stsJson = response.body().string();
            } else {
                throw new IOException("Unexpected code " + response);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
            Log.e("GetSTSTokenFail", e.toString());
            return null;
        }

        try {

            JSONObject jsonObjs = new JSONObject(stsJson);
            JSONObject data = jsonObjs.getJSONObject("data");
            String ak = data.getString("AccessKeyId");
            String sk = data.getString("AccessKeySecret");
            String token = data.getString("SecurityToken");
            String expiration = data.getString("Expiration");
            //    ak ="STS.NHmG1JwWSGXa5o94Sno8hkmXa\n";
            //       sk ="DM7xxdymQKMgtrqKTLcBTUi9EHCBcWnepVpLG2NnvTrQ\n";
            //      token ="CAISkAJ1q6Ft5B2yfSjIr4vYDIv+mohy8JqKN0mI0FM7Y7dEhKjzgzz2IHBLdHZtB+Ebs/0+nW1R5/salq96TY1FWEucgTG0SmASo22beIPkl5Gf395t0e+YewW6Dxr8w7WlAYHQR8/cffGAck3NkjQJr5LxaTSlWS7KU/COkoU1LK5UPG+CYCFBGc1dKyZ7tcYeLgGxD/u2NQPwiWeiaygNswFn22Rk8vb9kI/OqDim1Q2hlb5M/d+uc8D+MpI0ZK0SCYnlgLZEEYPayzNV5hRw86N7sbdJ4z+vvKvGUwYBvEjYYreJq4w2cVYoO/AgaKdArenhk/pjofDUlInxxBtLMPtcTyPFXoekzds4v2BS+0zwHhqAAbWbogIS9S2p4Ktxa++87DxY809VEVXvwlr/AMkay7UwvwGXP8TzYzF+W2VpPFMA+3UKkC9Fn5i0c01bCKkvv7hk+Qd1epmn4F8/TeZp38nC73G2i2BfVsNN4Z28b2/6PNMiSEiBtiXXAsCk0Fkx+7IVKFSa7q1JKFIWRAqX2HZU\n";
            //     expiration ="B642C450-9BEF-48EA-B2A0-6FCBA22DFB3E";

            return new OSSFederationToken(ak, sk, token, expiration);
        }
        catch (JSONException e) {
            Log.e("GetSTSTokenFail", e.toString());
            e.printStackTrace();
            return null;
        }
    }

}

