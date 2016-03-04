package com.experiment.sandbox.myfirstapp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.app.ListActivity;

import com.experiment.sandbox.myfirstapp.managers.Address;
import com.experiment.sandbox.myfirstapp.managers.User;

/**
 * Created by Joe on 3/3/2016.
 */
public class UserListActivity extends ListActivity {

    private TextView text;
    private List<String> listValues;
    private List<User> mUserList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        Intent intent = getIntent();
        mUserList = (List<User>) intent.getSerializableExtra("users");
        listValues = new ArrayList<String>();

        for (int i=0; i < mUserList.size(); ++i) {
            User user = mUserList.get(i);
            Address address = user.getAddress();
            listValues.add(user.getUsername());
            listValues.add(address.getStreet() + ", " +address.getSuite() + ", " + address.getCity() + " " + address.getZipcode());
        }

        // initiate the listadapter
        ArrayAdapter<String> myAdapter = new ArrayAdapter(this,
                R.layout.row_layout, R.id.listText, listValues);

        // assign the list adapter
        setListAdapter(myAdapter);

    }

    // when an item of the list is clicked
    @Override
    protected void onListItemClick(ListView list, View view, int position, long id) {
        super.onListItemClick(list, view, position, id);

        String selectedItem = (String) getListView().getItemAtPosition(position);
        //String selectedItem = (String) getListAdapter().getItem(position);

        text.setText("You clicked " + selectedItem + " at position " + position);
    }

}
