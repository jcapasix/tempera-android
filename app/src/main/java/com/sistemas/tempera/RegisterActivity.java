package com.sistemas.tempera;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import Data.RetrofitClient;
import Data.WebServices;
import Models.Login;
import Request.RequestRegister;
import Responses.BasicResponse;
import Responses.LoginResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    Button register;

    TextView txtUsername;
    TextView txtPassword;
    TextView txtRepeatPassword;

    ProgressDialog progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        txtUsername = (TextView) findViewById(R.id.txtUsernameRegister);
        txtPassword = (TextView) findViewById(R.id.txtPasswordRegister);
        txtRepeatPassword = (TextView) findViewById(R.id.txtRepeatPasswordRegister);

        register = (Button) findViewById(R.id.btnRegister);

        progress = new ProgressDialog(this);
        progress.setTitle("Registrando...");
        progress.setMessage("Por favor espere...");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String username = txtUsername.getText().toString();
                String password = txtPassword.getText().toString();
                String repeatPassword = txtRepeatPassword.getText().toString();

                if(username.isEmpty() || password.isEmpty() || password.isEmpty()){
                    showError("Verifique que los campos no estén vacios");
                    return;
                }

                if(!password.equals(repeatPassword)){
                    showError("Las contraseñas no coinciden");
                    return;
                }

                progress.show();

                RequestRegister data = new RequestRegister(username, password, true);

                WebServices webServices = RetrofitClient.getConfig().create(WebServices.class);

                Call<BasicResponse> login = webServices.register(data);

                login.enqueue(new Callback<BasicResponse>() {
                    @Override
                    public void onResponse(Call<BasicResponse> call, Response<BasicResponse> response) {
                        progress.dismiss();

                        BasicResponse registerResponse = response.body();

                        if (response.code() == 201) {


                            if(registerResponse.getSuccess()){
                                onSupportNavigateUp();
                            }
                            else{
                                showError("Ocurrió un problema al conectarse con el servidor, por favor intenta nuevamente.");
                            }
                        }
                        else if(response.code() == 200){
                            showError(registerResponse.getMessage());
                        }

                        else {

                            Log.i("_tempera",String.valueOf(response.code()));
                        }
                    }

                    @Override
                    public void onFailure(Call<BasicResponse> call, Throwable t) {
                        //Log.d(Constant.DEV_LOG_SERVICE, "call services error");
                        progress.dismiss();
                        showError("El usuario ya existe.");
                    }
                });
            }
        });
    }


    private void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
