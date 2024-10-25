package com.upn.contactsapp;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.upn.contactsapp.entities.Contact;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FirebaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase);

        EditText etname = findViewById(R.id.etName);
        Button btn = findViewById(R.id.btnCreateOnFirebase);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference contRef = database.getReference("N00245044").child("contacts");
        List<Contact> list = new ArrayList<>();
        contRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //Log.i("Datos",snapshot.toString());
                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    Contact c = dataSnapshot.getValue(Contact.class);
                    list.add(c);
                    Log.i("Contacts", c.uuid);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btn.setOnClickListener(v -> {
            // Write a message to the database
            //FirebaseDatabase database = FirebaseDatabase.getInstance();
            //DatabaseReference myRef = database.getReference("N00290851");

            //DatabaseReference table = myRef.child("contacts");

            //Contact c1 = new Contact("Luis", "12345678");
            //c1.uuid = UUID.randomUUID().toString();
            //Contact c2 = new Contact("Miguel", "123456");
            //c2.uuid = UUID.randomUUID().toString();
            String nombre = etname.getText().toString();
            Contact c3 = new Contact(nombre, "123456");
            c3.uuid = UUID.randomUUID().toString();

            //table.child(c3.uuid).setValue(c3);
            Log.i("Firebase","Ok");

            etname.setText("");
        });

    }
}