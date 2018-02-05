package cn.sy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.ldap.authentication.ad.ActiveDirectoryLdapAuthenticationProvider;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@EnableWebSecurity
@PropertySource("security.properties")
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	private static Logger logger = LoggerFactory.getLogger(SecurityConfig.class);
	
	@Value("${secure.key}")
	private String secureKey;
	
	@Value("${secure.ad.domain}")
	private String secureAdDomain;
	
	@Value("${secure.ad.url}")
	private String secureAdUrl;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth, AuthenticationProvider adProvider) throws Exception {
		logger.info("secureKey=" + secureKey);
//		auth
//		.jdbcAuthentication()
//			.dataSource(dataSource)
//			.withDefaultSchema()
//			.withUser(User.withDefaultPasswordEncoder().username("user").password("password").roles("USER"));
//		auth
//		.userDetailsService(userDetailsService);
//		
		auth.authenticationProvider(adProvider);
			
	}
	
	@Bean
	ActiveDirectoryLdapAuthenticationProvider adProvider() {
		return new ActiveDirectoryLdapAuthenticationProvider(secureAdDomain, secureAdUrl);
		
	}
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
		
        http
        	.csrf()
        	.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
//        	.disable()
        	
        	.and()        	
        	.authorizeRequests()
	        .antMatchers("/void.do", "/login.do").permitAll()
//	        .antMatchers("/count.do").authenticated()
//	        .antMatchers("/user.do").hasRole("USER")
//	        .antMatchers("/insert.do").hasRole("ADMIN")
//	        .antMatchers("/menus.do").hasRole("ADMIN")
	        
	        // hasRole match to ROLE_ + str
	        .antMatchers("/count.do").hasRole("count")
	        .antMatchers("/user.do").hasRole("user")
	        .antMatchers("/insert.do").hasRole("insert")
	        .antMatchers("/menus.do").hasRole("menus")
	        
//	        .antMatchers("/**").permitAll()
	        .anyRequest().authenticated()
//                .and().formLogin().loginPage("/login").failureUrl("/login?error").permitAll().and()
//                .logout()
//                .permitAll()
                ;
    }
	
}
