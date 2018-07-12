package com.example.zeeshanhussain.qwicklyapppart1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.zeeshanhussain.qwicklyapppart1.AccountActivity.SignUpActivity;
import com.example.zeeshanhussain.qwicklyapppart1.Functionality.barcodescan.ScanProduct;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button scan,favourite,offers;
        ImageView img;
        scan=(Button)findViewById(R.id.scanBtn);
        favourite=(Button)findViewById(R.id.favtBtn);
        offers=(Button)findViewById(R.id.OffersBtn);

        img=(ImageView)findViewById(R.id.logoImg);


        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
      //          startActivity(new Intent(this, ScanProduct.class));

                startActivity(new Intent(getApplicationContext(), ScanProduct.class));

            }
        });
    }



}
