package cn.grass.gate.activitys;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.weedys.weedlibrary.utils.LogUtil;

import cn.grass.gate.GlobalApp;
import cn.grass.gate.MainActivity;
import cn.grass.gate.R;
import cn.grass.gate.base.BaseActivity;
import cn.grass.gate.data.PrefManager;
import cn.grass.gate.utils.StatusBarUtils;

/**
 * Created by weedys on 16/8/1.
 */
public class FirstPageActivity extends BaseActivity {

    TextView intoTv;
    ViewPager vp;
    private int[] res={R.mipmap.icon_default_one,R.mipmap.icon_default_two,R.mipmap.icon_default_third,R.mipmap.icon_default_four};

    LinearLayout pointView;
    GestureDetector gestureDetector;
    int type=GUIDE_FIRST;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtils.setStatueBarColor(FirstPageActivity.this);
        setContentView(R.layout.activity_first_page);
        if (getIntent() != null) {
            type = getIntent().getIntExtra(GUIDE_TYPE,GUIDE_FIRST);
        }

        gestureDetector=new GestureDetector(this, new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                if(vp.getCurrentItem()==3){
                    LogUtil.show("滑动"+velocityX);
                }
                return super.onFling(e1, e2, velocityX, velocityY);
            }
        });

        intoTv=(TextView)findViewById(R.id.tv_into);
        if (type==GUIDE_NO_FIRST){
            intoTv.setText("返回并关闭");
            intoTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }else {
            intoTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    PrefManager.setPrefInt(GlobalApp.PRE_FIRST,0);
                    LoginActivity.startLoginActivity(FirstPageActivity.this);
                    finish();
                }
            });
        }
        pointView=(LinearLayout)findViewById(R.id.view_point);

        for(int i=0;i<res.length;i++){
            ImageView iv=new ImageView(this);
            iv.setImageResource(R.mipmap.icon_round_grey);
            if(i==0){
                iv.setImageResource(R.mipmap.icon_round_blue);
            }
            iv.setPadding(10,0,0,0);
            pointView.addView(iv);
        }

        vp=(ViewPager)findViewById(R.id.vp);
        vp.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return res.length;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view==object;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                if(container.getChildCount()>0) {
                    container.removeView((ImageView) object);
                }
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                ImageView vs=new ImageView(FirstPageActivity.this);
                ViewGroup.LayoutParams lp=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, ViewPager.LayoutParams.MATCH_PARENT);
                vs.setImageResource(res[position]);
                vs.setScaleType(ImageView.ScaleType.CENTER_CROP);
                container.addView(vs,lp);

                return vs;
            }
        });
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                LogUtil.show("hua:"+position+"   "+positionOffset+"   "+positionOffsetPixels);
            }
            @Override
            public void onPageSelected(int position) {
                    if(position==res.length-1){
                        intoTv.setVisibility(View.VISIBLE);
                    }else{
                        if(intoTv.getVisibility()==View.VISIBLE){
                            intoTv.setVisibility(View.INVISIBLE);
                        }
                    }

                    if(pointView!=null && pointView.getChildCount()==res.length){
                        for(int i=0;i<res.length;i++){
                            ImageView child=(ImageView) pointView.getChildAt(i);
                            if(i==position){
                                child.setImageResource(R.mipmap.icon_round_blue);
                            }else{
                                child.setImageResource(R.mipmap.icon_round_grey);
                            }
                        }
                    }
            }
            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    public static String GUIDE_TYPE = "guide_type";
    public static int GUIDE_FIRST = 1;//第一次使用
    public static int GUIDE_NO_FIRST = 0;//非第一次试用（我的）
    public static void startFirstPageActivity(Context c,int type){
        Intent it = new Intent(c, FirstPageActivity.class);
        it.putExtra(GUIDE_TYPE, type);
        c.startActivity(it);
    }

    @Override
    public void onBackPressed() {
        if(type==GUIDE_NO_FIRST){
            finish();
        }else{
            PrefManager.setPrefInt(GlobalApp.PRE_FIRST,0);
            MainActivity.startMainActivity(this);
            finish();
        }
    }
}
