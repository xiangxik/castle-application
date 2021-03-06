package kim.castle.core.support.mvc;

import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.data.web.config.PageableHandlerMethodArgumentResolverCustomizer;
import org.springframework.stereotype.Component;

@Component
public class PageableResolverCustomizer implements PageableHandlerMethodArgumentResolverCustomizer {

	@Override
	public void customize(PageableHandlerMethodArgumentResolver pageableResolver) {
		pageableResolver.setOneIndexedParameters(true);
		pageableResolver.setPageParameterName("current");
		pageableResolver.setSizeParameterName("rowCount");
	}

}
