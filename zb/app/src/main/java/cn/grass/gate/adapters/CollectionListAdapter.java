package cn.grass.gate.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import cn.grass.gate.R;
import cn.grass.gate.beans.CollectionListInfo;
import cn.grass.gate.inface.GItemClickListener;

/**
 * Created by linxc on 2017/7/24.
 */
public class CollectionListAdapter extends RecyclerView.Adapter<CollectionListHolder> {

    Context context;
    ArrayList<CollectionListInfo> data;

    public CollectionListAdapter(Context c, ArrayList<CollectionListInfo> list) {
        context = c;
        data = list;
    }

    public GItemClickListener listener;

    public void setItemClickListener(GItemClickListener l) {
        this.listener = l;
    }


    @Override
    public CollectionListHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View vs = LayoutInflater.from(context).inflate(R.layout.item_collection_list, null, false);
        return new CollectionListHolder(vs, listener);
    }

    @Override
    public void onBindViewHolder(CollectionListHolder holder, int position) {
        if (data != null) {
            holder.titleTv.setText(data.get(position).title);
            holder.numTv.setText(data.get(position).num+"收藏内容");
        }
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }
}
