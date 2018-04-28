package cn.grass.gate.widgets;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.weedys.weedlibrary.utils.DisplayUtil;

import cn.grass.gate.R;
import cn.grass.gate.utils.DensityUtil;

/**
 * Created by weedys on 16/7/25.
 */
public class ItemTextView extends RelativeLayout{
    private boolean leftShow = false;
    private String leftText = "";
    private float leftTextSize = 0;
    private int leftIconId;

    private boolean rightShow = false;
    private String rightText = "";
    private float rightTextSize = 0;
    private float rightdrawablePadding = 0;
    private float leftdrawablePadding = 0;
    private int rightTextColor;
    private int leftTextColor;
    private int rightIconId;
    public ItemTextView(Context context) {
        super(context);
        init(context,null);
    }

    public ItemTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    public ItemTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }
    TextView leftTv,rightTv;
    private void init(Context c,AttributeSet attrs){
        TypedArray a = c.obtainStyledAttributes(attrs, R.styleable.ItemTextView);

        /**
         * Left Right Position
         */
        leftShow = a.getBoolean(R.styleable.ItemTextView_left_tv_show,true);
        rightShow = a.getBoolean(R.styleable.ItemTextView_right_tv_show,false);

        /**
         * Left
         */
        leftIconId = a.getResourceId(R.styleable.ItemTextView_left_tv_drawable, 0);
        leftText = a.getString(R.styleable.ItemTextView_left_tv_text);
//        leftTextSize = a.getDimension(R.styleable.ItemTextView_left_text_tv_size, DisplayUtil.sp2px(getContext(), 14));
        leftTextSize = a.getDimension(R.styleable.ItemTextView_left_text_tv_size,getResources().getDimension(R.dimen.title));
        leftdrawablePadding = a.getDimension(R.styleable.ItemTextView_left_tv_drawable_padding, DisplayUtil.sp2px(getContext(), 0));
        leftTextColor = a.getColor(R.styleable.ItemTextView_left_text_tv_color,getResources().getColor(R.color.color_666));

        /**
         * Right
         */
        rightIconId = a.getResourceId(R.styleable.ItemTextView_right_tv_drawable, 0);
        rightText = a.getString(R.styleable.ItemTextView_right_tv_text);
//        rightTextSize = a.getDimension(R.styleable.ItemTextView_right_text_tv_size, DisplayUtil.sp2px(getContext(), 14));
        rightTextSize = a.getDimension(R.styleable.ItemTextView_right_text_tv_size, getResources().getDimension(R.dimen.content));
        rightdrawablePadding = a.getDimension(R.styleable.ItemTextView_right_tv_drawable_padding, DisplayUtil.sp2px(getContext(), 0));
        rightTextColor = a.getColor(R.styleable.ItemTextView_right_text_tv_color,getResources().getColor(R.color.color_666));

//        boolean showBaseLine = a.getBoolean(R.styleable.TopView_show_base_line, true);

        a.recycle();


        if(leftShow==true) {
            LayoutParams leftLp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            leftLp.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
            leftLp.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
            leftTv = new TextView(c);
            leftTv.setGravity(Gravity.CENTER_VERTICAL);
            leftTv.setTextColor(leftTextColor);
            leftTv.setTextSize(TypedValue.COMPLEX_UNIT_PX,leftTextSize);
            leftTv.setCompoundDrawablePadding((int)leftdrawablePadding);
            leftTv.setCompoundDrawablesWithIntrinsicBounds(leftIconId,0,0,0);
            leftLp.leftMargin = DensityUtil.dip2px(15);
            addView(leftTv, leftLp);
            leftTv.setText(leftText);
        }

        if(rightShow==true) {
            LayoutParams rightLp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            rightLp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
            rightLp.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
            rightTv = new TextView(c);
            rightTv.setGravity(Gravity.CENTER_VERTICAL);
            rightTv.setTextColor(rightTextColor);
            rightTv.setTextSize(TypedValue.COMPLEX_UNIT_PX,rightTextSize);
            rightTv.setCompoundDrawablePadding((int)rightdrawablePadding);
//            rightLp.rightMargin = DensityUtil.dip2px(1);
            rightTv.setCompoundDrawablesWithIntrinsicBounds(0,0,rightIconId,0);
            addView(rightTv, rightLp);
            rightTv.setText(rightText);

        }
    }
    public TextView getRightTv(){
        return rightTv;
    }
    public TextView getLeftTv(){
        return leftTv;
    }



}
