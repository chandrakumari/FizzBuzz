package com.twilio;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.twilio.twiml.Gather;
import com.twilio.twiml.Method;
import com.twilio.twiml.Say;
import com.twilio.twiml.TwiMLException;
import com.twilio.twiml.VoiceResponse;
@WebServlet("/FizzBuzzPlay")
public class FizzBuzzPlay extends HttpServlet{
	//to get digits that user enters
	public void service(HttpServletRequest request, HttpServletResponse response) throws IOException {
		VoiceResponse twiml = new VoiceResponse.Builder()
				.say(new Say.Builder("Enter a digit less than 100 followed by a pound").build())
				.gather(new Gather.Builder()
				        .action("/handle-key")
				        .method(Method.GET)
				        .numDigits(2)
				        .build()).build();

		response.setContentType("application/xml");
		try {
			response.getWriter().print(twiml.toXml());
		} catch (TwiMLException e) {
			e.printStackTrace();
		}//end catch
	}//end method

}//end class
