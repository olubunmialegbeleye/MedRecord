package com.example.medrecord;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DBHelper {
    public static final String DATABASE_NAME = "patient_record";
    public static final String TABLE_NAME = "patient";
    public static final int DATABASE_VERSION = 1;
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NAME = "patient_name";
    public static final String COLUMN_DOB = "patient_dob";
    public static final String COLUMN_GENDER = "patient_gender";
    public static final String COLUMN_OCCUPATION = "patient_occupation";
    public static final String COLUMN_MARITAL_STATUS = "patient_marital_status";
    public static final String COLUMN_PHONE = "patient_phone";
    public static final String COLUMN_ADDRESS = "patient_address";
    public static final String COLUMN_CITY = "patient_city";
    public static final String COLUMN_STATE = "patient_state";
    public static final String COLUMN_EMAIL = "patient_email";
    public static final String COLUMN_HEIGHT = "patient_height";
    public static final String COLUMN_WEIGHT = "patient_weight";
    public static final String COLUMN_BP_SYSTOLIC = "patient_bp_systolic";
    public static final String COLUMN_BP_DIASTOLIC = "patient_bp_diastolic";
    public static final String COLUMN_BG = "patient_bg";
    public static final String COLUMN_GENOTYPE = "patient_genotype";


    private SQLiteDatabase db;

    private InnerDBHelper dbHelper;
    private Context myContext;

    private class InnerDBHelper extends SQLiteOpenHelper {
        public InnerDBHelper(@Nullable Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("CREATE TABLE " + TABLE_NAME + " ( " +
                    //COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_NAME + " TEXT NOT NULL, " +
                    COLUMN_DOB + " DATE NOT NULL, " +
                    COLUMN_GENDER + " TEXT NOT NULL, " +
                    COLUMN_OCCUPATION + " TEXT NOT NULL, " +
                    COLUMN_MARITAL_STATUS + " TEXT NOT NULL, " +
                    COLUMN_ADDRESS + " TEXT NOT NULL, " +
                    COLUMN_PHONE + " TEXT NOT NULL, " +
                    COLUMN_CITY + " TEXT NOT NULL, " +
                    COLUMN_STATE + " TEXT NOT NULL, " +
                    COLUMN_EMAIL + " TEXT NOT NULL PRIMARY KEY, " +
                    COLUMN_HEIGHT + " TEXT NOT NULL, " +
                    COLUMN_WEIGHT + " TEXT NOT NULL, " +
                    COLUMN_BP_SYSTOLIC + " TEXT NOT NULL, " +
                    COLUMN_BP_DIASTOLIC + " TEXT NOT NULL, " +
                    COLUMN_BG + " TEXT NOT NULL, " +
                    COLUMN_GENOTYPE + " TEXT NOT NULL" +
                    " )"
            );
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }
    }

    public DBHelper (Context c){
        myContext = c;
    }
    public DBHelper open(){
        dbHelper = new InnerDBHelper(myContext);
        db = dbHelper.getWritableDatabase();
        return this;
    }
    public void close(){
        dbHelper.close();
    }
    public long createPatient(Patient patient){
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_NAME, patient.PATIENT_NAME);
        cv.put(COLUMN_DOB, patient.PATIENT_DOB.toString());
        cv.put(COLUMN_GENDER, patient.PATIENT_GENDER);
        cv.put(COLUMN_OCCUPATION, patient.PATIENT_OCCUPATION);
        cv.put(COLUMN_MARITAL_STATUS, patient.PATIENT_MARITAL_STATUS);
        cv.put(COLUMN_ADDRESS, patient.PATIENT_ADDRESS);
        cv.put(COLUMN_PHONE, patient.PATIENT_PHONE);
        cv.put(COLUMN_CITY, patient.PATIENT_CITY);
        cv.put(COLUMN_STATE, patient.PATIENT_STATE);
        cv.put(COLUMN_EMAIL, patient.PATIENT_EMAIL);
        cv.put(COLUMN_HEIGHT, patient.PATIENT_HEIGHT);
        cv.put(COLUMN_WEIGHT, patient.PATIENT_WEIGHT);
        cv.put(COLUMN_BP_SYSTOLIC, patient.PATIENT_BP_SYSTOLIC);
        cv.put(COLUMN_BP_DIASTOLIC, patient.PATIENT_BP_DIASTOLIC);
        cv.put(COLUMN_BG, patient.PATIENT_BG);
        cv.put(COLUMN_GENOTYPE, patient.PATIENT_GENOTYPE);
        this.open();
        long result_code = db.insert(TABLE_NAME, null, cv);
        this.close();
        return result_code;
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    public List<Patient> getPatientList(){
        List<Patient> patientList = new ArrayList<>();
        this.open();
        Cursor c = db.query(TABLE_NAME, null, null, null, null, null, null);
        int iName = c.getColumnIndex(COLUMN_NAME);
        int iDOB = c.getColumnIndex(COLUMN_DOB);
        int iGender = c.getColumnIndex(COLUMN_GENDER);
        int iOccupation = c.getColumnIndex(COLUMN_OCCUPATION);
        int iMaritalStatus = c.getColumnIndex(COLUMN_MARITAL_STATUS);
        int iAddress = c.getColumnIndex(COLUMN_ADDRESS);
        int iPhone = c.getColumnIndex(COLUMN_PHONE);
        int iCity = c.getColumnIndex(COLUMN_CITY);
        int iState = c.getColumnIndex(COLUMN_STATE);
        int iEmail = c.getColumnIndex(COLUMN_EMAIL);
        int iHeight = c.getColumnIndex(COLUMN_HEIGHT);
        int iWeight = c.getColumnIndex(COLUMN_WEIGHT);
        int iBpSystolic = c.getColumnIndex(COLUMN_BP_SYSTOLIC);
        int iBpDiastolic = c.getColumnIndex(COLUMN_BP_DIASTOLIC);
        int iBG = c.getColumnIndex(COLUMN_BG);
        int iGenotype = c.getColumnIndex(COLUMN_GENOTYPE);

        for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()){
            String pName = c.getString(iName);
            String pOccupation = c.getString(iOccupation);
            String pPhone = c.getString(iPhone);
            String pAddress = c.getString(iAddress);
            String pCity = c.getString(iCity);
            String pState = c.getString(iState);
            String pEmail = c.getString(iEmail);
            LocalDate pDOB = LocalDate.parse(c.getString(iDOB));
            Patient.GENDER pGender = Patient.GENDER.valueOf(c.getString(iGender));
            Patient.MARITAL_STATUS pMaritalStatus = Patient.MARITAL_STATUS.valueOf(c.getString(iMaritalStatus));
            String pHeight = c.getString(iHeight);
            String pWeight = c.getString(iWeight);
            String pBPSystolic = c.getString(iBpSystolic);
            String pBPDiastolic = c.getString(iBpDiastolic);
            Patient.BG pBG = Patient.BG.valueOf(c.getString(iBG));
            Patient.GENOTYPE pGenotype = Patient.GENOTYPE.valueOf(c.getString(iGenotype));
            Patient patient = new Patient(pName, pOccupation, pPhone, pAddress, pCity, pState, pEmail,
                    pDOB, pGender, pMaritalStatus,
                    pHeight, pWeight, pBPSystolic, pBPDiastolic, pBG, pGenotype);
            patientList.add(patient);
        }
        this.close();
        return patientList;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public Patient getPatient(String patientName){
        String[] columns = new String[] {COLUMN_NAME, COLUMN_DOB, COLUMN_GENDER, COLUMN_OCCUPATION, COLUMN_MARITAL_STATUS, COLUMN_ADDRESS, COLUMN_PHONE, COLUMN_CITY, COLUMN_STATE, COLUMN_EMAIL};
        this.open();
        Cursor c = db.query(TABLE_NAME, null, COLUMN_NAME + " = " + patientName, null, null, null, null);
        this.close();
        int iName = c.getColumnIndex(COLUMN_NAME);
        int iDOB = c.getColumnIndex(COLUMN_DOB);
        int iGender = c.getColumnIndex(COLUMN_GENDER);
        int iOccupation = c.getColumnIndex(COLUMN_OCCUPATION);
        int iMaritalStatus = c.getColumnIndex(COLUMN_MARITAL_STATUS);
        int iAddress = c.getColumnIndex(COLUMN_ADDRESS);
        int iPhone = c.getColumnIndex(COLUMN_PHONE);
        int iCity = c.getColumnIndex(COLUMN_CITY);
        int iState = c.getColumnIndex(COLUMN_STATE);
        int iEmail = c.getColumnIndex(COLUMN_EMAIL);
        int iHeight = c.getColumnIndex(COLUMN_HEIGHT);
        int iWeight = c.getColumnIndex(COLUMN_WEIGHT);
        int iBpSystolic = c.getColumnIndex(COLUMN_BP_SYSTOLIC);
        int iBpDiastolic = c.getColumnIndex(COLUMN_BP_DIASTOLIC);
        int iBG = c.getColumnIndex(COLUMN_BG);
        int iGenotype = c.getColumnIndex(COLUMN_GENOTYPE);
        if (c != null){
            c.moveToFirst();
            String pName = c.getString(iName);
            String pOccupation = c.getString(iOccupation);
            String pPhone = c.getString(iPhone);
            String pAddress = c.getString(iAddress);
            String pCity = c.getString(iCity);
            String pState = c.getString(iState);
            String pEmail = c.getString(iEmail);
            LocalDate pDOB = LocalDate.parse(c.getString(iDOB));
            Patient.GENDER pGender = Patient.GENDER.valueOf(c.getString(iGender));
            Patient.MARITAL_STATUS pMaritalStatus = Patient.MARITAL_STATUS.valueOf(c.getString(iMaritalStatus));
            String pHeight = c.getString(iHeight);
            String pWeight = c.getString(iWeight);
            String pBPSystolic = c.getString(iBpSystolic);
            String pBPDiastolic = c.getString(iBpDiastolic);
            Patient.BG pBG = Patient.BG.valueOf(c.getString(iBG));
            Patient.GENOTYPE pGenotype = Patient.GENOTYPE.valueOf(c.getString(iGenotype));
            Patient patient = new Patient(pName, pOccupation, pPhone, pAddress, pCity, pState, pEmail,
                    pDOB, pGender, pMaritalStatus,
                    pHeight, pWeight, pBPSystolic, pBPDiastolic, pBG, pGenotype);
            return patient;
        }
        else
            return null;
    }


    public int deletePatient(String[] patientNames){
        //look for a way to
        String pName = "";
        for(String s : patientNames){

        }
        return db.delete(TABLE_NAME,COLUMN_NAME + " IN " + " ? ", patientNames);
    }

    public int editPatient(String patientName, Patient updatedPatient){
        ContentValues cv = new ContentValues();
        cv.put("COLUMN_NAME", updatedPatient.PATIENT_NAME);
        cv.put("COLUMN_DOB", updatedPatient.PATIENT_DOB.toString());
        cv.put("COLUMN_GENDER", updatedPatient.PATIENT_GENDER);
        cv.put("COLUMN_OCCUPATION", updatedPatient.PATIENT_OCCUPATION);
        cv.put("COLUMN_MARITAL_STATUS", updatedPatient.PATIENT_MARITAL_STATUS);
        cv.put("COLUMN_ADDRESS", updatedPatient.PATIENT_ADDRESS);
        cv.put("COLUMN_PHONE", updatedPatient.PATIENT_PHONE);
        cv.put("COLUMN_CITY", updatedPatient.PATIENT_CITY);
        cv.put("COLUMN_STATE", updatedPatient.PATIENT_STATE);
        cv.put("COLUMN_EMAIL", updatedPatient.PATIENT_EMAIL);
        this.open();
        int result_code = db.update(TABLE_NAME, cv, COLUMN_NAME + " = " + patientName, null);
        this.close();
        return result_code;
    }
}
