package com.brydenfogelman.startupweekend;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class SignUp extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }

    public void newUser(View view) {

        // Creating strings from text boxes
        String firstname = ((EditText) findViewById(R.id.first_name)).getText().toString();
        String lastname = ((EditText) findViewById(R.id.last_name)).getText().toString();
        String screenname = ((EditText) findViewById(R.id.screen_name)).getText().toString();
        String email = ((EditText) findViewById(R.id.email)).getText().toString();
        String password = ((EditText) findViewById(R.id.password)).getText().toString();
        String age = ((EditText) findViewById(R.id.age)).getText().toString();
        String gender = ((EditText) findViewById(R.id.gender)).getText().toString();


        String create_account_success = new String();

        try {

            create_account_success = new phpLogin(this).execute(firstname, lastname, screenname, email, password, age, gender).get();
            Log.d("activity_login", create_account_success);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        // response type
        // Email already in use
        // username already in use


        // Returns to the SignUp
        // TODO: let user know that they fucked up, work on that implementation
        if (!create_account_success.contains("fail")) {
            Intent intent = new Intent(this, SignUp.class);
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
                String firstname = params[0];
                String lastname = params[1];
                String screenname = params[2];
                String email = params[3];
                String password = params[4];
                String age = params[5];
                String gender = params[6];

                String link = "http://159.203.66.71/Startup Weekend/create_user.php?username=" +
                        screenname + "&email=" + email + "&password=" + password + "&firstname=" +
                        firstname + "&lastname=" + lastname + "&gender=" + gender + "&age" + age;
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
