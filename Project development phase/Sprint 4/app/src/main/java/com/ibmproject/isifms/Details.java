package com.ibmproject.isifms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Details extends AppCompatActivity {
    DatePicker date;
    TimePicker time;
    EditText location;
    Button submitBtn;
    ArrayList<String> details = new ArrayList<>();
    //    Firebase Methods Declarations
    FirebaseDatabase firebaseDatabase;
    DatabaseReference ref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        date = (DatePicker) findViewById(R.id.date);
        time = (TimePicker) findViewById(R.id.time);
        location = (EditText) findViewById(R.id.location);
        submitBtn = (Button) findViewById(R.id.submitBtn);

        firebaseDatabase = FirebaseDatabase.getInstance();
        ref = firebaseDatabase.getReference();

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                details.add(String.valueOf("Date: " + date.getDayOfMonth()) + '/' + String.valueOf(date.getMonth()) + '/' + String.valueOf(date.getYear()));
                details.add(String.valueOf("Time: " + time.getCurrentHour()) + ":" + String.valueOf(time.getCurrentMinute()));
                details.add("Location: " + location.getText().toString());
                ref.setValue(details);
                startActivity(new Intent(Details.this, Notification.class));
                finish();
            }
        });
    }
}