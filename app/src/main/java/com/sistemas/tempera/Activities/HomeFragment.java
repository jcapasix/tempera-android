package com.sistemas.tempera.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sistemas.tempera.Data.RetrofitClient;
import com.sistemas.tempera.Data.WebServices;
import com.sistemas.tempera.Models.Cultivo;
import com.sistemas.tempera.Models.Temperatura;
import com.sistemas.tempera.R;
import com.sistemas.tempera.Responses.TemperaturaResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class HomeFragment extends Fragment {

    public Cultivo cultivo;
    public Temperatura temperatura;

    TextView txtTempera;
    TextView txtCultivo;
    TextView txtTemperaMax;


    private Boolean isCallService = true;
    private Integer TempNow = 0;
    private Integer TempBand = 0;


    public HomeFragment(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    @Override
    public void onResume() {
        super.onResume();
        initView();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);

        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        txtTempera = (TextView) rootView.findViewById(R.id.txtTempera);
        txtTemperaMax = (TextView) rootView.findViewById(R.id.txtActiveTemperatura);
        txtCultivo = (TextView) rootView.findViewById(R.id.txtActiveCultivo);


        return rootView;

    }

    public void mostrarNotificacion(View view, String name) {

        // Intent to open your activity
        Intent intent = new Intent(getContext(), MainActivity.class);
        NotificationUtils.notificatePush(getContext(), 001, "Ticker", "Temperatura Máxima - "+name, "La temperatura máxima ha sido superada.", intent);

    }


    private void initView() {

        WebServices webServices = RetrofitClient.getConfig().create(WebServices.class);
        Call<TemperaturaResponse> getTemperatura = webServices.getTemperatura();


        getTemperatura.enqueue(new Callback<TemperaturaResponse>() {
            @Override
            public void onResponse(Call<TemperaturaResponse> call, Response<TemperaturaResponse> response) {
                //progress.dismiss();
                if (response.code() == 200) {
                    TemperaturaResponse tempResponse = response.body();

                    if(tempResponse.getSuccess()){
                        cultivo = tempResponse.getCultivo();
                        temperatura = tempResponse.getTemperatura();

                        txtCultivo.setText(cultivo.getNombre());
                        txtTempera.setText(Integer.toString(temperatura.getValue()) + "°");
                        txtTemperaMax.setText("Temp Máxima: " + cultivo.getTemperaturaMaxString()+ "°");


                        TempNow = temperatura.getValue();

                        if ((TempNow > cultivo.getTemperaturaMax()) && (TempNow != TempBand)){

                            mostrarNotificacion(getView(), cultivo.getNombre());
                            TempBand = TempNow;
                            System.out.println("mostrarNotificacion ---> ");
                        }

                        if (isCallService){
                            //initView();
                        }

                    }
                } else {

                    Log.i("_tempera",String.valueOf(response.code()));
                }
            }

            @Override
            public void onFailure(Call<TemperaturaResponse> call, Throwable t) {
                Log.i("_tempera",":(");
            }
        });
    }
}
