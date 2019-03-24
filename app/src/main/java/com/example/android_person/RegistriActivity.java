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

import javax.net.ssl.HttpsURLConnection;

public class RegistriActivity extends AppCompatActivity {

    private EditText edName;
    private EditText edLastName;
    private EditText edUserName;
    private EditText edPassword;

    private String Name;
    private String LastName;
    private String UserName;
    private String Password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_registri );
    }

    public void onClickCreatAccount (View view) throws Exception {
        edName = (EditText) findViewById(R.id.editText3);
        edLastName = (EditText) findViewById(R.id.editText4);
        edUserName = (EditText) findViewById(R.id.editText5);
        edPassword = (EditText) findViewById(R.id.editText6);

        Name = edName.getText().toString();
        LastName = edLastName.getText().toString();
        UserName = edUserName.getText().toString();
        Password = edPassword.getText().toString();

        if ((Name.equals("") || LastName.equals("")) || ((UserName.equals("") || Password.equals("")))) {
            Toast.makeText(this, "Введите параметры", Toast.LENGTH_SHORT).show();
            return;
        }

        API api = new API();

        String reselt = api.execute(Helper.REGISER_TO, JSON.registerTo(
                Name, LastName, UserName, Password
        )).get();

        if (api.getCode() == HttpsURLConnection.HTTP_OK) {
            Intent intent = new Intent(RegistriActivity.this, FunctionalActivity.class);
            intent.putExtra("JSON", reselt);
            startActivity(intent);
        } else {
            makeDialogWindow("Ошибка регистрации", "Пользователь с такими параметрами уже создан, попробуйте вести другие параметры ");
        }
    }

    private void makeDialogWindow (String Title, String Message) {
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

    public void onClickTextRegTwo (View view) {
        startActivity(new Intent(RegistriActivity.this, MainActivity.class));
    }
}
