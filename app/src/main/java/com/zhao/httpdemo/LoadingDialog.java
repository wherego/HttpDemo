package com.zhao.httpdemo;

import android.content.Context;
import android.widget.TextView;

/**
 * Created by zhao on 2016/10/8.
 */

public class LoadingDialog extends BaseDialog {
    private TextView tvShowText;

    public LoadingDialog(Context context) {
        super(context, R.layout.loading_dialog, R.style.Base_Dialog_Loading);
    }

    @Override
    protected void initView() {
        tvShowText = (TextView) findViewById(R.id.show_msg_text);
    }

    @Override
    protected void initData() {
        tvShowText.setText("加载中...");
    }

    @Override
    protected void setListener() {

    }

    public void setShowText(int resId){
        tvShowText.setText(resId);
    }
    public void setShowText(String showText){
        tvShowText.setText(showText);
    }
}
