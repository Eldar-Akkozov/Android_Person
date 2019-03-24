package com.example.android_person.Activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android_person.API;
import com.example.android_person.Helper;
import com.example.android_person.JSON;
import com.example.android_person.R;

import java.net.HttpURLConnection;

public class MainActivity extends AppCompatActivity {

    EditText etUserName;
    EditText etPassword;

    public Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void OnClick (View view) throws Exception {

        etUserName = (EditText) findViewById(R.id.editText2);
        etPassword = (EditText) findViewById(R.id.editText);

        String sName = etUserName.getText().toString();
        String sPassword = etPassword.getText().toString();

        if(sName.equals("") || sPassword.equals("")) {
            Toast.makeText(this, "Введите параметры", Toast.LENGTH_SHORT).show();
            return;
        }

        API apiWork = new API();

        String Reselt = apiWork.execute(Helper.LOG_IN, JSON.logIn(sName, sPassword)).get();
        int responseCode = apiWork.getCode();

        Toast.makeText(this, Reselt + " " + responseCode, Toast.LENGTH_SHORT).show();

        if(responseCode == HttpURLConnection.HTTP_BAD_REQUEST) {
            makeDialogWindow("Ошибка авторизация","Пользователь с такими параметрами не сушествует. Либо не верно введеные параметры");
            return;
        } else if (responseCode == HttpURLConnection.HTTP_OK){
            intent = new Intent(this, FunctionalActivity.class);
            intent.putExtra("JSON", Reselt);
            startActivity(intent);
        }

    }

    public void makeDialogWindow (String Title, String Message) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle(Title);
        dialog.setMessage(Message);
        dialog.setNeutralButton("ОК", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        AlertDialog alertDialog = dialog.create();
        alertDialog.show();
    }

    public void onClickTextReg (View view) {
        startActivity(new Intent(MainActivity.this, RegistriActivity.class));
    }

    public void onBackPressed() {
        finish();
        System.exit(0);
    }

}
