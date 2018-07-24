package com.sistemas.tempera.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.sistemas.tempera.Data.RetrofitClient;
import com.sistemas.tempera.Data.WebServices;
import com.sistemas.tempera.Models.Login;
import com.sistemas.tempera.R;
import com.sistemas.tempera.Responses.LoginResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A login screen that offers login via email/password.
 */

public class LoginActivity extends AppCompatActivity {

    private TextView txtUsername;
    private TextView txtPassword;
    private Button btnLogin;

    private Button btnRegisterUser;

    ProgressDialog progress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        SharedPreferences prefs = getSharedPreferences("Auth", MODE_PRIVATE);
        Boolean is_login = prefs.getBoolean("is_login", false);

        if (is_login != null) {
            if(is_login){
                //show to Home
                showToHome();
            }
        }


        txtUsername = (TextView) findViewById(R.id.txtUsername);
        txtPassword = (TextView) findViewById(R.id.txtPassword);

        System.out.println(txtUsername.getText());
        System.out.println(txtPassword.getText());

        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnRegisterUser = (Button) findViewById(R.id.btnRegisterUser);

        progress = new ProgressDialog(this);
        progress.setTitle("Ingresando...");
        progress.setMessage("Por favor espere...");

        btnLogin.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = txtUsername.getText().toString();
                String password = txtPassword.getText().toString();


                if(username.isEmpty() || password.isEmpty()){
                    showLoginError("Verifique que los campos no estén vacios");
                    return;
                }

                progress.show();

                Login data = new Login(username, password);

                WebServices webServices = RetrofitClient.getConfig().create(WebServices.class);

                Call<LoginResponse> login = webServices.login(data);

                login.enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        progress.dismiss();
                        if (response.code() == 200) {
                            LoginResponse loginResponse = response.body();

                            if(loginResponse.getSuccess()){
                                SharedPreferences.Editor editor = getSharedPreferences("Auth", MODE_PRIVATE).edit();
                                editor.putBoolean("is_login", true);
                                editor.apply();
                                showToHome();
                            }
                        } else {

                            Log.i("_tempera",String.valueOf(response.code()));
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        //Log.d(Constant.DEV_LOG_SERVICE, "call services error");
                        progress.dismiss();
                        Log.i("_tempera",":(");
                    }
                });


            }
        });


        btnRegisterUser.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showToRegister();
            }
        });

    }


    private void showToHome() {
        //Intent i = new Intent(getApplicationContext(), MainActivity.class);
        //startActivity(i);
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    private void showToRegister() {
        Intent i = new Intent(getApplicationContext(), RegisterActivity.class);
        startActivity(i);
    }

    private void showLoginError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
    }




}

