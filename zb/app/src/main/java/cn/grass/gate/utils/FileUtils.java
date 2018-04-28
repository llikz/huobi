package cn.grass.gate.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Environment;

import com.weedys.weedlibrary.utils.FileUtil;

import cn.grass.gate.GlobalApp;

/**
 * 文件相关辅助类
 *
 * @author panyi
 *
 */
public class FileUtils {
	public static String PIC_URL = GlobalApp.APP_DIR+"/cache/";
	public static final String FOLDER_NAME = "edit";

	/**
	 * 获取存贮文件的文件夹路径
	 *
	 * @return
	 */
	public static File createFolders() {
//		File baseDir;
//		if (android.os.Build.VERSION.SDK_INT < 8) {
//			baseDir = Environment.getExternalStorageDirectory();
//		} else {
//			baseDir = Environment
//					.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
//		}
//		if (baseDir == null)
//			return Environment.getExternalStorageDirectory();
		File aviaryFolder = new File(PIC_URL, FOLDER_NAME);
		if (aviaryFolder.exists())
			return aviaryFolder;
		if (aviaryFolder.isFile())
			aviaryFolder.delete();
		if (aviaryFolder.mkdirs())
			return aviaryFolder;
		return Environment.getExternalStorageDirectory();
	}

	public static File genEditFile(){
		return FileUtils.getEmptyFile(System.currentTimeMillis() + ".png");
	}

	public static File getEmptyFile(String name) {
		File folder = FileUtils.createFolders();
		if (folder != null) {
			if (folder.exists()) {
				File file = new File(folder, name);
				return file;
			}
		}
		return null;
	}

	/**
	 * 删除指定文件
	 *
	 * @param path
	 * @return
	 */
	public static boolean deleteFileNoThrow(String path) {
		File file;
		try {
			file = new File(path);
		} catch (NullPointerException e) {
			return false;
		}

		if (file.exists()) {
			return file.delete();
		}
		return false;
	}

