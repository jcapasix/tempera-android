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

import com.sistemas.tempera.R;
import com.sistemas.tempera.UpdateCultivoActivity;

import java.util.List;

import com.sistemas.tempera.Models.Cultivo;
import com.sistemas.tempera.Resourses.Utils;

/**
 * Created by jcapasix on 23/06/18.
 */

public class CultivoAdapter extends RecyclerView.Adapter<CultivoAdapter.ViewHolder> {

    private static final String TAG = "CultivoAdapter";

    private List<Cultivo> mDataSet;
    //private String[] mDataSet;

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView TextViewNombre;
        private final TextView TextViewTemperaturaMax;
        private final TextView TextViewTemperaturaMin;
        private final TextView TextViewFechaIncial;
        private final TextView TextViewFechaFinal;
        private final CardView CardViewCultivo;
        private final FrameLayout FrameActive;


        public TextView getTextViewNombre() {
            return TextViewNombre;
        }

        public TextView getTextViewTemperaturaMax() {
            return TextViewTemperaturaMax;
        }

        public TextView getTextViewTemperaturaMin() {
            return TextViewTemperaturaMin;
        }

        public TextView getTextViewFechaIncial() {
            return TextViewFechaIncial;
        }

        public TextView getTextViewFechaFinal() {
            return TextViewFechaFinal;
        }

        public CardView getCardViewCultivo() {
            return CardViewCultivo;
        }

        public FrameLayout getActiveFrame() {
            return FrameActive;
        }

        public ViewHolder(View v) {
            super(v);
            // Define click listener for the ViewHolder's View.

            this.TextViewNombre =  (TextView) v.findViewById(R.id.txtNomnbre);
            this.TextViewTemperaturaMax =  (TextView) v.findViewById(R.id.txtTemperaturaMax);
            this.TextViewTemperaturaMin =  (TextView) v.findViewById(R.id.txtTemperaturaMin);
            this.TextViewFechaIncial = (TextView) v.findViewById(R.id.txtFechaInicial);
            this.TextViewFechaFinal = (TextView) v.findViewById(R.id.txtFechaFinal);
            this.CardViewCultivo = (CardView) v.findViewById(R.id.cv_cultivo);
            this.FrameActive = (FrameLayout) v.findViewById(R.id.activeFrame);
        }
    }

    public CultivoAdapter(List<Cultivo> dataSet) {
        mDataSet = dataSet;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view.
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.cultivo_card_item, viewGroup, false);
        return new ViewHolder(v);



    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {
        Log.d(TAG, "Element " + position + " set.");
        viewHolder.getTextViewNombre().setText("Nombre: " + mDataSet.get(position).getNombre());
        viewHolder.getTextViewTemperaturaMax().setText("Temperatura Máxima: " + mDataSet.get(position).getTemperaturaMaxString());
        viewHolder.getTextViewTemperaturaMin().setText("Temperatura Mínima: "+ mDataSet.get(position).getTemperaturaMinString());
        viewHolder.getTextViewFechaIncial().setText("Fecha Inicial: " + Utils.getDate(mDataSet.get(position).getFechaInicial()));
        viewHolder.getTextViewFechaFinal().setText("Fecha Final: " + Utils.getDate(mDataSet.get(position).getFechaFinal()));
        viewHolder.getActiveFrame().setBackgroundColor(mDataSet.get(position).getColor());



        viewHolder.getCardViewCultivo().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Element " + position + " clicked.");
                Intent i = new Intent(v.getContext(), UpdateCultivoActivity.class);
                i.putExtra("id", mDataSet.get(position).getId());
                i.putExtra("nombre", mDataSet.get(position).getNombre());
                i.putExtra("tempMax", mDataSet.get(position).getTemperaturaMax());
                i.putExtra("tempMin", mDataSet.get(position).getTemperaturaMin());
                i.putExtra("fechaInicial", mDataSet.get(position).getFechaInicial());
                i.putExtra("fechaFinal", mDataSet.get(position).getFechaFinal());

                v.getContext().startActivity(i);
            }
        });


    }

    @Override
    public int getItemCount() {
        return mDataSet.size();
    }
}
