package com.sirio.remindme.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.facebook.login.LoginManager;
import com.sirio.remindme.R;
import com.sirio.remindme.utils.SharedPreference;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private ImageView imageView;
    private TextView textView;

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

        if(SharedPreference.isLoggedIn(getApplicationContext())) {
            ((NavigationView) findViewById(R.id.nav_view)).getMenu().findItem(R.id.login).setVisible(false);
        }else{
            ((NavigationView) findViewById(R.id.nav_view)).getMenu().findItem(R.id.logout).setVisible(false);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        // Changing profile pic fron navigator header
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View hView =  navigationView.getHeaderView(0);

        imageView = hView.findViewById(R.id.profile_pic);
        textView = hView.findViewById(R.id.user_name);

        String id = SharedPreference.getId(getApplicationContext());
        String user = SharedPreference.getUsername(getApplicationContext());
        String imageUrl = "https://graph.facebook.com/" + id + "/picture?type=normal";

        textView.setText(user);
        Glide.with(MainActivity.this).load(imageUrl).into(imageView);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            FragmentManager fm = getSupportFragmentManager();
            if (fm.getBackStackEntryCount() > 0) {
                Log.i("MainActivity", "popping backstack");
                fm.popBackStack();
            } else {
                Log.i("MainActivity", "nothing on backstack, calling super");
                this.moveTaskToBack(true);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

/*        boolean loggedIn = !SharedPreference.getUsername(getApplicationContext()).isEmpty();
        getMenuInflater().inflate(R.menu.main, menu);

        menu.findItem(R.id.login).setEnabled(!loggedIn);
        menu.findItem(R.id.logout).setEnabled(loggedIn);
*/        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        /*
        if (id == R.id.logout) {
            SharedPreference.clear(getApplicationContext());
            Toast.makeText(MainActivity.this,"User logged out", Toast.LENGTH_LONG).show();
            Glide.with(MainActivity.this).load("@mipmap/ic_launcher").into(circleImageView);
            startActivity(new Intent(MainActivity.this, LoginActivity.class));
            return true;
        }
        */

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id){
            case R.id.login:
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                break;
            case R.id.logout:
                LoginManager.getInstance().logOut();
                SharedPreference.clear(getApplicationContext());
                Toast.makeText(MainActivity.this,"User logged out", Toast.LENGTH_LONG).show();
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                break;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
