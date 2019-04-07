package org.cit360.android.app;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.*;
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
import java.util.*;

public class AddUtility extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private String sessionId;
    private String sessionUserId;
    private SubmitUtilityTask mAuthTask = null;
    private String mBillingMonth;
    private String mCity;
    private String mState;
    private String mZipcode;
    private String mUtilityType;
    private String mAmount;

    // UI references.
    private Spinner mBillingMonthView;
    private EditText mCityView;
    private Spinner mStateView;
    private EditText mZipcodeView;
    private Spinner mUtilityTypeView;
    private EditText mAmountView;
    private View mProgressView;
    private View mAddUtilityFormView;

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
        setContentView(R.layout.activity_add_utility);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Get Session Id and User Id from Shared Preferences
        SharedPreferences sharedpreferences = getSharedPreferences(MainActivity.AppPREFERENCES, Context.MODE_PRIVATE);
        sessionId = sharedpreferences.getString("SessionId", null);
        sessionUserId = sharedpreferences.getString("SessionUserId", null);

        Spinner spinnerBillingMonth = (Spinner) findViewById(R.id.spinnerBillingMonth);
        // Billing Month Spinner click listener
        spinnerBillingMonth.setOnItemSelectedListener(this);

        Spinner spinnerState = (Spinner) findViewById(R.id.spinnerState);
        // State Spinner click listener
        spinnerState.setOnItemSelectedListener(this);

        Spinner spinnerUtilityType = (Spinner) findViewById(R.id.spinnerUtilityType);
        // Utility Type Spinner click listener
        spinnerUtilityType.setOnItemSelectedListener(this);

        mCityView = (EditText) findViewById(R.id.textCity);
        mZipcodeView = (EditText) findViewById(R.id.numberPostalCode);
        mAmountView = (EditText) findViewById(R.id.numberDecimalAmount);
        mAmountView.addTextChangedListener(new NumberTextWatcher(mAmountView, "#,###"));
        mAmountView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                    submitUtility();
                    return true;
                }
                return false;
            }
        });

        Button mSubmitButton = (Button) findViewById(R.id.buttonSubmit);
        mSubmitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                submitUtility();
            }
        });

        mAddUtilityFormView = findViewById(R.id.AddUtility);
        mProgressView = findViewById(R.id.add_progress);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();
        switch (parent.getId()) {
            case R.id.spinnerBillingMonth:
                mBillingMonth = item;
                break;
            case R.id.spinnerState:
                mState = item;
                break;
            case R.id.spinnerUtilityType:
                mUtilityType = item;
                break;
        }
        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

    }

    /**
     * Validates the fields values and submits data to servlet.
     */
    private void submitUtility() {
        if (mAuthTask != null) {
            return;
        }

        // Reset errors.
        mCityView.setError(null);
        mZipcodeView.setError(null);
        mAmountView.setError(null);

        // Store values at the time of the login attempt.
        mCity = mCityView.getText().toString();
        mZipcode = mZipcodeView.getText().toString();
        mAmount = mAmountView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // Check if the user selected a Billing Month
        if (TextUtils.isEmpty(mBillingMonth)) {
            //mBillingMonthView.setError(getString(R.string.error_invalid_password));
            focusView = mBillingMonthView;
            cancel = true;
        }
        // Check for a City value.
        if (TextUtils.isEmpty(mCity)) {
            mCityView.setError(getString(R.string.error_field_required));
            focusView = mCityView;
            cancel = true;
        }
        // Check if the user selected a State
        if (TextUtils.isEmpty(mState)) {
            //mBillingMonthView.setError(getString(R.string.error_invalid_password));
            focusView = mStateView;
            cancel = true;
        }
        // Check for a Zipcode value.
        if (TextUtils.isEmpty(mZipcode)) {
            mZipcodeView.setError(getString(R.string.error_field_required));
            focusView = mZipcodeView;
            cancel = true;
        }
        // Check for an Amount value.
        if (TextUtils.isEmpty(mAmount)) {
            mAmountView.setError(getString(R.string.error_field_required));
            focusView = mAmountView;
            cancel = true;
        }

        if (cancel) {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true);
            mAuthTask = new SubmitUtilityTask(sessionId, sessionUserId, mBillingMonth, mCity, mState, mZipcode, mUtilityType, mAmount);
            mAuthTask.execute((Void) null);
        }
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

            mAddUtilityFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mAddUtilityFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mAddUtilityFormView.setVisibility(show ? View.GONE : View.VISIBLE);
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
            mAddUtilityFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }
    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    public class SubmitUtilityTask extends AsyncTask<Void, Void, Boolean> {

        private final String mSessionId;
        private final String mSessionUserId;
        private final String mBillingMonth;
        private final String mCity;
        private final String mState;
        private final String mZipcode;
        private final String mUtilityType;
        private final String mAmount;

        SubmitUtilityTask(String sessionId, String sessionUserId, String billingMonth, String city, String state, String zipcode, String utilityType, String amount) {
            mSessionId = sessionId;
            mSessionUserId = sessionUserId;
            mBillingMonth = billingMonth;
            mCity = city;
            mState = state;
            mZipcode = zipcode;
            mUtilityType = utilityType;
            mAmount = amount;
        }

        @Override
        protected Boolean doInBackground(Void... params) {

            // Populate data hashmap
            HashMap<String, Object> dataHashMap = new HashMap<String, Object>();
            dataHashMap.put("BillingMonth", mBillingMonth);
            dataHashMap.put("City", mCity);
            dataHashMap.put("State", mState);
            dataHashMap.put("Zipcode", mZipcode);
            dataHashMap.put("UtilityType", mUtilityType);
            dataHashMap.put("Amount", mAmount);
            // String to hold serialized data
            String userJSON = new String();
            ObjectMapper mapper = new ObjectMapper();

            try {
                // Serialize user object to json string
                userJSON = mapper.writeValueAsString(dataHashMap);
                System.out.println("\nUser json string:" + userJSON);
            } catch (JsonParseException e) {
                e.printStackTrace();
            } catch (JsonMappingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Setup HTTP call parameters
            String utilityServlet = "http://10.0.2.2:8080/CIT360Servlet_war_exploded/UtilityServlet";
            LinkedHashMap<String, String> dataParams = new LinkedHashMap<String, String>();
            dataParams.put("Command", "AddUtility");
            dataParams.put("DataObject", userJSON);


            System.out.println("Testing - Send Http GET request to servlet");
            try {
                // Perform HTTP Post
                URL obj = new URL(utilityServlet);
                HttpURLConnection con = (HttpURLConnection) obj.openConnection();

                //add request header
                con.setRequestMethod("POST");
                con.setRequestProperty("User-Agent", USER_AGENT);
                con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
                con.setRequestProperty("Cookie","JSESSIONID=" + mSessionId);
                // Send post request
                con.setDoOutput(true);
                try {
                    DataOutputStream wr = new DataOutputStream(con.getOutputStream());
                    // Set Parameter values
                    wr.writeBytes(getPostDataString(dataParams));
                    wr.flush();
                    wr.close();
                } catch (Exception e) {
                    System.out.println("Error communicating with URL is " + e.getMessage());
                }

                int responseCode = con.getResponseCode();
                System.out.println("\nSending 'POST' request to URL : " + utilityServlet);
                System.out.println("Post parameters : " + dataParams);
                System.out.println("Response Code : " + responseCode);

                String responseStatus = con.getHeaderField("Status");
                if (responseStatus.equals("Success")) {
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
            } else {
//                mPasswordView.setError(getString(R.string.error_incorrect_password));
//                mPasswordView.requestFocus();
            }
        }

        @Override
        protected void onCancelled() {
            mAuthTask = null;
            showProgress(false);
        }
    }
}
