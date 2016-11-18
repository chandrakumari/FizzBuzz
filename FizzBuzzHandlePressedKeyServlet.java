package com.twilio;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.twilio.twiml.Say;
import com.twilio.twiml.TwiMLException;
import com.twilio.twiml.VoiceResponse;

/**
 * Servlet implementation class FizzBuzzHandlePressedKeyServlet
 */
@WebServlet("/FizzBuzzHandlePressedKeyServlet")
public class FizzBuzzHandlePressedKeyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FizzBuzzHandlePressedKeyServlet() {
		super();
		// TODO Auto-generated constructor stub
	}//end constructor
	
    //method to convert number to fizz buzzed string
	public String getFBNum(int num){
		int i = 1;
		String result = "";
		while(i <= num){
			if(i % 5 == 0 && i % 3 == 0){
				result += "Fizz Buzz ";
			}//end if
			else if(i % 5 == 0){
				result += "Buzz ";
			}//end else if
			else if(i % 3 == 0){
				result += "Fizz ";
			}//end else if
			else{
				result += Integer.toString(i) + " ";
			}//end else
			i++;
		}//end while
		return result;
	}//end method
    
	//get digits and convert to fizzbuzz then say it back to user
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String digits = request.getParameter("Digits");
		VoiceResponse twiml;
		Say say;
		if (digits != null) {
			int num = Integer.parseInt(digits);
			if(num < 100){
				String result = getFBNum(num);
				say = new Say.Builder(result).build();
			}//end if
			else{
				say = new Say.Builder("No number found, sorry. Goodbye.").build();
			}//end else
			twiml = new VoiceResponse.Builder().say(say).build();
		} else {
			response.sendRedirect("/FizzBuzzPlay");
			return;
		}//end else

		response.setContentType("application/xml");
		try {
			response.getWriter().print(twiml.toXml());
		} catch (TwiMLException e) {
			e.printStackTrace();
		}//end catch
	}//end method

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}//end method

}//end class
