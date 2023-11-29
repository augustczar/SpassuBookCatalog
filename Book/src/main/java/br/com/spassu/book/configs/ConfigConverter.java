package br.com.spassu.book.configs;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import br.com.spassu.book.converter.Converter;

@Configuration
public class ConfigConverter implements WebMvcConfigurer  {

	   @Override
	    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
	        converters.add(new Converter());
	    }
}
