package login.application.com.androidloginscreen;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.mahfa.dnswitch.DayNightSwitch;
import com.mahfa.dnswitch.DayNightSwitchAnimListener;
import com.mahfa.dnswitch.DayNightSwitchListener;
import com.nightonke.boommenu.BoomButtons.ButtonPlaceEnum;
import com.nightonke.boommenu.BoomButtons.OnBMClickListener;
import com.nightonke.boommenu.BoomButtons.TextOutsideCircleButton;
import com.nightonke.boommenu.BoomMenuButton;
import com.nightonke.boommenu.Piece.PiecePlaceEnum;
import com.nightonke.boommenu.Util;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private DayNightSwitch day_night_switch;
    public static final String KEY_DAY_NIGHT_SWITCH_STATE = "day_night_switch_state";
    public void SaveState(Boolean b)
    {

        SharedPreferences.Editor editor = getSharedPreferences(KEY_DAY_NIGHT_SWITCH_STATE, MODE_PRIVATE).edit();
        editor.putBoolean(KEY_DAY_NIGHT_SWITCH_STATE,b);
        editor.apply();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Getting Night Mode State

        BoomMenuButton bmb = (BoomMenuButton) findViewById(R.id.bmb);
        final View background_view = findViewById(R.id.background_view);
        day_night_switch = (DayNightSwitch) findViewById(R.id.day_night_switch);
        day_night_switch.setDuration(450);
        SharedPreferences prefs = getSharedPreferences(KEY_DAY_NIGHT_SWITCH_STATE, MODE_PRIVATE);
        Boolean State = prefs.getBoolean(KEY_DAY_NIGHT_SWITCH_STATE, false);
        Log.i("Test",String.valueOf(State));
        if(State.equals(true))
        {
         day_night_switch.setIsNight(true);
         background_view.setAlpha(1f);
        }
        else
        {
            day_night_switch.setIsNight(false);
            background_view.setAlpha(0);
        }
        bmb.setPiecePlaceEnum(PiecePlaceEnum.DOT_2_1);
        bmb.setButtonPlaceEnum(ButtonPlaceEnum.SC_2_1);
        for (int i = 0; i < bmb.getPiecePlaceEnum().pieceNumber(); i++) {
            TextOutsideCircleButton.Builder builder = new TextOutsideCircleButton.Builder();
            switch(i)
            {
                case 0:
                    builder.normalText("Login");
                    builder.normalImageRes(R.drawable.login_rounded);
                    builder.textSize(12);
                    builder.imageRect(new Rect(Util.dp2px(10), Util.dp2px(10), Util.dp2px(70), Util.dp2px(70)));
                    break;
                    case 1:
                    builder.normalText("SignUp");
                    builder.imageRect(new Rect(Util.dp2px(10), Util.dp2px(10), Util.dp2px(70), Util.dp2px(70)));
                    builder.textSize(12);
                    builder.normalImageRes(R.drawable.sign_up);
                    break;
            }
                    builder.listener(new OnBMClickListener() {
                        @Override
                        public void onBoomButtonClick(int index) {
                            switch (index)
                            {
                                case 0:startActivity(new Intent(MainActivity.this,Activity_Login.class));
                                break;
                                case 1:startActivity(new Intent(MainActivity.this,SignUpActivity.class));
                                break;
                            }
                        }
                    });

                bmb.addBuilder(builder);
            }
        day_night_switch.setListener(new DayNightSwitchListener() {
            @Override
            public void onSwitch(boolean is_night) {
                Log.d("Test", "onSwitch() called with: is_night = [" + is_night + "]");
                if (is_night)
                    background_view.setAlpha(1f);
                    SaveState(is_night);

            }
        });

        day_night_switch.setAnimListener(new DayNightSwitchAnimListener() {
            @Override
            public void onAnimStart() {
                Log.d("Test", "onAnimStart() called");
            }

            @Override
            public void onAnimEnd() {
                Log.d("Test", "onAnimEnd() called");
            }

            @Override
            public void onAnimValueChanged(float value) {
                background_view.setAlpha(value);
                Log.d("Test", "onAnimValueChanged() called with: value = [" + value + "]");
            }
        });

        }
    }
