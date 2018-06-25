package com.sistemas.tempera;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import Data.RetrofitClient;
import Data.WebServices;
import Models.Cultivo;
import Models.Login;
import Request.RequestCultivo;
import Resourses.Utils;
import Responses.BasicResponse;
import Responses.LoginResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateCultivoActivity extends AppCompatActivity {

    String id;
    TextView nombre;
    TextView tempMax;
    TextView tempMin;
    TextView fechaInicial;
    TextView fechaFinal;

    Button btnGuardar;
    Button btnEliminar;

    ProgressDialog progress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_cultivo);


        progress = new ProgressDialog(this);
        progress.setMessage("Por favor espere...");

        setTitle("Actualizar Cultivo");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        nombre = (TextView) findViewById(R.id.txtNombreCultivoUpdate);
        tempMax = (TextView) findViewById(R.id.txtTemperaturaMaxCultivoUpdate);
        tempMin = (TextView) findViewById(R.id.txtTemperaturaMinCultivoUpdate);
        fechaInicial = (TextView) findViewById(R.id.txtFechaInicioCultivoUpdate);
        fechaFinal = (TextView) findViewById(R.id.txtFechaFinCultivoUpdate);

        btnGuardar = (Button) findViewById(R.id.btnGuardarUpdate);
        btnEliminar = (Button) findViewById(R.id.btnEliminarUpdate);


        Bundle extras = getIntent().getExtras();
        if(extras == null) {
            nombre= null;
        } else {

            id = extras.getString("id");
            nombre.setText(extras.getString("nombre"));
            tempMax.setText(Integer.toString(extras.getInt("tempMax")));
            tempMin.setText(Integer.toString(extras.getInt("tempMin")));
            fechaInicial.setText(Utils.getDate(extras.getString("fechaInicial")));
            fechaFinal.setText(Utils.getDate(extras.getString("fechaFinal")));

        }

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                actualizarCultivo();
            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eliminarCultivo();
            }
        });

    }

    public void actualizarCultivo(){

        progress.setTitle("Actualizando...");

        final Cultivo cultivo = new Cultivo(id, nombre.getText().toString(), Integer.parseInt(tempMax.getText().toString()), Integer.parseInt(tempMin.getText().toString()), fechaInicial.getText().toString(),fechaFinal.getText().toString());


        AlertDialog alertDialog = new AlertDialog.Builder(UpdateCultivoActivity.this).create();
        alertDialog.setTitle("Confirmación");
        alertDialog.setMessage("Está seguro que desea actualizar el cultivo?");
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Aceptar",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        WebServices webServices = RetrofitClient.getConfig().create(WebServices.class);

                        Call<BasicResponse> updateCultivo = webServices.updateCultivo(new RequestCultivo(cultivo));

                        updateCultivo.enqueue(new Callback<BasicResponse>() {
                            @Override
                            public void onResponse(Call<BasicResponse> call, Response<BasicResponse> response) {
                                progress.dismiss();
                                if (response.code() == 200) {
                                    BasicResponse deleteCultivo = response.body();

                                    if(deleteCultivo.getSuccess()){
                                        onSupportNavigateUp();
                                    }
                                } else {

                                    Log.i("_banner_error",String.valueOf(response.code()));
                                }
                            }

                            @Override
                            public void onFailure(Call<BasicResponse> call, Throwable t) {
                                //Log.d(Constant.DEV_LOG_SERVICE, "call services error");
                                Log.i("_banner_error",":(");
                            }
                        });

                        dialog.dismiss();
                    }
                });

        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancelar",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    public void eliminarCultivo(){

        progress.setTitle("Eliminando...");

        AlertDialog alertDialog = new AlertDialog.Builder(UpdateCultivoActivity.this).create();
        alertDialog.setTitle("Confirmación");
        alertDialog.setMessage("Está seguro que desea eliminar el cultivo?");
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Aceptar",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        progress.show();

                        WebServices webServices = RetrofitClient.getConfig().create(WebServices.class);

                        Call<BasicResponse> deleteCultivo = webServices.deleteCultivo(id);

                        deleteCultivo.enqueue(new Callback<BasicResponse>() {
                            @Override
                            public void onResponse(Call<BasicResponse> call, Response<BasicResponse> response) {
                                progress.dismiss();
                                if (response.code() == 200) {
                                    BasicResponse deleteCultivo = response.body();

                                    if(deleteCultivo.getSuccess()){
                                        onSupportNavigateUp();
                                    }
                                    else {
                                        showError("Ocurrió un problema al conectarse con el servidor, por favor intenta nuevamente.");
                                    }
                                } else {

                                    Log.i("_tempera",String.valueOf(response.code()));
                                }
                            }

                            @Override
                            public void onFailure(Call<BasicResponse> call, Throwable t) {
                                //Log.d(Constant.DEV_LOG_SERVICE, "call services error");
                                progress.dismiss();
                                Log.i("_tempera",":(");
                            }
                        });

                        dialog.dismiss();
                    }
                });

        alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Cancelar",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


    private void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show();
    }




}
