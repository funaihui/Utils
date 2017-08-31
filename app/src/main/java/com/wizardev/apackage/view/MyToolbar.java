package com.wizardev.apackage.view;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.wizardev.apackage.R;
import com.wizardev.apackage.util.QMUIDisplayHelper;

/**
 * author : wizardev
 * e-mail : wizarddev@163.com
 * time   : 2017/08/30
 * desc   :
 * version: 1.0
 */
public class MyToolbar extends Toolbar implements View.OnClickListener {

    private static int leftResId;
    private ColorStateList rightColorStateList;
    private static boolean isHideRight = false;
    private static boolean isHideLeft = false;

    public String getLeftText() {
        return leftText;
    }

    public void setLeftText(String leftText) {
        this.leftText = leftText;
    }

    public static String getRightText() {
        return rightText;
    }

    public static void setRightText(String rightText) {
        MyToolbar.rightText = rightText;
    }

    private String leftText;
    private static String rightText;
    private int leftTextSize;
    private int rightTextSize;
    private ColorStateList leftColorStateList;
    private static Drawable leftDrawable;
    private Drawable rightDrawable;
    private OnLeftImgClickListener leftImgClickListener;
    private OnRightImgClickListener rightImgClickListener;
    private OnLeftTextClickListener leftTextClickListener;
    private OnRightTextClickListener rightTextClickListener;
    private TextView titleTextView;
    private static final String TAG = "MyToolbar";
    private ImageView leftImg;
    private ImageView rightImg;
    private TextView leftTextView;
    private TextView rightTextView;
    private static String title;

    private MyToolbar(Context context) {
        this(context, null);
    }

    public static MyToolbar getInstance(Context context, int leftResId, String title, String rightText) {
        MyToolbar.leftResId = leftResId;
        MyToolbar.rightText = rightText;
        MyToolbar.title = title;
        isHideRight = true;
        Log.i(TAG, "MyToolbar.rightText: " + MyToolbar.rightText);
        return new MyToolbar(context);
    }

    private MyToolbar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    private MyToolbar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {

        super(context, attrs, defStyleAttr);
        View view = LayoutInflater.from(context).inflate(R.layout.my_toolbar_layout, null);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.MyToolbar, defStyleAttr, 0);
        if (leftResId == -1) {
            isHideLeft = true;
        } else {
            isHideLeft = a.getBoolean(R.styleable.MyToolbar_hide_left_button, false);
            leftDrawable = getResources().getDrawable(leftResId);
        }
        isHideRight = a.getBoolean(R.styleable.MyToolbar_hide_right_button, false);
        leftText = (a.getString(R.styleable.MyToolbar_left_text) == null) ? leftText : a.getString(R.styleable.MyToolbar_left_text);
        rightText = (a.getString(R.styleable.MyToolbar_right_text) == null) ? rightText : a.getString(R.styleable.MyToolbar_right_text);
        leftTextSize = a.getDimensionPixelSize(R.styleable.MyToolbar_left_text_size, 18);
        rightTextSize = a.getDimensionPixelSize(R.styleable.MyToolbar_right_text_size, 18);
        leftColorStateList = a.getColorStateList(R.styleable.MyToolbar_left_text_color);
        rightColorStateList = a.getColorStateList(R.styleable.MyToolbar_right_text_color);
        leftDrawable = (a.getDrawable(R.styleable.MyToolbar_left_icon) == null) ? leftDrawable : a.getDrawable(R.styleable.MyToolbar_left_icon);
        rightDrawable = (a.getDrawable(R.styleable.MyToolbar_right_icon) == null) ? rightDrawable : a.getDrawable(R.styleable.MyToolbar_right_icon);
        a.recycle();
        initView(view);
        if (isHideLeft) {
            //隐藏左边的按钮
            hideLeftButton();
        } else {
            showLeftButton();
        }
        if (isHideRight) {
            //隐藏右边的按钮
            hideRightButton();
        } else {
            showRightButton();
        }
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, QMUIDisplayHelper.getActionBarHeight(context));
        addView(view, layoutParams);
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
        titleTextView = view.findViewById(R.id.center_title);
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
            leftTextView.setTextColor(leftColorStateList.getColorForState(getDrawableState(), 0));
        }
        if (rightColorStateList != null) {
            rightTextView.setTextColor(rightColorStateList.getColorForState(getDrawableState(), 0));
        }
        rightTextView.setTextSize(rightTextSize);
        if (title != null) {
            titleTextView.setText(title);
        }
        rightImg.setOnClickListener(this);
        leftImg.setOnClickListener(this);
        rightTextView.setOnClickListener(this);
        leftTextView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.left_button:
                if (leftDrawable != null) {
                    if (leftImgClickListener != null) {
                        leftImgClickListener.onLeftImgClick();

                    }
                }
                break;
            case R.id.right_button:
                if (rightDrawable != null) {
                    if (rightImgClickListener != null) {
                        rightImgClickListener.onRightImgClick();

                    }
                }
                break;
            case R.id.left_text:
                if (leftTextClickListener != null) {
                    leftTextClickListener.onLeftTextClick();

                }
                break;
            case R.id.right_text:
                if (rightTextClickListener != null) {
                    rightTextClickListener.onRightTextClick();
                }
                break;
        }
    }

    public interface OnLeftImgClickListener {
        void onLeftImgClick();
    }

    public interface OnRightImgClickListener {
        void onRightImgClick();
    }

    public interface OnRightTextClickListener {

        void onRightTextClick();

    }

    public interface OnLeftTextClickListener {
        void onLeftTextClick();
    }

    public void setOnLeftImgClickListener(OnLeftImgClickListener leftImgClickListener) {
        this.leftImgClickListener = leftImgClickListener;
    }

    public void setOnRighttImgClickListener(OnRightImgClickListener rightImgClickListener) {
        this.rightImgClickListener = rightImgClickListener;
    }

    public void setOnLeftTextClickListener(OnLeftTextClickListener leftTextClickListener) {
        this.leftTextClickListener = leftTextClickListener;
    }

    public void setOnRightTextClickListener(OnRightTextClickListener rightTextClickListener) {
        this.rightTextClickListener = rightTextClickListener;
    }

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


    public TextView getRightTextView() {
        return rightTextView;
    }

    public void setRightTextColor(int colorId) {
        if (rightTextView != null) {
            rightTextView.setTextColor(getResources().getColor(colorId));
        }
    }

}
