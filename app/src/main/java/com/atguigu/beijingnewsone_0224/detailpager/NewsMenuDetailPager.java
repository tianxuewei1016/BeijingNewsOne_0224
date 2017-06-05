package com.atguigu.beijingnewsone_0224.detailpager;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.atguigu.beijingnewsone_0224.R;
import com.atguigu.beijingnewsone_0224.base.MenuDetailBasePager;
import com.atguigu.beijingnewsone_0224.domain.NewsCenterBean;
import com.viewpagerindicator.TabPageIndicator;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;

/**
 * 作者：田学伟 on 2017/6/3 14:18
 * QQ：93226539
 * 作用：新闻详情页面的内容
 */

public class NewsMenuDetailPager extends MenuDetailBasePager {
    /**
     * TabDetailPager的对应的数据
     */
    private final List<NewsCenterBean.DataBean.ChildrenBean> datas;
    @InjectView(R.id.viewpager)
    ViewPager viewpager;
    @InjectView(R.id.indicator)
    TabPageIndicator indicator;
    @InjectView(R.id.ib_next)
    ImageButton ibNext;
    /**
     * TabDetailPager页面集合
     */
    private List<TabDetailPager> tabDetailPagers;

    public NewsMenuDetailPager(Context mContext, List<NewsCenterBean.DataBean.ChildrenBean> children) {
        super(mContext);
        this.datas = children;
    }

    @Override
    public View initView() {
        //实例视图
        View view = View.inflate(mContext, R.layout.pager_news_menu_detail, null);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        //根据数据创建子页面
        tabDetailPagers = new ArrayList<>();
        for (int i = 0; i < datas.size(); i++) {
            tabDetailPagers.add(new TabDetailPager(mContext, datas.get(i)));
        }
        viewpager.setAdapter(new NewsMenuDetailPagerAdapter());
        //TabPageIndicator和ViewPager关联起来
        indicator.setViewPager(viewpager);
    }

    @OnClick(R.id.ib_next)
    public void onViewClicked() {
        //切换到下一个页面
        viewpager.setCurrentItem(viewpager.getCurrentItem() + 1);
    }

    class NewsMenuDetailPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return tabDetailPagers == null ? 0 : tabDetailPagers.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            TabDetailPager tabDetailPager = tabDetailPagers.get(position);
            View rootView = tabDetailPager.rootView;
            container.addView(rootView);
            tabDetailPager.initData();
            return rootView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        /**
         * 或者抬头的12条数据
         *
         * @param position
         * @return
         */
        @Override
        public CharSequence getPageTitle(int position) {
            return datas.get(position).getTitle();
        }
    }
}
