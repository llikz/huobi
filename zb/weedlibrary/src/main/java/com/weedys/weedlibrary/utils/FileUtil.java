package com.weedys.weedlibrary.utils;

import android.app.Activity;
import android.content.ContentResolver;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.net.Uri;
import android.os.Environment;
import android.text.TextUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.UUID;


/**
 * 文件处理类
 *
 * @author 唐晓泽
 */
public class FileUtil {
    /**
     * 随机生成一个文件名
     *
     * @return
     */
    public static String getRandomName() {
        return Math.abs(UUID.randomUUID().hashCode()) + "";
    }

    /**
     * 首先判断sdcard是否插入
     *
     * @return
     */
    public static boolean isHasSdcard() {
        String status = Environment.getExternalStorageState();
        return status.equals(Environment.MEDIA_MOUNTED);
    }

    /**
     * 递归删除文件或文件夹以及该文件夹下的文件
     */
    public static void deleteFolderOrFile(String path) {
        if (!FileUtil.isHasSdcard()) {
            return;
        }
        File sdurlfile = new File(path);
        if (!sdurlfile.exists()) {
            return;
        }
        if (!sdurlfile.isDirectory()) {
            sdurlfile.delete();
        } else if (sdurlfile.isDirectory()) {
            String[] filelist = sdurlfile.list();
            for (int i = 0; i < filelist.length; i++) {
                File delfile = new File(path + filelist[i]);
                if (!delfile.isDirectory()) {
                    delfile.delete();
                } else if (delfile.isDirectory()) {
                    deleteFolderOrFile(path + filelist[i]);
                }
            }
        }
    }

