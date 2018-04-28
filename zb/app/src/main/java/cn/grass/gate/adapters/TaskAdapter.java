package cn.grass.gate.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import cn.grass.gate.R;
import cn.grass.gate.beans.WaitTask;
import cn.grass.gate.inface.GItemClickListener;

/**
 * Created by linxc on 2017/7/14.
 */
public class TaskAdapter extends RecyclerView.Adapter<TaskHolder>{
    Context context;
    ArrayList<WaitTask.DataBean.ListBean> data;
    public TaskAdapter(Context c, ArrayList<WaitTask.DataBean.ListBean> list){
        context =c;
        data = list;
    }

    public GItemClickListener listener;
    public void setItemClickListener(GItemClickListener l){
        this.listener = l;
    }

    @Override
    public TaskHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View vs= LayoutInflater.from(context).inflate(R.layout.item_task,null,false);
        return new TaskHolder(vs,listener);
    }

    @Override
    public void onBindViewHolder(TaskHolder holder, int position) {
        if (data!=null){
            holder.nameTv.setText(data.get(position).getTxtTaiQuMingCheng());
        }
    }

    @Override
    public int getItemCount() {
        return data==null?0:data.size();
    }
}
