package com.airline.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.airline.service.FlightService;

@WebServlet("/FlightDetails")
public class FlightDetails extends HttpServlet {

	private static final long serialVersionUID = -8682402696104335371L;

	private static final Logger logger = LoggerFactory.getLogger(FlightDetails.class);

	@EJB
	private FlightService flightService;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {

			PrintWriter writer = response.getWriter();
			writer.println("Some flight details: ");
			writer.println("from: " + flightService.getFrom() + " to: " + flightService.getTo());
			logger.info("logged some info");

		} catch (IOException e) {
			logger.error("shit happens", e);
		}
	}

}
