package com.miner.foodrecipe.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.miner.foodrecipe.Models.ExtendedIngredient;
import com.miner.foodrecipe.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.MyViewHolder> {
    Context context;
    List<ExtendedIngredient> list;

    public IngredientsAdapter(Context context,List<ExtendedIngredient> list){
        this.context=context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.list_meal_ingredients,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.textViewIngredientName.setText(list.get(position).name);
        holder.textViewIngredientName.setSelected(true);
        holder.textViewIngredientQuantity.setText(list.get(position).original);
        holder.textViewIngredientQuantity.setSelected(true);
        Picasso.get().load("https://spoonacular.com/cdn/ingredients_100x100/"+list.get(position).image).into(holder.imageViewIngredient);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView textViewIngredientQuantity,textViewIngredientName;
        ImageView imageViewIngredient;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewIngredientQuantity = (TextView) itemView.findViewById(R.id.textViewIngredientQuantity);
            textViewIngredientName = (TextView) itemView.findViewById(R.id.textViewIngredientName);
            imageViewIngredient = (ImageView) itemView.findViewById(R.id.imageViewIngredient);
        }
    }
}
