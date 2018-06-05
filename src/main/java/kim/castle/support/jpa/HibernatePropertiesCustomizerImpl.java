package kim.castle.support.jpa;

import java.util.Map;

import org.springframework.boot.autoconfigure.orm.jpa.HibernatePropertiesCustomizer;
import org.springframework.stereotype.Component;

@Component
public class HibernatePropertiesCustomizerImpl implements HibernatePropertiesCustomizer {

	@Override
	public void customize(Map<String, Object> hibernateProperties) {
		hibernateProperties.put("hibernate.current_session_context_class",
				"org.springframework.orm.hibernate5.SpringSessionContext");
		hibernateProperties.put("hibernate.enable_lazy_load_no_trans", true);
	}

}
