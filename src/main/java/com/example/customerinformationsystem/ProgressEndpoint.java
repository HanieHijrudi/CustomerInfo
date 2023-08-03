package com.example.customerinformationsystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Endpoint(id = "progress")
public class ProgressEndpoint {

    @Autowired
    private ProgressTracker progressTracker;

    @ReadOperation
    public Map<String, Object> getProgress() {
        Map<String, Object> response = new HashMap<>();

        int currentProgress = progressTracker.getCurrentProgress();
        int totalCustomers = progressTracker.getTotalCustomers();
        int progressPercentage = 0;

        if(totalCustomers !=0){
            progressPercentage= progressTracker.getProgressPercentage();
        }

         response.put("progress", currentProgress);
        response.put("totalCustomer", totalCustomers);


        if(progressPercentage == 0){
            response.put("progressPercentage", 0);
            response.put("message", "No available progress at the moment");
        } else {
            response.put("progressPercentage", progressPercentage);
            if (progressPercentage == 100){
                progressTracker.setCurrentProgress(0);
            }
        }

        return response;
    }

}
