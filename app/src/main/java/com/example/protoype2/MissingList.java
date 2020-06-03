package com.example.protoype2;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MissingList extends AppCompatActivity {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference employeeRef = db.collection("employees");
    public Button aliveCount, deadCount;
    MainActivity ma;


    private EmployeeAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_missing_list);
        aliveCount = findViewById(R.id.aliveCount);
        deadCount = findViewById(R.id.deadCount);

        setUpRecyclerview();

    }

    private void setUpRecyclerview() {
        Query query = employeeRef.orderBy("name");

        FirestoreRecyclerOptions<Employees>options = new FirestoreRecyclerOptions.Builder<Employees>()
                .setQuery(query, Employees.class)
                .build();

        adapter = new EmployeeAdapter(options);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }

    public void ChangeActivity(){
        Intent f = new Intent(MissingList.this, MainActivity.class);
        startActivity(f);
    }



    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}