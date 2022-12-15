package com.wgu.studentprogressapp.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wgu.studentprogressapp.Entities.CourseInstructor;
import com.wgu.studentprogressapp.R;
import com.wgu.studentprogressapp.UI.CreateInstructor;

import java.util.List;


public class CourseInstructorAdapter extends RecyclerView.Adapter<CourseInstructorAdapter.InstructorViewHolder> {

    public List<CourseInstructor> mInstructorList;
    private Context context;
    private LayoutInflater inflater;

    public CourseInstructorAdapter(Context context){
        inflater = LayoutInflater.from(context);
        this.context = context;
    }

    public class InstructorViewHolder extends RecyclerView.ViewHolder{
        public TextView recyclerViewItemLayout;

        public InstructorViewHolder(@NonNull View itemView) {
            super(itemView);
            recyclerViewItemLayout = itemView.findViewById(R.id.item_layout);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    CourseInstructor current = mInstructorList.get(position);

                    Intent intent = new Intent(context, CreateInstructor.class );
                    intent.putExtra("InstructorId", current.getInstructorID() );
                    intent.putExtra("position", position);
                    intent.putExtra("assessCourseId", current.getInstructorCourseId());
                    context.startActivity(intent);
                }
            });
        }
    }

    @NonNull
    @Override
    public InstructorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v  = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new InstructorViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull InstructorViewHolder holder, int position) {
        CourseInstructor currentInstructor = mInstructorList.get(position);
        holder.recyclerViewItemLayout.setText(currentInstructor.getInstructorName());
    }

    @Override
    public int getItemCount() {
        return mInstructorList.size();
    }

    public CourseInstructor getInstructorAt(int position){
        return mInstructorList.get(position);
    }

    public void instructorSetter(List<CourseInstructor> instructor){
        mInstructorList = instructor;
        notifyDataSetChanged();
    }

}

