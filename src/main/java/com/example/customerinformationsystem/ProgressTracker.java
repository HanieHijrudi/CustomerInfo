package com.example.customerinformationsystem;


import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

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


/*
    public void setTotalCustomers(int totalCustomers) {
        this.totalCustomers = totalCustomers;
    }*/

/*    public int getCurrentProgress() {
        return currentProgress;
    }*/

/*
    public int getTotalCustomers() {
        return totalCustomers;
    }
*/

}


