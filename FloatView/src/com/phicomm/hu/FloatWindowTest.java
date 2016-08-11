package com.phicomm.hu;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.Button;
import android.widget.LinearLayout;

public class FloatWindowTest extends Activity {

	WindowManager 				mWindowManager;
	WindowManager.LayoutParams 	wmParams;
	
	LinearLayout 				mFloatLayout;
	Button 						mFloatButton;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.main);

		Button start = (Button) findViewById(R.id.start);
		Button stop  = (Button) findViewById(R.id.stop);

		start.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
				createFloatView();
				// finish();
				// handle.post(r);
			}
		});

		stop.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (mFloatLayout != null) {
					mWindowManager.removeView(mFloatLayout);
					// finish();
				}
			}
		});
		
		findViewById(R.id.button1).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				startActivity(new Intent(FloatWindowTest.this,ResultActivity.class));
				
			}
		});

	}

	// 创建一个悬浮窗
	private void createFloatView() {
		// 获取LayoutParams对象
		wmParams = new WindowManager.LayoutParams();

		// 获取的是LocalWindowManager对象
		// mWindowManager = this.getWindowManager();
		// mWindowManager = getWindow().getWindowManager();
		// 获取的是CompatModeWrapper对象
		mWindowManager = (WindowManager) getApplication().getSystemService(Context.WINDOW_SERVICE);
		
		wmParams.type 		= LayoutParams.TYPE_PHONE;
		wmParams.format 	= PixelFormat.RGBA_8888;
		wmParams.flags 		= LayoutParams.FLAG_NOT_FOCUSABLE;
		wmParams.gravity 	= Gravity.LEFT | Gravity.TOP;
		wmParams.x = 0;
		wmParams.y = 0;
		wmParams.width 		= WindowManager.LayoutParams.WRAP_CONTENT;
		wmParams.height 	= WindowManager.LayoutParams.WRAP_CONTENT;

		LayoutInflater inflater = this.getLayoutInflater();

		mFloatLayout = (LinearLayout) inflater.inflate(R.layout.float_layout,null);
		// 添加自定义视图
		mWindowManager.addView(mFloatLayout, wmParams);
		
		// 初始化UI
		mFloatButton = (Button) mFloatLayout.findViewById(R.id.float_id);
		
		// 绑定触摸移动监听
		mFloatButton.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				
				wmParams.x = (int) event.getRawX() - mFloatLayout.getWidth()/ 2;
				// 25为状态栏高度
				wmParams.y = (int) event.getRawY() - mFloatLayout.getHeight()/ 2 - 40;
				mWindowManager.updateViewLayout(mFloatLayout, wmParams);
				return false;
			}
		});

		// 绑定点击监听
		mFloatButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				
//				Intent intent = new Intent(FloatWindowTest.this,ResultActivity.class);
//				startActivity(intent);
			}
		});

	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		finish();
		// 杀死app进程
		// System.exit(0);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		Log.d("andli","--onDestroy--");
	}
}