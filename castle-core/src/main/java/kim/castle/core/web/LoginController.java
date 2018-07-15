package kim.castle.core.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.WebAttributes;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.WebUtils;

@Controller
@RequestMapping("/login")
public class LoginController {

	@RequestMapping(method = RequestMethod.GET)
	public String page(HttpServletRequest request, Model model) {
		AuthenticationException exception = (AuthenticationException) WebUtils.getSessionAttribute(request,
				WebAttributes.AUTHENTICATION_EXCEPTION);
		if (exception != null) {
			model.addAttribute("ex", exception.getLocalizedMessage());
		}
		return "/login";
	}
}