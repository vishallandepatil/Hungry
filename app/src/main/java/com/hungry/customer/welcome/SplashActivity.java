package com.hungry.customer.welcome;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.hungry.customer.HomePage;
import com.hungry.customer.MainActivity2;
import com.hungry.customer.R;
import com.hungry.customer.app.PrefManager;
import com.hungry.customer.login.api.OTP;
import com.hungry.customer.retrofitsetting.RetrofitClientInstance;
import com.hungry.customer.welcome.model.AppVersionResponce;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashActivity extends AppCompatActivity {

    private static final String TAG = SplashActivity.class.getSimpleName();


    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        final Button btnUpdateApp = findViewById(R.id.btnUpdateApp);
        final TextView tvUpdateMessage = findViewById(R.id.tvUpdateMessage);
        final TextView tvUpdateTitle = findViewById(R.id.tvUpdateTitle);
        final ImageView bottom = findViewById(R.id.bottom);
       // Utilities.launchActivity(SplashActivity.this, HomeActivity.class, true);
      //  animation();

        OTP loginApi = RetrofitClientInstance.getRetrofitInstance().
                create(OTP.class);
        loginApi.appversion(AppVersionResponce.TYPE,RetrofitClientInstance.API_KEY).enqueue(new Callback<AppVersionResponce>() {
            @Override
            public void onResponse(Call<AppVersionResponce> call, Response<AppVersionResponce> response) {

                if(response.body().status == 200){
                    PackageInfo pinfo = null;
                    try {
                        pinfo = getPackageManager().getPackageInfo(getPackageName(), 0);
                        int versionNumber = pinfo.versionCode;
                        String versionName = pinfo.versionName;


                    if( Integer.parseInt(response.body().result) == versionNumber){

                        if(!new PrefManager(SplashActivity.this).islogin()) {
                            Intent intent = new Intent(SplashActivity.this, MainActivity2.class);
                            startActivity(intent);
                            finish();

                        } else {
                            Intent intent = new Intent(SplashActivity.this, HomePage.class);
                            startActivity(intent);
                            finish();
                        }


                    } else {
                        bottom.setVisibility(View.GONE);
                        btnUpdateApp.setVisibility(View.VISIBLE);
                        tvUpdateMessage.setVisibility(View.VISIBLE);
                        tvUpdateTitle.setVisibility(View.VISIBLE);
                    }
                    } catch (Exception e) {
                        e.printStackTrace();
                        bottom.setVisibility(View.GONE);
                        tvUpdateMessage.setVisibility(View.VISIBLE);
                        tvUpdateTitle.setVisibility(View.VISIBLE);
                        tvUpdateTitle.setText("There is Something Problem");
                        tvUpdateMessage.setText("Please check after some time");
                       // btnUpdateApp.setVisibility(View.VISIBLE);
                    }


                } else {
                    bottom.setVisibility(View.GONE);
                    bottom.setVisibility(View.GONE);
                    tvUpdateMessage.setVisibility(View.VISIBLE);
                    tvUpdateTitle.setVisibility(View.VISIBLE);
                    tvUpdateTitle.setText("There Network Problem");
                    tvUpdateMessage.setText("Please check data connection");
                }

            }

            @Override
            public void onFailure(Call<AppVersionResponce> call, Throwable t) {
                bottom.setVisibility(View.GONE);
                bottom.setVisibility(View.GONE);
                tvUpdateMessage.setVisibility(View.VISIBLE);
                tvUpdateTitle.setVisibility(View.VISIBLE);
                tvUpdateTitle.setText("There Network Problem");
                tvUpdateMessage.setText("Please check data connection");
            }
        });
        btnUpdateApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPlayStore();
            }
        });
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "Fetching FCM registration token failed", task.getException());
                            return;
                        }

                        // Get new FCM registration token
                        String token = task.getResult();

                        // Log and toast
                        String msg = token;
                        Log.d(TAG, msg);
                        //Toast.makeText(SplashActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });
    }
    private void openPlayStore() {
        final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object
        try {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
        } catch(android.content.ActivityNotFoundException anfe) {
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
        }
        catch (Exception e)
        {

        }
    }

    public void animation() {
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {



            }
        }, 3000);
    }


}