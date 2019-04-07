package org.cit360.android.app;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
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

public class SearchUtility extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Spinner spinnerAddress;
    private String sessionId;
    private String sessionUserId;
    private String mAddress;

    // UI references.
    private Spinner mAddressesView;
    private View mSearchFormView;

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
        setContentView(R.layout.activity_search_utility);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Get Session Id and User Id from Shared Preferences
        SharedPreferences sharedpreferences = getSharedPreferences(MainActivity.AppPREFERENCES, Context.MODE_PRIVATE);
        sessionId = sharedpreferences.getString("SessionId", null);
        sessionUserId = sharedpreferences.getString("SessionUserId", null);

        spinnerAddress = (Spinner) findViewById(R.id.spinnerAddress);

        // spinner item select listener
        spinnerAddress.setOnItemSelectedListener(this);
        new GetAddresses(sessionId, sessionUserId).execute();

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
        mAddress = item;
        }
    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

    }

    /**
     * Async task to get all user addresses
     * */
    public class GetAddresses extends AsyncTask<Void, Void, Boolean> {

        private final String mSessionId;
        private final String mSessionUserId;
        private List<String> addressList = new ArrayList<>();

        GetAddresses(String sessionId, String sessionUserId) {
            mSessionId = sessionId;
            mSessionUserId = sessionUserId;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected Boolean doInBackground(Void... arg0) {
            String addressJSON;
            // data hashmap
            HashMap<String, Object> dataHashMap = new HashMap<String, Object>();
            dataHashMap.put("UserId", mSessionUserId);
            // String to hold serialized data
            String dataJSON = new String();
            ObjectMapper mapper = new ObjectMapper();

            try {
                // Serialize user object to json string
                dataJSON = mapper.writeValueAsString(dataHashMap);
                System.out.println("\nUser json string:" + dataJSON);
            } catch (JsonParseException e) {
                e.printStackTrace();
            } catch (JsonMappingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Setup HTTP call parameters
            String utilityServlet = "http://10.0.2.2:8080/CIT360Servlet_war_exploded/UtilityServlet";
            LinkedHashMap<String, String> callParams = new LinkedHashMap<String, String>();
            callParams.put("Command", "GetAddresses");
            callParams.put("DataObject", dataJSON);


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
                    wr.writeBytes(getPostDataString(callParams));
                    wr.flush();
                    wr.close();
                } catch (Exception e) {
                    System.out.println("Error communicating with URL is " + e.getMessage());
                }

                int responseCode = con.getResponseCode();
                System.out.println("\nSending 'POST' request to URL : " + utilityServlet);
                System.out.println("Post parameters : " + callParams);
                System.out.println("Response Code : " + responseCode);

                String responseStatus = con.getHeaderField("Status");
                if (responseStatus.equals("Success")) {
                    addressJSON = con.getHeaderField("User Addresses");
                    // Create mapper, this is reusable.
                    // ObjectMapper mapper = new ObjectMapper();
                    try {
                        // Import a new user - read json string from file and change to user object and save it
                        addressList = mapper.readValue(addressJSON, List.class);
                        System.out.println("Address List created from json value: " + addressJSON);
                    }
                    catch (JsonParseException e) {e.printStackTrace();}
                    catch (JsonMappingException e) {e.printStackTrace();}
                    catch (IOException e) {e.printStackTrace();}

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
            super.onPostExecute(success);
            populateSpinner();
        }

        /**
         * Adding spinner data
         * */
        private void populateSpinner() {
            List<String> lables = new ArrayList<>();

            for (int i = 0; i < addressList.size(); i++) {
                lables.add(addressList.get(i));
            }

            // Creating adapter for spinner
            ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, lables);

            // Drop down layout style - list view with radio button
            spinnerAdapter
                    .setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            // attaching data adapter to spinner
            spinnerAddress.setAdapter(spinnerAdapter);
        }

    }
}
