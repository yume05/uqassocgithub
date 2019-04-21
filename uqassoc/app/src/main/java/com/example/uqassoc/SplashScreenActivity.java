package com.example.uqassoc;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by navneet on 12/11/16.
 */

public class SplashScreenActivity extends Activity {

    private static int SPLASH_TIME_OUT = 2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
        ImageView iv= (ImageView) findViewById(R.id.imagesplash);
        ImageView iv1= (ImageView) findViewById(R.id.imageView3);
        TextView iv2= (TextView) findViewById(R.id.textView10);

        iv1.setBackgroundResource(R.color.colorUQAC);
        iv.setBackgroundResource(R.mipmap.ic_logouqassoc);
        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            iv.startAnimation(AnimationUtils.loadAnimation(SplashScreenActivity.this, R.anim.move_left));
            iv1.startAnimation(AnimationUtils.loadAnimation(SplashScreenActivity.this, R.anim.move_left));
            iv2.startAnimation(AnimationUtils.loadAnimation(SplashScreenActivity.this, R.anim.appear_right));
        }else{
            iv.startAnimation(AnimationUtils.loadAnimation(SplashScreenActivity.this, R.anim.move_left_l));
            iv1.startAnimation(AnimationUtils.loadAnimation(SplashScreenActivity.this, R.anim.move_left_l));
            iv2.startAnimation(AnimationUtils.loadAnimation(SplashScreenActivity.this, R.anim.appear_right_l));
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run(){
                Intent i = new Intent(getBaseContext(),MainActivity.class);
                startActivity(i);
                finish();
            }
        }, SPLASH_TIME_OUT);
       /* final ImageView imagesplash = (ImageView) findViewById(R.id.imagesplash);
        imagesplash.setVisibility(View.VISIBLE);
        final Animation animation_1 = AnimationUtils.loadAnimation(getBaseContext(),R.anim.rotate);
        final Animation animation_2 = AnimationUtils.loadAnimation(getBaseContext(),R.anim.antirotate);

        Intent i = new Intent(getBaseContext(),MainActivity.class);
        startActivity(i);
       /* imagesplash.startAnimation(animation_2);
        animation_2.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                imagesplash.startAnimation(animation_1);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        animation_1.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                finish();
                Intent i = new Intent(getBaseContext(),MainActivity.class);
                startActivity(i);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });*/
    }
}
