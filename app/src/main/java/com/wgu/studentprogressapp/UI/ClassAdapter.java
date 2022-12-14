package com.wgu.studentprogressapp.UI;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wgu.studentprogressapp.Entities.Class;
import com.wgu.studentprogressapp.R;

import java.util.List;

public class ClassAdapter extends RecyclerView.Adapter<ClassAdapter.ClassHolder> {
    List<Class> mAllClasses;
    Context context;
    public ClassAdapter(Context ctext, List<Class> allClass) {
        context = ctext;
        mAllClasses = allClass;
    }

    @NonNull
    @Override
    public ClassHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.class_object, parent, false);
        return new ClassHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ClassHolder holder, int position) {
        holder.name.setText(mAllClasses.get(position).getClassName());
        holder.start.setText(mAllClasses.get(position).getStartDate());
        holder.end.setText(mAllClasses.get(position).getEndDate());
    }

    public void resetView(List<Class> allClass){
        mAllClasses = allClass;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mAllClasses.size();
    }

    public class ClassHolder extends RecyclerView.ViewHolder{
        TextView name;
        TextView start;
        TextView end;

        public ClassHolder(@NonNull View itemView){
            super(itemView);
            name = itemView.findViewById(R.id.textClassName);
            start = itemView.findViewById(R.id.txtClassStart);
            end = itemView.findViewById(R.id.txtClassEnd);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    final Class currentClass = mAllClasses.get(position);
                    Intent intent = new Intent(context, ClassDetailController.class);
                    intent.putExtra("id", String.valueOf(currentClass.getClassID()));
                    intent.putExtra("name", String.valueOf(currentClass.getClassName()));
                    intent.putExtra("term", String.valueOf(currentClass.getTermID()));
                    intent.putExtra("start", String.valueOf(currentClass.getStartDate()));
                    intent.putExtra("end", String.valueOf(currentClass.getEndDate()));
                    intent.putExtra("status", String.valueOf(currentClass.getClassStatus()));
                    intent.putExtra("instructorName",String.valueOf(currentClass.getInstructorName()));
                    intent.putExtra("instructorPhone",String.valueOf(currentClass.getInstructorPhone()));
                    intent.putExtra("instructorEmail",String.valueOf(currentClass.getInstructorEmail()));
                    intent.putExtra("notes",String.valueOf(currentClass.getClassNotes()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
