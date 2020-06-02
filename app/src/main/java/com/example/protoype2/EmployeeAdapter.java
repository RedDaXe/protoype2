package com.example.protoype2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class EmployeeAdapter extends FirestoreRecyclerAdapter<Employees, EmployeeAdapter.EmployeeHolder>{



    public EmployeeAdapter(@NonNull FirestoreRecyclerOptions<Employees> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull EmployeeHolder employeeHolder, final int i, @NonNull Employees employees) {
        employeeHolder.setEmployeeID(getSnapshots().getSnapshot(i).getId());
        employeeHolder.name.setText(employees.getName());
        employeeHolder.distance.setText(Integer.toString(employees.getDistance()) + " m ");


    }

    @NonNull
    @Override
    public EmployeeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_layout, parent, false);
        return new EmployeeHolder(v);
    }

    class EmployeeHolder extends RecyclerView.ViewHolder {

        TextView name, distance;
        LinearLayout root;
        ImageView alive, dead;
        public String employeeID;
        private FirebaseFirestore db = FirebaseFirestore.getInstance();
        private CollectionReference employeeRef = db.collection("employees");

        public EmployeeHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            root = itemView.findViewById(R.id.list_root);
            distance = itemView.findViewById(R.id.distance);
            alive = itemView.findViewById(R.id.alive);
            dead = itemView.findViewById(R.id.dead);

            alive.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    employeeRef.document(employeeID).delete();
                }
            });

            dead.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    employeeRef.document(employeeID).delete();
                }
            });
        }

        public void setEmployeeID(String employeeID){
            this.employeeID = employeeID;
        }
    }
}