	/**
	 * 保存图片
	 *
	 * @param bitName
	 * @param mBitmap
	 */
	public static String saveBitmap(String bitName, Bitmap mBitmap) {
		File baseFolder = createFolders();
		File f = new File(baseFolder.getAbsolutePath(), bitName);
		FileOutputStream fOut = null;
		try {
			f.createNewFile();
			fOut = new FileOutputStream(f);
		} catch (IOException e) {
			e.printStackTrace();
		}
		mBitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
		try {
			fOut.flush();
			fOut.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return f.getAbsolutePath();
	}



	/**
	 * @param bitmap
	 * @return String
	 * @Title: saveDrawableToCache
	 */
	public static String saveDrawableToCache(Bitmap bitmap) {
		String filePath = GlobalApp.PIC_URL + System.currentTimeMillis()+".JPG";
		OutputStream outStream = null;
		try {
			File file = new File(filePath);

			file.createNewFile();

			outStream = new FileOutputStream(file);
			if (bitmap!=null){
				bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
				outStream.flush();
				outStream.close();
			}
		} catch (IOException e) {
			filePath = null;
			e.printStackTrace();
		} finally {
			if (null != bitmap) {
				if (!bitmap.isRecycled()) {
					bitmap.recycle();
				}
			}
			if (null != outStream) {
				try {
					outStream.close();
				} catch (IOException e) {
				}
			}
		}
		return filePath;
	}


	// 获取文件夹大小
	public static long getFolderSize(File file) throws Exception {
		long size = 0;
		try {
			File[] fileList = file.listFiles();
			for (int i = 0; i < fileList.length; i++) { // 如果下面还有文件
				if (fileList[i].isDirectory()) {
					size = size + getFolderSize(fileList[i]);
				} else {
					size = size + fileList[i].length();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return size;
	}

	/** * 格式化单位 * * @param size * @return */
	public static String getFormatSize(double size) {
		double kiloByte = size / 1024d;
		int megaByte = (int) (kiloByte / 1024d);
		return megaByte + "MB";
	}

	/**
	 *
	 * @Description:
	 * @Author 11120500
	 * @Date 2013-4-25
	 */
	public static boolean isConnect(Context context) {
		try {
			ConnectivityManager connectivity = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			if (connectivity != null) {
				NetworkInfo info = connectivity.getActiveNetworkInfo();
				if (info != null && info.isConnected()) {
					if (info.getState() == NetworkInfo.State.CONNECTED) {
						return true;
					}
				}
			}
		} catch (Exception e) {

		}
		return false;
	}

	/***
	 * 获取指定目录下的所有的文件（不包括文件夹），采用了递归
	 *
	 * @param obj
	 * @return
	 */
	public static ArrayList<File> getListFiles(Object obj) {
		File directory = null;
		if (obj instanceof File) {
			directory = (File) obj;
		} else {
			directory = new File(obj.toString());
		}
		ArrayList<File> files = new ArrayList<File>();
		if (directory.isFile()) {
			files.add(directory);
			return files;
		} else if (directory.isDirectory()) {
			File[] fileArr = directory.listFiles();
			for (int i = 0; i < fileArr.length; i++) {
				File fileOne = fileArr[i];
				files.addAll(getListFiles(fileOne));
			}
		}
		return files;
	}


//	public static String compressPicture(String srcPath) {//srcPath 原路径
//		String desPath =null;
//		FileOutputStream fos = null;
//		BitmapFactory.Options op = new BitmapFactory.Options();
//
//		op.inJustDecodeBounds = true;
//		Bitmap bitmap = BitmapFactory.decodeFile(srcPath, op);
//		if (bitmap!=null){
//			desPath = GlobalApp.PIC_URL + DateUtil.getSimpleTimeFormat(System.currentTimeMillis()) + ".JPG";
//			op.inJustDecodeBounds = false;
//
//			// 缩放图片的尺寸
//			float w = op.outWidth;
//			float h = op.outHeight;
//			float hh = 1024f;//
//			float ww = 1024f;//
//			// 最长宽度或高度1024
//			float be = 1.0f;
//			if (w > h && w > ww) {
//				be = (float) (w / ww);
//			} else if (w < h && h > hh) {
//				be = (float) (h / hh);
//			}
//			if (be <= 0) {
//				be = 1.0f;
//			}
//			op.inSampleSize = (int) be;// 设置缩放比例,这个数字越大,图片大小越小.
//			// 重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
//			bitmap = BitmapFactory.decodeFile(srcPath, op);
//			int desWidth = (int) (w / be);
//			int desHeight = (int) (h / be);
//			bitmap = Bitmap.createScaledBitmap(bitmap, desWidth, desHeight, true);
//			try {
//				fos = new FileOutputStream(desPath);
//				if (bitmap != null) {
//					bitmap.compress(Bitmap.CompressFormat.JPEG, 80, fos);
//				}
//			} catch (FileNotFoundException e) {
//				e.printStackTrace();
//			}
//		}
//		return desPath;
//	}


	/***
	 * 图片压缩
	 *
	 * @param srcPath 原路径
	 * @param del 压缩后是否删除原图片
	 * @return desPath 压缩后路径
	 */
	public static String compressPicture(String srcPath,Boolean del) {
		String desPath =null;
		FileOutputStream fos = null;
		BitmapFactory.Options newOpts = new BitmapFactory.Options();
		//开始读入图片，此时把options.inJustDecodeBounds 设回true了
		newOpts.inJustDecodeBounds = true;
		Bitmap bitmap = BitmapFactory.decodeFile(srcPath,newOpts);//此时返回bm为空

		newOpts.inJustDecodeBounds = false;
		int w = newOpts.outWidth;
		int h = newOpts.outHeight;
		//现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
		float hh = 800f;//这里设置高度为800f
		float ww = 480f;//这里设置宽度为480f
		//缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
		int be = 1;//be=1表示不缩放
		if (w > h && w > ww) {//如果宽度大的话根据宽度固定大小缩放
			be = (int) (newOpts.outWidth / ww);
		} else if (w < h && h > hh) {//如果高度高的话根据宽度固定大小缩放
			be = (int) (newOpts.outHeight / hh);
		}
		if (be <= 0)
			be = 1;
		newOpts.inSampleSize = be;//设置缩放比例
		//重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
		bitmap = BitmapFactory.decodeFile(srcPath, newOpts);
		if (bitmap!=null){
			desPath = GlobalApp.PIC_URL + DateUtil.getSimpleTimeFormat(System.currentTimeMillis()) + ".JPG";
			try {
				fos = new FileOutputStream(desPath);
				if (bitmap != null) {
					bitmap.compress(Bitmap.CompressFormat.JPEG, 80, fos);
				}
				if (del){
					FileUtil.delFile(srcPath);
				}
			} catch (FileNotFoundException e) {
				desPath=null;
				e.printStackTrace();
			}
		}
		return desPath;//压缩好比例大小后再进行质量压缩
	}

	/***
	 * 图片压缩long文件名变了
	 *
	 * @param srcPath 原路径
	 * @param del 压缩后是否删除原图片
	 * @return desPath 压缩后路径
	 */
	public static String compressPicture2(String srcPath,Boolean del) {
		String desPath =null;
		FileOutputStream fos = null;
		BitmapFactory.Options newOpts = new BitmapFactory.Options();
		//开始读入图片，此时把options.inJustDecodeBounds 设回true了
		newOpts.inJustDecodeBounds = true;
		Bitmap bitmap = BitmapFactory.decodeFile(srcPath,newOpts);//此时返回bm为空

		newOpts.inJustDecodeBounds = false;
		int w = newOpts.outWidth;
		int h = newOpts.outHeight;
		//现在主流手机比较多是800*480分辨率，所以高和宽我们设置为
		float hh = 800f;//这里设置高度为800f
		float ww = 480f;//这里设置宽度为480f
		//缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
		int be = 1;//be=1表示不缩放
		if (w > h && w > ww) {//如果宽度大的话根据宽度固定大小缩放
			be = (int) (newOpts.outWidth / ww);
		} else if (w < h && h > hh) {//如果高度高的话根据宽度固定大小缩放
			be = (int) (newOpts.outHeight / hh);
		}
		if (be <= 0)
			be = 1;
		newOpts.inSampleSize = be;//设置缩放比例
		//重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
		bitmap = BitmapFactory.decodeFile(srcPath, newOpts);
		if (bitmap!=null){
			desPath = GlobalApp.PIC_URL + System.currentTimeMillis() + ".JPG";
			try {
				fos = new FileOutputStream(desPath);
				if (bitmap != null) {
					bitmap.compress(Bitmap.CompressFormat.JPEG, 80, fos);
				}
				if (del){
					FileUtil.delFile(srcPath);
				}
			} catch (FileNotFoundException e) {
				desPath=null;
				e.printStackTrace();
			}
		}
		return desPath;//压缩好比例大小后再进行质量压缩
	}


}// end
