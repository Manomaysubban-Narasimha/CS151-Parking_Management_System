package com.classProject.project;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class InfluxHandler {
	
    public InfluxHandler(){
    
    }
	
    public void createDB(String dataBaseName) throws IOException{
        URL url = new URL("http://localhost:8086/query");
		HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
		httpConn.setRequestMethod("POST");

		httpConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

		httpConn.setDoOutput(true);
		OutputStreamWriter writer = new OutputStreamWriter(httpConn.getOutputStream());
		writer.write("q=CREATE%20DATABASE%20%22" + dataBaseName + "%22");
		writer.flush();
		writer.close();
		httpConn.getOutputStream().close();

		InputStream responseStream = httpConn.getResponseCode() / 100 == 2
				? httpConn.getInputStream()
				: httpConn.getErrorStream();
		try (Scanner s = new Scanner(responseStream).useDelimiter("\\A")) {
            String response = s.hasNext() ? s.next() : "";
            System.out.println(response);
        }
    }

    public String getData(String dataBaseName) throws IOException{
        URL url = new URL("http://localhost:8086/query?db=" + dataBaseName  + "&pretty=true&q=SELECT%20%2A%20FROM%20%22mymeas%22");
		HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
		httpConn.setRequestMethod("GET");

		InputStream responseStream = httpConn.getResponseCode() / 100 == 2
				? httpConn.getInputStream()
				: httpConn.getErrorStream();
		try (Scanner s = new Scanner(responseStream).useDelimiter("\\A")) {
            String response = s.hasNext() ? s.next() : "";
            return response;
        }
    }

    public boolean postData(String password, String licensePlate) throws IOException{
        URL url = new URL("http://localhost:8086/write?db=mydb");
		HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
		httpConn.setRequestMethod("POST");

		httpConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

		httpConn.setDoOutput(true);
		OutputStreamWriter writer = new OutputStreamWriter(httpConn.getOutputStream());
		writer.write("mymeas,password=" + password + " licensePlate=\"" + licensePlate + "\"");
		writer.flush();
		writer.close();
		httpConn.getOutputStream().close();

		InputStream responseStream = httpConn.getResponseCode() / 100 == 2
				? httpConn.getInputStream()
				: httpConn.getErrorStream();
		try (Scanner s = new Scanner(responseStream).useDelimiter("\\A")) {
            String response = s.hasNext() ? s.next() : "";
            if(response == "") return true;
        }
		return false;
    }

    public boolean postDataTime(String licensePlate, String hour) throws IOException{
        URL url = new URL("http://localhost:8086/write?db=garage");
		HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
		httpConn.setRequestMethod("POST");

		httpConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

		httpConn.setDoOutput(true);
		OutputStreamWriter writer = new OutputStreamWriter(httpConn.getOutputStream());
		writer.write("mymeas,licensePlate=" + licensePlate + " hour=\"" + hour + "\"");
		writer.flush();
		writer.close();
		httpConn.getOutputStream().close();

		InputStream responseStream = httpConn.getResponseCode() / 100 == 2
				? httpConn.getInputStream()
				: httpConn.getErrorStream();
		try (Scanner s = new Scanner(responseStream).useDelimiter("\\A")) {
            String response = s.hasNext() ? s.next() : "";
            if(response == "") return true;
        }
		return false;
    }

	public String parseData(String response, String substring, Boolean isGarage){
        int index;
		if(isGarage) index = response.lastIndexOf(substring);
		else index = response.indexOf(substring);
        if(index == -1) return "Wrong License Plate";
        String restOfresponse = response.substring(index);
        index = (restOfresponse.indexOf('"', restOfresponse.indexOf('"') + 1));
        restOfresponse = restOfresponse.substring(index + 1);
        restOfresponse = restOfresponse.substring(0, restOfresponse.indexOf('"'));
        return restOfresponse;
    }
}
