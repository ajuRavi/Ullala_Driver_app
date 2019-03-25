package com.example.ravisundar.ullala_driver;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CustomerCheck extends AppCompatActivity {
TextView place;
Button yes,no;
 String val=" ", number,cnumber;
 List<String> ar=new ArrayList<String>();
    int i=0;
    private DatabaseReference databaseReference,databaseReference2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_check);
        yes=findViewById(R.id.yes);
        no=findViewById(R.id.no);
        place=findViewById(R.id.placename);
        Bundle extras = getIntent().getExtras();
        number = extras.getString("number");

        databaseReference = FirebaseDatabase.getInstance().getReference("customer");

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                place.setText("");
                if(i<ar.size()){
                    cnumber=ar.get(i);
                    databaseReference.child(ar.get(i)).child("source").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            val = dataSnapshot.getValue(String.class);
                            place.setText(val);
                            i++;
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
                else{
                    Toast.makeText(getApplicationContext(),"wait for your next ride",Toast.LENGTH_SHORT).show();
                }
            }
        });


        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                databaseReference.child(cnumber).child("cab").setValue("yes").addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Intent intent =new Intent(getApplicationContext(),confirmation.class);
                        intent.putExtra("number",cnumber);
                        startActivity(intent);
                    }
                });
                }
                catch (Exception e) {
                    e.printStackTrace();
                }



            }
        });



        //Toast.makeText(getApplicationContext(),"yes1",Toast.LENGTH_SHORT).show();
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot data: dataSnapshot.getChildren()){


                     cnumber=data.getKey();
                     ar.add(cnumber);
                    val=dataSnapshot.child(data.getKey()).child("source").getValue(String.class);
                    place.setText(val);



                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });





    }
}
