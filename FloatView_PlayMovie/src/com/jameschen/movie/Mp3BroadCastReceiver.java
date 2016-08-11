package com.jameschen.movie;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.jameschen.movie.service.MediaPlaybackService;

public class Mp3BroadCastReceiver extends BroadcastReceiver {

	public static final String ACTION_MOVIE_START = "com.jameschen.movie.START";

	@Override
	public void onReceive(Context context, Intent intent) {
		String action = intent.getAction();
	if (action.equals(ACTION_MOVIE_START)) {

			Intent mIntent = new Intent("createUI");
			mIntent.setClass(context, MediaPlaybackService.class);
			context.startService(mIntent);
		}
	}

}
