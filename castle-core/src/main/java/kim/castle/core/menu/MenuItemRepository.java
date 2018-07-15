package kim.castle.core.menu;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

public interface MenuItemRepository extends JpaRepository<MenuItem, Long>, QuerydslPredicateExecutor<MenuItem>,
		QuerydslBinderCustomizer<QMenuItem> {

	@Override
	default void customize(QuerydslBindings bindings, QMenuItem root) {

	}
}
