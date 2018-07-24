package com.sistemas.tempera.Activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.sistemas.tempera.Data.RetrofitClient;
import com.sistemas.tempera.Data.WebServices;
import com.sistemas.tempera.Models.CultivoEntity;
import com.sistemas.tempera.R;
import com.sistemas.tempera.Request.RequestCultivoEntity;
import com.sistemas.tempera.Responses.BasicResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddCultivoActivity extends AppCompatActivity {


    TextView nombre;
    TextView tempMax;
    TextView tempMin;
    TextView fechaInicial;
    TextView fechaFinal;

    Button btnGuardar;
    ProgressDialog progress;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cultivo);
        setTitle("Nuevo Cultivo");

        progress = new ProgressDialog(this);
        progress.setMessage("Por favor espere...");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        nombre = (TextView) findViewById(R.id.txtNombreCultivo);
        tempMax = (TextView) findViewById(R.id.txtTemperaturaMaxCultivo);
        tempMin = (TextView) findViewById(R.id.txtTemperaturaMinCultivo);
        fechaInicial = (TextView) findViewById(R.id.txtFechaInicioCultivo);
        fechaFinal = (TextView) findViewById(R.id.txtFechaFinCultivo);
        btnGuardar = (Button) findViewById(R.id.btnGuardar);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final CultivoEntity cultivo = new CultivoEntity(nombre.getText().toString(), Integer.parseInt(tempMax.getText().toString()), Integer.parseInt(tempMin.getText().toString()), fechaInicial.getText().toString(),fechaFinal.getText().toString());

                WebServices webServices = RetrofitClient.getConfig().create(WebServices.class);

                Call<BasicResponse> updateCultivo = webServices.createCultivo(new RequestCultivoEntity(cultivo));

                updateCultivo.enqueue(new Callback<BasicResponse>() {
                    @Override
                    public void onResponse(Call<BasicResponse> call, Response<BasicResponse> response) {
                        progress.dismiss();
                        if (response.code() == 200) {
                            BasicResponse deleteCultivo = response.body();

                            if(deleteCultivo.getSuccess()){
                                onSupportNavigateUp();
                            }
                            else{
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

            }
        });


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
