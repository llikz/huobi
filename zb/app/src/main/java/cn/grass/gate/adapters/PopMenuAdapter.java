package cn.grass.gate.adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import cn.grass.gate.R;


/**
 * Created by linxc on 2017/3/21.
 */

public class PopMenuAdapter extends BaseAdapter {
    Context context;
    String[] data =null;
    Drawable[] resources =null;
    public PopMenuAdapter(Context context, String[] list, Drawable[] r) {
        this.context = context;
        data = list;
        resources = r;
    }

    @Override
    public int getCount() {
        return data == null ? 0 :data.length;
    }

    @Override
    public Object getItem(int position) {
        return data[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    ViewHolder holder = null;
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_pop_menu, null);
            holder.textView = (TextView) convertView.findViewById(R.id.add_tv);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.textView.setText(data[position]);
        Drawable drawable = resources[position];
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        holder.textView.setCompoundDrawables(drawable, null, null, null);
        return convertView;
    }

    class ViewHolder {
        TextView textView;
    }
}