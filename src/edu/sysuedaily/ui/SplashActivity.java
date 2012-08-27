package edu.sysuedaily.ui;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import edu.sysuedaily.R;

public class SplashActivity extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash);
		
		ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
		scheduledExecutorService.schedule(new Runnable() {
			
			@Override
			public void run() {
				startActivity(new Intent(SplashActivity.this, MainActivity.class));
				SplashActivity.this.finish();
				
			}
		}, 2, TimeUnit.SECONDS);
		
	}

}
