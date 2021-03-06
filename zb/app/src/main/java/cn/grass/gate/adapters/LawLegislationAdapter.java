package cn.grass.gate.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import cn.grass.gate.R;
import cn.grass.gate.beans.TaxMessageInfo;
import cn.grass.gate.inface.GItemClickListener;
import cn.grass.gate.utils.DateUtil;

/**
 * Created by linxc on 2017/7/14.
 */
public class LawLegislationAdapter extends RecyclerView.Adapter<LawLegislationHolder>{

    Context context;
    ArrayList<TaxMessageInfo> data;
    public LawLegislationAdapter(Context c, ArrayList<TaxMessageInfo> list){
        context =c;
        data = list;
    }

    public GItemClickListener listener;
    public void setItemClickListener(GItemClickListener l){
        this.listener = l;
    }

    @Override
    public LawLegislationHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View vs= LayoutInflater.from(context).inflate(R.layout.item_law_legislation,null,false);
        return new LawLegislationHolder(vs,listener);
    }

    @Override
    public void onBindViewHolder(LawLegislationHolder holder, int position) {
        if (data!=null){
            holder.titleTv.setText(data.get(position).title);
            long dateLg = data.get(position).date;
            if (dateLg>0){
                String date = DateUtil.getStandardDate(data.get(position).date);
                holder.dateTv.setText(date);
            }else {
                holder.dateTv.setText("");
            }
        }
    }

    @Override
    public int getItemCount() {
        return data==null?0:data.size();
    }
}
