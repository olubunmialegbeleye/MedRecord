package com.example.medrecord;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import java.time.LocalDate;
import java.util.HashMap;

public class NewRecordActivity extends AppCompatActivity implements PatientInfoInterface{
    TabLayout tabLayout;
    ViewPager viewPager;
    PagerAdapter PagerAdapter;
    PersonalInformationFragment personalInfoFragment;
    ContactInformationFragment contactInfoFragment;
    HealthInformationFragment healthInformationFragment;
    DBHelper dbHelper = new DBHelper(this);
    HashMap<String, Object> holder = new HashMap<String, Object>();
    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_layout);
        //setContentView(R.layout.activity_new_record);
        initComponents();
        setUpViewPager();
    }
    private void initComponents(){
        tabLayout = (TabLayout) findViewById(R.id.tabs_bottom);
        viewPager = (ViewPager) findViewById(R.id.viewpager_container);
    }

    private void setUpViewPager(){
        PagerAdapter = new PagerAdapter(getSupportFragmentManager());
        personalInfoFragment = new PersonalInformationFragment();
        contactInfoFragment = new ContactInformationFragment();
        healthInformationFragment = new HealthInformationFragment();
        PagerAdapter.addFragment(personalInfoFragment);
        PagerAdapter.addFragment(contactInfoFragment);
        PagerAdapter.addFragment(healthInformationFragment);
        viewPager.setAdapter(PagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

    }

    @Override
    public void setName(String name) {
        holder.put("name", name);
    }

    @Override
    public void setDOB(LocalDate DOB) {
        holder.put("DOB", DOB);
    }

    @Override
    public void setGender(Patient.GENDER gender) {
        holder.put("gender", gender);
    }

    @Override
    public void setOccupation(String occupation) {
        holder.put("occupation", occupation);
    }

    @Override
    public void setMaritalStatus(Patient.MARITAL_STATUS marital_status) {
        holder.put("marital_status", marital_status);
    }

    @Override
    public void setPhone(String phone) {
        holder.put("phone", phone);
    }

    @Override
    public void setAddress(String address) {
        holder.put("address", address);
    }

    @Override
    public void setCity(String city) {
        holder.put("city", city);
    }

    @Override
    public void setState(String state) {
        holder.put("state", state);
    }

    @Override
    public void setEmail(String email) {
        holder.put("email", email);
    }

    @Override
    public void setBG(Patient.BG bg) {
        holder.put("BG", bg);
    }

    @Override
    public void setGenotype(Patient.GENOTYPE genotype) {
        holder.put("genotype", genotype);
    }

    @Override
    public void setBP(String systolic, String diastolic) {
        holder.put("systolic", systolic);
        holder.put("diastolic", diastolic);
    }

    @Override
    public void setWeight(String weight) {
        holder.put("weight", weight);
    }

    @Override
    public void setHeight(String height) {
        holder.put("height", height);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void createPatientinDB() {
        Patient p = new Patient((String) holder.get("name"), (String) holder.get("occupation"), (String) holder.get("phone"), (String) holder.get("address"), (String) holder.get("city"), (String) holder.get("state"), (String) holder.get("email"), (LocalDate) holder.get("DOB"), (Patient.GENDER) holder.get("gender"), (Patient.MARITAL_STATUS) holder.get("marital_status"));
        dbHelper.createPatient(p);
    }
}