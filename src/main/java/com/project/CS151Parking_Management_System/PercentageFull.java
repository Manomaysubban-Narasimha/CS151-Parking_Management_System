package com.project.CS151Parking_Management_System;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PercentageFull {

    private InfluxHandler influx;

    public PercentageFull(){
        influx = InfluxHandler.getInstance();
    }

    public int getCurrentAmount(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd-HH");
        LocalDateTime now = LocalDateTime.now();
        try {
            String rawOutput = influx.parseData(influx.getData("spotsAvailable"), dtf.format(now));
            if(rawOutput.equals("Wrong License Plate")) return 0;
            else{
                return Integer.valueOf(rawOutput);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public void updateCurrentAmount(int updateAmount){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd-HH");
        LocalDateTime now = LocalDateTime.now();
        try {
            influx.postData(dtf.format(now), updateAmount + "", "spotsAvailable");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void checkAndReset(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd-HH");
        LocalDateTime now = LocalDateTime.now();
        try {
            if(influx.parseData(influx.getData("garage"), dtf.format(now)).equals("Wrong License Plate")){
                influx.postData(dtf.format(now), 50 + "", "spotsAvailable");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}