package kim.castle.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

public class ClientTokenBasedRemeberMeServices extends PersistentTokenBasedRememberMeServices {

	public static final String REMEMBER_TOKEN_ATTRIBUTE = "_rememberToken";

	public ClientTokenBasedRemeberMeServices(String key, UserDetailsService userDetailsService,
			PersistentTokenRepository tokenRepository) {
		super(key, userDetailsService, tokenRepository);

		setAlwaysRemember(true);
	}

	@Override
	protected String extractRememberMeCookie(HttpServletRequest request) {
		return request.getParameter("token");
	}

	@Override
	protected void setCookie(String[] tokens, int maxAge, HttpServletRequest request, HttpServletResponse response) {
		// super.setCookie(tokens, maxAge, request, response);
		String cookieValue = encodeCookie(tokens);
		request.setAttribute(REMEMBER_TOKEN_ATTRIBUTE, cookieValue);
	}

	@Override
	protected void cancelCookie(HttpServletRequest request, HttpServletResponse response) {
		// super.cancelCookie(request, response);
	}

}