    /**
     * @param bitmap
     * @param filePath
     * @return void
     * @Title: saveDrawableToCache
     */
    public static void saveDrawableToCache(Bitmap bitmap, String filePath) {
        OutputStream outStream = null;
        try {
            File file = new File(filePath);

            file.createNewFile();

            outStream = new FileOutputStream(file);
            bitmap.compress(CompressFormat.JPEG, 100, outStream);
            outStream.flush();
            outStream.close();

        } catch (IOException e) {

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
    }

    /**
     * 将字符串存到sdcard上
     *
     * @param filePath 文件路径
     * @param str      数据
     * @return 是否成功
     */
    public static void saveFile(String filePath, String str) {
        FileOutputStream fos = null;
        if (!FileUtil.isHasSdcard()) {

            return;
        }
        try {
            File file = new File(filePath);
            if (!file.getParentFile().exists())
                file.getParentFile().mkdirs();
            fos = new FileOutputStream(file);
            if (str == null) {
                str = "";
            }
            fos.write(str.getBytes("UTF-8"));
            fos.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != fos)
                    fos.close();
                System.gc();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public static void saveFile(String filePath, InputStream in) {

        FileOutputStream fout = null;
        try {
            if (in == null) {
                return;
            }
            fout = new FileOutputStream(filePath);
            int len = in.available() + 1024;
            LogUtil.show("len:" + len);
            byte[] bs = new byte[len];
            int cc = 0;
            while ((cc = in.read(bs)) != -1) {
                fout.write(bs, 0, cc);
                fout.flush();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != fout)
                    fout.close();
                if (null != in)
                    in.close();
                System.gc();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * 根据给定的文件获取文件的内容
     *
     * @param
     * @return 文件内容，获取失败时返回null
     */
    public static String readFile(String filePath) {
        File file = new File(filePath);
        if (!file.exists())
            return null;
        FileInputStream fis = null;
        final byte[] buffer = new byte[1024 * 7];
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            fis = new FileInputStream(file);
            int x;
            while ((x = fis.read(buffer)) != -1) {
                baos.write(buffer, 0, x);
            }
            baos.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                baos.close();
                if (null != fis)
                    fis.close();
                System.gc();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return new String(baos.toByteArray());// new
        // String(baos.toByteArray(),"UTF-8");
    }

    /**
     * 通过uri获取文件路径
     *
     * @param mUri
     * @return
     */
    public static String getFilePath(Activity act, Uri mUri) {
        try {
            if (mUri.getScheme().equals("file")) {
                return mUri.getPath();
            } else {
                return getFilePathByUri(act.getContentResolver(), mUri);
            }
        } catch (Exception ex) {
            return null;
        }
    }

    private static String getFilePathByUri(ContentResolver mContentResolver,
                                           Uri mUri) throws FileNotFoundException {
        String imgPath;
        Cursor cursor = mContentResolver.query(mUri, null, null, null, null);
        cursor.moveToFirst();
        imgPath = cursor.getString(1);
        return imgPath;
    }

    /**
     * 获取本地保存的UUID(注册登录时，无法获取到imei号时生成的uuid)
     *
     * @return
     */
    public static String getLocalDeviceIdUUID() {
        String result = null;
        String filePath = "/sdcard/logs/deviceId";
        File file = new File(filePath);
        if (file.exists()) {
            result = readFile(filePath);
        }
        if (!TextUtils.isEmpty(result)) {
            result = UUID.randomUUID().toString();
            saveFile(filePath, result);
        }
        return result;
    }


//	/**
//	 * 从resources中的raw 文件夹中获取文件并读取数据
//	 *
//	 * @param context
//	 * @param rId
//	 *            资源文件id，比如 R.raw.str
//	 * @return 返回文件字符串
//	 */
//	public static String getFromRaw(Context context, int rId) {
//		String result = "";
//		try {
//			InputStream in = context.getResources().openRawResource(rId);
//			// 获取文件的字节数
//			int lenght = in.available();
//			// 创建byte数组
//			byte[] buffer = new byte[lenght];
//			// 将文件中的数据读到byte数组中
//			in.read(buffer);
//			result = EncodingUtils.getString(buffer, "UTF-8");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return result;
//	}

    /**
     * 从assets 文件夹中获取文件并读取数据
     *
     * @param fileName
     *            资源文件名
     * @return 返回文件字符串
     */
//	public static String getFromAssets(Context context, String fileName) {
//		String result = "";
//		try {
//			InputStream in = context.getResources().getAssets().open(fileName);
//			// 获取文件的字节数
//			int lenght = in.available();
//			// 创建byte数组
//			byte[] buffer = new byte[lenght];
//			// 将文件中的数据读到byte数组中
//			in.read(buffer);
//			result = EncodingUtils.getString(buffer, "UTF-8");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return result;
//	}

    /**
     * 设置一定高宽的缩略图
     *
     * @param path
     * @param itemHeight
     * @return
     */
    public static Bitmap getThumBitmap(String path, int itemHeight) {
        Bitmap dest = null;
        if (path != null) {
            Options options = new Options();
            options.inJustDecodeBounds = true;
            Bitmap bs = BitmapFactory.decodeFile(path, options);

            int w = options.outWidth;
            int h = options.outHeight;
            float scan = 1;
            int mw = 0;
            int mh = 0;
            if (w <= h) {
                scan = (float) h / (float) itemHeight;
                if (scan <= 1) {
                    scan = 1;
                }
                mw = (int) (w / scan);
                mh = itemHeight;
                if (h < itemHeight) {
                    mh = h;
                    mw = w;
                }
            } else {
                scan = (float) w / (float) itemHeight;
                if (scan <= 1) {
                    scan = 1;
                }
                mh = (int) (h / scan);
                mw = itemHeight;
                if (w < itemHeight) {
                    mw = w;
                    mh = h;
                }

            }
            options.inSampleSize = (int) scan;
            options.outHeight = mh;
            options.outWidth = mw;
            options.inPreferredConfig = Bitmap.Config.ARGB_4444;
            options.inPurgeable = true;
            options.inInputShareable = true;
            options.inJustDecodeBounds = false;
            //	Bitmap bm=BitmapFactory.decodeByteArray(out.toByteArray(), 0, out.toByteArray().length,options);
            Bitmap bm = BitmapFactory.decodeFile(path, options);
            if (bm == null) {
                return null;
            }
            dest = Bitmap.createBitmap(bm, 0, 0, mw, mh);
            bs = null;
            bm = null;
        }
        return dest;
    }
//	/***
//	 * 将图片压缩后放入自定义文件夹
//	 * @param path
//	 * @param width
//	 * @return
//	 */
//	public static String getComp(String path,int width){
//		String fname=Global.PHOTO_PATH+"/"+System.currentTimeMillis()+".jpg";
//		Bitmap bitmap=ImageLoader.getInstance().loadImageSync(Scheme.FILE.wrap(path));
//		try {
//			FileOutputStream out=new FileOutputStream(fname);
//			if(bitmap!=null){
//				bitmap.compress(CompressFormat.JPEG, 80, out);
//				out.flush();
//				out.close();
//				out=null;
//			}
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return fname;
//	//	ByteArrayInputStream inputStream=new ByteArrayInputStream(out.toByteArray());
//
//	}

    /**
     * 读取文件内容
     *
     * @param objPath
     * @return
     */
    public static Object readObj(String objPath) {
        try {
            if (new File(objPath).exists()) {
                FileInputStream fin = new FileInputStream(objPath);
                ObjectInputStream input = new ObjectInputStream(fin);
                Object out = input.readObject();
                input.close();
                fin.close();
                return out;
            }

        } catch (Exception e) {
            LogUtil.show(e.getMessage());
        }
        return null;
    }

    /**
     * 写入文件
     *
     * @param obj
     * @param url
     * @return
     */
    public static void saveObj(Object obj, String url) {
        try {
            FileOutputStream fout = new FileOutputStream(url);
            ObjectOutputStream out = new ObjectOutputStream(fout);
            out.writeObject(obj);
            out.flush();
            out.close();
            fout.close();

        } catch (Exception e) {

        }
    }

    /**
     * 删除文件
     *
     * @param url
     * @return
     */
    public static void delFile(String url) {
        File f = new File(url);
        if (f.exists()) {
            f.delete();
        }
    }

//    public static void compressPicture(String srcPath, String desPath) {//srcPath 原路径
//        FileOutputStream fos = null;
//        BitmapFactory.Options op = new BitmapFactory.Options();
//
//        op.inJustDecodeBounds = true;
//        Bitmap bitmap = BitmapFactory.decodeFile(srcPath, op);
//        op.inJustDecodeBounds = false;
//
//        // 缩放图片的尺寸
//        float w = op.outWidth;
//        float h = op.outHeight;
//        float hh = 1024f;//
//        float ww = 1024f;//
//        // 最长宽度或高度1024
//        float be = 1.0f;
//        if (w > h && w > ww) {
//            be = (float) (w / ww);
//        } else if (w < h && h > hh) {
//            be = (float) (h / hh);
//        }
//        if (be <= 0) {
//            be = 1.0f;
//        }
//        op.inSampleSize = (int) be;// 设置缩放比例,这个数字越大,图片大小越小.
//        // 重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
//        bitmap = BitmapFactory.decodeFile(srcPath, op);
//        int desWidth = (int) (w / be);
//        int desHeight = (int) (h / be);
//        bitmap = Bitmap.createScaledBitmap(bitmap, desWidth, desHeight, true);
//        try {
//            fos = new FileOutputStream(desPath);
//            if (bitmap != null) {
//                bitmap.compress(Bitmap.CompressFormat.JPEG, 80, fos);
//            }
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//
//    }



    /**
     * @param path 文件夹路径
     */
    public static void isExist(String path) {
        File file = new File(path);
        if (!file.exists()) {
            file.mkdir();
        }
    }

    /**
     * 下载文件到本地
     *
     * @param urlString 被下载的文件地址
     * @param filename  本地文件名
     * @throws Exception 各种异常
     */
    public static void download(String urlString, String filename) throws Exception {
        // 构造URL
        URL url = new URL(urlString);
        // 打开连接
        URLConnection con = url.openConnection();
        // 输入流
        InputStream is = con.getInputStream();
        // 1K的数据缓冲
        byte[] bs = new byte[1024];
        // 读取到的数据长度
        int len;
        // 输出的文件流
        OutputStream os = new FileOutputStream(filename);
        // 开始读取
        while ((len = is.read(bs)) != -1) {
            os.write(bs, 0, len);
        }
        // 完毕，关闭所有链接
        os.close();
        is.close();
    }

}