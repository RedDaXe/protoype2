package com.example.protoype2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class EmployeeAdapter extends FirestoreRecyclerAdapter<CategoryItem, EmployeeAdapter.EmployeeHolder>{


    public EmployeeAdapter(@NonNull FirestoreRecyclerOptions<CategoryItem> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull EmployeeHolder employeeHolder, int i, @NonNull CategoryItem categoryItem) {

        employeeHolder.name.setText(categoryItem.getName());
        employeeHolder.distance.setText(categoryItem.getDistance());

    }

    @NonNull
    @Override
    public EmployeeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_layout, parent, false);
        return new EmployeeHolder(v);
    }

    class EmployeeHolder extends RecyclerView.ViewHolder {

        TextView name, distance;

        public EmployeeHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            distance = itemView.findViewById(R.id.distance);
        }
    }
}
