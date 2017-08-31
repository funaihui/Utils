package com.wizardev.apackage.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.wizardev.apackage.view.MyToolbar;

public abstract class BaseActivity extends AppCompatActivity {
    LinearLayout vLayout;
    protected View viewLayout;
    protected MyToolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getToolbar();
        setContentView(requestView(inflateView()));
    }

    public abstract int inflateView();

    public abstract MyToolbar getToolbar();
    /**
     * @param layoutId
     *      1代表无占位图，0代表有展位图
     * @return
     */
    public  LinearLayout requestView(int layoutId) {
        return requestView(layoutId, toolbar);
    }
    public LinearLayout requestView(int layoutId, MyToolbar toolBar) {

        if (vLayout == null) {
            vLayout = new LinearLayout(getBaseContext());
            vLayout.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));//全部填充窗口
            vLayout.setOrientation(LinearLayout.VERTICAL);
        }
        vLayout.removeAllViews();
        viewLayout = LayoutInflater.from(getBaseContext()).inflate(layoutId,
                vLayout, false);
        vLayout.addView(viewLayout);
        if (toolBar != null) {
          //  LinearLayout linearLayout = new LinearLayout(this);
           // linearLayout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, QMUIDisplayHelper.getStatusBarHeight(this)));
          //  linearLayout.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));
            vLayout.addView(toolBar, 0);
           // LinearLayout line = new LinearLayout(this);
          //  line.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,QMUIDisplayHelper.dp2px(this,1)));
          //  line.setBackgroundResource(R.color.line);
          //  vLayout.addView(line);//标题栏下面的横线
         //   vLayout.addView(linearLayout, 0);//添加状态栏
            toolbar.setOnLeftImgClickListener(new MyToolbar.OnLeftImgClickListener() {
                @Override
                public void onLeftImgClick() {
                    finish();

                }
            });
        }
        return vLayout;
    }
}
