package asmaa.movieapp.UI.Activities.Splash;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.VideoView;

import com.roger.catloadinglibrary.CatLoadingView;

import asmaa.movieapp.R;
import asmaa.movieapp.UI.Activities.Home.HomeActivity;

public class SplashActivity extends AppCompatActivity {

    private CatLoadingView mView;
    private Handler mHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        initialization();
    }

    private void initialization() {
        mView = new CatLoadingView();
        mView.show(getSupportFragmentManager(), "");

        mHandler = new Handler();
        setHandler();
    }

    private void setHandler() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                        Intent mainIntent = new Intent(SplashActivity.this, HomeActivity.class);
                        startActivity(mainIntent);
                        finish();


            }
        }, 3250);
    }

}
