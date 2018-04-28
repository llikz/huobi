package cn.grass.gate.activitys;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;

import com.umeng.analytics.MobclickAgent;

import cn.grass.gate.R;
import cn.grass.gate.base.BaseActivity;
import cn.grass.gate.utils.ToastUtil;
import cn.grass.gate.widgets.TopView;


/**
 * Created by weedys on 16/7/26.
 * 立即反馈
 */
public class FeedBackActivity extends BaseActivity/* implements UserDetailView*/{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        initView();
    }
    String initStr="";
    EditText msgEdit;
//    UserDetailPresenter presenter;
    private void initView(){
        ((TopView)findViewById(R.id.top_bar)).setOnLeftListener(new TopView.OnLeftClickListener() {
            @Override
            public void onLeftClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });
//        presenter = new UserDetailPresenter(this);
        findViewById(R.id.bt_submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg=msgEdit.getText().toString().trim();
                if(TextUtils.isEmpty(msg)){
                    ToastUtil.shortShow("不能提交空字符串!");
                    return;
                }
//                presenter.feedback(msg);
            }
        });
        msgEdit=(EditText)findViewById(R.id.edit_feed);
        if(getIntent()!=null) {
            initStr=getIntent().getStringExtra(KEY_FEED);
            msgEdit.setText(initStr);
        }

        msgEdit.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    // 在这里编写自己想要实现的功能
                    findViewById(R.id.bt_submit).performClick();
                }
                return false;
            }
        });

    }
    public static String KEY_FEED="feed";
    public static void startFeedBackActivity(Context c,String msg,int code){
        Intent it=new Intent(c,FeedBackActivity.class);
        it.putExtra(KEY_FEED,msg);
        ((Activity)c).startActivityForResult(it,code);
    }

//    @Override
//    public void onSuccess(int callId, String body, String msg) {
//        hiddenDialog();
//        ToastUtil.shortShow("提交成功!");
//        finish();
//    }
//
//    @Override
//    public void onFail(int callId, String msg, int errId) {
//        hiddenDialog();
//        ToastUtil.shortShow(msg);
//    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }
}
