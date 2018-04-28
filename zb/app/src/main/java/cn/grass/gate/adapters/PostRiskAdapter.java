package cn.grass.gate.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import cn.grass.gate.R;
import cn.grass.gate.beans.BaseInfo;
import cn.grass.gate.inface.GItemClickListener;

/**
 * Created by linxc on 2017/7/24.
 */
public class PostRiskAdapter extends RecyclerView.Adapter<PostRiskHolder> {

    Context context;
    ArrayList<BaseInfo> data;

    public PostRiskAdapter(Context c, ArrayList<BaseInfo> list) {
        context = c;
        data = list;
    }

    public GItemClickListener listener;

    public void setItemClickListener(GItemClickListener l) {
        this.listener = l;
    }

    @Override
    public PostRiskHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View vs = LayoutInflater.from(context).inflate(R.layout.item_post_risk, null, false);
        return new PostRiskHolder(vs, listener);
    }

    @Override
    public void onBindViewHolder(PostRiskHolder holder, int position) {
        if (data != null) {
            holder.titleTv.setText(data.get(position).name);
        }
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }
}
