package com.twilio;

import java.io.IOException;
import java.net.URI;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.twilio.http.TwilioRestClient;
import com.twilio.rest.api.v2010.account.Call;
import com.twilio.rest.api.v2010.account.CallCreator;
import com.twilio.type.PhoneNumber;

//import com.twilio.http.TwilioRestClient;
//import com.twilio.type.PhoneNumber;
//import com.twilio.rest.api.v2010.account.CallCreator;
//import com.twilio.rest.api.v2010.account.Call;


/**
 * Servlet implementation class MakeCall
 */
@WebServlet("/MakeCall")
public class MakeCall extends HttpServlet {
	public static final String ACCOUNT_SID = "AC71930d3fcc4df9383be6c8e49cee5b14";
	public static final String AUTH_TOKEN = "8eae9a1f10f96c2a5a71226ee7406234";
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MakeCall() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		TwilioRestClient client = new TwilioRestClient.Builder(ACCOUNT_SID, AUTH_TOKEN).build();
        String number = request.getParameter("Number");
        String timeDelay = request.getParameter("Time");
        PhoneNumber toNumber = new PhoneNumber(number); // Replace with your phone number
        PhoneNumber from = new PhoneNumber("4082905894"); // Replace with a Twilio number
        URI uri = URI.create("https://floating-inlet-76345.herokuapp.com/FizzBuzzPlay");

        // Make the call
        int time = Integer.parseInt(timeDelay);
        try
		{
			Thread.sleep(time*1000);
		}
		catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        Call call = new CallCreator(toNumber, from, uri).create(client);
        // Print the call SID (a 32 digit hex like CA123..)
        System.out.println(call.getSid());
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
