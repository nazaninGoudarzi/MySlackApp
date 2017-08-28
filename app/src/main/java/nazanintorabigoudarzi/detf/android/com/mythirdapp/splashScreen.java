package nazanintorabigoudarzi.detf.android.com.mythirdapp;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class splashScreen extends AppCompatActivity {

    private static int SPLASH_TIME_OUT = 3000;
    private boolean InternetCheck = true;
    private ProgressBar spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Toast.makeText(splashScreen.this , "Welcome to this app" , Toast.LENGTH_LONG).show();

        spinner = (ProgressBar) findViewById(R.id.progressbar);
        spinner.setVisibility(View.VISIBLE);
        PostDelayedMethod();
    }

    public void PostDelayedMethod() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                boolean InternetResult = checkConnection();
                if (InternetResult) {
                    Intent intent = new Intent(splashScreen.this,MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    spinner.setVisibility(View.VISIBLE);
                    spinner.setVisibility(View.GONE);
                    DialogAppear();
                }
            }
        },SPLASH_TIME_OUT);
    }

    public void DialogAppear() {
        AlertDialog.Builder builder = new AlertDialog.Builder(splashScreen.this);
        builder.setTitle("Network Error");
        builder.setMessage("No Internet Connection");
        builder.setNegativeButton("Exit",new android.content.DialogInterface.OnClickListener(){
            public  void onClick(DialogInterface dialog, int which){
                finish();
            }
        });
        builder.setPositiveButton("Retry", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                recreate();
            }
        });
        builder.show();
    }

    private boolean checkConnection() {
        if (isOnline())
            return InternetCheck;
        else {
            InternetCheck = false;
            return InternetCheck;
        }
    }

    private boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if(netInfo != null && netInfo.isConnectedOrConnecting()){
            return true;
        }else
            return  false;
    }
}
