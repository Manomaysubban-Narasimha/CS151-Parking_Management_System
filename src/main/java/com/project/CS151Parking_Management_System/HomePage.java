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

    String plateString = "";
    String passString = "";

    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        // TODO Auto-generated method stub
        final Optional<String> plateNumber = event.getRouteParameters().get("plateNumber");
        final Optional<String> password = event.getRouteParameters().get("password");
        plateNumber.ifPresentOrElse(id -> {
            plateString = id;
        }, 
        () -> {
            System.out.println("UHOH");
        });

        password.ifPresentOrElse(id -> {
            passString = id;
        }, 
        () -> {
            System.out.println("UHOH");
        });
        InfluxHandler influx = new InfluxHandler();
        try {
            String passwordOfficial = influx.parseData(influx.getData("keys"), plateString, false);
            System.out.println(passwordOfficial);
            System.out.println(passString);
            if(passString.equals(passwordOfficial)){
                div1();
                div2();
                // div3();
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
        // div.getStyle().set("border-radius", "10em");

        H1 plate = new H1("Plate #" + plateString);
        H1 password = new H1();

        H1 currentAmount = new H1("Thank You for Choosing Us");

        
        div.add(new HorizontalLayout(new VerticalLayout(plate, password), currentAmount));
        add(div);
    }

    public void div2(){
        Div div = new Div();
        setHorizontalComponentAlignment(Alignment.CENTER, div);
        div.getStyle().set("background-color", "#AFEEEE");
        div.getStyle().set("width", "100%");
        div.getStyle().set("height", "18.75em");
        // div.getStyle().set("border-radius", "10em");

        H1 payNeeded = new H1("Need to Pay for Parking?");
        Button pay = new Button("Pay Here");
        H1 currentAmount = new H1("Paid Status");
        pay.addClickListener(e -> {
            InfluxHandler influx = new InfluxHandler();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd-HH");  
            LocalDateTime now = LocalDateTime.now();  
            System.out.println(dtf.format(now)); 
            try {
                influx.createDB("garage");
                influx.postDataTime(dtf.format(now), plateString);
                currentAmount.getStyle().set("color", "green");
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        });
        if(isGreen()) currentAmount.getStyle().set("color", "green");
        else currentAmount.getStyle().set("color", "red");

        div.add(new HorizontalLayout(new VerticalLayout(payNeeded, pay), currentAmount));
        add(div);
    }

    public void div3(){
        Div div = new Div();
        setHorizontalComponentAlignment(Alignment.END, div);
        div.getStyle().set("background-color", "#AFEEEE");
        div.getStyle().set("width", "50%");
        div.getStyle().set("height", "18.75em");
        div.getStyle().set("border-radius", "10em");

        add(div);
    }

    public boolean isGreen(){
        InfluxHandler influx = new InfluxHandler();
        try {
            String time = influx.parseData(influx.getData("garage"), plateString, true);
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd-HH");  
            LocalDateTime now = LocalDateTime.now();  
            String influxTime = dtf.format(now);
            System.out.println("Time: " + time + "   DTF: " + influxTime);
            if(influxTime.equals(time)) return true;
            return false;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
