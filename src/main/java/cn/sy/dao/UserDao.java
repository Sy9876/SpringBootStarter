package cn.sy.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import cn.sy.domain.MyUserDetails;
import cn.sy.domain.User;
import cn.sy.mapper.UserMapper;

@Repository
public class UserDao implements UserDetailsService {
	private static Logger logger = LoggerFactory.getLogger(UserDetailsService.class);
	
	@Autowired
	private UserMapper userMapper;
	
	public User findByName(String name) {
		return userMapper.findByName(name);
	}
	
	public int count() {
		return userMapper.count();
	}
	
	public int insert(User user) {
		return userMapper.insert(user);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.info("username: " + username);
		User user = findByName(username);
		if(user==null) {
			logger.info("throw new UsernameNotFoundException username: " + username);
			throw new UsernameNotFoundException(username);
		}
		return new MyUserDetails(user);
	}
}
