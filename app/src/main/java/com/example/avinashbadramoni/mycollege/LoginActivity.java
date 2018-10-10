package com.example.avinashbadramoni.mycollege;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText memail,mpassword;
    private TextView mbutton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();

        memail = (EditText)findViewById(R.id.email);
        mpassword = (EditText)findViewById(R.id.password);
        mbutton =(TextView)findViewById(R.id.btnSignIn);






        mbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String email = memail.getText().toString();
                String password = mpassword.getText().toString();



                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){

                            Intent user = new Intent(LoginActivity.this,
                                    MainActivity.class);
                            startActivity(user);
                            finish();
                        }else {
                            Toast.makeText(LoginActivity.this,"error",Toast.LENGTH_LONG).show();
                        }


                    }
                });

            }
        });










    }
}
