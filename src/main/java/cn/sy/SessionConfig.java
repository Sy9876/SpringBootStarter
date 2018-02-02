package cn.sy;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.session.jdbc.config.annotation.web.http.EnableJdbcHttpSession;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import cn.sy.dao.UserDao;

@Configuration
@EnableJdbcHttpSession
public class SessionConfig {

	// spring.session.store-type=jdbc in application.properties not work.
	
	@Autowired
	private DataSource dataSource;
	
	
}
