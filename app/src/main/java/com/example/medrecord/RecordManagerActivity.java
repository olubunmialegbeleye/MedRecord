package com.example.medrecord;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.LinkedList;
import java.util.List;

public class RecordManagerActivity extends AppCompatActivity {
    private final LinkedList<String> mWordList = new LinkedList<>();
    DBHelper dbHelper = new DBHelper(this);


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record_manager);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        initializeRecyclerView();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RecordManagerActivity.this, NewRecordActivity.class));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.record_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.app_bar_search) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void initializeRecyclerView(){
        RecyclerView recyclerPatientList = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager listLayoutManager = new LinearLayoutManager(this);
        recyclerPatientList.setLayoutManager(listLayoutManager);
        List<Patient> patientList =dbHelper.getPatientList(); //from database
        final PatientListRecyclerAdapter patientListRecyclerAdapter = new PatientListRecyclerAdapter(this, patientList);
        recyclerPatientList.setAdapter(patientListRecyclerAdapter);
    }
}