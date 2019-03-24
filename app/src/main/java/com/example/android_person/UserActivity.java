package com.example.android_person.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android_person.R;

import org.json.JSONException;
import org.json.JSONObject;

public class UserActivity extends AppCompatActivity {

    String Reselt = "";
    JSONObject jsonObject;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        Reselt = getIntent().getStringExtra("JSON");

        makeText();
    }

    public void makeText () {

        String Name = "";
        String LastName = "";

        TextView textView = (TextView) findViewById(R.id.textView5);

        try {
            jsonObject = new JSONObject(Reselt);
            JSONObject jsonObjectTwo = new JSONObject(jsonObject.getString("user"));
            LastName = jsonObjectTwo.getString("first_name");
            Name = jsonObjectTwo.getString("last_name");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Toast.makeText(this, jsonObject.toString(), Toast.LENGTH_LONG).show();
        textView.setText("Имя: " + Name + "\n" + "Фамилия: " + LastName + "\n" + "Пол: " + "defaut" + "\n" + "Регион " + "defaut" + "\n" + "Номер: " + "defaut" + "\n" + "Mail: " + "defaut" );

    }

    @Override
    public void onBackPressed() {
        // super.onBackPressed();
        Intent intent = new Intent(this, FunctionalActivity.class);
        intent.putExtra("JSON", Reselt);
        startActivity(intent);
    }

}
