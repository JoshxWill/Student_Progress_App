package com.wgu.studentprogressapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.wgu.studentprogressapp.Entities.Assessment;
import com.wgu.studentprogressapp.R;
import com.wgu.studentprogressapp.UI.CreateAssessment;

import java.util.List;

public class AssessmentAdapter extends RecyclerView.Adapter<AssessmentAdapter.AssessmentViewHolder> {

    public List<Assessment> mAssessmentList;
    private Context context;
    private LayoutInflater inflater;

    public AssessmentAdapter(Context context){
        inflater = LayoutInflater.from(context);
        this.context = context;
    }



    public class AssessmentViewHolder extends RecyclerView.ViewHolder{
        public TextView recyclerViewItemLayout;
        public AssessmentViewHolder(@NonNull View itemView) {
            super(itemView);
            recyclerViewItemLayout = itemView.findViewById(R.id.item_layout);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    Assessment current  = mAssessmentList.get(position);

                    Intent intent = new Intent(context, CreateAssessment.class);
                    intent.putExtra("assessmentId", current.getAssessmentId());
                    intent.putExtra("position", position);
                    intent.putExtra("assessCourseId",current.getAssessmentCourseId() );
                    context.startActivity(intent);
                }
            });

        }
    }




    @NonNull
    @Override
    public AssessmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new AssessmentViewHolder(v);
    }


    // Sets text to card on rv.
    @Override
    public void onBindViewHolder(@NonNull AssessmentViewHolder holder, int position) {
        Assessment currentAssessment = mAssessmentList.get(position);
        holder.recyclerViewItemLayout.setText(currentAssessment.getAssessmentTitle());
    }


    @Override
    public int getItemCount() {
        return mAssessmentList.size();
    }



    public Assessment getAssessmentAt(int position){
        return mAssessmentList.get(position);
    }


    public void assessmentSetter (List<Assessment> assessments){
        mAssessmentList = assessments;
        notifyDataSetChanged();
    }






}