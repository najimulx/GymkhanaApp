package com.example.dell.gymkhanaapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class PortfolioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portfolio);
    }
    public void callPortfolio(View view){
        String tag = view.getTag().toString();
        if(tag == getString(R.string.vp_name)){
            callIntent(R.string.vp_no);
        }
        if(tag == getString(R.string.gs_name)){
            callIntent(R.string.gs_no);
        }
        if(tag == getString(R.string.gsc_name)){
            callIntent(R.string.gsc_no);
        }
        if(tag == getString(R.string.gss_name)){
            callIntent(R.string.gss_no);
        }
        if(tag == getString(R.string.gst_name)){
            callIntent(R.string.gst_no);
        }
        if(tag == getString(R.string.photography_secy_name)){
            callIntent(R.string.photography_secy_no);
        }
        if(tag == getString(R.string.eco_secy_name)){
            callIntent(R.string.eco_secy_no);
        }
        if(tag == getString(R.string.dance_secy_name)){
            callIntent(R.string.dance_secy_no);
        }
        if(tag == getString(R.string.music_secy_name)){
            callIntent(R.string.music_secy_no);
        }
        if(tag == getString(R.string.indoor_secy_name)){
            callIntent(R.string.indoor_secy_no);
        }
        if(tag == getString(R.string.lpfa_secy_name)){
            callIntent(R.string.lpfa_secy_no);
        }
        if(tag == getString(R.string.gym_secy_name)){
            callIntent(R.string.gym_secy_no);
        }
    }

    public void callIntent(int number){
        Log.i("CALL","call intent called");
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:"+getString(number)));
        startActivity(intent);
    }
}
