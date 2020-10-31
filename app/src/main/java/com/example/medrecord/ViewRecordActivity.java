package com.example.medrecord;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ViewRecordActivity extends AppCompatActivity {
    Patient p;
    TextView textViewName, textViewDOB, textViewOccupation, textViewGender, textViewMaritalStatus,
            textViewPhone, textViewEmail, textViewAddress, textViewCity, textViewState,
            textViewBG, textViewGenotype, textViewWeight, textViewHeight, textViewBP;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_view_record); //DEBUG
        initComponents();
        p = (Patient) getIntent().getSerializableExtra("patient object");
        updatePatientInfo(p);
    }

    public void initComponents(){
        textViewName = (TextView) findViewById(R.id.textViewName);
        textViewDOB = (TextView) findViewById(R.id.textViewDOB);
        textViewOccupation = (TextView) findViewById(R.id.textViewOccupation);
        textViewGender = (TextView) findViewById(R.id.textViewGender);
        textViewMaritalStatus = (TextView) findViewById(R.id.textViewMaritalStatus);
        textViewPhone = (TextView) findViewById(R.id.textViewPhone);
        textViewEmail = (TextView) findViewById(R.id.textViewEmail);
        textViewAddress = (TextView) findViewById(R.id.textViewAddress);
        textViewCity = (TextView) findViewById(R.id.textViewCity);
        textViewState = (TextView) findViewById(R.id.textViewState);
        textViewBG = (TextView) findViewById(R.id.view_blood_group_value);
        textViewGenotype = (TextView) findViewById(R.id.view_genotype_value);
        textViewWeight = (TextView) findViewById(R.id.view_weight_value);
        textViewHeight = (TextView) findViewById(R.id.view_height_value);
        textViewBP = (TextView) findViewById(R.id.view_bp_value);
    }
    public void updatePatientInfo(Patient p){
        textViewName.setText(p.PATIENT_NAME);
        textViewDOB.setText(p.PATIENT_DOB.toString());
        textViewOccupation.setText(p.PATIENT_OCCUPATION);
        textViewGender.setText(p.PATIENT_GENDER);
        textViewMaritalStatus.setText(p.PATIENT_MARITAL_STATUS);
        textViewPhone.setText(p.PATIENT_PHONE);
        textViewEmail.setText(p.PATIENT_EMAIL);
        textViewAddress.setText(p.PATIENT_ADDRESS);
        textViewCity.setText(p.PATIENT_CITY);
        textViewState.setText(p.PATIENT_STATE);
        textViewBG.setText(p.PATIENT_BG);
        textViewGenotype.setText(p.PATIENT_GENOTYPE);
        textViewWeight.setText(p.PATIENT_STATE);
        textViewHeight.setText(p.PATIENT_STATE);
        textViewBP.setText(p.PATIENT_BP_SYSTOLIC + "/" + p.PATIENT_BP_DIASTOLIC);
    }
}