package manu.apps.evote.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import manu.apps.evote.R;
import manu.apps.evote.classes.GlobalVariables;

public class ProfileFragment extends Fragment {

    TextView tvEmail, tvIdNumber, tvCounty, tvConstituency;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.profile_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvEmail = view.findViewById(R.id.tv_email);
        tvIdNumber = view.findViewById(R.id.tv_id_number);
        tvCounty = view.findViewById(R.id.tv_county);
        tvConstituency = view.findViewById(R.id.tv_constituency);

        tvEmail.setText("Email Address: " + GlobalVariables.currentUser.getEmail());
        tvIdNumber.setText("Id Number:  " + (GlobalVariables.currentUser.getIdNumber()));
        tvCounty.setText("County: " + GlobalVariables.currentUser.getCounty());
        tvConstituency.setText("County: " + (GlobalVariables.currentUser.getConstituency()));

    }
}