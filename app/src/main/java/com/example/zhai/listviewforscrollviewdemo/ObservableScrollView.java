package com.example.zhai.listviewforscrollviewdemo;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * Created by Mastra on 2017/11/16.
 *
 * 由于ScrollView嵌套ListView，ListView的高度设置为最大值，导致ListView没有了滑动事件，
 * 无法响应OnScrollListener事件，故可通过重写ScrollVIew的onScrollChanged方法来替代
 * ListView的OnScrollListener方法
 */

public class ObservableScrollView extends ScrollView {

    private OnScollChangedListener mListener;

    public ObservableScrollView(Context context) {
        super(context);
    }

    public ObservableScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ObservableScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public interface OnScollChangedListener {
        void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldx, int oldy);

    }

    public void addOnScrollChangeListener(OnScollChangedListener listener ) {
        mListener = listener;
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (mListener != null) {
            mListener.onScrollChanged(this, l, t, oldl, oldt);
        }
    }
}
