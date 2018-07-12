package com.example.zeeshanhussain.qwicklyapppart1.AccountActivity;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.example.zeeshanhussain.qwicklyapppart1.MainActivity;
import com.google.android.gms.tasks.OnCompleteListener;


import com.example.zeeshanhussain.qwicklyapppart1.R;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;

import static android.widget.Toast.*;
import static android.widget.Toast.LENGTH_LONG;

public class LoginActivityPage extends AppCompatActivity implements View.OnClickListener {

    private EditText email,password;
    private Button LoginBtn,VIsitBtn,SignUpButton;
    private TextView WelcomeText,InfoText;
    private FirebaseAuth firebaseAuth;
    ProgressDialog progressDialog;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

    email=(EditText)findViewById(R.id.Username);
    password=(EditText)findViewById(R.id.Password);
    LoginBtn=(Button)findViewById(R.id.Login);
    VIsitBtn=(Button)findViewById(R.id.BtnVisit);
    SignUpButton=(Button)findViewById(R.id.SignUpBtn);
    WelcomeText=(TextView)findViewById(R.id.WelcomeTitle);
    InfoText=(TextView)findViewById(R.id.InfoText);
    progressDialog= new ProgressDialog(this);

    firebaseAuth= FirebaseAuth.getInstance();




    LoginBtn.setOnClickListener(this);
    SignUpButton.setOnClickListener(this);



    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser =firebaseAuth.getCurrentUser();
        if(currentUser !=null) {
            startActivity(new Intent(this, MainActivity.class));

        }
        //updateUI(currentUser);

    }

    private void userLogin() {
        String emailTxt=email.getText().toString().trim();
        String passwordTxt =password.getText().toString().trim();
        // checking if email and password are empty

        if(TextUtils.isEmpty(emailTxt)) {
            makeText(this,"Please enter email", LENGTH_LONG).show();
            return;

        }
        if(TextUtils.isEmpty(passwordTxt)){
            makeText(this,"Please enter your password", LENGTH_LONG).show();
            return;
        }
        // if both are not empty

        // display progress dialog
        progressDialog.setMessage("Please wait ....");
        progressDialog.show();


        // Now Firebase

        firebaseAuth.signInWithEmailAndPassword(emailTxt,passwordTxt)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                    progressDialog.dismiss();
                     if(task.isSuccessful()){
                        // Toast.makeText(this,"Succesfully Registered User", LENGTH_LONG).show();
                        // makeText(this,"Created", LENGTH_LONG).show();
                     // start main activity
                     }
                    }
                });




    }
    @Override
    public void onClick(View view) {
        if(view == LoginBtn) {
            userLogin();
        }

        if(view == SignUpButton) {
        finish();
        startActivity(new Intent(this, SignUpActivity.class));
        }
    }
}
