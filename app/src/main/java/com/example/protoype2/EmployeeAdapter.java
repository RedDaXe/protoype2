package com.example.protoype2;

import android.app.Dialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class EmployeeAdapter extends FirestoreRecyclerAdapter<Employees, EmployeeAdapter.EmployeeHolder>{



    public EmployeeAdapter(@NonNull FirestoreRecyclerOptions<Employees> options) {
        super(options);
    }


    @Override
    protected void onBindViewHolder(@NonNull EmployeeHolder employeeHolder, final int i, @NonNull Employees employees) {
        DataHandler dh =  new DataHandler();
        employeeHolder.setEmployeeID(getSnapshots().getSnapshot(i).getId());
        employeeHolder.name.setText(employees.getName());
        employeeHolder.distance.setText(Float.toString(dh.CalculateDistance(employees.getDistance())) + " m ");


    }

    @NonNull
    @Override
    public EmployeeHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_layout, parent, false);
        return new EmployeeHolder(v);
    }

    class EmployeeHolder extends RecyclerView.ViewHolder {

        TextView name, distance, aliveReport, deadReport;
        LinearLayout root;
        ImageView alive, dead;
        Button doneBtn;
        public String employeeID;
        private FirebaseFirestore db = FirebaseFirestore.getInstance();
        private CollectionReference employeeRef = db.collection("employees");
        MissingList ml = new MissingList();

        public EmployeeHolder(@NonNull final View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            root = itemView.findViewById(R.id.list_root);
            distance = itemView.findViewById(R.id.distance);
            alive = itemView.findViewById(R.id.alive);
            dead = itemView.findViewById(R.id.dead);

            final Dialog dialog = new Dialog(itemView.getContext());

            alive.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MissingList.aliveC = MissingList.aliveC + 1;
                    MissingList.aliveCount.setText(Integer.toString(MissingList.aliveC));
                    employeeRef.document(employeeID).delete();
                    employeeRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if(task.getResult().size()==0){
                                Log.d(String.valueOf(this), "empty");
                                dialog.setContentView(R.layout.dialog_box);
                                aliveReport = dialog.findViewById(R.id.aliveLog);
                                deadReport = dialog.findViewById(R.id.deadReport);
                                aliveReport.setText(Integer.toString(MissingList.aliveC));
                                deadReport.setText(Integer.toString(MissingList.deadC));
                                doneBtn = dialog.findViewById(R.id.doneBtn);
                                doneBtn.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        dialog.dismiss();
                                        ml.ChangeActivity();
                                    }
                                });
                                dialog.show();
                            }

                        }
                    });
                }
            });
            dead.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MissingList.deadC = MissingList.deadC + 1;
                    MissingList.deadCount.setText(Integer.toString(MissingList.deadC));
                    employeeRef.document(employeeID).delete();
                    employeeRef.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if(task.getResult().size()==0){
                                Log.d(String.valueOf(this), "empty");
                                dialog.setContentView(R.layout.dialog_box);
                                aliveReport = dialog.findViewById(R.id.aliveLog);
                                deadReport = dialog.findViewById(R.id.deadReport);
                                aliveReport.setText(Integer.toString(MissingList.aliveC));
                                deadReport.setText(Integer.toString(MissingList.deadC));
                                doneBtn = dialog.findViewById(R.id.doneBtn);
                                doneBtn.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        dialog.dismiss();
                                        ml.ChangeActivity();
                                    }
                                });
                                dialog.show();
                            }

                        }
                    });
                }
            });

        }
        public void setEmployeeID(String employeeID){
            this.employeeID = employeeID;
        }
    }

}
