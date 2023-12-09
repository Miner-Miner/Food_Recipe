package com.miner.foodrecipe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.miner.foodrecipe.Adapters.AnalyzedInstructionAdapter;
import com.miner.foodrecipe.Adapters.IngredientsAdapter;
import com.miner.foodrecipe.Adapters.SimilarRecipeAdapter;
import com.miner.foodrecipe.Listeners.AnalyzedInstructionListener;
import com.miner.foodrecipe.Listeners.RecipeClickListener;
import com.miner.foodrecipe.Listeners.RecipeDetailsListener;
import com.miner.foodrecipe.Listeners.SimilarRecipesListener;
import com.miner.foodrecipe.Models.AnalyzedInstructionApiResponse;
import com.miner.foodrecipe.Models.Recipe;
import com.miner.foodrecipe.Models.RecipeDetailsApiResponse;
import com.miner.foodrecipe.Models.SimilarRecipesApiResponse;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RecipeDetailActivity extends AppCompatActivity {

    int id;
    TextView textViewMealName,textViewMealSource,textViewMealSummary,textViewMealSummary2,textViewSeeMore,textViewSeeLess;
    ImageView imageViewMeal;
    RecyclerView recyclerMealIngredients,recyclerMealSimilar,recyclerMealInstruction;
    RequestManager manager;
    ProgressDialog dialog;

    IngredientsAdapter adapter;
    SimilarRecipeAdapter similarRecipeAdapter;
    AnalyzedInstructionAdapter analyzedInstructionAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        textViewMealName = (TextView) findViewById(R.id.textViewMealName);
        textViewMealSource = (TextView) findViewById(R.id.textViewMealSource);
        textViewMealSummary = (TextView) findViewById(R.id.textViewMealSummary);
        textViewMealSummary2 = (TextView) findViewById(R.id.textViewMealSummary2);
        textViewSeeMore = (TextView) findViewById(R.id.textViewSeeMore);
        textViewSeeLess = (TextView) findViewById(R.id.textViewSeeLess);
        imageViewMeal = (ImageView) findViewById(R.id.imageViewMeal);
        recyclerMealIngredients = (RecyclerView) findViewById(R.id.recyclerMealIngredients);
        recyclerMealSimilar = (RecyclerView) findViewById(R.id.recyclerMealSimilar);
        recyclerMealInstruction = (RecyclerView) findViewById(R.id.recyclerMealInstruction);

        id = Integer.parseInt(getIntent().getStringExtra("id"));

        textViewSeeMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textViewMealSummary.setVisibility(View.GONE);
                textViewSeeMore.setVisibility(View.GONE);
                textViewMealSummary2.setVisibility(View.VISIBLE);
                textViewSeeLess.setVisibility(View.VISIBLE);
            }
        });

        textViewSeeLess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textViewMealSummary.setVisibility(View.VISIBLE);
                textViewSeeMore.setVisibility(View.VISIBLE);
                textViewMealSummary2.setVisibility(View.GONE);
                textViewSeeLess.setVisibility(View.GONE);
            }
        });

        manager = new RequestManager(this);
        manager.getRecipeDetails(recipeDetailsListener,id);
        manager.getSimilarRecipes(similarRecipesListener,id);
        manager.getAnalyzedInstruction(analyzedInstructionListener,id);

        dialog = new ProgressDialog(this);
        dialog.setTitle("Loading Details...");
        dialog.show();
    }

    private final RecipeDetailsListener recipeDetailsListener = new RecipeDetailsListener() {
        @Override
        public void didFetch(RecipeDetailsApiResponse response, String message) {
            textViewMealName.setText(response.title);
            textViewMealSource.setText(response.sourceName);
            //Get HTML Element and bold in TextView
            response.summary.replaceAll("</a>","</b></font>");             //   Replace </a> to </b>
            StringBuilder stringBuilder = new StringBuilder(response.summary);          //    To Replace string with String Builder
            while(stringBuilder.toString().contains("<a href")){                                      //    Check <a href contains
                int startIndex,endIndex;                                                                         //
                startIndex = stringBuilder.indexOf("<a href");                                         //    first index of <a href
                endIndex = stringBuilder.indexOf("\">")+2;                                            //    finish tag index of <a href
                stringBuilder.replace(startIndex,endIndex,"<b> <font color=#5FCA1C>");                             //    Replace <a href="Link"> with <b>
            }
            String  mealSummary = stringBuilder.toString();                                        //    string builder to To String
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                textViewMealSummary.setText(Html.fromHtml(mealSummary,Html.FROM_HTML_MODE_LEGACY));         //Bold Here with HTML
                textViewMealSummary2.setText(Html.fromHtml(mealSummary,Html.FROM_HTML_MODE_LEGACY));         //Bold Here with HTML
            } else {
                textViewMealSummary.setText(Html.fromHtml(mealSummary));                                                              //Bold Here with HTML
                textViewMealSummary2.setText(Html.fromHtml(mealSummary));                                                              //Bold Here with HTML
            }
            Picasso.get().load(response.image).into(imageViewMeal);
    
            recyclerMealIngredients.setHasFixedSize(true);
            recyclerMealIngredients.setLayoutManager(new LinearLayoutManager(RecipeDetailActivity.this, LinearLayoutManager.HORIZONTAL,false));
            adapter = new IngredientsAdapter(RecipeDetailActivity.this,response.extendedIngredients);
            recyclerMealIngredients.setAdapter(adapter);
            dialog.dismiss();
        }

        @Override
        public void didError(String message) {
            Toast.makeText(RecipeDetailActivity.this, message, Toast.LENGTH_SHORT).show();
        }
    };

    private final SimilarRecipesListener similarRecipesListener = new SimilarRecipesListener() {
        @Override
        public void didFetch(List<SimilarRecipesApiResponse> response, String message) {
            recyclerMealSimilar.setHasFixedSize(true);
            recyclerMealSimilar.setLayoutManager(new LinearLayoutManager(RecipeDetailActivity.this,LinearLayoutManager.HORIZONTAL,false));
            similarRecipeAdapter = new SimilarRecipeAdapter(RecipeDetailActivity.this, response, recipeClickListener);
            recyclerMealSimilar.setAdapter(similarRecipeAdapter);
        }

        @Override
        public void didError(String message) {
            Toast.makeText(RecipeDetailActivity.this, message, Toast.LENGTH_SHORT).show();
        }
    };

    private final RecipeClickListener recipeClickListener = new RecipeClickListener() {
        @Override
        public void onRecipeClick(String id) {
            startActivity(new Intent(RecipeDetailActivity.this,RecipeDetailActivity.class).putExtra("id",id));
            finish();
        }
    };

    private final AnalyzedInstructionListener analyzedInstructionListener = new AnalyzedInstructionListener() {
        @Override
        public void didFetch(List<AnalyzedInstructionApiResponse> response, String message) {
            recyclerMealInstruction.setHasFixedSize(true);
            recyclerMealInstruction.setLayoutManager(new LinearLayoutManager(RecipeDetailActivity.this,LinearLayoutManager.VERTICAL,false));
            analyzedInstructionAdapter = new AnalyzedInstructionAdapter(RecipeDetailActivity.this,response);
            recyclerMealInstruction.setAdapter(analyzedInstructionAdapter);
        }

        @Override
        public void didError(String message) {
            Toast.makeText(RecipeDetailActivity.this, message, Toast.LENGTH_SHORT).show();
        }
    };
}