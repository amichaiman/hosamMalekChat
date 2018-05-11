package com.example.user.myapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    EditText emailEditText;
    EditText passwordEditText;
    Button signInButton;
    Button signUpButton;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth= FirebaseAuth.getInstance();
        emailEditText=(EditText)findViewById(R.id.editText);
        passwordEditText=(EditText)findViewById(R.id.editText2);
        signInButton=(Button) findViewById(R.id.button2);
        signUpButton=(Button)findViewById(R.id.button3);
        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoToSignUpActivity();
            }
        });
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mAuth.signInWithEmailAndPassword(emailEditText.getText().toString(), passwordEditText.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Toast.makeText(MainActivity.this, "Sign in success",
                                            Toast.LENGTH_SHORT).show();
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    GoToBaseActivity();
                                    //updateUI(user);
                                } else {
                                    // If sign in fails, display a message to the user.
                                    Toast.makeText(MainActivity.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();
                                   // updateUI(null);
                                }

                                // ...
                            }
                        });
            }
        });

    }
    public void GoToSignUpActivity()
    {
        Intent intent=new Intent(this,SignUpActivity.class);
        startActivity(intent);
    }
    public void GoToBaseActivity()
    {
        Intent intent=new Intent(this,BaseActivity.class);
        startActivity(intent);
    }
}
