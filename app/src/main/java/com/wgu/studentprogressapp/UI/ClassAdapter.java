package com.wgu.studentprogressapp.UI;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wgu.studentprogressapp.Entities.Class;

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
    public ClassAdapter.ClassHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ClassAdapter.ClassHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
