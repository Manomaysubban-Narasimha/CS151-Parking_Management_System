package com.project.CS151Parking_Management_System;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;


public class InfluxHandler implements Serializable{

	private static volatile InfluxHandler handler;

	private InfluxHandler()
	{
		if(handler != null)
			throw new RuntimeException("Influx handler already instantiated." + 
			                           " Use getInstance() method to get the single handler.");
	}

	static InfluxHandler getInstance()
	{
		if(handler == null)
		{
			synchronized(InfluxHandler.class)
			{
				if(handler == null) handler = new InfluxHandler();
			}
		}
		return handler;
	}

	InfluxHandler readResolve()
	{
		return getInstance();
	}

	public boolean createDB(String dataBaseName) throws IOException{
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
			return "".equals(response);
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
            return s.hasNext() ? s.next() : "";
        }
    }

    public boolean postData(String licenseplate, String secondParam, String database) throws IOException{
        URL url = new URL("http://localhost:8086/write?db=" + database);
		HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
		httpConn.setRequestMethod("POST");

		httpConn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

		httpConn.setDoOutput(true);
		OutputStreamWriter writer = new OutputStreamWriter(httpConn.getOutputStream());
		writer.write("mymeas,secondParam=" + secondParam + " licensePlate=\"" + licenseplate + "\"");
		writer.flush();
		writer.close();
		httpConn.getOutputStream().close();

		InputStream responseStream = httpConn.getResponseCode() / 100 == 2
				? httpConn.getInputStream()
				: httpConn.getErrorStream();
		try (Scanner s = new Scanner(responseStream).useDelimiter("\\A")) {
            String response = s.hasNext() ? s.next() : "";
            return response.equals("");
        }
    }

	public String parseData(String response, String substring){
		int index = response.lastIndexOf(substring);
        if(index == -1) return "Wrong License Plate";
        String restOfresponse = response.substring(index);
        index = (restOfresponse.indexOf('"', restOfresponse.indexOf('"') + 1));
        restOfresponse = restOfresponse.substring(index + 1);
        restOfresponse = restOfresponse.substring(0, restOfresponse.indexOf('"'));
        return restOfresponse;
    }

	public String getAlphaNumericString(int n){

		// chose a Character random from this String
		String alphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
			+ "0123456789"
			+ "abcdefghijklmnopqrstuvxyz";

		// create StringBuffer size of alphaNumericString
		StringBuilder sb = new StringBuilder(n);

		for (int i = 0; i < n; i++) {

		// generate a random number between
		// 0 to alphaNumericString variable length
		int index = (int)(alphaNumericString.length()* Math.random());

		// add Character one by one in end of sb
		sb.append(alphaNumericString.charAt(index));
		}

		return sb.toString();
	}
}