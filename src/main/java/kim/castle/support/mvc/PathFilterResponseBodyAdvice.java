package kim.castle.support.mvc;

import javax.servlet.http.HttpServletRequest;

import org.springframework.core.MethodParameter;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.AbstractMappingJacksonResponseBodyAdvice;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.google.common.base.Strings;

import ch.mfrey.jackson.antpathfilter.AntPathPropertyFilter;
import kim.castle.support.data.DataWrapper;
import kim.castle.support.data.Tree;
import kim.castle.support.json.PathFilter;

@ControllerAdvice
@Order(value = Ordered.LOWEST_PRECEDENCE)
public class PathFilterResponseBodyAdvice extends AbstractMappingJacksonResponseBodyAdvice {

	@Override
	protected void beforeBodyWriteInternal(MappingJacksonValue bodyContainer, MediaType contentType,
			MethodParameter returnType, ServerHttpRequest request, ServerHttpResponse response) {

		Object value = bodyContainer.getValue();

		if (returnType.getDeclaringClass().isAssignableFrom(DataWrapper.class)) {
			bodyContainer.setValue(JsonResponseResult.success(value));
			return;
		}
		if (value != null) {
			HttpServletRequest httpRequest = ((ServletServerHttpRequest) request).getServletRequest();

			// antpathfilter
			FilterProvider filterProvider = null;

			String pathFilter = returnType.hasMethodAnnotation(PathFilter.class)
					? returnType.getMethodAnnotation(PathFilter.class).value()
					: httpRequest.getParameter("path_filter");
			if (!Strings.isNullOrEmpty(pathFilter)) {
				filterProvider = new SimpleFilterProvider().addFilter("antPathFilter",
						new AntPathPropertyFilter(pathFilter.split(",")));
			} else {
				if (value instanceof Tree) {
					filterProvider = new SimpleFilterProvider().addFilter("antPathFilter", new AntPathPropertyFilter(
							new String[] { "*", "*.*", "*.*.id", "*.*.name", "*.*.title", "*.*.fullName" }));
				} else if (value instanceof Page) {
					filterProvider = new SimpleFilterProvider().addFilter("antPathFilter", new AntPathPropertyFilter(
							new String[] { "*", "*.*", "*.*.id", "*.*.name", "*.*.title", "*.*.fullName" }));
				} else {
					filterProvider = new SimpleFilterProvider().addFilter("antPathFilter",
							new AntPathPropertyFilter(new String[] { "*", "*.id", "*.name", "*.title", "*.fullName" }));
				}
			}

			bodyContainer.setFilters(filterProvider);
		}

		// bodyContainer.setFilters(filters);

	}

}