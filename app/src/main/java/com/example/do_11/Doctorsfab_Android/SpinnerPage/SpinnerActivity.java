package com.example.do_11.Doctorsfab_Android.SpinnerPage;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.do_11.R;


public class SpinnerActivity extends AppCompatActivity {

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_spinner);
//
//        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
//        getSupportActionBar().setTitle("네비게이션 Option 1 ");
//        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xFF0FCAA5));
//
//        final Spinner yearSpinner = (Spinner) findViewById(R.id.spinner);
//        ArrayAdapter yearAdapter = ArrayAdapter.createFromResource(this,
//                R.array.data_year, android.R.layout.simple_spinner_item);
//        yearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        yearSpinner.setAdapter(yearAdapter);
//
//        final Spinner monthSpinner = (Spinner) findViewById(R.id.spinner2);
//        ArrayAdapter monthAdapter = ArrayAdapter.createFromResource(this,
//                R.array.data_month, android.R.layout.simple_spinner_item);
//        monthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        monthSpinner.setAdapter(monthAdapter);
//
//
//        String YearTxt = yearSpinner.getSelectedItem().toString();
//        String MonthTxt = monthSpinner.getSelectedItem().toString();
//
//
//
//
//        yearSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                TextView textView = findViewById(R.id.editText);
//                textView.setText(yearSpinner.getSelectedItem().toString());
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });
//
//
//        monthSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                TextView textView = findViewById(R.id.editText2);
//                textView.setText(monthSpinner.getSelectedItem().toString());
//
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });
//
//
// //Task 실험
//
//   /*     String sendmsg = "vision_write";
//
//        String result = "값"; //자신이 보내고싶은 값을 보내시면됩니다
//
//        try{
//
//            String rst = new Task(sendmsg).execute(result,"vision_write").get();
//
//        }catch (Exception e){
//
//            e.printStackTrace();
//
//        }*/
//
//    }
}

