package com.sistemas.tempera.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.sistemas.tempera.R;

import static android.content.Context.MODE_PRIVATE;

public class MoreFragment extends Fragment {


    public MoreFragment(){

    }



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);

        View rootView = inflater.inflate(R.layout.fragment_more, container, false);

        Button logout = (Button) rootView.findViewById(R.id.btnLogout);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences.Editor editor =  getActivity().getSharedPreferences("Auth",MODE_PRIVATE).edit();
                editor.putBoolean("is_login", false);
                editor.apply();
                System.out.println("LOGOUT");

                Intent intent=new Intent(getActivity().getApplicationContext(), LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                getActivity().finish();
            }
        });



        return rootView;

    }


}
