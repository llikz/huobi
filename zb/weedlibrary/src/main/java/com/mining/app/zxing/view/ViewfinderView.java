/*
 * Copyright (C) 2008 ZXing authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mining.app.zxing.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;

import com.google.zxing.ResultPoint;
import com.mining.app.zxing.camera.CameraManager;
import com.weedys.weedlibrary.R;
import com.weedys.weedlibrary.utils.DensityUtil;

import java.util.Collection;
import java.util.HashSet;

/**
 * This view is overlaid on top of the camera preview. It adds the viewfinder
 * rectangle and partial transparency outside it, as well as the laser scanner
 * animation and result points.
 * 
 */
public final class ViewfinderView extends View {
	private static final String TAG = "log";
	/**
	 */
	private static final long ANIMATION_DELAY = 10L;
	private static final int OPAQUE = 0xFF;

	/**
	 * 四个绿色边角对应的长度
	 */
	private int ScreenRate;
	
	/**
	 * 四个绿色边角对应的宽度
	 */
	private static final int CORNER_WIDTH = 10;
	/**
	 *  扫描框中的中间线的宽度
	 */
	private static final int MIDDLE_LINE_WIDTH = 6;
	
	/**
	 * 扫描框中的中间线的与扫描框左右的间隙
	 */
	private static final int MIDDLE_LINE_PADDING = 5;
	
	/**
	 * 中间那条线每次刷新移动的距离
	 */
	private static final int SPEEN_DISTANCE = 5;
	
	/**
	 * 手机的屏幕密度
	 */
	private static float density;
	/**
	 */
	private static final int TEXT_SIZE = 16;
	/**
	 * 字体距离扫描框上边的距离
	 */
	private static final int TEXT_PADDING_TOP = 30;
	
	/**
	 */
	private Paint paint;
	
	/**
	 */
	private int slideTop;
	
	/**
	 */
	private int slideBottom;
	
	/**
	 */
	private Bitmap resultBitmap;
	private final int maskColor;
	private final int resultColor;
	
	private final int resultPointColor;
	private Collection<ResultPoint> possibleResultPoints;
	private Collection<ResultPoint> lastPossibleResultPoints;

	boolean isFirst;
	
	public ViewfinderView(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		density = context.getResources().getDisplayMetrics().density;

		initWindow(context);
		//将像素转换成dp
		ScreenRate = (int)(20 * density);

		paint = new Paint();
		Resources resources = getResources();
		maskColor = resources.getColor(R.color.viewfinder_mask);
		resultColor = resources.getColor(R.color.result_view);

		resultPointColor = resources.getColor(R.color.possible_result_points);
		possibleResultPoints = new HashSet<ResultPoint>(5);
	}
	private int screenWidth = 0;
	private int screenHeight = 0;
	private void initWindow(Context c){
		DisplayMetrics metrics = new DisplayMetrics();
		WindowManager wm = (WindowManager) c
				.getSystemService(Context.WINDOW_SERVICE);
		wm.getDefaultDisplay().getMetrics(metrics);
		if (metrics.widthPixels < metrics.heightPixels) {
			screenWidth = metrics.widthPixels;
			screenHeight = metrics.heightPixels;
		} else {
			screenWidth = metrics.heightPixels;
			screenHeight = metrics.widthPixels;
		}
	}

