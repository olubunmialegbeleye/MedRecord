package com.example.medrecord;

import android.os.Build;
import android.os.Parcelable;

import androidx.annotation.RequiresApi;

import java.io.Serializable;
import java.time.LocalDate;

import static java.time.temporal.ChronoUnit.YEARS;

public class Patient implements Serializable {
    final String PATIENT_NAME, PATIENT_OCCUPATION, PATIENT_PHONE;
    final String PATIENT_ADDRESS, PATIENT_CITY, PATIENT_STATE, PATIENT_EMAIL;
    final String PATIENT_HEIGHT, PATIENT_WEIGHT, PATIENT_BP_SYSTOLIC, PATIENT_BP_DIASTOLIC;
    final String PATIENT_GENDER, PATIENT_MARITAL_STATUS, PATIENT_BG, PATIENT_GENOTYPE;
    final LocalDate PATIENT_DOB;
    enum GENDER { MALE, FEMALE}
    enum MARITAL_STATUS {SINGLE, MARRIED, DIVORCED}
    enum BG {A, B, AB, O}
    enum GENOTYPE {AA, AS, AC, SS}

    public Patient(String patient_name, String patient_occupation, String patient_phone, String patient_address,
                   String patient_city, String patient_state, String patient_email, LocalDate patient_dob,
                   GENDER gender, MARITAL_STATUS marital_status,
                   String patient_height, String patient_weight, String patient_bp_systolic, String patient_bp_diastolic,
                   BG bg, GENOTYPE genotype) {
        PATIENT_NAME = patient_name;
        PATIENT_OCCUPATION = patient_occupation;
        PATIENT_PHONE = patient_phone;
        PATIENT_ADDRESS = patient_address;
        PATIENT_CITY = patient_city;
        PATIENT_STATE = patient_state;
        PATIENT_EMAIL = patient_email;
        PATIENT_DOB = patient_dob;
        PATIENT_GENDER = gender.name();
        PATIENT_MARITAL_STATUS = marital_status.name();
        PATIENT_HEIGHT = patient_height;
        PATIENT_WEIGHT = patient_weight;
        PATIENT_BP_SYSTOLIC = patient_bp_systolic;
        PATIENT_BP_DIASTOLIC = patient_bp_diastolic;
        PATIENT_BG = bg.name();
        PATIENT_GENOTYPE = genotype.name();
    }
    public String getPatientName(){
        return this.PATIENT_NAME;
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public long getPatientAge(){
        return this.PATIENT_DOB.until(LocalDate.now(), YEARS);
    }
    public String getPatientGender(){
        return this.PATIENT_GENDER;
    }
    public String getPatientBP(){
        return this.PATIENT_BP_SYSTOLIC + " / " + this.PATIENT_BP_DIASTOLIC;
    }
}
