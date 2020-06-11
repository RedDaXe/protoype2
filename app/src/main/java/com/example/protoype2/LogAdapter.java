package com.example.protoype2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class LogAdapter extends FirestoreRecyclerAdapter<Logs, LogAdapter.LogHolder> {

    public LogAdapter(@NonNull FirestoreRecyclerOptions<Logs> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull LogHolder logHolder, final int i, @NonNull Logs logs) {
        logHolder.setLogID(getSnapshots().getSnapshot(i).getId());
        logHolder.dateLog.setText(logs.getDate());
        logHolder.aliveLog.setText(Integer.toString(logs.getAlive()));
        logHolder.deadLog.setText(Integer.toString(logs.getDead()));

    }

    @NonNull
    @Override
    public LogHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.report_list, parent, false);
        return new LogHolder(v);
    }

    class LogHolder extends RecyclerView.ViewHolder {

        public String logID;
        TextView dateLog, aliveLog, deadLog, aliveView, deadView;
        ImageButton undoBtn;
        Button moreDetails;
        ConstraintLayout logDetails;
        LinearLayout listView;

        public LogHolder(@NonNull final View itemView) {
            super(itemView);
            aliveView = itemView.findViewById(R.id.textView7);
            deadView = itemView.findViewById(R.id.textView9);
            dateLog = itemView.findViewById(R.id.dateLog);
            aliveLog = itemView.findViewById(R.id.aliveLog);
            deadLog = itemView.findViewById(R.id.deadLog);
            undoBtn = itemView.findViewById(R.id.undoBtn);
            moreDetails = itemView.findViewById(R.id.moreDetails);
            logDetails = itemView.findViewById(R.id.logDetails);
            listView = itemView.findViewById(R.id.list_root_log);

            moreDetails.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    moreDetails.setVisibility(View.GONE);
                    logDetails.setVisibility(View.VISIBLE);
                }
            });

            undoBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    moreDetails.setVisibility(View.VISIBLE);
                    logDetails.setVisibility(View.GONE);
                }
            });
        }
        public void setLogID(String logID){
            this.logID = logID;
        }
    }
}
