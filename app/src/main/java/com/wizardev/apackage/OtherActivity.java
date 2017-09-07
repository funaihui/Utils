package com.wizardev.apackage;

import android.os.Bundle;

import com.wizardev.apackage.base.BaseActivity;
import com.wizardev.apackage.view.MyToolbar;

public class OtherActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int inflateView() {
        return R.layout.activity_other;
    }

    @Override
    public MyToolbar setupToolbar() {
        toolbar = MyToolbar.getInstance(this, -1, "另一個", "");
        return toolbar;
    }
}
