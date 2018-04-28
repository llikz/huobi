package com.weed.views;

import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import cn.grass.gate.R;

/**
 * Created by weedys on 16/10/13.
 */
public class RightDialog extends Dialog implements View.OnClickListener {

    protected Context mContext;

    protected View mContentView = null;

    protected Handler mHandler = new Handler();

    public RightDialog(Context context) {
        super(context, R.style.customDialog);
        getWindow().setGravity(Gravity.RIGHT);
        getWindow().setWindowAnimations(R.style.dialogWindowAnim);
        mContext = context;
    }
    public void addContentView(View v)
    {
        this.mContentView=v;
    }
    @Override
    public void show() {

        if (mContentView != null) {
            DisplayMetrics dm = new DisplayMetrics();
            WindowManager wm = (WindowManager) mContext
                    .getSystemService(Context.WINDOW_SERVICE);
            wm.getDefaultDisplay().getMetrics(dm);
            int width=dm.widthPixels*5/6;
            ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(width,
                    ViewGroup.LayoutParams.MATCH_PARENT);

            this.setContentView(mContentView, params);
            this.setCancelable(true);
            this.setCanceledOnTouchOutside(true);
        }
        super.show();
    }

    @Override
    public void dismiss() {
        if (mContentView != null) {
            // Animation anim = AnimationUtils.loadAnimation(mContext,
            // R.anim.dialog_out);
            // anim.setAnimationListener(new Animation.AnimationListener() {
            // @Override
            // public void onAnimationStart(Animation animation) {
            // // TODO Auto-generated method stub
            // }
            //
            // @Override
            // public void onAnimationRepeat(Animation animation) {
            // // TODO Auto-generated method stub
            // }
            //
            // @Override
            // public void onAnimationEnd(Animation animation) {
            // // TODO Auto-generated method stub
            // BottomBaseDialog.super.dismiss();
            // }
            // });
            //
            // mContentView.startAnimation(anim);
            mContentView = null;
        }
        super.dismiss();

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        dismiss();
    }
}
