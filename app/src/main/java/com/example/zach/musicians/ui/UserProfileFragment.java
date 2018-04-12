package com.example.zach.musicians.ui;


import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.zach.musicians.Constants;
import com.example.zach.musicians.R;
import com.example.zach.musicians.models.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.parceler.Parcels;
import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UserProfileFragment extends Fragment {
    @BindView(R.id.userNameTextView) TextView mUserNameTextView;
    @BindView(R.id.userBioTextView) TextView mUserBioTextView;
    @BindView(R.id.instrumentTextView) TextView mInstrumentTextView;


    private User mUser;

    public UserProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_profile, container, false);
        ButterKnife.bind(this, view);

        FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseDatabase currentUserDB = FirebaseDatabase.getInstance();
        DatabaseReference currentUserRef = currentUserDB.getReference("users")
                .child(firebaseUser.getUid());

        currentUserRef.child("userName").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String userName = dataSnapshot.getValue(String.class);
                mUserNameTextView.setText(userName);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });

        currentUserRef.child("userBio").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String userBio = dataSnapshot.getValue(String.class);
                mUserBioTextView.setText(userBio);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });


        currentUserRef.child("userInstruments").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<String> userInstruments = new ArrayList<>();
                for (DataSnapshot childSnapshot: dataSnapshot.getChildren()) {
                    userInstruments.add(childSnapshot.getValue().toString());
                }
                mInstrumentTextView.setText(userInstruments.get(0));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d("Instruments","Error");
            }
        });

        return view;
    }

    }
