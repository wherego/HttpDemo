package com.zhao.httpdemo;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Point;
import android.view.Display;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by zhao on 2016/10/8.
 */

public abstract class BaseDialog extends Dialog {
    /**
     * 用一个默认样式文件
     *
     * @param context     C
     * @param layoutResId 布局文件
     */
    public BaseDialog(Context context, int layoutResId) {
        super(context, R.style.Base_Dialog);
        init(layoutResId);
    }

    /**
     * @param context     C
     * @param layoutResId 布局文件
     * @param styleId     样式文件
     */
    public BaseDialog(Context context, int layoutResId, int styleId) {
        super(context, styleId);
        init(layoutResId);
    }

    private void init(int layoutResId) {
        setContentView(layoutResId);
        setProperty();
        initView();
        initData();
        setListener();
    }


    private void setProperty() {
        Window window = getWindow();
        WindowManager.LayoutParams p = window.getAttributes();
        Display d = getWindow().getWindowManager().getDefaultDisplay();
        Point point = new Point();
        d.getSize(point);
        p.height = point.y * 1;
        p.width = point.x * 1;
        window.setAttributes(p);

    }

    protected abstract void initView();

    protected abstract void initData();

    protected abstract void setListener();
}

