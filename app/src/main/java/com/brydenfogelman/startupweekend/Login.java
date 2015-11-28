package com.brydenfogelman.startupweekend;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class Login extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void login(View view) {

        String username = ((EditText) findViewById(R.id.email)).getText().toString();
        String password = ((EditText) findViewById(R.id.password)).getText().toString();
        String login_success = new String();

        try {

            login_success = new phpLogin(this).execute(username, password).get();
            Log.d("activity_login", login_success);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        if (!login_success.contains("fail")) {
            Intent intent = new Intent(this, Find_trails.class);
            startActivity(intent);
        }
    }


    class phpLogin extends AsyncTask<String, Void, String> {
        private Context context;

        public phpLogin(Context context) {
            this.context = context;
        }

        @Override
        protected String doInBackground(String... params) {
            try {
                String email = params[0].trim();
                String password = params[1].trim();
                String link = "http://159.203.66.71/Startup%20Weekend/signin.php?email=" + email + "&password=" + password;
                Log.d("link", link);

                URL url = new URL(link);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestProperty("User-Agent", "");
                connection.setRequestMethod("GET");
                connection.setDoInput(true);
                connection.connect();
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));

                StringBuffer sb = new StringBuffer("");
                String line = "";
                while ((line = in.readLine()) != null) {
                    sb.append(line);
                }
                in.close();
                Log.d("result", sb.toString());
                return sb.toString();
            } catch (Exception e) {
                return new String("Exception: " + e.getMessage());
            }
        }
    }
}



