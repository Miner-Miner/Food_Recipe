package com.miner.foodrecipe;

import android.content.Context;

import com.miner.foodrecipe.Listeners.AnalyzedInstructionListener;
import com.miner.foodrecipe.Listeners.RandomRecipeResponseListener;
import com.miner.foodrecipe.Listeners.RecipeDetailsListener;
import com.miner.foodrecipe.Listeners.SimilarRecipesListener;
import com.miner.foodrecipe.Models.AnalyzedInstructionApiResponse;
import com.miner.foodrecipe.Models.RandomRecipeApiResponse;
import com.miner.foodrecipe.Models.RecipeDetailsApiResponse;
import com.miner.foodrecipe.Models.SimilarRecipesApiResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public class RequestManager {
    Context context;
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.spoonacular.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public RequestManager(Context context) { this.context = context; }

    public void getRandomRecipe(RandomRecipeResponseListener listener, List<String> tags){
        CallRandomRecipes callRandomRecipes = retrofit.create(CallRandomRecipes.class);
        Call<RandomRecipeApiResponse> call = callRandomRecipes.callRandomRecipe(context.getString(R.string.apiKey), "10", tags);
        call.enqueue(new Callback<RandomRecipeApiResponse>() {
            @Override
            public void onResponse(Call<RandomRecipeApiResponse> call, Response<RandomRecipeApiResponse> response) {
                if(!response.isSuccessful()){
                    listener.didError(response.message());
                    return;
                }
                listener.didFetch(response.body(), response.message());
            }

            @Override
            public void onFailure(Call<RandomRecipeApiResponse> call, Throwable t) {
                listener.didError(t.getMessage());
            }
        });
    }

    private interface CallRandomRecipes{
        @GET("recipes/random")
        Call<RandomRecipeApiResponse> callRandomRecipe(
                @Query("apiKey") String apiKey,
                @Query("number") String number,
                @Query("tags") List<String> tags
        );
    }

    public void getRecipeDetails(RecipeDetailsListener listener, int id){
        CallRecipeDetails callRecipeDetails = retrofit.create(CallRecipeDetails.class);
        Call<RecipeDetailsApiResponse> call = callRecipeDetails.callRecipeDetails(id,context.getString(R.string.apiKey));
        call.enqueue(new Callback<RecipeDetailsApiResponse>() {
            @Override
            public void onResponse(Call<RecipeDetailsApiResponse> call, Response<RecipeDetailsApiResponse> response) {
                if(!response.isSuccessful()){
                    listener.didError(response.message());
                }
                listener.didFetch(response.body(),response.message());
            }

            @Override
            public void onFailure(Call<RecipeDetailsApiResponse> call, Throwable t) {
                listener.didError(t.getMessage());
            }
        });
    }

    private interface CallRecipeDetails{
        @GET("recipes/{id}/information")
        Call<RecipeDetailsApiResponse> callRecipeDetails(
                @Path("id") int id,
                @Query("apiKey") String apiKey
        );
    }

    public void getSimilarRecipes(SimilarRecipesListener listener, int id){
        CallSimilarRecipes callSimilarRecipes = retrofit.create(CallSimilarRecipes.class);
        Call<List<SimilarRecipesApiResponse>> call = callSimilarRecipes.callSimilarRecipes(id,"10", context.getString(R.string.apiKey));
        call.enqueue(new Callback<List<SimilarRecipesApiResponse>>() {
            @Override
            public void onResponse(Call<List<SimilarRecipesApiResponse>> call, Response<List<SimilarRecipesApiResponse>> response) {
                if(!response.isSuccessful()){
                    listener.didError(response.message());
                    return;
                }
                listener.didFetch(response.body(),response.message());
            }

            @Override
            public void onFailure(Call<List<SimilarRecipesApiResponse>> call, Throwable t) {
                listener.didError(t.getMessage());
            }
        });
    }

    private interface CallSimilarRecipes{
        @GET("recipes/{id}/similar")
        Call<List<SimilarRecipesApiResponse>> callSimilarRecipes(
                @Path("id") int id,
                @Query("number") String number,
                @Query("apiKey") String apiKey
        );
    }

    public void getAnalyzedInstruction(AnalyzedInstructionListener listener,int id){
        CallAnalyzedInstruction callAnalyzedInstruction = retrofit.create(CallAnalyzedInstruction.class);
        Call<List<AnalyzedInstructionApiResponse>> call = callAnalyzedInstruction.callAnalyzedInstruction(id,context.getString(R.string.apiKey));
        call.enqueue(new Callback<List<AnalyzedInstructionApiResponse>>() {
            @Override
            public void onResponse(Call<List<AnalyzedInstructionApiResponse>> call, Response<List<AnalyzedInstructionApiResponse>> response) {
                if(!response.isSuccessful()){
                    listener.didError(response.message());
                    return;
                }
                listener.didFetch(response.body(),response.message());
            }

            @Override
            public void onFailure(Call<List<AnalyzedInstructionApiResponse>> call, Throwable t) {
                listener.didError(t.getMessage());
            }
        });
    }
    
    private interface CallAnalyzedInstruction{
        @GET("recipes/{id}/analyzedInstructions")
        Call<List<AnalyzedInstructionApiResponse>> callAnalyzedInstruction(
                @Path("id") int id,
                @Query("apiKey") String apiKey
        );
    }
}