package cn.grass.gate.http;

import com.weedys.weedlibrary.utils.LogUtil;
import com.zhy.http.okhttp.callback.Callback;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by weedys on 16/9/19.
 */
public class FileUploadCallback extends Callback<String> {
    @Override
    public String parseNetworkResponse(Response response, int id) throws Exception {
        String str= response.body().string();
        LogUtil.show("parser net:"+str);
        return str;
    }

    @Override
    public void onError(Call call, Exception e, int id) {

    }

    @Override
    public void onResponse(String response, int id) {

    }

    @Override
    public void inProgress(float progress, long total, int id) {
        super.inProgress(progress, total, id);
        LogUtil.show("parser progress:"+progress+" all:"+total);
    }
}
