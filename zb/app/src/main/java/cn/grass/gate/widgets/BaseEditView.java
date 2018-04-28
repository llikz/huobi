package cn.grass.gate.widgets;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.weed.views.ClearEditView;

/**
 * Created by weedys on 16/7/25.
 */
public class BaseEditView extends RelativeLayout{
    public BaseEditView(Context context) {
        super(context);
    }

    public BaseEditView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initEditView();
    }

    public BaseEditView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initEditView();
    }
    private void initEditView()
    {
        ClearEditView clearView =new ClearEditView(getContext());
        addView(clearView);

    }
}
