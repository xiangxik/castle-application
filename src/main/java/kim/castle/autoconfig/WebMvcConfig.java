package kim.castle.autoconfig;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

@ConditionalOnWebApplication
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/login").setViewName("/login");
	}

	@Override
	public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> resolvers) {
		resolvers.add(new DefaultHandlerExceptionResolver() {
			@Override
			protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response,
					Object handler, Exception ex) {
				ModelAndView modelAndView = super.doResolveException(request, response, handler, ex);
				if (modelAndView == null) {
					modelAndView = new ModelAndView();
				}
				modelAndView.addObject("status", HttpStatus.resolve(response.getStatus()));
				modelAndView.setViewName("exception");
				modelAndView.addObject("ex", ex);
				return modelAndView;
			}
		});
	}

}
