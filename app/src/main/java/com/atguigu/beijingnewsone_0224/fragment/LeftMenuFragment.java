package com.atguigu.beijingnewsone_0224.fragment;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.atguigu.beijingnewsone_0224.R;
import com.atguigu.beijingnewsone_0224.activity.MainActivity;
import com.atguigu.beijingnewsone_0224.base.BaseFragment;
import com.atguigu.beijingnewsone_0224.domain.NewsCenterBean;

import java.util.List;

/**
 * 作者：田学伟 on 2017/6/2 15:59
 * QQ：93226539
 * 作用：
 */

public class LeftMenuFragment extends BaseFragment {
    private ListView listView;
    private LeftMenuAdapter adapter;
    /**
     * 传入的数据
     */
    private List<NewsCenterBean.DataBean> datas;
    private int prePosition = 0;

    @Override
    protected View initView() {
        listView = new ListView(mContext);
        listView.setPadding(0, 40, 0, 0);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                prePosition = position;
                adapter.notifyDataSetChanged();

                MainActivity mainActivity = (MainActivity) mContext;
                mainActivity.getSlidingMenu().toggle();//关<->开
            }
        });
        return listView;
    }

    @Override
    public void initData() {
        super.initData();
    }

    public void setData(List<NewsCenterBean.DataBean> datas) {
        this.datas = datas;
        //绑定适配器
        adapter = new LeftMenuAdapter();
        listView.setAdapter(adapter);

    }

    class LeftMenuAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return datas == null ? 0 : datas.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView textView = (TextView) View.inflate(mContext, R.layout.item_leftmenu,null);

            if(prePosition == position) {
                //高亮
                textView.setEnabled(true);
            }else{
                //默认
                textView.setEnabled(false);
            }
            NewsCenterBean.DataBean dataBean = datas.get(position);
            textView.setText(dataBean.getTitle());
            return textView;
        }
    }
}
