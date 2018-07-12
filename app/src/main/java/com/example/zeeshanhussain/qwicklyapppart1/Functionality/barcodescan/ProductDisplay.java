package com.example.zeeshanhussain.qwicklyapppart1.Functionality.barcodescan;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.zeeshanhussain.qwicklyapppart1.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProductDisplay extends AppCompatActivity {

    String barcode;
    DatabaseReference rootRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_display);
        rootRef= FirebaseDatabase.getInstance().getReference();
        DatabaseReference myRef=rootRef.child("Product");
        barcode=getIntent().getStringExtra("BarcodeID");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String value=dataSnapshot.toString();
                if(value.contains(barcode)) {
                    // Display in AR
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
