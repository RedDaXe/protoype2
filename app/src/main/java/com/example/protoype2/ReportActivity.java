package com.example.protoype2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class ReportActivity extends AppCompatActivity {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference logRef = db.collection("history");
    private LogAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        setUpRecyclerView();
    }

    private void setUpRecyclerView() {
        Query query = logRef.orderBy("date");
        FirestoreRecyclerOptions<Logs> options = new FirestoreRecyclerOptions.Builder<Logs>()
                .setQuery(query, Logs.class)
                .build();

        adapter = new LogAdapter(options);

        RecyclerView logView = findViewById(R.id.logView);
        logView.setHasFixedSize(true);
        logView.setLayoutManager(new LinearLayoutManager(this));
        logView.setAdapter(adapter);
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