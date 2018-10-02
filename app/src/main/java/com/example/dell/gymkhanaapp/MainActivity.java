package com.example.dell.gymkhanaapp;

import android.app.ActionBar;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.CookieManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    WebView webView;
    String home;
    int webViewLoad = 0;
    String shareMsg = "Download the Gymkhana App From GooglePlay Store Now";
    SwipeRefreshLayout swipeRefreshLayout;
    public static SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();



        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        sharedPreferences = this.getSharedPreferences("com.example.dell.gymkhanaapp",MODE_PRIVATE);
        SharedPreferences wmbPreference = PreferenceManager.getDefaultSharedPreferences(this);
        boolean isFirstRun = wmbPreference.getBoolean("FIRSTRUN", true);
        if (isFirstRun)
        {
            sharedPreferences.edit().putString("societySelected","false").apply();
            sharedPreferences.edit().putString("link","null").apply();
            sharedPreferences.edit().putString("home","null").apply();
            SharedPreferences.Editor editor = wmbPreference.edit();
            editor.putBoolean("FIRSTRUN", false);
            editor.commit();
        }


        Button button1 = (Button)findViewById(R.id.hbutton1);
        Button button2 = (Button)findViewById(R.id.hbutton2);
        Button button3 = (Button)findViewById(R.id.hbutton3);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webView.loadUrl("https://www.facebook.com/director.nitsilchar/");
                home = "https://m.facebook.com/director.nitsilchar/";
                setTitlebarText("Director Page");
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                webView.loadUrl("https://www.facebook.com/groups/gymkhana.nits/");
                home = "https://m.facebook.com/groups/gymkhana.nits/";
                setTitlebarText("Gymkhana");
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String check = sharedPreferences.getString("societySelected",null);
                if(check.equals("false")){
                    Intent intent = new Intent(getApplicationContext(),SocietyActivity.class);
                    startActivity(intent);
                }else{
                    String link = sharedPreferences.getString("link",null);
                    String linkHome = sharedPreferences.getString("home",null);
                    Log.i("URLhome",home);
                    webView.loadUrl(link);
                    home = linkHome;
                    setTitlebarText("Society");
                }
            }
        });




        Button button = (Button) findViewById(R.id.button);
        button .setFocusable(false);
        button .setFocusableInTouchMode(false);
        button .setClickable(true);
        swipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.refreshLayout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                webView.reload();
            }
        });



        webView = (WebView)findViewById(R.id.webView);


        WebSettings webSettings = webView.getSettings();
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setBuiltInZoomControls(true);
        webView.getSettings().setDisplayZoomControls(false);
        webView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        webView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        webView.getSettings().setAppCacheEnabled(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        webSettings.setUseWideViewPort(true);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // chromium, enable hardware acceleration
            webView.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        } else {
            // older android version, disable hardware acceleration
            webView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        }


        webView.loadUrl("https://www.facebook.com/groups/gymkhana.nits/");
        setTitlebarText("Gymkhana");
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {

                swipeRefreshLayout.setRefreshing(true);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {

                }
                super.onPageStarted(view, url, favicon);
            }

            @Override
            public void onPageFinished(WebView view, String url) {

                webViewLoad = 1;
                swipeRefreshLayout.setRefreshing(false);
                super.onPageFinished(view, url);
            }
        });


        webView.setWebChromeClient(new WebChromeClient());
        home="https://m.facebook.com/groups/gymkhana.nits/";

        if (android.os.Build.VERSION.SDK_INT >= 21) {
            CookieManager.getInstance().setAcceptThirdPartyCookies(webView, true);
        }else {
            CookieManager.getInstance().setAcceptCookie(true);
        }


    }

    @Override
    public void onBackPressed() {

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
            String comp = home;
            String url = webView.getUrl();

            Log.i("URL",url);

            if(!home.equals(url)){
                if (webView.canGoBack()) {
                    webView.goBack();
                }
            }
        if(webViewLoad == 0){super.onBackPressed();}

        else if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.society) {
            String check = sharedPreferences.getString("societySelected",null);
            if(check.equals("false")){
                Intent intent = new Intent(getApplicationContext(),SocietyActivity.class);
                startActivity(intent);
            }else{
                String link = sharedPreferences.getString("link",null);
                String linkHome = sharedPreferences.getString("home",null);
                Log.i("URLhome",home);
                webView.loadUrl(link);
                home = linkHome;
                setTitlebarText("Society");
            }
            return true;
        }
        if (id == R.id.exit) {
            Intent startMain = new Intent(Intent.ACTION_MAIN);
            startMain.addCategory(Intent.CATEGORY_HOME);
            startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(startMain);
        }
        if (id == R.id.portfolio){
            Intent intent = new Intent(getApplicationContext(),PortfolioActivity.class);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.sportsClub) {
            webView.loadUrl("https://www.facebook.com/groups/884300704925948/");
            home = "https://m.facebook.com/groups/884300704925948/";
            setTitlebarText("Sports Club");
        } else if (id == R.id.photography) {
            webView.loadUrl("https://www.facebook.com/groups/123175331103402/");
            home = "https://m.facebook.com/groups/123175331103402/";
            setTitlebarText("Photography Club");
        } else if (id == R.id.nav_share) {
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType("text/plain");
            String shareBody = shareMsg;
            String shareSub = "Sub";
            intent.putExtra(Intent.EXTRA_SUBJECT,shareSub);
            intent.putExtra(Intent.EXTRA_TEXT,shareBody);
            startActivity(Intent.createChooser(intent,"Share using"));

        } else if (id == R.id.about) {
            Intent intent = new Intent(getApplicationContext(),AboutActivity.class);
            startActivity(intent);

        } else if (id == R.id.musicClub){
            webView.loadUrl("https://www.facebook.com/groups/208556289185219/");
            home = "https://m.facebook.com/groups/208556289185219/";
            setTitlebarText("Music Club");
        } else if (id == R.id.danceClub){
            webView.loadUrl("https://www.facebook.com/groups/298845613650547/");
            home = "https://m.facebook.com/groups/298845613650547/";
            setTitlebarText("Dance Club");
        } else if (id == R.id.dramaticsClub){
            webView.loadUrl("https://www.facebook.com/groups/571031136260933/");
            home = "https://m.facebook.com/groups/571031136260933/";
            setTitlebarText("Dramatics Club");
        } else if (id == R.id.literaryClub){
            webView.loadUrl("https://www.facebook.com/groups/1589104411336298/");
            home = "https://m.facebook.com/groups/1589104411336298/";
            setTitlebarText("Literary Club");
        } else if (id == R.id.gymkhana){
            webView.loadUrl("https://www.facebook.com/groups/gymkhana.nits/");
            home = "https://m.facebook.com/groups/gymkhana.nits/";
            setTitlebarText("Gymkhana");
        } else if (id == R.id.directorPage){
            webView.loadUrl("https://www.facebook.com/director.nitsilchar/");
            home = "https://m.facebook.com/director.nitsilchar/";
            setTitlebarText("Director Page");
        } else if (id == R.id.illuminits){
            webView.loadUrl("https://www.facebook.com/Illuminits/");
            home = "https://m.facebook.com/Illuminits/";
            setTitlebarText("Illuminits");
        } else if (id == R.id.tecnoesis){
            webView.loadUrl("https://www.facebook.com/tecnoesis.nits/");
            home = "https://m.facebook.com/tecnoesis.nits/";
            setTitlebarText("Tecnoesis");
        } else if (id == R.id.incand){
            webView.loadUrl("https://www.facebook.com/incandescencenit/");
            home = "https://m.facebook.com/incandescencenit/";
            setTitlebarText("Incandescence");
        } else if (id == R.id.obiettivo){
            webView.loadUrl("https://www.facebook.com/PhotographyClub.NITS/");
            home = "https://m.facebook.com/PhotographyClub.NITS/";
            setTitlebarText("Obiettivo");
        } else if (id == R.id.codingclub){
            webView.loadUrl("https://www.facebook.com/groups/CodingClub.NITSilchar/");
            home = "https://m.facebook.com/groups/CodingClub.NITSilchar/";
            setTitlebarText("Coding Club");
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void setTitlebarText(String title){
        setTitle(title);
    }


}
