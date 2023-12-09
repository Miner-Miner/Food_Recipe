package com.miner.foodrecipe.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.miner.foodrecipe.Models.AnalyzedInstructionApiResponse;
import com.miner.foodrecipe.R;

import java.util.List;

public class AnalyzedInstructionAdapter extends RecyclerView.Adapter<AnalyzedInstructionAdapter.MyViewHolder> {

    Context context;
    List<AnalyzedInstructionApiResponse> list;

    public AnalyzedInstructionAdapter(Context context, List<AnalyzedInstructionApiResponse> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.list_instruction,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.recyclerMealInstructionSteps.setHasFixedSize(true);
        holder.recyclerMealInstructionSteps.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
        AnalyzedInstructionStepAdapter analyzedInstructionStepAdapter = new AnalyzedInstructionStepAdapter(context,list.get(position).steps);
        holder.recyclerMealInstructionSteps.setAdapter(analyzedInstructionStepAdapter);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        RecyclerView recyclerMealInstructionSteps;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            recyclerMealInstructionSteps = (RecyclerView) itemView.findViewById(R.id.recyclerMealInstructionSteps);
        }
    }
}
