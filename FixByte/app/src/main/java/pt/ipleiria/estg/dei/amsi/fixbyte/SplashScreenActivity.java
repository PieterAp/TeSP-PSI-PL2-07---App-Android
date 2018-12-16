package pt.ipleiria.estg.dei.amsi.fixbyte;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class SplashScreenActivity extends AppCompatActivity {
    private static int  SplashTimeOut = 2500;
    TextView textViewVersion;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //Fullscreen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash_screen);

        textViewVersion = findViewById(R.id.textViewVersion);
        textViewVersion.setText((getResources().getString(R.string.action_version)) + ": " + BuildConfig.VERSION_NAME);

        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                intent = new Intent (getApplication(), LoginActivity.class);
                startActivity(intent);
                finish();
            }
        },SplashTimeOut);
    }
}
