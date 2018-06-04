package kim.castle.busi;

import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import kim.castle.busi.user.User;
import kim.castle.busi.user.UserRepository;

@Component
public class DataInitializer implements SmartInitializingSingleton {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public void afterSingletonsInstantiated() {
		if (userRepository.count() == 0) {
			User user = new User();
			user.setUsername("admin");
			user.setPassword(passwordEncoder.encode("qwe123"));
			user.setName("管理员");
			userRepository.save(user);
		}
	}

}
