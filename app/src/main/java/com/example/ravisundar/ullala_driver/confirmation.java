package com.example.ravisundar.ullala_driver;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class confirmation extends AppCompatActivity {
TextView name,source,destinaton,number,amount;
EditText otp;
Button cancel,confirm;
ImageButton call;
String amount1,otp1,val3;
    private DatabaseReference databaseReference ,databaseReference2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmation);
        name=findViewById(R.id.name);
        source=findViewById(R.id.source);
        destinaton=findViewById(R.id.destination);
        number=findViewById(R.id.dnumber);
        cancel=findViewById(R.id.cancel);
        confirm=findViewById(R.id.confirm);
        call=findViewById(R.id.call);
        otp=findViewById(R.id.otp);
        amount=findViewById(R.id.amount);

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(otp.getText().toString().matches(val3)){
                   amount.setText(amount1);
                }
                else{
                    Toast.makeText(getApplicationContext(),"enter a correct otp",Toast.LENGTH_LONG).show();
                }

            }
        });
  cancel.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
          Intent i =new Intent(getApplicationContext(),CustomerCheck.class);
          i.putExtra("number", String.valueOf(number));
          startActivity(i);

      }
  });
        Bundle extras = getIntent().getExtras();
      final String  cnumber = extras.getString("number");

      call.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent callIntent = new Intent(Intent.ACTION_DIAL);
              callIntent.setData(Uri.parse("tel:" + number.getText().toString()));
//                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
//                    // TODO: Consider calling
//                    //    ActivityCompat#requestPermissions
//                    // here to request the missing permissions, and then overriding
//                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//                    //                                          int[] grantResults)
//                    // to handle the case where the user grants the permission. See the documentation
//                    // for ActivityCompat#requestPermissions for more details.
//                    return;
//                }
              startActivity(callIntent);
          }

      });

    databaseReference = FirebaseDatabase.getInstance().getReference("customer");
    databaseReference.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            String val= dataSnapshot.child(cnumber).child("name").getValue(String.class);
            name.setText(val);
            String val1= dataSnapshot.child(cnumber).child("source").getValue(String.class);
            source.setText(val1);
            String val2= dataSnapshot.child(cnumber).child("destination").getValue(String.class);
            destinaton.setText(val2);
            val3=dataSnapshot.child(cnumber).child("otp").getValue(String.class);
           // Toast.makeText(getApplicationContext(),val3,Toast.LENGTH_LONG).show();
            amount1=dataSnapshot.child(cnumber).child("amount").getValue(String.class);

            number.setText(cnumber);
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    });


    }
}
