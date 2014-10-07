package com.example.simplerank;

import android.os.Bundle;
import android.app.Activity;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
        		WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_main);
		final MeterCircle mCircle = (MeterCircle)findViewById(R.id.mcircle1);
		mCircle.getViewTreeObserver().addOnGlobalLayoutListener(new OnGlobalLayoutListener() {
			
			@Override
			public void onGlobalLayout() {
				int width = mCircle.getWidth();
				int height = mCircle.getHeight();
				mCircle.setDimensions(width, height);
			}
		});
	}

}
