package ru.myitschool.sunsataerohockey;

import android.os.Bundle;
import android.view.View;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import ru.myitschool.sunsataerohockey.MyGame;

public class AndroidLauncher extends AndroidApplication {
	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		initialize(new MyGame(), config);
		View rootView = AndroidLauncher.this.getWindow().getDecorView().findViewById(android.R.id.content);
		// View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
		// View.SYSTEM_UI_FLAG_FULLSCREEN
		// View.SYSTEM_UI_FLAG_LAYOUT_STABLE
		// View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
		// View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
		// View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
		rootView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
	}
}
