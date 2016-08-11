package com.jameschen.movie;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class MoviewView extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		Intent intent = new Intent("com.jameschen.movie.ACTION_START_ACTIVITY");
		startActivity(intent);
		finish();

	}

}