package cn.grass.gate.widgets;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.weedys.weedlibrary.utils.DisplayUtil;

import cn.grass.gate.R;


/**
 * App顶端栏位
 */
public class TopView extends RelativeLayout {
    private Show leftShow = Show.getDefault();
    private String leftText = "";
    private float leftTextSize = 0;
    private int leftIconId;

    private Show rightShow = Show.getDefault();
    private String rightText = "";
    private float rightTextSize = 0;
    private int rightIconId;
    private int rightTextColor;

    private String titleText = "";
    private float titleTextSize = 0;
    private int titleTextColor;

    private View leftView;
    private View rightView;

    private TextView rightTv;
    private TextView msgTv;

    private boolean showMsg = false;

    public TopView(Context context) {
        super(context);
    }

    public TopView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(context, attrs);
    }

    public TopView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.TopView);

        /**
         * Title
         */
        titleText = a.getString(R.styleable.TopView_title_text);
//        titleTextSize = a.getDimension(R.styleable.TopView_title_text_size, DisplayUtil.sp2px(getContext(), 16));
        titleTextSize = a.getDimension(R.styleable.TopView_title_text_size, getResources().getDimension(R.dimen.title_bar));


        titleTextColor = a.getColor(R.styleable.TopView_title_text_color, getResources().getColor(R.color.color_white));

        /**
         * Left Right Position
         */
        leftShow = Show.mapIntToValue(a.getInteger(R.styleable.TopView_left_show, Show.getDefault().getIntValue()));
        rightShow = Show.mapIntToValue(a.getInteger(R.styleable.TopView_right_show, Show.getDefault().getIntValue()));

        /**
         * Left
         */
        leftIconId = a.getResourceId(R.styleable.TopView_left_drawable, 0);
        leftText = a.getString(R.styleable.TopView_left_text);

//        leftTextSize = a.getDimension(R.styleable.TopView_left_text_size, DisplayUtil.sp2px(getContext(), 15));
        leftTextSize = a.getDimension(R.styleable.TopView_left_text_size, getResources().getDimension(R.dimen.title));

        /**
         * Right
         */
        rightIconId = a.getResourceId(R.styleable.TopView_right_drawable, 0);
        rightText = a.getString(R.styleable.TopView_right_text);
//        if(!this.isInEditMode())
//        rightTextSize = a.getDimension(R.styleable.TopView_right_text_size, DisplayUtil.sp2px(getContext(), 15));
        rightTextSize = a.getDimension(R.styleable.TopView_right_text_size, getResources().getDimension(R.dimen.title));
        rightTextColor = a.getColor(R.styleable.TopView_right_text_color, getResources().getColor(R.color.color_white));

        /**
         * 消息
         */
        showMsg = a.getBoolean(R.styleable.TopView_right_msg_show, false);
//        boolean showBaseLine = a.getBoolean(R.styleable.TopView_show_base_line, true);

        a.recycle();

        setTitleShow();
        setLeftShow();
        setRightShow();
        setMessageView();
//        if (showBaseLine)
        setBaseLine();
    }

    private void setBaseLine() {
        View line = new View(getContext());
        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, DisplayUtil.dip2px(getContext(), 1));
        lp.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM, RelativeLayout.TRUE);
        line.setLayoutParams(lp);
//        line.setBackgroundColor(getResources().getColor(R.color.color_dbdbdb));
        addView(line);
    }

    /**
     * 设置标题栏
     */
    TextView titleTv = null;

    private void setTitleShow() {
        LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        lp.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);

        titleTv = new TextView(getContext());
        addView(titleTv, lp);

        titleTv.setText(titleText);
        titleTv.setSingleLine();
        titleTv.setTextColor(titleTextColor);
        titleTv.setTextSize(TypedValue.COMPLEX_UNIT_PX, titleTextSize);
