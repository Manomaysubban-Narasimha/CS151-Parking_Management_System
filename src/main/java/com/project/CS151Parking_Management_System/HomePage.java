package com.project.CS151Parking_Management_System;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;


import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;




@Route("homePage/:plateNumber?/:password?")
public class HomePage extends VerticalLayout implements BeforeEnterObserver {

    private String plateString = "";
    private String passString = "";
    private H1 currentAmount = new H1("");
    private PercentageFull pFull = new PercentageFull();

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        final Optional<String> plateNumber = event.getRouteParameters().get("plateNumber");
        final Optional<String> password = event.getRouteParameters().get("password");
        plateNumber.ifPresentOrElse(id -> {
            plateString = id;
        }, 
        () -> {
        });

        password.ifPresentOrElse(id -> {
            passString = id;
        }, 
        () -> {
        });
        InfluxHandler influx = new InfluxHandler();
        try {
            String passwordOfficial = influx.parseData(influx.getData("keys"), plateString);
            
            pFull.checkAndReset();
            if(passString.equals(passwordOfficial)){
                if(pFull.getCurrentAmount() <= 0){
                    currentAmount.setText(100 + "% Full");
                }else currentAmount.setText(100 - (pFull.getCurrentAmount() * 2) + "% Full");
                div1();
                div2();

            }
            else{
                add(new H1("Oops youre not supposed to be here"));
            }
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    public void div1(){
        Div div = new Div();
        div.getStyle().set("background-color", "#AFEEEE");
        div.getStyle().set("width", "100%");
        div.getStyle().set("height", "18.75em");
        div.getStyle().set("border-radius", "4em");
        div.getStyle().set("font-family", "futura");



        try {
            H1 plate = new H1("Plate #: " + plateString);
            InfluxHandler influx = new InfluxHandler();
            String type;

            type = influx.parseData(influx.getData("vehicleType"), plateString);
            H1 password = new H1(type);
    
            div.add(new HorizontalLayout(new VerticalLayout(plate, password), currentAmount));
            add(div);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void div2(){
        Div div = new Div();
        setHorizontalComponentAlignment(Alignment.CENTER, div);
        div.getStyle().set("background-color", "#AFEEEE");
        div.getStyle().set("width", "100%");
        div.getStyle().set("height", "18.75em");
        div.getStyle().set("border-radius", "4em");
        div.getStyle().set("font-family", "futura");
        H1 payNeeded = new H1("Need to Pay for Parking?");
        Button pay = new Button("Pay Here");
        pay.getStyle().set("font-size", "1.5em");
        H1 paidStatus = new H1("Paid Status");
        Button leave = new Button("Leave Garage");
        leave.getStyle().set("font-size", "1.5em");


        InfluxHandler influx = new InfluxHandler();
        leave.addClickListener(e -> {
            int currAmount = pFull.getCurrentAmount();
            currAmount++;
            currentAmount.setText(100 - (currAmount * 2) + "% Full");
            pFull.updateCurrentAmount(currAmount);
            paidStatus.getStyle().set("color", "red");
            try {
                influx.postData(plateString, "00/00-00", "garage");
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        pay.addClickListener(e -> {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd-HH");  
            LocalDateTime now = LocalDateTime.now();  

            try {
                if(!isGreen() && pFull.getCurrentAmount() != 0){
                    int toUpdate = pFull.getCurrentAmount();

                    toUpdate -= 1;

                    currentAmount.setText(100 - (toUpdate * 2) + "% Full");
                    pFull.updateCurrentAmount(toUpdate);
                    paidStatus.getStyle().set("color", "green");

                    influx.postData(plateString, dtf.format(now), "garage");
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        if(isGreen()) {
            paidStatus.getStyle().set("color", "green");
            div.add(new HorizontalLayout(new VerticalLayout(payNeeded, leave), paidStatus));
            add(div);

        } 
        else {
            paidStatus.getStyle().set("color", "red"); 
            div.add(new HorizontalLayout(new VerticalLayout(payNeeded, pay), paidStatus));
            add(div);
        }
    }

    public boolean isGreen(){
        InfluxHandler influx = new InfluxHandler();
        try {
            String time = influx.parseData(influx.getData("garage"), plateString);
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd-HH");  
            LocalDateTime now = LocalDateTime.now();  
            String influxTime = dtf.format(now);

            return influxTime.equals(time);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
