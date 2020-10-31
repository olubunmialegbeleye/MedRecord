package com.example.medrecord;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;

public class HealthInformationFragment extends Fragment{
    Button buttonHealthInfoCreate;
    Spinner spinnerBG, spinnerGenotype;
    RadioGroup radioGroupBG, radioGroupGenotype;
    EditText editTextBPSystolic, editTextBPDiastolic, editTextWeight, editTextHeight;
    PatientInfoInterface toNewRecordActivity;

    public HealthInformationFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        try{
            toNewRecordActivity = (PatientInfoInterface) activity;
        }
        catch (ClassCastException e){
            throw new ClassCastException(activity.toString() + " must implement PersonalInfoInterface");
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_health_information, container, false);
        initComponents(view);
        buttonHealthInfoCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logData();
                toNewRecordActivity.createPatientinDB();
                startActivity(new Intent((Activity) HealthInformationFragment.this.getHost(), RecordManagerActivity.class));
                Activity parent = (Activity) HealthInformationFragment.this.getHost();
                parent.finish();
            }
        });
        return view;
    }
    public void initComponents(View view){
        buttonHealthInfoCreate = (Button) view.findViewById(R.id.buttonHealthInfoCreate);
        spinnerBG = (Spinner) view.findViewById(R.id.spinnerBG);
        spinnerGenotype = (Spinner) view.findViewById(R.id.spinnerGenotype);
        editTextBPSystolic = (EditText) view.findViewById(R.id.editTextBPSystolic);
        editTextBPDiastolic = (EditText) view.findViewById(R.id.editTextBPDiastolic);
        editTextWeight = (EditText) view.findViewById(R.id.editTextWeight);
        editTextHeight = (EditText) view.findViewById(R.id.editTextHeight);

        ArrayAdapter<CharSequence> spinnerBGAdapter =ArrayAdapter.createFromResource((Activity) this.getHost(), R.array.BG_array, android.R.layout.simple_spinner_item);
        spinnerBGAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerBG.setAdapter(spinnerBGAdapter);

        ArrayAdapter<CharSequence> spinnerGenotypeAdapter =ArrayAdapter.createFromResource((Activity) this.getHost(), R.array.genotype_array, android.R.layout.simple_spinner_item);
        spinnerGenotypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerGenotype.setAdapter(spinnerGenotypeAdapter);
    }
    public void logData(){
        toNewRecordActivity.setBP(editTextBPSystolic.getText().toString(), editTextBPDiastolic.getText().toString());
        toNewRecordActivity.setWeight(editTextWeight.getText().toString());
        toNewRecordActivity.setHeight(editTextHeight.getText().toString());
        switch (spinnerBG.getSelectedItemPosition()){
            case 0: //A B AB O
                toNewRecordActivity.setBG(Patient.BG.A);
            case 1:
                toNewRecordActivity.setBG(Patient.BG.B);
            case 2:
                toNewRecordActivity.setBG(Patient.BG.AB);
            case 3:
                toNewRecordActivity.setBG(Patient.BG.O);
        }
        switch (spinnerGenotype.getSelectedItemPosition()){
            case 0: //AA AS AC SS
                toNewRecordActivity.setGenotype(Patient.GENOTYPE.AA);
            case 1:
                toNewRecordActivity.setGenotype(Patient.GENOTYPE.AS);
            case 2:
                toNewRecordActivity.setGenotype(Patient.GENOTYPE.AC);
            case 3:
                toNewRecordActivity.setGenotype(Patient.GENOTYPE.SS);
        }
    }
}