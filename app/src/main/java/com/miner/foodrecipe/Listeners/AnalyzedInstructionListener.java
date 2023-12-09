package com.miner.foodrecipe.Listeners;

import com.miner.foodrecipe.Models.AnalyzedInstructionApiResponse;

import java.util.List;

public interface AnalyzedInstructionListener {
    void didFetch(List<AnalyzedInstructionApiResponse> response, String message);
    void didError(String message);
}
