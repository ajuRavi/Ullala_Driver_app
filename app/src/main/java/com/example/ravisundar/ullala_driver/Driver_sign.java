package com.example.ravisundar.ullala_driver;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Driver_sign extends AppCompatActivity {
    Button signup;
    EditText name,phonenumber,vehiclenumber;
    private DatabaseReference databaseReference;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_sign);
        databaseReference = FirebaseDatabase.getInstance().getReference();

        name=findViewById(R.id.username);
        phonenumber=findViewById(R.id.phonenumber);
        vehiclenumber=findViewById(R.id.vehiclenumber);
       // vehicletype=findViewById(R.id.type);
        signup=findViewById(R.id.signup);
        spinner = (Spinner) findViewById(R.id.spinner);
        final Double latitude1 = 0.0, longitude1 = 0.0;

        String sexarray[]={"bike","auto","cars3","cars5"};

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,sexarray);
        spinner.setAdapter(adapter);




        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String  name1 = name.getText().toString();
              final String vehiclenumber1=vehiclenumber.getText().toString();
               // String vehilcenumber1=vehiclenumber.getText().toString();
                final String number=phonenumber.getText().toString();
               // String type=vehicletype.getText().toString();

                final String se=spinner.getSelectedItem().toString();
                Driver_signup signup=new Driver_signup(name1,number,vehiclenumber1,se,latitude1,longitude1);
                databaseReference.child("driver").child(number).setValue(signup).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.w("done","yes");
                        Intent intent= new Intent(getApplicationContext(), Driver_display.class);
                        intent.putExtra("number",number);
                        Toast.makeText(getApplicationContext(), se, Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                    }
                });

            }
        });
    }
}
