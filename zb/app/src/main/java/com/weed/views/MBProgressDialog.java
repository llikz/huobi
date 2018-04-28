package com.weed.views;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by weedys on 16/7/29.
 */
public class MBProgressDialog extends ProgressDialog{
    public MBProgressDialog(Context context) {
        super(context);
    }

    public MBProgressDialog(Context context, int theme) {
        super(context, theme);
    }
}
