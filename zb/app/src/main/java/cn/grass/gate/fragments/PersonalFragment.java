package cn.grass.gate.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import cn.grass.gate.R;
import cn.grass.gate.activitys.FeedBackActivity;
import cn.grass.gate.activitys.UserSettingActivity;
import cn.grass.gate.base.BaseFragment;

/**
 * Created by PC-9 on 2017/7/13.
 * 咨询
 */
public class PersonalFragment extends BaseFragment implements View.OnClickListener {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View vs = inflater.inflate(R.layout.fragment_personal, container, false);
        initView(vs);
        return vs;
    }

    private void initView(View vs) {
        vs.findViewById(R.id.ll_user_detail).setOnClickListener(this);
        vs.findViewById(R.id.tv_massess).setOnClickListener(this);
//        vs.findViewById(R.id.tv_message).setOnClickListener(this);
        vs.findViewById(R.id.tv_feedback).setOnClickListener(this);
        vs.findViewById(R.id.tv_setting).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_user_detail:
                UserSettingActivity.startUserSettingActivity(getActivity());
                break;
            case R.id.tv_massess://草稿(暂存)

                break;
            case R.id.tv_about://关于

                break;
            case R.id.tv_feedback://用户反馈
                FeedBackActivity.startFeedBackActivity(getActivity(),"",11);
                break;
            case R.id.tv_setting://设置

                break;
        }
    }
}
