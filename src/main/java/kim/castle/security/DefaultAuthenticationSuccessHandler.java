package kim.castle.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

import kim.castle.busi.user.User;
import kim.castle.security.DefaultDetailsService.CurrentUserDetails;
import kim.castle.support.data.DataWrapper;
import kim.castle.support.mvc.JsonResponseResult;

public class DefaultAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	@Autowired
	private ObjectMapper objectMapper;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		CurrentUserDetails principal = (CurrentUserDetails) authentication.getPrincipal();
		User user = principal.getCustomUser();

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		DataWrapper data = DataWrapper.instance().v("username", user.getUsername()).v("name", user.getName()).v("token",
				request.getAttribute(ClientTokenBasedRemeberMeServices.REMEMBER_TOKEN_ATTRIBUTE));
		objectMapper.writeValue(response.getWriter(), JsonResponseResult.success(data));

	}

}
