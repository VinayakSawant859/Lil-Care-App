package com.example.lilcare;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DatabaseClass extends parentRegPage {

    private DatabaseReference databaseReference;
    public DatabaseClass(){
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        databaseReference = db.getReference(Users.class.getSimpleName());
    }

    // Firebase is working..
    public Task<Void> add(Users user)
    {
        return databaseReference.push().setValue(user);
    }
}
