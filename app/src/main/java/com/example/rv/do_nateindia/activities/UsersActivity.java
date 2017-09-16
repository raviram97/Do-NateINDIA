package com.example.rv.do_nateindia.activities;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rv.do_nateindia.R;

public class UsersActivity extends AppCompatActivity implements View.OnClickListener {

    private ActionBarDrawerToggle mToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.users_page);

        String emailFromIntent = getIntent().getStringExtra("EMAIL");
        String toast = getString(R.string.toast);

        TextView textViewName = (TextView) findViewById(R.id.text1);
        String welcome = getString(R.string.welcome, emailFromIntent);
        textViewName.setText(welcome);


        //ActionBar
        Toolbar mToolbar = (Toolbar) findViewById(R.id.nav_action);
        setSupportActionBar(mToolbar);

        DrawerLayout mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);

        mDrawerLayout.addDrawerListener(mToggle);
        mToggle.syncState();

        if (getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        else
            Toast.makeText(UsersActivity.this, toast, Toast.LENGTH_LONG).show();


    }

    //ActionBar Button
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return mToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {

    }
}
