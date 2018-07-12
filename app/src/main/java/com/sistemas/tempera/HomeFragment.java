package com.sistemas.tempera;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.sistemas.tempera.Data.RetrofitClient;
import com.sistemas.tempera.Data.WebServices;
import com.sistemas.tempera.Models.Cultivo;
import com.sistemas.tempera.Responses.CultivoResponse;
import com.sistemas.tempera.Responses.CultivosResponse;
import com.sistemas.tempera.adapters.CultivoAdapter;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;



public class HomeFragment extends Fragment {

    Cultivo cultivo;
    public List<Cultivo> cultivos;

    TextView txtTempera;
    TextView txtCultivo;

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

        //txtTempera = (TextView) rootView.findViewById(R.id.txtTempera);
        txtCultivo = (TextView) rootView.findViewById(R.id.txtActiveCultivo);

        return rootView;

    }


    private void initView() {

        //WebServices webServices = RetrofitClient.getConfig().create(WebServices.class);
        //Call<CultivoResponse> getCultivo = webServices.getActiveCultivo();


//        getCultivo.enqueue(new Callback<CultivoResponse>() {
//            @Override
//            public void onResponse(Call<CultivoResponse> call, Response<CultivoResponse> response) {
//                //progress.dismiss();
////                if (response.code() == 200) {
////                    CultivoResponse cultivoResponse = response.body();
////
////                    if(cultivoResponse.getSuccess()){
////                        cultivo = cultivoResponse.getCultivo();
////                        //txtCultivo.setText(cultivo.getNombre());
////
////                    }
////                } else {
////
////                    Log.i("_tempera",String.valueOf(response.code()));
////                }
//            }
//
//            @Override
//            public void onFailure(Call<CultivoResponse> call, Throwable t) {
//                Log.i("_tempera",":(");
//            }
//        });

        WebServices webServices = RetrofitClient.getConfig().create(WebServices.class);
        Call<CultivosResponse> getCultivos = webServices.cultivos();


        getCultivos.enqueue(new Callback<CultivosResponse>() {
            @Override
            public void onResponse(Call<CultivosResponse> call, Response<CultivosResponse> response) {
                //progress.dismiss();
                if (response.code() == 200) {
                    CultivosResponse cultivoResponse = response.body();

                    if(cultivoResponse.getSuccess()){
                        cultivos = cultivoResponse.getCustivos();

                        for (Cultivo c : cultivos) {
                            // fruit is an element of the `fruits` array.
                            if(c.getActive() == true){
                                txtCultivo.setText(c.getNombre());
                            }
                        }

                    }
                } else {

                    Log.i("_tempera",String.valueOf(response.code()));
                }
            }

            @Override
            public void onFailure(Call<CultivosResponse> call, Throwable t) {
                Log.i("_tempera",":(");
            }
        });
    }
}
