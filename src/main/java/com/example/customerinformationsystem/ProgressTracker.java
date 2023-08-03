package com.example.customerinformationsystem;

import lombok.Data;

@Data
public class ProgressTracker {
    private int currentProgress;
    private int totalCustomers;

    public void incrementProgress() {
        currentProgress++;
    }

    public int getProgressPercentage() {
        if (totalCustomers == 0) {
            return 0;
        }
        return (currentProgress * 100) / totalCustomers;
    }
}


