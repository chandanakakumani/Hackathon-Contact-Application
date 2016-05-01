package users.example.com.hackathoncontact;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends Activity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash);

		final Handler handler = new Handler();
		handler.postDelayed(new Runnable() {
			public void run() {
				startActivity(new Intent(getBaseContext(),
						ViewBusinessCards.class));
				finish();
			}
		}, 2000);
	}
}