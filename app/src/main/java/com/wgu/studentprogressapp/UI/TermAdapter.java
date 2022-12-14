package com.wgu.studentprogressapp.UI;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wgu.studentprogressapp.Entities.Term;
import com.wgu.studentprogressapp.R;

import java.util.List;

public class TermAdapter extends RecyclerView.Adapter<TermAdapter.TermHolder>{
    List<Term> mAllTerms;
    Context context;
    public TermAdapter(Context ctext, List<Term> allTerms) {
        context = ctext;
        mAllTerms = allTerms;
    }

    @NonNull
    @Override
    public TermHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.term_object, parent, false);
        return new TermHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TermHolder holder, int position) {
        holder.name.setText(mAllTerms.get(position).getTermName());
        holder.start.setText(mAllTerms.get(position).getStartDate());
        holder.end.setText(mAllTerms.get(position).getEndDate());

    }

    @Override
    public int getItemCount() {
        return mAllTerms.size();
    }

    public class TermHolder extends RecyclerView.ViewHolder{
        TextView name;
        TextView start;
        TextView end;

        public TermHolder(@NonNull View view){
            super(view);
            name = view.findViewById(R.id.textTermName);
            start = view.findViewById(R.id.txtTermStart);
            end = view.findViewById(R.id.txtTermEnd);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    final Term current = mAllTerms.get(position);
                    Intent intent = new Intent(context, TermDetailController.class);
                    intent.putExtra("id", String.valueOf(current.getTermID()));
                    intent.putExtra("termName", String.valueOf(current.getTermName()));
                    intent.putExtra("startTermDate", String.valueOf(current.getStartDate()));
                    intent.putExtra("endTermDate", String.valueOf(current.getEndDate()));
                    context.startActivity(intent);
                }
            });
        }
    }
}
