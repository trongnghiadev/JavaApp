package com.example.appfood;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import com.google.firebase.auth.FirebaseAuth;
import com.example.appfood.View.Account.SignInActivity;
import com.example.appfood.View.HomeActivity;

public class MainActivity extends AppCompatActivity {
    MediaPlayer bgMs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       bgMs = MediaPlayer.create(this, R.raw.nhacnen);
            bgMs.start();

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        new Handler().postDelayed(new Runnable() {

            // check ktra đã đăng nhập vào thẳng vào Home
            public void run() {

                if (firebaseAuth.getCurrentUser() != null) {
                    if (firebaseAuth.getCurrentUser().getEmail().length() > 0) {
                        startActivity(new Intent(MainActivity.this, HomeActivity.class));
                    }
                } else {
                    startActivity(new Intent(MainActivity.this, SignInActivity.class));
                }

                finish();
            }
        }, 3500);


    }

    @Override
    protected void onStop() {
        super.onStop();
        bgMs.pause();
    }
}
