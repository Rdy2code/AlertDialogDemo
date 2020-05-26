package com.example.android.dialogs;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.android.dialogs.model.Person;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity implements CustomDialogFragment.DataEntryListener  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        if (fab == null) throw new AssertionError();
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //showDialog();     //Use this for standard dialog
                showCustomDialog(); //Use this for custom layout
            }
        });
    }

    //Custom DialogFragment
    private void showCustomDialog() {
        //Pass in a parcelable model data object
        Person p = new Person("Louis", "W", 35);
        CustomDialogFragment customDialogFragment = CustomDialogFragment.newInstance(p);
        customDialogFragment.show(getSupportFragmentManager(), "CustomDialog");
        customDialogFragment.setCancelable(false);
    }

    //Standard DialogFragment
    private void showDialog() {
        AlertDialogFragment alertDialogFragment = new AlertDialogFragment();
        alertDialogFragment.show(getSupportFragmentManager(), "AlertDialogFragment");
        alertDialogFragment.setCancelable(false);
    }

    @Override
    public void onDataEntryDone(Person person) {
        Toast.makeText(this, "Name: " + person.getFirstName(), Toast.LENGTH_SHORT).show();
    }
}
