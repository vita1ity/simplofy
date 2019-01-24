package org.crama.simplofy.error;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice
public class ControllerExceptionHandler {

	private static final Logger logger = Logger.getLogger(ControllerExceptionHandler.class);

	@ExceptionHandler(MessageException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public MessageError processErrorMessages(MessageException ex) {
		logger.error("Message Exception: " + ex.getCode() + ", " + ex.getMessage());

		String code = ex.getCode();
		String message = ex.getMessage();

		MessageError error = new MessageError(code, message);

		return error;
	}

}
