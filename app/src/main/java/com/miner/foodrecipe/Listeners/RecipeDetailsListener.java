package com.miner.foodrecipe.Listeners;

import com.miner.foodrecipe.Models.RecipeDetailsApiResponse;

public interface RecipeDetailsListener {
    void didFetch(RecipeDetailsApiResponse response, String message);
    void didError(String message);
}
