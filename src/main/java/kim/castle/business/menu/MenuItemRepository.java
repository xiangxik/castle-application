package kim.castle.business.menu;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;

public interface MenuItemRepository extends JpaRepository<MenuItem, Long>, QuerydslPredicateExecutor<MenuItem>,
		QuerydslBinderCustomizer<QMenuItem> {

}
