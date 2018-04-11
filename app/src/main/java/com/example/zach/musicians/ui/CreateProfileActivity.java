package com.example.zach.musicians.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;

import com.example.zach.musicians.Constants;
import com.example.zach.musicians.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CreateProfileActivity extends AppCompatActivity implements View.OnClickListener {
    FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
    private FirebaseAuth mAuth;
    private String setName;
    private String setBio;


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

        mInstrumentButton.setOnClickListener(this);
        mGenreButton.setOnClickListener(this);
        mSetDetailsButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view == mSetDetailsButton) {
            setUserDetails();
        }
    }

    private void setUserDetails() {
        final String name = mSetUserNameEditText.getText().toString().trim();
        final String bio = mSetUserBioEditText.getText().toString().trim();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference("users")
                .child(firebaseUser.getUid())
                .child("userName");

        ref.setValue(name);
    }
}
