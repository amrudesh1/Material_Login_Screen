package login.application.com.androidloginscreen;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class SignUpActivity extends AppCompatActivity {
    public static final String KEY_DAY_NIGHT_SWITCH_STATE = "day_night_switch_state";
    RelativeLayout Layout2;

    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            Layout2.setVisibility(View.VISIBLE);

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        ImageView imageView = (ImageView) findViewById(R.id.imgView_logo);
        SharedPreferences prefs = getSharedPreferences(KEY_DAY_NIGHT_SWITCH_STATE, MODE_PRIVATE);
        Boolean State = prefs.getBoolean(KEY_DAY_NIGHT_SWITCH_STATE, false);
        final View background_view = findViewById(R.id.background_view);
        if (State.equals(true))
        { background_view.setAlpha(1f);
            imageView.setImageResource(R.drawable.logo_dark);}
        else
        {background_view.setAlpha(0);}
        Layout2 = (RelativeLayout) findViewById(R.id.rellay1);
        handler.postDelayed(runnable, 1500); //2000 is the timeout for the splash
    }
}