	@Override
	public void onDraw(Canvas canvas) {
		//中间的扫描框，你要修改扫描框的大小，去CameraManager里面修改
		Rect frame = CameraManager.get().getFramingRect();
		if (frame == null) {
			return;
		}
		
		if(!isFirst){
			isFirst = true;
			slideTop = frame.top;
			slideBottom = frame.bottom;
		}
		
		int width = canvas.getWidth();
		int height = canvas.getHeight();

		paint.setColor(resultBitmap != null ? resultColor : maskColor);
		
		canvas.drawRect(0, 0, width, frame.top, paint);
		canvas.drawRect(0, frame.top, frame.left, frame.bottom + 1, paint);
		canvas.drawRect(frame.right + 1, frame.top, width, frame.bottom + 1,
				paint);
		canvas.drawRect(0, frame.bottom + 1, width, height, paint);
		
		

		if (resultBitmap != null) {
			// Draw the opaque result bitmap over the scanning rectangle
			paint.setAlpha(OPAQUE);
			canvas.drawBitmap(resultBitmap, frame.left, frame.top, paint);
		} else {

			//��ɨ�����ϵĽǣ��ܹ�8������
			paint.setColor(Color.GREEN);
			canvas.drawRect(frame.left, frame.top, frame.left + ScreenRate,
					frame.top + CORNER_WIDTH, paint);
			canvas.drawRect(frame.left, frame.top, frame.left + CORNER_WIDTH, frame.top
					+ ScreenRate, paint);
			canvas.drawRect(frame.right - ScreenRate, frame.top, frame.right,
					frame.top + CORNER_WIDTH, paint);
			canvas.drawRect(frame.right - CORNER_WIDTH, frame.top, frame.right, frame.top
					+ ScreenRate, paint);
			canvas.drawRect(frame.left, frame.bottom - CORNER_WIDTH, frame.left
					+ ScreenRate, frame.bottom, paint);
			canvas.drawRect(frame.left, frame.bottom - ScreenRate,
					frame.left + CORNER_WIDTH, frame.bottom, paint);
			canvas.drawRect(frame.right - ScreenRate, frame.bottom - CORNER_WIDTH,
					frame.right, frame.bottom, paint);
			canvas.drawRect(frame.right - CORNER_WIDTH, frame.bottom - ScreenRate,
					frame.right, frame.bottom, paint);

			
			slideTop += SPEEN_DISTANCE;
			if(slideTop >= frame.bottom){
				slideTop = frame.top;
			}
			canvas.drawRect(frame.left + MIDDLE_LINE_PADDING, slideTop - MIDDLE_LINE_WIDTH/2, frame.right - MIDDLE_LINE_PADDING,slideTop + MIDDLE_LINE_WIDTH/2, paint);
			
			
			//��ɨ����������
			 

			
			Rect targetRect = new Rect(50, 50,screenWidth-100, 200);
			Paint mpain=new Paint(Paint.ANTI_ALIAS_FLAG);
			mpain.setColor(Color.WHITE);
			mpain.setTextSize(DensityUtil.dip2px(getContext(),TEXT_SIZE));
		//	paint.setAlpha(0x40);
		
			mpain.setTypeface(Typeface.create("System", Typeface.BOLD));
			String tipString=getResources().getString(R.string.scan_text); 
			float left = (screenWidth - getTextWidth(tipString, mpain)) / 2;
			canvas.drawText(
					tipString,
					left,
					(float) (frame.bottom + (float) TEXT_PADDING_TOP * density),
					mpain); 
		  
			Collection<ResultPoint> currentPossible = possibleResultPoints;
			Collection<ResultPoint> currentLast = lastPossibleResultPoints;
			if (currentPossible.isEmpty()) {
				lastPossibleResultPoints = null;
			} else {
				possibleResultPoints = new HashSet<ResultPoint>(8);
				lastPossibleResultPoints = currentPossible;
				paint.setAlpha(OPAQUE);
				paint.setColor(resultPointColor);
				for (ResultPoint point : currentPossible) {
					canvas.drawCircle(frame.left + point.getX(), frame.top
							+ point.getY(), 6.0f, paint);
				}
			}
			if (currentLast != null) {
				paint.setAlpha(OPAQUE / 2);
				paint.setColor(resultPointColor);
				for (ResultPoint point : currentLast) {
					canvas.drawCircle(frame.left + point.getX(), frame.top
							+ point.getY(), 3.0f, paint);
				}
			}
 
			postInvalidateDelayed(ANIMATION_DELAY, frame.left, frame.top,
					frame.right, frame.bottom);
			
		}
	}
	 public int getTextWidth(String text, Paint paint) {
		 Rect bounds = new Rect();
		 paint.getTextBounds(text, 0, text.length(), bounds);
		 int width = bounds.left + bounds.width();
		 return width;
		}
	public void drawViewfinder() {
		resultBitmap = null;
		invalidate();
	}

	/**
	 * Draw a bitmap with the result points highlighted instead of the live
	 * scanning display.
	 * 
	 * @param barcode
	 *            An image of the decoded barcode.
	 */
	public void drawResultBitmap(Bitmap barcode) {
		resultBitmap = barcode;
		invalidate();
	}

	public void addPossibleResultPoint(ResultPoint point) {
		possibleResultPoints.add(point);
	}

}
