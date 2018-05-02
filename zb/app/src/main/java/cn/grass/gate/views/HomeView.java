package cn.grass.gate.views;

import cn.grass.gate.beans.HangQing;
import cn.grass.gate.beans.HomeBean;
import cn.grass.gate.beans.Price;
import cn.grass.gate.model.HuoBiData;

/**
 * Created by weedys on 16/10/6.
 */
public interface HomeView extends BaseView {
    public void homeList(HomeBean homeBean);
    public void hangQing(HangQing hangQing);
    public void price(Price price);
    public void huoBiData(HuoBiData data);
}
