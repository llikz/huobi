package cn.grass.gate.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import cn.grass.gate.R;
import cn.grass.gate.inface.GItemClickListener;

/**
 * Created by linxc on 2017/7/14.
 */
public class TaskHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    TextView nameTv;

    public TaskHolder(View itemView, GItemClickListener l) {
        super(itemView);
        listener = l;
        itemView.setOnClickListener(this);

//        titleTv = (TextView) itemView.findViewById(R.id.tv_title);
//        publishTv = (TextView) itemView.findViewById(R.id.tv_publish);
//        dateTv = (TextView) itemView.findViewById(R.id.tv_date);
        nameTv = (TextView) itemView.findViewById(R.id.area_name_content_tv);
    }

    private GItemClickListener listener;
    @Override
    public void onClick(View v) {
        if (listener != null) {
            listener.onItemClicked(v, getPosition());
        }
    }
}
