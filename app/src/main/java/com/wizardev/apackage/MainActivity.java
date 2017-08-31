package com.wizardev.apackage;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.wizardev.apackage.base.BaseActivity;
import com.wizardev.apackage.dialog.DialogFromBottom;
import com.wizardev.apackage.view.MyToolbar;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        findViewById(R.id.show_dialog).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFromBottom dialogFromBottom = new DialogFromBottom(MainActivity.this);
                dialogFromBottom.setContentView(R.layout.bottom_dialog_layout);
                dialogFromBottom.show();
            }
        });

        toolbar.setOnRightTextClickListener(new MyToolbar.OnRightTextClickListener() {
            @Override
            public void onRightTextClick() {
                startActivity(new Intent(MainActivity.this, OtherActivity.class));
            }
        });
    }

    @Override
    public int inflateView() {
        return R.layout.activity_main;
    }

    @Override
    public MyToolbar getToolbar() {
        toolbar = MyToolbar.getInstance(this, R.drawable.ic_launcher_background, "测试", "下一个");
        toolbar.setRightTextColor(android.R.color.holo_blue_bright);
        return toolbar;
    }
}
