package com.example.zhai.listviewforscrollviewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private ObservableScrollView scrollView;
    private ListViewForScrollView listView;
    private MyAdapter mAdapter;
    private boolean mEnableFlag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scrollView = (ObservableScrollView) findViewById(R.id.scrollView);
        listView = (ListViewForScrollView) findViewById(R.id.listView);

        mAdapter = new MyAdapter();
        listView.setAdapter(mAdapter);

        // ListView的OnScrollListener不能响应，通过该方法实现加载更多的判断
        scrollView.addOnScrollChangeListener(new ObservableScrollView.OnScollChangedListener() {
            @Override
            public void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldx, int oldy) {
                int height=scrollView.getHeight();
                int scrollViewMeasuredHeight=scrollView.getChildAt(0).getMeasuredHeight();
                Log.d(TAG, "y="+y+" height="+height+" scrollViewMeasuredHeight="+scrollViewMeasuredHeight);
                if((y+height)>=scrollViewMeasuredHeight) {
                    if(mEnableFlag) {
                        mEnableFlag=false;
                        Log.e(TAG, "加载更多");
                    }
                }
            }
        });
    }

    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return 30;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(MainActivity.this).inflate(R.layout.list_item, null);
            }
            return convertView;
        }
    }
}
