package com.example.user.myapp;

import android.database.sqlite.SQLiteDatabase;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ChatActivity extends AppCompatActivity {
    TextView userNameTextView;
    EditText messageToSendToDataBaseEditText;
    Button sendMessageButton;
    Button signOut;
    Animation shake;
    FirebaseDatabase database;
    LinearLayout messageLinearLayout;
    ScrollView scrollView;
    public static String UserId=AppUsersActivity.UserID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        signOut = findViewById(R.id.signOutButton);
        userNameTextView = findViewById(R.id.userNameTextView);
        messageToSendToDataBaseEditText = findViewById(R.id.messageToSendToDataBaseEditText);
        sendMessageButton = findViewById(R.id.sendMessageButton);
        messageLinearLayout = findViewById(R.id.messageLinearLayout);
        scrollView = findViewById(R.id.scrollView);
        database = FirebaseDatabase.getInstance();


        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                finish();
            }
        });

        sendMessageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (messageToSendToDataBaseEditText.getText().toString().isEmpty()){
                    messageToSendToDataBaseEditText.startAnimation(shake);
                } else {
                    AppAllMessages appAllMessages=new AppAllMessages(getApplicationContext());
                    SQLiteDatabase database=appAllMessages.getWritableDatabase();
                    appAllMessages.addChat(FirebaseAuth.getInstance().getCurrentUser().getUid(),UserId,messageToSendToDataBaseEditText.getText().toString(),database);
                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams
                            (LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1f);
                    params.setMargins(0,5,0,5);
                    params.gravity = Gravity.RIGHT;
                    TextView textView = new EditText(getApplicationContext());
                    textView.setBackgroundResource(R.drawable.sent_message);
                    textView.setText(messageToSendToDataBaseEditText.getText().toString());
                    textView.setTextColor(Color.BLACK);
                    textView.setLayoutParams(params);
                    textView.setPadding(15,15,15,15);
                    textView.setEnabled(false);
                    messageLinearLayout.addView(textView,messageLinearLayout.getChildCount());
                    messageToSendToDataBaseEditText.setText("");
                    scrollView.post(new Runnable() {
                        @Override
                        public void run() {
                            scrollView.fullScroll(View.FOCUS_DOWN);
                        }
                    });
                }
            }
        });

    }
    private void addMessage(String s) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams
                (LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1f);
        params.setMargins(0, 5, 0, 5);
        TextView textView = new EditText(getApplicationContext());
        textView.setBackgroundResource(R.drawable.recieved_message);
        textView.setText(s);
        textView.setTextColor(Color.BLACK);
        textView.setLayoutParams(params);
        textView.setEnabled(false);
        textView.setPadding(15, 15, 15, 15);
        messageLinearLayout.addView(textView, messageLinearLayout.getChildCount());
        scrollView.post(new Runnable() {
            @Override
            public void run() {
                scrollView.fullScroll(View.FOCUS_DOWN);
            }
        });
    }
}

