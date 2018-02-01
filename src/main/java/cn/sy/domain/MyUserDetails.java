package cn.sy.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

public class MyUserDetails extends User implements UserDetails {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2978047440845128709L;

	private final String AccountEnabled = "1";
	private final String AccountDisabled = "0";
	private final String AccountExpired = "2";
	private final String AccountLocked = "3";

	public MyUserDetails(String id, String name, String status, String password) {
		super(id, name, status, password);
	}

	public MyUserDetails(User user) {
		this(user.id, user.name, user.status, user.password);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
		
		authorities.add(new SimpleGrantedAuthority("USER"));
		return authorities;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.name;
	}

	@Override
	public boolean isAccountNonExpired() {
		if(this.status.equals(AccountExpired)) {
			return false;
		}
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		if(this.status.equals(AccountLocked)) {
			return false;
		}
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		if(!this.status.equals(AccountEnabled)) {
			return false;
		}
		return true;
	}


}
