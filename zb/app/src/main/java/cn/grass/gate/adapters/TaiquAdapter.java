package cn.grass.gate.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import cn.grass.gate.R;
import cn.grass.gate.beans.Taiqu;
import cn.grass.gate.inface.GItemClickListener;

/**
 * Created by linxc on 2017/7/14.
 */
public class TaiquAdapter extends RecyclerView.Adapter<AreaHolder>{
    Context context;
    ArrayList<Taiqu.DataBean.ListBean> data;
    public TaiquAdapter(Context c, ArrayList<Taiqu.DataBean.ListBean> list){
        context =c;
        data = list;
    }

    public GItemClickListener listener;
    public void setItemClickListener(GItemClickListener l){
        this.listener = l;
    }

    @Override
    public AreaHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View vs= LayoutInflater.from(context).inflate(R.layout.item_area,null,false);
        return new AreaHolder(vs,listener);
    }

    @Override
    public void onBindViewHolder(AreaHolder holder, int position) {
        if (data!=null){
            holder.areaNameTv.setText(data.get(position).getTxtTaiQuMingCheng());
            holder.userNameTv.setText(data.get(position).getYongHuMingCheng());
            holder.userNoTv.setText(data.get(position).getYongHuBianHao());
            holder.userAddrTv.setText(data.get(position).getYongDianDiZhi());
            holder.timeTv.setText(data.get(position).getCreatedAt());
            int type = 0;//TODO 这个要澄清
            if(0 == type){
                holder.workTypeTv.setText(context.getString(R.string.work_type_all));
            }else if(1 == type){
                holder.workTypeTv.setText(context.getString(R.string.work_type_meter));
            }else{
                holder.workTypeTv.setText(context.getString(R.string.work_type_meter_box));
            }
        }
    }

    @Override
    public int getItemCount() {
        return data==null?0:data.size();
    }
}
