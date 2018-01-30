package cn.sy.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
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
	AuthenticationManager auth;
	
    @ResponseBody
    @RequestMapping("/login.do")
    public User login(
			@RequestParam(value="name", required=true) String name,
			@RequestParam(value="password", required=true) String password) {
    	
    	User user = null;

    	logger.info("login.do start. name=" + name + " password=" + password);
    	try {
    		user=userDao.findByName(name);
    		
    		try {
	    		Authentication request = new UsernamePasswordAuthenticationToken(name, password);
	    		logger.info("login.do", request);
	    		Authentication result = auth.authenticate(request);
	    		logger.info("login.do", result);
	    		SecurityContextHolder.getContext().setAuthentication(result);

			} catch(AuthenticationException e) {
				System.out.println("Authentication failed: " + e.getMessage());
				throw e;
    		}

    		System.out.println("Successfully authenticated. Security context contains: " +
    		SecurityContextHolder.getContext().getAuthentication());



    		logger.info("login.do", user);
		} catch (Exception e) {
			logger.error("login.do", e);
			throw e;
		}
    	logger.info("login.do end");
    	return user;
    }
    
    @ResponseBody
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
			throw e;
		}
//    	System.out.println("user end");
    	logger.info("user.do end");
    	return user;
    }

    @ResponseBody
    @RequestMapping("/void.do")
    public void empty() {

    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	logger.info("void.do principal: " + authentication.isAuthenticated());
    	logger.info("void.do user: " + authentication.getName());
    	//    	System.out.println("void.do start.");
    	logger.info("void.do end");
    }
    
    @ResponseBody
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
