package com.florianmski.urbandictionary.ui.activities;



import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.florianmski.urbandictionary.R;

public class SplashActivity extends Activity {

	
	ImageView i1;
	public boolean mbactive;
	public ProgressBar progressBar;
	ActionBar mActionBar;
	protected static final int TIMER_RUNTIME = 5000;

	int SPLASH_TIME_OUT = 5000;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
	
		mActionBar = getActionBar();
		mActionBar.setBackgroundDrawable(new ColorDrawable(Color.rgb(0,153,204)));
		
		progressBar();
		i1=(ImageView)this.findViewById(R.id.imageView2);
		i1.setImageResource(R.drawable.wordbot);
	}
		public void progressBar() {
			progressBar=(ProgressBar)findViewById(R.id.progressBar1);
			final Thread timer=new Thread(new Runnable() {
				
				@Override
				public void run() {
					mbactive=true;
					int wait=0;
					while (mbactive && (wait<TIMER_RUNTIME)) {
						try {
							Thread.sleep(200);
							wait+=200;
							progressUpdate(wait);
							
							
						} catch (Exception e) {}					
					}
					
				}
			});
			timer.start();
	
			new Handler().postDelayed(new Runnable() {
				 
	            /*
	             * Showing splash screen with a timer. This will be useful when you
	             * want to show case your app logo / company
	             */
	 
	            @Override
	            public void run() {
	                // This method will be executed once the timer is over
	                // Start your app main activity
	                Intent i = new Intent(SplashActivity.this,SearchActivity.class);
	                startActivity(i);
	 
	                // close this activity
	                finish();
	            }
	        }, SPLASH_TIME_OUT);
			
		}
	    public void progressUpdate(int waited) {
			
			if(null != progressBar) {
		           // Ignore rounding error here
		           final int progress = progressBar.getMax() * waited / TIMER_RUNTIME;
		           progressBar.setProgress(progress);
		          
				}
		       }
		
	
}