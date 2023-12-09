package com.miner.foodrecipe.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.miner.foodrecipe.Models.Equipment;
import com.miner.foodrecipe.Models.Ingredient;
import com.miner.foodrecipe.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AnalyzedInstructionStepEquipmentAdapter extends RecyclerView.Adapter<AnalyzedInstructionStepEquipmentAdapter.MyViewHolder> {
    Context context;
    List<Equipment> list;

    public AnalyzedInstructionStepEquipmentAdapter(Context context, List<Equipment> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.list_instruction_step_items,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        if(list.size()==0){
            holder.textViewInstructionStepItem.setText("No Equipment");
            holder.imageViewInstructionStepItem.setImageResource(R.drawable.no_equipment);
        }else{
            holder.textViewInstructionStepItem.setText(list.get(position).name);
            Picasso.get().load("https://spoonacular.com/cdn/equipment_100x100/"+list.get(position).image).into(holder.imageViewInstructionStepItem);
        }
        holder.textViewInstructionStepItem.setSelected(true);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textViewInstructionStepItem;
        ImageView imageViewInstructionStepItem;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewInstructionStepItem = (TextView) itemView.findViewById(R.id.textViewInstructionStepItem);
            imageViewInstructionStepItem = (ImageView) itemView.findViewById(R.id.imageViewInstructionStepItem);
        }
    }
}
