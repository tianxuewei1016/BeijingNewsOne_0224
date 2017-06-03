package com.atguigu.beijingnewsone_0224;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;

import com.atguigu.beijingnewsone_0224.activity.GuideActivity;
import com.atguigu.beijingnewsone_0224.activity.MainActivity;
import com.atguigu.beijingnewsone_0224.utils.CacheUtils;

import butterknife.ButterKnife;
import butterknife.InjectView;

import static android.view.animation.Animation.RELATIVE_TO_SELF;

public class WelcomeActivity extends AppCompatActivity {

    @InjectView(R.id.rl_root)
    RelativeLayout rlRoot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.inject(this);

        setAnimation();
    }

    private void setAnimation() {
        //设置三个动画: 渐变动画,缩放动画,旋转动画
        AlphaAnimation aa = new AlphaAnimation(0, 1);
        aa.setDuration(2000);
        aa.setFillAfter(true);

        ScaleAnimation sa = new ScaleAnimation(0, 1, 0, 1, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f);
        sa.setDuration(2000);
        sa.setFillAfter(true);

        RotateAnimation ra = new RotateAnimation(0, 360, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f);
        ra.setDuration(2000);
        ra.setFillAfter(true);

        //把动画加入到集合中去
        AnimationSet set = new AnimationSet(false);
        set.addAnimation(aa);
        set.addAnimation(sa);
        set.addAnimation(ra);

        //开始播放动画
        rlRoot.startAnimation(set);
        //监听动画播放完成
        set.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                boolean startMain = CacheUtils.getBoolean(WelcomeActivity.this, "start_main");
                Intent intent = null;
                if (startMain) {
                    //进入主页面
                    intent = new Intent(WelcomeActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    intent = new Intent(WelcomeActivity.this, GuideActivity.class);
                    startActivity(intent);
                }
                //关闭当前页面
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
