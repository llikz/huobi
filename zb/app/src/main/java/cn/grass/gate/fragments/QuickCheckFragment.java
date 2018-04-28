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

import cn.grass.gate.IntentParameter;
import cn.grass.gate.R;
import cn.grass.gate.base.BaseFragment;

/**
 * Created by weedys on 17/7/13.
 * 快查
 */
public class QuickCheckFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View vs = inflater.inflate(R.layout.fragment_quick_check, container, false);
        initVP(vs);
        return vs;
    }

    private String[] title = null;
    private void initVP(View v) {
        title = new String[2];
        title[0] = "法律法规";
        title[1] = "学术论文";

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
            Fragment fragment= new LawLegislationFragment();
            Bundle bundle = new Bundle();
            switch (position) {
                case 0:
//                    fragment = new LawLegislationFragment();
                    bundle.putString(IntentParameter.COLUMN_ID, IntentParameter.ID_LAW_LEGISLATION);
                    fragment.setArguments(bundle);
                    break;
                case 1:
//                    fragment = new AcademicPaperFragment();
                    bundle.putString(IntentParameter.COLUMN_ID, IntentParameter.ID_ACADEMIC_PAPER);
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


    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser == true) {
//            refreshContact();
        }

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

}
