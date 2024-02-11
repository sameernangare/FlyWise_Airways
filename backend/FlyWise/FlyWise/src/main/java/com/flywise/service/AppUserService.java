
package com.flywise.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.flywise.pojos.AppUser;
import com.flywise.repository.AppUserRepository;

@Service
public class AppUserService implements UserDetailsService {
	
	@Autowired
	private AppUserRepository userDao;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		AppUser appUser = userDao.findByEmail(email);
		
		//toUser() helper method converts appUser to type User
		return appUser.toUser();
		//This User class is inherited from UserDetails
	}

}
