package com.example.protoype2;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;

public class EmployeeAdapter extends FirestoreRecyclerAdapter<CategoryItem, >{

    class EmployeeHolder extends RecyclerView.ViewHolder {

        TextView name, distance;

        public EmployeeHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            distance = itemView.findViewById(R.id.distance);
        }
    }
}
