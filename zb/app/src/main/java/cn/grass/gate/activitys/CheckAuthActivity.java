package cn.grass.gate.activitys;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.umeng.analytics.MobclickAgent;
import com.weedys.weedlibrary.utils.CommonUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Timer;
import java.util.TimerTask;

import cn.grass.gate.GrassApp;
import cn.grass.gate.R;
import cn.grass.gate.base.BaseActivity;
import cn.grass.gate.beans.UserInfo;
import cn.grass.gate.http.message.AccountEvent;
import cn.grass.gate.presenters.CheckAuthPresenter;
import cn.grass.gate.utils.CommonsUtils;
import cn.grass.gate.utils.ToastUtil;
import cn.grass.gate.views.CheckAuthView;
import cn.grass.gate.widgets.TopView;


/**
 * Created by weedys on 16/7/25.
 * 找回密码、修改密码、注册
 */
public class CheckAuthActivity extends BaseActivity implements View.OnClickListener, CheckAuthView {

    public final static int FUN_FORGET_PWD=0x1;
    public final static int FUN_CHANGE_PHONE=0x2;
    public final static int FUN_CHANGE_PWD=0x3;
    public final static int FUN_REGEDIT=0x4;
    private int fid = 0;
    private int type =0;
    private CheckAuthPresenter presenter =null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth_check);
        Intent it=getIntent();
        if(it!=null){
            fid=it.getIntExtra(FUN_FID,0);
        }
        EventBus.getDefault().register(this);
        initView();

    }
    TopView topView;
    EditText phoneEt,codeEt,pwdEt;
    TextView getCodeTv;
    boolean sendEnable= false;
    Button submitBt;
    boolean isDoing =false;
    private void initView()
    {
        presenter = new CheckAuthPresenter(this);
        topView=(TopView)findViewById(R.id.top_bar);

        topView.setOnLeftListener(new TopView.OnLeftClickListener() {
            @Override
            public void onLeftClick(View v) {
                setResult(RESULT_CANCELED);
                onBackPressed();
            }
        });
        submitBt=(Button) findViewById(R.id.bt_submit);
        submitBt.setOnClickListener(this);
        phoneEt=(EditText)findViewById(R.id.edit_phone);
        pwdEt=(EditText) findViewById(R.id.edit_pwd);
        switch (fid){
            case FUN_FORGET_PWD:
                topView.setTitleText("找回密码");
                pwdEt.setVisibility(View.VISIBLE);
                pwdEt.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                pwdEt.setHint("请设置新密码");
                submitBt.setText("提 交");
                type=1;
                break;
            case FUN_CHANGE_PHONE:
                topView.setTitleText("修改手机");
                phoneEt.setHint("请输入新手机号码");
                submitBt.setText("确定修改");
                type=2;
                break;
            case FUN_CHANGE_PWD:
                topView.setTitleText("修改密码");

                pwdEt.setHint("请设置新密码");
                pwdEt.setVisibility(View.VISIBLE);
                pwdEt.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD);
                pwdEt.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                submitBt.setText("确定修改");
                type=3;
                break;
            case FUN_REGEDIT:
                topView.setTitleText("注册");
                pwdEt.setVisibility(View.VISIBLE);
                pwdEt.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                submitBt.setText("注 册");
                type=0;
                break;
            default:

                break;
        }

        pwdEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.toString().contains(" ")) {
                    ToastUtil.show("密码不可为空格键~");
                    String[] str = s.toString().split(" ");
                    String str1 = "";
                    for (int i = 0; i < str.length; i++) {
                        str1 += str[i];
                    }
                    pwdEt.setText(str1);
                    pwdEt.setSelection(start);

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        codeEt=(EditText)findViewById(R.id.edit_code);
        getCodeTv=(TextView) findViewById(R.id.tv_get_code);
        getCodeTv.setOnClickListener(this);

        phoneEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                if(charSequence==null || charSequence.length()<11 || charSequence.length()>11){
                    getCodeTv.setBackgroundResource(R.drawable.shape_btn_send_disable);
                    sendEnable = false;
                    return;
                }else{
                    getCodeTv.setBackgroundResource(R.drawable.selector_btn_primary);
                    sendEnable = true;
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });



        findViewById(R.id.tv_web).setOnClickListener(this);
        findViewById(R.id.iv_check).setOnClickListener(this);
        findViewById(R.id.iv_check).setSelected(false);

    }
    private void toWhitchActivity(){
        switch (fid){
            case FUN_FORGET_PWD:
//                ForgetPwdActivity.startForgetPwdActivity(this);
                String phone=phoneEt.getText().toString();
//                PrefManager.setPrefString(GlobalApp.PRE_USER_LOGIN_FNAME,phone);
                setResult(RESULT_OK);
                finish();
                break;
            case FUN_REGEDIT:
//                EventBus.getDefault().post(new AccountEvent(AccountEvent.TYPE_TO_MAIN));
                //注册后不再跳转到登录界面,而是去切换角色界面
//                SwitchRoleActivity.startSwitchRoleActivity(getApplication());
                finish();
                break;
            case FUN_CHANGE_PHONE:
                setResult(RESULT_OK);
                GrassApp.getInstance().logout();
//                EventBus.getDefault().post(new AccountEvent(AccountEvent.TYPE_RELOGIN_ACCOUNT,"1"));
                finish();
                break;
            case FUN_CHANGE_PWD:
                setResult(RESULT_OK);
                GrassApp.getInstance().logout();
//                EventBus.getDefault().post(new AccountEvent(AccountEvent.TYPE_RELOGIN_ACCOUNT,"1"));
                finish();
                break;
            default:

                break;
        }
    }
    private void toWhitchAction(){

        String phone=phoneEt.getText().toString();
        if(TextUtils.isEmpty(phone)){
            ToastUtil.show(getResources().getString(R.string.prompt_account));
            phoneEt.setFocusable(true);
            phoneEt.requestFocus();
            phoneEt.requestFocusFromTouch();
            return;
        }
        if(!CommonUtils.isPhoneNumberValid(phone)){
            ToastUtil.show("请填写正确的手机号码");
            phoneEt.setFocusable(true);
            phoneEt.requestFocus();
            phoneEt.requestFocusFromTouch();
            return;
        }
        String code=codeEt.getText().toString();
        if(TextUtils.isEmpty(code)){
            ToastUtil.shortShow("请输入验证码");
            codeEt.setFocusable(true);
            codeEt.requestFocus();
            codeEt.requestFocusFromTouch();
            return;
        }
        View checkView=findViewById(R.id.iv_check);

        String pwd="";
        switch (fid){
            case FUN_FORGET_PWD:
                pwd=pwdEt.getText().toString();
                if(TextUtils.isEmpty(pwd)){
                    ToastUtil.shortShow("请输入新密码!");
                    pwdEt.setFocusable(true);
                    pwdEt.requestFocus();
                    pwdEt.requestFocusFromTouch();
                    return;
                }else {
//                    if (pwd.length()<3||pwd.length()>20){
//                        ToastUtil.show("请输入多于3位，少于20位的密码");
//                        return;
//                    }
                    if (CommonsUtils.pwdLimit(pwd)==false){
                        return;
                    }
                }
                if(!checkView.isSelected()){
                    ToastUtil.show("请您先阅读并同意《使用协议》");
                    return;
                }

                if(isDoing){
                    return;
                }
                isDoing =true;
                showProgressDialog("请稍候...");
                presenter.forgetPwd(phone,pwd,code);//TODO 忘记密码
                break;
            case FUN_REGEDIT:
                pwd=pwdEt.getText().toString();
                if(TextUtils.isEmpty(pwd)){
                    ToastUtil.shortShow(getResources().getString(R.string.prompt_password));
                    pwdEt.setFocusable(true);
                    pwdEt.requestFocus();
                    pwdEt.requestFocusFromTouch();
                    return;
                }else {
//                    if (pwd.length()<3||pwd.length()>20){
//                        ToastUtil.show("请输入多于3位，少于20位的密码");
//                        return;
//                    }

                    if (CommonsUtils.pwdLimit(pwd)==false){
                        return;
                    }

                }
                if(!checkView.isSelected()){
                    ToastUtil.show("请您先阅读并同意《使用协议》");
                    return;
                }
                if(isDoing){
                    return;
                }
                isDoing =true;
                showProgressDialog("请稍候...");

//                presenter.regedit(phone,pwd,code);
                break;
            case FUN_CHANGE_PHONE:
                UserInfo u= GrassApp.getInstance().getUserInfo();
                if(u!=null) {
                    u.phone = phoneEt.getText().toString();
                    if(!checkView.isSelected()){
                        ToastUtil.show("请您先阅读并同意《使用协议》");
                        return;
                    }
                    if(isDoing){
                        return;
                    }
                    isDoing = true;
                    showProgressDialog("请稍候...");
//                    presenter.setPhone(u.phone,code);
//                    setResult(RESULT_OK);
//                    EventBus.getDefault().post(new AccountEvent(AccountEvent.TYPE_RELOGIN_ACCOUNT, "1"));
//                    finish();
                }else{
                    ToastUtil.shortShow("请您先登录");
                    setResult(RESULT_CANCELED);
                    finish();
                }
                break;
            case FUN_CHANGE_PWD:
                if(pwdEt!=null){
                    pwd=pwdEt.getText().toString();
                    if(TextUtils.isEmpty(pwd)){
                        ToastUtil.show("请输入您的密码");
                        pwdEt.setFocusable(true);
                        pwdEt.requestFocus();
                        pwdEt.requestFocusFromTouch();
                        return;
                    }else {
//                        if (pwd.length()<3||pwd.length()>20){
//                            ToastUtil.show("请输入多于3位，少于20位的密码");
//                            return;
//                        }
                        if (CommonsUtils.pwdLimit(pwd)==false){
                            return;
                        }
                    }
                }else{
                    return;
                }
                if(!checkView.isSelected()){
                    ToastUtil.show("请您先阅读并同意《使用协议》");
                    return;
                }
                if(isDoing){
                    return;
                }
                isDoing = true;
                showProgressDialog("请稍候...");
//                presenter.changePwd(phone,pwd,code);
                break;
            default:

                break;
        }
    }
    Timer timer;
    int TIME=60;
    int curTime = TIME;
    int btnStatus= 0;
    private void initTimer(){
        if(!sendEnable){
            return;
        }
        if(btnStatus==1){
            return;
        }
        if(timer==null)
        timer = new Timer();
        btnStatus =1;
        timer.schedule(new TimerTask() {
            @Override
            public void run() {

                if(curTime<=1){
                    timer.cancel();
                    timer = null;
                    curTime = TIME;
                    sendEnable = true;
                    updateHandler.sendEmptyMessage(0);
                }else {
                    curTime--;
                    updateHandler.sendEmptyMessage(1);
                }

            }
        },1000,1000);
    }
    private Handler updateHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if(msg.what==1){
                if(getCodeTv!=null){
                    getCodeTv.setText("剩余 "+curTime+" 秒");
                }
            }else if(msg.what==0){
                if(getCodeTv!=null){
                    getCodeTv.setText("重新获取");
                    btnStatus=0;
                    sendEnable =true;
                    getCodeTv.setEnabled(true);
                }
            }
        }
    };
    private void stopTimer(){
        if(timer!=null){
            timer.cancel();
            timer=null;
            curTime = TIME;
        }
        getCodeTv.setEnabled(true);
        btnStatus=0;
    }
    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.tv_get_code:
