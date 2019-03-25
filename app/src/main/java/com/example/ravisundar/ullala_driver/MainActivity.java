package com.example.ravisundar.ullala_driver;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText number;
    Button login,signup1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       number=findViewById(R.id.number);
        login=findViewById(R.id.Login);
        signup1=findViewById(R.id.signup);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (TextUtils.isEmpty(number.getText()))
                    Toast.makeText(getApplicationContext(), R.string.number_empty, Toast.LENGTH_SHORT).show();
                else if (number.getText().toString().trim().length() < 10)
                    Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();
               else if(number.getText().toString().matches("7904934446")){
                    Toast.makeText(getApplicationContext(), "correct", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(getApplicationContext(), Driver_display.class);
                    i.putExtra("number", String.valueOf(number));
                    startActivity(i);
                }
                else {
                    Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();
                }
            }
        });

       signup1.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent i=new Intent(getApplicationContext(),Driver_sign.class);
               startActivity(i);
           }
       });

    }
}
