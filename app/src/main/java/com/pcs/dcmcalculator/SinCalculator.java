package com.pcs.dcmcalculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.text.DecimalFormat;

public class SinCalculator extends AppCompatActivity {

    EditText edtHeight, edtLength, edtDegree, edtMinute, edtSecond;
    RadioGroup rdgCalculation;
    RadioButton radHeight, radAngle;
    Button btnCalHeight, btnCalAngle;
    DecimalFormat df;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sin_calculator);

        //Format Settings
        df = new DecimalFormat("0.000");

        //Widgets declaration
        rdgCalculation = findViewById(R.id.rdgCalculation);
        radHeight = findViewById(R.id.radHeight);
        radAngle = findViewById(R.id.radAngle);

        edtHeight = findViewById(R.id.edtHeight);
        edtLength = findViewById(R.id.edtLength);
        edtDegree = findViewById(R.id.edtDegree);
        edtMinute = findViewById(R.id.edtMinute);
        edtSecond = findViewById(R.id.edtSecond);

        btnCalAngle = findViewById(R.id.btnCalAngle);
        btnCalHeight = findViewById(R.id.btnCalHeight);

        rdgCalculation.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.radAngle:
                        edtHeight.setEnabled(true);
                        btnCalHeight.setVisibility(View.GONE);
                        btnCalAngle.setVisibility(View.VISIBLE);
                        edtDegree.setEnabled(false);
                        edtMinute.setEnabled(false);
                        edtSecond.setEnabled(false);
                        Toast.makeText(SinCalculator.this, "Angle", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.radHeight:
                        edtHeight.setEnabled(false);
                        btnCalAngle.setVisibility(View.GONE);
                        btnCalHeight.setVisibility(View.VISIBLE);
                        edtDegree.setEnabled(true);
                        edtMinute.setEnabled(true);
                        edtSecond.setEnabled(true);
                        Toast.makeText(SinCalculator.this, "Height", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });
    }


    public void btnClickCalculateHeight(View view) {
        //df.setRoundingMode(RoundingMode.CEILING);

        double valLength = getDoubleVal(edtLength.getText().toString());
        double valDegree = getDoubleVal(edtDegree.getText().toString());
        double valMinute = getDoubleVal(edtMinute.getText().toString());
        double valSecond = getDoubleVal(edtSecond.getText().toString());

        double radianVal = (Math.PI * (valDegree + (valMinute / 60) + (valSecond / 3600))) / 180;
        double height = Math.sin(radianVal) * valLength;

        edtHeight.setText(df.format(height));
    }

    public void btnClickCalculateAngle(View view) {
        double valLength = getDoubleVal(edtLength.getText().toString());
        double valHeight = getDoubleVal(edtHeight.getText().toString());

        double radAngel = Math.asin(valHeight/valLength);
        double degAngle = Math.toDegrees(radAngel);
        double valDegree = (int)degAngle;
        double valMinute = (int)((degAngle-valDegree)*60);
        double valSecond = (degAngle - valDegree - (valMinute/60))*3600;

        edtDegree.setText(String.valueOf(valDegree));
        edtMinute.setText(String.valueOf(valMinute));
        edtSecond.setText(df.format(valSecond));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.omenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mnuCalc:
                startActivity(new Intent(this, MainActivity.class));
                finish();
        }
        return super.onOptionsItemSelected(item);
    }

    private double getDoubleVal(String s) {
        if (s.trim().equals("")) return 0.0;
        else return Double.parseDouble(s);
    }
}