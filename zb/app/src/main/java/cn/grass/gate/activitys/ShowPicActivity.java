package cn.grass.gate.activitys;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.umeng.analytics.MobclickAgent;
import com.weedys.weedlibrary.utils.FileUtil;
import com.weedys.weedlibrary.utils.LogUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import cn.grass.gate.GlobalApp;
import cn.grass.gate.R;
import cn.grass.gate.base.BaseActivity;
import cn.grass.gate.http.Api;
import cn.grass.gate.utils.DownLoadImageService;
import cn.grass.gate.utils.GlideLoader;
import cn.grass.gate.utils.MediaScanner;
import cn.grass.gate.utils.OptionDialogHelper;
import cn.grass.gate.utils.ToastUtil;
import cn.grass.gate.widgets.FixedViewPager;
import cn.grass.gate.widgets.TopView;
import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by weedys on 16/8/11.
 */
public class ShowPicActivity extends BaseActivity implements PhotoViewAttacher.OnViewTapListener {
    Activity activity;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showpic);
        initView();
    }

    FixedViewPager vp;
    String[] pics;
    boolean isHttp = true;
    private int pos = 0;
    public boolean isAllScreen = false;
    public boolean loaded = false;

    private void initView() {
        ((TopView) findViewById(R.id.top_bar)).setOnLeftListener(new TopView.OnLeftClickListener() {
            @Override
            public void onLeftClick(View v) {
                onBackPressed();
            }
        });

        pics = getIntent().getStringArrayExtra(KEY_PICS);
        pos = getIntent().getIntExtra(KEY_POS, 0);
        isAllScreen = getIntent().getIntExtra(KEY_ALL, 0) == 0 ? false : true;
        isHttp = getIntent().getIntExtra(KEY_HTTP, 1) == 1 ? true : false;

        if (isAllScreen) {
            findViewById(R.id.top_bar).setVisibility(View.GONE);
        }
        vp = (FixedViewPager) findViewById(R.id.vp);
        vp.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return pics == null ? 0 : pics.length;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                if (container != null) {
                    container.removeView((View) object);
                }
            }

            @Override
            public Object instantiateItem(ViewGroup container, final int position) {
                final PhotoView photoView = new PhotoView(container.getContext());
                String url = pics[position];
                if (isHttp) {
                    url = Api.HOST + url;
                }
                Glide.with(ShowPicActivity.this).load(url).asBitmap().into(new BitmapImageViewTarget(photoView) {

                    @Override
                    protected void setResource(Bitmap resource) {
                        super.setResource(resource);
                        if (resource != null)
                            loaded = true;
                    }

                    @Override
                    public void onLoadFailed(Exception e, Drawable errorDrawable) {
                        super.onLoadFailed(e, errorDrawable);
                        photoView.setImageResource(R.mipmap.icon_default_image);
                        loaded = false;
                    }
                });
                photoView.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
                    @Override
                    public void onPhotoTap(View view, float x, float y) {
                        if (isAllScreen) {
                            finish();
                        }
                    }
                });
                photoView.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View view) {
                        if (loaded)
                            OptionDialogHelper.showDialog(ShowPicActivity.this, "是否要保存图片到本地", "保存", "取消", new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                    String imgurl = pics[position];
                                    if (isHttp) {
                                        imgurl = Api.HOST + imgurl;
                                    }
                                    GlideLoader.downLoadImage(ShowPicActivity.this, imgurl, new DownLoadImageService.ImageDownLoadCallBack() {
                                        @Override
                                        public void onDownLoadSuccess(File f) {
                                            FileOutputStream fout = null;
                                            try {
                                                if (f != null && f.exists()) {
                                                    long mlen = f.length();
                                                    LogUtil.show("file:" + mlen);
                                                    if (mlen <= 0) {
                                                        updateHandler.sendEmptyMessage(-1);
                                                        return;
                                                    }
                                                    String fname = f.getName();
                                                    FileUtil.isExist(GlobalApp.PIC_URL);
                                                    FileUtil.saveFile(GlobalApp.PIC_URL+fname+".jpeg", new FileInputStream(f));
                                                    updateHandler.sendEmptyMessage(1);
                                                    MediaScanner mediaScanner = new MediaScanner(ShowPicActivity.this);
                                                    String[] filePaths = new String[]{GlobalApp.PIC_URL+fname+".jpeg"};
                                                    String[] mimeTypes = new String[]{MimeTypeMap.getSingleton().getMimeTypeFromExtension("png")};
                                                    mediaScanner.scanFiles(filePaths, mimeTypes);
                                                } else {
                                                    updateHandler.sendEmptyMessage(-1);
                                                }

                                            } catch (Exception e) {
                                                e.printStackTrace();
                                            } finally {
                                                try {
                                                    fout.close();
                                                    fout = null;
                                                } catch (IOException e) {
                                                    e.printStackTrace();
                                                }
                                            }


                                        }

                                        @Override
                                        public void onDownLoadFailed() {
                                            updateHandler.sendEmptyMessage(-1);
                                        }
                                    });
                                }
                            }, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {

                                }
                            });
                        return false;
                    }
                });
                // Now just add PhotoView to ViewPager and return it
                container.addView(photoView, ViewPager.LayoutParams.MATCH_PARENT, ViewPager.LayoutParams.MATCH_PARENT);
                return photoView;
            }
        });
        if (pos < pics.length) {
            vp.setCurrentItem(pos, true);
        }
    }

    public static String KEY_PICS = "pics";
    public static String KEY_POS = "pos";
    public static String KEY_ALL = "all_screen";
    public static String KEY_HTTP = "http";

    public static void ShowPicActivity(Context c, String[] pic, int pos) {
        Intent it = new Intent(c, ShowPicActivity.class);
        it.putExtra(KEY_PICS, pic);
        it.putExtra(KEY_POS, pos);
        it.putExtra(KEY_HTTP, 1);
        c.startActivity(it);
    }

    public static void ShowPicActivityAllScreen(Context c, String[] pic, int pos) {
        Intent it = new Intent(c, ShowPicActivity.class);
        it.putExtra(KEY_PICS, pic);
        it.putExtra(KEY_POS, pos);
        it.putExtra(KEY_ALL, 1);
        it.putExtra(KEY_HTTP, 1);
        c.startActivity(it);
    }

    public static void ShowLocalPicActivity(Context c, String[] pic, int pos) {
        Intent it = new Intent(c, ShowPicActivity.class);
        it.putExtra(KEY_PICS, pic);
        it.putExtra(KEY_POS, pos);
        it.putExtra(KEY_HTTP, 0);
        c.startActivity(it);
    }

    @Override
    public void onViewTap(View view, float x, float y) {
        finish();
    }

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

    public Handler updateHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == -1) {
                ToastUtil.shortShow("保存图片失败");
            } else if (msg.what == 1) {
                ToastUtil.shortShow("保存图片成功");
            }
        }
    };
}
