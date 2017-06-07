package com.atguigu.beijingnewsone_0224.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.atguigu.beijingnewsone_0224.R;
import com.atguigu.beijingnewsone_0224.activity.PicassoSampleActivity;
import com.atguigu.beijingnewsone_0224.domain.PhotosMenuDetailPagerBean;
import com.atguigu.beijingnewsone_0224.utils.BitmapCacheUtils;
import com.atguigu.beijingnewsone_0224.utils.Constants;
import com.atguigu.beijingnewsone_0224.utils.NetCachUtils;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * 作者：田学伟 on 2017/6/6 15:41
 * QQ：93226539
 * 作用：
 */

public class PhotosMenuDetailPagerAdapater extends RecyclerView.Adapter<PhotosMenuDetailPagerAdapater.MyViewHolder> {

    private final Context mContext;
    private final List<PhotosMenuDetailPagerBean.DataEntity.NewsEntity> datas;
    private final RecyclerView recyclerview;
    /**
     * 做图片三级缓存
     * 1.内存缓存
     * 2.本地缓存
     * 3.网络缓存
     */
    private BitmapCacheUtils bitmapCacheUtils;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case NetCachUtils.SUCESS:
                    Bitmap bitmap = (Bitmap) msg.obj;
                    int position = msg.arg1;
                    Log.e("TAG", "请求图片成功==" + position);
                    ImageView imageview = (ImageView) recyclerview.findViewWithTag(position);
                    if (imageview != null && bitmap != null) {
                        imageview.setImageBitmap(bitmap);
                    }

                    break;
                case NetCachUtils.FAIL:
                    position = msg.arg1;
                    Log.e("TAG", "请求图片失败==" + position);
                    break;
            }
        }
    };

    public PhotosMenuDetailPagerAdapater(Context mContext, List<PhotosMenuDetailPagerBean.DataEntity.NewsEntity> datas, RecyclerView recyclerview) {
        this.mContext = mContext;
        this.datas = datas;
        //把Hanlder传入构造方法
        bitmapCacheUtils = new BitmapCacheUtils(handler);
        this.recyclerview = recyclerview;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(View.inflate(mContext, R.layout.item_photos, null));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        PhotosMenuDetailPagerBean.DataEntity.NewsEntity newsEntity = datas.get(position);
        holder.tvTitle.setText(newsEntity.getTitle());

        String imageUrl = Constants.BASE_URL + newsEntity.getListimage();
//        Glide.with(mContext)
//                .load(imageUrl)
//                .placeholder(R.drawable.news_pic_default)
//                .error(R.drawable.news_pic_default)
//                .diskCacheStrategy(DiskCacheStrategy.ALL)
//                .into(holder.ivIcon);
        //设置点击事件
        Bitmap bitmap = bitmapCacheUtils.getBitmap(imageUrl,position);
        //图片对应的Tag就是位置
        holder.ivIcon.setTag(position);
        if(bitmap != null) {//来自内存和本地，不包括网络
            holder.ivIcon.setImageBitmap(bitmap);
        }
    }

    @Override
    public int getItemCount() {
        return datas == null ? 0 : datas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @InjectView(R.id.iv_icon)
        ImageView ivIcon;
        @InjectView(R.id.tv_title)
        TextView tvTitle;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.inject(this, itemView);
            //设置点击事件
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    String imageUrl = Constants.BASE_URL + datas.get(getLayoutPosition()).getListimage();
//                    Intent intent = new Intent(mContext, PicassoSampleActivity.class);
//                    intent.setData(Uri.parse(imageUrl));
//                    mContext.startActivity(intent);
                    Intent intent = new Intent(mContext, PicassoSampleActivity.class);
                    intent.putExtra("url", Constants.BASE_URL + datas.get(getLayoutPosition()).getListimage());
                    mContext.startActivity(intent);
                }
            });
        }
    }
}
