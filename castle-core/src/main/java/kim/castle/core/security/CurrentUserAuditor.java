package kim.castle.core.security;

import java.util.Optional;

import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import kim.castle.core.security.DefaultDetailsService.CurrentUserDetails;
import kim.castle.core.user.User;

@Component
@EnableJpaAuditing
public class CurrentUserAuditor implements AuditorAware<User> {

	@Override
	public Optional<User> getCurrentAuditor() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null) {
			return Optional.empty();
		}
		Object principal = authentication.getPrincipal();
		if (principal instanceof CurrentUserDetails) {
			return Optional.ofNullable(((CurrentUserDetails) principal).getCustomUser());
		}
		return Optional.empty();
	}

}
