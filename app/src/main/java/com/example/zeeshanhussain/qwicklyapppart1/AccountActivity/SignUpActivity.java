package com.example.zeeshanhussain.qwicklyapppart1.AccountActivity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
//import android.text.method.SingleLineTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.zeeshanhussain.qwicklyapppart1.MainActivity;
import com.example.zeeshanhussain.qwicklyapppart1.R;
import com.example.zeeshanhussain.qwicklyapppart1.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.*;

public class SignUpActivity extends AppCompatActivity {

    private EditText email,password,username,mobile;
    private Button SignUp,Login,Reset;
    private ProgressBar progressBar;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // get firebase auth instance

        firebaseAuth=FirebaseAuth.getInstance();

        email=(EditText)findViewById(R.id.emailSignUp);
        password=(EditText)findViewById(R.id.passwordSignUp);
        mobile=(EditText)findViewById(R.id.MblSignUp);
        username=(EditText)findViewById(R.id.UserNameSignUp);


        SignUp=(Button)findViewById(R.id.sign_up_button);
        Login=(Button)findViewById(R.id.sign_in_button);
        Reset=(Button)findViewById(R.id.btn_reset_password);

        progressBar=(ProgressBar)findViewById(R.id.progressBarSignUp);

        Reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              startActivity(new Intent(SignUpActivity.this,PasswordReset.class));
            }
        });
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String emailText = email.getText().toString().trim();
                final String passwordText = password.getText().toString().trim();
                final String number = mobile.getText().toString().trim();
                final String username1 = username.getText().toString().trim();


                if(TextUtils.isEmpty(emailText)) {
                    Toast.makeText(getApplicationContext(),"Enter email address!",Toast.LENGTH_LONG).show();
                }
                if(TextUtils.isEmpty(passwordText)){
                    Toast.makeText(getApplicationContext(),"Enter password!",Toast.LENGTH_SHORT).show();
                    return;

                }
                if(TextUtils.isEmpty(number)) {
                    Toast.makeText(getApplicationContext(),"Enter number... ",Toast.LENGTH_LONG).show();
                }
                if(TextUtils.isEmpty(username1)) {
                    Toast.makeText(getApplicationContext(),"Enter user... ",Toast.LENGTH_LONG).show();
                }

                if(passwordText.length() <6) {
                    Toast.makeText(getApplicationContext(),"Password too short, enter minimum 6 characters!",Toast.LENGTH_SHORT).show();
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                // create user



                firebaseAuth.createUserWithEmailAndPassword(emailText,passwordText).addOnCompleteListener(SignUpActivity.this,
                        new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Toast.makeText(SignUpActivity.this,"createUserWithEmail:onComplete"+task.isSuccessful(),Toast.LENGTH_SHORT).show();
                                progressBar.setVisibility(View.GONE);


                                if(task.isSuccessful()){
                                  // Toast.makeText(SignUpActivity.this,"Authentication failed."+task.getException(),Toast.LENGTH_SHORT).show();

                                    User user = new User(emailText,number,username1);

                                    FirebaseDatabase.getInstance().getReference("Users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if(task.isSuccessful()){
                                                Toast.makeText(SignUpActivity.this,"Yes Done User ",Toast.LENGTH_LONG).show();

                                            }
                                            else {
                                                Toast.makeText(SignUpActivity.this,"Failed :( ",Toast.LENGTH_LONG).show();

                                            }
                                        }
                                    });

                                }
                                else {
                                    Toast.makeText(SignUpActivity.this,task.getException().getMessage(),Toast.LENGTH_LONG).show();

                                    //startActivity(new Intent(SignUpActivity.this, MainActivity.class));
                                    //finish();
                                }
                            }
                        });
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    if(firebaseAuth.getCurrentUser() !=null) {

    }
    }

    @Override
    protected void onResume() {
        super.onResume();
        progressBar.setVisibility(View.GONE);

    }
}
