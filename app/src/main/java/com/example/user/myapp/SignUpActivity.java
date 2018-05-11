package com.example.user.myapp;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
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

public class SignUpActivity extends AppCompatActivity {
    EditText eamil,password,password2;
    Button btn;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mAuth= FirebaseAuth.getInstance();
        eamil=(EditText)findViewById(R.id.editEamil);
        password=(EditText)findViewById(R.id.editPassword);
        password2=(EditText)findViewById(R.id.editPassword2);
        btn=(Button)findViewById(R.id.SignUp);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(password.getText().toString().equals(password2.getText().toString()))
                {
                    mAuth.createUserWithEmailAndPassword(eamil.getText().toString(),password.getText().toString())
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    AppDBHelper appDBHelper=new AppDBHelper(getApplicationContext());
                                    SQLiteDatabase database=appDBHelper.getWritableDatabase();
                                        if (task.isSuccessful()) {
                                            // Sign in success, update UI with the signed-in user's informationFirebaseUser user = mAuth.getCurrentUser();
                                            Toast.makeText(SignUpActivity.this, "Sign up success!!", Toast.LENGTH_SHORT).show();
                                            FirebaseUser userFB = mAuth.getCurrentUser();
                                            User user=new User(eamil.getText().toString(),FirebaseAuth.getInstance().getCurrentUser().getUid());
                                            appDBHelper.addUser(user,database);
                                            SignUp();
                                            //updateUI(user);
                                        }
                                        else {
                                            // If sign in fails, display a message to the user.
                                            Toast.makeText(SignUpActivity.this, "Authentication failed.",
                                                    Toast.LENGTH_SHORT).show();
                                            //updateUI(null);
                                        }



                                }
                            });
                }
                else
                    Toast.makeText(SignUpActivity.this, "Enter The Same Password",Toast.LENGTH_SHORT).show();


            }
        });
    }
    public void SignUp()
    {
        Intent intent=new Intent(this,SignUpActivity.class);
        startActivity(intent);
    }
}

