package com.jameschen.movie;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class StartActivity extends Activity   {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		sendBroadcast(new Intent(Mp3BroadCastReceiver.ACTION_MOVIE_START));	
		finish();
	}
}
