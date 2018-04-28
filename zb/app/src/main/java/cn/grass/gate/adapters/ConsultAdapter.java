package cn.grass.gate.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import cn.grass.gate.R;
import cn.grass.gate.beans.ConsultInfo;
import cn.grass.gate.inface.GItemClickListener;

/**
 * Created by linxc on 2017/7/24.
 */
public class ConsultAdapter extends RecyclerView.Adapter<ConsultHolder> {

    Context context;
    ArrayList<ConsultInfo> data;

    public ConsultAdapter(Context c, ArrayList<ConsultInfo> list) {
        context = c;
        data = list;
    }

    public GItemClickListener listener;

    public void setItemClickListener(GItemClickListener l) {
        this.listener = l;
    }


    @Override
    public ConsultHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View vs = LayoutInflater.from(context).inflate(R.layout.item_consult, null, false);
        return new ConsultHolder(vs, listener);
    }

    @Override
    public void onBindViewHolder(ConsultHolder holder, int position) {
        if (data != null) {
            holder.titleTv.setText(data.get(position).title);
        }
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }
}
