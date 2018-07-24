package com.sistemas.tempera.Activities;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import com.sistemas.tempera.Data.RetrofitClient;
import com.sistemas.tempera.Data.WebServices;
import com.sistemas.tempera.Models.Cultivo;
import com.sistemas.tempera.R;
import com.sistemas.tempera.Responses.CultivosResponse;
import com.sistemas.tempera.adapters.CultivoAdapter;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class CultivosFragment extends Fragment {

    public List<Cultivo> cultivos;
    protected RecyclerView cultivosRecyclerView;
    protected CultivoAdapter cultivosAdapter;

    public CultivosFragment(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataset();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_cultivos, container, false);
        cultivosRecyclerView = (RecyclerView) rootView.findViewById(R.id.rv_cultivos);
        this.setRecyclerViewLayoutManager();

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        initDataset();
    }

    public void setRecyclerViewLayoutManager() {
        int scrollPosition = 0;

        // If a layout manager has already been set, get current scroll position.
        if (cultivosRecyclerView.getLayoutManager() != null) {
            scrollPosition = ((GridLayoutManager) cultivosRecyclerView.getLayoutManager())
                    .findFirstCompletelyVisibleItemPosition();
        }

        cultivosRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1));
        cultivosRecyclerView.scrollToPosition(scrollPosition);
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private void initDataset() {

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
                            cultivosAdapter = new CultivoAdapter(cultivos);
                            cultivosRecyclerView.setAdapter(cultivosAdapter);
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

