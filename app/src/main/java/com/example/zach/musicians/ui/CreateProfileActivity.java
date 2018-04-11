package com.example.zach.musicians.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.example.zach.musicians.Constants;
import com.example.zach.musicians.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CreateProfileActivity extends AppCompatActivity implements View.OnClickListener {
    FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
    private FirebaseAuth mAuth;
    private ArrayList<String> instrumentArray = new ArrayList<String>();

    @BindView(R.id.setUserNameEditText) EditText mSetUserNameEditText;
    @BindView(R.id.instrumentSpinner) Spinner mInstrumentSpinner;
    @BindView(R.id.instrumentButton) Button mInstrumentButton;
    @BindView(R.id.genreSpinner) Spinner mGenreSpinner;
    @BindView(R.id.genreButton) Button mGenreButton;
    @BindView(R.id.setUserBioEditText) EditText mSetUserBioEditText;
    @BindView(R.id.collaboratorSwitch) Switch mCollaboratorSwitch;
    @BindView(R.id.giggerSwitch) Switch mGiggerSwitch;
    @BindView(R.id.setDetailsButton) Button mSetDetailsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_profile);
        ButterKnife.bind(this);

        mAuth = FirebaseAuth.getInstance();
        FirebaseDatabase instrumentDB = FirebaseDatabase.getInstance();
        DatabaseReference instrumentRef = instrumentDB.getReference("users")
                .child(firebaseUser.getUid())
                .child("userInstruments");

        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                GenericTypeIndicator<ArrayList<String>> t = new GenericTypeIndicator<ArrayList<String>>() {};
                ArrayList<String> mInstrumentArray = snapshot.getValue(t);
                Log.d("hello", mInstrumentArray.toString());
            }
            @Override
            public void onCancelled(DatabaseError firebaseError) {
                Log.e("The read failed: " ,firebaseError.getMessage());
            }
        };
        instrumentRef.addValueEventListener(postListener);


        Spinner instrumentSpinner = (Spinner) findViewById(R.id.instrumentSpinner);
        ArrayAdapter<CharSequence> instrumentAdapter = ArrayAdapter.createFromResource(this,
                R.array.instruments_array, android.R.layout.simple_spinner_item);
        instrumentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        instrumentSpinner.setAdapter(instrumentAdapter);

        Spinner genreSpinner = (Spinner) findViewById(R.id.genreSpinner);
        ArrayAdapter<CharSequence> genreAdapter = ArrayAdapter.createFromResource(this,
                R.array.genres_array, android.R.layout.simple_spinner_item);
        genreAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genreSpinner.setAdapter(genreAdapter);

        Log.d("array", instrumentArray.toString());

        mInstrumentButton.setOnClickListener(this);
        mGenreButton.setOnClickListener(this);
        mSetDetailsButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == mInstrumentButton) {
            String instrument = mInstrumentSpinner.getSelectedItem().toString();
            instrumentArray.add(instrument);
            Log.d("adding", instrument);
            Log.d("array", instrumentArray.toString());
        }
        if (view == mSetDetailsButton) {
            setUserDetails();
        }
    }

    private void setUserDetails() {
        final String name = mSetUserNameEditText.getText().toString().trim();
        final String bio = mSetUserBioEditText.getText().toString().trim();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference nameRef = database.getReference("users")
                .child(firebaseUser.getUid())
                .child("userName");

        if (!name.equals("")) {
            nameRef.setValue(name);
        }

        DatabaseReference bioRef = database.getReference("users")
                .child(firebaseUser.getUid())
                .child("userBio");

        if (!bio.equals("")) {
            bioRef.setValue(bio);
        }

        DatabaseReference instrumentRef = database.getReference("users")
                .child(firebaseUser.getUid())
                .child("userInstruments");

        if (!instrumentArray.isEmpty()) {
            instrumentRef.setValue(instrumentArray);
        }

    }
}