//        titleTv.setTextSize(TypedValue.COMPLEX_UNIT_SP, titleTextSize);

        titleTv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (titleListener != null) {
                    titleListener.onTitleClick(v);
                }
            }
        });
    }

    /**
     * 设置左边控件
     */
    private void setLeftShow() {
        switch (leftShow) {
            case NONE:

                break;
            case IMG: {
//			FloatingActionButton btn=new FloatingActionButton(getContext());
                ImageView btn = new ImageView(getContext());
                addView(btn, getLeftLayoutParams());
                btn.setPadding(DisplayUtil.dip2px(getContext(), 10), 5, 10, 5);
//			btn.setBackgroundResource(leftIconId);
                btn.setImageResource(leftIconId);
                btn.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (leftListener != null) {
                            leftListener.onLeftClick(v);
                        }
                    }
                });


                leftView = btn;
            }
            break;
            case TEXT: {
                TextView tv = new TextView(getContext());
                addView(tv, getLeftLayoutParams());

                tv.setPadding(DisplayUtil.dip2px(getContext(), 10), 0, 0, 0);
                tv.setText(leftText);
                tv.setSingleLine();
                tv.setClickable(true);
                tv.setTextColor(getLeftRightTextCsl());
                tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, leftTextSize);


                tv.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (leftListener != null) {
                            leftListener.onLeftClick(v);
                        }
                    }
                });

                leftView = tv;
            }
            break;
            case BOTH: {
                TextView tv = new TextView(getContext());
                addView(tv, getLeftLayoutParams());
                tv.setPadding(0, 0, 0, 0);
//                tv.setPadding(DisplayUtil.dip2px(getContext(), 10), 0, 0, 0);
                tv.setText(leftText);
                tv.setSingleLine();
                tv.setClickable(true);
                tv.setTextColor(getLeftRightTextCsl());
                tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, leftTextSize);
                if (leftIconId != 0) {
                    Drawable drawable = getResources().getDrawable(leftIconId);
                    // 这一步必须要做,否则不会显示.
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    tv.setCompoundDrawables(drawable, null, null, null);
                }
                tv.setCompoundDrawablePadding(5);
                tv.setGravity(Gravity.CENTER);
                tv.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (leftListener != null) {
                            leftListener.onLeftClick(v);
                        }
                    }
                });

                leftView = tv;
            }
            break;
            default:
                break;
        }
    }

    /**
     * 设置右边控件
     */
    private void setRightShow() {
        switch (rightShow) {
            case NONE:

                break;
            case IMG: {
                ImageView btn = new ImageView(getContext());
                addView(btn, getRightLayoutParams());

                btn.setPadding(0, 0, DisplayUtil.dip2px(getContext(), 10), 0);
                btn.setBackgroundResource(rightIconId);

                btn.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (rightListener != null) {
                            rightListener.onRightClick(v);
                        }
                    }
                });

                rightView = btn;
            }
            break;
            case TEXT: {
                TextView tv = new TextView(getContext());
                addView(tv, getRightLayoutParams());

                tv.setPadding(0, 0, DisplayUtil.dip2px(getContext(), 10), 0);
                tv.setText(rightText);
                tv.setSingleLine();
                tv.setClickable(true);
                tv.setTextColor(rightTextColor);
//                tv.setTextColor(getLeftRightTextCsl());
//                tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, rightTextSize);

                tv.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (rightListener != null) {
                            rightListener.onRightClick(v);
                        }
                    }
                });

                rightView = tv;

                rightTv = tv;
            }
            break;
            case BOTH: {
                TextView tv = new TextView(getContext());
                addView(tv, getLeftLayoutParams());

                tv.setPadding(0, 0, DisplayUtil.dip2px(getContext(), 10), 0);
                tv.setText(rightText);
                tv.setSingleLine();
                tv.setClickable(true);
                tv.setTextColor(rightTextColor);
//                tv.setTextSize(TypedValue.COMPLEX_UNIT_PX, rightTextSize);
                if (rightIconId != 0) {
                    Drawable drawable = getResources().getDrawable(rightIconId);
                    // 这一步必须要做,否则不会显示.
                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                    tv.setCompoundDrawables( null, null,drawable, null);
                }
                tv.setCompoundDrawablePadding(15);
                tv.setGravity(Gravity.CENTER);
                tv.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (rightListener != null) {
                            rightListener.onRightClick(v);
                        }
                    }
                });

                rightView = tv;

                rightTv = tv;
            }
            break;
            default:
                break;
        }


    }

    private void setMessageView() {

        if (showMsg) {
            msgTv = new TextView(getContext());
            msgTv.setBackgroundResource(R.drawable.shape_round_small_red);
            msgTv.setGravity(Gravity.CENTER);
            msgTv.setTextColor(getResources().getColor(R.color.color_white));
            msgTv.setText("10");
            msgTv.setTextSize(TypedValue.COMPLEX_UNIT_PX, DisplayUtil.sp2px(getContext(), 8));
            int w = DisplayUtil.dip2px(getContext(), 18);
            LayoutParams msgLp = new LayoutParams(w, w);
            msgLp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
//        msgLp.addRule(RelativeLayout.CENTER_VERTICAL,RelativeLayout.TRUE);
            msgLp.rightMargin = DisplayUtil.dip2px(getContext(), 12);
            msgLp.topMargin = DisplayUtil.dip2px(getContext(), 12);
            addView(msgTv, msgLp);
            msgTv.setPadding(2, 2, 2, 2);
        } else {
            if (msgTv != null)
                msgTv.setVisibility(INVISIBLE);
        }
    }

    /**
     * 获取左边控件的LayoutParams
     *
     * @return
     */
    private LayoutParams getLeftLayoutParams() {
        LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        lp.addRule(RelativeLayout.ALIGN_PARENT_LEFT, RelativeLayout.TRUE);
        lp.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
        lp.setMargins(DisplayUtil.dip2px(getContext(), 8), 0, 0, 0);
        return lp;
    }

    /**
     * 获取右边控件的LayoutParams
     *
     * @return
     */
    private LayoutParams getRightLayoutParams() {
        LayoutParams lp = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        lp.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, RelativeLayout.TRUE);
        lp.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
        lp.setMargins(0, 0, DisplayUtil.dip2px(getContext(), 8), 0);
        return lp;
    }

    /**
     * 获取左右TextView控件的文字颜色
     *
     * @return
     */
    private ColorStateList getLeftRightTextCsl() {
        return (ColorStateList) getContext().getResources().getColorStateList(R.color.color_white);
    }

    /**
     * 枚举，用来控制左右显示什么
     */
    public enum Show {
        NONE(0x00), IMG(0x01), TEXT(0x02), BOTH(0x03);

        static Show mapIntToValue(final int modeInt) {
            for (Show value : Show.values()) {
                if (modeInt == value.getIntValue()) {
                    return value;
                }
            }
            // If not, return default
            return getDefault();
        }

        private int mIntValue;

        Show(int modeInt) {
            mIntValue = modeInt;
        }

        static Show getDefault() {
            return NONE;
        }

        int getIntValue() {
            return mIntValue;
        }
    }

    public interface OnLeftClickListener {
        void onLeftClick(View v);
    }

    public interface OnRightClickListener {
        void onRightClick(View v);
    }

    public interface OnTitleClickListener {
        void onTitleClick(View v);
    }

    private OnLeftClickListener leftListener;
    private OnRightClickListener rightListener;
    private OnTitleClickListener titleListener;

    public OnLeftClickListener getLeftListener() {
        return leftListener;
    }

    public void setOnLeftListener(OnLeftClickListener l) {
        this.leftListener = l;
    }

    public OnRightClickListener getRightListener() {
        return rightListener;
    }

    public void setOnRightListener(OnRightClickListener l) {
        this.rightListener = l;
    }

    public OnTitleClickListener getTitleListener() {
        return titleListener;
    }

    public void setOnTitleListener(OnTitleClickListener l) {
        this.titleListener = l;
    }

    public void setTitleText(String title) {
        if (titleTv != null) {
            titleTv.setText(title);
        }
    }

    public void setMarqueeTitleText(String title) {
        if (titleTv != null) {
            titleTv.setWidth(getMeasuredWidth() / 3 * 2);
            titleTv.setText(title);
            titleTv.setEllipsize(TextUtils.TruncateAt.MARQUEE);
            titleTv.setMarqueeRepeatLimit(Integer.MAX_VALUE);
            titleTv.setFocusable(true);
            titleTv.setFocusableInTouchMode(true);
            titleTv.requestFocus();
        }
    }

    public View getRightView() {
        return rightView;
    }

    public View getLeftView() {
        return leftView;
    }

    public TextView getRightTextView() {
        return rightTv;
    }

    public TextView getMessageTextView() {
        return msgTv;
    }

    public TextView getTitleTextView() {
        return titleTv;
    }
}
