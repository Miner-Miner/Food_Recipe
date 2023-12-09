package com.miner.foodrecipe.Adapters;

import android.content.Context;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.miner.foodrecipe.Models.Equipment;
import com.miner.foodrecipe.Models.Step;
import com.miner.foodrecipe.R;

import java.util.List;

public class AnalyzedInstructionStepAdapter extends RecyclerView.Adapter<AnalyzedInstructionStepAdapter.MyViewHolder> {

    Context context;
    List<Step> list;

    public AnalyzedInstructionStepAdapter(Context context, List<Step> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.list_instruction_steps,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.textViewInstructionStepNumber.setText(String.valueOf(list.get(position).number));
        holder.textViewInstructionStepTitle.setText(list.get(position).step);

        holder.recyclerInstructionIngredients.setHasFixedSize(true);
        holder.recyclerInstructionIngredients.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false));
        AnalyzedInstructionStepIngredientAdapter analyzedInstructionStepIngredientAdapter= new AnalyzedInstructionStepIngredientAdapter(context,list.get(position).ingredients);
        holder.recyclerInstructionIngredients.setAdapter(analyzedInstructionStepIngredientAdapter);

        holder.recyclerInstructionEquipments.setHasFixedSize(true);
        holder.recyclerInstructionEquipments.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL,false));
        AnalyzedInstructionStepEquipmentAdapter analyzedInstructionStepEquipmentAdapter;
        analyzedInstructionStepEquipmentAdapter = new AnalyzedInstructionStepEquipmentAdapter(context,list.get(position).equipment);
        holder.recyclerInstructionEquipments.setAdapter(analyzedInstructionStepEquipmentAdapter);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textViewInstructionStepNumber,textViewInstructionStepTitle;
        RecyclerView recyclerInstructionEquipments,recyclerInstructionIngredients;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewInstructionStepNumber = (TextView) itemView.findViewById(R.id.textViewInstructionStepNumber);
            textViewInstructionStepTitle = (TextView) itemView.findViewById(R.id.textViewInstructionStepTitle);
            recyclerInstructionEquipments = (RecyclerView) itemView.findViewById(R.id.recyclerInstructionEquipments);
            recyclerInstructionIngredients = (RecyclerView) itemView.findViewById(R.id.recyclerInstructionIngredients);
        }
    }
}
