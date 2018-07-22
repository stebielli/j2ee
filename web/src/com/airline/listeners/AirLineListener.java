package com.airline.listeners;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.airline.models.Passenger;

/**
 * Application Lifecycle Listener implementation class AirLineListener
 *
 */
@WebListener
public class AirLineListener implements ServletContextListener {

	private static final Logger logger = LoggerFactory.getLogger(AirLineListener.class);

	@Override
	public void contextInitialized(ServletContextEvent event) {
		logger.info("hello ");

		ServletContext servletContext = event.getServletContext();
		List<Passenger> passengers = new ArrayList<>();
		servletContext.setAttribute("passengers", passengers);
		logger.info("initialized passengers list");

	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		logger.info("bye :)");
	}

}
