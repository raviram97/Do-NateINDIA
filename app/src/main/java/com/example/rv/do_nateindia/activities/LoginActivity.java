package com.example.rv.do_nateindia.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;

import com.example.rv.do_nateindia.R;
import com.example.rv.do_nateindia.db.DatabaseHelper;
import com.example.rv.do_nateindia.helper.InputValidation;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    //Code for Taking input and validating them.
    private final AppCompatActivity activity = LoginActivity.this;

    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutPassword;

    private TextInputEditText textInputEditTextEmail;
    private TextInputEditText textInputEditTextPassword;

    private AppCompatButton button_login;
    private AppCompatTextView text_signUp;

    private InputValidation inputValidation;
    private DatabaseHelper databaseHelper;

    private DrawerLayout mDrawerLayout;


    //Operation of ActionBar(Display, ClickEvent, Show)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //ActionBar
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDrawerLayout = (DrawerLayout) findViewById(R.id.DrawerLayout);

        initViews();
        initListeners();
        initObjects();
    }
/*
    //Handling the ClickEvent for ActionBar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return mToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }


        @Override
        protected void onCreate(Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            getSupportActionBar().hide();

            initViews();
            initListeners();
            initObjects();
        }
    */

    private void initViews(){
        textInputLayoutEmail = (TextInputLayout) findViewById(R.id.textInputLayoutEmail);
        textInputLayoutPassword = (TextInputLayout) findViewById(R.id.textInputLayoutPassword);

        textInputEditTextEmail = (TextInputEditText) findViewById(R.id.textInputEditTextEmail);
        textInputEditTextPassword = (TextInputEditText) findViewById(R.id.textInputEditTextPassword);

        button_login = (AppCompatButton) findViewById(R.id.button_login);

        text_signUp = (AppCompatTextView) findViewById(R.id.signUp);
    }

    private void initListeners(){
        button_login.setOnClickListener(this);
        text_signUp.setOnClickListener(this);
    }

    private void initObjects(){
        databaseHelper = new DatabaseHelper(activity);
        inputValidation = new InputValidation(activity);
    }

    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.button_login:
                verifyFromSQLite();
                break;
            case R.id.signUp:
                Intent intentRegister = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intentRegister);
                break;
        }
    }

    private void verifyFromSQLite(){
        if(inputValidation.isInputFilled(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_email)))
            return;
        if(inputValidation.isInputEmail(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_email)))
            return;
        if(inputValidation.isInputFilled(textInputEditTextPassword, textInputLayoutPassword, getString(R.string.error_password)))
            return;

        if(databaseHelper.checkUser(textInputEditTextEmail.getText().toString().trim())){

            if(databaseHelper.checkUser(textInputEditTextEmail.getText().toString().trim(),
                    textInputEditTextPassword.getText().toString().trim())){
                Intent accountsIntent = new Intent(activity, UsersActivity.class);
                accountsIntent.putExtra("EMAIL", textInputEditTextEmail.getText().toString().trim());
                emptyInputEditText();
                startActivity(accountsIntent);
            }
            else
                Snackbar.make(mDrawerLayout, getString(R.string.error_passwordMismatch), Snackbar.LENGTH_LONG).show();
        }
        else
            Snackbar.make(mDrawerLayout, getString(R.string.notregistered), Snackbar.LENGTH_LONG).show();

    }

    private void emptyInputEditText(){
        textInputEditTextEmail.setText(null);
        textInputEditTextPassword.setText(null);
    }
}
