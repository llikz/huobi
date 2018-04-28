package cn.grass.gate.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import cn.grass.gate.R;
import cn.grass.gate.inface.GItemClickListener;

/**
 * Created by linxc on 2017/7/14.
 */
public class AreaHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    TextView areaNameTv;
    TextView userNameTv;
    TextView userNoTv;
    TextView userAddrTv;
    TextView workTypeTv;
    TextView timeTv;

    public AreaHolder(View itemView, GItemClickListener l) {
        super(itemView);
        listener = l;
        itemView.setOnClickListener(this);

        areaNameTv = (TextView) itemView.findViewById(R.id.area_name_tv);
        userNameTv = (TextView) itemView.findViewById(R.id.user_name_tv);
        userNoTv = (TextView) itemView.findViewById(R.id.user_no_tv);
        userAddrTv = (TextView) itemView.findViewById(R.id.user_addr_tv);
        workTypeTv = (TextView) itemView.findViewById(R.id.work_type_tv);
        timeTv = (TextView) itemView.findViewById(R.id.time_tv);
    }

    private GItemClickListener listener;
    @Override
    public void onClick(View v) {
        if (listener != null) {
            listener.onItemClicked(v, getPosition());
        }
    }
}
