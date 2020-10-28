package com.example.medrecord;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;


public class ContactInformationFragment extends Fragment{
    Button buttonContactInfoNext;
    EditText editTextPhone, editTextAddress, editTextCity, editTextState, editTextEmail;
    PatientInfoInterface toNewRecordActivity;
    public ContactInformationFragment() {
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
        View view = inflater.inflate(R.layout.fragment_contact_information, container, false);
        initComponents(view);
        buttonContactInfoNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logData();
                Activity activity = (Activity) ContactInformationFragment.this.getHost();
                ViewPager viewPager = (ViewPager) activity.findViewById(R.id.viewpager_container);
                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
            }
        });
        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void onPause() {
        super.onPause();
        logData();
    }

    public void initComponents(View view){
        buttonContactInfoNext = (Button) view.findViewById(R.id.buttonContactInfoNext);
        editTextPhone = (EditText) view.findViewById(R.id.editTextPhone);
        editTextAddress = (EditText) view.findViewById(R.id.editTextAddress);
        editTextCity = (EditText) view.findViewById(R.id.editTextCity);
        editTextState = (EditText) view.findViewById(R.id.editTextState);
        editTextEmail = (EditText) view.findViewById(R.id.editTextEmail);
    }
    public void logData(){
        toNewRecordActivity.setPhone(editTextPhone.getText().toString());
        toNewRecordActivity.setAddress(editTextAddress.getText().toString());
        toNewRecordActivity.setCity(editTextCity.getText().toString());
        toNewRecordActivity.setState(editTextState.getText().toString());
        toNewRecordActivity.setEmail(editTextEmail.getText().toString());
    }
}