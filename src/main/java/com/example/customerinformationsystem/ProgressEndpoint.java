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
        Map<String, Object> respone = new HashMap<>();

/*        int currentProgress = progressTracker.getCurrentProgress();
        int totalCustomers = progressTracker.getTotalCustomers();
        int progressPercentage = progressTracker.getProgressPercentage();

        respone.put("progress", currentProgress);
        respone.put("totalCustomer", totalCustomers);

        if(progressPercentage == 0){
            respone.put("progressPercentage", null);
            respone.put("message", "No available progress at the moment");
        } else {
            respone.put("progressPercentage", progressPercentage);
        }
        return respone;*/


        respone.put("progress", progressTracker.getCurrentProgress());
        respone.put("totalCustomer", progressTracker.getTotalCustomers());
        respone.put("progressPercentage", progressTracker.getProgressPercentage());
        return respone;
    }

}

/*    private final ProgressTracker progressTracker;

    @Autowired
    public ProgressEndpoint(ProgressTracker progressTracker) {
        this.progressTracker = progressTracker;
    }*/