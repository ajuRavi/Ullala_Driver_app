package com.example.ravisundar.ullala_driver;

import android.content.Intent;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Driver_display extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private DatabaseReference databaseReference;
    LocationListener locationListener;
    LocationManager locationManager;
    EditText yourplace, destinationplace;
    Button search,viewCustomers;
    int PLACE_PICKER_REQUEST = 1;
    int a = 0;
    Double latitude1=0.0,longitude1=0.0;
    String number1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_display);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        yourplace = findViewById(R.id.place);

        search = findViewById(R.id.search);
        viewCustomers = findViewById(R.id.viewCustomers);

        Bundle extras = getIntent().getExtras();

        number1=extras.getString("number");


        databaseReference= FirebaseDatabase.getInstance().getReference("driver");


        viewCustomers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent i = new Intent(getApplicationContext(), CustomerCheck.class);
               i.putExtra("latitude",latitude1);
               i.putExtra("longitude",longitude1);
               i.putExtra("number",number1);
                startActivity(i);
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                a=0;
                PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
                try {
                    startActivityForResult(builder.build(Driver_display.this), PLACE_PICKER_REQUEST);
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    String x = e.toString();
                    Log.e("@@@", x);
                }
            }

        });

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                Place place;
                //Bundle extras = getIntent().getExtras();
                //String id1=extras.getString("id");
                place = PlacePicker.getPlace(Driver_display.this, data);
                String toastMsg = String.format("%s", place.getName());
                    yourplace.setText(toastMsg);
                    latitude1= place.getLatLng().latitude;
                    longitude1=place.getLatLng().longitude;
                    Toast.makeText(this, latitude1+" "+longitude1, Toast.LENGTH_LONG).show();
                    LatLng     source = new LatLng(latitude1,longitude1);
                  //  databaseReference.child("driver").child(id1).setValue(latitude1,longitude1);
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(source,16));
                    mMap.addMarker(new MarkerOptions().position(source).title("Your place"));
                    mMap.moveCamera(CameraUpdateFactory.newLatLng(source));



            }
        }


    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


    }
}
