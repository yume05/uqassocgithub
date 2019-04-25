package com.example.uqassoc;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import android.support.v7.app.AppCompatActivity;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import java.util.Arrays;
import java.util.List;

public class LogInActivity extends MainActivity {

    public TextView buttonHome;
    public Button buttonLogin = null;
    public Button buttonLoginFbk = null;
    private EditText login_edit;
    private EditText password_edit;
    SharedPreferences pref;
    Intent intent;

   CallbackManager callbackManager = CallbackManager.Factory.create();

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);
        super.onCreateDrawer();
        //super.gestionLogIn();


        intent = new Intent(LogInActivity.this, MainActivity.class);

        navigationView = (NavigationView) findViewById(R.id.activity_main_nav_view);
        View hView =  navigationView.getHeaderView(0);

        pref = getSharedPreferences("user_details", MODE_PRIVATE);

        buttonLogIn = (ImageButton)hView.findViewById(R.id.imageLogIn);
        buttonLogOut = (ImageButton)hView.findViewById(R.id.imageLogOut);
        //super.gestionLogIn();
        buttonLogIn.setVisibility(View.VISIBLE);
        buttonLogOut.setVisibility(View.INVISIBLE);

        buttonLogin = (Button) findViewById(R.id.button_valider_connexion);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                login_edit = (EditText) findViewById(R.id.login_edit);
                password_edit = (EditText) findViewById(R.id.password_edit);
                String login = login_edit.getText().toString();
                 String password = password_edit.getText().toString();
        /*databaseAccess.select("SELECT \n" +
                "    name\n" +
                "FROM \n" +
                "    sqlite_master \n" +
                "WHERE \n" +
                "    type ='table' AND \n" +
                "    name NOT LIKE 'sqlite_%';");*/
                DatabaseAccess databaseAccess = DatabaseAccess.getInstance(LogInActivity.this);
                databaseAccess.open();
                boolean resultat = databaseAccess.selectLogin("SELECT login, password FROM user  WHERE login = '"+login+"' AND password = '"+password+"';");
                databaseAccess.close();
                if (resultat) {
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putString("username", login);
                    editor.putString("password", password);
                    editor.commit();
                    Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Credentials are not valid", Toast.LENGTH_SHORT).show();
                }
                //login_edit = (EditText) findViewById(R.id.login_edit);
                //password_edit = (EditText) findViewById(R.id.password_edit);
                //String login = login_edit.getText().toString();
               // String password = password_edit.getText().toString();

               // Log.i("Connect", list.toString());
               /* if(list.size() > 0){
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putString("username", list.get(1));
                    editor.putString("password", list.get(2));
                    editor.putString("priority", list.get(3));
                    editor.commit();
                    Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(), "Credentials are not valid", Toast.LENGTH_SHORT).show();
                }*/
                /*if (login.equals("root") && password.equals("ini01")) {
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putString("username", login);
                    editor.putString("password", password);
                    editor.commit();
                    Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                    startActivity(intent);
                } else {
                    Toast.makeText(getApplicationContext(), "Credentials are not valid", Toast.LENGTH_SHORT).show();
                }*/

            }
        });

    }
}
