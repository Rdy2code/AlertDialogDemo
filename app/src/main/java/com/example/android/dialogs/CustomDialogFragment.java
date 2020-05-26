package com.example.android.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.android.dialogs.model.Person;

public class CustomDialogFragment extends DialogFragment {

    private DataEntryListener mListener;
    private static final String PERSON_KEY = "PERSON_KEY";
    private EditText etFirstName, etLastName, etAge;

    public static CustomDialogFragment newInstance(Person person) {
        Bundle args = new Bundle();
        args.putParcelable(PERSON_KEY, person);
        CustomDialogFragment fragment = new CustomDialogFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        mListener = (DataEntryListener) getActivity();
        Person person = new Person();
        if (getArguments() != null) {
            person = getArguments().getParcelable(PERSON_KEY);
        }
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View rootView = inflater.inflate(R.layout.alert_dialog, null);
        etFirstName = rootView.findViewById(R.id.textFirstName);
        etLastName = rootView.findViewById(R.id.textLastName);
        etAge = rootView.findViewById(R.id.textAge);
        etFirstName.setText(person.getFirstName());
        etLastName.setText(person.getLastName());
        etAge.setText(String.valueOf(person.getAge()));

        alertDialog.setView(rootView)
                .setPositiveButton(R.string.save, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        saveData();
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Do something else here
                    }
                });
        return alertDialog.create();
    }

    private void saveData() {
        Person person = new Person();
        String firstName = etFirstName.getText().toString().trim();
        String lastName = etLastName.getText().toString().trim();
        int age = Integer.parseInt(etAge.getText().toString());
        person.setFirstName(firstName);
        person.setLastName(lastName);
        person.setAge(age);
        mListener.onDataEntryDone(person);
        dismiss();
    }

    public interface DataEntryListener {
        void onDataEntryDone(Person person);
    }


}
