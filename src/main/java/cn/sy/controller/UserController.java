package cn.sy.controller;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.sy.dao.UserDao;
import cn.sy.domain.User;


@RestController
public class UserController {
	private static Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private AuthenticationManager authManager;

    @RequestMapping("/login.do")
    public Authentication login(
			@RequestParam(value="name", required=true) String name,
			@RequestParam(value="password", required=true) String password) throws Exception {
    	Authentication auth = null;
    	logger.info("login.do start. name=" + name);
    	try {
    		auth = authManager.authenticate(new UsernamePasswordAuthenticationToken(name, password));
    		SecurityContextHolder.getContext().setAuthentication(auth);
    		logger.info("login.do", auth);
		} catch (Exception e) {
			logger.error("login.do", e);
			throw e;
		}

    	logger.info("login.do end");
    	return auth;
    }
    
    @RequestMapping("/whoami.do")
    public UserDetails whoami() throws Exception {
    	UserDetails principal = null;
    	try {
    		Object o = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    		if(o instanceof UserDetails) {
    			principal=(UserDetails)o;
    		}
    		logger.info("whoami.do", principal);
		} catch (Exception e) {
			logger.error("whoami.do", e);
			throw e;
		}

    	logger.info("whoami.do end");
    	return principal;
    }
    
    
    @RequestMapping("/user.do")
    public User greeting(
			@RequestParam(value="name", required=true) String name) {
    	
    	User user = null;
    	
//    	System.out.println("user start. name=" + name);
    	logger.info("user.do start. name=" + name);
    	try {
    		user=userDao.findByName(name);
//    		System.out.println(user);
    		logger.info("user.do", user);
		} catch (Exception e) {
//			e.printStackTrace();
			logger.error("user.do", e);
		}
//    	System.out.println("user end");
    	logger.info("user.do end");
    	return user;
    }

    @RequestMapping("/void.do")
    public void empty() {

//    	System.out.println("void.do start.");
    	logger.info("void.do end");
    }
    
    @RequestMapping("/count.do")
    public int count() {
    	int cnt=0;
//    	System.out.println("count.do start.");
    	logger.info("count.do end");
    	try {
    		cnt=userDao.count();
//    		System.out.println(cnt);
    		logger.info("count.do ", cnt);
		} catch (Exception e) {
//			e.printStackTrace();
			logger.error("count.do ", e);
		}
//    	System.out.println("count.do end");
    	logger.info("count.do end");
    	return cnt;
    }

    
    @ResponseBody
    @RequestMapping("/insert.do")
    public int insert() {
    	int cnt=0;
//    	System.out.println("insert.do start.");
    	logger.info("insert.do start");
    	try {
    		cnt=userDao.insert(new User("1", "admin", "1", "password"));
//    		System.out.println(cnt);
    		logger.info("insert.do ", cnt);
		} catch (Exception e) {
//			e.printStackTrace();
			logger.error("insert.do ", e);
		}
//    	System.out.println("insert end");
    	logger.info("insert.do end");
    	return cnt;
    }
}
