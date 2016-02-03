package meteor.android.todo;

import android.app.Application;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;

import im.delight.android.ddp.MeteorCallback;
import im.delight.android.ddp.MeteorSingleton;
import im.delight.android.ddp.ResultListener;

public class MainActivity extends AppCompatActivity implements MeteorCallback {


    EditText mEmail;
    EditText mPassword;
    App app;
    Button mLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        app = (App) this.getApplication();

        mEmail = (EditText) findViewById(R.id.mEmail);
        mPassword = (EditText) findViewById(R.id.mPassword);
        mLogin = (Button) findViewById(R.id.button);

        mLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                login(mEmail.getText().toString(), mPassword.getText().toString());
            }
        });
    }


    void login(final String email, final String password)
    {
        MeteorSingleton.getInstance().loginWithEmail(email, password, new ResultListener() {
            @Override
            public void onSuccess(String result) {
                System.out.println("Successfully logged in: " + result);
                System.out.println("Is logged in: " + MeteorSingleton.getInstance().isLoggedIn());
                System.out.println("User ID: " + MeteorSingleton.getInstance().getUserId());

                MeteorSingleton.getInstance().call("findUserByEmail", new Object[]{email}, new ResultListener() {

                    @Override
                    public void onSuccess(String result) {
                        System.out.println("findUserByEmail: " + result);
                        Gson gson = new Gson();
                        //User user = null;
                        //user = User.fromJson(result);
                       // Application.user = user;
                        //Application.saveUser(result);

                        SharedPreferences settings = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                        SharedPreferences.Editor editor = settings.edit();
                        editor.putString("user", result);
                        editor.commit();

                        //Log success now call intent

                    }

                    @Override
                    public void onError(String error, String reason, String details) {
                        System.out.println(error + " / " + reason + " / " + details);
                    }
                });
            }

            @Override
            public void onError(String error, String reason, String details) {

                System.out.println("Could not log in: " + error + " / " + reason + " / " + details);
            }
        });
    }

    @Override
    public void onConnect(boolean b) {

    }

    @Override
    public void onDisconnect() {

    }

    @Override
    public void onException(Exception e) {

    }

    @Override
    public void onDataAdded(String s, String s1, String s2) {

    }

    @Override
    public void onDataChanged(String s, String s1, String s2, String s3) {

    }

    @Override
    public void onDataRemoved(String s, String s1) {

    }
}
