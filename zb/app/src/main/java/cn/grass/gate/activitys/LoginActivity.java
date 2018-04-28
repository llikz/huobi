package cn.grass.gate.activitys;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import com.weed.views.ClearEditView;

import cn.grass.gate.GlobalApp;
import cn.grass.gate.MainActivity;
import cn.grass.gate.R;
import cn.grass.gate.base.BaseActivity;
import cn.grass.gate.data.PrefManager;
import cn.grass.gate.presenters.LoginPresenter;
import cn.grass.gate.utils.CommonsUtils;
import cn.grass.gate.utils.ToastUtil;
import cn.grass.gate.views.LoginView;

/**
 * Created by linxc on 2017/7/13.
 * 登录
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener, LoginView {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();

    }

    ClearEditView phoneEt, pswEt;
    TextView loginTv, forgetPswTv;
    LoginPresenter presenter;

    private void initView() {
        phoneEt = (ClearEditView) findViewById(R.id.edit_phone);
        pswEt = (ClearEditView) findViewById(R.id.edit_pwd);
        loginTv = (TextView) findViewById(R.id.bt_login);
        forgetPswTv = (TextView) findViewById(R.id.tv_forget_psw);
//        registerTv = (TextView) findViewById(R.id.tv_register);

        loginTv.setOnClickListener(this);
        forgetPswTv.setOnClickListener(this);

        String un = PrefManager.getPrefString(GlobalApp.PRE_USER_LOGIN_UNAME, "");

        if (!TextUtils.isEmpty(un)) {
            phoneEt.setText(un);
            pswEt.setFocusable(true);
            pswEt.setFocusableInTouchMode(true);
            pswEt.requestFocus();
            pswEt.requestFocusFromTouch();
        } else {
            phoneEt.setFocusable(true);
            phoneEt.setFocusableInTouchMode(true);
            phoneEt.requestFocus();
            phoneEt.requestFocusFromTouch();
        }

        pswEt.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    // 在这里编写自己想要实现的功能
                    findViewById(R.id.bt_login).performClick();
                }
                return false;
            }
        });

        pswEt.addTextChangedListener(new TextWatcher() {
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
                    pswEt.setText(str1);
                    pswEt.setSelection(start);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

//        registerTv.setOnClickListener(this);
        presenter = new LoginPresenter(this);
    }

    public static void startLoginActivity(Context c) {
        Intent it = new Intent(c, LoginActivity.class);
        c.startActivity(it);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_login:
            //     //TODO 限制使用时间
            //     long time = System.currentTimeMillis();
            //     if(1507910400000l < time){
            //     return;
            // }
            //     OldMeterBoxActivity.startOldMeterBoxActivity(this);
            //     finish();
                String name = phoneEt.getText().toString();
                String pwd = pswEt.getText().toString();


                if (TextUtils.isEmpty(name)) {
                    ToastUtil.show(getResources().getString(R.string.prompt_account) + "不能为空~");
                    phoneEt.setFocusable(true);
                    phoneEt.setFocusable(true);
                    phoneEt.setFocusableInTouchMode(true);
                    phoneEt.requestFocus();
                    phoneEt.requestFocusFromTouch();
                    return;
                }
                if (CommonsUtils.pwdLimit(pwd) == false) {
//                    ToastUtil.shortShow(getResources().getString(R.string.prompt_password)+"不能为空~");
                    pswEt.setFocusable(true);
                    pswEt.setFocusable(true);
                    pswEt.setFocusableInTouchMode(true);
                    pswEt.requestFocus();
                    pswEt.requestFocusFromTouch();
                    return;
                }

                presenter.login(name, pwd);
                break;
            case R.id.tv_forget_psw:
                CheckAuthActivity.startCheckActivity(this, CheckAuthActivity.FUN_FORGET_PWD, 25);
                break;
//            case R.id.tv_register:
//
//                break;
        }
    }

    @Override
    public void onSuccess(int callId, String body, String msg) {
        MainActivity.startMainActivity(this);
        finish();
    }

    @Override
    public void onFail(int callId, String msg, int errId) {
        ToastUtil.show(msg);
    }
}
