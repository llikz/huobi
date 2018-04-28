package cn.grass.gate.inface;

import android.view.View;

/**
 * Created by linxc on 2017/2/20.
 */

public interface GChildClickListener {
    public void onItemClicked(View vs, int fatherPos, int childPos);
    public void onItemClicked(View vs, int Pos);

}
