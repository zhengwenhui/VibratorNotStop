package com.jwd.vibrator;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class FullscreenActivity extends Activity {

	private Vibrator vibrator;
	private static boolean isVibrator = false;
	private static TextView contentView;
	private static Button button;

	private static int count = 0;
	static Handler timerHandler = new Handler() {
		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			if(isVibrator){
				button.setText(String.valueOf(count));
			}
			super.handleMessage(msg);
		}
	};
	Runnable timerRunnable=new Runnable(){
		@Override
		public void run() {
			// TODO Auto-generated method stub
			//要做的事情
			count++;
			timerHandler.postDelayed(this, 1000);
			timerHandler.sendMessage(new Message());
		}
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_fullscreen);
		contentView = (TextView) findViewById(R.id.dummy_content);
		button = (Button) findViewById(R.id.dummy_button);
		timerHandler.postDelayed(timerRunnable, 0);
	}

	@SuppressLint("NewApi")
	public void onClickButton(View view){
		count = 0;
		if(!isVibrator){
			Log.i("zhengwenhui", "start");
			vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
			if (vibrator == null || !vibrator.hasVibrator()) {
				contentView.append("\n没有震动");
			}
			long[] pattern = {1000, 10000, 1000, 10000};
			vibrator.vibrate(pattern, 0);
			isVibrator = true;
			contentView.append("\n震动...");
		}
		else{
			vibrator.cancel();
			isVibrator = false;
			contentView.append("\n停止");
		}
	}
}
