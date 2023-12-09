package com.miner.foodrecipe.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.miner.foodrecipe.Listeners.RecipeClickListener;
import com.miner.foodrecipe.Models.SimilarRecipesApiResponse;
import com.miner.foodrecipe.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SimilarRecipeAdapter extends RecyclerView.Adapter<SimilarRecipeAdapter.MyViewHolder> {
    Context context;
    List<SimilarRecipesApiResponse> list;
    RecipeClickListener listener;

    public SimilarRecipeAdapter(Context context, List<SimilarRecipesApiResponse> list, RecipeClickListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.list_similar_recipes,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.textViewSimilarTitle.setText(list.get(position).title);
        holder.textViewSimilarTitle.setSelected(true);
        holder.textViewSimilarServing.setText(String.valueOf(list.get(position).servings)+" People");
        Picasso.get().load("https://spoonacular.com/recipeImages/"+list.get(position).id+"-556x370."+list.get(position).imageType).into(holder.imageViewSimilar);

        holder.similarRecipeHolder.setOnClickListener(new View.OnClickListener() {
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

    public class MyViewHolder extends RecyclerView.ViewHolder {
        CardView similarRecipeHolder;
        TextView textViewSimilarTitle,textViewSimilarServing;
        ImageView imageViewSimilar;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            similarRecipeHolder = (CardView) itemView.findViewById(R.id.similarRecipeHolder);
            textViewSimilarTitle = (TextView) itemView.findViewById(R.id.textViewSimilarTitle);
            textViewSimilarServing = (TextView) itemView.findViewById(R.id.textViewSimilarServing);
            imageViewSimilar = (ImageView) itemView.findViewById(R.id.imageViewSimilar);
        }
    }
}
