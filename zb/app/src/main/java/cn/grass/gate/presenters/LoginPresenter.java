package cn.grass.gate.presenters;

import com.weedys.weedlibrary.http.RequestCallBack;
import com.weedys.weedlibrary.utils.CommonUtils;
import com.weedys.weedlibrary.utils.LogUtil;

import org.json.JSONObject;

import java.util.HashMap;

import cn.grass.gate.GlobalApp;
import cn.grass.gate.GrassApp;
import cn.grass.gate.beans.UserInfo;
import cn.grass.gate.data.PrefManager;
import cn.grass.gate.http.Api;
import cn.grass.gate.http.HttpManager;
import cn.grass.gate.views.LoginView;


/**
 * Created by weedys on 16/7/21.
 */
public class LoginPresenter {
    LoginView loginView;
    public LoginPresenter(LoginView v){
        loginView = v;
    }
    public void login(String uname,String pwd){
        HashMap<String,String> map=new HashMap<>();
        String date =System.currentTimeMillis()+"";
        map.put("userName",uname);
        pwd= CommonUtils.MD5(pwd);
        map.put("password",pwd);
        map.put("date",date);
        HttpManager.getInstance().postRequest(GrassApp.getInstance(), Api.URL_LOGIN, map, new RequestCallBack() {
            @Override
            public void onSuccess(int callId, String content, String msg) {
                try {
                    LogUtil.show("content:"+content+"callId:"+callId+"msg:"+msg);

                    JSONObject json = new JSONObject(content);
                    if(json!=null){
                        //TODO 这个userInfo暂时没定下来,先放着,等用到的时候再加
                        UserInfo u=new UserInfo();
                        u.token=json.optString("token","");
                        u.createTime=json.optString("createTime");
                        u.phone=json.optString("phone");
                        u.birth=json.optString("birth");
                        u.sort=json.optInt("sort");
                        u.token=json.optString("token");
                        u.imageUrl=json.optString("imageUrl");
                        u.xb_dm = json.optString("xb_dm");
                        u.password =json.optString("password");
                        u.id = json.optString("id");
                        u.landline = json.optString("landline");
                        u.username = json.optString("username");
                        u.personnel = json.optInt("personnel");
                        u.head = json.optInt("head");
                        u.revTime = json.optString("revTime");
                        u.modifyTime = json.optString("modifyTime");
                        u.idNumber = json.optString("idNumber");
                        u.createUserId = json.optString("createUserId");
                        u.status = json.optInt("status");
                        u.nickname = json.optString("nickname");
                        u.cornet = json.optString("cornet");
                        u.revOrStop = json.optInt("revOrStop");
                        u.imUserName = json.optString("imUserName");
                        u.stopTime = json.optString("stopTime");
                        u.modifyUserId = json.optString("modifyUserId");
                        u.deptCode = json.optString("deptCode");
                        u.salt = json.optString("salt");

                        GrassApp.getInstance().enablePush();

                        GrassApp.getInstance().setUserInfo(u);
                        PrefManager.setPrefString(GlobalApp.PRE_USER_LOGIN_UNAME,u.phone);
                        loginView.onSuccess(callId,content,msg);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                    return;
                }

            }

            @Override
            public void onFail(int callId, int errorId, String msg) {
                LogUtil.show("msg:"+msg+"callId:"+callId+"errorId:"+errorId);
                loginView.onFail(errorId,msg,errorId);
            }
        },CallBackCode.TOKEN_GET_LOGIN);
    }
}
