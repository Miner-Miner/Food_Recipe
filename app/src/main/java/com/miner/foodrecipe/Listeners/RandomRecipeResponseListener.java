package com.miner.foodrecipe.Listeners;

import com.miner.foodrecipe.Models.RandomRecipeApiResponse;

public interface RandomRecipeResponseListener {
    void didFetch(RandomRecipeApiResponse response, String message);
    void didError(String message);
}
