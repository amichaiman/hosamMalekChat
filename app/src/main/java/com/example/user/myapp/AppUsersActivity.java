package com.example.user.myapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;
import java.util.List;

public class AppUsersActivity extends AppCompatActivity {
    ListView listView;
    List<Contact> contact;
    public static String UserID;
    Button chat;
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_users);
        chat=(Button)findViewById(R.id.Chat);
        editText=(EditText)findViewById(R.id.UserIDhere);
        listView=(ListView)findViewById(R.id.listview);
        AppDBHelper contactDBHelper=new AppDBHelper(getApplicationContext());
        SQLiteDatabase database=contactDBHelper.getReadableDatabase();
        Cursor cursor=contactDBHelper.getContactName(database);
        contact=new ArrayList<Contact>();
        while (cursor.moveToNext())
        {
            Contact contact1=new Contact(cursor.getString(0),cursor.getString(1));
            contact.add(contact1);
        }
        CustomAdapter customAdapter=new CustomAdapter();
        listView.setAdapter(customAdapter);

        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserID=editText.getText().toString();
                ChatIn();
            }
        });

    }
    class CustomAdapter extends BaseAdapter
    {

        @Override
        public int getCount() {
            return 0;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            convertView=getLayoutInflater().inflate(R.layout.customlistview,null);
            TextView textView=(TextView)findViewById(R.id.emailtext);
            TextView textView2=(TextView)findViewById(R.id.useridtext);

            textView.setText(contact.get(0).getEmail());
            textView2.setText(contact.get(1).getUserID());




            return convertView;
        }
    }
    public void ChatIn()
    {
        Intent intent=new Intent(this,ChatActivity.class);
        startActivity(intent);
    }
}
