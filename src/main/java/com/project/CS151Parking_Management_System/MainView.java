package com.project.CS151Parking_Management_System;

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
    public MainView(){
        getStyle().set("text-align", "center");
        
        Div div = new Div();
        setHorizontalComponentAlignment(Alignment.CENTER, div);
        div.getStyle().set("background-color", "#AFEEEE");
        div.getStyle().set("width", "50%");
        div.getStyle().set("height", "18.75em");
        div.getStyle().set("margin", "auto");
        div.getStyle().set("border-radius", "10em");

        HtmlComponent br = new HtmlComponent("br");
        HtmlComponent br3 = new HtmlComponent("br");
        HtmlComponent br4 = new HtmlComponent("br");


        Button login = new Button("Login");
        login.addClickListener(e ->
            login.getUI().ifPresent(ui ->
                ui.navigate("login"))
        );
        login.getStyle().set("margin-left", "14em");
		Button register = new Button("Register");
        register.addClickListener(e ->
            register.getUI().ifPresent(ui ->
                ui.navigate("register"))
        );
        H2 p = new H2();
        p.getStyle().set("color", "#1D3F6E");
        p.add("Welcome to 151 Garage");
        div.add(br3);
        div.add(br4);
        div.add(p);
        div.add(br);
        div.add(new HorizontalLayout(login, register));
		add(div);


        Div div2 = new Div();
        div2.getStyle().set("background-color", "#AFEEEE");
        div2.getStyle().set("width", "30%");
        div2.getStyle().set("height", "10.75em");
        div2.getStyle().set("margin", "auto");
        div2.getStyle().set("border-radius", "10em");
        div2.getStyle().set("text-align", "center");
        HtmlComponent br2 = new HtmlComponent("br");
        div2.add(br2);

        PercentageFull pFull = new PercentageFull();
        pFull.checkAndReset();
        H3 amountFull = new H3();
        amountFull.getStyle().set("color", "#1D3F6E");

        amountFull.setText("151 is Currently " + (100 - (2 * pFull.getCurrentAmount())) + "% full");
        div2.add(amountFull);
        add(div2);


        Div div3 = new Div();
        div3.getStyle().set("background-color", "#AFEEEE");
        div3.getStyle().set("width", "15%");
        div3.getStyle().set("height", "6.75em");
        div3.getStyle().set("margin", "auto");
        div3.getStyle().set("border-radius", "10em");
        Paragraph creds = new Paragraph("A Group 6 Production");
        creds.getStyle().set("color", "#1D3F6E");
        div3.add(br);
        div3.add(creds);
        add(div3);
    
    }
}