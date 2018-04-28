package cn.grass.gate.utils;

import cn.grass.gate.GrassApp;


public class DensityUtil {
	public static int dip2px(float dpValue) {
		if(GrassApp.getInstance()==null ||GrassApp.getInstance().getResources()==null){
			return (int)dpValue;
		}
		final float scale = GrassApp.getInstance().getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	public static int px2dip(float pxValue) {

		final float scale = GrassApp.getInstance().getResources().getDisplayMetrics().density;

		return (int) (pxValue / scale + 0.5f);

	}



}
