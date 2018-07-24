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
import com.sistemas.tempera.Models.Reporte;
import com.sistemas.tempera.R;
import com.sistemas.tempera.Responses.CultivosResponse;
import com.sistemas.tempera.Responses.ReportesResponse;
import com.sistemas.tempera.adapters.CultivoAdapter;
import com.sistemas.tempera.adapters.ReporteAdapter;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ReporteFragment extends Fragment {

    public List<Reporte> reportes;
    protected RecyclerView reportesRecyclerView;
    protected ReporteAdapter reporteAdapter;

    public ReporteFragment(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDataset();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_reporte, container, false);
        reportesRecyclerView = (RecyclerView) rootView.findViewById(R.id.rv_reportes);
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
        if (reportesRecyclerView.getLayoutManager() != null) {
            scrollPosition = ((GridLayoutManager) reportesRecyclerView.getLayoutManager())
                    .findFirstCompletelyVisibleItemPosition();
        }

        reportesRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 1));
        reportesRecyclerView.scrollToPosition(scrollPosition);
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    private void initDataset() {

        WebServices webServices = RetrofitClient.getConfig().create(WebServices.class);
        Call<ReportesResponse> getReportes = webServices.reportes();


        getReportes.enqueue(new Callback<ReportesResponse>() {
            @Override
            public void onResponse(Call<ReportesResponse> call, Response<ReportesResponse> response) {
                //progress.dismiss();
                if (response.code() == 200) {
                    ReportesResponse cultivoResponse = response.body();

                    if(cultivoResponse.getSuccess()){
                        reportes = cultivoResponse.getReportes();
                        reporteAdapter = new ReporteAdapter(reportes);
                        reportesRecyclerView.setAdapter(reporteAdapter);
                    }
                } else {

                    Log.i("_tempera",String.valueOf(response.code()));
                }
            }

            @Override
            public void onFailure(Call<ReportesResponse> call, Throwable t) {
                Log.i("_tempera",":(");
            }
        });
    }

}

