package com.miner.foodrecipe.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.miner.foodrecipe.Listeners.RecipeClickListener;
import com.miner.foodrecipe.Models.Recipe;
import com.miner.foodrecipe.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RandomRecipeAdapter extends RecyclerView.Adapter<RandomRecipeAdapter.MyViewHolder> {

    Context context;
    List<Recipe> list;
    RecipeClickListener listener;

    public RandomRecipeAdapter(Context context, List<Recipe> list, RecipeClickListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        CardView randomListContainer;
        TextView textViewTitle,textViewServing,textViewLike,textViewTime;
        ImageView imageViewFood;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            randomListContainer = (CardView) itemView.findViewById(R.id.randomListContainer);
            textViewTitle = (TextView)  itemView.findViewById(R.id.textViewTitle);
            textViewServing = (TextView) itemView.findViewById(R.id.textViewServing);
            textViewLike = (TextView) itemView.findViewById(R.id.textViewLike);
            textViewTime = (TextView)  itemView.findViewById(R.id.textViewTime);
            imageViewFood = (ImageView) itemView.findViewById(R.id.imageViewFood);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.list_random_recipe,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.textViewTitle.setText(list.get(position).title);
        holder.textViewTitle.setSelected(true);
        holder.textViewLike.setText(String.valueOf(list.get(position).aggregateLikes + " Likes"));
        holder.textViewServing.setText(String.valueOf(list.get(position).servings + " Servings"));
        holder.textViewTime.setText(String.valueOf(list.get(position).readyInMinutes+" Minutes"));
        Picasso.get().load(list.get(position).image).into(holder.imageViewFood);

        holder.randomListContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onRecipeClick(String.valueOf(list.get(holder.getAdapterPosition()).id));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
