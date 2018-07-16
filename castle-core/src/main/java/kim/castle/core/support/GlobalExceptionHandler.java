package kim.castle.core.support;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler
	public ModelAndView handle(Exception ex) {
		Map<String, Object> model = new HashMap<>();
		model.put("ex", ex);
		return new ModelAndView("/exception", model);
	}
}
