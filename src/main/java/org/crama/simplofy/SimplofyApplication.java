package org.crama.simplofy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

@SpringBootApplication
/*@ComponentScan("org.crama.simplofy")*/
@EntityScan(
	basePackages = "org.crama.simplofy.model",
	basePackageClasses = {Jsr310JpaConverters.class}
)
public class SimplofyApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(SimplofyApplication.class, args);
	}
	
	@Bean(name = "messageSource")
	public ReloadableResourceBundleMessageSource messageSource() {
	  ReloadableResourceBundleMessageSource messageBundle = new ReloadableResourceBundleMessageSource();
	  messageBundle.setBasename("classpath:messages/messages");
	  messageBundle.setDefaultEncoding("UTF-8");
	  return messageBundle;
	}
	
}