//                getCode();//TODO 先将验证码获取注释
               // initTimer();
                break;
            case R.id.bt_submit:
                toWhitchAction();
                break;
            case R.id.iv_check:
                if(!view.isSelected()){
                    view.setSelected(true);
                }else{
                    view.setSelected(false);
                }
                break;
        }
    }

    public static String FUN_FID="fid";
    public static void startCheckActivity(Context c,int fid){
        Intent it=new Intent(c,CheckAuthActivity.class);
        it.putExtra(FUN_FID,fid);
        c.startActivity(it);
    }

    public static void startCheckActivity(Context c,int fid,int code){
        Intent it=new Intent(c,CheckAuthActivity.class);
        it.putExtra(FUN_FID,fid);
        ((Activity)c).startActivityForResult(it,code);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopTimer();
        EventBus.getDefault().unregister(this);
    }

//    @Override
    public void getCode() {
        if(phoneEt==null){
            return;
        }
        String phone=phoneEt.getText().toString();
        if(TextUtils.isEmpty(phone)){
            ToastUtil.shortShow("请输入手机号码!");
            phoneEt.setFocusable(true);
            phoneEt.requestFocus();
            phoneEt.requestFocusFromTouch();
            return;
        }
        if(!CommonUtils.isPhoneNumberValid(phone)){
            ToastUtil.shortShow("请输入合法手机号码");
            phoneEt.setFocusable(true);
            phoneEt.requestFocus();
            phoneEt.requestFocusFromTouch();
            return;
        }


        codeEt.setFocusable(true);
        codeEt.requestFocus();
        codeEt.requestFocusFromTouch();

        showProgressDialog("请稍候...");
        sendEnable = true;
        getCodeTv.setEnabled(false);
//        presenter.sendCode(type,phone);
    }

