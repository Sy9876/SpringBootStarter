package cn.sy;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	private static Logger logger = LoggerFactory.getLogger(SecurityConfig.class);

	@Bean
	public UserDetailsService userDetailsService() {
		logger.info("userDetailsService");
		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
		manager.createUser(User.withUsername("user").password("password").roles("USER").build());
		return manager;
	}
	
	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider(UserDetailsService userDetailsService) {
		logger.info("daoAuthenticationProvider");
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		return provider;
	}
	
	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider2() {
		logger.info("daoAuthenticationProvider2");
		
		InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
		manager.createUser(User.withUsername("admin").password("password").roles("ADMIN").build());
		
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(manager);
		return provider;
	}
	
	@Bean
	public ProviderManager providerManager(DaoAuthenticationProvider daoAuthenticationProvider,
			DaoAuthenticationProvider daoAuthenticationProvider2) {
		logger.info("providerManager");
		List<AuthenticationProvider> providers = new ArrayList<AuthenticationProvider>();
		providers.add(daoAuthenticationProvider);
		providers.add(daoAuthenticationProvider2);
		return new ProviderManager(providers);
	}
	
	protected void configure(HttpSecurity http) throws Exception {
		logger.info("configure");
		http
		.authorizeRequests()
		.antMatchers("/login.do").permitAll()
		.anyRequest().authenticated();

	}
	
	
}
