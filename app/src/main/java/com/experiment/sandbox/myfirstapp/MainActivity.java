package com.experiment.sandbox.myfirstapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.JsonReader;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.experiment.sandbox.myfirstapp.managers.Address;
import com.experiment.sandbox.myfirstapp.managers.Geo;
import com.experiment.sandbox.myfirstapp.managers.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TextView mGetUsers;
    private List<User> mUserList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mGetUsers = (TextView) findViewById(R.id.getUsers);

        createRequest();



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void createRequest() {

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "http://jsonplaceholder.typicode.com/users";

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            mUserList = readStringToJson(response);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mGetUsers.setText("That didn't work!");
            }
        });
        // Add the request to the RequestQueue.
        queue.add(stringRequest);

    }

    public List<User> readStringToJson(String in) throws JSONException, IOException {
        JSONArray jsonArray = new JSONArray(in);

        return readJsonArray(jsonArray);
    }

    public List<User> readJsonArray(JSONArray in) throws IOException, JSONException {
        List<User> users = new ArrayList();

        for (int i = 0; i < in.length(); i++) {
            User newUser = new User();
            Address newAddress = new Address();
            Geo newGeo = new Geo();

            JSONObject currentUser;
            JSONObject currentAddress;
            JSONObject currentGeo;

            currentUser = in.getJSONObject(i);
            currentAddress = currentUser.getJSONObject("address");
            currentGeo = currentAddress.getJSONObject("geo");

            newUser.setId(Integer.parseInt(currentUser.get("id").toString()));
            newUser.setName(currentUser.get("name").toString());
            newUser.setUsername(currentUser.get("username").toString());
            newUser.setEmail(currentUser.get("email").toString());

            newAddress.setStreet(currentAddress.get("street").toString());
            newAddress.setSuite(currentAddress.get("suite").toString());
            newAddress.setCity(currentAddress.get("city").toString());
            newAddress.setZipcode(currentAddress.get("zipcode").toString());

            newGeo.setLat(currentGeo.get("lat").toString());
            newGeo.setLng(currentGeo.get("lng").toString());

            newAddress.setGeo(newGeo);
            newUser.setAddress(newAddress);

            users.add(newUser);
        }

        return users;
    }

    public void showUsersClicked(View v) {
        // does something very interesting

        Intent mIntent = new Intent(this, UserListActivity.class);
        mIntent.putExtra("users", (Serializable) mUserList);
        startActivity(mIntent);
        finish();
    }

}
