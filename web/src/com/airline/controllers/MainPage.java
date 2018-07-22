package com.airline.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.airline.models.Passenger;

/**
 * Servlet implementation class MainPage
 */
@WebServlet("")
public class MainPage extends HttpServlet {

	private static final long serialVersionUID = 3258495591991674735L;

	private static final Logger logger = LoggerFactory.getLogger(MainPage.class);

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			@SuppressWarnings("unchecked")
			List<Passenger> passengers = (List<Passenger>) this.getServletContext().getAttribute("passengers");
			response.setContentType("text/html");
			response.getWriter().append("Passenger has been added to the list: " + passengers.get(0));

		} catch (IOException e) {
			logger.error("something wrong printing passengers", e);
		}
	}

}
