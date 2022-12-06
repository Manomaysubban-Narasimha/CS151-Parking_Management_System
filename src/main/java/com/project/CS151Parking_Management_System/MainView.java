package com.project.CS151Parking_Management_System;

import java.io.IOException;

import com.vaadin.flow.component.HtmlComponent;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;

@Route("")
public class MainView extends VerticalLayout{

    private Div div, div2, div3;
    private HtmlComponent lineBreak;
    private Button login, register;
    private H2 greetings;
    private H3 amountFull;
    private PercentageFull occupancyRatio;
    private Paragraph creds;

    public MainView(){

        getStyle().set("text-align", "center");
        
        div = new Div();
        setHorizontalComponentAlignment(Alignment.CENTER, div);
        div.getStyle().set("background-color", "#AFEEEE");
        div.getStyle().set("width", "50%");
        div.getStyle().set("height", "18.75em");
        div.getStyle().set("margin", "auto");
        div.getStyle().set("border-radius", "10em");
        div.getStyle().set("font-family", "futura");


        lineBreak = new HtmlComponent("br");


        login = new Button("Login");
        login.addClickListener(e ->
            login.getUI().ifPresent(ui ->
                ui.navigate("login"))
        );
        login.getStyle().set("margin-left", "14em");
		register = new Button("Register");
        register.addClickListener(e ->
            register.getUI().ifPresent(ui ->
                ui.navigate("register"))
        );
        greetings = new H2();
        greetings.getStyle().set("color", "#1D3F6E");
        greetings.add("Welcome to 151 Garage");
        div.add(lineBreak);
        div.add(lineBreak);
        div.add(greetings);
        div.add(lineBreak);
        div.add(new HorizontalLayout(login, register));
		add(div);


        div2 = new Div();
        div2.getStyle().set("background-color", "#AFEEEE");
        div2.getStyle().set("width", "30%");
        div2.getStyle().set("height", "10.75em");
        div2.getStyle().set("margin", "auto");
        div2.getStyle().set("border-radius", "10em");
        div2.getStyle().set("text-align", "center");
        div2.getStyle().set("font-family", "futura");

        div2.add(lineBreak);

        occupancyRatio = new PercentageFull();
        occupancyRatio.checkAndReset();
        amountFull = new H3();
        amountFull.getStyle().set("color", "#1D3F6E");

        InfluxHandler influx = InfluxHandler.getInstance();
        try {
            influx.createDB("spotsAvailable");
            influx.createDB("mydb");
            influx.createDB("keys");
            influx.createDB("vehicleType");
            influx.createDB("garage");
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        amountFull.setText("151 is Currently " + (100 - (2 * occupancyRatio.getCurrentAmount())) + "% full");
        div2.add(amountFull);
        add(div2);


        div3 = new Div();
        div3.getStyle().set("background-color", "#AFEEEE");
        div3.getStyle().set("width", "15%");
        div3.getStyle().set("height", "6.75em");
        div3.getStyle().set("margin", "auto");
        div3.getStyle().set("border-radius", "10em");
        div3.getStyle().set("font-family", "lato");

        creds = new Paragraph("A Group 6 Production");
        creds.getStyle().set("color", "#1D3F6E");
        div3.add(lineBreak);
        div3.add(creds);
        add(div3);
    
    }
}