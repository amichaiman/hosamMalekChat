package com.example.user.myapp;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class BaseActivity extends AppCompatActivity {
    TextView username;
    Button chatin;
    Button users;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        username=(TextView)findViewById(R.id.theStart);
        users=(Button)findViewById(R.id.ShowUsers);
        username.setText("Hey" + FirebaseAuth.getInstance().getCurrentUser().getEmail());
        chatin=(Button)findViewById(R.id.EnterTheChat);
        chatin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chatin();
            }
        });
        users.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                users();
            }
        });

    }
    public void chatin()
    {
        Intent intent=new Intent(BaseActivity.this,WorldWideChatActivity.class);
        startActivity(intent);
    }
    public void users()
    {
        Intent intent=new Intent(BaseActivity.this,AppUsersActivity.class);
        startActivity(intent);
    }

}
