package com.weed.views;

import android.app.Dialog;
import android.content.Context;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.WindowManager;

import cn.grass.gate.R;


public class BottomDialog extends Dialog implements View.OnClickListener {

	protected Context mContext;

	protected View mContentView = null;

	protected Handler mHandler = new Handler();

	public BottomDialog(Context context) {
		super(context, R.style.customDialog);
		getWindow().setGravity(Gravity.BOTTOM);
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

			LayoutParams params = new LayoutParams(dm.widthPixels,
					LayoutParams.WRAP_CONTENT);

			this.setContentView(mContentView, params);
			this.setCancelable(true);
			this.setCanceledOnTouchOutside(true);

			// mContentView.startAnimation(AnimationUtils.loadAnimation(mContext,
			// R.anim.dialog_in));
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
}
