package login.application.com.androidloginscreen;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class Activity_Login extends AppCompatActivity {
    public static final String KEY_DAY_NIGHT_SWITCH_STATE = "day_night_switch_state";
    RelativeLayout Layout1,Layout2;

    Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            Layout1.setVisibility(View.VISIBLE);
            Layout2.setVisibility(View.VISIBLE);

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ImageView imageView = (ImageView) findViewById(R.id.imgView_logo);
        SharedPreferences prefs = getSharedPreferences(KEY_DAY_NIGHT_SWITCH_STATE, MODE_PRIVATE);
        Boolean State = prefs.getBoolean(KEY_DAY_NIGHT_SWITCH_STATE, false);
        final View background_view = findViewById(R.id.background_view);
        if (State.equals(true))
        { background_view.setAlpha(1f);
          imageView.setImageResource(R.drawable.logo_dark);}
        else
        {background_view.setAlpha(0);}







        Layout1 = (RelativeLayout) findViewById(R.id.rellay1);
        Layout2 = (RelativeLayout) findViewById(R.id.rellay2);
        handler.postDelayed(runnable, 2000);
        Button bt1 = (Button) findViewById(R.id.signupBtn);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Activity_Login.this,SignUpActivity.class));
                finish();
            }
        });
    }
}