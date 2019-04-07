package org.cit360.android.app;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.cit360.app.app.R;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class FunctionMenuActivity extends AppCompatActivity {
    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    private UserLogoutTask mAuthTask = null;
    private String sessionId;
    private String sessionUserId;

    // UI references.
    private View mProgressView;
    private View mMainMenuFormView;

    // Variables needed for httpurlconnections
    private final String USER_AGENT = "Mozilla/5.0";

    // Turn POST parameters into the URL string
    private String getPostDataString(LinkedHashMap<String, String> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for(Map.Entry<String, String> entry : params.entrySet()){
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }

        return result.toString();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_function_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Get Session Id and User Id from Shared Preferences
        SharedPreferences sharedpreferences = getSharedPreferences(MainActivity.AppPREFERENCES, Context.MODE_PRIVATE);
        sessionId = sharedpreferences.getString("SessionId", null);
        sessionUserId = sharedpreferences.getString("SessionUserId", null);

        Button mEnterUtilityButton = (Button) findViewById(R.id.buttonEnterUtility);
        mEnterUtilityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enterUtility();
            }
        });

        Button mSearchButton = (Button) findViewById(R.id.buttonSearch);
        mSearchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                search();
            }
        });

        Button mExitButton = (Button) findViewById(R.id.buttonExit);
        mExitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logout();
            }
        });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mMainMenuFormView = findViewById(R.id.MainMenu);
        mProgressView = findViewById(R.id.logout_progress);

    }

    /**
     * Go to the AddUtility Activity and supply the sessionid and userid
     */
    private void enterUtility() {
        Intent intent = new Intent(this, AddUtility.class);
        intent.putExtra("SessionId", sessionId);
        intent.putExtra("UserId", sessionUserId);
        startActivity(intent);
        }

    /**
     * Go to the Search Activity and supply the sessionid and userid
     */
    private void search() {
        Intent intent = new Intent(this, SearchUtility.class);
        intent.putExtra("SessionId", sessionId);
        intent.putExtra("UserId", sessionUserId);
        startActivity(intent);
    }

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void logout() {
        if (mAuthTask != null) {
            return;
        }

        // Show a progress spinner, and kick off a background task to
        // perform the user login attempt.
        showProgress(true);
        mAuthTask = new UserLogoutTask(sessionId, sessionUserId);
        mAuthTask.execute((Void) null);
    }

    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show) {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mMainMenuFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mMainMenuFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mMainMenuFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        } else {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mMainMenuFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    /**
     * Represents an asynchronous logout task used to close the current session
     */
    public class UserLogoutTask extends AsyncTask<Void, Void, Boolean> {

        private final String mSessionId;
        private final String mSessionUserId;

        UserLogoutTask(String sessionId, String sessionUserId) {
            mSessionId = sessionId;
            mSessionUserId = sessionUserId;
        }

        @Override
        protected Boolean doInBackground(Void... params) {

            // Populate User hashmap
            HashMap<String, Object> userHashMap = new HashMap<String, Object>();
            userHashMap.put("SessionId", mSessionId);
            // String to hold serialized data
            String userJSON = new String();
            ObjectMapper mapper = new ObjectMapper();

            try {
                // Serialize user object to json string
                userJSON = mapper.writeValueAsString(userHashMap);
                System.out.println("\nUser json string:" + userJSON);
            }
            catch (JsonParseException e) {e.printStackTrace();}
            catch (JsonMappingException e) {e.printStackTrace();}
            catch (IOException e) {e.printStackTrace();}

            // Setup HTTP call parameters
            String utilityServlet = "http://10.0.2.2:8080/CIT360Servlet_war_exploded/UtilityServlet";
            LinkedHashMap<String, String> logoutParams = new LinkedHashMap<String, String>();
            logoutParams.put("Command", "Logout");
            logoutParams.put("DataObject", userJSON);


            System.out.println("Testing - Send Http GET request to servlet");
            try {
                // Perform HTTP Post
                URL obj = new URL(utilityServlet);
                HttpURLConnection con = (HttpURLConnection) obj.openConnection();

                //add request header
                con.setRequestMethod("POST");
                con.setRequestProperty("User-Agent", USER_AGENT);
                con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
                con.setRequestProperty("Cookie", "JSESSIONID="+mSessionId);
                // Send post request
                con.setDoOutput(true);
                try {
                    DataOutputStream wr = new DataOutputStream(con.getOutputStream());
                    // Set Parameter values
                    wr.writeBytes(getPostDataString(logoutParams));
                    wr.flush();
                    wr.close();
                } catch (Exception e) {
                    System.out.println("Error communicating with URL is " + e.getMessage());
                }

                int responseCode = con.getResponseCode();
                System.out.println("\nSending 'POST' request to URL : " + utilityServlet);
                System.out.println("Post parameters : " + logoutParams);
                System.out.println("Response Code : " + responseCode);

                String responseStatus = con.getHeaderField("Status");
                if (responseStatus.equals("Success")) {

                    // Remove Session Id to Shared Preferences
                    SharedPreferences sharedpreferences = getSharedPreferences(MainActivity.AppPREFERENCES, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedpreferences.edit();
                    editor.remove("SessionId");
                    editor.remove("SessionUserId");
                    editor.commit();
                    return true;
                } else {
                    return false;
                }

            } catch (Exception e) {
                return false;
            }
        }

        @Override
        protected void onPostExecute(final Boolean success) {
            mAuthTask = null;
            showProgress(false);

            if (success) {
                finish();
            }
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
            showProgress(false);
        }
    }
}
