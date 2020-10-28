package com.example.medrecord;

import java.time.LocalDate;

public interface PatientInfoInterface {
    public void setName(String name);
    public void setDOB(LocalDate DOB);
    public void setGender(Patient.GENDER gender);
    public void setOccupation(String occupation);
    public void setMaritalStatus(Patient.MARITAL_STATUS marital_status);
    public void setPhone(String phone);
    public void setAddress(String address);
    public void setCity(String city);
    public void setState(String state);
    public void setEmail(String email);
    public void setBG(Patient.BG bg);
    public void setGenotype(Patient.GENOTYPE genotype);
    public void setBP(String systolic, String diastolic);
    public void setWeight(String weight);
    public void setHeight(String height);
    public void createPatientinDB();
}
