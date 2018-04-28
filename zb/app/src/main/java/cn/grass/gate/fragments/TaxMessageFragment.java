package cn.grass.gate.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import java.util.ArrayList;

import cn.grass.gate.IntentParameter;
import cn.grass.gate.R;
import cn.grass.gate.base.BaseFragment;
import cn.grass.gate.beans.ADInfo;
import cn.grass.gate.widgets.ImageCycleView;


/**
 * Created by weedys on 17/07/13.
 * 税讯
 */
public class TaxMessageFragment extends BaseFragment implements View.OnClickListener {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View contentView = inflater.inflate(R.layout.fragment_tax_massage, null);
        initView(contentView);
        initVP(contentView);
        return contentView;
    }

    private ArrayList<ADInfo> infos = new ArrayList<>();
    private String[] imageUrls = {"http://img.taodiantong.cn/v55183/infoimg/2013-07/130720115322ky.jpg",
            "http://pic30.nipic.com/20130626/8174275_085522448172_2.jpg",
            "http://pic18.nipic.com/20111215/577405_080531548148_2.jpg",
            "http://pic15.nipic.com/20110722/2912365_092519919000_2.jpg",
            "http://pic.58pic.com/58pic/12/64/27/55U58PICrdX.jpg"};

    private ImageCycleView mAdView;

    private void initView(View v) {
        for (int i = 0; i < imageUrls.length; i++) {
            ADInfo info = new ADInfo();
            info.setUrl(imageUrls[i]);
            info.setContent("top-->" + i);
            infos.add(info);
        }

        mAdView = (ImageCycleView) v.findViewById(R.id.ad_view);
        mAdView.setImageResources(infos, mAdCycleViewListener);
    }

    private String[] title = null;

    private void initVP(View v) {
        title = new String[3];
        title[0] = "税务动态";
        title[1] = "工作指导";
        title[2] = "最新案例";

        FragmentPagerAdapter adapter = new TabPageIndicatorAdapter(getChildFragmentManager());
        ViewPager pager = (ViewPager) v.findViewById(R.id.pager);
        pager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) v.findViewById(R.id.tab_views);
        tabLayout.setupWithViewPager(pager);
        FragmentManager fm = getChildFragmentManager();
        adapter = new TabPageIndicatorAdapter(fm);
        pager.setAdapter(adapter);
        tabLayout.setupWithViewPager(pager);
    }

    class TabPageIndicatorAdapter extends FragmentPagerAdapter {
        public TabPageIndicatorAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment= new TaxDynamicFragment();
            Bundle bundle = new Bundle();
            switch (position) {
                case 0:
                    bundle.putString(IntentParameter.COLUMN_ID, IntentParameter.ID_TAX_DYNAMIC);
                    fragment.setArguments(bundle);
                    break;
                case 1:
                    bundle.putString(IntentParameter.COLUMN_ID, IntentParameter.ID_WORK_GUIDE);
                    fragment.setArguments(bundle);
                    break;
                case 2:
                    bundle.putString(IntentParameter.COLUMN_ID, IntentParameter.ID_NEW_CASE);
                    fragment.setArguments(bundle);
                    break;
            }
            return fragment;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return title[position % title.length];
        }

        @Override
        public int getCount() {
            return title.length;
        }

    }


    private ImageCycleView.ImageCycleViewListener mAdCycleViewListener = new ImageCycleView.ImageCycleViewListener() {

        @Override
        public void onImageClick(ADInfo info, int position, View imageView) {
            Toast.makeText(getActivity(), "content->" + info.getContent(), Toast.LENGTH_SHORT).show();
        }

        @Override
        public void displayImage(String imageURL, ImageView imageView) {
            Glide.with(getActivity()).load(imageURL).asBitmap().error(R.mipmap.icon_default_image).centerCrop().into(imageView);
        }
    };

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
//            notifyMessage();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mAdView.startImageCycle();
    }

    @Override
    public void onPause() {
        super.onPause();
        mAdView.pushImageCycle();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mAdView.pushImageCycle();
    }
}
