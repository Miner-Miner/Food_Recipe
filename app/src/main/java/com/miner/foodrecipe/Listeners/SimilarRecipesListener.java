package com.miner.foodrecipe.Listeners;

import com.miner.foodrecipe.Models.SimilarRecipesApiResponse;

import java.util.List;

public interface SimilarRecipesListener {
    void didFetch(List<SimilarRecipesApiResponse> response, String message);
    void didError(String message);
}
