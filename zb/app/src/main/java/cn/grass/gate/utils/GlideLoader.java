package cn.grass.gate.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.yancy.imageselector.ImageConfig;
import com.yancy.imageselector.ImageLoader;
import com.yancy.imageselector.ImageSelector;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import cn.grass.gate.GlobalApp;
import cn.grass.gate.R;

/**
 * Created by weedys on 16/7/27.
 */
public class GlideLoader implements ImageLoader {

    @Override
    public void displayImage(Context context, String path, ImageView imageView) {
        Glide.with(context)
                .load(path)
                .placeholder(com.yancy.imageselector.R.mipmap.imageselector_photo)
                .centerCrop()
                .into(imageView);
    }
    public static void show(Context c)
    {
        ImageConfig imageConfig
                = new ImageConfig.Builder(new GlideLoader())
                .steepToolBarColor(c.getResources().getColor(R.color.black))
                .titleBgColor(c.getResources().getColor(R.color.black))
                .titleSubmitTextColor(c.getResources().getColor(R.color.white))
                .titleTextColor(c.getResources().getColor(R.color.white))
                .crop(320,320,320,320)
                // 开启单选   （默认为多选）
                .singleSelect()
                // 开启拍照功能 （默认关闭）
                .showCamera()
                // 拍照后存放的图片路径（默认 /temp/picture） （会自动创建）
                .filePath(GlobalApp.PHOTO_PATH+"/")
                .build();

        ImageSelector.open((Activity)c, imageConfig);   // 开启图片选择器
    }

    public static void showCustom(Context c)
    {
        ImageConfig imageConfig
                = new ImageConfig.Builder(new GlideLoader())
                .steepToolBarColor(c.getResources().getColor(R.color.black))
                .titleBgColor(c.getResources().getColor(R.color.black))
                .titleSubmitTextColor(c.getResources().getColor(R.color.white))
                .titleTextColor(c.getResources().getColor(R.color.white))
                // 开启单选   （默认为多选）
                .singleSelect()
                // 开启拍照功能 （默认关闭）
                .showCamera()
                // 拍照后存放的图片路径（默认 /temp/picture） （会自动创建）
                .filePath(GlobalApp.PHOTO_PATH+"/")
                .build();

        ImageSelector.open((Activity)c, imageConfig);   // 开启图片选择器
    }
    public static void show(Context c,int size)
    {
        ImageConfig imageConfig
                = new ImageConfig.Builder(new GlideLoader())
                .steepToolBarColor(c.getResources().getColor(R.color.black))
                .titleBgColor(c.getResources().getColor(R.color.black))
                .titleSubmitTextColor(c.getResources().getColor(R.color.white))
                .titleTextColor(c.getResources().getColor(R.color.white))
                // 开启单选   （默认为多选）
                .mutiSelect()
                // 开启拍照功能 （默认关闭）
                .showCamera()
                .mutiSelectMaxSize(size)
                // 拍照后存放的图片路径（默认 /temp/picture） （会自动创建）
                .filePath(GlobalApp.PHOTO_PATH+"/")
                .build();


        ImageSelector.open((Activity)c, imageConfig);   // 开启图片选择器
    }

    public static void show(Context c, ArrayList<String> list, int number)
    {
        ImageConfig imageConfig
                = new ImageConfig.Builder(new GlideLoader())
                .steepToolBarColor(c.getResources().getColor(R.color.black))
                .titleBgColor(c.getResources().getColor(R.color.black))
                .titleSubmitTextColor(c.getResources().getColor(R.color.white))
                .titleTextColor(c.getResources().getColor(R.color.white))
                // 开启拍照功能 （默认关闭）
                .showCamera()
                // 拍照后存放的图片路径（默认 /temp/picture） （会自动创建）
                .filePath(GlobalApp.PHOTO_PATH+"/")
                .pathList(list)
                .mutiSelectMaxSize(number)
                .build();
        ImageSelector.open((Activity)c, imageConfig);   // 开启图片选择器
    }

    public static void show2(Context c, ArrayList<String> list, int number)
    {
        ImageConfig imageConfig
                = new ImageConfig.Builder(new GlideLoader())
                .steepToolBarColor(c.getResources().getColor(R.color.black))
                .titleBgColor(c.getResources().getColor(R.color.black))
                .titleSubmitTextColor(c.getResources().getColor(R.color.white))
                .titleTextColor(c.getResources().getColor(R.color.white))
                // 拍照后存放的图片路径（默认 /temp/picture） （会自动创建）
                .filePath(GlobalApp.PHOTO_PATH+"/")
                .pathList(list)
                .mutiSelectMaxSize(number)
                .build();
        ImageSelector.open((Activity)c, imageConfig);   // 开启图片选择器
    }
    /**
     * 单线程列队执行
     */
    private static ExecutorService singleExecutor = null;
    /**
     * 执行单线程列队执行
     */

    public static  void downLoadImage(Context c,String url,DownLoadImageService.ImageDownLoadCallBack callBack){
        DownLoadImageService service = new DownLoadImageService(c,url,
               callBack);
        //启动图片下载线程
        if (singleExecutor == null) {
            singleExecutor = Executors.newSingleThreadExecutor();
        }
        singleExecutor.submit(service);
    }

    /**
     *  常用来加载头像
     * @param context
     * @param path
     * @param imageView
     */
    public static void putImage(final Context context, String path, final ImageView imageView) {
        Glide.with(context).load(path).asBitmap().centerCrop().diskCacheStrategy(DiskCacheStrategy.ALL).into(new BitmapImageViewTarget(imageView) {
            @Override
            protected void setResource(Bitmap resource) {
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(context.getResources(), resource);
                circularBitmapDrawable.setCircular(true);
                if(imageView!=null)imageView.setImageDrawable(circularBitmapDrawable);
            }

            @Override
            public void onLoadFailed(Exception e, Drawable errorDrawable) {
                RoundedBitmapDrawable circularBitmapDrawable =
                        RoundedBitmapDrawableFactory.create(context.getResources(), BitmapFactory.decodeResource(context.getResources(),R.mipmap.icon_user_avatar));
                circularBitmapDrawable.setCircular(true);
                imageView.setImageDrawable(circularBitmapDrawable);
            }
        });
    }
}
