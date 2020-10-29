package com.example.medrecord;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;

import java.time.LocalDate;


public class PersonalInformationFragment extends Fragment{
    Button buttonPersonalInfoNext;
    EditText editTextName, editTextDOB, editTextOccupation;
    RadioGroup radioGroupGender, radioGroupMaritalStatus;
    PatientInfoInterface toNewRecordActivity;
    public PersonalInformationFragment() {
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
        View view = inflater.inflate(R.layout.fragment_personal_information, container, false);
        initComponents(view);
        buttonPersonalInfoNext.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                logData();
                Activity activity = (Activity) PersonalInformationFragment.this.getHost();
                ViewPager viewPager = activity.findViewById(R.id.viewpager_container);
                viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
            }
        });
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                editTextDOB.setText(year + "-" + month + "-" + dayOfMonth);
            }
        };
        editTextDOB.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                new DatePickerDialog((Activity) PersonalInformationFragment.this.getHost(),
                        date, 1990, 6,6).show();
            }
        });
        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void onPause() {
        super.onPause();
        logData();
    }

    public void initComponents(View view) {
        buttonPersonalInfoNext = (Button) view.findViewById(R.id.buttonPersonalInfoNext);
        editTextName = (EditText) view.findViewById(R.id.editTextName);
        editTextDOB = (EditText) view.findViewById(R.id.editTextDOB);
        editTextOccupation = (EditText) view.findViewById(R.id.editTextOccupation);
        radioGroupGender = (RadioGroup) view.findViewById(R.id.radioGroupGender);
        radioGroupMaritalStatus = (RadioGroup) view.findViewById(R.id.radioGroupMaritalStatus);
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void logData(){
        System.out.println("DEBUG: " + editTextName.getText().toString());
        toNewRecordActivity.setName(editTextName.getText().toString());
        toNewRecordActivity.setDOB(LocalDate.parse(editTextDOB.getText().toString()));  //DEBUG
        toNewRecordActivity.setOccupation(editTextOccupation.getText().toString());
        //Get the status of GENDER radiogroup
        switch (radioGroupGender.getCheckedRadioButtonId()){
            case R.id.radioButtonMale:
                toNewRecordActivity.setGender(Patient.GENDER.MALE);
                break;
            case R.id.radioButtonFemale:
                toNewRecordActivity.setGender(Patient.GENDER.FEMALE);
                break;
            default:
                //throw warning
        }

        //Get the status of MARITAL STATUS radiogroup
        switch (radioGroupMaritalStatus.getCheckedRadioButtonId()){
            case R.id.radioButtonSingle:
                toNewRecordActivity.setMaritalStatus(Patient.MARITAL_STATUS.SINGLE);
                break;
            case R.id.radioButtonMarried:
                toNewRecordActivity.setMaritalStatus(Patient.MARITAL_STATUS.MARRIED);
                break;
            case R.id.radioButtonDivorced:
                toNewRecordActivity.setMaritalStatus(Patient.MARITAL_STATUS.DIVORCED);
                break;
            default:
                //throw warning
        }
    }
}