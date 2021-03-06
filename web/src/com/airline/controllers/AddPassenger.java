package com.airline.controllers;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.airline.models.Gender;
import com.airline.models.Passenger;

/**
 * Servlet implementation class AddPassenger
 */
@WebServlet("/AddPassenger")
public class AddPassenger extends HttpServlet {

	private static final long serialVersionUID = -4662803158420918553L;

	private static final Logger logger = LoggerFactory.getLogger(AddPassenger.class);

	private static final String GENDER_ERROR = "gender_error";

	private static final String BIRTHDATE_ERROR = "birthdate_error";

	private static final String LAST_NAME_ERROR = "last_name_error";

	private static final String FIRST_NAME_ERROR = "first_name_error";

	private static final String ERRORS = "errors";

	private static final String ADD_PASSENGER_JSP = "WEB-INF/views/add_passenger.jsp";

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setAttribute("first-name", "");
		request.setAttribute("last-name", "");
		request.setAttribute("dob", "");

		try {
			RequestDispatcher view = request.getRequestDispatcher(ADD_PASSENGER_JSP);
			view.forward(request, response);
		} catch (ServletException | IOException e) {
			logger.error("somethig wrong forwarding the page", e);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute(ERRORS, false);

		String firstName = request.getParameter("first-name");
		if (StringUtils.isBlank(firstName)) {
			logger.error("error parsing first name");
			request.setAttribute(ERRORS, true);
			request.setAttribute(FIRST_NAME_ERROR, true);
			request.setAttribute("first-name", "");
		} else {
			request.setAttribute("first-name", firstName);
		}

		String lastName = request.getParameter("last-name");
		if (StringUtils.isBlank(lastName)) {
			logger.error("error parsing last name");
			request.setAttribute(ERRORS, true);
			request.setAttribute(LAST_NAME_ERROR, true);
			request.setAttribute("last-name", "");
		} else {
			request.setAttribute("last-name", lastName);

		}

		Date birthDate = null;
		try {
			birthDate = new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("dob"));
		} catch (ParseException e) {
			logger.error("error parsing birthday", e);
			request.setAttribute(ERRORS, true);
			request.setAttribute(BIRTHDATE_ERROR, true);
			request.setAttribute("dob", "");
		}
		request.setAttribute("dob", request.getParameter("dob"));

		String genderStr = request.getParameter("gender");
		if (StringUtils.isBlank(genderStr)) {
			logger.error("error parsing gender");
			request.setAttribute(ERRORS, true);
			request.setAttribute(GENDER_ERROR, true);
		} else {
			request.setAttribute("gender", genderStr);
		}
		Gender gender = Gender.valueOf(genderStr.toUpperCase());

		if ((boolean) request.getAttribute(ERRORS)) {
			try {
				request.getRequestDispatcher(ADD_PASSENGER_JSP).forward(request, response);
				return;
			} catch (ServletException | IOException e) {
				logger.error("somethig wrong forwarding the page", e);
			}
		}

		Passenger passenger = new Passenger(firstName, lastName, birthDate, gender);

		synchronized (this) {
			@SuppressWarnings("unchecked")
			List<Passenger> passengers = (List<Passenger>) this.getServletContext().getAttribute("passengers");
			passengers.add(passenger);
		}
		logger.info("added new passenger: {}", passenger);

		try {
			response.sendRedirect("");
		} catch (IOException e) {
			logger.error("somethig wrong redirecting to main page", e);
		}
	}

}
