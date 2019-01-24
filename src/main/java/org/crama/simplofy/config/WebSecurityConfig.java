package org.crama.simplofy.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	private static final Logger logger = LoggerFactory.getLogger(WebSecurityConfig.class);
	
	@Autowired
	private UserDetailsService userService;
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        	.csrf().disable()
            .authorizeRequests()
            	.antMatchers("/",
            			"/registration",
            			"/view",
            			"/fonts/**",
            			"/css/**",
            			"/images/**",
            			"/js/**",
            			"/html/**",
            			"/docs/**",
            			"/favicon.ico").permitAll()
            	.antMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().fullyAuthenticated()
                .and()
                .formLogin()
                .loginPage("/")
                .defaultSuccessUrl("/stories")
                .failureUrl("/?error")
                .permitAll()
                .and()
            .logout()
            	.logoutSuccessUrl("/?logout")
            	.deleteCookies("JSESSIONID")
                .permitAll();;
        
            
    }
	
	@Autowired
    @Override
    protected void configure(AuthenticationManagerBuilder authManagerBuilder) throws Exception {
    	
        authManagerBuilder.userDetailsService(userService).passwordEncoder(passwordEncoder());
        
    }

	@Bean
    public PasswordEncoder passwordEncoder() {
    	
      logger.info("Creating password encoder bean");
      
      return new BCryptPasswordEncoder();
    }
	
	/*@Bean
    public AuthenticationSuccessHandler successHandler() {
        SimpleUrlAuthenticationSuccessHandler handler = new SimpleUrlAuthenticationSuccessHandler();
        handler.setUseReferer(true);
        handler.setDefaultTargetUrl("/stories");
        return handler;
    }*/
	
}
