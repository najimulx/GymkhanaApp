package com.example.dell.gymkhanaapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

public class SocietyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_society);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow(); // in Activity's onCreate() for instance
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        Button button1 = (Button)findViewById(R.id.cseButton);
        Button button2 = (Button)findViewById(R.id.eceButton);
        Button button3 = (Button)findViewById(R.id.eeButton);
        Button button4 = (Button)findViewById(R.id.meButton);
        Button button5 = (Button)findViewById(R.id.ceButton);
        Button button6 = (Button)findViewById(R.id.eniButton);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonAction(1);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonAction(2);
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonAction(3);
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonAction(4);
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonAction(5);
            }
        });
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buttonAction(6);
            }
        });




    }

    public void buttonAction(final int selectedButton){
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Are You Sure ?")
                .setMessage("Society Once Selected CANNOT be changed later !!!")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MainActivity.sharedPreferences.edit().putString("societySelected","true").apply();
                        buttonFinalClicked(selectedButton);
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(SocietyActivity.this,"Select A Society",Toast.LENGTH_SHORT).show();
                    }
                }).show();
    }

    public void buttonFinalClicked(int selectedButton){
        if(selectedButton == 1){
            MainActivity.sharedPreferences.edit().putString("link","https://www.facebook.com/groups/186753138074295/").apply();
            MainActivity.sharedPreferences.edit().putString("home","https://m.facebook.com/groups/186753138074295/").apply();
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
        }
        if(selectedButton == 5){
            MainActivity.sharedPreferences.edit().putString("link","https://www.facebook.com/groups/136786866371031/").apply();
            MainActivity.sharedPreferences.edit().putString("home","https://m.facebook.com/groups/136786866371031/").apply();
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
        }
        if(selectedButton == 3){
            MainActivity.sharedPreferences.edit().putString("link","https://www.facebook.com/groups/electra.nits/").apply();
            MainActivity.sharedPreferences.edit().putString("home","https://m.facebook.com/groups/electra.nits/").apply();
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
        }
        if(selectedButton == 2){
            MainActivity.sharedPreferences.edit().putString("link","https://www.facebook.com/groups/ecsnits/").apply();
            MainActivity.sharedPreferences.edit().putString("home","https://m.facebook.com/groups/ecsnits/").apply();
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
        }
        if(selectedButton == 6){
            MainActivity.sharedPreferences.edit().putString("link","https://www.facebook.com/groups/insees/").apply();
            MainActivity.sharedPreferences.edit().putString("home","https://m.facebook.com/groups/insees/").apply();
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
        }
        if(selectedButton == 4){
            MainActivity.sharedPreferences.edit().putString("link","https://www.facebook.com/mesnits/").apply();
            MainActivity.sharedPreferences.edit().putString("home","https://m.facebook.com/mesnits/").apply();
            Intent intent = new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent);
        }
    }
}
