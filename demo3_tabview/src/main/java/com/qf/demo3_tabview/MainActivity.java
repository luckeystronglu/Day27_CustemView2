package com.qf.demo3_tabview;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qf.widget.TabView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager vp;
    private String[] datas;
    private MyAdapter myAdapter;

    private TabView tabview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        vp = (ViewPager) findViewById(R.id.vp);
        tabview = (TabView) findViewById(R.id.tabview);
        /**
         * 设置需要级联的ViewPager
         */
        tabview.setViewPager(vp);
        loadDatas();
    }

    private void loadDatas() {
        datas = new String[]{"头条", "新闻", "热点", "娱乐头条", "IT", "湘西土家族苗族自治州", "直播间"};

        /**
         * 设置组合控件的数据
         */
        tabview.setTabs(datas);

        myAdapter = new MyAdapter(this, datas);
        vp.setAdapter(myAdapter);

    }

    public static class MyAdapter extends PagerAdapter{

        private List<TextView> tvs;
        private Context context;

        public MyAdapter(Context context, String[]datas){
            tvs = new ArrayList<>();
            for(String data : datas){
                TextView tv = new TextView(context);
                tv.setText(data);
                tv.setTextSize(30);
                tv.setTextColor(Color.RED);
                tv.setGravity(Gravity.CENTER);
                tvs.add(tv);
            }
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(tvs.get(position));
            return tvs.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView(tvs.get(position));
        }

        @Override
        public int getCount() {
            return tvs.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }
}
