package cn.grass.gate.base;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import cn.grass.gate.GrassApp;
import cn.grass.gate.R;


/**
 * Created by weedys on 16/7/20.
 */
public class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if(!EventBus.getDefault().isRegistered(this)){
//            EventBus.getDefault().register(this);
//        }
        GrassApp application = (GrassApp) this.getApplication();
        application.getActivityManager().pushActivity(this);
    }

    private ProgressDialog progressDialog =null;
    public void showProgressDialog(String msg){
        if(progressDialog==null){
            progressDialog = new ProgressDialog(this);

        }
        progressDialog.setMessage(msg);
        progressDialog.setProgressStyle(R.style.progressDialog);
        progressDialog.show();
    }

    public void showProgressDialog(){
        if(progressDialog==null){
            progressDialog = new ProgressDialog(this);

        }
        progressDialog.setMessage(getString(R.string.progress_dialog_msg));
        progressDialog.setProgressStyle(R.style.progressDialog);
        progressDialog.show();
    }

    public void showProgressDialog(String msg,boolean cancel){
        if(progressDialog==null){
            progressDialog = new ProgressDialog(this);

        }
        progressDialog.setMessage(msg);
        progressDialog.setProgressStyle(R.style.progressDialog);
        progressDialog.setCanceledOnTouchOutside(cancel);
        progressDialog.show();
    }
    public void hiddenDialog(){
        if(progressDialog!=null){
            if(progressDialog.isShowing()){
                progressDialog.dismiss();
            }
        }
    }

    public void showErrorView(View errorView,View rV,int status) {
        if (errorView != null) {
            if (status == 0) {//正常状态
                errorView.setVisibility(View.GONE);
                rV.setVisibility(View.VISIBLE);
            } else if (status == 1) {
                errorView.setVisibility(View.VISIBLE);
                rV.setVisibility(View.GONE);
                TextView errorTv = (TextView) errorView.findViewById(R.id.tv_layout_err);
                errorTv.setText("暂无数据喔~");
                errorTv.setCompoundDrawablesWithIntrinsicBounds(0, R.mipmap.icon_err_nodata, 0, 0);
            } else if (status == 2) {
                errorView.setVisibility(View.VISIBLE);
                TextView errorTv = (TextView) errorView.findViewById(R.id.tv_layout_err);
                errorTv.setText("请检查网络,轻触重新加载");
                errorTv.setCompoundDrawablesWithIntrinsicBounds(0, R.mipmap.icon_network, 0, 0);
                rV.setVisibility(View.GONE);
            }
        }
    }

    public void showErrorView(View errorView,View rV,int status,String hint) {
        if (errorView != null) {
            if (status == 0) {//正常状态
                errorView.setVisibility(View.GONE);
                rV.setVisibility(View.VISIBLE);
            } else if (status == 1) {
                errorView.setVisibility(View.VISIBLE);
                rV.setVisibility(View.GONE);
                TextView errorTv = (TextView) errorView.findViewById(R.id.tv_layout_err);
                errorTv.setText(hint);
                errorTv.setCompoundDrawablesWithIntrinsicBounds(0, R.mipmap.icon_err_nodata, 0, 0);
            } else if (status == 2) {
                errorView.setVisibility(View.VISIBLE);
                TextView errorTv = (TextView) errorView.findViewById(R.id.tv_layout_err);
                errorTv.setText("请检查网络");
                errorTv.setCompoundDrawablesWithIntrinsicBounds(0, R.mipmap.icon_network, 0, 0);
                rV.setVisibility(View.GONE);
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        GrassApp application = (GrassApp) getApplication();
        application.getActivityManager().popActivity(this);
    }
}
