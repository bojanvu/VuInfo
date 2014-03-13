package com.algebra.vuinfo.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.algebra.vuinfo.R;

public class SplashActivity extends Activity {

	private static int SPLASH_TIME_OUT = 4000;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);

		final ImageView vu = (ImageView) findViewById(R.id.imageview_vu);
		final ImageView vukovar = (ImageView) findViewById(R.id.imageview_vukovar);
		final ImageView info = (ImageView) findViewById(R.id.imageview_info);

		Animation anim = AnimationUtils.loadAnimation(SplashActivity.this,
				R.anim.scale_animation);
		Animation anim2 = AnimationUtils.loadAnimation(SplashActivity.this,
				R.anim.move);
		Animation anim3 = AnimationUtils.loadAnimation(SplashActivity.this,
				R.anim.bounce);
		vu.startAnimation(anim);
		vukovar.startAnimation(anim2);
		info.startAnimation(anim3);

		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {

				Intent i = new Intent(SplashActivity.this,
						NavDrawerActivity.class);
				startActivity(i);

				finish();
			}
		}, SPLASH_TIME_OUT);
	}

}
