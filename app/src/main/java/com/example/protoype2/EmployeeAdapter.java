package com.example.protoype2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class EmployeeAdapter extends FirestoreRecyclerAdapter<Employees, EmployeeAdapter.EmployeeHolder>{


    public EmployeeAdapter(@NonNull FirestoreRecyclerOptions<Employees> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull EmployeeHolder employeeHolder, int i, @NonNull Employees employees) {

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

        public EmployeeHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            root = itemView.findViewById(R.id.list_root);
            distance = itemView.findViewById(R.id.distance);
        }
    }
}
