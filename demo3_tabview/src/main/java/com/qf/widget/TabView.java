package com.qf.widget;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qf.demo3_tabview.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ken on 2016/9/22.16:03
 */
public class TabView extends HorizontalScrollView implements ViewPager.OnPageChangeListener, View.OnClickListener {


    private LinearLayout ll;
    private View view;
    private LinearLayout.LayoutParams layoutParams;

    private List<Integer> widths;
    private ViewPager viewPager;

    public TabView(Context context) {
        this(context, null);
    }

    public TabView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TabView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.custem_tabview, this, true);
        ll = (LinearLayout) findViewById(R.id.ll_textviews);
        view = findViewById(R.id.view);
        layoutParams = (LinearLayout.LayoutParams) view.getLayoutParams();
    }

    /**
     * 设置控件的内容
     */
    public void setTabs(String[] tabs){
        if(tabs != null && tabs.length > 0){
            widths = new ArrayList<>();
            for (int i = 0; i < tabs.length; i++) {
                TextView tv = new TextView(getContext());
                tv.setText(tabs[i]);
                tv.setTextSize(20);
                tv.setPadding(20, 10, 20, 10);
                tv.setTag(i);
                tv.setOnClickListener(this);

                if(i == 0){
                    tv.setTextColor(Color.RED);
                } else {
                    tv.setTextColor(Color.GRAY);
                }

                tv.measure(0, 0);
                widths.add(tv.getMeasuredWidth());
                ll.addView(tv);
            }

            //设置光标的宽度
            view.getLayoutParams().width = widths.get(0);
        }
    }

    public void setViewPager(ViewPager viewPager){
        if(viewPager != null){
            this.viewPager = viewPager;
            viewPager.addOnPageChangeListener(this);
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        //TextView不等长，移动指示器
        int movex = 0;
        for(int i = 0; i < position; i++){
            movex += widths.get(i);
        }
        int move = (int)(movex + widths.get(position) * positionOffset);
        layoutParams.leftMargin = move;
        view.setLayoutParams(layoutParams);

        //处理光标的长度
        if(position != widths.size() - 1) {
            int changewidth = widths.get(position + 1) - widths.get(position);//变化的宽度
            layoutParams.width = (int) (widths.get(position) + changewidth * positionOffset);
        } else {
            //滑动到最后一个tab了
            layoutParams.width = widths.get(position);
        }

        //移动scrollview
        this.scrollTo(move - 100, 0);
    }

    @Override
    public void onPageSelected(int position) {
        for(int i = 0; i < ll.getChildCount(); i++){
            TextView tv = (TextView) ll.getChildAt(i);
            if(i == position){
                tv.setTextColor(Color.RED);
            } else {
                tv.setTextColor(Color.GRAY);
            }
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(View v) {
        int index = (int) v.getTag();
        if(viewPager != null){
            viewPager.setCurrentItem(index);
        }
    }
}
