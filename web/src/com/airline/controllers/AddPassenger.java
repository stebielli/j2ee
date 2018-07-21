package com.airline.controllers;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			RequestDispatcher view = request.getRequestDispatcher("WEB-INF/views/add_passenger.jsp");
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
			logger.error("{}", "error parsing first name");
			request.setAttribute(ERRORS, true);
			request.setAttribute(FIRST_NAME_ERROR, true);
		}

		String lastName = request.getParameter("last-name");
		if (StringUtils.isBlank(firstName)) {
			logger.error("{}", "error parsing last name");
			request.setAttribute(ERRORS, true);
			request.setAttribute(LAST_NAME_ERROR, true);
		}

		Date birthDate = null;
		try {
			birthDate = new SimpleDateFormat("dd/MM/yyyy").parse(request.getParameter("dob"));
		} catch (ParseException e) {
			logger.error("error parsing birthday", e);
			request.setAttribute(ERRORS, true);
			request.setAttribute(BIRTHDATE_ERROR, true);
		}

		String genderStr = request.getParameter("gender");
		if (StringUtils.isBlank(firstName)) {
			logger.error("{}", "error parsing gender");
			request.setAttribute(ERRORS, true);
			request.setAttribute(GENDER_ERROR, true);
		}
		Gender gender = Gender.valueOf(genderStr.toUpperCase());

		Passenger passenger = new Passenger(firstName, lastName, birthDate, gender);
		logger.info("created new passenger: {}", passenger);

	}

}