//    @Override
//    public void onSuccess(int callId, String body, String msg) {
//        hiddenDialog();
//        if(callId==presenter.TOKEN_SEND){
//            ToastUtil.shortShow(msg);
//            initTimer();
//        }else if(callId==presenter.TOKEN_REGEDIT ||callId==presenter.TOKEN_CHANGE_PWD ||callId==presenter.TOKEN_FORGET_PWD
//                || callId ==presenter.TOKEN_SETPHONE){
//            isDoing =false;
//            ToastUtil.shortShow(msg);
//            toWhitchActivity();
//        }
//    }
//
//    @Override
//    public void onFail(int callId, String msg, int errId) {
//        hiddenDialog();
//        isDoing =false;
//        sendEnable = true;
//        ToastUtil.shortShow(msg);
//        getCodeTv.setEnabled(true);
//        if (callId==presenter.TOKEN_SEND){
//            if (errId==0){
//                CheckAuthActivity.startCheckActivity(this, CheckAuthActivity.FUN_REGEDIT, 24);
//            }
//        }
//    }
//
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void AccountEventBus(AccountEvent message){
        if(message!=null){
            switch (message.eventId){
                case AccountEvent.TYPE_EXIT_ACCOUNT:
                    finish();
                    break;
//                case AccountEvent.TYPE_RELOGIN_ACCOUNT:
//                    LoginActivity.startLoginActivity(this);
//                    finish();
//                    break;
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onPageStart("CheckAuthActivity");
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPageEnd("CheckAuthActivity");
        MobclickAgent.onPause(this);
    }

    @Override
    public void onSuccess(int callId, String body, String msg) {
        ToastUtil.show(msg);
        hiddenDialog();
        isDoing = false;
        finish();
    }

    @Override
    public void onFail(int callId, String msg, int errId) {
        ToastUtil.show(msg);
        hiddenDialog();
        isDoing = false;
    }
}
