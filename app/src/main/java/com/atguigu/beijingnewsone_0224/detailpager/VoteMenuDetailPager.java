package com.atguigu.beijingnewsone_0224.detailpager;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.atguigu.beijingnewsone_0224.base.MenuDetailBasePager;

/**
 * 作者：田学伟 on 2017/6/3 14:18
 * QQ：93226539
 * 作用：投票详情页面的内容
 */

public class VoteMenuDetailPager extends MenuDetailBasePager {
    public TextView textView;

    public VoteMenuDetailPager(Context mContext) {
        super(mContext);
    }

    @Override
    public View initView() {
        //实例视图
        textView = new TextView(mContext);
        textView.setText("投票详情页面的内容");
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(Color.RED);
        return textView;
    }

    @Override
    public void initData() {
        super.initData();
        textView.setText("投票详情页面的内容");
    }
}
