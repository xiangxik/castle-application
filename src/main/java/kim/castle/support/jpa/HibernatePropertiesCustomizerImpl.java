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

		hibernateProperties.put("hibernate.show_sql", true);
		hibernateProperties.put("hibernate.format_sql", true);

		hibernateProperties.put("hibernate.id.new_generator_mappings", true);
		hibernateProperties.put("javax.persistence.validation.mode", "none");
		hibernateProperties.put("hibernate.query.substitutions", "true 1, false 0");
		hibernateProperties.put("hibernate.max_fetch_depth", 4);
		hibernateProperties.put("hibernate.bytecode.use_reflection_optimizer", true);
	}

}
