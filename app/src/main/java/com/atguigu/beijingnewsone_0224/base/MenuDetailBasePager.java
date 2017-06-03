package com.atguigu.beijingnewsone_0224.base;

import android.content.Context;
import android.view.View;

/**
 * 作者：田学伟 on 2017/6/3 15:10
 * QQ：93226539
 * 作用：
 */

public abstract class MenuDetailBasePager {

    public final Context mContext;
    /**
     * 代表子类的整个视图
     */
    public View rootView;

    public MenuDetailBasePager(Context mContext){
       this.mContext = mContext;
        rootView = initView();
    }

    /**
     * 该方法是抽象的，由子类实现，达到自己的视图
     * @return
     */
    public abstract View initView();

    /**
     * 子类要绑定数据的时候重写该方法
     */
    public void initData(){

    }
}
