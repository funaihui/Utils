package com.wizardev.apackage.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wizardev.apackage.R;

/**
 * author : wizardev
 * e-mail : wizarddev@163.com
 * time   : 2017/08/30
 * desc   :
 * version: 1.0
 */
public class MyToolbar extends Toolbar {

    private ColorStateList rightColorStateList;
    private boolean isHideRight = false;
    private boolean isHideLeft = false;
    private String leftText;
    private String rightText;
    private int leftTextSize;
    private int rightTextSize;
    private ColorStateList leftColorStateList;
    private Drawable leftDrawable;
    private Drawable rightDrawable;

    public ImageView getLeftImg() {
        return leftImg;
    }

    public void setLeftImg(ImageView leftImg) {
        this.leftImg = leftImg;
    }

    public ImageView getRightImg() {
        return rightImg;
    }

    public void setRightImg(ImageView rightImg) {
        this.rightImg = rightImg;
    }

    public TextView getLeftTextView() {
        return leftTextView;
    }

    public void setLeftTextView(TextView leftTextView) {
        this.leftTextView = leftTextView;
    }

    public TextView getRightTextView() {
        return rightTextView;
    }

    public void setRightTextView(TextView rightTextView) {
        this.rightTextView = rightTextView;
    }

    private ImageView leftImg;
    private ImageView rightImg;
    private TextView leftTextView;
    private TextView rightTextView;

    public MyToolbar(Context context) {
        this(context, null);
    }

    public MyToolbar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyToolbar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View view = LayoutInflater.from(context).inflate(R.layout.my_toolbar_layout, null);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.MyToolbar, defStyleAttr, 0);
        isHideLeft = a.getBoolean(R.styleable.MyToolbar_hide_left_button, false);
        isHideRight = a.getBoolean(R.styleable.MyToolbar_hide_right_button, false);
        leftText = a.getString(R.styleable.MyToolbar_left_text);
        rightText = a.getString(R.styleable.MyToolbar_right_text);
        leftTextSize = a.getDimensionPixelSize(R.styleable.MyToolbar_left_text_size, 15);
        rightTextSize = a.getDimensionPixelSize(R.styleable.MyToolbar_right_text_size, 15);
        leftColorStateList = a.getColorStateList(R.styleable.MyToolbar_left_text_color);
        rightColorStateList = a.getColorStateList(R.styleable.MyToolbar_right_text_color);
        leftDrawable = a.getDrawable(R.styleable.MyToolbar_left_icon);
        rightDrawable = a.getDrawable(R.styleable.MyToolbar_right_icon);
        a.recycle();
        initView(view);
        if (isHideLeft) {
            //隐藏左边的按钮
            hideLeftButton();
        }else {
            showLeftButton();
        }
        if (isHideRight) {
            //隐藏右边的按钮
            hideRightButton();
        } else {
            showRightButton();
        }
        addView(view);
    }

    private void showRightButton() {
        rightImg.setVisibility(VISIBLE);
    }

    private void showLeftButton() {
        leftImg.setVisibility(VISIBLE);
    }

    private void hideRightButton() {
        rightImg.setVisibility(GONE);
    }

    private void hideLeftButton() {
        leftImg.setVisibility(GONE);
    }

    private void initView(View view) {
        leftImg = view.findViewById(R.id.left_button);
        rightImg = view.findViewById(R.id.right_button);
        leftTextView = view.findViewById(R.id.left_text);
        rightTextView = view.findViewById(R.id.right_text);
        if (leftDrawable != null) {
            leftImg.setImageDrawable(leftDrawable);
        }
        if (rightDrawable != null) {
            rightImg.setImageDrawable(rightDrawable);
        }
        if (leftText != null) {
            leftTextView.setText(leftText);
        }
        if (rightText != null) {
            rightTextView.setText(rightText);
        }
        leftTextView.setTextSize(leftTextSize);
        rightTextView.setTextSize(rightTextSize);
        if (leftColorStateList != null) {
            leftTextView.setTextColor(leftColorStateList.getColorForState(getDrawableState(),0));
        }
        if (rightColorStateList != null) {
            rightTextView.setTextColor(rightColorStateList.getColorForState(getDrawableState(),0));
        }
        rightTextView.setTextSize(rightTextSize);
    }
}
