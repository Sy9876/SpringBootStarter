package cn.sy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import cn.sy.dao.UserDao;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDao userDetailsService;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//		auth
//		.jdbcAuthentication()
//			.dataSource(dataSource)
//			.withDefaultSchema()
//			.withUser(User.withDefaultPasswordEncoder().username("user").password("password").roles("USER"));
		auth
		.userDetailsService(userDetailsService);
			
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
	        .antMatchers("/count.do").authenticated()
//	        .antMatchers("/**").permitAll()
	        .anyRequest().authenticated()
//                .and().formLogin().loginPage("/login").failureUrl("/login?error").permitAll().and()
//                .logout()
//                .permitAll()
                ;
    }
	
}
