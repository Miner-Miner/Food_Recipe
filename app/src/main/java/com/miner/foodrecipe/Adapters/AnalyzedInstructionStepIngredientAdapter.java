package com.miner.foodrecipe.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.miner.foodrecipe.Models.Ingredient;
import com.miner.foodrecipe.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AnalyzedInstructionStepIngredientAdapter extends RecyclerView.Adapter<AnalyzedInstructionStepIngredientAdapter.MyViewHolder> {
    Context context;
    List<Ingredient> list;

    public AnalyzedInstructionStepIngredientAdapter(Context context, List<Ingredient> list) {
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
        holder.textViewInstructionStepItem.setText(list.get(position).name);
        holder.textViewInstructionStepItem.setSelected(true);
        Picasso.get().load("https://spoonacular.com/cdn/ingredients_100x100/"+list.get(position).image).into(holder.imageViewInstructionStepItem);

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
