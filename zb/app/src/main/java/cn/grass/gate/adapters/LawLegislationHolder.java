package cn.grass.gate.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import cn.grass.gate.R;
import cn.grass.gate.inface.GItemClickListener;

/**
 * Created by linxc on 2017/7/14.
 */
public class LawLegislationHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
    TextView titleTv,dateTv;

    public LawLegislationHolder(View itemView, GItemClickListener l) {
        super(itemView);
        listener = l;
        itemView.setOnClickListener(this);

        titleTv = (TextView) itemView.findViewById(R.id.tv_title);
        dateTv = (TextView) itemView.findViewById(R.id.tv_date);
    }

    private GItemClickListener listener;
    @Override
    public void onClick(View v) {
        if (listener != null) {
            listener.onItemClicked(v, getPosition());
        }
    }
}
