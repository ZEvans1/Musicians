package com.example.zach.musicians.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.zach.musicians.R;
import com.example.zach.musicians.models.User;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class FirebaseUserViewHolder extends RecyclerView.ViewHolder {
    View mView;
    Context mContext;
    FirebaseUser user;

    public FirebaseUserViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
    }

    public void bindUser(User user) {
        TextView listUserNameTextView = (TextView) mView.findViewById(R.id.listUserNameTextView);
        listUserNameTextView.setText(user.getUserName());
    }

}

//    DatabaseReference ref = FirebaseDatabase.getInstance().getReference("users");
//        ref.addListenerForSingleValueEvent(new ValueEventListener() {
//@Override
//public void onDataChange(DataSnapshot dataSnapshot) {
//        List<User> users = new ArrayList<User>();
//        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
//        users.add(snapshot.getValue(User.class));
//        }
//        int itemPosition = getLayoutPosition();
//        }
//
//@Override
//public void onCancelled(DatabaseError databaseError) {
//        Log.d("User on click", databaseError.toString());
//        }
//        });
