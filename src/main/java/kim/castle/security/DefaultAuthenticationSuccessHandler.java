package kim.castle.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import ch.mfrey.jackson.antpathfilter.AntPathPropertyFilter;

public class DefaultAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	@Autowired
	private ObjectMapper objectMapper;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		CustomUserDetails<?, ?> principal = (CustomUserDetails<?, ?>) authentication.getPrincipal();
		ObjectWriter objectWriter = objectMapper.writer(new SimpleFilterProvider().addFilter("antPathFilter",
				new AntPathPropertyFilter(new String[] { "*", "*.*", "*.*.id", "*.*.name" })));
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		objectWriter.writeValue(response.getWriter(), principal.getCustomUser());
	}

}
