/*
 * Copyright (C) 2011 The Android Open Source Project
 * Copyright (C) 2011 Jake Wharton
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.weed.views;

import android.content.Context;
import android.content.res.ColorStateList;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.weedys.weedlibrary.utils.DensityUtil;
import com.weedys.weedlibrary.utils.DisplayUtil;
import com.weedys.weedlibrary.utils.LogUtil;

import java.util.ArrayList;

import cn.grass.gate.R;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

/**
 * This widget implements the dynamic action bar tab behavior that can change
 * across different configurations or circumstances.
 */
public class TabPageIndicator extends HorizontalScrollView{
    /** Title text used when no title is provided by the adapter. */
    private static final CharSequence EMPTY_TITLE = "";


    private Runnable mTabSelector;
    private final OnClickListener mTabClickListener = new OnClickListener() {
        public void onClick(View view) {
            TabView tabView = (TabView)view;
//            if(selectedView!=null){
//                selectedView.setSelected(false);
//            }
//            selectedView=tabView;
            final int newSelected = tabView.getIndex();
        //    LogUtil.showLog("index:" + newSelected);
         //   ToastUtil.show(getContext(), "" + newSelected);
        //    tabView.setSelected(true);
            setCurrentItem(newSelected);
            if(listener!=null){
                listener.onTabItemClick(tabView,newSelected);
            }
//            if (oldSelected == newSelected && mTabReselectedListener != null) {
//                mTabReselectedListener.onTabReselected(newSelected);
//            }
        }
    };

    private final LinearLayout mTabLayout;
    private ArrayList<String> titles;

    private int mMaxTabWidth;
    private int mSelectedTabIndex;

    public TabPageIndicator(Context context) {
        this(context, null);
    }

    public TabPageIndicator(Context context, AttributeSet attrs) {
        super(context, attrs);
        setHorizontalScrollBarEnabled(false);
        mTabLayout = new LinearLayout(context);
        addView(mTabLayout, new ViewGroup.LayoutParams(WRAP_CONTENT, MATCH_PARENT));
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        final int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        final boolean lockedExpanded = widthMode == MeasureSpec.EXACTLY;
        setFillViewport(lockedExpanded);

        final int childCount = mTabLayout.getChildCount();
        if (childCount > 1 && (widthMode == MeasureSpec.EXACTLY || widthMode == MeasureSpec.AT_MOST)) {
            if (childCount > 2) {
                mMaxTabWidth = (int)(MeasureSpec.getSize(widthMeasureSpec) * 0.4f);
            } else {
                mMaxTabWidth = MeasureSpec.getSize(widthMeasureSpec) / 2;
            }
        } else {
            mMaxTabWidth = -1;
        }

        final int oldWidth = getMeasuredWidth();
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        final int newWidth = getMeasuredWidth();

        if (lockedExpanded && oldWidth != newWidth) {
            // Recenter the tab display if we're at a new (scrollable) size.
            setCurrentItem(mSelectedTabIndex);
        }
    }
    public void setCurrentItem(int item) {
        mSelectedTabIndex = item;
        final int tabCount = mTabLayout.getChildCount();
        for (int i = 0; i < tabCount; i++) {
            final View child = mTabLayout.getChildAt(i);
            final boolean isSelected = (i == item);
            child.setSelected(isSelected);
            if (isSelected) {
                animateToTab(item);
                if(child instanceof TabView) {
                    ((TabView) child).setSelect(true);
                }
            }else{
                if(child instanceof TabView) {
                    ((TabView) child).setSelect(false);
                }
            }
        }
    }
    public void animateToTab(final int position) {
        final View tabView = mTabLayout.getChildAt(position);
        if (mTabSelector != null) {
            removeCallbacks(mTabSelector);
        }
        mTabSelector = new Runnable() {
            public void run() {
                final int scrollPos = tabView.getLeft() - (getWidth() - tabView.getWidth()) / 2;
             //   smoothScrollTo(scrollPos, 0);
                LogUtil.show("移动..." + scrollPos);
                smoothScrollTo(scrollPos,0);
                mTabSelector = null;

            }
        };
        this.post(mTabSelector);
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (mTabSelector != null) {
            // Re-post the selector we saved
            post(mTabSelector);
        }
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (mTabSelector != null) {
            removeCallbacks(mTabSelector);
        }
    }

    private void addTab(int index, CharSequence text) {
        final TabView tabView = new TabView(getContext());
        tabView.mIndex = index;
        tabView.setFocusable(true);
        tabView.setOnClickListener(mTabClickListener);
        tabView.setText(text);
        mTabLayout.addView(tabView, new LinearLayout.LayoutParams(0, MATCH_PARENT, 1));
    }



    public void notifyDataSetChanged() {
        if(titles==null){
            return;
        }
        mTabLayout.removeAllViews();
        final int count = titles.size();
        for (int i = 0; i < count; i++) {
            CharSequence title = titles.get(i);
            if (title == null) {
                title = EMPTY_TITLE;
            }
            addTab(i, title);
        }
        if (mSelectedTabIndex > count) {
            mSelectedTabIndex = count - 1;
        }
       setCurrentItem(mSelectedTabIndex);
        requestLayout();
    }

    public void setTitles(ArrayList<String> mtitles) {
        if(mtitles==null){
            return;
        }
        this.titles =mtitles;
        notifyDataSetChanged();
    }

    TabItemClickListener listener;
    public void setOnItemClickListener(TabItemClickListener l){
        listener = l;
    }

//
//    @Override
//    public void setOnPageChangeListener(OnPageChangeListener listener) {
//        mListener = listener;
//    }

    private class TabView extends TextView {
        private int mIndex;

        public TabView(Context context) {
            super(context, null, R.style.textviewstyle);
          //  context.setTheme(R.style.textviewstyle);

            ColorStateList colorStateList=getResources().getColorStateList(R.color.selector_toggle_text);
            setTextColor(colorStateList);
//            setBackgroundResource(R.drawable.selector_tab_text_bg);
            setGravity(Gravity.CENTER_HORIZONTAL);
           // setPadding(15, 10, 15, 10);
            float dim=getContext().getResources().getDimension(R.dimen.input_size_16);
            setTextSize(TypedValue.COMPLEX_UNIT_PX,DensityUtil.dip2px(getContext(),16));
            setWidth(DisplayUtil.dip2px(getContext(),50));
        }
        @Override
        public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            // Re-measure if we went beyond our maximum size.
            if (mMaxTabWidth > 0 && getMeasuredWidth() > mMaxTabWidth) {
                super.onMeasure(MeasureSpec.makeMeasureSpec(mMaxTabWidth, MeasureSpec.EXACTLY),
                        heightMeasureSpec);
            }
        }

        public int getIndex() {
            return mIndex;
        }

        public void setSelect(boolean iselect){
            if(iselect) {
                setCompoundDrawablesWithIntrinsicBounds(0,0,0,R.drawable.shape_line_green);
            }else{
                setCompoundDrawablesWithIntrinsicBounds(0,0, 0, 0);
            }
        }
    }
    public interface TabItemClickListener{
        public void onTabItemClick(View v,int pos);
    }
}
