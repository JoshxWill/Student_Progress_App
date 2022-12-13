package com.wgu.studentprogressapp.UI;

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

import java.util.List;

/**
 * Assessment Adapter
 *
 * @author Joshua Williams
 */
public class AssessmentAdapter extends RecyclerView.Adapter<AssessmentAdapter.AssessmentHolder>{

    List<Assessment> mAllAssessments;
    Context context;

    public AssessmentAdapter(Context ct, List<Assessment> allAssessments) {
        context = ct;
        mAllAssessments = allAssessments;
    }

    @NonNull
    @Override
    public AssessmentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.assessment_object, parent, false);
        return new AssessmentHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AssessmentHolder holder, int position) {
        holder.name.setText(mAllAssessments.get(position).getAssessmentName());
        holder.type.setText(mAllAssessments.get(position).getAssessmentType());
        holder.end.setText(mAllAssessments.get(position).getEndDate());

    }

    public void resetView(List<Assessment> allAssessments){
        mAllAssessments = allAssessments;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mAllAssessments.size();
    }

    public class AssessmentHolder extends RecyclerView.ViewHolder{
        TextView name;
        TextView type;
        TextView end;

        public AssessmentHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.textAssessmentName);
            type = itemView.findViewById(R.id.txtAssessmentType);
            end = itemView.findViewById(R.id.txtAssessmentEnd);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    final Assessment current = mAllAssessments.get(position);
                    Intent intent = new Intent(context, AssessmentDetailController.class);
                    intent.putExtra("id" , String.valueOf(current.getAssessmentID()));
                    intent.putExtra("name",String.valueOf(current.getAssessmentName()));
                    intent.putExtra("course",String.valueOf(current.getClassID()));
                    intent.putExtra("type",String.valueOf(current.getAssessmentType()));
                    intent.putExtra("end", String.valueOf(current.getEndDate()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
