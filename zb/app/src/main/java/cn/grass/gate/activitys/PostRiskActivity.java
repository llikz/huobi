package cn.grass.gate.activitys;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import cn.grass.gate.R;
import cn.grass.gate.base.BaseActivity;
import cn.grass.gate.fragments.ExerciseNotebookFragment;
import cn.grass.gate.fragments.PostRiskFragment;
import cn.grass.gate.widgets.TopView;

/**
 * Created by linxc on 2017/7/24.
 * 岗位风险
 */
public class PostRiskActivity extends BaseActivity{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_risk);
        initView();
        initVP();
    }

    private void initView() {
        ((TopView)findViewById(R.id.top_bar)).setOnLeftListener(new TopView.OnLeftClickListener() {
            @Override
            public void onLeftClick(View v) {
                onBackPressed();
            }
        });
    }

    private String[] title = null;
    private void initVP() {
        title = new String[2];
        title[0] = "行权手册";
        title[1] = "岗位风险";

        FragmentPagerAdapter adapter = new TabPageIndicatorAdapter(getSupportFragmentManager());
        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(adapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_views);
        tabLayout.setupWithViewPager(pager);
        FragmentManager fm = getSupportFragmentManager();
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
            Fragment fragment = null;
            switch (position) {
                case 0:
                    fragment = new ExerciseNotebookFragment();
                    break;
                case 1:
                    fragment = new PostRiskFragment();
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


    public static void startPostRiskActivity(Context c) {
        Intent it = new Intent(c, PostRiskActivity.class);
        c.startActivity(it);
    }

}
