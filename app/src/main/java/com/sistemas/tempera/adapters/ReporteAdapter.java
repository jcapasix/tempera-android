package com.sistemas.tempera.adapters;

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.sistemas.tempera.Models.Reporte;
import com.sistemas.tempera.R;
import com.sistemas.tempera.UpdateCultivoActivity;

import java.util.List;

import com.sistemas.tempera.Models.Cultivo;
import com.sistemas.tempera.Resourses.Utils;

/**
 * Created by jcapasix on 23/06/18.
 */

public class ReporteAdapter extends RecyclerView.Adapter<ReporteAdapter.ViewHolder> {

    private static final String TAG = "ReporteAdapter";

    private List<Reporte> mDataSet;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView TextViewCodigo;
        private final TextView TextViewNombre;
        private final TextView TextViewAlerta;
        private final TextView TextViewTemperatura;
        private final CardView CardViewReporte;
        private final FrameLayout FrameActive;


        public TextView getTextViewCodigo() {
            return TextViewCodigo;
        }

        public TextView getTextViewNombre() {
            return TextViewNombre;
        }

        public TextView getTextViewAlerta() {
            return TextViewAlerta;
        }

        public TextView getTextViewTemperatura() {
            return TextViewTemperatura;
        }

        public CardView getCardViewReporte() {
            return CardViewReporte;
        }

        public FrameLayout getFrameActive() {
            return FrameActive;
        }



        public ViewHolder(View v) {
            super(v);

            this.TextViewCodigo =  (TextView) v.findViewById(R.id.txtCodigoReporte);
            this.TextViewNombre =  (TextView) v.findViewById(R.id.txtNombreCultivoReporte);
            this.TextViewAlerta =  (TextView) v.findViewById(R.id.txtAlertaReporte);
            this.TextViewTemperatura = (TextView) v.findViewById(R.id.txtTemperaturaReporte);
            this.CardViewReporte = (CardView) v.findViewById(R.id.cv_reporte);
            this.FrameActive = (FrameLayout) v.findViewById(R.id.activeFrame);
        }
    }

    public ReporteAdapter(List<Reporte> dataSet) {
        mDataSet = dataSet;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view.
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.reporte_card_item, viewGroup, false);
        return new ViewHolder(v);



    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        Log.d(TAG, "Element " + position + " set.");
        viewHolder.getTextViewCodigo().setText("C: " + position);
        viewHolder.getTextViewNombre().setText("Nombre Cultivo: " + mDataSet.get(position).getCultivo().getNombre());
        viewHolder.getTextViewTemperatura().setText("Temperatura Promedio: " + mDataSet.get(position).getTemperatura());
        viewHolder.getTextViewAlerta().setText("Número de Alertas: " + mDataSet.get(position).getAlertas());
    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }
}
